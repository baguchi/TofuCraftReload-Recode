package baguchi.tofucraft.world.gen;

import com.mojang.serialization.DataResult;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class TofuNoiseRouterData {
	protected static final NoiseSettings OVERWORLD_NOISE_SETTINGS = create(-64, 384, 1, 2);

	private static final ResourceKey<DensityFunction> ZERO = createKey("zero");
	private static final ResourceKey<DensityFunction> Y = createKey("y");
	private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
	private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
	public static final ResourceKey<DensityFunction> CONTINENTS = createKey("overworld/continents");
	public static final ResourceKey<DensityFunction> EROSION = createKey("overworld/erosion");
	public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");

	public static final ResourceKey<DensityFunction> OFFSET = createKey("overworld/offset");
	public static final ResourceKey<DensityFunction> FACTOR = createKey("overworld/factor");
	public static final ResourceKey<DensityFunction> DEPTH = createKey("overworld/depth");
	private static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("overworld/sloped_cheese");

	private static final ResourceKey<DensityFunction> SLOPED_CHEESE_END = createKey("end/sloped_cheese");
	private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey("overworld/caves/spaghetti_roughness_function");
	private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
	private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
	private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
	private static final ResourceKey<DensityFunction> SPAGHETTI_2D_THICKNESS_MODULATOR = createKey("overworld/caves/spaghetti_2d_thickness_modulator");
	private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

	public static NoiseSettings create(int minY, int height, int noiseSizeHorizontal, int noiseSizeVertical) {
		NoiseSettings noiseSettings = new NoiseSettings(minY, height, noiseSizeHorizontal, noiseSizeVertical);
		guardY(noiseSettings).error().ifPresent((error) -> {
			throw new IllegalStateException(error.message());
		});
		return noiseSettings;
	}

	private static DataResult<NoiseSettings> guardY(NoiseSettings dimensionType) {
		if (dimensionType.minY() + dimensionType.height() > DimensionType.MAX_Y + 1) {
			return DataResult.error(() -> "min_y + height cannot be higher than: " + (DimensionType.MAX_Y + 1));
		} else if (dimensionType.height() % 16 != 0) {
			return DataResult.error(() -> "height has to be a multiple of 16");
		} else {
			return dimensionType.minY() % 16 != 0 ? DataResult.error(() -> "min_y has to be a multiple of 16") : DataResult.success(dimensionType);
		}
	}

	private static ResourceKey<DensityFunction> createKey(String name) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace(name));
	}

	public static NoiseRouter overworld(HolderGetter<DensityFunction> functions, HolderGetter<NormalNoise.NoiseParameters> noises, boolean largeBiomes, boolean amplified) {
		DensityFunction barrierNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_BARRIER), (double) 0.5F);
		DensityFunction fluidLevelFloodednessNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67);
		DensityFunction fluidLevelSpreadNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
		DensityFunction lavaNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_LAVA));
		DensityFunction shiftX = getFunction(functions, SHIFT_X);
		DensityFunction shiftZ = getFunction(functions, SHIFT_Z);
		DensityFunction temperature = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, (double) 0.25F, noises.getOrThrow(largeBiomes ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE));
		DensityFunction vegetation = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, (double) 0.25F, noises.getOrThrow(largeBiomes ? Noises.VEGETATION_LARGE : Noises.VEGETATION));
		DensityFunction offset = getFunction(functions, OFFSET);
		DensityFunction factor = getFunction(functions, FACTOR);
		DensityFunction depth = getFunction(functions, DEPTH);
		DensityFunction preliminarySurfaceLevel = preliminarySurfaceLevel(offset, factor, amplified);
		DensityFunction slopedCheese = getFunction(functions, SLOPED_CHEESE);
		DensityFunction surfaceWithEntrances = DensityFunctions.min(slopedCheese, DensityFunctions.mul(DensityFunctions.constant((double) 5.0F), getFunction(functions, ENTRANCES)));
		DensityFunction caves = DensityFunctions.rangeChoice(slopedCheese, (double) -1000000.0F, (double) 1.5625F, surfaceWithEntrances, underground(functions, noises, slopedCheese));
		DensityFunction fullNoise = DensityFunctions.min(postProcess(slideOverworld(amplified, caves)), getFunction(functions, NOODLE));
		DensityFunction y = getFunction(functions, Y);
		return new NoiseRouter(barrierNoise, fluidLevelFloodednessNoise, fluidLevelSpreadNoise, DensityFunctions.zero(), temperature, vegetation, getFunction(functions, CONTINENTS), getFunction(functions, EROSION), depth, getFunction(functions, RIDGES), preliminarySurfaceLevel, fullNoise, DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero());
	}

	private static DensityFunction underground(HolderGetter<DensityFunction> functions, HolderGetter<NormalNoise.NoiseParameters> noises, DensityFunction slopedCheese) {
		DensityFunction spaghetti2DFunction = getFunction(functions, SPAGHETTI_2D);
		DensityFunction spaghettiRoughnessFunction = getFunction(functions, SPAGHETTI_ROUGHNESS_FUNCTION);
		DensityFunction layerNoiseSource = DensityFunctions.noise(noises.getOrThrow(Noises.CAVE_LAYER), (double) 8.0F);
		DensityFunction layerizedCavernsFunction = DensityFunctions.mul(DensityFunctions.constant((double) 4.0F), layerNoiseSource.square());
		DensityFunction cheese = DensityFunctions.noise(noises.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666);
		DensityFunction solidifedCheeseWithTopSlide = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27), cheese).clamp((double) -1.0F, (double) 1.0F), DensityFunctions.add(DensityFunctions.constant((double) 1.5F), DensityFunctions.mul(DensityFunctions.constant(-0.64), slopedCheese)).clamp((double) 0.0F, (double) 0.5F));
		DensityFunction baseCaveDensity = DensityFunctions.add(layerizedCavernsFunction, solidifedCheeseWithTopSlide);
		DensityFunction undergroundSubtractions = DensityFunctions.min(DensityFunctions.min(baseCaveDensity, getFunction(functions, ENTRANCES)), DensityFunctions.add(spaghetti2DFunction, spaghettiRoughnessFunction));
		DensityFunction pillarsWithoutCutoff = getFunction(functions, PILLARS);
		DensityFunction pillars = DensityFunctions.rangeChoice(pillarsWithoutCutoff, (double) -1000000.0F, 0.03, DensityFunctions.constant((double) -1000000.0F), pillarsWithoutCutoff);
		return DensityFunctions.max(undergroundSubtractions, pillars);
	}

	private static DensityFunction postProcess(DensityFunction slide) {
		DensityFunction blended = DensityFunctions.blendDensity(slide);
		return DensityFunctions.mul(DensityFunctions.interpolated(blended), DensityFunctions.constant(0.64)).squeeze();
	}

	private static DensityFunction remap(DensityFunction input, double fromMin, double fromMax, double toMin, double toMax) {
		double factor = (toMax - toMin) / (fromMax - fromMin);
		double offset = toMin - fromMin * factor;
		return DensityFunctions.add(DensityFunctions.mul(input, DensityFunctions.constant(factor)), DensityFunctions.constant(offset));
	}

	private static DensityFunction offsetToDepth(DensityFunction offset) {
		return DensityFunctions.add(DensityFunctions.yClampedGradient(-64, 320, 1.5, -1.5), offset);
	}

	private static DensityFunction preliminarySurfaceLevel(DensityFunction offset, DensityFunction factor, boolean amplified) {
		DensityFunction cachedFactor = DensityFunctions.cache2d(factor);
		DensityFunction cachedOffset = DensityFunctions.cache2d(offset);
		DensityFunction upperBound = remap(DensityFunctions.add(DensityFunctions.mul(DensityFunctions.constant((double) 0.2734375F), cachedFactor.invert()), DensityFunctions.mul(DensityFunctions.constant((double) -1.0F), cachedOffset)), (double) 1.5F, (double) -1.5F, (double) -64.0F, (double) 320.0F);
		upperBound = upperBound.clamp((double) -40.0F, (double) 320.0F);
		DensityFunction density = DensityFunctions.add(slideOverworld(amplified, DensityFunctions.add(noiseGradientDensity(cachedFactor, offsetToDepth(cachedOffset)), DensityFunctions.constant((double) -0.703125F)).clamp((double) -64.0F, (double) 64.0F)), DensityFunctions.constant((double) -0.390625F));
		return DensityFunctions.findTopSurface(density, upperBound, -64, OVERWORLD_NOISE_SETTINGS.getCellHeight());
	}

	private static DensityFunction noiseGradientDensity(DensityFunction factor, DensityFunction depthWithJaggedness) {
		DensityFunction gradientUnscaled = DensityFunctions.mul(depthWithJaggedness, factor);
		return DensityFunctions.mul(DensityFunctions.constant(4.0), gradientUnscaled.quarterNegative());
	}


	private static DensityFunction slideOverworld(boolean isAmplified, DensityFunction caves) {
		return slide(caves, -64, 384, isAmplified ? 16 : 80, isAmplified ? 0 : 64, (double) -0.078125F, 0, 24, isAmplified ? 0.4 : (double) 0.1171875F);
	}

	private static DensityFunction slide(DensityFunction caves, int minY, int height, int topStartY, int topEndY, double topTarget, int bottomStartY, int bottomEndY, double bottomTarget) {
		DensityFunction topFactor = DensityFunctions.yClampedGradient(minY + height - topStartY, minY + height - topEndY, (double) 1.0F, (double) 0.0F);
		DensityFunction noiseValue = DensityFunctions.lerp(topFactor, topTarget, caves);
		DensityFunction bottomFactor = DensityFunctions.yClampedGradient(minY + bottomStartY, minY + bottomEndY, (double) 0.0F, (double) 1.0F);
		return DensityFunctions.lerp(bottomFactor, bottomTarget, noiseValue);
	}

	private static DensityFunction getFunction(HolderGetter<DensityFunction> functions, ResourceKey<DensityFunction> name) {
		return new DensityFunctions.HolderHolder(functions.getOrThrow(name));
	}
}

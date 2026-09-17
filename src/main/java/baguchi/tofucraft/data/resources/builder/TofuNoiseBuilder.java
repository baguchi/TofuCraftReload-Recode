package baguchi.tofucraft.data.resources.builder;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuNoiseSettings;
import baguchi.tofucraft.world.TofuMaterialRules;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.OverworldFunctionSet;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.Optional;

public class TofuNoiseBuilder {
	public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");

	public static NoiseGeneratorSettings tofuWorld(BootstrapContext<NoiseGeneratorSettings> context) {
		HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
		HolderGetter<NormalNoise> noises = context.lookup(Registries.NOISE);
		NoiseRouter router = overworld(functions, NoiseRouterData.OVERWORLD_FUNCTIONS);
		Holder<DensityFunction> weirdness = functions.getOrThrow(NoiseRouterData.RIDGES);
		OverworldFunctionSet<ResourceKey<DensityFunction>> functionNames = NoiseRouterData.OVERWORLD_FUNCTIONS;
		return new NoiseGeneratorSettings(new NoiseSettings(-64, 384), TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), TofuBlocks.SOYMILK.get().defaultBlockState(),
				router,
				context.lookup(Registries.MATERIAL_RULE).getOrThrow(TofuMaterialRules.TOFU_WORLD), List.of(), 63, false, Optional.of(overworldAquifers(functions, noises, functionNames)), false
				, new NoiseGeneratorSettings.DebugFunctions(
				List.of(
						new NoiseGeneratorSettings.DebugFunctionEntry("N", router.finalDensity()),
						new NoiseGeneratorSettings.DebugFunctionEntry("T", router.temperature()),
						new NoiseGeneratorSettings.DebugFunctionEntry("V", router.vegetation()),
						new NoiseGeneratorSettings.DebugFunctionEntry("C", router.continents()),
						new NoiseGeneratorSettings.DebugFunctionEntry("E", router.erosion()),
						new NoiseGeneratorSettings.DebugFunctionEntry("D", router.depth()),
						new NoiseGeneratorSettings.DebugFunctionEntry("W", router.ridges()),
						new NoiseGeneratorSettings.DebugFunctionEntry("PV", NoiseRouterData.peaksAndValleys(router.ridges())),
						new NoiseGeneratorSettings.DebugFunctionEntry("PS", NoiseRouterData.getFunction(functions, functionNames.preliminarySurfaceLevel()))
				)
		));
	}

	protected static Aquifer.Config overworldAquifers(
			HolderGetter<DensityFunction> functions, HolderGetter<NormalNoise> noises, OverworldFunctionSet<ResourceKey<DensityFunction>> names
	) {
		DensityFunction barrierNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_BARRIER), 0.5);
		DensityFunction fluidLevelFloodednessNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67);
		DensityFunction fluidLevelSpreadNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
		DensityFunction lavaNoise = DensityFunctions.noise(noises.getOrThrow(Noises.AQUIFER_LAVA));
		DensityFunction exclusion = OverworldBiomeBuilder.deepDarkRegion(getFunction(functions, names.erosion()), getFunction(functions, names.depth()));
		return new Aquifer.Config(
				barrierNoise, fluidLevelFloodednessNoise, fluidLevelSpreadNoise, lavaNoise, exclusion, getFunction(functions, names.preliminarySurfaceLevel())
		);
	}


	protected static NoiseRouter overworld(HolderGetter<DensityFunction> functions, OverworldFunctionSet<ResourceKey<DensityFunction>> functionNames) {
		OverworldFunctionSet<DensityFunction> functionSet = functionNames.map(key -> getFunction(functions, key));
		return new NoiseRouter(
				functionSet.temperature(),
				functionSet.vegetation(),
				functionSet.continents(),
				functionSet.erosion(),
				functionSet.depth(),
				getFunction(functions, RIDGES),
				functionSet.chunkSurfaceLevel(),
				functionSet.finalDensity()
		);
	}

	public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> p_256365_) {
		p_256365_.register(TofuNoiseSettings.TOFU_WORLD, TofuNoiseBuilder.tofuWorld(p_256365_));
	}

	private static ResourceKey<DensityFunction> createKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.parse(p_209537_));
	}

	private static ResourceKey<DensityFunction> createModKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, p_209537_));
	}


	private static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_, ResourceKey<DensityFunction> p_256077_) {
		return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
	}

}

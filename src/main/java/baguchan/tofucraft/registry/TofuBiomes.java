package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.data.resources.TofuBiomeBuilders;
import baguchan.tofucraft.world.biome.TofuBiomeBuilder;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Function;

public class TofuBiomes {
	public static final ResourceKey<Biome> TOFU_PLAINS = register("tofu_plains");
	public static final ResourceKey<Biome> TOFU_FOREST = register("tofu_forest");
	public static final ResourceKey<Biome> TOFU_WASTES = register("tofu_waste");
	public static final ResourceKey<Biome> TOFU_MOUNTAIN = register("tofu_mountain");

	public static final ResourceKey<Biome> ZUNDA_FOREST = register("zunda_forest");
	public static final ResourceKey<Biome> SOYBEAN_FOREST = register("soybean_forest");
	public static final ResourceKey<Biome> SOYBEAN_FOREST_SPARSE = register("soybean_forest_sparse");

	public static final ResourceKey<Biome> TOFU_OCEAN = register("tofu_ocean");
	public static final ResourceKey<Biome> TOFU_RIVER = register("tofu_river");
	public static final ResourceKey<Biome> TOFU_BEACH = register("tofu_beach");


	public static final ResourceKey<MultiNoiseBiomeSourceParameterList> TOFU_WORLD = registerPreset("tofu_world");

	public static final MultiNoiseBiomeSourceParameterList.Preset TOFU_WORLD_PRESET = new MultiNoiseBiomeSourceParameterList.Preset(new ResourceLocation(TofuCraftReload.MODID, "tofu_world"), new MultiNoiseBiomeSourceParameterList.Preset.SourceProvider() {
		public <T> Climate.ParameterList<T> apply(Function<ResourceKey<Biome>, T> p_275530_) {
			return generateTofuBiomes(p_275530_);
		}
	});

	static <T> Climate.ParameterList<T> generateTofuBiomes(Function<ResourceKey<Biome>, T> p_275249_) {
		ImmutableList.Builder<Pair<Climate.ParameterPoint, T>> builder = ImmutableList.builder();
		(new TofuBiomeBuilder()).addBiomes((p_275579_) -> {
			builder.add(p_275579_.mapSecond(p_275249_));
		});
		return new Climate.ParameterList<>(builder.build());
	}

	private static ResourceKey<MultiNoiseBiomeSourceParameterList> registerPreset(String p_275281_) {
		return ResourceKey.create(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, new ResourceLocation(TofuCraftReload.MODID, p_275281_));
	}

	public static void bootstrapPreset(BootstapContext<MultiNoiseBiomeSourceParameterList> p_275387_) {
		HolderGetter<Biome> holdergetter = p_275387_.lookup(Registries.BIOME);
		p_275387_.register(TOFU_WORLD, new MultiNoiseBiomeSourceParameterList(TOFU_WORLD_PRESET, holdergetter));
	}


	public static void init() {

	}

	public static void bootstrap(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> vanillaConfiguredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(SOYBEAN_FOREST, TofuBiomeBuilders.soybeanForestBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(SOYBEAN_FOREST_SPARSE, TofuBiomeBuilders.soybeanForestSpareBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(ZUNDA_FOREST, TofuBiomeBuilders.zundaForestBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_PLAINS, TofuBiomeBuilders.tofuPlainBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_FOREST, TofuBiomeBuilders.tofuForestBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_WASTES, TofuBiomeBuilders.tofuWasteBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_MOUNTAIN, TofuBiomeBuilders.tofuMountainBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_OCEAN, TofuBiomeBuilders.tofuOceanBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_BEACH, TofuBiomeBuilders.tofuBeachBiome(placedFeatures, vanillaConfiguredCarvers));
		context.register(TOFU_RIVER, TofuBiomeBuilders.tofuRiverBiome(placedFeatures, vanillaConfiguredCarvers));
	}

	private static ResourceKey<Biome> register(String p_48229_) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(TofuCraftReload.MODID, p_48229_));
	}
}

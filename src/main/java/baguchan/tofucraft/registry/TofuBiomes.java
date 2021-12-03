package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class TofuBiomes {
	public static final ResourceKey<Biome> TOFU_PLAINS = key("tofu_plains");
	public static final ResourceKey<Biome> TOFU_FOREST = key("tofu_forest");
	public static final ResourceKey<Biome> TOFU_WASTE = key("tofu_waste");
	public static final ResourceKey<Biome> TOFU_OCEAN = key("tofu_ocean");

	private static ResourceKey<Biome> key(String key) {
		ResourceKey<Biome> rk = ResourceKey.create(Registry.BIOME_REGISTRY, TofuCraftReload.prefix(key));
		return rk;
	}

	/*private static Biome tofuPlainsBiome() {
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();

		TofuDefaultFeatures.addCommonOreGeneration(biomegenerationsettings$builder);
		TofuDefaultFeatures.addCommonCaveGeneration(biomegenerationsettings$builder);

		//biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_PLAINS);

		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		TofuDefaultFeatures.tofuCommonSpawn(mobspawnsettings$builder);
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.PLAINS).temperature(0.7F).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(8037887).backgroundMusic(TofuMusics.SOFT).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
	}

	private static Biome tofuForestBiome() {
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();

		TofuDefaultFeatures.addCommonOreGeneration(biomegenerationsettings$builder);
		TofuDefaultFeatures.addCommonCaveGeneration(biomegenerationsettings$builder);

		//biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_FOREST);

		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		TofuDefaultFeatures.tofuCommonSpawn(mobspawnsettings$builder);
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST).temperature(0.7F).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(8037887).backgroundMusic(TofuMusics.MILKY_EARTH).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
	}

	private static Biome tofuOceanBiome() {
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();

		TofuDefaultFeatures.addCommonOreGeneration(biomegenerationsettings$builder);
		TofuDefaultFeatures.addCommonCaveGeneration(biomegenerationsettings$builder);

		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		TofuDefaultFeatures.tofuCommonSpawn(mobspawnsettings$builder);
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.OCEAN).temperature(0.7F).downfall(0.85F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(8037887).backgroundMusic(TofuMusics.MILKY_EARTH).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
	}

	private static Biome tofuWasteBiome() {
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();

		TofuDefaultFeatures.addLeekGeneration(biomegenerationsettings$builder);
		TofuDefaultFeatures.addCommonOreGeneration(biomegenerationsettings$builder);
		TofuDefaultFeatures.addCommonCaveGeneration(biomegenerationsettings$builder);

		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		TofuDefaultFeatures.tofuCommonSpawn(mobspawnsettings$builder);
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.OCEAN).temperature(0.6F).downfall(0.65F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(8037887).backgroundMusic(TofuMusics.ROUGH_GROUND).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
	}

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> registry) {
		registry.getRegistry().registerAll(
				tofuPlainsBiome().setRegistryName("tofu_plains"),
				tofuForestBiome().setRegistryName("tofu_forest"),
				tofuWasteBiome().setRegistryName("tofu_waste"),
				tofuOceanBiome().setRegistryName("tofu_ocean")
		);
	}*/
}

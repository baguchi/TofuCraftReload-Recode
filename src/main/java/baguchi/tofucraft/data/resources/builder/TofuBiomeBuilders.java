package baguchi.tofucraft.data.resources.builder;

import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.world.biome.TofuBiomeDefaultFeatures;
import baguchi.tofucraft.world.gen.placement.TofuWorldPlacements;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TofuBiomeBuilders {
	public static Biome zundaForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addZundaForestFeatures(builder);
		TofuBiomeDefaultFeatures.addDefaultCarvers(builder);
		TofuBiomeDefaultFeatures.addDefaultOres(builder);
		TofuBiomeDefaultFeatures.tofuCreatureSpawns(builder1);
		return fullDefinition(
				0.8F,
				0.0F,
				new BiomeSpecialEffects.Builder()
						.waterColor(0xDCF6E5)
						.grassColorOverride(7115607)
						.foliageColorOverride(7115607)
						.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
						.build(),
				builder1.build(),
				builder.build(),
				Biome.TemperatureModifier.NONE
		).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.GREEN_BRANCH_BGM)).build();
	}

	public static Biome soybeanForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addSoybeanForestFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.MILKY_EARTH_BGM)).build();
	}

	public static Biome soybeanForestSpareBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addSoybeanForestSpareFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.MILKY_EARTH_BGM)).build();
	}

	public static Biome tofuWasteBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addWasteFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.ROUGH_GROUND_BGM)).build();
	}

	public static Biome tofuBeachBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.MILKY_EARTH_BGM)).build();
	}

	public static Biome tofuForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addForestFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.SOFT_BGM)).build();
	}

	public static Biome tofuPlainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.TOFU_ROAD_BGM)).build();
	}

	public static Biome tofuRiverBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		TofuBiomeDefaultFeatures.tofuWaterCreatureSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.TOFU_ROAD_BGM)).build();
	}

	public static Biome tofuOceanBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		TofuBiomeDefaultFeatures.tofuWaterCreatureSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.TOFU_ROAD_BGM)).build();
	}

	public static Biome tofuMountainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addMountainFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.ROUGH_GROUND_BGM)).build();
	}

	public static Biome mabouMountainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_MINCED_TOFU);

		TofuBiomeDefaultFeatures.addMabouMountainFeatures(builder);
		builder1.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(TofuEntityTypes.OAGE_CUBE.get(), 3, 4));
		//builder1.addMobCharge(TofuEntityTypes.OAGE_CUBE.get(), 0.8, 0.1F);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuSounds.ROUGH_GROUND_BGM)).build();
	}


	public static Biome.BiomeBuilder makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting) {
		TofuBiomeDefaultFeatures.addDefaultCarvers(builder);
		TofuBiomeDefaultFeatures.addDefaultOres(builder);
		TofuBiomeDefaultFeatures.tofuCreatureSpawns(mobSpawnSetting);
		return fullDefinition(
				0.8F,
				0.0F,
				new BiomeSpecialEffects.Builder()
						.waterColor(0xBBDAF0)
						.grassColorOverride(7115607)
						.foliageColorOverride(7115607)
						.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
						.build(),
				mobSpawnSetting.build(),
				builder.build(),
				Biome.TemperatureModifier.NONE
		);
	}

	public static Biome.BiomeBuilder fullDefinition(float temperature, float downfall, BiomeSpecialEffects effects, MobSpawnSettings spawnSettings, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
		return new Biome.BiomeBuilder()
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects(effects)
				.mobSpawnSettings(spawnSettings)
				.generationSettings(generationSettings)
				.temperatureAdjustment(temperatureModifier);
	}
}

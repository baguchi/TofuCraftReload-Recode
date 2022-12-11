package baguchan.tofucraft.world.biome;

import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TofuBiomeBuilders {
	public static Biome zundaForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addZundaForestFeatures(builder);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome soybeanForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addSoybeanForestFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome soybeanForestSpareBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addSoybeanForestSpareFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuWasteBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addWasteFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuBeachBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addForestFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuPlainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuRiverBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		TofuBiomeDefaultFeatures.tofuWaterCreatureSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuOceanBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		TofuBiomeDefaultFeatures.tofuWaterCreatureSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuMountainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting) {
		TofuBiomeDefaultFeatures.addDefaultCarvers(builder);
		TofuBiomeDefaultFeatures.addDefaultOres(builder);
		TofuBiomeDefaultFeatures.tofuCreatureSpawns(mobSpawnSetting);
		return fullDefinition(
				Biome.Precipitation.NONE,
				0.8F,
				0.0F,
				new BiomeSpecialEffects.Builder()
						.fogColor(0x93_93_bc)
						.skyColor(0xc0_c0_ff)
						.waterColor(0x3f_76_e4)
						.waterFogColor(0x05_05_33)
						.grassColorOverride(0xb1_ff_cb)
						.foliageColorOverride(0xb1_ff_cb)
						.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
						.backgroundMusic(new Music(TofuSounds.MILKY_EARTH_BGM.getHolder().orElseThrow(), 12000, 24000, true))
						.build(),
				mobSpawnSetting.build(),
				builder.build(),
				Biome.TemperatureModifier.NONE
		);
	}

	public static Biome fullDefinition(Biome.Precipitation precipitation, float temperature, float downfall, BiomeSpecialEffects effects, MobSpawnSettings spawnSettings, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
		return new Biome.BiomeBuilder()
				.precipitation(precipitation)
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects(effects)
				.mobSpawnSettings(spawnSettings)
				.generationSettings(generationSettings)
				.temperatureAdjustment(temperatureModifier)
				.build();
	}
}

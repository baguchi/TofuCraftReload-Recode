package baguchan.tofucraft.data.resources;

import baguchan.tofucraft.registry.TofuSounds;
import baguchan.tofucraft.world.biome.TofuBiomeDefaultFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

public class TofuBiomeBuilders {
	public static Biome zundaForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addZundaForestFeatures(builder);
		return makeDefaultBiome(builder, builder1, TofuSounds.GREEN_BRANCH_BGM);
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
		return makeDefaultBiome(builder, builder1, TofuSounds.ROUGH_GROUND_BGM);
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
		return makeDefaultBiome(builder, builder1, TofuSounds.ROUGH_GROUND_BGM);
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
		TofuBiomeDefaultFeatures.addMountainFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.MILKY_EARTH_BGM);
	}

	public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting) {
		return makeDefaultBiome(builder, mobSpawnSetting, TofuSounds.SOFT_BGM);
	}

	public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting, RegistryObject<SoundEvent> soundEvent) {
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
						.grassColorOverride(7115607)
						.foliageColorOverride(7115607)
						.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
						.backgroundMusic(new Music(soundEvent.getHolder().orElseThrow(), 12000, 24000, true))
						.build(),
				mobSpawnSetting.build(),
				builder.build(),
				Biome.TemperatureModifier.NONE
		);
	}

	public static Biome fullDefinition(Biome.Precipitation precipitation, float temperature, float downfall, BiomeSpecialEffects effects, MobSpawnSettings spawnSettings, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
		return new Biome.BiomeBuilder()
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects(effects)
				.mobSpawnSettings(spawnSettings)
				.generationSettings(generationSettings)
				.temperatureAdjustment(temperatureModifier)
				.build();
	}
}

package baguchan.tofucraft.world.biome;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.world.carver.TofuConfiguredWorldCarvers;
import baguchan.tofucraft.world.placement.TofuWorldPlacements;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class TofuBiomeDefaultFeatures {
	public static void addDefaultCarvers(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addCarver(GenerationStep.Carving.AIR, TofuConfiguredWorldCarvers.CAVE);
		p_194721_.addCarver(GenerationStep.Carving.AIR, TofuConfiguredWorldCarvers.CAVE_EXTRA_UNDERGROUND);
		p_194721_.addCarver(GenerationStep.Carving.AIR, TofuConfiguredWorldCarvers.CANYON);
	}

	public static void tofuMonsterSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUSLIME.get(), 100, 4, 4));
		p_126813_.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUSPIDER.get(), 10, 4, 4));
	}

	public static void tofuCreatureSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUCOW.get(), 100, 4, 4));
		p_126813_.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUPIG.get(), 200, 4, 4));
	}

	public static void tofuWaterCreatureSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUFISH.get(), 10, 3, 6));
	}

	public static void addDefaultOres(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_BURIED);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_LARGE);
	}

	public static void addForestFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_FOREST);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addPlainsFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_PLAINS);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addWasteFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_BUILDING);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK_WASTE);
	}

	public static void addSoybeanForestSpareFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BEG_LEEK);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addSoybeanForestFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BEG_LEEK);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addZundaForestFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BIG_ZUNDA_TOFU_MUSHROOM);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_ZUNDA_TOFU_MUSHROOM);
	}
}
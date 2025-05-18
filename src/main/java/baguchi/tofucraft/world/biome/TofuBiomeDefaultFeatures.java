package baguchi.tofucraft.world.biome;

import baguchi.tofucraft.data.resources.TofuConfiguredWorldCarvers;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.world.gen.placement.TofuWorldPlacements;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class TofuBiomeDefaultFeatures {
	public static void addDefaultCarvers(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addCarver(TofuConfiguredWorldCarvers.CAVE);
		p_194721_.addCarver(TofuConfiguredWorldCarvers.CAVE_EXTRA_UNDERGROUND);
		p_194721_.addCarver(TofuConfiguredWorldCarvers.CANYON);
	}

	public static void tofuMonsterSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUSLIME.get(), 4, 4));
		p_126813_.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUCREEPER.get(), 2, 3));
		p_126813_.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUSPIDER.get(), 2, 3));
	}

	public static void tofuCreatureSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUCOW.get(), 4, 4));
		p_126813_.addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUPIG.get(), 4, 4));
	}

	public static void tofuWaterCreatureSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(MobCategory.WATER_AMBIENT, 10, new MobSpawnSettings.SpawnerData(TofuEntityTypes.TOFUFISH.get(), 3, 6));
	}

	public static void addDefaultOres(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_BURIED);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_LARGE);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_SOY_FORCE);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFUGEM);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFUGEM_LARGE);
	}

	public static void addForestFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_FOREST);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_TOFU_FLOWER);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addPlainsFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_PLAINS);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_TOFU_FLOWER);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addWasteFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_KINU_TOFU);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_MINCED_TOFU);

		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_BUILDING);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_PLAINS);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK_WASTE);
	}

	public static void addMountainFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_KINU_TOFU);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_MINCED_TOFU);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_PLAINS);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addMabouMountainFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_DELTA);
	}

	public static void addSoybeanForestSpareFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BIG_LEEK);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addSoybeanForestFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BIG_LEEK);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
	}

	public static void addZundaForestFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BIG_ZUNDA_TOFU_MUSHROOM);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_ZUNDA_TOFU_MUSHROOM);
	}
}
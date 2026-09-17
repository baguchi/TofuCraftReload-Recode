package baguchi.tofucraft.world.biome;

import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.world.gen.placement.TofuWorldPlacements;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class TofuBiomeDefaultFeatures {
	public static void addDefaultCarvers(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addCarver(Carvers.CAVE);
		p_194721_.addCarver(Carvers.CAVE_EXTRA_UNDERGROUND);
		p_194721_.addCarver(Carvers.CANYON);
	}

	public static void tofuMonsterSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(TofuEntityTypes.TOFUSLIME.get(), 100, 4, 4);
		p_126813_.addSpawn(TofuEntityTypes.TOFUCREEPER.get(), 10, 2, 3);
		p_126813_.addSpawn(TofuEntityTypes.TOFUSPIDER.get(), 30, 2, 3);
	}

	public static void tofuCreatureSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(TofuEntityTypes.TOFUCOW.get(), 10, 4, 4);
		p_126813_.addSpawn(TofuEntityTypes.TOFUPIG.get(), 20, 4, 4);
	}

	public static void tofuWaterCreatureSpawns(MobSpawnSettings.Builder p_126813_) {
		p_126813_.addSpawn(TofuEntityTypes.TOFUFISH.get(), 10, 3, 6);
	}

	public static void addDefaultOres(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_MEDIUM);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_BURIED);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_LARGE);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_SOY_FORCE);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFUGEM);
		p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFUGEM_LARGE);
	}

	public static void addCaveSproutFeatures(BiomeGenerationSettings.Builder p_194721_) {
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.BIG_SPROUT);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.SPROUT_WATER_POOL);
		p_194721_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_WILD_SPROUTS);

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
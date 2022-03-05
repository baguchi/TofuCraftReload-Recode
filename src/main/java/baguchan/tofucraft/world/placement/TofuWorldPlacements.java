package baguchan.tofucraft.world.placement;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.gen.features.TofuWorldFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class TofuWorldPlacements {
	public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

	public static final Holder<PlacedFeature> ORE_TOFU_DIAMOND = PlacementUtils.register("tofucraft:ore_tofu_diamond", TofuWorldFeatures.ORE_DIAMOND_SMALL, commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
	public static final Holder<PlacedFeature> ORE_TOFU_DIAMOND_LARGE = PlacementUtils.register("tofucraft:ore_tofu_diamond_large", TofuWorldFeatures.ORE_DIAMOND_LARGE, rareOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
	public static final Holder<PlacedFeature> ORE_TOFU_DIAMOND_BURIED = PlacementUtils.register("tofucraft:ore_tofu_diamond_buried", TofuWorldFeatures.ORE_DIAMOND_BURIED, commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

	public static final Holder<PlacedFeature> PATCH_LEEK = PlacementUtils.register("tofucraft:patch_leek", TofuWorldFeatures.LEEK, NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
	public static final Holder<PlacedFeature> PATCH_LEEK_WASTE = PlacementUtils.register("tofucraft:patch_leek_waste", TofuWorldFeatures.LEEK, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BEG_LEEK = PlacementUtils.register("tofucraft:big_leek", TofuWorldFeatures.BIG_LEEK, PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> TOFU_BUILDING = PlacementUtils.register("tofucraft:tofu_building", TofuWorldFeatures.TOFU_BUILDING, PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PATCH_ZUNDA_TOFU_MUSHROOM = PlacementUtils.register("tofucraft:patch_zunda_tofu_mushroom", TofuWorldFeatures.ZUNDA_TOFU_MUSHROOM, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BIG_ZUNDA_TOFU_MUSHROOM = PlacementUtils.register("tofucraft:big_zunda_tofu_mushroom", TofuWorldFeatures.BIG_ZUNDA_TOFU_MUSHUROOM, PlacementUtils.countExtra(3, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> TOFU_TREES_FOREST = PlacementUtils.register("tofucraft:tofu_trees_forest", TofuWorldFeatures.TOFU_TREES, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), TofuBlocks.SAPLING_TOFU.get()));
	public static final Holder<PlacedFeature> TOFU_TREES_PLAINS = PlacementUtils.register("tofucraft:tofu_trees_plains", TofuWorldFeatures.TOFU_TREES, treePlacement(PlacementUtils.countExtra(0, 0.01F, 1), TofuBlocks.SAPLING_TOFU.get()));

	public static void init() {

	}

	private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier p_195485_) {
		return ImmutableList.<PlacementModifier>builder().add(p_195485_).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
	}

	public static List<PlacementModifier> treePlacement(PlacementModifier p_195480_) {
		return treePlacementBase(p_195480_).build();
	}

	public static List<PlacementModifier> treePlacement(PlacementModifier p_195482_, Block p_195483_) {
		return treePlacementBase(p_195482_).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(p_195483_.defaultBlockState(), BlockPos.ZERO))).build();
	}

	private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
		return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
	}

	private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
		return orePlacement(CountPlacement.of(p_195344_), p_195345_);
	}

	private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
		return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
	}
}

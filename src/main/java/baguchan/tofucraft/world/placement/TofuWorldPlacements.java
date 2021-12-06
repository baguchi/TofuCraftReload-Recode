package baguchan.tofucraft.world.placement;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.gen.feature.TofuWorldFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class TofuWorldPlacements {
	public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

	public static final PlacedFeature ORE_TOFU_DIAMOND = register("tofucraft:ore_tofu_diamond", TofuWorldFeatures.ORE_DIAMOND_SMALL.placed(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
	public static final PlacedFeature ORE_TOFU_DIAMOND_LARGE = register("tofucraft:ore_tofu_diamond_large", TofuWorldFeatures.ORE_DIAMOND_LARGE.placed(rareOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
	public static final PlacedFeature ORE_TOFU_DIAMOND_BURIED = register("tofucraft:ore_tofu_diamond_buried", TofuWorldFeatures.ORE_DIAMOND_BURIED.placed(commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

	public static final PlacedFeature PATCH_LEEK = register("tofucraft:patch_leek", TofuWorldFeatures.LEEK.placed(NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

	public static final PlacedFeature TOFU_TREES_FOREST = register("tofucraft:tofu_trees_forest", TofuWorldFeatures.TOFU_TREES.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), TofuBlocks.SAPLING_TOFU)));
	public static final PlacedFeature TOFU_TREES_PLAINS = register("tofucraft:tofu_trees_plains", TofuWorldFeatures.TOFU_TREES.placed(treePlacement(PlacementUtils.countExtra(0, 0.01F, 1), TofuBlocks.SAPLING_TOFU)));

	public static void init() {

	}

	public static PlacedFeature register(String p_195369_, PlacedFeature p_195370_) {
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, p_195369_, p_195370_);
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

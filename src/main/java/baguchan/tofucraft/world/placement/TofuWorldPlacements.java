package baguchan.tofucraft.world.placement;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class TofuWorldPlacements {
	public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

	//public static final PlacedFeature TOFU_TREES_FOREST = PlacementUtils.register("tofucraft:tofu_trees_forest", TofuWorldFeatures.TOFU_TREES.placed(treePlacement(PlacementUtils.countExtra(6, 0.1F, 1))));
	//public static final PlacedFeature TOFU_TREES_PLAINS = PlacementUtils.register("tofucraft:tofu_trees_plains", TofuWorldFeatures.TOFU_TREES.placed(treePlacement(PlacementUtils.countExtra(0, 0.01F, 1))));
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
}

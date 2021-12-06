package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModTreeFeatures {
	public static final ConfiguredFeature<TreeConfiguration, ?> TOFU_TREE = register("tofucraft:tofu_tree", Feature.TREE.configured(createTofuTree().build()));
	public static final ConfiguredFeature<TreeConfiguration, ?> TOFU_TREE_BIG = register("tofucraft:tofu_tree_big", Feature.TREE.configured(createTofuTreeBig().build()));


	public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String p_195006_, ConfiguredFeature<FC, ?> p_195007_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, p_195006_, p_195007_);
	}

	private static TreeConfiguration.TreeConfigurationBuilder createTofuTree() {
		return createStraightBlobTree(TofuBlocks.ISHITOFU, TofuBlocks.LEAVES_TOFU, 4, 2, 2).ignoreVines().dirt(BlockStateProvider.simple(TofuBlocks.TOFU_TERRAIN));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createTofuTreeBig() {
		return createStraightBlobTree(TofuBlocks.ISHITOFU, TofuBlocks.LEAVES_TOFU, 5, 3, 3).ignoreVines().dirt(BlockStateProvider.simple(TofuBlocks.TOFU_TERRAIN));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block trunk, Block leaves, int trunkSize, int foliageSize, int twoLayerSize) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(trunk), new StraightTrunkPlacer(trunkSize, 2, 0), BlockStateProvider.simple(leaves), new TofuFoliagePlacer(ConstantInt.of(foliageSize), ConstantInt.of(0), foliageSize + 1), new TwoLayersFeatureSize(twoLayerSize, twoLayerSize, twoLayerSize));
	}

	public static void init() {

	}
}

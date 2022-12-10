package baguchan.tofucraft.world.gen.features;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModTreeFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_TREE = registerKey("tofu_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_TREE_BIG = registerKey("tofu_tree_big");

	public static final ResourceKey<ConfiguredFeature<?, ?>> APRICOT_TREE = registerKey("apricot_tree");

	private static TreeConfiguration.TreeConfigurationBuilder createTofuTree() {
		return createStraightBlobTree(TofuBlocks.ISHITOFU.get(), TofuBlocks.LEAVES_TOFU.get(), 4, 2, 2).ignoreVines().dirt(BlockStateProvider.simple(TofuBlocks.TOFU_TERRAIN.get()));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createTofuTreeBig() {
		return createStraightBlobTree(TofuBlocks.ISHITOFU.get(), TofuBlocks.LEAVES_TOFU.get(), 5, 3, 3).ignoreVines().dirt(BlockStateProvider.simple(TofuBlocks.TOFU_TERRAIN.get()));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block trunk, Block leaves, int trunkSize, int foliageSize, int twoLayerSize) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(trunk), new StraightTrunkPlacer(trunkSize, 2, 0), BlockStateProvider.simple(leaves), new TofuFoliagePlacer(ConstantInt.of(foliageSize), ConstantInt.of(0), foliageSize + 1), new TwoLayersFeatureSize(twoLayerSize, twoLayerSize, twoLayerSize));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createApricotTree() {
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(Blocks.OAK_LOG),
				new StraightTrunkPlacer(4, 2, 1),
				BlockStateProvider.simple(TofuBlocks.LEAVES_APRICOT.get()),
				new TofuFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(2, 1, 3)
		);

	}

	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, TOFU_TREE, Feature.TREE, createTofuTree().build());
		FeatureUtils.register(context, TOFU_TREE_BIG, Feature.TREE, createTofuTreeBig().build());
		FeatureUtils.register(context, APRICOT_TREE, Feature.TREE, createApricotTree().build());
	}
}

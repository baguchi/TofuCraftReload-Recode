package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.provider.TofuBlockStateProviders;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.world.gen.foliage.MushroomFoliagePlacer;
import baguchi.tofucraft.world.gen.foliage.NoFoliagePlacer;
import baguchi.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import baguchi.tofucraft.world.gen.treedecorators.SproutTopDecorator;
import baguchi.tofucraft.world.gen.trunk.SproutTrunkPlacer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;

public class ModTreeFeatures {
	public static final ResourceKey<Feature> TOFU_TREE = registerKey("tofu_tree");
	public static final ResourceKey<Feature> TOFU_TREE_BIG = registerKey("tofu_tree_big");

	public static final ResourceKey<Feature> ZUNDA_MUSHROOM = registerKey("zunda_mushroom");
	public static final ResourceKey<Feature> ZUNDA_MUSHROOM_BIG = registerKey("zunda_mushroom_big");

	public static final ResourceKey<Feature> SPROUT = registerKey("sprout");


	public static final ResourceKey<Feature> APRICOT_TREE = registerKey("apricot_tree");

	private static TreeFeature.Builder createTofuTree(Holder<BlockStateProvider> belowTrunkProvider) {
		return createStraightBlobTree(TofuBlocks.ISHI_TOFU_STEM.get(), TofuBlocks.LEAVES_TOFU.get(), 4, 2, belowTrunkProvider);
	}

	private static TreeFeature.Builder createTofuTreeBig(Holder<BlockStateProvider> belowTrunkProvider) {
		return createBigTree(TofuBlocks.ISHI_TOFU_STEM.get(), TofuBlocks.LEAVES_TOFU.get(), 6, 2, belowTrunkProvider);
	}


	private static TreeFeature.Builder createZunda(Holder<BlockStateProvider> belowTrunkProvider) {
		return createMushroom(TofuBlocks.TOFU_STEM.get(), TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get(), 3, 2, belowTrunkProvider);
	}

	private static TreeFeature.Builder createZundaBig(Holder<BlockStateProvider> belowTrunkProvider) {
		return createBigTrunkMushroom(TofuBlocks.TOFU_STEM.get(), TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get(), 8, 2, belowTrunkProvider);
	}

	private static TreeFeature.Builder createStraightBlobTree(Block trunk, Block leaves, int trunkSize, int foliageSize, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(new SimpleStateProvider(trunk.defaultBlockState()), new StraightTrunkPlacer(trunkSize, 2, 0), new SimpleStateProvider(leaves.defaultBlockState()), new TofuFoliagePlacer(ConstantInt.of(foliageSize), ConstantInt.of(0), foliageSize + 1), new TwoLayersFeatureSize(1, 0, 1), belowTrunkProvider);
	}

	private static TreeFeature.Builder createBigTrunkMushroom(Block trunk, Block leaves, int trunkSize, int foliageSize, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(new SimpleStateProvider(trunk.defaultBlockState()), new FancyTrunkPlacer(trunkSize, 4, 0), new SimpleStateProvider(leaves.defaultBlockState()), new MushroomFoliagePlacer(ConstantInt.of(foliageSize), ConstantInt.of(1), foliageSize + 1), new TwoLayersFeatureSize(1, 0, 1), belowTrunkProvider);
	}

	private static TreeFeature.Builder createMushroom(Block trunk, Block leaves, int trunkSize, int foliageSize, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(new SimpleStateProvider(trunk.defaultBlockState()), new StraightTrunkPlacer(trunkSize, 2, 0), new SimpleStateProvider(leaves.defaultBlockState()), new MushroomFoliagePlacer(ConstantInt.of(foliageSize), ConstantInt.of(0), foliageSize + 1), new TwoLayersFeatureSize(1, 0, 1), belowTrunkProvider);
	}

	private static TreeFeature.Builder createBigTree(Block trunk, Block leaves, int trunkSize, int foliageSize, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(new SimpleStateProvider(trunk.defaultBlockState()), new FancyTrunkPlacer(trunkSize, 8, 0), new SimpleStateProvider(leaves.defaultBlockState()), new TofuFoliagePlacer(ConstantInt.of(foliageSize), ConstantInt.of(2), foliageSize + 1), new TwoLayersFeatureSize(1, 0, 1), belowTrunkProvider);
	}

	private static TreeFeature.Builder createSprout(Block trunk, int trunkSize, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(new SimpleStateProvider(trunk.defaultBlockState()), new SproutTrunkPlacer(trunkSize, 4, 4), new SimpleStateProvider(Blocks.AIR.defaultBlockState()), new NoFoliagePlacer(), new TwoLayersFeatureSize(1, 0, 1), belowTrunkProvider).decorators(List.of(new SproutTopDecorator()));
	}

	private static TreeFeature.Builder createApricotTree(Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				new SimpleStateProvider(Blocks.OAK_LOG.defaultBlockState()),
				new StraightTrunkPlacer(4, 2, 1),
				new SimpleStateProvider(TofuBlocks.LEAVES_APRICOT.get().defaultBlockState()),
				new TofuFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3)
				, Optional.empty(), new TwoLayersFeatureSize(1, 0, 1)
				, belowTrunkProvider);

	}

	public static ResourceKey<Feature> registerKey(String name) {
		return ResourceKey.create(Registries.FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Feature> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
		HolderGetter<BlockStateProvider> blockStateProviders = context.lookup(Registries.BLOCK_STATE_PROVIDER);

		Holder<BlockStateProvider> belowTrunkProvider = blockStateProviders.getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);
		Holder<BlockStateProvider> belowTofuTrunkProvider = blockStateProviders.getOrThrow(TofuBlockStateProviders.SOIL_BENEATH_TOFU_TREE);


		context.register(TOFU_TREE, createTofuTree(belowTofuTrunkProvider).ignoreVines().build());
		context.register(TOFU_TREE_BIG, createTofuTreeBig(belowTofuTrunkProvider).ignoreVines().build());
		context.register(APRICOT_TREE, createApricotTree(belowTrunkProvider).ignoreVines().build());
		context.register(ZUNDA_MUSHROOM, createZunda(belowTofuTrunkProvider).ignoreVines().build());
		context.register(ZUNDA_MUSHROOM_BIG, createZundaBig(belowTofuTrunkProvider).ignoreVines().build());
		context.register(SPROUT, createSprout(TofuBlocks.SPROUT_STEM.get(), 2, belowTofuTrunkProvider).ignoreVines().build());
	}
}

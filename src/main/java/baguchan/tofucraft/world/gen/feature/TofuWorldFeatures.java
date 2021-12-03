package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class TofuWorldFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN);
	public static final RuleTest TOFUSLATE_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFUSLATE);

	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.defaultBlockState()), OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.defaultBlockState()));

	public static final ConfiguredFeature<?, ?> ORE_DIAMOND_SMALL = FeatureUtils.register("tofucraft:ore_tofu_diamond_small", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 4, 0.5F)));
	public static final ConfiguredFeature<?, ?> ORE_DIAMOND_LARGE = FeatureUtils.register("tofucraft:ore_tofu_diamond_large", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 12, 0.7F)));
	public static final ConfiguredFeature<?, ?> ORE_DIAMOND_BURIED = FeatureUtils.register("tofucraft:ore_tofu_diamond_buried", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 8, 1.0F)));

	public static final ConfiguredFeature<?, ?> LEEK = FeatureUtils.register("tofucraft:leek", Feature.RANDOM_PATCH.configured(grassPatch(BlockStateProvider.simple(TofuBlocks.LEEK), 32)));

	public static final ConfiguredFeature<RandomFeatureConfiguration, ?> TOFU_TREES = FeatureUtils.register("tofucraft:tofu_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ModTreeFeatures.TOFU_TREE_BIG.placed(), 0.33333334F)), ModTreeFeatures.TOFU_TREE.placed())));

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(p_195203_)).onlyWhenEmpty());
	}

	public static void init() {

	}
}
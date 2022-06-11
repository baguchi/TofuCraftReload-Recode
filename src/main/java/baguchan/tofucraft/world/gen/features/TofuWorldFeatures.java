package baguchan.tofucraft.world.gen.features;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class TofuWorldFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN.get());
	public static final RuleTest TOFUSLATE_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFUSLATE.get());

	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.get().defaultBlockState()), OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get().defaultBlockState()));
	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_TOFUGEM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFUGEM.get().defaultBlockState()));


	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DIAMOND_SMALL = FeatureUtils.register("tofucraft:ore_tofu_diamond_small", Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 5, 0.2F));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DIAMOND_LARGE = FeatureUtils.register("tofucraft:ore_tofu_diamond_large", Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 12, 0.5F));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DIAMOND_BURIED = FeatureUtils.register("tofucraft:ore_tofu_diamond_buried", Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 8, 1.0F));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_TOFUGEM_SMALL = FeatureUtils.register("tofucraft:ore_tofugem_small", Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 6));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_TOFUGEM_LARGE = FeatureUtils.register("tofucraft:ore_tofugem_large", Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 12));


	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> LEEK = FeatureUtils.register("tofucraft:leek", Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.LEEK.get()), 32));
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BIG_LEEK = FeatureUtils.register("tofucraft:big_leek", TofuFeatures.BIG_LEEK.get(), RandomFeatureConfiguration.NONE);

	public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> TOFU_BUILDING = FeatureUtils.register("tofucraft:tofu_building", TofuFeatures.TOFU_BUILDING.get(), new BlockStateConfiguration(TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()));

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ZUNDA_TOFU_MUSHROOM = FeatureUtils.register("tofucraft:zunda_tofu_mushroom", Feature.FLOWER, grassPatch(BlockStateProvider.simple(TofuBlocks.ZUNDATOFU_MUSHROOM.get()), 32));

	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BIG_ZUNDA_TOFU_MUSHUROOM = FeatureUtils.register("tofucraft:big_zunda_tofu_mushroom", TofuFeatures.BIG_ZUNDA_TOFU_MUSHROOM.get(), RandomFeatureConfiguration.NONE);


	public static final Holder<PlacedFeature> TOFU_CHECKED = PlacementUtils.register("tofucraft:tofu_checked", ModTreeFeatures.TOFU_TREE, PlacementUtils.filteredByBlockSurvival(TofuBlocks.SAPLING_TOFU.get()));


	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TOFU_TREES = FeatureUtils.register("tofucraft:tofu_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(ModTreeFeatures.TOFU_TREE_BIG), 0.33333334F)), TOFU_CHECKED));

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}

	public static void init() {

	}
}
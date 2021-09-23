package baguchan.tofucraft.registry;

import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class TofuConfiguredFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN);
	public static final RuleTest TOFUSLATE_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFUSLATE);

	public static RandomPatchConfiguration NETHER_SOYBEAN_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.SOYBEAN_NETHER.defaultBlockState().setValue(SoybeanNetherCropsBlock.AGE, 7)), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static RandomPatchConfiguration SOUL_SOYBEAN_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.SOYBEAN_SOUL.defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7)), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static RandomPatchConfiguration LEEK_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.LEEK.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.defaultBlockState()), OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.defaultBlockState()));

	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN = register("tofucraft:nether_soybean", (Feature.RANDOM_PATCH.configured(NETHER_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(2));
	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN_PATCH = register("tofucraft:nether_soybean_patch", (Feature.RANDOM_PATCH.configured(NETHER_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(3));
	public static final ConfiguredFeature<?, ?> SOUL_SOYBEAN = register("tofucraft:soul_soybean", (Feature.RANDOM_PATCH.configured(SOUL_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(3));

	public static final ConfiguredFeature<?, ?> TOFU_ROCK = register("tofucraft:tofu_rock", TofuFeatures.TOFU_ROCK.configured(new BlockStateConfiguration(TofuBlocks.ISHITOFU.defaultBlockState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(2));
	public static final ConfiguredFeature<?, ?> TOFU_BUILDING = register("tofucraft:tofu_building", TofuFeatures.TOFU_BUILDING.configured(new BlockStateConfiguration(TofuBlocks.TOFU_TERRAIN.defaultBlockState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(3));

	public static final ConfiguredFeature<TreeConfiguration, ?> TOFU_TREE = register("tofucraft:tofu_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(TofuBlocks.ISHITOFU.defaultBlockState()), new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider(TofuBlocks.LEAVES_TOFU.defaultBlockState()), new SimpleStateProvider(TofuBlocks.SAPLING_TOFU.defaultBlockState()), new TofuFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(2, 2, 2))).dirt(new SimpleStateProvider(TofuBlocks.TOFU_TERRAIN.defaultBlockState())).ignoreVines().build()));
	public static final ConfiguredFeature<TreeConfiguration, ?> TOFU_TREE_BIG = register("tofucraft:tofu_tree_big", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(TofuBlocks.ISHITOFU.defaultBlockState()), new StraightTrunkPlacer(5, 2, 0), new SimpleStateProvider(TofuBlocks.LEAVES_TOFU.defaultBlockState()), new SimpleStateProvider(TofuBlocks.SAPLING_TOFU.defaultBlockState()), new TofuFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(3, 3, 3))).dirt(new SimpleStateProvider(TofuBlocks.TOFU_TERRAIN.defaultBlockState())).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> LEEK = register("tofucraft:leek", (Feature.RANDOM_PATCH.configured(LEEK_CLUSTER).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(5)));

	public static final ConfiguredFeature<?, ?> TOFUPLAIN_VEGETATION = register("tofucraft:tofu_plain_vegetation", TOFU_TREE.decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05F, 1))));
	public static final ConfiguredFeature<?, ?> TOFUFOREST_VEGETATION = register("tofucraft:tofu_forest_vegetation", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(TOFU_TREE_BIG.weighted(0.1F)), TOFU_TREE)).decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(6, 0.1F, 1))));


	public static final ConfiguredFeature<?, ?> ORE_TOFU_DIAMOND = register("tofucraft:ore_tofu_diamond", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 6, 0.5F)).rangeTriangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(100)).squared().count(15));
	public static final ConfiguredFeature<?, ?> ORE_TOFU_DIAMOND_LARGE = register("tofucraft:ore_tofu_diamond_large", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 16, 0.7F)).rangeTriangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)).squared().rarity(6));


	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, p_243968_1_);
	}

	public static void init() {
	}
}
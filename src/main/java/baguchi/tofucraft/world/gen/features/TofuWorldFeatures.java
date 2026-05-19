package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFeatures;
import baguchi.tofucraft.registry.TofuTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class TofuWorldFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN.get());
	public static final RuleTest TOFUSLATE_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFUSLATE.get());

	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.get().defaultBlockState()), OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get().defaultBlockState()));
	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_TOFUGEM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFUGEM.get().defaultBlockState()));
	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_SOY_FORCE_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get().defaultBlockState()));

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KINU_TOFU = registerKey("ore_kinu_tofu");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MINCED_TOFU = registerKey("ore_minced_tofu");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_SMALL = registerKey("ore_tofu_diamond_small");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_MEDIUM = registerKey("ore_tofu_diamond_medium");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_LARGE = registerKey("ore_tofu_diamond_large");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_BURIED = registerKey("ore_tofu_diamond_buried");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SOY_FORCE = registerKey("ore_soy_force");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOFUGEM_SMALL = registerKey("ore_tofugem_small");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOFUGEM_LARGE = registerKey("ore_tofugem_large");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_DELTA = registerKey("tofu_delta");

	public static final ResourceKey<ConfiguredFeature<?, ?>> SPROUT_WATER_POOL = registerKey("sprout_water_pool");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_SPROUTS = registerKey("wild_sprouts");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_FLOWER = registerKey("tofu_flower");
	public static final ResourceKey<ConfiguredFeature<?, ?>> LEEK = registerKey("leek");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_LEEK = registerKey("big_leek");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_BUILDING = registerKey("tofu_building");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ZUNDA_TOFU_MUSHROOM = registerKey("zunda_tofu_mushroom");


	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest ruletest = new TagMatchTest(TofuTags.Blocks.TOFU_TERRAIN);
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
		FeatureUtils.register(context, ORE_KINU_TOFU, Feature.ORE, new OreConfiguration(ruletest, TofuBlocks.KINUTOFU.get().defaultBlockState(), 20));
		FeatureUtils.register(context, ORE_MINCED_TOFU, Feature.ORE, new OreConfiguration(ruletest, TofuBlocks.MINCEDTOFU.get().defaultBlockState(), 28));

		FeatureUtils.register(context, ORE_DIAMOND_SMALL, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 4, 0.0F));
		FeatureUtils.register(context, ORE_DIAMOND_MEDIUM, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 8, 0.25F));
		FeatureUtils.register(context, ORE_DIAMOND_LARGE, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 12, 0.5F));
		FeatureUtils.register(context, ORE_DIAMOND_BURIED, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 8, 1.0F));

		FeatureUtils.register(context, ORE_SOY_FORCE, Feature.ORE, new OreConfiguration(ORE_SOY_FORCE_TARGET_LIST, 8, 0.0F));


		FeatureUtils.register(context, ORE_TOFUGEM_SMALL, Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 6));
		FeatureUtils.register(context, ORE_TOFUGEM_LARGE, Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 10));

		FeatureUtils.register(
				context,
				TOFU_DELTA,
				Feature.DELTA_FEATURE,
				new DeltaFeatureConfiguration(TofuBlocks.DOUBANJIANG.get().defaultBlockState(), TofuBlocks.MABOU_TERRAIN.get().defaultBlockState(), UniformInt.of(3, 7), UniformInt.of(0, 2))
		);
		FeatureUtils.register(context, WILD_SPROUTS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TofuBlocks.WILD_SPROUTS.get())));

		FeatureUtils.register(context, SPROUT_WATER_POOL, Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE, BlockStateProvider.simple(TofuBlocks.MINCEDTOFU.get()), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ModTreeFeatures.SPROUT), new PlacementModifier[0]), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.055F, UniformInt.of(4, 7), 0.7F));

		FeatureUtils.register(context, TOFU_FLOWER, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TofuBlocks.TOFU_FLOWER.get())));
		FeatureUtils.register(context, LEEK, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
				WeightedList.<BlockState>builder().add(TofuBlocks.LEEK.get().defaultBlockState(), 10).add(TofuBlocks.TALL_LEEK.get().defaultBlockState(), 1)
		)));
		FeatureUtils.register(context, BIG_LEEK, TofuFeatures.BIG_LEEK.get(), RandomFeatureConfiguration.NONE);

		FeatureUtils.register(context, TOFU_BUILDING, TofuFeatures.TOFU_BUILDING.get(), new BlockStateConfiguration(TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()));

		FeatureUtils.register(context, ZUNDA_TOFU_MUSHROOM, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TofuBlocks.ZUNDATOFU_MUSHROOM.get())));

	}
}
package baguchan.tofucraft.world.gen.features;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFeatures;
import baguchan.tofucraft.registry.TofuTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class TofuWorldFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN.get());
	public static final RuleTest TOFUSLATE_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFUSLATE.get());

	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.get().defaultBlockState()), OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get().defaultBlockState()));
	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_TOFUGEM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFUGEM.get().defaultBlockState()));

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KINU_TOFU = registerKey("ore_kinu_tofu");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MINCED_TOFU = registerKey("ore_minced_tofu");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_SMALL = registerKey("ore_tofu_diamond_small");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_LARGE = registerKey("ore_tofu_diamond_large");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_BURIED = registerKey("ore_tofu_diamond_buried");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOFUGEM_SMALL = registerKey("ore_tofugem_small");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOFUGEM_LARGE = registerKey("ore_tofugem_large");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_FLOWER = registerKey("tofu_flower");
	public static final ResourceKey<ConfiguredFeature<?, ?>> LEEK = registerKey("leek");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_LEEK = registerKey("big_leek");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TOFU_BUILDING = registerKey("tofu_building");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ZUNDA_TOFU_MUSHROOM = registerKey("zunda_tofu_mushroom");

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest ruletest = new TagMatchTest(TofuTags.Blocks.TOFU_TERRAIN);
		FeatureUtils.register(context, ORE_KINU_TOFU, Feature.ORE, new OreConfiguration(ruletest, TofuBlocks.KINUTOFU.get().defaultBlockState(), 20));
		FeatureUtils.register(context, ORE_MINCED_TOFU, Feature.ORE, new OreConfiguration(ruletest, TofuBlocks.MINCEDTOFU.get().defaultBlockState(), 28));

		FeatureUtils.register(context, ORE_DIAMOND_SMALL, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 5, 0.2F));
		FeatureUtils.register(context, ORE_DIAMOND_LARGE, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 12, 0.5F));
		FeatureUtils.register(context, ORE_DIAMOND_BURIED, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 8, 1.0F));

		FeatureUtils.register(context, ORE_TOFUGEM_SMALL, Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 6));
		FeatureUtils.register(context, ORE_TOFUGEM_LARGE, Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 10));

		FeatureUtils.register(context, TOFU_FLOWER, Feature.FLOWER, grassPatch(BlockStateProvider.simple(TofuBlocks.TOFU_FLOWER.get()), 32));
		FeatureUtils.register(context, LEEK, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.LEEK.get()), 32));
		FeatureUtils.register(context, BIG_LEEK, TofuFeatures.BIG_LEEK.get(), RandomFeatureConfiguration.NONE);

		FeatureUtils.register(context, TOFU_BUILDING, TofuFeatures.TOFU_BUILDING.get(), new BlockStateConfiguration(TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()));

		FeatureUtils.register(context, ZUNDA_TOFU_MUSHROOM, Feature.FLOWER, grassPatch(BlockStateProvider.simple(TofuBlocks.ZUNDATOFU_MUSHROOM.get()), 32));
	}
}
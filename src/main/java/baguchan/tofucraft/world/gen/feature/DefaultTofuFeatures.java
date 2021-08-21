package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class DefaultTofuFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN);

	public static RandomPatchConfiguration NETHER_SOYBEAN_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.SOYBEAN_NETHER.defaultBlockState().setValue(SoybeanNetherCropsBlock.AGE, 7)), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static RandomPatchConfiguration SOUL_SOYBEAN_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.SOYBEAN_SOUL.defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7)), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static RandomPatchConfiguration LEEK_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.LEEK.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.defaultBlockState()));

	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN = register("tofucraft:nether_soybean", (Feature.RANDOM_PATCH.configured(NETHER_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(2));
	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN_PATCH = register("tofucraft:nether_soybean_patch", (Feature.RANDOM_PATCH.configured(NETHER_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(3));
	public static final ConfiguredFeature<?, ?> SOUL_SOYBEAN = register("tofucraft:soul_soybean", (Feature.RANDOM_PATCH.configured(SOUL_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(3));

	public static final ConfiguredFeature<?, ?> LEEK = register("tofucraft:leek", (Feature.RANDOM_PATCH.configured(LEEK_CLUSTER).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(5)));

	public static final ConfiguredFeature<?, ?> ORE_TOFU_DIAMOND = register("tofucraft:ore_tofu_diamond", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 6, 0.5F)).rangeTriangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)).squared().count(12));
	public static final ConfiguredFeature<?, ?> ORE_TOFU_DIAMOND_LARGE = register("tofucraft:ore_tofu_diamond_large", Feature.ORE.configured(new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 16, 0.7F)).rangeTriangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)).squared().rarity(9));


	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, p_243968_1_);
	}

	public static void init() {
	}
}
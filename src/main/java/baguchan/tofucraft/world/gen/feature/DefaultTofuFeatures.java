package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public class DefaultTofuFeatures {
	public static RandomPatchConfiguration NETHER_SOYBEAN_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.SOYBEAN_NETHER.defaultBlockState().setValue(SoybeanNetherCropsBlock.AGE, 7)), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static RandomPatchConfiguration SOUL_SOYBEAN_CLUSTER = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(TofuBlocks.SOYBEAN_SOUL.defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7)), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build();

	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN = register("tofucraft:nether_soybean", (Feature.RANDOM_PATCH.configured(NETHER_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(2));
	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN_PATCH = register("tofucraft:nether_soybean_patch", (Feature.RANDOM_PATCH.configured(NETHER_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(3));
	public static final ConfiguredFeature<?, ?> SOUL_SOYBEAN = register("tofucraft:soul_soybean", (Feature.RANDOM_PATCH.configured(SOUL_SOYBEAN_CLUSTER).range(Features.Decorators.FULL_RANGE)).rarity(2));

	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, p_243968_1_);
	}

	public static void init() {
	}
}
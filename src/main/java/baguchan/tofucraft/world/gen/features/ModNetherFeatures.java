package baguchan.tofucraft.world.gen.features;

import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModNetherFeatures {
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> NETHER_SOYBEAN = FeatureUtils.register("tofucraft:nether_soybean", Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.SOYBEAN_NETHER.get().defaultBlockState().setValue(SoybeanNetherCropsBlock.AGE, 7)), 32));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SOUL_SOYBEAN = FeatureUtils.register("tofucraft:soul_soybean", Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.SOYBEAN_SOUL.get().defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7)), 32));


	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, p_243968_1_);
	}

	public static void init() {
	}

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}
}
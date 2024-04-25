package baguchan.tofucraft.world.gen.features;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModNetherFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SOYBEAN = registerKey("nether_soybean");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_SOYBEAN = registerKey("soul_soybean");


	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, NETHER_SOYBEAN, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.SOYBEAN_NETHER.get().defaultBlockState().setValue(SoybeanNetherCropsBlock.AGE, 7)), 32));
		FeatureUtils.register(context, SOUL_SOYBEAN, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.SOYBEAN_SOUL.get().defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7)), 32));
	}

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}
}
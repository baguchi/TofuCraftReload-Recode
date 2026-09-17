package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModNetherFeatures {
	public static final ResourceKey<Feature> NETHER_SOYBEAN = registerKey("nether_soybean");
	public static final ResourceKey<Feature> SOUL_SOYBEAN = registerKey("soul_soybean");


	public static ResourceKey<Feature> registerKey(String name) {
		return ResourceKey.create(Registries.FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Feature> context) {
		context.register(NETHER_SOYBEAN, new SimpleBlockFeature(
				BlockStateProvider.of(TofuBlocks.SOYBEAN_NETHER.get().defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7))
		));
		context.register(SOUL_SOYBEAN, new SimpleBlockFeature(BlockStateProvider.of(TofuBlocks.SOYBEAN_SOUL.get().defaultBlockState().setValue(SoybeanSoulCropsBlock.AGE, 7))));
	}

}
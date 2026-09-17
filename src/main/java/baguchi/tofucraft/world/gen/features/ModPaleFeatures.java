package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.crop.SoybeanPaleCropsBlock;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import static net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.matchesBlocks;

public class ModPaleFeatures {
	public static final ResourceKey<Feature> PALE_SOYBEAN = registerKey("pale_soybean");
	public static final BlockPredicate ONLY_IN_AIR_OR_GRASS_PREDICATE = matchesBlocks(Blocks.AIR, Blocks.SHORT_GRASS, Blocks.TALL_GRASS);


	public static ResourceKey<Feature> registerKey(String name) {
		return ResourceKey.create(Registries.FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Feature> context) {
		context.register(PALE_SOYBEAN, new SimpleBlockFeature(new SimpleStateProvider(TofuBlocks.SOYBEAN_PALE.get().defaultBlockState().setValue(SoybeanPaleCropsBlock.AGE, 3))));
	}
}
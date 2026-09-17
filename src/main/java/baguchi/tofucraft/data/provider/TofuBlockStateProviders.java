package baguchi.tofucraft.data.provider;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;

public class TofuBlockStateProviders {
	public static final ResourceKey<BlockStateProvider> SOIL_BENEATH_TOFU_TREE = key("soil_beneath_tofu_tree");

	public static void bootstrap(BootstrapContext<BlockStateProvider> context) {
		context.register(SOIL_BENEATH_TOFU_TREE, RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)), TofuBlocks.TOFU_TERRAIN.get()));
	}

	private static ResourceKey<BlockStateProvider> key(String id) {
		return ResourceKey.create(Registries.BLOCK_STATE_PROVIDER, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, id));
	}
}
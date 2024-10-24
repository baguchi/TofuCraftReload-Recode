package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class TofuLootTables {
	public static final ResourceKey<LootTable> TOFU_WORLD_FISHING_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "gameplay/fishing/fish"));
	public static final ResourceKey<LootTable> TOFUNIAN_GIFT_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "gameplay/tofunian_gift"));

	public static final ResourceKey<LootTable> TOFU_VAULT_REWARD = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "chests/tofu_castle/tofu_vault"));
}

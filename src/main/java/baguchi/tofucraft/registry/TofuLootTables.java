package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class TofuLootTables {
	public static final ResourceKey<LootTable> TOFU_WORLD_FISHING_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "gameplay/fishing/fish"));
	public static final ResourceKey<LootTable> TOFUNIAN_GIFT_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "gameplay/tofunian_gift"));

	public static final ResourceKey<LootTable> TOFUSTICK = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "injections/tofustick"));
	public static final ResourceKey<LootTable> TOFU_BOOK = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "injections/tofu_book"));
	public static final ResourceKey<LootTable> ROTTEN_PORK = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "injections/rotten_pork"));
	public static final ResourceKey<LootTable> SEEDS_CHILI_ANCIENT_CITY = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "injections/seeds_chili_ancient_city"));
}

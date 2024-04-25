package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class TofuLootTables {
	public static final ResourceKey<LootTable> TOFU_WORLD_FISHING_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation(TofuCraftReload.MODID, "gameplay/fishing/fish"));
	public static final ResourceKey<LootTable> TOFUNIAN_GIFT_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation(TofuCraftReload.MODID, "gameplay/tofunian_gift"));

}

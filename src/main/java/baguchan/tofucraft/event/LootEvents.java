package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.Sets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Set;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class LootEvents {
	private static final Set<ResourceLocation> TEMPLE_LOOT = Sets.newHashSet(LootTables.JUNGLE_TEMPLE);

	private static final Set<ResourceLocation> SHIP_LOOT = Sets.newHashSet(LootTables.SHIPWRECK_SUPPLY);

	private static final Set<ResourceLocation> RUIN_LOOT = Sets.newHashSet(LootTables.UNDERWATER_RUIN_BIG);

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		if (TEMPLE_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_temple")).setWeight(1).setQuality(1)).name("tofustick_temple").build();
			event.getTable().addPool(pool);
		}
		if (SHIP_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_ship")).setWeight(1).setQuality(1)).name("tofustick_ship").build();
			event.getTable().addPool(pool);
		}
		if (RUIN_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_ruin")).setWeight(1).setQuality(1)).name("tofustick_ruin").build();
			event.getTable().addPool(pool);
		}
	}
}

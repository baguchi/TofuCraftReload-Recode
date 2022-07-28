package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class LootEvents {
	private static final Set<ResourceLocation> TEMPLE_LOOT = Sets.newHashSet(new ResourceLocation("chests/jungle_temple"));

	private static final Set<ResourceLocation> SHIP_SUPPLY_LOOT = Sets.newHashSet(new ResourceLocation("chests/shipwreck_supply"));

	private static final Set<ResourceLocation> SHIP_TREASURE_LOOT = Sets.newHashSet(new ResourceLocation("chests/shipwreck_treasure"));

	private static final Set<ResourceLocation> RUIN_LOOT = Sets.newHashSet(new ResourceLocation("chests/underwater_ruin_big"));

	private static final Set<ResourceLocation> DUNGEONS_ROOT = Sets.newHashSet(BuiltInLootTables.SIMPLE_DUNGEON);

	private static final Set<ResourceLocation> ANCIENT_CITY_ROOT = Sets.newHashSet(BuiltInLootTables.ANCIENT_CITY);


	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		if (TEMPLE_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_temple")).setWeight(2).setQuality(1)).name("tofustick_temple").build();
			event.getTable().addPool(pool);
		}
		if (SHIP_TREASURE_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_ship")).setWeight(10).setQuality(1)).name("tofustick_ship").build();
			event.getTable().addPool(pool);
		}
		if (RUIN_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_ruin")).setWeight(2).setQuality(1)).name("tofustick_ruin").build();
			event.getTable().addPool(pool);
		}
		if (ANCIENT_CITY_ROOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/seeds_chili_ancient_city")).setWeight(10).setQuality(5)).name("seeds_chili_ancient_city").build();
			event.getTable().addPool(pool);
		}

		if (SHIP_SUPPLY_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/sapling_apricot_ship")).setWeight(10).setQuality(5)).name("sapling_apricot_ship").build();
			event.getTable().addPool(pool);
		}

		if (DUNGEONS_ROOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/sapling_apricot_dungeons")).setWeight(10).setQuality(5)).name("sapling_apricot_dungeons").build();
			event.getTable().addPool(pool);
		}
	}
}

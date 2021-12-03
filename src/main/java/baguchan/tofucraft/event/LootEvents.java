package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class LootEvents {
	private static final Set<ResourceLocation> TEMPLE_LOOT = Sets.newHashSet(new ResourceLocation("chests/jungle_temple"));

	private static final Set<ResourceLocation> SHIP_LOOT = Sets.newHashSet(new ResourceLocation("chests/shipwreck_supply"));

	private static final Set<ResourceLocation> RUIN_LOOT = Sets.newHashSet(new ResourceLocation("chests/underwater_ruin_big"));

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		//TODO KEEP Comment OUT UNTIL DIMENSION IS DONE
		/*if (TEMPLE_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_temple")).setWeight(1).setQuality(1)).name("tofustick_temple").build();
			event.getTable().addPool(pool);
		}
		if (SHIP_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_ship")).setWeight(1).setQuality(1)).name("tofustick_ship").build();
			event.getTable().addPool(pool);
		}
		if (RUIN_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation("tofucraft", "injections/tofustick_ruin")).setWeight(1).setQuality(1)).name("tofustick_ruin").build();
			event.getTable().addPool(pool);
		}*/
	}
}

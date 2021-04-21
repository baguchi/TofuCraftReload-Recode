package baguchan.tofucraft.event;

import com.google.common.collect.Sets;

import java.util.Set;

import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft")
public class LootEvents {
	private static final Set<ResourceLocation> TEMPLE_LOOT = Sets.newHashSet((Object[]) new ResourceLocation[]{LootTables.field_186430_l});

	private static final Set<ResourceLocation> SHIP_LOOT = Sets.newHashSet((Object[]) new ResourceLocation[]{LootTables.field_204772_t});

	private static final Set<ResourceLocation> RUIN_LOOT = Sets.newHashSet((Object[]) new ResourceLocation[]{LootTables.field_204115_q});

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		if (TEMPLE_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.func_216096_a().func_216045_a((LootEntry.Builder) TableLootEntry.func_216171_a(new ResourceLocation("tofucraft", "injections/tofustick_temple")).func_216086_a(1).func_216085_b(1)).name("tofustick_temple").func_216044_b();
			event.getTable().addPool(pool);
		}
		if (SHIP_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.func_216096_a().func_216045_a((LootEntry.Builder) TableLootEntry.func_216171_a(new ResourceLocation("tofucraft", "injections/tofustick_ship")).func_216086_a(1).func_216085_b(1)).name("tofustick_ship").func_216044_b();
			event.getTable().addPool(pool);
		}
		if (RUIN_LOOT.contains(event.getName())) {
			LootPool pool = LootPool.func_216096_a().func_216045_a((LootEntry.Builder) TableLootEntry.func_216171_a(new ResourceLocation("tofucraft", "injections/tofustick_ruin")).func_216086_a(1).func_216085_b(1)).name("tofustick_ruin").func_216044_b();
			event.getTable().addPool(pool);
		}
	}
}

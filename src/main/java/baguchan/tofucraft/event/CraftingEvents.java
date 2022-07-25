package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class CraftingEvents {

	@SubscribeEvent
	public static void onCraftingOkara(PlayerEvent.ItemCraftedEvent event) {
		if (event.getCrafting().is(TofuItems.BUCKET_SOYMILK.get())) {
			if (event.getInventory().hasAnyOf(Set.of(TofuItems.SEEDS_SOYBEANS.get(), Items.BUCKET, TofuItems.FILTERCLOTH.get()))) {
				ItemStack itemstack = new ItemStack(TofuItems.OKARA.get());
				if (!event.getEntity().getInventory().add(itemstack)) {
					event.getEntity().drop(itemstack, false);
				}
			}
		}
	}
}

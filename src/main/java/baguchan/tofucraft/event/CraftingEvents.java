package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Optional;
import java.util.Set;

public class CraftingEvents {

	@SubscribeEvent
	public void onCraftingOkara(PlayerEvent.ItemCraftedEvent event) {
		Player player = event.getEntity();
		ItemStack item = event.getCrafting();
		Container craftMatrix = event.getInventory();
		if (item.is(TofuItems.BUCKET_SOYMILK.get())) {
			if (craftMatrix.hasAnyOf(Set.of(Items.BUCKET, TofuItems.FILTERCLOTH.get(), TofuItems.SEEDS_SOYBEANS.get()))) {
				Optional<RecipeHolder<?>> recipe = player.level().getRecipeManager().byKey(new ResourceLocation(TofuCraftReload.MODID, "bucket_soymilk_okara"));
				if (recipe.isPresent()) {
					player.getInventory().add(new ItemStack(TofuItems.OKARA.get(), 1));
				}
			}
		}
	}
}

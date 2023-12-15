package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;
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
				CraftingContainer craftingcontainer = makeCraftContainer(craftMatrix);
				Optional<Pair<ResourceLocation, RecipeHolder<CraftingRecipe>>> recipe = player.level().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingcontainer, player.level(), new ResourceLocation(TofuCraftReload.MODID, "bucket_soymilk_okara"));
				if (recipe.isPresent() && Objects.equals(recipe.get().getFirst(), new ResourceLocation(TofuCraftReload.MODID, "bucket_soymilk_okara"))) {
					player.getInventory().add(new ItemStack(TofuItems.OKARA.get(), 1));
				}
			}
		}
	}

	private CraftingContainer makeCraftContainer(Container craftMatrix) {
		CraftingContainer craftingcontainer = new TransientCraftingContainer(new AbstractContainerMenu(null, -1) {
			@Override
			public ItemStack quickMoveStack(Player p_218264_, int p_218265_) {
				return ItemStack.EMPTY;
			}

			@Override
			public boolean stillValid(Player p_29888_) {
				return false;
			}
		}, 3, 3);
		int count = craftMatrix.getContainerSize();
		for (int i = 0; i < count; i++) {
			ItemStack stack = craftMatrix.getItem(i);
			craftingcontainer.setItem(i, stack);
		}
		return craftingcontainer;
	}
}

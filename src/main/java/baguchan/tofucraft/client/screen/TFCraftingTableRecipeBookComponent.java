package baguchan.tofucraft.client.screen;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class TFCraftingTableRecipeBookComponent extends RecipeBookComponent {
	private static final WidgetSprites FILTER_BUTTON_SPRITES = new WidgetSprites(
			ResourceLocation.withDefaultNamespace("recipe_book/filter_enabled"),
			ResourceLocation.withDefaultNamespace("recipe_book/filter_disabled"),
			ResourceLocation.withDefaultNamespace("recipe_book/filter_enabled_highlighted"),
			ResourceLocation.withDefaultNamespace("recipe_book/filter_disabled_highlighted")
	);

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(FILTER_BUTTON_SPRITES);
	}

	public void hide() {
		this.setVisible(false);
	}

	@Override
	public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
		ItemStack resultStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
		this.ghostRecipe.setRecipe(recipe);

		this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
	}
}
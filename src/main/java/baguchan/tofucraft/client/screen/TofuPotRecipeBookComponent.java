package baguchan.tofucraft.client.screen;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nonnull;
import java.util.List;

public class TofuPotRecipeBookComponent extends RecipeBookComponent {
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
	@Nonnull
	protected Component getRecipeFilterName() {
		return Component.translatable("container.tofucraft.recipe_book.cookable");
	}

	@Override
	public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
		ItemStack resultStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
		this.ghostRecipe.setRecipe(recipe);
		/*if (slots.get(6).getItem().isEmpty()) {
			this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(6)).x, (slots.get(6)).y);
		}

		if (recipe.value() instanceof TofuPotBlockEntity cookingRecipe) {
			ItemStack containerStack = cookingRecipe.getItem(12);
			if (!containerStack.isEmpty()) {
				this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(7)).x, (slots.get(7)).y);
			}
		}*/

		this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
	}
}
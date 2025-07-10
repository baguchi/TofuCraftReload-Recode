package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.recipe.TFShapedRecipe;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Iterator;
import java.util.List;

public class TFCraftingTableRecipeBookComponent extends RecipeBookComponent {

	public void hide() {
		this.setVisible(false);
	}

	@Override
	public void setupGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
		ItemStack resultStack = recipe.getResultItem(this.minecraft.level.registryAccess());
		this.ghostRecipe.setRecipe(recipe);

		this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
	}

	@Override
	public void placeRecipe(int width, int height, int outputSlot, Recipe<?> recipe, Iterator<Ingredient> ingredients, int maxAmount) {
		int i = width;
		int j = height;
		if (recipe instanceof TFShapedRecipe shapedrecipe) {
			i = shapedrecipe.getWidth();
			j = shapedrecipe.getHeight();
		}

		int k1 = 0;

		for (int k = 0; k < height; k++) {
			if (k1 == outputSlot) {
				k1++;
			}

			boolean flag = (float) j < (float) height / 2.0F;
			int l = Mth.floor((float) height / 2.0F - (float) j / 2.0F);
			if (flag && l > k) {
				k1 += width;
				k++;
			}

			for (int i1 = 0; i1 < width; i1++) {
				if (!ingredients.hasNext()) {
					return;
				}

				flag = (float) i < (float) width / 2.0F;
				l = Mth.floor((float) width / 2.0F - (float) i / 2.0F);
				int j1 = i;
				boolean flag1 = i1 < i;
				if (flag) {
					j1 = l + i;
					flag1 = l <= i1 && i1 < l + i;
				}

				if (flag1) {
					this.addItemToSlot(ingredients, k1, maxAmount, i1, k);
				} else if (j1 == i1) {
					k1 += width - i1;
					break;
				}

				k1++;
			}
		}
	}
}
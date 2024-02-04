package baguchan.tofucraft.client.screen;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class WorkStationBookComponent extends RecipeBookComponent {
	private static final WidgetSprites FILTER_BUTTON_SPRITES = new WidgetSprites(
			new ResourceLocation("recipe_book/filter_enabled"),
			new ResourceLocation("recipe_book/filter_disabled"),
			new ResourceLocation("recipe_book/filter_enabled_highlighted"),
			new ResourceLocation("recipe_book/filter_disabled_highlighted")
	);

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(FILTER_BUTTON_SPRITES);
	}

	@Override
	public void slotClicked(@Nullable Slot p_100120_) {
		super.slotClicked(p_100120_);
		if (p_100120_ != null && p_100120_.index < this.menu.getSize()) {
			this.ghostRecipe.clear();
		}
	}

	@Override
	public void setupGhostRecipe(RecipeHolder<?> p_301099_, List<Slot> p_100123_) {
		ItemStack itemstack = p_301099_.value().getResultItem(this.minecraft.level.registryAccess());
		this.ghostRecipe.setRecipe(p_301099_);
		this.ghostRecipe.addIngredient(Ingredient.of(itemstack), p_100123_.get(3).x, p_100123_.get(3).y);
		NonNullList<Ingredient> nonnulllist = p_301099_.value().getIngredients();

		Iterator<Ingredient> iterator = nonnulllist.iterator();

		for (int i = 0; i < 3; ++i) {
			if (!iterator.hasNext()) {
				return;
			}

			Ingredient ingredient = iterator.next();
			if (!ingredient.isEmpty()) {
				Slot slot1 = p_100123_.get(i);
				this.ghostRecipe.addIngredient(ingredient, slot1.x, slot1.y);
			}
		}
	}
}

package baguchan.tofucraft.client.recipe;

import baguchan.tofucraft.inventory.TFOvenMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

import java.util.List;

public class OvenRecipeBookComponent extends RecipeBookComponent<TFOvenMenu> {
	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
			ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
			ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
			ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
			ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted")
	);
	private final Component recipeFilterName;

	public OvenRecipeBookComponent(TFOvenMenu p_361081_, Component p_363597_, List<TabInfo> p_379441_) {
		super(p_361081_, p_379441_);
		this.recipeFilterName = p_363597_;
	}

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(FILTER_SPRITES);
	}

	@Override
	protected boolean isCraftingSlot(Slot p_364139_) {
		return switch (p_364139_.index) {
			case 0, 1, 2 -> true;
			default -> false;
		};
	}

	@Override
	protected void fillGhostRecipe(GhostSlots p_379383_, RecipeDisplay p_380318_, ContextMap p_380984_) {
		p_379383_.setResult(this.menu.getResultSlot(), p_380984_, p_380318_.result());
		if (p_380318_ instanceof FurnaceRecipeDisplay furnacerecipedisplay) {
			p_379383_.setInput(this.menu.slots.get(0), p_380984_, furnacerecipedisplay.ingredient());
			Slot slot = this.menu.slots.get(1);
			if (slot.getItem().isEmpty()) {
				p_379383_.setInput(slot, p_380984_, furnacerecipedisplay.fuel());
			}
		}
	}

	@Override
	protected Component getRecipeFilterName() {
		return this.recipeFilterName;
	}

	@Override
	protected void selectMatchingRecipes(RecipeCollection p_361156_, StackedItemContents p_362080_) {
		p_361156_.selectRecipes(p_362080_, p_378786_ -> p_378786_ instanceof FurnaceRecipeDisplay);
	}
}

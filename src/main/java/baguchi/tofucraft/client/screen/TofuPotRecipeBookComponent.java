package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.inventory.TofuPotMenu;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.recipebook.PlaceRecipeHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;

import javax.annotation.Nonnull;
import java.util.List;

public class TofuPotRecipeBookComponent extends RecipeBookComponent {
	private static final WidgetSprites FILTER_BUTTON_SPRITES = new WidgetSprites(
			ResourceLocation.withDefaultNamespace("recipe_book/filter_enabled"),
			ResourceLocation.withDefaultNamespace("recipe_book/filter_disabled"),
			ResourceLocation.withDefaultNamespace("recipe_book/filter_enabled_highlighted"),
			ResourceLocation.withDefaultNamespace("recipe_book/filter_disabled_highlighted")
	);

	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
			new RecipeBookComponent.TabInfo(TofuItems.TOFU_CRAFTERS_BOOK.get(), TofuRecipeBookCategory.SEARCH),
			new RecipeBookComponent.TabInfo(TofuItems.SOYMILK.get(), TofuRecipeBookCategory.COOKING_DRINKS.get()),
			new RecipeBookComponent.TabInfo(TofuItems.NIKUJAGA.get(), TofuRecipeBookCategory.COOKING_MEALS.get()),
			new RecipeBookComponent.TabInfo(TofuItems.SOY_CHEESE.get(), TofuRecipeBookCategory.COOKING_FAST_FOODS.get()),
			new RecipeBookComponent.TabInfo(TofuItems.PUDDING_SOYMILK.get(), TofuRecipeBookCategory.COOKING_MISC.get())
	);


	public TofuPotRecipeBookComponent(RecipeBookMenu p_365070_) {
		super(p_365070_, TABS);
	}


	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(FILTER_BUTTON_SPRITES);
	}

	@Override
	protected boolean isCraftingSlot(Slot p_364400_) {
		return this.menu.getSlot(12) == p_364400_ || this.menu.getItems().contains(p_364400_);
	}

	private boolean canDisplay(RecipeDisplay p_379470_) {
		int i = 4;
		int j = 3;

		return switch (p_379470_) {
			case ShapedCraftingRecipeDisplay shapedcraftingrecipedisplay -> i >= shapedcraftingrecipedisplay.width()
					&& j >= shapedcraftingrecipedisplay.height();
			case ShapelessCraftingRecipeDisplay shapelesscraftingrecipedisplay ->
					i * j >= shapelesscraftingrecipedisplay.ingredients().size();
			default -> false;
		};
	}


	@Override
	protected void selectMatchingRecipes(RecipeCollection p_363827_, StackedItemContents p_362085_) {
		p_363827_.selectRecipes(p_362085_, this::canDisplay);
	}

	@Override
	@Nonnull
	protected Component getRecipeFilterName() {
		return Component.translatable("container.tofucraft.recipe_book.cookable");
	}

	@Override
	protected void fillGhostRecipe(GhostSlots p_379930_, RecipeDisplay p_379870_, ContextMap p_380956_) {
		p_379930_.setResult(((TofuPotMenu) this.menu).getResultSlot(), p_380956_, p_379870_.result());

		ShapedCraftingRecipeDisplay shapedcraftingrecipedisplay = (ShapedCraftingRecipeDisplay) p_379870_;
		List<Slot> list1 = ((TofuPotMenu) this.menu).getInputGridSlots();
		PlaceRecipeHelper.placeRecipe(4, 3, shapedcraftingrecipedisplay.width(), shapedcraftingrecipedisplay.height(), shapedcraftingrecipedisplay.ingredients(), (p_380786_, p_380787_, p_380788_, p_380789_) -> {
			Slot slot = (Slot) list1.get(p_380787_);
			p_379930_.setInput(slot, p_380956_, p_380786_);
		});
	}

}
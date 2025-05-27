package baguchi.tofucraft.compat.jei;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.recipe.TFCraftingRecipe;
import baguchi.tofucraft.recipe.TFShapedRecipe;
import baguchi.tofucraft.registry.TofuBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class TFRecipeCategory implements IRecipeCategory<TFCraftingRecipe> {

	public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tf_craft");
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public TFRecipeCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.tf_craft");
		ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_crafting_table.png");
		background = helper.createDrawable(backgroundImage, 10, 12, 141, 63);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TofuBlocks.TF_CRAFTING_TABLE.get()));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public int getWidth() {
		return 141;
	}

	@Override
	public int getHeight() {
		return 63;
	}

	@Override
	public ResourceLocation getRegistryName(TFCraftingRecipe recipe) {
		return UID;
	}

	@Override
	public IRecipeType<TFCraftingRecipe> getRecipeType() {
		return JEIPlugin.TF_RECIPE_JEI_TYPE;
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, TFCraftingRecipe recipe, IFocusGroup focuses) {
		List<Optional<Ingredient>> recipeIngredients = recipe.getIngredients();
		int startX = 8;
		int startY = 18;
		int inputStartX = 30 - 10;
		int inputStartY = 17 - 12;
		int borderSlotSize = 18;
		int sizeWidth = 3;
		int sizeHeight = 3;
		if (recipe instanceof TFShapedRecipe shapedRecipe) {
			sizeWidth = shapedRecipe.getWidth();
			sizeHeight = shapedRecipe.getHeight();
		}
		Iterator<Optional<Ingredient>> ingredients = recipeIngredients.iterator();

		for (int row = 0; row < sizeHeight; ++row) {
			for (int column = 0; column < sizeWidth; ++column) {
				if (!ingredients.hasNext()) {
					break;
				}
				Optional<Ingredient> ingredient = ingredients.next();

				if (ingredient.isPresent()) {
					builder.addSlot(RecipeIngredientRole.INPUT,
							inputStartX + (column * borderSlotSize),
							inputStartY + (row * borderSlotSize)).add(ingredient.get());
				}
			}
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 123 - 10, 34 - 12).add(recipe.getResult().copy());

	}


	@Override
	public void draw(TFCraftingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
		background.draw(stack);
		arrow.draw(stack, 89 - 10, 34 - 12);
	}


}

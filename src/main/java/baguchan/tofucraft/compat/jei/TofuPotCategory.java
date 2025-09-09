package baguchan.tofucraft.compat.jei;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.TofuPotRecipe;
import baguchan.tofucraft.registry.TofuBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Iterator;


public class TofuPotCategory implements IRecipeCategory<TofuPotRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(TofuCraftReload.MODID, "tofu_pot");
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public TofuPotCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.tofu_pot");
		ResourceLocation backgroundImage = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/tofu_pot.png");
		background = helper.createDrawable(backgroundImage, 37, 4, 112, 79);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TofuBlocks.TOFU_POT.get()));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getRegistryName(TofuPotRecipe recipe) {
		return UID;
	}

	@Override
	public RecipeType<TofuPotRecipe> getRecipeType() {
		return JEIPlugin.TOFU_POT_RECIPE_JEI_TYPE;
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, TofuPotRecipe recipe, IFocusGroup focuses) {
		NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
		int startX = 8;
		int startY = 18;
		int inputStartX = 44 - 37;
		int inputStartY = 8 - 4;
		int borderSlotSize = 18;
		Iterator<Ingredient> ingredients = recipeIngredients.iterator();
		int sizeWidth = 3;
		int sizeHeight = 4;
		for (int row = 0; row < sizeHeight; ++row) {
			for (int column = 0; column < sizeWidth; ++column) {
				if (!ingredients.hasNext()) {
					break;
				}
				builder.addSlot(RecipeIngredientRole.INPUT,
						inputStartX + (column * borderSlotSize),
						inputStartY + (row * borderSlotSize)).addIngredients(ingredients.next());
			}
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 130 - 37, 37 - 4).addItemStack(recipe.getResult());
		builder.addSlot(RecipeIngredientRole.CATALYST, 130 - 37, 37 - 22).addIngredients(ForgeTypes.FLUID_STACK, Arrays.stream(recipe.fluidIngredient().getFluids()).toList());

	}


	@Override
	public void draw(TofuPotRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
		arrow.draw(stack, 103 - 37, 37 - 4);
	}
}

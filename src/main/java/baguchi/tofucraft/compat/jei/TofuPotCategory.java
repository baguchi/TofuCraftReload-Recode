package baguchi.tofucraft.compat.jei;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.recipe.TofuPotRecipe;
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
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class TofuPotCategory implements IRecipeCategory<TofuPotRecipe> {
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public TofuPotCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.tofu_pot");
		Identifier backgroundImage = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tofu_pot.png");
		background = helper.createDrawable(backgroundImage, 37, 4, 112, 79);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TofuBlocks.TOFU_POT.get()));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public int getWidth() {
		return 112;
	}

	@Override
	public int getHeight() {
		return 79;
	}

	@Override
	public IRecipeType<TofuPotRecipe> getRecipeType() {
		return JEIPlugin.TOFU_POT_RECIPE_JEI_TYPE;
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
	public void setRecipe(IRecipeLayoutBuilder builder, TofuPotRecipe recipe, IFocusGroup focuses) {
		List<Optional<Ingredient>> recipeIngredients = recipe.getIngredients();
		int startX = 8;
		int startY = 18;
		int inputStartX = 44 - 37;
		int inputStartY = 8 - 4;
		int borderSlotSize = 18;
		Iterator<Optional<Ingredient>> ingredients = recipeIngredients.iterator();
		int sizeWidth = 3;
		int sizeHeight = 4;
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
		builder.addSlot(RecipeIngredientRole.OUTPUT, 130 - 37, 37 - 4).add(recipe.getResult());
		if (recipe.fluidIngredient().isPresent()) {
			builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 130 - 37, 37 - 22).add(recipe.fluidIngredient().get().ingredient().fluids().get(0).value(), recipe.fluidIngredient().get().amount());
		}
	}


	@Override
	public void draw(TofuPotRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor stack, double mouseX, double mouseY) {
		background.draw(stack);
		arrow.draw(stack, 103 - 37, 38 - 4);
	}
}

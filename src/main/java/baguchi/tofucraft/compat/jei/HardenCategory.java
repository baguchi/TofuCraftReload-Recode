package baguchi.tofucraft.compat.jei;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.recipe.HardenRecipe;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class HardenCategory implements IRecipeCategory<HardenRecipe> {

	public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "harden");
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public HardenCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.harden");
		ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/general_jei_recipe.png");
		background = helper.createDrawable(backgroundImage, 16, 16, 144, 54);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.COBBLESTONE));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public int getWidth() {
		return 144;
	}

	@Override
	public int getHeight() {
		return 54;
	}

	@Override
	public ResourceLocation getRegistryName(HardenRecipe recipe) {
		return UID;
	}

	@Override
	public IRecipeType<HardenRecipe> getRecipeType() {
		return JEIPlugin.HARDEN_JEI_TYPE;
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
	public void setRecipe(IRecipeLayoutBuilder builder, HardenRecipe recipe, IFocusGroup focuses) {
		Ingredient recipeIngredients = recipe.getTofu();
		int borderSlotSize = 18;


		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18)
				.add(recipeIngredients);
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18 - borderSlotSize)
				.add(Items.COBBLESTONE);
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18 + borderSlotSize)
				.add(Items.COBBLESTONE);

		builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 18).add(recipe.getResult());

	}


	@Override
	public void draw(HardenRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
		background.draw(stack);
		arrow.draw(stack, 72 - 17, 35 - 17);
	}
}
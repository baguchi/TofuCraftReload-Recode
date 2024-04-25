package baguchan.tofucraft.compat.jei;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.BitternRecipe;
import baguchan.tofucraft.registry.TofuItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.Arrays;


public class BitternCategory implements IRecipeCategory<BitternRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(TofuCraftReload.MODID, "bittern");
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public BitternCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.bittern");
		ResourceLocation backgroundImage = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/general_jei_recipe.png");
		background = helper.createDrawable(backgroundImage, 16, 16, 144, 54);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TofuItems.BITTERN_BOTTLE.get()));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getRegistryName(BitternRecipe recipe) {
		return UID;
	}

	@Override
	public RecipeType<BitternRecipe> getRecipeType() {
		return JEIPlugin.BITTERN_JEI_TYPE;
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
	public void setRecipe(IRecipeLayoutBuilder builder, BitternRecipe recipe, IFocusGroup focuses) {
		FluidStack recipeIngredients = recipe.getFluid();
		int borderSlotSize = 18;
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18)
				.setFluidRenderer(1000, false, 16, 16)
				.addIngredients(NeoForgeTypes.FLUID_STACK, Arrays.asList(recipeIngredients));

		builder.addSlot(RecipeIngredientRole.INPUT, 10, 18)
				.setFluidRenderer(1000, false, 16, 16)
				.addIngredients(recipe.getIngredient());


		builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 18).addItemStack(recipe.getResult());

	}


	@Override
	public void draw(BitternRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
		arrow.draw(stack, 72 - 17, 35 - 17);
	}
}

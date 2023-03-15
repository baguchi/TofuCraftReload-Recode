package baguchan.tofucraft.compat.jei;

/*

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.HardenRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class HardenCategory implements IRecipeCategory<HardenRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(TofuCraftReload.MODID, "harden");
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public HardenCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.harden");
		ResourceLocation backgroundImage = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/general_jei_recipe.png");
		background = helper.createDrawable(backgroundImage, 16, 16, 144, 54);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.COBBLESTONE));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getRegistryName(HardenRecipe recipe) {
		return UID;
	}

	@Override
	public RecipeType<HardenRecipe> getRecipeType() {
		return JEIPlugin.HARDEN_JEI_TYPE;
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
	public void setRecipe(IRecipeLayoutBuilder builder, HardenRecipe recipe, IFocusGroup focuses) {
		Ingredient recipeIngredients = recipe.getTofu();
		int borderSlotSize = 18;


		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18)
				.addIngredients(recipeIngredients);
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18 - borderSlotSize)
				.addIngredients(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18 + borderSlotSize)
				.addIngredients(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 18).addItemStack(recipe.getResultItem());

	}


	@Override
	public void draw(HardenRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		arrow.draw(stack, 72 - 17, 35 - 17);
	}
}
*/



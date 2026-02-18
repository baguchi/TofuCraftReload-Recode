package baguchi.tofucraft.recipe;

import baguchi.tofucraft.registry.TofuRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;

public class BitternRecipe implements Recipe<RecipeInput> {

	/**
	 * The ingredient used for the Before it hardens tofu.
	 */
	protected FluidStackTemplate fluid;
	protected Ingredient ingredient;
	/**
	 * This ingredient used for the harden tofu.
	 */
	final ItemStackTemplate result;

	public BitternRecipe(FluidStackTemplate fluid, Ingredient ingredient, ItemStackTemplate results) {
		this.fluid = fluid;
		this.ingredient = ingredient;
		this.result = results;
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return "";
	}

	/**
	 * Gets an ingredient that can be used to match an Block as a tofu for this tofu.
	 *
	 * @return An ingredient that can used to match an Block as a tofu for the tofu.
	 */
	public FluidStackTemplate getFluid() {

		return this.fluid;
	}

	public Ingredient getBitternIngredient() {
		return ingredient;
	}

	@Override
	public boolean matches(RecipeInput recipeInput, Level level) {
		ItemStack stack = recipeInput.getItem(0);
		ItemStack stack2 = recipeInput.getItem(1);
		if (FluidUtil.getFirstStackContained(stack).is(this.fluid.fluid()) && !stack2.isEmpty()) {
			return this.ingredient.test(stack2);
		}

		return false;
	}

	@Override
	public ItemStack assemble(RecipeInput recipeInput) {
		return this.getResult().create();
	}

	public ItemStackTemplate getResult() {
		return result;
	}

	@Override
	public RecipeSerializer<? extends Recipe<RecipeInput>> getSerializer() {

		return TofuRecipes.RECIPE_BITTERN.get();
	}

	@Override
	public RecipeType<? extends Recipe<RecipeInput>> getType() {
		return TofuRecipes.RECIPETYPE_BITTERN.get();
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return null;
	}
}
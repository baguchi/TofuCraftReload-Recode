package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class HardenRecipe implements Recipe<EmptyRecipeInput> {

	/**
	 * The ingredient used for the Before it hardens tofu.
	 */
	protected Ingredient tofu;
	/**
	 * This ingredient used for the harden tofu.
	 */
	final ItemStack result;

	public HardenRecipe(Ingredient tofu, ItemStack results) {
		this.tofu = tofu;
		this.result = results;
	}

	/**
	 * Gets an ingredient that can be used to match an Block as a tofu for this tofu.
	 *
	 * @return An ingredient that can used to match an Block as a tofu for the tofu.
	 */
	public Ingredient getTofu() {

		return this.tofu;
	}

	/**
	 * Gets all the possible results when harden the tofu.
	 *
	 * @return An array of harden results for the tofu.
	 */

	public void setTofu(Ingredient tofu) {

		this.tofu = tofu;
	}

	@Override
	public boolean matches(EmptyRecipeInput p_44002_, Level p_44003_) {
		return false;
	}

	@Override
	public ItemStack assemble(EmptyRecipeInput p_44001_, HolderLookup.Provider p_336092_) {
		return null;
	}


	public ItemStack getResult() {
		return result;
	}


	@Override
	public RecipeSerializer<? extends Recipe<EmptyRecipeInput>> getSerializer() {

		return TofuRecipes.RECIPE_HARDER.get();
	}

	@Override
	public RecipeType<? extends Recipe<EmptyRecipeInput>> getType() {

		return TofuRecipes.RECIPETYPE_HARDER.get();
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
package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public class BitternRecipe implements Recipe<EmptyRecipeInput> {

	/**
	 * The ingredient used for the Before it hardens tofu.
	 */
	protected FluidStack fluid;
	protected FluidStack extraFluid;
	/**
	 * This ingredient used for the harden tofu.
	 */
	final ItemStack result;

	public BitternRecipe(FluidStack fluid, FluidStack extraFluid, ItemStack results) {
		this.fluid = fluid;
		this.extraFluid = extraFluid;
		this.result = results;
	}

	/**
	 * Gets an ingredient that can be used to match an Block as a tofu for this tofu.
	 *
	 * @return An ingredient that can used to match an Block as a tofu for the tofu.
	 */
	public FluidStack getFluid() {

		return this.fluid;
	}

	public FluidStack getExtraFluid() {
		return extraFluid;
	}

	@Override
	public boolean matches(EmptyRecipeInput p_44002_, Level p_44003_) {
		return false;
	}

	@Override
	public ItemStack assemble(EmptyRecipeInput p_44001_, HolderLookup.Provider p_336092_) {
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return false;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
		return this.result;
	}

	public ItemStack getResult() {
		return result;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {

		return TofuRecipes.RECIPE_BITTERN.get();
	}

	@Override
	public RecipeType<?> getType() {

		return TofuRecipes.RECIPETYPE_BITTERN.get();
	}
}
package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.Nullable;

public class BitternRecipe implements Recipe<RecipeInput> {

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
	public boolean matches(RecipeInput recipeInput, Level level) {
		ItemStack stack = recipeInput.getItem(0);
		ItemStack stack2 = recipeInput.getItem(1);
		@Nullable IFluidHandlerItem fluidHandler = stack.getCapability(Capabilities.FluidHandler.ITEM);
		@Nullable IFluidHandlerItem fluidHandler2 = stack2.getCapability(Capabilities.FluidHandler.ITEM);

		if (fluidHandler != null && fluidHandler2 != null) {
			return fluidHandler.isFluidValid(1000, fluid) && fluidHandler2.isFluidValid(250, extraFluid);
		}

		return false;
	}

	@Override
	public ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
		return this.getResultItem(provider).copy();
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return p_43999_ > 0 && p_44000_ > 0;
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
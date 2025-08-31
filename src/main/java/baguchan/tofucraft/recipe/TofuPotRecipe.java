package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface TofuPotRecipe extends Recipe<Container> {
	@Override
	default RecipeType<?> getType() {
		return TofuRecipes.RECIPETYPE_TOFU_POT.get();
	}

	TofuPotCategory category();

	int getCookTime();

	float getExperience();

	FluidIngredient fluidIngredient();

	ItemStack getResult();

	default boolean matchesWithFluid(FluidStack fluid, RecipeWrapper inv, Level worldIn) {
		if (this.fluidIngredient() == FluidIngredient.EMPTY)
			return fluid.isEmpty() && matches(inv, worldIn);
		return this.fluidIngredient().test(fluid) && matches(inv, worldIn);
	}

	default boolean matchesWithFluid(FluidStack fluid, Container inv, Level worldIn) {
		if (this.fluidIngredient() == FluidIngredient.EMPTY)
			return fluid.isEmpty() && matches(inv, worldIn);
		return this.fluidIngredient().test(fluid) && matches(inv, worldIn);
	}

	@Override
	default ItemStack getToastSymbol() {
		return TofuBlocks.TOFU_POT.get().asItem().getDefaultInstance();
	}
}

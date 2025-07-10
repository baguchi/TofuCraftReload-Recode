package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

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


	@Override
	default ItemStack getToastSymbol() {
		return TofuBlocks.TOFU_POT.get().asItem().getDefaultInstance();
	}
}

package baguchi.tofucraft.recipe;

import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import baguchi.tofucraft.registry.TofuRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;

import java.util.Optional;

public interface TofuPotRecipe extends Recipe<CraftingInput> {
	@Override
	default RecipeType<? extends TofuPotRecipe> getType() {
		return TofuRecipes.RECIPETYPE_TOFU_POT.get();
	}

	TofuPotCategory category();

	default RecipeBookCategory recipeBookCategory() {
		RecipeBookCategory var10000;
		switch (this.category()) {
			case DRINK -> var10000 = TofuRecipeBookCategory.COOKING_DRINKS.get();
			case MEAL -> var10000 = TofuRecipeBookCategory.COOKING_MEALS.get();
			case FAST_FOOD -> var10000 = TofuRecipeBookCategory.COOKING_FAST_FOODS.get();
			case MISC -> var10000 = TofuRecipeBookCategory.COOKING_MISC.get();
			default -> throw new MatchException((String) null, (Throwable) null);
		}

		return var10000;
	}

	int getCookTime();

	float getExperience();

	Optional<FluidIngredient> fluidIngredient();

	ItemStack getResult();
}

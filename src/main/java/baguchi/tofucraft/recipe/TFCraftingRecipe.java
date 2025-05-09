package baguchi.tofucraft.recipe;

import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import baguchi.tofucraft.registry.TofuRecipes;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeType;

public interface TFCraftingRecipe extends Recipe<CraftingInput> {
	@Override
	default RecipeType<? extends Recipe<CraftingInput>> getType() {
		return TofuRecipes.RECIPETYPE_TF_CRAFT.get();
	}

	TFCraftingCategory category();

	int getNeedTF();

	default RecipeBookCategory recipeBookCategory() {
		RecipeBookCategory var10000;
		switch (this.category()) {
			case TF_MECHA -> var10000 = TofuRecipeBookCategory.TF_MECHA.get();
			case MISC -> var10000 = TofuRecipeBookCategory.TF_MISC.get();
			default -> throw new MatchException((String) null, (Throwable) null);
		}

		return var10000;
	}
}

package baguchi.tofucraft.compat.jei;

import baguchi.tofucraft.recipe.BitternRecipe;
import baguchi.tofucraft.recipe.HardenRecipe;
import baguchi.tofucraft.recipe.TFCraftingRecipe;
import baguchi.tofucraft.recipe.TofuPotRecipe;
import baguchi.tofucraft.registry.TofuRecipes;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeMap;

import java.util.ArrayList;
import java.util.List;

public class JEIContents {
	public static List<RecipeHolder<TFCraftingRecipe>> getAllTFCraftRecipes(RecipeMap manager) {
		List<RecipeHolder<TFCraftingRecipe>> recipes = new ArrayList<>();
		recipes.addAll(manager.byType(TofuRecipes.RECIPETYPE_TF_CRAFT.get()));
		return recipes;
	}

	public static List<RecipeHolder<TofuPotRecipe>> getAllTofuPotRecipes(RecipeMap manager) {
		List<RecipeHolder<TofuPotRecipe>> recipes = new ArrayList<>();
		recipes.addAll(manager.byType(TofuRecipes.RECIPETYPE_TOFU_POT.get()));
		return recipes;
	}

	public static List<RecipeHolder<BitternRecipe>> getAllBitternRecipes(RecipeMap manager) {
		List<RecipeHolder<BitternRecipe>> recipes = new ArrayList<>();
		recipes.addAll(manager.byType(TofuRecipes.RECIPETYPE_BITTERN.get()));
		return recipes;
	}

	public static List<RecipeHolder<HardenRecipe>> getAllHardenRecipes(RecipeMap manager) {
		List<RecipeHolder<HardenRecipe>> recipes = new ArrayList<>();
		recipes.addAll(manager.byType(TofuRecipes.RECIPETYPE_HARDER.get()));
		return recipes;
	}
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.recipe.TofuPotCategory;
import baguchan.tofucraft.recipe.TofuPotRecipe;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;

public class TofuRecipeCategories {
	public static RecipeBookCategories COOKING_SEARCH = RecipeBookCategories.valueOf("TOFUCRAFT_COOKING_SEARCH");
	public static RecipeBookCategories COOKING_MEALS = RecipeBookCategories.valueOf("TOFUCRAFT_COOKING_MEALS");
	public static RecipeBookCategories COOKING_DRINKS = RecipeBookCategories.valueOf("TOFUCRAFT_COOKING_DRINKS");
	public static RecipeBookCategories COOKING_MISC = RecipeBookCategories.valueOf("TOFUCRAFT_COOKING_MISC");
	public static RecipeBookCategories COOKING_FAST_FOOD = RecipeBookCategories.valueOf("TOFUCRAFT_COOKING_FAST_FOODS");

	public static void init(RegisterRecipeBookCategoriesEvent event) {
		event.registerBookCategories(RecipeBookType.valueOf("TOFUCRAFT_COOKING"), ImmutableList.of(COOKING_SEARCH, COOKING_MEALS, COOKING_DRINKS, COOKING_MISC));
		event.registerAggregateCategory(COOKING_SEARCH, ImmutableList.of(COOKING_MEALS, COOKING_DRINKS, COOKING_MISC));
		event.registerRecipeCategoryFinder(TofuRecipes.RECIPETYPE_TOFU_POT.get(), recipe ->
		{
			if (recipe.value() instanceof TofuPotRecipe cookingRecipe) {
				TofuPotCategory tab = cookingRecipe.category();
				if (tab != null) {
					return switch (tab) {
						case MEAL -> COOKING_MEALS;
						case DRINK -> COOKING_DRINKS;
						case MISC -> COOKING_MISC;
						case FAST_FOOD -> COOKING_FAST_FOOD;
					};
				}
			}
			return COOKING_MISC;
		});
	}
}
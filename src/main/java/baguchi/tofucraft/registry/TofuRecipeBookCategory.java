package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuRecipeBookCategory {
	public static final DeferredRegister<RecipeBookCategory> RECIPE_CATEGORY_REGISTER = DeferredRegister.create(BuiltInRegistries.RECIPE_BOOK_CATEGORY, TofuCraftReload.MODID);

	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> COOKING_FAST_FOODS = register("cooking_fast_foods");
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> COOKING_MEALS = register("cooking_meals");
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> COOKING_DRINKS = register("cooking_drinks");
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> COOKING_MISC = register("cooking_misc");

	private static DeferredHolder<RecipeBookCategory, RecipeBookCategory> register(String p_380980_) {
		return RECIPE_CATEGORY_REGISTER.register(p_380980_, RecipeBookCategory::new);
	}

	public static final RecipeBookCategory SEARCH = new RecipeBookCategory();


}

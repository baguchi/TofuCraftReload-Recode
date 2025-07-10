package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.TFCraftingCategory;
import baguchan.tofucraft.recipe.TFCraftingRecipe;
import baguchan.tofucraft.recipe.TofuPotCategory;
import baguchan.tofucraft.recipe.TofuPotRecipe;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuRecipeBookTypes {
	public static final RecipeBookType WORK_STATION = RecipeBookType.create("tofu_work_station");

	public static Supplier<RecipeBookCategories> COOKING_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_COOKING_SEARCH", new ItemStack(Items.COMPASS)));
	public static Supplier<RecipeBookCategories> COOKING_MEALS = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_COOKING_MEALS", new ItemStack(TofuItems.MISOSOUP.get())));
	public static Supplier<RecipeBookCategories> COOKING_DRINKS = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_COOKING_DRINKS", new ItemStack(TofuItems.SOYMILK.get())));
	public static Supplier<RecipeBookCategories> COOKING_MISC = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_COOKING_MISC", new ItemStack(TofuItems.SOY_CHEESE.get())));
	public static Supplier<RecipeBookCategories> COOKING_FAST_FOOD = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_COOKING_FAST_FOODS", new ItemStack(TofuItems.YUDOFU.get())));

	public static Supplier<RecipeBookCategories> TF_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_TF_SEARCH", new ItemStack(Items.COMPASS)));
	public static Supplier<RecipeBookCategories> TF_MISC = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_TF_MISC", new ItemStack(TofuItems.TF_CIRCUIT.get())));
	public static Supplier<RecipeBookCategories> TF_MECHA = Suppliers.memoize(() -> RecipeBookCategories.create("TOFUCRAFT_TF_MECHA", new ItemStack(TofuItems.TF_BATTERY.get())));


	public static void init(RegisterRecipeBookCategoriesEvent event) {
		event.registerBookCategories(RecipeBookType.valueOf("TOFUCRAFT_COOKING"), ImmutableList.of(COOKING_SEARCH.get(), COOKING_MEALS.get(), COOKING_FAST_FOOD.get(), COOKING_DRINKS.get(), COOKING_MISC.get()));
		event.registerAggregateCategory(COOKING_SEARCH.get(), ImmutableList.of(COOKING_MEALS.get(), COOKING_FAST_FOOD.get(), COOKING_DRINKS.get(), COOKING_MISC.get()));
		event.registerRecipeCategoryFinder(TofuRecipes.RECIPETYPE_TOFU_POT.get(), recipe ->
		{
			if (recipe instanceof TofuPotRecipe cookingRecipe) {
				TofuPotCategory tab = cookingRecipe.category();
				if (tab != null) {
					return switch (tab) {
						case MEAL -> COOKING_MEALS.get();
						case DRINK -> COOKING_DRINKS.get();
						case MISC -> COOKING_MISC.get();
						case FAST_FOOD -> COOKING_FAST_FOOD.get();
					};
				}
			}
			return COOKING_MISC.get();
		});

		event.registerBookCategories(RecipeBookType.valueOf("TOFUCRAFT_TF_CRAFT"), ImmutableList.of(TF_SEARCH.get(), TF_MECHA.get(), TF_MISC.get()));
		event.registerAggregateCategory(TF_SEARCH.get(), ImmutableList.of(TF_MECHA.get(), TF_MISC.get()));
		event.registerRecipeCategoryFinder(TofuRecipes.RECIPETYPE_TF_CRAFT.get(), recipe ->
		{
			if (recipe instanceof TFCraftingRecipe cookingRecipe) {
				TFCraftingCategory tab = cookingRecipe.category();
				if (tab != null) {
					return switch (tab) {
						case TF_MECHA -> TF_MECHA.get();
						case MISC -> TF_MISC.get();
					};
				}
			}
			return TF_MISC.get();
		});
	}
}

package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface IWorkRecipe extends Recipe<CraftingInput> {
	default RecipeType<?> getType() {
		return TofuRecipes.RECIPETYPE_TOFU_WORK_STATION.get();
	}

	default boolean canCraftInDimensions(int p_266835_, int p_266829_) {
		return p_266835_ >= 1 && p_266829_ >= 1;
	}

	default ItemStack getToastSymbol() {
		return new ItemStack(TofuBlocks.TOFU_WORK_STATION.get());
	}

	boolean isIngredient(ItemStack p_266982_);

	boolean isBaseIngredient(ItemStack p_266962_);

	boolean isSubIngredient(ItemStack p_267132_);
}
package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface TFCraftingRecipe extends Recipe<CraftingInput> {
	@Override
	default RecipeType<?> getType() {
		return TofuRecipes.RECIPETYPE_TF_CRAFT.get();
	}

	TFCraftingCategory category();

	int getNeedTF();

	@Override
	default ItemStack getToastSymbol() {
		return TofuBlocks.TF_CRAFTING_TABLE.get().asItem().getDefaultInstance();
	}
}

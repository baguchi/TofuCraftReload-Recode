package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface TFCraftingRecipe extends Recipe<Container> {
	@Override
	default RecipeType<?> getType() {
		return TofuRecipes.RECIPETYPE_TF_CRAFT.get();
	}

	TFCraftingCategory category();

	int getNeedTF();


	ItemStack getResult();


	@Override
	default ItemStack getToastSymbol() {
		return TofuBlocks.TF_CRAFTING_TABLE.get().asItem().getDefaultInstance();
	}
}

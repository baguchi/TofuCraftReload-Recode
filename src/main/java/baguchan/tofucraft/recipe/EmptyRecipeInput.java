package baguchan.tofucraft.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class EmptyRecipeInput implements RecipeInput {
	@Override
	public ItemStack getItem(int p_346128_) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}

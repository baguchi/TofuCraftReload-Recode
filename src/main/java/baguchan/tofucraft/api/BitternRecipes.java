package baguchan.tofucraft.api;

import com.google.common.collect.Maps;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;

public class BitternRecipes {
	public static final Map<Fluid, Block> recipesList = Maps.newHashMap();

	public static void addRecipe(Fluid input, Block stack) {
		if (findResult(input))
			return;
		recipesList.put(input, stack);
	}

	public static boolean findResult(Fluid input) {
		for (Fluid key : recipesList.keySet()) {
			if (compareFluids(key, input))
				return true;
		}
		return false;
	}

	public static Map.Entry<Fluid, Block> getResult(Fluid stack) {
		for (Map.Entry<Fluid, Block> entry : recipesList.entrySet()) {
			if (compareFluids(entry.getKey(), stack))
				return entry;
		}
		return null;
	}

	public static Map<Fluid, Block> getRecipeList() {
		return recipesList;
	}

	public static void removeRecipe(Fluid input) {
		recipesList.remove(input);
	}

	public static void clearAllRecipes() {
		recipesList.clear();
	}

	public static boolean compareFluids(Fluid input, Fluid output) {
		Fluid inputFluid = input, outputFluid = output;
		boolean flag = true;
		if (!inputFluid.getTags().isEmpty() && !outputFluid.getTags().isEmpty())
			flag = outputFluid.getTags().equals(inputFluid.getTags());
		return (input == output && flag);
	}
}

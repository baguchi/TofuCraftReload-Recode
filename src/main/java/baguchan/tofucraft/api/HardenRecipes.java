package baguchan.tofucraft.api;

import com.google.common.collect.Maps;
import net.minecraft.world.level.block.Block;

import java.util.Map;


public class HardenRecipes {
	public static final Map<Block, Block> recipesList = Maps.newHashMap();

	public static void addRecipe(Block input, Block stack) {
		if (findResult(input))
			return;
		recipesList.put(input, stack);
	}

	public static boolean findResult(Block input) {
		for (Block key : recipesList.keySet()) {
			if (compareBlocks(key, input))
				return true;
		}
		return false;
	}

	public static Map.Entry<Block, Block> getResult(Block stack) {
		for (Map.Entry<Block, Block> entry : recipesList.entrySet()) {
			if (compareBlocks(entry.getKey(), stack))
				return entry;
		}
		return null;
	}

	public static Map<Block, Block> getRecipeList() {
		return recipesList;
	}

	public static void removeRecipe(Block input) {
		recipesList.remove(input);
	}

	public static void clearAllRecipes() {
		recipesList.clear();
	}

	public static boolean compareBlocks(Block input, Block output) {
		Block inputBlock = input, outputBlock = output;
		boolean flag = true;
		if (!inputBlock.getTags().isEmpty() && !outputBlock.getTags().isEmpty())
			flag = outputBlock.getTags().equals(inputBlock.getTags());
		return (input == output && flag);
	}
}

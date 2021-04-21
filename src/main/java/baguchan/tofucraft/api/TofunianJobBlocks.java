package baguchan.tofucraft.api;

import baguchan.tofucraft.entity.TofunianEntity;
import com.google.common.collect.Maps;

import java.util.Map;

import net.minecraft.block.Block;

public class TofunianJobBlocks {
	public static final Map<Block, TofunianEntity.Roles> recipesList = Maps.newHashMap();

	public static void addJobBlock(Block input, TofunianEntity.Roles roles) {
		recipesList.put(input, roles);
	}

	public static Map<Block, TofunianEntity.Roles> getJobBlockList() {
		return recipesList;
	}

	public static void removeJobBlock(Block input) {
		recipesList.remove(input);
	}

	public static void clearAllJobBlocks() {
		recipesList.clear();
	}
}

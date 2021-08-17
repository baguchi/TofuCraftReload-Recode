package baguchan.tofucraft.data;

import baguchan.tofucraft.registry.TofuItems;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;

import java.nio.file.Path;
import java.util.function.Consumer;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void saveAdvancement(HashCache p_208310_1_, JsonObject p_208310_2_, Path p_208310_3_) {
		//Silence. This just makes it so that we don't gen advancements
		//TODO Recipe advancements control the unlock of a recipe, so if we ever consider actually making them, recipes should unlock based on also possible prerequisite conditions, instead of ONLY obtaining the item itself
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		helmetItem(consumer, "tofudiamond_helmet", TofuItems.ARMOR_TOFU_DIAMONDHELMET, TofuItems.TOFUDIAMOND);
		helmetItem(consumer, "tofudiamond_chestplate", TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, TofuItems.TOFUDIAMOND);
		helmetItem(consumer, "tofudiamond_leggings", TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, TofuItems.TOFUDIAMOND);
		helmetItem(consumer, "tofudiamond_boots", TofuItems.ARMOR_TOFU_DIAMONDBOOTS, TofuItems.TOFUDIAMOND);
	}
}

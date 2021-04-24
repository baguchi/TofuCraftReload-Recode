package baguchan.tofucraft.registry;

import baguchan.tofucraft.api.BitternRecipes;
import baguchan.tofucraft.api.HardenRecipes;
import baguchan.tofucraft.api.TofunianJobBlocks;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.block.Blocks;

public class TofuRecipes {
	public static void register() {
		BitternRecipes.addRecipe(TofuFluids.SOYMILK, TofuBlocks.KINUTOFU);
		BitternRecipes.addRecipe(TofuFluids.SOYMILK_HELL, TofuBlocks.HELLTOFU);
		BitternRecipes.addRecipe(TofuFluids.SOYMILK_SOUL, TofuBlocks.SOULTOFU);
		HardenRecipes.addRecipe(TofuBlocks.MOMENTOFU, TofuBlocks.ISHITOFU);
		HardenRecipes.addRecipe(TofuBlocks.ISHITOFU, TofuBlocks.METALTOFU);
		TofunianJobBlocks.addJobBlock(Blocks.CRAFTING_TABLE, TofunianEntity.Roles.TOFUCOOK);
		TofunianJobBlocks.addJobBlock(Blocks.FURNACE, TofunianEntity.Roles.TOFUSMITH);
		TofunianJobBlocks.addJobBlock(Blocks.CAULDRON, TofunianEntity.Roles.SOYWORKER);
	}
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.api.BitternRecipes;
import baguchan.tofucraft.api.HardenRecipes;
import baguchan.tofucraft.api.TofunianJobBlocks;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;

public class TofuRecipes {
	public static void register() {
		BitternRecipes.addRecipe((Fluid) TofuFluids.SOYMILK, TofuBlocks.KINUTOFU);
		BitternRecipes.addRecipe((Fluid) TofuFluids.SOYMILK_HELL, TofuBlocks.HELLTOFU);
		BitternRecipes.addRecipe((Fluid) TofuFluids.SOYMILK_SOUL, TofuBlocks.SOULTOFU);
		HardenRecipes.addRecipe(TofuBlocks.MOMENTOFU, TofuBlocks.ISHITOFU);
		HardenRecipes.addRecipe(TofuBlocks.ISHITOFU, TofuBlocks.METALTOFU);
		TofunianJobBlocks.addJobBlock(Blocks.field_150462_ai, TofunianEntity.Roles.TOFUCOOK);
		TofunianJobBlocks.addJobBlock(Blocks.field_150460_al, TofunianEntity.Roles.TOFUSMITH);
		TofunianJobBlocks.addJobBlock(Blocks.field_150383_bp, TofunianEntity.Roles.SOYWORKER);
	}
}

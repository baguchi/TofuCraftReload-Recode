package baguchan.tofucraft.registry;

import baguchan.tofucraft.api.BitternRecipes;
import baguchan.tofucraft.api.HardenRecipes;

public class TofuRecipes {
	public static void register() {
		BitternRecipes.addRecipe(TofuFluids.SOYMILK, TofuBlocks.KINUTOFU);
		HardenRecipes.addRecipe(TofuBlocks.MOMENTOFU, TofuBlocks.ISHITOFU);
		HardenRecipes.addRecipe(TofuBlocks.ISHITOFU, TofuBlocks.METALTOFU);
	}
}

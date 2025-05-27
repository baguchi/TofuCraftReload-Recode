package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipePropertySet;

public class TofuRecipePropertySets {
	public static final ResourceKey<RecipePropertySet> BITTERN = register("bittern");
	public static final ResourceKey<RecipePropertySet> HARDER = register("harder");
	public static final ResourceKey<RecipePropertySet> TF_CRAFT = register("tf_craft");
	public static final ResourceKey<RecipePropertySet> TOFU_POT = register("tofu_pot");

	private static ResourceKey<RecipePropertySet> register(String id) {
		return ResourceKey.create(RecipePropertySet.TYPE_KEY, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, id));
	}

	public static void addToMap() {
	}
}

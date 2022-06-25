package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.*;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TofuCraftReload.MODID);

	public static final RecipeType<HardenRecipe> RECIPETYPE_HARDER = register("tofucraft:harder");
	public static final RegistryObject<RecipeSerializer<?>> RECIPE_HARDER = RECIPE_SERIALIZERS.register("harder", HardenSerializer::new);

	public static final RecipeType<BitternRecipe> RECIPETYPE_BITTERN = register("tofucraft:bittern");
	public static final RegistryObject<RecipeSerializer<?>> RECIPE_BITTERN = RECIPE_SERIALIZERS.register("bittern", BitternSerializer::new);

	public static final RecipeType<AggregatorRecipe> RECIPETYPE_AGGREGATOR = register("tofucraft:aggregator");
	public static final RegistryObject<RecipeSerializer<?>> RECIPE_AGGREGATOR = RECIPE_SERIALIZERS.register("aggregator",
			AggregatorSerializer::new);

	static <T extends Recipe<?>> RecipeType<T> register(final String p_44120_) {
		return new RecipeType<T>() {
			public String toString() {
				return p_44120_;
			}
		};
	}
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.BitternRecipe;
import baguchan.tofucraft.recipe.BitternSerializer;
import baguchan.tofucraft.recipe.HardenRecipe;
import baguchan.tofucraft.recipe.HardenSerializer;
import baguchan.tofucraft.recipe.TFCraftingRecipe;
import baguchan.tofucraft.recipe.TFShapedRecipe;
import baguchan.tofucraft.recipe.TFShapelessRecipe;
import baguchan.tofucraft.recipe.TofuPotRecipe;
import baguchan.tofucraft.recipe.TofuPotShapelessRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuRecipes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, TofuCraftReload.MODID);

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, TofuCraftReload.MODID);

	public static final Supplier<RecipeType<HardenRecipe>> RECIPETYPE_HARDER = RECIPE_TYPES.register("harder", () -> register(TofuCraftReload.prefix("harder")));
	public static final Supplier<HardenSerializer> RECIPE_HARDER = RECIPE_SERIALIZERS.register("harder", HardenSerializer::new);

	public static final Supplier<RecipeType<BitternRecipe>> RECIPETYPE_BITTERN = RECIPE_TYPES.register("bittern", () -> register(TofuCraftReload.prefix("bittern")));
	public static final Supplier<BitternSerializer> RECIPE_BITTERN = RECIPE_SERIALIZERS.register("bittern", BitternSerializer::new);

	public static final Supplier<RecipeType<TofuPotRecipe>> RECIPETYPE_TOFU_POT = RECIPE_TYPES.register("tofu_pot", () -> register(TofuCraftReload.prefix("tofu_pot")));
	public static final Supplier<TofuPotShapelessRecipe.Serializer> RECIPE_TOFU_POT_SHAPELESS = RECIPE_SERIALIZERS.register("tofu_pot_shapeless", TofuPotShapelessRecipe.Serializer::new);

	public static final Supplier<RecipeType<TFCraftingRecipe>> RECIPETYPE_TF_CRAFT = RECIPE_TYPES.register("tf_craft", () -> register(TofuCraftReload.prefix("tf_craft")));
	public static final Supplier<TFShapelessRecipe.Serializer> RECIPE_TF_CRAFT_SHAPELESS = RECIPE_SERIALIZERS.register("tf_craft_shapeless", TFShapelessRecipe.Serializer::new);
	public static final Supplier<TFShapedRecipe.Serializer> RECIPE_TF_CRAFT_SHAPED = RECIPE_SERIALIZERS.register("tf_craft_shaped", TFShapedRecipe.Serializer::new);


	static <T extends Recipe<?>> RecipeType<T> register(final ResourceLocation p_44120_) {
		return new RecipeType<T>() {
			public String toString() {
				return p_44120_.toString();
			}
		};
	}
}

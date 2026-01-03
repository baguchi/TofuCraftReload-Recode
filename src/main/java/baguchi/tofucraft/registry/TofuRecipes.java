package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.recipe.BitternRecipe;
import baguchi.tofucraft.recipe.BitternSerializer;
import baguchi.tofucraft.recipe.HardenRecipe;
import baguchi.tofucraft.recipe.HardenSerializer;
import baguchi.tofucraft.recipe.TFCraftingRecipe;
import baguchi.tofucraft.recipe.TFShapedRecipe;
import baguchi.tofucraft.recipe.TFShapelessRecipe;
import baguchi.tofucraft.recipe.TFTofuMakerRecipe;
import baguchi.tofucraft.recipe.TofuPotRecipe;
import baguchi.tofucraft.recipe.TofuPotShapelessRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuRecipes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, TofuCraftReload.MODID);

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, TofuCraftReload.MODID);

	public static final Supplier<RecipeType<HardenRecipe>> RECIPETYPE_HARDER = RECIPE_TYPES.register("harder", () -> RecipeType.simple(TofuCraftReload.prefix("harder")));
	public static final Supplier<HardenSerializer> RECIPE_HARDER = RECIPE_SERIALIZERS.register("harder", HardenSerializer::new);

	public static final Supplier<RecipeType<BitternRecipe>> RECIPETYPE_BITTERN = RECIPE_TYPES.register("bittern", () -> RecipeType.simple(TofuCraftReload.prefix("bittern")));
	public static final Supplier<BitternSerializer> RECIPE_BITTERN = RECIPE_SERIALIZERS.register("bittern", BitternSerializer::new);

	public static final Supplier<RecipeType<TofuPotRecipe>> RECIPETYPE_TOFU_POT = RECIPE_TYPES.register("tofu_pot", () -> RecipeType.simple(TofuCraftReload.prefix("tofu_pot")));
	public static final Supplier<TofuPotShapelessRecipe.Serializer> RECIPE_TOFU_POT_SHAPELESS = RECIPE_SERIALIZERS.register("tofu_pot_shapeless", TofuPotShapelessRecipe.Serializer::new);
	public static final Supplier<RecipeType<TFCraftingRecipe>> RECIPETYPE_TF_CRAFT = RECIPE_TYPES.register("tf_craft", () -> RecipeType.simple(TofuCraftReload.prefix("tf_craft")));
	public static final Supplier<TFShapelessRecipe.Serializer> RECIPE_TF_CRAFT_SHAPELESS = RECIPE_SERIALIZERS.register("tf_craft_shapeless", TFShapelessRecipe.Serializer::new);
	public static final Supplier<TFShapedRecipe.Serializer> RECIPE_TF_CRAFT_SHAPED = RECIPE_SERIALIZERS.register("tf_craft_shaped", TFShapedRecipe.Serializer::new);

	public static final Supplier<RecipeType<TFTofuMakerRecipe>> RECIPETYPE_TF_TOFU_MAKER = RECIPE_TYPES.register("tf_tofu_maker", () -> RecipeType.simple(TofuCraftReload.prefix("tf_tofu_maker")));

	public static final Supplier<TFTofuMakerRecipe.Serializer> RECIPE_TF_TOFU_MAKER = RECIPE_SERIALIZERS.register("tf_tofu_maker", TFTofuMakerRecipe.Serializer::new);
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.BitternRecipe;
import baguchan.tofucraft.recipe.BitternSerializer;
import baguchan.tofucraft.recipe.HardenRecipe;
import baguchan.tofucraft.recipe.HardenSerializer;
import baguchan.tofucraft.recipe.TofuWorkStationRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class TofuRecipes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TofuCraftReload.MODID);

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TofuCraftReload.MODID);

	public static final RegistryObject<RecipeType<HardenRecipe>> RECIPETYPE_HARDER = RECIPE_TYPES.register("harder", () -> register(TofuCraftReload.prefix("harder")));
	public static final RegistryObject<HardenSerializer> RECIPE_HARDER = RECIPE_SERIALIZERS.register("harder", HardenSerializer::new);

	public static final RegistryObject<RecipeType<BitternRecipe>> RECIPETYPE_BITTERN = RECIPE_TYPES.register("bittern", () -> register(TofuCraftReload.prefix("bittern")));
	public static final RegistryObject<BitternSerializer> RECIPE_BITTERN = RECIPE_SERIALIZERS.register("bittern", BitternSerializer::new);
	public static final RegistryObject<RecipeType<TofuWorkStationRecipe>> RECIPETYPE_TOFU_WORK_STATION = RECIPE_TYPES.register("tofu_work_station", () -> register(TofuCraftReload.prefix("tofu_work_station")));
	public static final RegistryObject<TofuWorkStationRecipe.Serializer> RECIPE_TOFU_WORK_STATION = RECIPE_SERIALIZERS.register("tofu_work_station", TofuWorkStationRecipe.Serializer::new);

	static <T extends Recipe<?>> RecipeType<T> register(final ResourceLocation p_44120_) {
		return new RecipeType<T>() {
			public String toString() {
				return p_44120_.toString();
			}
		};
	}
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.BitternSerializer;
import baguchan.tofucraft.recipe.HardenSerializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuRecipes {

	public static final RecipeType<?> RECIPETYPE_HARDER = register("tofucraft:harder");
	public static final RecipeSerializer<?> RECIPE_HARDER = new HardenSerializer();

	public static final RecipeType<?> RECIPETYPE_BITTERN = register("tofucraft:bittern");
	public static final RecipeSerializer<?> RECIPE_BITTERN = new BitternSerializer();

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<RecipeSerializer<?>> registry) {
		registry.getRegistry().register(RECIPE_HARDER.setRegistryName(new ResourceLocation(TofuCraftReload.MODID, "harder")));
		registry.getRegistry().register(RECIPE_BITTERN.setRegistryName(new ResourceLocation(TofuCraftReload.MODID, "bittern")));
	}

	static <T extends Recipe<?>> RecipeType<T> register(final String p_44120_) {
		return (RecipeType) Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(p_44120_), new RecipeType<T>() {
			public String toString() {
				return p_44120_;
			}
		});
	}
}

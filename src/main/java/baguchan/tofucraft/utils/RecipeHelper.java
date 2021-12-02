package baguchan.tofucraft.utils;

import baguchan.tofucraft.recipe.BitternInfo;
import baguchan.tofucraft.recipe.HardenInfo;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeHelper {
	@Nullable
	public static ItemStack getTofu(Block block) {

		final RecipeManager manager = getManager();

		if (manager != null && block.asItem() != null) {
			Stream<Recipe<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.getType() == TofuRecipes.RECIPETYPE_HARDER;
			});
			for (Recipe<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe instanceof HardenInfo && ((HardenInfo) recipe).getTofu().test(new ItemStack(block.asItem()))) {
					return ((HardenInfo) recipe).getResults().getItems()[0];
				}
			}
		}

		return null;
	}

	@Nullable
	public static ItemStack getBitternResult(Fluid fluid) {
		final RecipeManager manager = getManager();

		if (manager != null && fluid != null) {
			Stream<Recipe<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.getType() == TofuRecipes.RECIPETYPE_BITTERN;
			});
			for (Recipe<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe instanceof BitternInfo && ((BitternInfo) recipe).getFluid().test(new FluidStack(fluid, 1000))) {
					return ((BitternInfo) recipe).getResults().getItems()[0];
				}
			}
		}

		return null;
	}

	public static RecipeManager getManager() {

		return getManager(null);
	}

	public static RecipeManager getManager(@Nullable RecipeManager manager) {

		return manager != null ? manager : DistExecutor.runForDist(() -> () -> Minecraft.getInstance().player.connection.getRecipeManager(), () -> () -> ServerLifecycleHooks.getCurrentServer().getRecipeManager());
	}
}

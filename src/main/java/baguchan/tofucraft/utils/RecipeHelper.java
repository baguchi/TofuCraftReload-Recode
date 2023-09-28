package baguchan.tofucraft.utils;

import baguchan.tofucraft.recipe.BitternRecipe;
import baguchan.tofucraft.recipe.HardenRecipe;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
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
	/*
	 * This method use in not condition type tofu recipe(like ishi tofu and metal tofu)
	 */
	@Nullable
	public static ItemStack getTofu(ServerLevel serverLevel, Block block) {

		final RecipeManager manager = serverLevel.getRecipeManager();

		if (block.asItem() != null) {
			Stream<RecipeHolder<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.value() instanceof HardenRecipe hardenRecipe && hardenRecipe.getType() == TofuRecipes.RECIPETYPE_HARDER.get();
			});
			for (RecipeHolder<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe.value() instanceof HardenRecipe && ((HardenRecipe) recipe.value()).getTofu().test(new ItemStack(block.asItem()))) {
					return ((HardenRecipe) recipe.value()).getResultItem(serverLevel.registryAccess());
				}
			}
		}

		return null;
	}

	@Nullable
	public static ItemStack getBitternResult(ServerLevel serverLevel, Fluid fluid) {
		final RecipeManager manager = serverLevel.getRecipeManager();

		if (fluid != null) {
			Stream<RecipeHolder<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.value() instanceof BitternRecipe bittern && bittern.getType() == TofuRecipes.RECIPETYPE_BITTERN.get();
			});
			for (RecipeHolder<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe.value() instanceof BitternRecipe && ((BitternRecipe) recipe.value()).getFluid().test(new FluidStack(fluid, 1000))) {
					return ((BitternRecipe) recipe.value()).getResultItem(serverLevel.registryAccess());
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

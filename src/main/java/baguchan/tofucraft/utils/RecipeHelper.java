package baguchan.tofucraft.utils;

import baguchan.tofucraft.recipe.BitternRecipe;
import baguchan.tofucraft.recipe.HardenRecipe;
import baguchan.tofucraft.recipe.TFShapedRecipe;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.recipebook.PlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeHelper {
	public static <T> void placeRecipe(int width, int height, int outputSlot, RecipeHolder<?> recipe, Iterator<T> ingredients, int maxAmount, PlaceRecipe<T> placeRecipeMixin) {
		int i = width;
		int j = height;
		if (recipe.value() instanceof TFShapedRecipe shapedrecipe) {
			i = shapedrecipe.getWidth();
			j = shapedrecipe.getHeight();
		}

		int k1 = 0;

		for (int k = 0; k < height; k++) {
			if (k1 == outputSlot) {
				k1++;
			}

			boolean flag = (float) j < (float) height / 2.0F;
			int l = Mth.floor((float) height / 2.0F - (float) j / 2.0F);
			if (flag && l > k) {
				k1 += width;
				k++;
			}

			for (int i1 = 0; i1 < width; i1++) {
				if (!ingredients.hasNext()) {
					return;
				}

				flag = (float) i < (float) width / 2.0F;
				l = Mth.floor((float) width / 2.0F - (float) i / 2.0F);
				int j1 = i;
				boolean flag1 = i1 < i;
				if (flag) {
					j1 = l + i;
					flag1 = l <= i1 && i1 < l + i;
				}

				if (flag1) {
					placeRecipeMixin.addItemToSlot(ingredients.next(), k1, maxAmount, i1, k);
				} else if (j1 == i1) {
					k1 += width - i1;
					break;
				}

				k1++;
			}
		}
	}

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
				if (recipe.value() instanceof HardenRecipe hardenRecipe && hardenRecipe.getTofu().test(new ItemStack(block.asItem()))) {
					return ((HardenRecipe) recipe.value()).getResultItem(serverLevel.registryAccess());
				}
			}
		}

		return null;
	}

	@Nullable
	public static ItemStack getBitternResult(ServerLevel serverLevel, Fluid fluid, FluidStack extraFluid) {
		final RecipeManager manager = serverLevel.getRecipeManager();

		if (fluid != null) {
			Stream<RecipeHolder<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.value() instanceof BitternRecipe bittern && bittern.getType() == TofuRecipes.RECIPETYPE_BITTERN.get();
			});
			for (RecipeHolder<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe.value() instanceof BitternRecipe bitternRecipe && bitternRecipe.getFluid().is(fluid)) {
					if (bitternRecipe.getExtraFluid().getFluid() == extraFluid.getFluid()) {
						return bitternRecipe.getResultItem(serverLevel.registryAccess());
					}
				}
			}
		}

		return null;
	}

	public static RecipeManager getManager() {
		return getManager(null);
	}

	public static RecipeManager getManager(@Nullable RecipeManager manager) {
		if (manager != null) {
			return manager;
		}
		if (FMLEnvironment.dist == Dist.CLIENT) {
			return Minecraft.getInstance().player.connection.getRecipeManager();
		} else {
			return ServerLifecycleHooks.getCurrentServer().getRecipeManager();
		}
	}
}
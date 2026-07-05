package baguchi.tofucraft.compat.jei.extension;

import baguchi.tofucraft.recipe.BucketToBottleRecipe;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;

public class BucketToBottleExtension implements ICraftingCategoryExtension<BucketToBottleRecipe> {

	@Override
	public List<SlotDisplay> getIngredients(RecipeHolder<BucketToBottleRecipe> recipeHolder) {
		List<SlotDisplay> list = Lists.newArrayList();
		list.add(new SlotDisplay.ItemSlotDisplay(Items.GLASS_BOTTLE));
		return list;
	}

	@Override
	public void setRecipe(RecipeHolder<BucketToBottleRecipe> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		List<List<ItemStack>> inputs = new ArrayList<>();
		inputs.add(List.of(Items.GLASS_BOTTLE.getDefaultInstance()));
		inputs.add(List.of(Items.GLASS_BOTTLE.getDefaultInstance()));
		inputs.add(List.of(Items.GLASS_BOTTLE.getDefaultInstance()));
		var bucket = new ItemStack(TofuItems.TOFU_METAL_BUCKET, 1, DataComponentPatch.builder().set(TofuDataComponents.STORED_FLUID.get(), SimpleFluidContent.copyOf(new FluidStack(recipeHolder.value().getFluid().value(), 1000))).build());

		inputs.add(List.of(bucket));

		craftingGridHelper.createAndSetInputs(builder, inputs, 0, 0);
		builder.setShapeless();

		craftingGridHelper.createAndSetOutputs(builder, List.of(recipeHolder.value().getResult().create()));
	}
}
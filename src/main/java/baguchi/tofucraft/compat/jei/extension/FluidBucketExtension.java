package baguchi.tofucraft.compat.jei.extension;

import baguchi.tofucraft.recipe.FluidBucketRecipe;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;

public class FluidBucketExtension implements ICraftingCategoryExtension<FluidBucketRecipe> {

	@Override
	public List<SlotDisplay> getIngredients(RecipeHolder<FluidBucketRecipe> recipeHolder) {
		List<SlotDisplay> list = Lists.newArrayList();
		list.addAll(recipeHolder.value().getIngredient().items().map(Holder::value).map(Item::getDefaultInstance).map(stack -> new SlotDisplay.ItemStackSlotDisplay(new ItemStackTemplate(stack.getItem()))).toList());
		return list;
	}

	@Override
	public void setRecipe(RecipeHolder<FluidBucketRecipe> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		List<List<ItemStack>> inputs = new ArrayList<>();
		inputs.add(recipeHolder.value().getIngredient().items().map(Holder::value).map(Item::getDefaultInstance).toList());
		inputs.add(List.of(TofuItems.TOFU_METAL_BUCKET.get().getDefaultInstance()));

		craftingGridHelper.createAndSetInputs(builder, inputs, 0, 0);
		builder.setShapeless();

		var bucket = new ItemStack(TofuItems.TOFU_METAL_BUCKET, 1, DataComponentPatch.builder().set(TofuDataComponents.STORED_FLUID.get(), SimpleFluidContent.copyOf(new FluidStack(recipeHolder.value().getFluid().value(), 1000))).build());
		craftingGridHelper.createAndSetOutputs(builder, List.of(bucket));
	}
}
package baguchan.tofucraft.mixin;

import baguchan.tofucraft.recipe.TFShapedRecipe;
import baguchan.tofucraft.utils.RecipeHelper;
import net.minecraft.recipebook.PlaceRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(PlaceRecipe.class)
public interface PlaceRecipeMixin {

	@Inject(method = "placeRecipe", at = @At("HEAD"), cancellable = true)
	private <T> void placeRecipe(int width, int height, int outputSlot, RecipeHolder<?> recipe, Iterator<T> ingredients, int maxAmount, CallbackInfo ci) {
		if (recipe.value() instanceof TFShapedRecipe shapedrecipe) {
			RecipeHelper.placeRecipe(width, height, outputSlot, recipe, ingredients, maxAmount, (PlaceRecipe<T>) this);
		}
	}
}

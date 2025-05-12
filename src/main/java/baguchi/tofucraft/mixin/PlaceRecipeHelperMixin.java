package baguchi.tofucraft.mixin;

import baguchi.tofucraft.recipe.TFShapedRecipe;
import net.minecraft.recipebook.PlaceRecipeHelper;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlaceRecipeHelper.class)
public interface PlaceRecipeHelperMixin {
	@Shadow
	static <T> void placeRecipe(int p_362769_, int p_364202_, int p_380112_, int p_379800_, Iterable<T> p_360986_, PlaceRecipeHelper.Output<T> p_362411_) {

	}

	@Inject(method = "placeRecipe(IILnet/minecraft/world/item/crafting/Recipe;Ljava/lang/Iterable;Lnet/minecraft/recipebook/PlaceRecipeHelper$Output;)V", at = @At("HEAD"), cancellable = true)
	private static <T> void placeRecipe(int p_380087_, int p_379836_, Recipe<?> p_379573_, Iterable<T> p_379986_, PlaceRecipeHelper.Output<T> p_380252_, CallbackInfo ci) {
		if (p_379573_ instanceof TFShapedRecipe shapedrecipe) {
			placeRecipe(p_380087_, p_379836_, shapedrecipe.getWidth(), shapedrecipe.getHeight(), p_379986_, p_380252_);
			ci.cancel();
		}
	}
}

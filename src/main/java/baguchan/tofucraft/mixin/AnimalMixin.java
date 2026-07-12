package baguchan.tofucraft.mixin;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public abstract class AnimalMixin extends AgeableMob {
	protected AnimalMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
	}

	@Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
	private void tofucraft$extraFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (!cir.getReturnValue()) {
			var self = (Animal) (Object) this;
			if (self instanceof Cow) {
				var food = Ingredient.of(
						TofuItems.RICE.get()
				);
				cir.setReturnValue(food.test(stack));
			}
		}
	}
}

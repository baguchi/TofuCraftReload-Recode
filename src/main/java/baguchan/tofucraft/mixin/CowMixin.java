package baguchan.tofucraft.mixin;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Cow.class)
public abstract class CowMixin extends Animal {

	private static final Ingredient FOOD_ITEMS = Ingredient.of(TofuItems.RICE.get());

	protected CowMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
	}


	@Inject(method = "registerGoals", at = @At("TAIL"))
	protected void registerGoals(CallbackInfo ci) {
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, FOOD_ITEMS, false));
	}

}

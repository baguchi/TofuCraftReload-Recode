package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FloatGoal.class)
public class FloatGoalMixin {
	@Shadow
	@Final
	private Mob mob;

	@Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
	public void canUse(CallbackInfoReturnable<Boolean> cir) {
		if (!cir.getReturnValue() && this.mob.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK) && this.mob.getFluidHeight(TofuTags.Fluids.SOYMILK) > this.mob.getFluidJumpThreshold()) {
			cir.setReturnValue(true);
		}
	}
}

package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Swim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Swim.class)
public class SwimMixin {
	@Inject(method = "shouldSwim", at = @At("RETURN"), cancellable = true)
	public static <T extends Mob> void canUse(T mob, CallbackInfoReturnable<Boolean> cir) {
		if (!cir.getReturnValue() && mob.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK) && mob.getFluidHeight(TofuTags.Fluids.SOYMILK) > mob.getFluidJumpThreshold()) {
			cir.setReturnValue(true);
		}
	}
}

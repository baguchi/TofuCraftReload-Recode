package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	@Final
	private EntityFluidInteraction fluidInteraction;

	@Shadow
	public abstract void resetFallDistance();

	@Shadow
	@Deprecated
	public abstract boolean isPushedByFluid();

	@Shadow
	private Level level;

	@Inject(method = "updateFluidInteraction", at = @At("RETURN"), cancellable = true)
	public void updateFluidInteraction(CallbackInfoReturnable<Boolean> cir) {
		boolean inSoymilk = this.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK);
		boolean inWaterLike = this.fluidInteraction.isInFluid(TofuTags.Fluids.WATER_LIKE);
		boolean inLavaLike = this.fluidInteraction.isInFluid(TofuTags.Fluids.DOUBANJIANG);
		if (inSoymilk) {
			this.resetFallDistance();
		}

		if (this.isPushedByFluid()) {
			Entity entity = (Entity) (Object) this;
			if (inSoymilk) {
				this.fluidInteraction.applyCurrentTo(TofuTags.Fluids.SOYMILK, entity, 0.007F);
			}

			if (inWaterLike) {
				this.fluidInteraction.applyCurrentTo(TofuTags.Fluids.WATER_LIKE, entity, 0.007F);
			}

			if (inLavaLike) {
				double lavaFlowScale = this.level.environmentAttributes().getDimensionValue(EnvironmentAttributes.FAST_LAVA) ? 0.007 : 0.0023333333333333335;
				this.fluidInteraction.applyCurrentTo(TofuTags.Fluids.DOUBANJIANG, entity, lavaFlowScale);
			}
		}

		if (inSoymilk || inWaterLike || inLavaLike) {
			cir.setReturnValue(true);
		}
	}
}

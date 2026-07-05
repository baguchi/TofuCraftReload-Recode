package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntityExtension {

	@Shadow
	private int noJumpDelay;

	@Shadow
	public abstract void jumpFromGround();

	@Shadow
	protected abstract void travelInWater(Vec3 input, double baseGravity, boolean isFalling, double oldY);

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getFluidJumpThreshold()D", shift = At.Shift.AFTER))
	public void floatInFluid(CallbackInfo ci) {
		double fluidHeight = this.getFluidHeight(TofuTags.Fluids.SOYMILK);
		double fluidJumpThreshold = this.getFluidJumpThreshold();
		if (!this.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK) || this.onGround() && !(fluidHeight > fluidJumpThreshold)) {
			if ((this.onGround() || this.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK) && fluidHeight <= fluidJumpThreshold) && this.noJumpDelay == 0) {
				this.jumpFromGround();
				this.noJumpDelay = 10;
			}
		} else {
			this.jumpInFluid(TofuFluids.SOYMILK.get().getFluidType());
		}

		double fluidHeight2 = this.getFluidHeight(TofuTags.Fluids.DOUBANJIANG);
		double fluidJumpThreshold2 = this.getFluidJumpThreshold();
		if (!this.fluidInteraction.isInFluid(TofuTags.Fluids.DOUBANJIANG) || this.onGround() && !(fluidHeight2 > fluidJumpThreshold2)) {
			if ((this.onGround() || this.fluidInteraction.isInFluid(TofuTags.Fluids.DOUBANJIANG) && fluidHeight2 <= fluidJumpThreshold2) && this.noJumpDelay == 0) {
				this.jumpFromGround();
				this.noJumpDelay = 10;
			}
		} else {
			this.jumpInFluid(TofuFluids.DOUBANJIANG.get().getFluidType());
		}


		double fluidHeight3 = this.getFluidHeight(TofuTags.Fluids.WATER_LIKE);
		double fluidJumpThreshold3 = this.getFluidJumpThreshold();
		if (!this.fluidInteraction.isInFluid(TofuTags.Fluids.WATER_LIKE) || this.onGround() && !(fluidHeight3 > fluidJumpThreshold3)) {
			if ((this.onGround() || this.fluidInteraction.isInFluid(TofuTags.Fluids.WATER_LIKE) && fluidHeight3 <= fluidJumpThreshold3) && this.noJumpDelay == 0) {
				this.jumpFromGround();
				this.noJumpDelay = 10;
			}
		} else {
			this.jumpInFluid(TofuFluids.BITTERN.get().getFluidType());
		}
	}

	@WrapOperation(method = "baseTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
	private boolean baseTick(LivingEntity livingEntity, TagKey<Fluid> type, Operation<Boolean> original) {
		boolean isEyeInFluid = original.call(livingEntity, type);
		if (!isEyeInFluid) {
			return livingEntity.isEyeInFluid(TofuTags.Fluids.SOYMILK) || livingEntity.isEyeInFluid(TofuTags.Fluids.WATER_LIKE);
		} else {
			return true;
		}
	}

	@WrapOperation(method = "shouldTravelInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWater()Z"))
	public boolean travelInFluid(LivingEntity instance, Operation<Boolean> original) {
		return original.call(instance) || this.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK) || this.fluidInteraction.isInFluid(TofuTags.Fluids.WATER_LIKE) || this.fluidInteraction.isInFluid(TofuTags.Fluids.DOUBANJIANG);
	}

	@Inject(method = "travelInLava", at = @At(value = "HEAD"), cancellable = true)
	public void travelInFluid(Vec3 input, double baseGravity, boolean isFalling, double oldY, CallbackInfo ci) {
		if (this.fluidInteraction.isInFluid(TofuTags.Fluids.SOYMILK)) {
			this.travelInWater(input, baseGravity, isFalling, oldY);
			ci.cancel();
		}
		if (this.fluidInteraction.isInFluid(TofuTags.Fluids.WATER_LIKE)) {
			this.travelInWater(input, baseGravity, isFalling, oldY);
			ci.cancel();
		}
	}
}
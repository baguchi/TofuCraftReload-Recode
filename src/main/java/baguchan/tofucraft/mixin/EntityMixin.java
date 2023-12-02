package baguchan.tofucraft.mixin;

import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuParticleTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class)
public abstract class EntityMixin implements net.neoforged.neoforge.common.extensions.IEntityExtension {

	@Shadow
	protected boolean firstTick;
	@Unique
	protected boolean tofuCraftReload_Recode$wasTouchingSoyMilk;
	@Shadow
	@Final
	protected RandomSource random;
	@Shadow
	private EntityDimensions dimensions;

	@Inject(method = "updateInWaterStateAndDoFluidPushing",
			at = @At(value = "RETURN"))
	protected void updateInWaterStateAndDoFluidPushing(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		tofuCraftReload_Recode$updateInSoyMilk();
	}

	@Unique
	void tofuCraftReload_Recode$updateInSoyMilk() {
		if (this.isInFluidType(TofuFluidTypes.SOYMILK.get()) || this.isInFluidType(TofuFluidTypes.SOYMILK_HELL.get()) || this.isInFluidType(TofuFluidTypes.SOYMILK_SOUL.get())) {
			if (!this.tofuCraftReload_Recode$wasTouchingSoyMilk && !this.firstTick) {
				this.tofuCraftReload_Recode$doTofuSplashEffect();
			}
			this.tofuCraftReload_Recode$wasTouchingSoyMilk = true;
		} else {
			this.tofuCraftReload_Recode$wasTouchingSoyMilk = false;
		}

	}

	@Unique
	protected void tofuCraftReload_Recode$doTofuSplashEffect() {
		Entity realEntity = (Entity) ((Object) this);
		Entity entity = (Entity) (realEntity.isVehicle() && realEntity.getControllingPassenger() != null ? realEntity.getControllingPassenger() : realEntity);
		float f = entity == realEntity ? 0.2F : 0.9F;
		Vec3 vec3 = entity.getDeltaMovement();
		float f1 = Math.min(1.0F, (float) Math.sqrt(vec3.x * vec3.x * (double) 0.2F + vec3.y * vec3.y + vec3.z * vec3.z * (double) 0.2F) * f);
		if (f1 < 0.25F) {
			realEntity.playSound(this.getSwimSplashSound(), f1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
		} else {
			realEntity.playSound(this.getSwimHighSpeedSplashSound(), f1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
		}

		float f2 = (float) Mth.floor(realEntity.getY());

		for (int i = 0; (float) i < 1.0F + this.dimensions.width * 20.0F; ++i) {
			double d0 = (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.dimensions.width;
			double d1 = (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.dimensions.width;
			this.level().addParticle(ParticleTypes.BUBBLE, realEntity.getX() + d0, (double) (f2 + 1.0F), realEntity.getZ() + d1, vec3.x, vec3.y - this.random.nextDouble() * (double) 0.2F, vec3.z);
		}

		for (int j = 0; (float) j < 1.0F + this.dimensions.width * 20.0F; ++j) {
			double d2 = (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.dimensions.width;
			double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.dimensions.width;
			this.level().addParticle(TofuParticleTypes.SOYMILK_SPLASH.get(), realEntity.getX() + d2, (double) (f2 + 1.0F), realEntity.getZ() + d3, vec3.x, vec3.y, vec3.z);
		}

		realEntity.gameEvent(GameEvent.SPLASH);
	}

	@Shadow
	protected SoundEvent getSwimHighSpeedSplashSound() {
		return null;
	}

	@Shadow
	protected SoundEvent getSwimSplashSound() {
		return null;
	}

	@Shadow
	public Level level() {
		return null;
	}
}

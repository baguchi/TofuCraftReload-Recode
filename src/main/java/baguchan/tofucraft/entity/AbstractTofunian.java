package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public abstract class AbstractTofunian extends AbstractVillager {
	protected int idleAnimationTimeout;
	public final AnimationState idleAnimationState = new AnimationState();

	public AbstractTofunian(EntityType<? extends AbstractTofunian> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public void tick() {
		super.tick();
		if (getUnhappyCounter() > 0) {
			setUnhappyCounter(getUnhappyCounter() - 1);
		}
		if (this.level().isClientSide()) {
			this.setupAnimationStates();
		}
	}

	public void setupAnimationStates() {
		if (!this.isSleeping()) {
			if (this.idleAnimationTimeout <= 0) {
				this.idleAnimationTimeout = this.random.nextInt(40) + 80;
				this.idleAnimationState.start(this.tickCount);
			} else {
				--this.idleAnimationTimeout;
			}
		}
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return TofuSounds.TOFUNIAN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_) {
		return TofuSounds.TOFUNIAN_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return TofuSounds.TOFUNIAN_DEATH.get();
	}


	protected SoundEvent getTradeUpdatedSound(boolean p_213721_1_) {
		return p_213721_1_ ? TofuSounds.TOFUNIAN_YES.get() : TofuSounds.TOFUNIAN_NO.get();
	}

	public SoundEvent getNotifyTradeSound() {
		return TofuSounds.TOFUNIAN_YES.get();
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return isBaby() ? 0.3F : (sizeIn.height * 0.8F);
	}

	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
}

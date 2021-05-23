package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class AbstractTofunianEntity extends AbstractVillagerEntity {
	public AbstractTofunianEntity(EntityType<? extends AbstractTofunianEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void shakeHead() {
		setUnhappyCounter(40);
		if (!this.level.isClientSide())
			playSound(TofuSounds.TOFUNIAN_NO, getSoundVolume(), getVoicePitch());
	}

	public void tick() {
		super.tick();
		if (getUnhappyCounter() > 0)
			setUnhappyCounter(getUnhappyCounter() - 1);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return TofuSounds.TOFUNIAN_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return TofuSounds.TOFUNIAN_DEATH;
	}


	protected SoundEvent getTradeUpdatedSound(boolean p_213721_1_) {
		return p_213721_1_ ? TofuSounds.TOFUNIAN_YES : TofuSounds.TOFUNIAN_NO;
	}

	public SoundEvent getNotifyTradeSound() {
		return TofuSounds.TOFUNIAN_YES;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return isBaby() ? 0.3F : (sizeIn.height * 0.8F);
	}

	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
}

package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public abstract class AbstractTofunianEntity extends AbstractVillager {
	public AbstractTofunianEntity(EntityType<? extends AbstractTofunianEntity> type, Level worldIn) {
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

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return isBaby() ? 0.3F : (sizeIn.height * 0.8F);
	}

	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
}

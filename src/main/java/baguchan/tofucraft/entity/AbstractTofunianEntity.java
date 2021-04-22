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

	protected SoundEvent func_184639_G() {
		return TofuSounds.TOFUNIAN_AMBIENT;
	}

	public SoundEvent func_213714_ea() {
		return TofuSounds.TOFUNIAN_YES;
	}

	protected SoundEvent func_213721_r(boolean getYesSound) {
		return getYesSound ? TofuSounds.TOFUNIAN_YES : TofuSounds.TOFUNIAN_NO;
	}

	@Nullable
	protected SoundEvent func_184615_bR() {
		return TofuSounds.TOFUNIAN_DEATH;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return isBaby() ? 0.3F : (sizeIn.height * 0.8F);
	}

	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
}

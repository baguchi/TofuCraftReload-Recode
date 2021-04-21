package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuSounds;

import javax.annotation.Nullable;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class AbstractTofunianEntity extends AbstractVillagerEntity {
	public AbstractTofunianEntity(EntityType<? extends AbstractTofunianEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void shakeHead() {
		func_213720_r(40);
		if (!this.level.isClientSide())
			playSound(TofuSounds.TOFUNIAN_NO, func_70599_aP(), func_70647_i());
	}

	public void func_70071_h_() {
		super.func_70071_h_();
		if (func_213719_ec() > 0)
			func_213720_r(func_213719_ec() - 1);
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

	protected float func_213348_b(Pose poseIn, EntitySize sizeIn) {
		return func_70631_g_() ? 0.3F : (sizeIn.field_220316_b * 0.8F);
	}

	public boolean func_213397_c(double distanceToClosestPlayer) {
		return false;
	}
}

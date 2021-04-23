package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.projectile.FukumameEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TofuSpiderEntity extends SpiderEntity implements IRangedAttackMob {
	public TofuSpiderEntity(EntityType<? extends SpiderEntity> p_i48550_1_, World p_i48550_2_) {
		super(p_i48550_1_, p_i48550_2_);
	}


	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new RangedAttackGoal(this, 1.0D, 80, 15.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
		this.targetSelector.addGoal(2, new TargetGoal<>(this, PlayerEntity.class));
		this.targetSelector.addGoal(3, new TargetGoal<>(this, IronGolemEntity.class));
	}

	public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
		playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		for (int i = 0; i < 4; i++) {
			FukumameEntity snowballentity = new FukumameEntity(this.level, this);
			double d0 = p_82196_1_.getEyeY() - getEyeY();
			double d1 = p_82196_1_.getX() - getX();
			double d2 = d0;
			double d3 = p_82196_1_.getZ() - getZ();
			float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
			snowballentity.shoot(d1, d2 + f, d3, 1.0F, 3.0F);
			this.level.addFreshEntity(snowballentity);
		}
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(SpiderEntity p_i45818_1_, Class<T> p_i45818_2_) {
			super(p_i45818_1_, p_i45818_2_, true);
		}

		public boolean canUse() {
			float f = this.mob.getBrightness();
			return !(f >= 0.5F) && super.canUse();
		}
	}

	protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
		return 0.35F;
	}
}

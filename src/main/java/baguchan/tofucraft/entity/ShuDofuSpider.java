package baguchan.tofucraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumSet;

public class ShuDofuSpider extends Monster {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState deathAnimationState = new AnimationState();

	public ShuDofuSpider(EntityType<? extends ShuDofuSpider> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.xpReward = 60;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new ShuDofuSpider.AttackGoal(this));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 6.0F));

		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractTofunian.class, true));
	}

	protected float getStandingEyeHeight(Pose p_33799_, EntityDimensions p_33800_) {
		return 1.25F;
	}

	private boolean isMovingOnLand() {
		return this.onGround && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && !this.isInWaterOrBubble();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double p_21542_) {
		return false;
	}

	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	public void tick() {
		if (this.isMovingOnLand()) {
			this.walkAnimationState.startIfStopped(this.tickCount);
		} else {
			this.walkAnimationState.stop();
		}

		super.tick();
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 4) {
			this.attackAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 7) {
			this.deathAnimationState.start(this.tickCount);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}

	}

	@Override
	public boolean doHurtTarget(Entity p_21372_) {
		this.level.broadcastEntityEvent(this, (byte) 4);
		return super.doHurtTarget(p_21372_);
	}


	@Override
	public void die(DamageSource p_21014_) {
		super.die(p_21014_);
		this.playSound(SoundEvents.BEACON_DEACTIVATE, 2.0F, 1.0F);

		if (!this.level.isClientSide()) {
			this.level.broadcastEntityEvent(this, (byte) 7);
		}
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 38) {
		}
		if (this.deathTime == 40) {
		}

		if (this.deathTime == 100 && !this.level.isClientSide()) {
			this.level.broadcastEntityEvent(this, (byte) 60);
			this.remove(Entity.RemovalReason.KILLED);
		}
	}

	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.FOLLOW_RANGE, 28F).add(Attributes.MOVEMENT_SPEED, 0.6D).add(Attributes.ATTACK_KNOCKBACK, 0.75F).add(Attributes.KNOCKBACK_RESISTANCE, 0.9D).add(Attributes.ARMOR, 12.0F).add(Attributes.ARMOR_TOUGHNESS, 1.0F).add(Attributes.ATTACK_DAMAGE, 8.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}

	static class AttackGoal extends Goal {
		private final ShuDofuSpider spider;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		public AttackGoal(ShuDofuSpider p_32247_) {
			this.spider = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.spider.getTarget();
			return livingentity != null && livingentity.isAlive() && this.spider.canAttack(livingentity);
		}

		public void tick() {
			--this.attackTime;
			LivingEntity livingentity = this.spider.getTarget();
			if (livingentity != null) {
				boolean flag = this.spider.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.lastSeen = 0;
				} else {
					++this.lastSeen;
				}

				double d0 = this.spider.distanceToSqr(livingentity);
				if (d0 < 32.0D) {
					if (!flag) {
						return;
					}
					this.attackStep = 0;

					if (d0 < 4.0D + this.spider.getBbWidth() && this.attackTime <= 0) {
						this.attackTime = 20;
						this.spider.doHurtTarget(livingentity);
					}

					this.spider.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					this.spider.getNavigation().moveTo(this.spider.getTarget(), 1.0F);
				} else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 20;
						} else if (this.attackStep <= 2) {
							this.attackTime = 10;
						} else {
							this.attackTime = 30;
							this.attackStep = 0;
						}

					}

					this.spider.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					this.spider.getNavigation().stop();
				} else if (this.lastSeen < 5) {
					this.spider.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
				}

				super.tick();
			}

		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		private double getFollowDistance() {
			return this.spider.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}

}

package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.control.StafeableFlyingMoveControl;
import baguchan.tofucraft.entity.goal.SpinAttackGoal;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumSet;
import java.util.List;

public class TofuGandlem extends Monster implements RangedAttackMob {
	private static final EntityDataAccessor<Boolean> DATA_ID_SHOOT = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ID_RUSH = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ID_SLEEP = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BOOLEAN);


	private static final UniformInt RUSH_COOLDOWN = UniformInt.of(100, 400);


	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState shootAnimationState = new AnimationState();
	public final AnimationState shootingAnimationState = new AnimationState();
	public final AnimationState rushAnimationState = new AnimationState();
	public final AnimationState deathAnimationState = new AnimationState();

	public TofuGandlem(EntityType<? extends TofuGandlem> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.moveControl = new StafeableFlyingMoveControl(this, 20, false);
		this.xpReward = 60;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_ID_SHOOT, false);
		this.entityData.define(DATA_ID_RUSH, false);
		this.entityData.define(DATA_ID_SLEEP, false);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new DoNothingGoal());
		this.goalSelector.addGoal(2, new SpinAttackGoal(this, RUSH_COOLDOWN));
		this.goalSelector.addGoal(3, new AttackGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 0.9D));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Mob.class, 6.0F));

		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractTofunian.class, true));
	}

	@Override
	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	public boolean isShoot() {
		return this.entityData.get(DATA_ID_SHOOT);
	}

	public void setShoot(boolean shoot) {
		this.entityData.set(DATA_ID_SHOOT, shoot);
	}

	public boolean isRush() {
		return this.entityData.get(DATA_ID_RUSH);
	}

	public void setRush(boolean rush) {
		this.entityData.set(DATA_ID_RUSH, rush);
	}

	public boolean isSleep() {
		return this.entityData.get(DATA_ID_SLEEP);
	}

	public void setSleep(boolean rush) {
		this.entityData.set(DATA_ID_SLEEP, rush);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sleep", this.isSleep());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setSleep(compound.getBoolean("Sleep"));
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
		if (this.level.isClientSide()) {
			if (this.isAlive() && !this.isSleep()) {
				this.idleAnimationState.startIfStopped(this.tickCount);
			} else {
				this.idleAnimationState.stop();
				this.rushAnimationState.stop();
			}

			if (this.isAlive() && this.isShoot()) {
				this.shootAnimationState.startIfStopped(this.tickCount);
			} else {
				this.shootAnimationState.stop();
				this.shootingAnimationState.stop();
			}
		}

		if (this.isAlive() && this.isRush()) {
			Vec3 movement = this.getDeltaMovement();
			this.checkRushAttack(this.getBoundingBox(), this.getBoundingBox().expandTowards(movement.x, movement.y, movement.z));
		}

		super.tick();
	}

	protected void checkRushAttack(AABB p_21072_, AABB p_21073_) {
		AABB aabb = p_21072_.minmax(p_21073_);
		List<Entity> list = this.level.getEntities(this, aabb);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); ++i) {
				Entity entity = list.get(i);
				this.rushAttack(entity);
				break;
			}
		} else if (this.horizontalCollision && this.tickCount % 3 == 0) {
			this.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 2.0F, 1.0F);
		}

	}

	public void rushAttack(Entity p_36347_) {
		if (p_36347_.isAttackable()) {
			p_36347_.hurt(DamageSource.mobAttack(this), 16.0F);
			float i = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK); // Forge: Initialize this value to the attack knockback attribute of the player, which is by default 0
			i += EnchantmentHelper.getKnockbackBonus(this) + 0.65F;

			if (i > 0) {
				if (p_36347_ instanceof LivingEntity) {
					((LivingEntity) p_36347_).knockback((double) ((float) i * 0.5F), (double) Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(this.getYRot() * ((float) Math.PI / 180F))));
				} else {
					p_36347_.push((double) (-Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F), 0.1D, (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F));
				}

				this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
				this.setSprinting(false);
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 4) {
			this.attackAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 5) {
			this.shootingAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 6) {
			this.rushAnimationState.start(this.tickCount);
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
	public void travel(Vec3 p_21280_) {
		this.flyingSpeed = this.getSpeed() * 0.21600002F;
		super.travel(p_21280_);
		this.flyingSpeed = 0.02F;
	}

	public void aiStep() {
		super.aiStep();
		if (this.isAlive() && !this.isSleep()) {
			this.calculateFlapping();
		}

		if (this.isSleep() && this.getTarget() != null) {
			this.setSleep(false);
			this.playSound(SoundEvents.BEACON_ACTIVATE, 3.0F, 1.0F);
		}

	}

	private void calculateFlapping() {
		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}
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
			this.playSound(SoundEvents.ITEM_BREAK, 1.0F, 1.4F);
		}
		if (this.deathTime == 40) {
			this.playSound(SoundEvents.ITEM_BREAK, 1.0F, 1.35F);
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
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 300.0D).add(Attributes.FOLLOW_RANGE, 28F).add(Attributes.MOVEMENT_SPEED, 0.26D).add(Attributes.FLYING_SPEED, 0.25F).add(Attributes.ATTACK_KNOCKBACK, 0.75F).add(Attributes.KNOCKBACK_RESISTANCE, 0.9D).add(Attributes.ARMOR, 12.0F).add(Attributes.ARMOR_TOUGHNESS, 1.0F).add(Attributes.ATTACK_DAMAGE, 8.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}
	@Override
	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		this.playSound(SoundEvents.SHULKER_SHOOT, 3.0F, 1.0F);
		for (int i = 0; i < 4; i++) {
			FukumameEntity fukumame = new FukumameEntity(this.level, this);
			double d1 = p_29912_.getX() - this.getX();
			double d2 = p_29912_.getEyeY() - this.getEyeY();
			double d3 = p_29912_.getZ() - this.getZ();
			float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
			fukumame.shoot(d1, d2 + f, d3, 1.0F, 2.0F + p_29913_);
			fukumame.damage = 2;
			this.level.addFreshEntity(fukumame);
		}
	}

	static class AttackGoal extends Goal {
		private final TofuGandlem gandlem;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		private boolean strafingClockwise;
		private boolean strafingBackwards;
		private int strafingTime = -1;

		public AttackGoal(TofuGandlem p_32247_) {
			this.gandlem = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.gandlem.getTarget();
			return livingentity != null && livingentity.isAlive() && this.gandlem.canAttack(livingentity);
		}

		public void start() {
			this.attackStep = 0;
			this.gandlem.setShoot(true);
		}

		public void stop() {
			this.lastSeen = 0;
			this.gandlem.setShoot(false);
		}

		public void tick() {
			--this.attackTime;
			LivingEntity livingentity = this.gandlem.getTarget();
			if (livingentity != null) {
				boolean flag = this.gandlem.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.lastSeen = 0;
				} else {
					++this.lastSeen;
				}

				double d0 = this.gandlem.distanceToSqr(livingentity);
				if (d0 < 8.0D) {
					if (!flag) {
						return;
					}
					this.attackStep = 0;

					if (this.gandlem.isShoot()) {
						this.gandlem.setShoot(false);
					}

					if (d0 < 5.0D + this.gandlem.getBbWidth() && this.attackTime <= 0) {
						this.attackTime = 20;
						this.gandlem.doHurtTarget(livingentity);
					}

					this.gandlem.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					this.gandlem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
				} else if (d0 < this.getFollowDistance() * this.getFollowDistance() * 0.85F && flag) {
					if (!this.gandlem.isShoot()) {
						this.gandlem.setShoot(true);
					}
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 30;
						} else if (this.attackStep <= 4) {
							this.attackTime = 8;
						} else {
							this.attackTime = 30;
							this.attackStep = 0;
						}

						if (this.attackStep > 1) {
							double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
							this.gandlem.level.broadcastEntityEvent(this.gandlem, (byte) 5);
							this.gandlem.performRangedAttack(livingentity, attackTime);
						}
					}

					this.gandlem.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);

					if (d0 < this.getFollowDistance() * this.getFollowDistance() * 0.75F && this.lastSeen <= 20) {
						++this.strafingTime;
						this.gandlem.getNavigation().stop();
					} else {
						this.strafingTime = -1;
						this.gandlem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
					}

					if (this.strafingTime >= 20) {
						if ((double) this.gandlem.getRandom().nextFloat() < 0.3D) {
							this.strafingClockwise = !this.strafingClockwise;
						}

						if ((double) this.gandlem.getRandom().nextFloat() < 0.3D) {
							this.strafingBackwards = !this.strafingBackwards;
						}

						this.strafingTime = 0;
					}

					if (this.strafingTime > -1) {
						if (d0 > (double) (this.getFollowDistance() * this.getFollowDistance() * 0.6F)) {
							this.strafingBackwards = false;
						} else if (d0 < (double) (this.getFollowDistance() * this.getFollowDistance() * 0.4F)) {
							this.strafingBackwards = true;
						}

						this.gandlem.getMoveControl().strafe(this.strafingBackwards ? -0.35F : 0.35F, this.strafingClockwise ? 0.35F : -0.35F);
					}

				} else if (this.lastSeen < 200 && d0 > this.getFollowDistance() * this.getFollowDistance() || d0 <= this.getFollowDistance() * this.getFollowDistance()) {
					this.gandlem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.1F);
				}

				super.tick();
			}
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		private double getFollowDistance() {
			return this.gandlem.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}

	class DoNothingGoal extends Goal {
		public DoNothingGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			return TofuGandlem.this.isSleep();
		}
	}
}

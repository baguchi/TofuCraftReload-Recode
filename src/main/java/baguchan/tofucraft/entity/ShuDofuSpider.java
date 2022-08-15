package baguchan.tofucraft.entity;

import baguchan.tofucraft.client.particle.ParticleStink;
import baguchan.tofucraft.client.particle.ParticleZundaCloud;
import baguchan.tofucraft.registry.TofuParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
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
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class ShuDofuSpider extends Monster {
	private static final EntityDataAccessor<Boolean> DATA_ID_JUMP = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> ATTACK_ANIMATION = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> JUMP_ANIMATION = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);

	private int attackTime;
	private int jumpTime;
	private int impactTime;
	private int stinkTime;

	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState deathAnimationState = new AnimationState();
	public final AnimationState jumpAnimationState = new AnimationState();

	private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

	public ShuDofuSpider(EntityType<? extends ShuDofuSpider> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.xpReward = 60;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ATTACK_ANIMATION, false);
		this.entityData.define(JUMP_ANIMATION, false);
		this.entityData.define(DATA_ID_JUMP, false);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(2, new ShuDofuSpider.JumpAttackGoal());
		this.goalSelector.addGoal(3, new ShuDofuSpider.AttackGoal(this));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Mob.class, 6.0F));

		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, true, null));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractTofunian.class, 10, true, true, null));
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
		++this.stinkTime;
		if (this.stinkTime == 120) {
			this.level.addParticle(new ParticleStink.StinkData(TofuParticleTypes.STINK.get(), 45f, 20, ParticleStink.EnumStinkBehavior.GROW, 2.0f), this.getX(), this.getY() + 1.0, this.getZ(), 0, 0, 0);
			this.stinkTime = this.random.nextInt(0, 20);
		}
		if (this.isAlive() && this.isMovingOnLand()) {
			this.walkAnimationState.startIfStopped(this.tickCount);
		} else {
			this.walkAnimationState.stop();
		}
		if (this.isAlive() && this.isAttackAnim() && this.getTarget() != null) {
			++this.attackTime;
			if (this.attackTime == 10) {
				this.level.broadcastEntityEvent(this, (byte) 100);
				float speed = 0.25f;
				var yaw = (float) Math.toRadians(this.getYHeadRot() + 90);
				Vec3 m = this.getDeltaMovement().add(speed * Math.cos(yaw), 0, speed * Math.sin(yaw));
				this.setDeltaMovement(m.x, 0.2, m.z);
			}
			if (this.attackTime == 14) {
				float radius = 2;
				float swipePosX = (float) (this.getX() + radius * Math.cos(Math.toRadians(this.getYHeadRot() + 90)));
				float swipePosZ = (float) (this.getZ() + radius * Math.sin(Math.toRadians(this.getYHeadRot() + 90)));
				AABB hitBox = new AABB(new BlockPos(swipePosX, this.getY() - 0.5f, swipePosZ)).inflate(1, 1.5, 1);
				List<LivingEntity> entitiesHit = this.level.getEntitiesOfClass(LivingEntity.class, hitBox);
				for (LivingEntity entity : entitiesHit) {
					if (entity != this) {
						doHurtTarget(entity);
					}
				}
				this.attackTime = 0;
				this.setAttackAnimation(false);
			}
		}

		if (this.isAlive() && this.isJumpAnim() && this.getTarget() != null) {
			++this.jumpTime;
			if (this.jumpTime == 1) {
				this.level.broadcastEntityEvent(this, (byte) 102);
			}
			if (this.jumpTime == 2) {
				Vec3 movement = this.getDeltaMovement();
				this.checkJumpAttack(this.getBoundingBox(), this.getBoundingBox().expandTowards(movement.x, movement.y, movement.z));
			}
			if (this.jumpTime == 30 && this.onGround) {
				this.impactTime = this.jumpTime + 40;
			}
			if (this.jumpTime >= this.impactTime && this.onGround) {
				int count = 10;
				//for (int i = 1; i <= count; i++) {
				//double yaw = i * 365f / count;
				this.level.addParticle(new ParticleZundaCloud.CloudData(TofuParticleTypes.ZUNDA_CLOUD.get(), 50f, 20, ParticleZundaCloud.EnumCloudBehavior.GROW, 1f), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
				//}
				float radius = 5;
				AABB hitBox = new AABB(new BlockPos(ShuDofuSpider.this.getX() - radius, ShuDofuSpider.this.getY() - 1, ShuDofuSpider.this.getZ() - radius), new BlockPos(ShuDofuSpider.this.getX() + radius, ShuDofuSpider.this.getY() + 2, ShuDofuSpider.this.getZ() + radius));
				List<LivingEntity> entitiesHit = ShuDofuSpider.this.level.getEntitiesOfClass(LivingEntity.class, hitBox);
				for (LivingEntity entity : entitiesHit) {
					if (entity != ShuDofuSpider.this) {
						entity.hurt(DamageSource.mobAttack(ShuDofuSpider.this), 30.0F);
					}
				}
				this.impactTime = 0;
				this.jumpTime = 0;
				this.setJumpAnimation(false);
			}
		}

		super.tick();
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 100) {
			this.attackAnimationState.start(this.tickCount);
		}
		if (p_70103_1_ == 101) {
			this.deathAnimationState.start(this.tickCount);
		}
		if (p_70103_1_ == 102) {
			this.jumpAnimationState.start(this.tickCount);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}
	}

	@Override
	public boolean doHurtTarget(Entity p_21372_) {
		return super.doHurtTarget(p_21372_);
	}

	protected void checkJumpAttack(AABB p_21072_, AABB p_21073_) {
		AABB aabb = p_21072_.minmax(p_21073_);
		List<Entity> list = this.level.getEntities(this, aabb);
		if (!list.isEmpty()) {
			for (Entity entity : list) {
				this.jumpAttack(entity);
				break;
			}
		} else if (this.horizontalCollision && this.tickCount % 3 == 0) {
			this.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 2.0F, 1.0F);
		}
	}

	public void jumpAttack(Entity p_36347_) {
		if (p_36347_.isAttackable()) {
			p_36347_.hurt(DamageSource.mobAttack(this), 18.0F);
			float i = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK); // Forge: Initialize this value to the attack knockback attribute of the player, which is by default 0
			i += EnchantmentHelper.getKnockbackBonus(this) + 0.65F;

			if (i > 0) {
				if (p_36347_ instanceof LivingEntity) {
					((LivingEntity) p_36347_).knockback((double) ((float) i * 0.5F), (double) Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(this.getYRot() * ((float) Math.PI / 180F))));
				} else {
					p_36347_.push((double) (-Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F), 0.1D, (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F));
				}

				this.setSprinting(false);
			}
		}
	}

	protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
		this.playSound(SoundEvents.SPIDER_STEP, 1.0F, 0.6F);
	}


	@Override
	public void die(DamageSource p_21014_) {
		super.die(p_21014_);
		this.playSound(SoundEvents.SPIDER_DEATH, 1.0F, 0.6F);

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
			this.level.broadcastEntityEvent(this, (byte) 101);
			this.remove(Entity.RemovalReason.KILLED);
		}
	}

	public boolean isPushable() {
		return false;
	}

	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 400.0D).add(Attributes.FOLLOW_RANGE, 28F).add(Attributes.MOVEMENT_SPEED, 0.5D).add(Attributes.ATTACK_KNOCKBACK, 0.75F).add(Attributes.KNOCKBACK_RESISTANCE, 0.9D).add(Attributes.ARMOR, 12.0F).add(Attributes.ARMOR_TOUGHNESS, 1.0F).add(Attributes.ATTACK_DAMAGE, 8.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}

	static class AttackGoal extends MeleeAttackGoal {
		private final ShuDofuSpider spider;
		private int attackTime;

		public AttackGoal(ShuDofuSpider p_32247_) {
			super(p_32247_, 0.8D, true);
			this.spider = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.spider.getTarget();
			if (livingentity != null) {
				if (this.spider.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ())) {
					return livingentity.isAlive() && this.spider.canAttack(livingentity);
				}
				return false;
			}
			return false;
		}

		protected double getAttackReachSqr(LivingEntity p_33825_) {
			return (double) (2.0F + p_33825_.getBbWidth());
		}

		public void tick() {
			--this.attackTime;

			var entity = this.spider;
			var entityTarget = entity.getTarget();

			if (entityTarget != null) {
				entity.getLookControl().setLookAt(entityTarget, 10.0F, 10.0F);
			}
			if (entityTarget != null && entity.distanceTo(entityTarget) <= 4.2F && attackTime <= 0) {
				entity.setAttackAnimation(true);
				this.attackTime = 40;
			}
			super.tick();
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		private double getFollowDistance() {
			return this.spider.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}


	public class JumpAttackGoal extends JumpGoal {
		private int attackTime;

		public boolean canUse() {
			if (attackTime == 0) {
				LivingEntity livingentity = ShuDofuSpider.this.getTarget();
				if (livingentity != null && livingentity.isAlive()) {
					if (livingentity.getMotionDirection() != livingentity.getDirection()) {
						return false;
					} else {
						boolean flag = ShuDofuSpider.isPathClear(ShuDofuSpider.this, livingentity);
						if (!flag) {
							ShuDofuSpider.this.getNavigation().createPath(livingentity, 0);
						}
						ShuDofuSpider.this.setJump(true);
						this.attackTime = 80;
						return flag;
					}
				} else {
					return false;
				}
			} else {
				--this.attackTime;
				return false;
			}
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = ShuDofuSpider.this.getTarget();
			if (livingentity != null && livingentity.isAlive()) {
				double d0 = ShuDofuSpider.this.getDeltaMovement().y;
				return (!(d0 * d0 < (double) 0.05F) || !(Math.abs(ShuDofuSpider.this.getXRot()) < 15.0F) || !ShuDofuSpider.this.onGround);
			} else {
				return false;
			}
		}

		public boolean isInterruptable() {
			return false;
		}

		public void start() {
			ShuDofuSpider.this.setJumping(true);
			LivingEntity livingentity = ShuDofuSpider.this.getTarget();
			if (livingentity != null) {
				ShuDofuSpider.this.setJumpAnimation(true);
				ShuDofuSpider.this.getLookControl().setLookAt(livingentity, 60.0F, 30.0F);
				Vec3 vec3 = (new Vec3(livingentity.getX() - ShuDofuSpider.this.getX(), livingentity.getY() - ShuDofuSpider.this.getY(), livingentity.getZ() - ShuDofuSpider.this.getZ())).normalize();
				ShuDofuSpider.this.setDeltaMovement(ShuDofuSpider.this.getDeltaMovement().add(vec3.x * 1.2D, 1.2D, vec3.z * 1.2D));
			}

			ShuDofuSpider.this.getNavigation().stop();
		}

		public void stop() {
			ShuDofuSpider.this.setJump(false);
		}

		public void tick() {
			LivingEntity livingentity = ShuDofuSpider.this.getTarget();
			if (livingentity != null) {
				ShuDofuSpider.this.getLookControl().setLookAt(livingentity, 60.0F, 30.0F);
			}

			Vec3 vec3 = ShuDofuSpider.this.getDeltaMovement();
			if (vec3.y * vec3.y < (double) 0.03F && ShuDofuSpider.this.getXRot() != 0.0F) {

				ShuDofuSpider.this.setXRot(Mth.rotlerp(ShuDofuSpider.this.getXRot(), 0.0F, 0.2F));
			} else {
				double d0 = vec3.horizontalDistance();
				double d1 = Math.signum(-vec3.y) * Math.acos(d0 / vec3.length()) * (double) (180F / (float) Math.PI);
				ShuDofuSpider.this.setXRot((float) d1);
			}

		}
	}

	protected void customServerAiStep() {
		this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
	}

	public static boolean isPathClear(ShuDofuSpider p_28472_, LivingEntity p_28473_) {
		double d0 = p_28473_.getZ() - p_28472_.getZ();
		double d1 = p_28473_.getX() - p_28472_.getX();
		double d2 = d0 / d1;

		for (int j = 0; j < 6; ++j) {
			double d3 = d2 == 0.0D ? 0.0D : d0 * (double) ((float) j / 6.0F);
			double d4 = d2 == 0.0D ? d1 * (double) ((float) j / 6.0F) : d3 / d2;

			for (int k = 1; k < 4; ++k) {
				if (!p_28472_.level.getBlockState(new BlockPos(p_28472_.getX() + d4, p_28472_.getY() + (double) k, p_28472_.getZ() + d3)).getMaterial().isReplaceable()) {
					return false;
				}
			}
		}

		return true;
	}

	public void startSeenByPlayer(ServerPlayer p_31483_) {
		super.startSeenByPlayer(p_31483_);
		this.bossEvent.addPlayer(p_31483_);
	}

	public void stopSeenByPlayer(ServerPlayer p_31488_) {
		super.stopSeenByPlayer(p_31488_);
		this.bossEvent.removePlayer(p_31488_);
	}

	public void readAdditionalSaveData(CompoundTag p_31474_) {
		super.readAdditionalSaveData(p_31474_);
		if (this.hasCustomName()) {
			this.bossEvent.setName(this.getDisplayName());
		}
	}

	public void setCustomName(@Nullable Component p_31476_) {
		super.setCustomName(p_31476_);
		this.bossEvent.setName(this.getDisplayName());
	}

	public void setAttackAnimation(boolean attack) {
		this.entityData.set(ATTACK_ANIMATION, attack);
	}

	public boolean isAttackAnim() {
		return this.entityData.get(ATTACK_ANIMATION);
	}

	public void setJumpAnimation(boolean jump) {
		this.entityData.set(JUMP_ANIMATION, jump);
	}

	public boolean isJumpAnim() {
		return entityData.get(JUMP_ANIMATION);
	}

	private void setJump(boolean jump) {
		this.entityData.set(DATA_ID_JUMP, jump);
	}

	public boolean isJump() {
		return this.entityData.get(DATA_ID_JUMP);
	}

	protected double getAttackReachSqr(LivingEntity p_25556_) {
		return (double) (this.getBbWidth() * 2.0F * this.getBbWidth() * 2.0F + p_25556_.getBbWidth());
	}

}

package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.registry.TofuAdvancements;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.List;

public class TofuSpider extends Spider implements RangedAttackMob {
	private static final EntityDataAccessor<Boolean> DATA_CONVERTING_ID = SynchedEntityData.defineId(ZombieVillager.class, EntityDataSerializers.BOOLEAN);

	private int conversionTime;

	public TofuSpider(EntityType<? extends TofuSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
		this.xpReward = 4;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_CONVERTING_ID, false);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 18.0D).add(Attributes.ATTACK_DAMAGE, 2.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.FOLLOW_RANGE, 18.0F);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new TofuSpiderAttackGoal(this));
	}

	public void addAdditionalSaveData(CompoundTag p_34397_) {
		super.addAdditionalSaveData(p_34397_);
		p_34397_.putInt("ConversionTime", this.isConverting() ? this.conversionTime : -1);
	}

	public void readAdditionalSaveData(CompoundTag p_34387_) {
		super.readAdditionalSaveData(p_34387_);
		if (p_34387_.contains("ConversionTime", 99) && p_34387_.getInt("ConversionTime") > -1) {
			this.startConverting(p_34387_.getInt("ConversionTime"));
		}
	}

	public void tick() {
		if (!this.level.isClientSide && this.isAlive() && this.isConverting()) {
			this.conversionTime -= 1;
			if (this.conversionTime <= 0 && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(this, TofuEntityTypes.SHUDOFUSPIDER.get(), (timer) -> this.conversionTime = timer)) {
				this.finishConversion((ServerLevel) this.level);
			}
		}

		super.tick();
	}

	private void finishConversion(ServerLevel p_34399_) {
		ShuDofuSpider shudofuSpider = this.convertTo(TofuEntityTypes.SHUDOFUSPIDER.get(), false);
		shudofuSpider.finalizeSpawn(p_34399_, p_34399_.getCurrentDifficultyAt(shudofuSpider.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);

		List<Player> players = this.level.getNearbyPlayers(TargetingConditions.forNonCombat(), this, this.getBoundingBox().inflate(60D));

		for (Player player : players) {
			if (player instanceof ServerPlayer serverPlayer) {
				TofuAdvancements.NIGHTMARES_ECHO.trigger(serverPlayer);
			}
		}

		shudofuSpider.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
		if (!this.isSilent()) {
			this.playSound(SoundEvents.WITHER_SPAWN, 4.0F, 1.0F);
		}
		net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, shudofuSpider);
	}

	public boolean removeWhenFarAway(double p_34414_) {
		return !this.isConverting();
	}

	public boolean isConverting() {
		return this.getEntityData().get(DATA_CONVERTING_ID);
	}

	public void startConverting(int p_34385_) {
		this.getEntityData().set(DATA_CONVERTING_ID, true);
		this.conversionTime = p_34385_;
		this.level.broadcastEntityEvent(this, (byte) 16);
	}

	protected float getStandingEyeHeight(Pose p_33799_, EntityDimensions p_33800_) {
		return 0.35F;
	}

	static class TofuSpiderAttackGoal extends Goal {
		private final TofuSpider spider;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		public TofuSpiderAttackGoal(TofuSpider p_32247_) {
			this.spider = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.spider.getTarget();
			return livingentity != null && livingentity.isAlive() && this.spider.canAttack(livingentity);
		}

		public void start() {
			this.attackStep = 0;
		}

		public void stop() {
			this.lastSeen = 0;
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
					this.spider.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
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

						if (this.attackStep > 1) {
							this.spider.performRangedAttack(livingentity, attackTime);

							this.spider.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 0.4F / (this.spider.getRandom().nextFloat() * 0.4F + 0.8F));
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

		private double getFollowDistance() {
			return this.spider.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}

	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		this.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 1.0F);
		for (int i = 0; i < 3; i++) {
			FukumameEntity fukumame = new FukumameEntity(this.level, this);
			double d1 = p_29912_.getX() - this.getX();
			double d2 = p_29912_.getEyeY() - this.getEyeY();
			double d3 = p_29912_.getZ() - this.getZ();
			float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
			fukumame.damage = 1.0F;
			fukumame.shoot(d1, d2 + f, d3, 1.0F, 2.0F + p_29913_);

			this.level.addFreshEntity(fukumame);
		}
	}

	@Override
	public float getScale() {
		return this.isBaby() ? 0.65F : 1.15F;
	}
}
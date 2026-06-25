package baguchi.tofucraft.entity;

import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class TofuSlime extends AbstractCubeMob {
	private static final EntityDataAccessor<Boolean> DATA_CONVERSION_ID = SynchedEntityData.defineId(TofuSlime.class, EntityDataSerializers.BOOLEAN);

	private int onZundaTime;
	private int conversionTime;


	public TofuSlime(EntityType<? extends TofuSlime> p_33588_, Level p_33589_) {
		super(p_33588_, p_33589_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_CONVERSION_ID, false);
	}

	@Override
	protected void addBehaviourGoals() {

	}

	@Override
	protected void addTargetingGoals() {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, (target, level) -> Math.abs(target.getY() - this.getY()) <= (double) 4.0F));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
	}

	public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
		Vec3 vec3 = new Vec3(p_37266_, p_37267_, p_37268_)
				.normalize()
				.add(
						this.random.triangle(0.0, 0.0172275 * (double) p_37270_),
						this.random.triangle(0.0, 0.0172275 * (double) p_37270_),
						this.random.triangle(0.0, 0.0172275 * (double) p_37270_)
				)
				.scale((double) p_37269_);
		this.setDeltaMovement(vec3);
		double d0 = vec3.horizontalDistance();
		this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * 180.0F / (float) Math.PI));
		this.setXRot((float) (Mth.atan2(vec3.y, d0) * 180.0F / (float) Math.PI));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
	}

	@Override
	public void thunderHit(ServerLevel p_19927_, LightningBolt p_19928_) {
		if (!this.level().isClientSide() && this.isAlive()) {
			this.doZundaConversion();
		}
	}

	public boolean isZundaConverting() {
		return this.getEntityData().get(DATA_CONVERSION_ID);
	}

	@Override
	public void tick() {
		if (!this.level().isClientSide() && this.isAlive() && !this.isNoAi()) {
			if (this.isZundaConverting()) {
				--this.conversionTime;
				if (this.conversionTime < 0) {
					this.doZundaConversion();
				}
			} else if (this.convertsOnZunda()) {
				if (this.level().getBiome(this.blockPosition()).is(TofuBiomes.ZUNDA_FOREST) || this.hasData(TofuAttachments.TOFU_LIVING.get()) && this.getData(TofuAttachments.TOFU_LIVING.get()).isZundafied()) {
					++this.onZundaTime;
					if (this.onZundaTime >= 600) {
						this.startZundaConversion(300);
					}
				} else {
					this.onZundaTime = -1;
				}
			}
		}

		super.tick();
	}

	@Override
	protected SoundEvent getJumpSound() {
		return this.isTiny() ? SoundEvents.SLIME_JUMP_SMALL : SoundEvents.SLIME_JUMP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isTiny() ? SoundEvents.SLIME_HURT_SMALL : SoundEvents.SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isTiny() ? SoundEvents.SLIME_DEATH_SMALL : SoundEvents.SLIME_DEATH;
	}

	@Override
	protected SoundEvent getSquishSound() {
		return this.isTiny() ? SoundEvents.SLIME_SQUISH_SMALL : SoundEvents.SLIME_SQUISH;
	}

	protected boolean convertsOnZunda() {
		return true;
	}

	private void startZundaConversion(int p_34279_) {
		this.conversionTime = p_34279_;
		this.getEntityData().set(DATA_CONVERSION_ID, true);
	}

	protected void doZundaConversion() {
		this.spawnAtLocation((ServerLevel) this.level(), new ItemStack(TofuItems.TOFU_ZUNDA.get(), 2 * getSize()));
		this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED);
		this.discard();
	}

	@Override
	public void addAdditionalSaveData(ValueOutput p_34319_) {
		super.addAdditionalSaveData(p_34319_);
		p_34319_.putInt("OnZundaTime", this.convertsOnZunda() ? this.onZundaTime : -1);
		p_34319_.putInt("ZundaConversionTime", this.isZundaConverting() ? this.conversionTime : -1);
		p_34319_.putBoolean("ZundaConverting", this.isZundaConverting());
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_34305_) {
		super.readAdditionalSaveData(p_34305_);
		this.onZundaTime = p_34305_.getIntOr("OnZundaTime", -1);
		if (p_34305_.getIntOr("ZundaConversionTime", -1) > -1) {
			this.startZundaConversion(p_34305_.getIntOr("ZundaConversionTime", -1));
		}
	}

	@Override
	protected ParticleOptions getParticleType() {
		return new ItemParticleOption(ParticleTypes.ITEM, TofuItems.TOFU_KINU.get());
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, RandomSource p_33011_) {
		if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
			return false;
		} else {
			int var3 = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
			return var3 <= p_33011_.nextInt(8);
		}
	}

	public static boolean checkMonsterSpawnRules(EntityType<? extends TofuSlime> p_33018_, ServerLevelAccessor p_33019_, EntitySpawnReason p_33020_, BlockPos p_33021_, RandomSource p_33022_) {
		return p_33019_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_33019_, p_33021_, p_33022_) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
	}
}

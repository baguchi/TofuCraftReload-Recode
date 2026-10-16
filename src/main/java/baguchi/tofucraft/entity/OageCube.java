package baguchi.tofucraft.entity;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.BooleanSupplier;

public class OageCube extends AbstractCubeMob implements Enemy {
	public OageCube(EntityType<? extends OageCube> p_32968_, Level p_32969_) {
		super(p_32968_, p_32969_);
	}

	@Override
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(2, new AbstractCubeMob.CubeMobAttackGoal(this));
	}

	@Override
	protected void addTargetingGoals() {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, (target, level) -> Math.abs(target.getY() - this.getY()) <= (double) 4.0F));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
	}

	public static boolean checkOageSpawnRules(EntityType<? extends OageCube> p_219014_, ServerLevelAccessor p_219015_, EntitySpawnReason p_361180_, BlockPos p_219017_, RandomSource p_219018_) {
		return p_219015_.getDifficulty() != Difficulty.PEACEFUL && (p_219015_.getBlockState(p_219017_.below()).is(TofuBlocks.MABOU_TERRAIN) || EntitySpawnReason.ignoresLightRequirements(p_361180_) || isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_)) && checkMobSpawnRules(p_219014_, p_219015_, p_361180_, p_219017_, p_219018_);
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, RandomSource p_33011_) {
		if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
			return false;
		} else {
			int var3 = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
			return var3 <= p_33011_.nextInt(8);
		}
	}

	@Override
	public void setSize(int size, boolean updateHealth) {
		super.setSize(size, updateHealth);
		int actualSize = (Integer) this.entityData.get(ID_SIZE);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double) actualSize);
		this.getAttribute(Attributes.ARMOR).setBaseValue((double) (size * 2));
		this.xpReward = actualSize;
	}

	@Override
	protected float getAttackDamage() {
		return super.getAttackDamage() + 1.0F;
	}

	@Override
	protected ParticleOptions getParticleType() {
		return new ItemParticleOption(ParticleTypes.ITEM, TofuItems.OAGE.get());
	}

	@Override
	protected int getJumpDelay() {
		return this.random.nextInt(20) + 30;
	}

	@Override
	protected void decreaseSquish() {
		this.targetSquish *= 0.85F;
	}

	@Override
	public boolean isOnFire() {
		return false;
	}


	private void jumpInLiquidInternal(BooleanSupplier isLava, Runnable onSuper) {
		if (isLava.getAsBoolean()) {
			Vec3 vec3 = this.getDeltaMovement();
			this.setDeltaMovement(vec3.x, (double) (0.22F + (float) this.getSize() * 0.05F), vec3.z);
			this.needsSync = true;
		} else {
			onSuper.run();
		}

	}

	@Override
	public void jumpInFluid(FluidType type) {
		this.jumpInLiquidInternal(() -> type == TofuFluidTypes.DOUBANJIANG.get(), () -> super.jumpInFluid(type));
	}

	@Override
	public void jumpFromGround() {
		Vec3 movement = this.getDeltaMovement();
		float sizeJumpBoostPower = (float) this.getSize() * 0.1F;
		this.setDeltaMovement(movement.x, (double) (this.getJumpPower() + sizeJumpBoostPower), movement.z);
		this.needsSync = true;
		CommonHooks.onLivingJump(this);
	}

	@Override
	protected boolean canDealDamage() {
		return this.isEffectiveAi();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isTiny() ? SoundEvents.MAGMA_CUBE_HURT_SMALL : SoundEvents.MAGMA_CUBE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isTiny() ? SoundEvents.MAGMA_CUBE_DEATH_SMALL : SoundEvents.MAGMA_CUBE_DEATH;
	}

	@Override
	protected SoundEvent getSquishSound() {
		return this.isTiny() ? SoundEvents.MAGMA_CUBE_SQUISH_SMALL : SoundEvents.MAGMA_CUBE_SQUISH;
	}

	@Override
	protected SoundEvent getJumpSound() {
		return SoundEvents.MAGMA_CUBE_JUMP;
	}

	@Override
	protected boolean canBeABaby() {
		return false;
	}

}

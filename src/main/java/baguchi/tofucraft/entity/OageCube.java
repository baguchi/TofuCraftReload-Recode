package baguchi.tofucraft.entity;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.BooleanSupplier;

public class OageCube extends MagmaCube {
	public OageCube(EntityType<? extends OageCube> p_32968_, Level p_32969_) {
		super(p_32968_, p_32969_);
	}


	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new TofuSlime.TofuSlimeFloatGoal(this));
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
	public void setSize(int p_32972_, boolean p_32973_) {
		super.setSize(p_32972_, p_32973_);
		this.getAttribute(Attributes.ARMOR).setBaseValue((double) (p_32972_ * 2));
	}

	@Override
	protected float getAttackDamage() {
		return super.getAttackDamage() + 1.0F;
	}

	@Override
	public float getLightLevelDependentMagicValue() {
		return this.level().hasChunkAt(this.getBlockX(), this.getBlockZ()) ? this.level().getLightLevelDependentMagicValue(BlockPos.containing(this.getX(), this.getEyeY(), this.getZ())) : 0.0F;
	}

	@Override
	protected ParticleOptions getParticleType() {
		return new ItemParticleOption(ParticleTypes.ITEM, TofuItems.OAGE.get().getDefaultInstance());
	}

	@Override
	protected int getJumpDelay() {
		return this.random.nextInt(30) + 30;
	}

	@Override
	protected void decreaseSquish() {
		this.targetSquish *= 0.85F;
	}


	private void jumpInLiquidInternal(BooleanSupplier isLava, Runnable onSuper) {
		if (isLava.getAsBoolean()) {
			Vec3 vec3 = this.getDeltaMovement();
			this.setDeltaMovement(vec3.x, (double) (0.22F + (float) this.getSize() * 0.05F), vec3.z);
			this.hasImpulse = true;
		} else {
			onSuper.run();
		}

	}

	@Override
	public void jumpInFluid(FluidType type) {
		this.jumpInLiquidInternal(() -> type == TofuFluidTypes.DOUBANJIANG.get(), () -> super.jumpInFluid(type));
	}
}

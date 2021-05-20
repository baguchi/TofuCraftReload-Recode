package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TofuSlimeEntity extends SlimeEntity {
	protected int timeInZundaPlace = 0;
	protected int timeInHotPlace = 0;


	public TofuSlimeEntity(EntityType<? extends TofuSlimeEntity> p_i48552_1_, World p_i48552_2_) {
		super(p_i48552_1_, p_i48552_2_);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	protected void customServerAiStep() {
		super.customServerAiStep();
		if (this.isWeakFromZunda()) {
			++this.timeInZundaPlace;
		} else {
			this.timeInZundaPlace = 0;
		}

		if (this.isWeakFromUltraWarm()) {
			++this.timeInHotPlace;
		} else {
			this.timeInHotPlace = 0;
		}

		if (this.timeInZundaPlace > 300) {
			if (this.getDeathSound() != null) {
				this.playSound(this.getDeathSound(), 1.0F, 1.0F);
				this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 1.0F, 1.0F);
			}
			this.finishConversionFromZunda((ServerWorld) this.level);
		}

		if (this.timeInHotPlace > 300 && this.tickCount % 40 == 0) {
			this.hurt(DamageSource.STARVE, 2.0F);
		}
	}

	public boolean isConverting() {
		return (this.isWeakFromUltraWarm() || this.isWeakFromZunda()) && !this.isNoAi();
	}

	public boolean isWeakFromZunda() {
		return this.level.getBiomeName(this.blockPosition()).isPresent() && this.level.getBiomeName(this.blockPosition()).get().equals(TofuBiomes.ZUNDA_TOFU_FUNGI_FOREST);
	}

	public boolean isWeakFromUltraWarm() {
		return this.level.dimensionType().ultraWarm();
	}

	protected void finishConversionFromZunda(ServerWorld p_234416_1_) {
		this.spawnAtLocation(new ItemStack(TofuItems.TOFUZUNDA, this.getSize() * 2));
		this.remove();
	}

	public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		super.addAdditionalSaveData(p_213281_1_);

		p_213281_1_.putInt("TimeInZundaPlace", this.timeInZundaPlace);
		p_213281_1_.putInt("TimeInHotPlace", this.timeInHotPlace);
	}

	public double getMyRidingOffset() {
		return this.isBaby() ? -0.05D : -0.45D;
	}

	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		this.timeInZundaPlace = p_70037_1_.getInt("TimeInZundaPlace");
		this.timeInHotPlace = p_70037_1_.getInt("TimeInHotPlace");
	}

	public static boolean checkTofuSlimeSpawnRules(EntityType<? extends TofuSlimeEntity> p_223366_0_, IServerWorld p_223366_1_, SpawnReason p_223366_2_, BlockPos p_223366_3_, Random p_223366_4_) {
		if (p_223366_1_.getDifficulty() != Difficulty.PEACEFUL) {
			if (!(p_223366_1_ instanceof net.minecraft.world.ISeedReader))
				return false;
			if (p_223366_4_.nextInt(10) == 0)
				return isDarkEnoughToSpawn(p_223366_1_, p_223366_3_, p_223366_4_);
		}
		return false;
	}

	public static boolean isDarkEnoughToSpawn(IServerWorld p_223323_0_, BlockPos p_223323_1_, Random p_223323_2_) {
		if (p_223323_0_.getBrightness(LightType.SKY, p_223323_1_) > p_223323_2_.nextInt(32)) {
			return false;
		} else {
			int i = p_223323_0_.getLevel().isThundering() ? p_223323_0_.getMaxLocalRawBrightness(p_223323_1_, 10) : p_223323_0_.getMaxLocalRawBrightness(p_223323_1_);
			return i <= p_223323_2_.nextInt(8);
		}
	}


	@Override
	protected IParticleData getParticleType() {
		return new ItemParticleData(ParticleTypes.ITEM, new ItemStack(TofuItems.TOFUKINU));
	}
}

package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.projectile.FallingTofuEntity;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class TofuCreeper extends Creeper {
	public TofuCreeper(EntityType<? extends Creeper> p_32278_, Level p_32279_) {
		super(p_32278_, p_32279_);
	}

	public void explodeCreeper() {
		if (!this.level.isClientSide) {
			float f = this.isPowered() ? 1.8F : 0.8F;
			this.dead = true;
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, Explosion.BlockInteraction.NONE);
			this.discard();
			this.spawnTofu();
			this.spawnLingeringCloud();
		}
	}

	private void spawnTofu() {
		float f = this.isPowered() ? 0.8F : 0.5F;
		if (level instanceof ServerLevel serverLevel) {
			for (int i = 0; i < 6; i++) {
				FallingTofuEntity fallingBlock = new FallingTofuEntity(serverLevel, this, TofuBlocks.KINUTOFU.get().defaultBlockState());
				fallingBlock.setDeltaMovement((this.random.nextFloat() - this.random.nextFloat()) * f, this.random.nextFloat() * 0.5F + f, (this.random.nextFloat() - this.random.nextFloat()) * f);
				serverLevel.addFreshEntityWithPassengers(fallingBlock);
			}
		}
	}

	private void spawnLingeringCloud() {
		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
			areaeffectcloud.setRadius(2.5F);
			areaeffectcloud.setRadiusOnUse(-0.5F);
			areaeffectcloud.setWaitTime(10);
			areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
			areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float) areaeffectcloud.getDuration());

			for (MobEffectInstance mobeffectinstance : collection) {
				areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
			}

			this.level.addFreshEntity(areaeffectcloud);
		}

	}
}

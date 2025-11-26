package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class NattoBallEntity extends ThrowableProjectile {
	protected static final EntityDataAccessor<Boolean> DATA_SMALL = SynchedEntityData.defineId(NattoBallEntity.class, EntityDataSerializers.BOOLEAN);

	public NattoBallEntity(EntityType<? extends NattoBallEntity> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);
	}

	public NattoBallEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.NATTO_BALL.get(), throwerIn, worldIn);
	}

	protected boolean canHitEntity(Entity p_37250_) {
		return false;
	}

	protected void onHit(HitResult p_36913_) {
		super.onHit(p_36913_);
		if (p_36913_.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult) p_36913_).getEntity())) {
			if (!this.level().isClientSide) {
				List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
				AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
				Entity entity = this.getOwner();
				if (entity instanceof LivingEntity) {
					areaeffectcloud.setOwner((LivingEntity) entity);
				}

				areaeffectcloud.setParticle(TofuParticleTypes.SIMPLE_STINKE.get());
				areaeffectcloud.setRadius(this.isSmall() ? 1.25F : 3.0F);
				areaeffectcloud.setDuration(this.isSmall() ? 80 : 200);
				areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
				if (!list.isEmpty()) {
					for (LivingEntity livingentity : list) {
						double d0 = this.distanceToSqr(livingentity);
						if (d0 < 16.0D) {
							areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
							break;
						}
					}
				}

				this.level().addFreshEntity(areaeffectcloud);
				this.discard();
			}

		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_SMALL, false);
	}

	public boolean isSmall() {
		return this.entityData.get(DATA_SMALL);
	}

	public void setSmall(boolean p_149789_) {
		this.entityData.set(DATA_SMALL, p_149789_);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_33432_) {
		super.readAdditionalSaveData(p_33432_);
		this.setSmall(p_33432_.getBoolean("Small"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_33443_) {
		super.addAdditionalSaveData(p_33443_);
		p_33443_.putBoolean("Small", this.isSmall());
	}

	@Override
	public boolean isPickable() {
		return false;
	}
}

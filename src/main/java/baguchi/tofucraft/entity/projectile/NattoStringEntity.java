package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.entity.effect.NattoCobWebEntity;
import baguchi.tofucraft.registry.TofuEntityTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class NattoStringEntity extends ThrowableProjectile {
	protected static final EntityDataAccessor<Boolean> DATA_SMALL = SynchedEntityData.defineId(NattoStringEntity.class, EntityDataSerializers.BOOLEAN);

	public NattoStringEntity(EntityType<? extends ThrowableProjectile> p_37466_, Level p_37467_) {
		super(p_37466_, p_37467_);
	}

	public NattoStringEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.NATTO_STRNIG.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_SMALL, false);
	}

	@Override
	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		playSound(SoundEvents.SLIME_JUMP_SMALL, 0.8F, 0.8F + this.level().getRandom().nextFloat() * 0.4F);

		if (!this.level().isClientSide()) {
			NattoCobWebEntity entity = new NattoCobWebEntity(this.level(), getX(), getY(), getZ());
			this.level().addFreshEntity(entity);
			this.discard();
		}
	}

	@Override
	protected boolean canHitEntity(Entity p_37250_) {
		return false;
	}

	@Override
	protected void onHitBlock(BlockHitResult p_37406_) {
		super.onHitBlock(p_37406_);
		playSound(SoundEvents.SLIME_JUMP_SMALL, 0.8F, 0.8F + this.level().getRandom().nextFloat() * 0.4F);
		if (!this.level().isClientSide()) {
			//I don't use it particularly, but it's something.↓
			this.level().broadcastEntityEvent(this, (byte) 80);
			NattoCobWebEntity entity = new NattoCobWebEntity(this.level(), p_37406_.getLocation().x, p_37406_.getLocation().y, p_37406_.getLocation().z);
			entity.setAttachFace(p_37406_.getDirection());
			entity.setSmall(this.isSmall());
			this.level().addFreshEntity(entity);
			this.discard();
		}
	}


	public boolean isSmall() {
		return this.entityData.get(DATA_SMALL);
	}

	public void setSmall(boolean p_149789_) {
		this.entityData.set(DATA_SMALL, p_149789_);
	}


	@Override
	public void addAdditionalSaveData(ValueOutput p_37222_) {
		super.addAdditionalSaveData(p_37222_);
		p_37222_.putBoolean("Small", this.isSmall());
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		this.setSmall(p_37220_.getBooleanOr("Small", false));
	}
}

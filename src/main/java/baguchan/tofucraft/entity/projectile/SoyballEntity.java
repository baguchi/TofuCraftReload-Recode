package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuDamageSource;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class SoyballEntity extends ThrowableProjectile {
	public float damage = 2;
	@Nullable
	protected ItemStack firedFromWeapon = null;

	public SoyballEntity(EntityType<? extends SoyballEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public SoyballEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.SOYBALL.get(), throwerIn, worldIn);
	}

	public SoyballEntity(Level worldIn, LivingEntity throwerIn, ItemStack stack) {
		super(TofuEntityTypes.SOYBALL.get(), throwerIn, worldIn);
		this.firedFromWeapon = stack.copy();
	}

	public SoyballEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.SOYBALL.get(), x, y, z, worldIn);

	}

	public SoyballEntity(EntityType<? extends SoyballEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	public SoyballEntity(EntityType<? extends SoyballEntity> entityType, LivingEntity throwerIn, Level worldIn) {
		super(entityType, throwerIn, worldIn);
	}

	@Override
	protected void defineSynchedData() {

	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level().addParticle(TofuParticleTypes.SOYMILK_SPLASH.get(), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		DamageSource damagesource = this.damageSources().source(TofuDamageSource.SOY_SPLASH, this, this.getOwner());
		double d0 = this.damage;
		if (this.getOwner() != null && this.getOwner().isAlliedTo(entity)) {
			return;
		}
		if (entity.hurt(damagesource, (float) d0) && this.level() instanceof ServerLevel serverlevel) {
			if (!this.level().isClientSide) {
				this.level().broadcastEntityEvent(this, (byte) 3);
				this.discard();
			}
		}
	}

	protected void onHit(HitResult p_37406_) {
		super.onHit(p_37406_);
		playSound(SoundEvents.GENERIC_SPLASH, 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	public void addAdditionalSaveData(CompoundTag p_37222_) {
		super.addAdditionalSaveData(p_37222_);
		p_37222_.putFloat("Damage", (byte) this.damage);
	}

	public void readAdditionalSaveData(CompoundTag p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		if (p_37220_.contains("Damage", 99)) {
			this.damage = p_37220_.getFloat("Damage");
		}
	}
}
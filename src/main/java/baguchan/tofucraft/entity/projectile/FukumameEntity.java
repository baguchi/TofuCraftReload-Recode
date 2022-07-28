package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class FukumameEntity extends ThrowableProjectile {
	public float damage = 1;

	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public FukumameEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.FUKUMAME.get(), throwerIn, worldIn);
	}

	public FukumameEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.FUKUMAME.get(), x, y, z, worldIn);
	}

	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	protected void defineSynchedData() {
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(TofuItems.SEEDS_SOYBEANS.get())), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		int i = 1;
		entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) this.damage);
		entity.invulnerableTime = 5;
	}

	protected void onHit(HitResult p_37406_) {
		super.onHit(p_37406_);
		playSound(TofuSounds.SOYBEAN_CRACK.get(), 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F);
		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte) 3);
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

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
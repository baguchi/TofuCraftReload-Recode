package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class FukumameEntity extends ThrowableEntity {
	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, World p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public FukumameEntity(World worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.FUKUMAME, throwerIn, worldIn);
	}

	public FukumameEntity(World worldIn, double x, double y, double z) {
		super(TofuEntityTypes.FUKUMAME, x, y, z, worldIn);
	}

	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, World worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	protected void defineSynchedData() {
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level.addParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(TofuItems.SEEDS_SOYBEANS)), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}


	protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
		super.onHitEntity(p_213868_1_);
		p_213868_1_.getEntity().hurt(DamageSource.thrown(this, getOwner()), 1.0F);
		p_213868_1_.getEntity().invulnerableTime = 5;
	}

	protected void onHit(RayTraceResult result) {
		super.onHit(result);
		playSound(TofuSounds.SOYBEAN_CRACK, 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F);
		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte) 3);
			remove();
		}
	}

	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

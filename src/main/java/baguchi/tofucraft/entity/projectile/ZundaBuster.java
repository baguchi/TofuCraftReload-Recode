package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ZundaBuster extends ThrowableProjectile {
	public float damage = 10;
	@Nullable
	protected ItemStack firedFromWeapon = null;

	public ZundaBuster(EntityType<? extends ZundaBuster> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public ZundaBuster(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.ZUNDA_BUSTER.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}

	public ZundaBuster(Level worldIn, LivingEntity throwerIn, ItemStack stack) {
		super(TofuEntityTypes.ZUNDA_BUSTER.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
		this.firedFromWeapon = stack.copy();
	}

	public ZundaBuster(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.ZUNDA_BUSTER.get(), x, y, z, worldIn);

	}

	public ZundaBuster(EntityType<? extends ZundaBuster> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	public ZundaBuster(EntityType<? extends ZundaBuster> entityType, LivingEntity throwerIn, Level worldIn) {
		super(entityType, throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}


	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			this.level().addParticle(TofuParticleTypes.ZUNDA_EXPLOSION.get(), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		DamageSource damagesource = this.damageSources().source(TofuDamageTypes.ZUNDA, this, this.getOwner());
		double d0 = this.damage;
		if (this.level() instanceof ServerLevel serverlevel) {
			if (this.getWeaponItem() != null) {
				d0 = (double) EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, damagesource, (float) d0);
			}
			if (entity.hurtServer(serverlevel, damagesource, (float) d0)) {
				EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
			}
		}
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	protected void hitBlockEnchantmentEffects(ServerLevel p_345462_, BlockHitResult p_345204_, ItemStack p_345083_) {
		Vec3 vec3 = p_345204_.getBlockPos().clampLocationWithin(p_345204_.getLocation());
		EnchantmentHelper.onHitBlock(
				p_345462_,
				p_345083_,
				this.getOwner() instanceof LivingEntity livingentity ? livingentity : null,
				this,
				null,
				vec3,
				p_345462_.getBlockState(p_345204_.getBlockPos()),
				p_348569_ -> this.firedFromWeapon = null
		);
	}

	@Override
	public ItemStack getWeaponItem() {
		return this.firedFromWeapon;
	}

	protected void onHit(HitResult p_37406_) {
		super.onHit(p_37406_);
		playSound(SoundEvents.GENERIC_EXPLODE.value(), 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);

		ItemStack itemstack = this.getWeaponItem();
		if (this.level() instanceof ServerLevel serverlevel && itemstack != null) {
			this.hitBlockEnchantmentEffects(serverlevel, result, itemstack);
		}
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	public void addAdditionalSaveData(ValueOutput p_37222_) {
		super.addAdditionalSaveData(p_37222_);
		p_37222_.putFloat("Damage", (byte) this.damage);
		if (this.firedFromWeapon != null) {
			p_37222_.store("weapon", ItemStack.CODEC, this.firedFromWeapon);
		}
	}

	public void readAdditionalSaveData(ValueInput p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		this.damage = p_37220_.getFloatOr("Damage", 10);
		this.firedFromWeapon = p_37220_.read("weapon", ItemStack.CODEC).orElse(null);

	}
}
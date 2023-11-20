package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.compat.CompatHandler;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Optional;

public class FukumameEntity extends ThrowableProjectile {
	public float damage = 1;
	private int totalHits;
	private static final EntityDataAccessor<Integer> BOUNCE_LEVEL = SynchedEntityData.defineId(FukumameEntity.class, EntityDataSerializers.INT);


	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public FukumameEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.FUKUMAME.get(), throwerIn, worldIn);
	}

	public FukumameEntity(Level worldIn, LivingEntity throwerIn, ItemStack stack) {
		super(TofuEntityTypes.FUKUMAME.get(), throwerIn, worldIn);
		Optional<Holder.Reference<Enchantment>> resourceKey = BuiltInRegistries.ENCHANTMENT.getHolder(CompatHandler.HUNTERILLAGER_BOUNCE);
		if (resourceKey.isPresent()) {
			this.entityData.set(BOUNCE_LEVEL, EnchantmentHelper.getItemEnchantmentLevel(resourceKey.get().value(), stack));
		}
	}

	public FukumameEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.FUKUMAME.get(), x, y, z, worldIn);

	}

	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	public FukumameEntity(EntityType<? extends FukumameEntity> entityType, LivingEntity throwerIn, Level worldIn) {
		super(entityType, throwerIn, worldIn);
	}

	protected void defineSynchedData() {
		this.entityData.define(BOUNCE_LEVEL, 0);
	}

	public void setBounceLevel(int bounceLevel) {
		this.entityData.set(BOUNCE_LEVEL, bounceLevel);
	}

	public int getBounceLevel() {
		return this.entityData.get(BOUNCE_LEVEL);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(TofuItems.SEEDS_SOYBEANS.get())), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		int i = 2;
		entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float) this.damage);
		entity.invulnerableTime = 5;
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	protected void onHit(HitResult p_37406_) {
		super.onHit(p_37406_);
		playSound(TofuSounds.SOYBEAN_CRACK.get(), 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		BlockPos pos = result.getBlockPos();
		BlockState state = this.level().getBlockState(pos);

		BlockState blockstate = this.level().getBlockState(result.getBlockPos());
		if (!blockstate.getCollisionShape(this.level(), result.getBlockPos()).isEmpty()) {
			Direction face = result.getDirection();
			Vec3 motion = getDeltaMovement();
			double motionX = motion.x;
			double motionY = motion.y;
			double motionZ = motion.z;
			if (face == Direction.EAST) {
				motionX = -motionX;
			} else if (face == Direction.SOUTH) {
				motionZ = -motionZ;
			} else if (face == Direction.WEST) {
				motionX = -motionX;
			} else if (face == Direction.NORTH) {
				motionZ = -motionZ;
			} else if (face == Direction.UP) {
				motionY = -motionY;
			} else if (face == Direction.DOWN) {
				motionY = -motionY;
			}
			setDeltaMovement(motionX, motionY, motionZ);
		}

		if (this.totalHits >= this.getBounceLevel()) {
			if (!this.level().isClientSide) {
				this.level().broadcastEntityEvent(this, (byte) 3);
				this.discard();
			}
		} else {
			this.totalHits++;
		}
	}

	public void addAdditionalSaveData(CompoundTag p_37222_) {
		super.addAdditionalSaveData(p_37222_);
		p_37222_.putFloat("Damage", (byte) this.damage);
		p_37222_.putInt("TotalHits", this.totalHits);
		p_37222_.putInt("BounceLevel", this.getBounceLevel());
	}

	public void readAdditionalSaveData(CompoundTag p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		if (p_37220_.contains("Damage", 99)) {
			this.damage = p_37220_.getFloat("Damage");
		}
		this.totalHits = p_37220_.getInt("TotalHits");
		this.setBounceLevel(p_37220_.getInt("BounceLevel"));
	}
}
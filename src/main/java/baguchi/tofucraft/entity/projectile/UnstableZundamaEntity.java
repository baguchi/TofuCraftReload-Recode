package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class UnstableZundamaEntity extends ThrowableItemProjectile {
	private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
			true, true, Optional.of(1.06F), Optional.empty()
	) {
		@Override
		public boolean shouldDamageEntity(Explosion p_346248_, Entity p_344983_) {
			return p_344983_.getType().is(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA);
		}

		@Override
		public boolean shouldBlockExplode(Explosion p_345994_, BlockGetter p_345042_, BlockPos p_345057_, BlockState p_345932_, float p_345776_) {
			return false;
		}
	};

	public UnstableZundamaEntity(EntityType<? extends UnstableZundamaEntity> p_37466_, Level p_37467_) {
		super(p_37466_, p_37467_);
	}

	public UnstableZundamaEntity(Level p_37476_, LivingEntity p_363925_, ItemStack p_363058_) {
		super(TofuEntityTypes.UNSTABLE_ZUNDAMA.get(), p_363925_, p_37476_, p_363058_);
	}

	public UnstableZundamaEntity(Level p_37481_, double p_362518_, double p_363859_, double p_364605_, ItemStack p_362063_) {
		super(TofuEntityTypes.UNSTABLE_ZUNDAMA.get(), p_362518_, p_363859_, p_364605_, p_37481_, p_362063_);
	}

	@Override
	protected Item getDefaultItem() {
		return TofuItems.UNSTABLE_ZUNDAMA.get();
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		if (!this.level().isClientSide) {
			Vec3 vec31 = this.position();
			this.level()
					.explode(
							this,
							this.damageSources().source(TofuDamageTypes.ZUNDA, this, this.getOwner()),
							EXPLOSION_DAMAGE_CALCULATOR,
							vec31.x(),
							vec31.y(),
							vec31.z(),
							3F,
							false,
							Level.ExplosionInteraction.MOB,
							TofuParticleTypes.ZUNDA_EXPLOSION.get(),
							TofuParticleTypes.ZUNDA_EMIT.get(),
							SoundEvents.GENERIC_EXPLODE
					);
			this.discard();
		}
	}

	protected void onHitBlock(BlockHitResult p_37406_) {
		super.onHitBlock(p_37406_);
		if (!this.level().isClientSide) {
			Vec3i vec3i = p_37406_.getDirection().getUnitVec3i();
			Vec3 vec3 = Vec3.atLowerCornerOf(vec3i).multiply((double) 0.25F, (double) 0.25F, (double) 0.25F);
			Vec3 vec31 = p_37406_.getLocation().add(vec3);
			this.level()
					.explode(
							this,
							this.damageSources().source(TofuDamageTypes.ZUNDA, this, this.getOwner()),
							EXPLOSION_DAMAGE_CALCULATOR,
							vec31.x(),
							vec31.y(),
							vec31.z(),
							3F,
							false,
							Level.ExplosionInteraction.MOB,
							TofuParticleTypes.ZUNDA_EXPLOSION.get(),
							TofuParticleTypes.ZUNDA_EMIT.get(),
							SoundEvents.GENERIC_EXPLODE
					);
			this.discard();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_37222_) {
		super.addAdditionalSaveData(p_37222_);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_37220_) {
		super.readAdditionalSaveData(p_37220_);
	}
}

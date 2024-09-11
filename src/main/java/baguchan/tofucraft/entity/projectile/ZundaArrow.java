package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.client.particle.ParticleZundaCloud;
import baguchan.tofucraft.entity.TofuSlime;
import baguchan.tofucraft.registry.TofuDamageSource;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

public class ZundaArrow extends AbstractArrow {
	private int duration = 100;

	public ZundaArrow(EntityType<? extends ZundaArrow> p_37411_, Level p_37412_) {
		super(p_37411_, p_37412_);
	}

	public ZundaArrow(Level p_37419_, LivingEntity p_37420_) {
		super(TofuEntityTypes.ZUNDA_ARROW.get(), p_37420_, p_37419_);
	}

	public ZundaArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
		super(TofuEntityTypes.ZUNDA_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
	}

	public void tick() {
		super.tick();
		if (this.level().isClientSide && !this.inGround) {
			this.level().addParticle(new ParticleZundaCloud.CloudData(TofuParticleTypes.ZUNDA_CLOUD.get(), 5f, 20, ParticleZundaCloud.EnumCloudBehavior.GROW, 0.98f), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
		}

	}

	protected ItemStack getPickupItem() {
		return new ItemStack(TofuItems.ZUNDA_ARROW.get());
	}

	public DamageSource zundaAttack(@Nullable Entity p_270857_) {
		return this.damageSources().source(TofuDamageSource.ZUNDA, this, p_270857_);
	}

	@Override
	protected void onHitEntity(EntityHitResult p_36757_) {
		if (!this.level().isClientSide()) {
			float f = (float) this.getDeltaMovement().length();
			int i = Mth.ceil(Mth.clamp((double) f * this.getBaseDamage(), 0.0D, 2.147483647E9D));

			if (this.isCritArrow()) {
				long j = this.random.nextInt(i / 2 + 2);
				i = (int) Math.min(j + (long) i, 2147483647L);
			}

			if (p_36757_.getEntity() instanceof TofuSlime slime) {
				this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 1.0F, 1.0F);
				this.spawnAtLocation(new ItemStack(TofuItems.TOFUZUNDA.get(), slime.getSize() * 2));
				p_36757_.getEntity().discard();
				this.discard();
			} else if (p_36757_.getEntity() instanceof Mob && ((Mob) p_36757_.getEntity()).getMobType() == MobType.UNDEAD || p_36757_.getEntity().getType().is(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA)) {

				if (p_36757_.getEntity().hurt(zundaAttack(this.getOwner()), i)) {
					this.discard();
				} else {
					this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
					this.setYRot(this.getYRot() + 180.0F);
					this.yRotO += 180.0F;
					if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
						if (this.pickup == AbstractArrow.Pickup.ALLOWED) {
							this.spawnAtLocation(this.getPickupItem(), 0.1F);
						}

						this.discard();
					}
				}
			} else {
				if (p_36757_.getEntity() instanceof LivingEntity) {
					MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.REGENERATION, this.duration * i, 0);
					((LivingEntity) p_36757_.getEntity()).addEffect(mobeffectinstance, this.getEffectSource());
					this.discard();
				}
			}
		}
	}

	public void readAdditionalSaveData(CompoundTag p_37424_) {
		super.readAdditionalSaveData(p_37424_);
		if (p_37424_.contains("Duration")) {
			this.duration = p_37424_.getInt("Duration");
		}

	}

	public void addAdditionalSaveData(CompoundTag p_37426_) {
		super.addAdditionalSaveData(p_37426_);
		p_37426_.putInt("Duration", this.duration);
	}
}
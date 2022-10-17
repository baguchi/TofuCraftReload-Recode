package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ZundaArrow extends AbstractArrow {
	private int duration = 200;

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
		if (this.level.isClientSide && !this.inGround) {
			this.level.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
		}

	}

	protected ItemStack getPickupItem() {
		return new ItemStack(TofuItems.ZUNDA_ARROW.get());
	}


	@Override
	protected void onHitEntity(EntityHitResult p_36757_) {
		if (!this.level.isClientSide()) {
			if (p_36757_.getEntity() instanceof LivingEntity) {
				MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.REGENERATION, this.duration, 0);
				((LivingEntity) p_36757_.getEntity()).addEffect(mobeffectinstance, this.getEffectSource());
				this.discard();
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
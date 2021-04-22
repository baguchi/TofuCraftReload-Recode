package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ZundaArrowEntity extends AbstractArrowEntity {
	private int duration = 200;

	public ZundaArrowEntity(EntityType<? extends ZundaArrowEntity> p_i50158_1_, World p_i50158_2_) {
		super(p_i50158_1_, p_i50158_2_);
	}

	public ZundaArrowEntity(World p_i46768_1_, LivingEntity p_i46768_2_) {
		super(TofuEntityTypes.ZUNDA_ARROW, p_i46768_2_, p_i46768_1_);
	}

	public ZundaArrowEntity(World p_i46769_1_, double p_i46769_2_, double p_i46769_4_, double p_i46769_6_) {
		super(TofuEntityTypes.ZUNDA_ARROW, p_i46769_2_, p_i46769_4_, p_i46769_6_, p_i46769_1_);
	}

	public void tick() {
		super.tick();
		if (this.level.isClientSide && !this.field_70254_i)
			this.level.func_195594_a((IParticleData) ParticleTypes.field_197590_A, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
	}

	protected ItemStack func_184550_j() {
		return new ItemStack(TofuItems.ZUNDA_ARROW);
	}

	protected void func_213868_a(EntityRayTraceResult p_213868_1_) {
		DamageSource damagesource;
		Entity entity = p_213868_1_.func_216348_a();
		Entity entity1 = func_234616_v_();
		float f = (float) func_213322_ci().func_72433_c();
		int i = MathHelper.func_76143_f(MathHelper.func_151237_a(f * func_70242_d(), 0.0D, 2.147483647E9D));
		if (entity1 == null) {
			damagesource = DamageSource.func_76354_b((Entity) this, (Entity) this);
		} else {
			damagesource = DamageSource.func_76354_b((Entity) this, entity1);
			if (entity1 instanceof LivingEntity)
				((LivingEntity) entity1).func_130011_c(entity);
		}
		if (func_70241_g()) {
			long j = this.random.nextInt(i / 2 + 2);
			i = (int) Math.min(j + i, 2147483647L);
		}
		if (entity instanceof CreatureEntity && ((CreatureEntity) entity).func_70668_bt() == CreatureAttribute.field_223223_b_) {
			if (entity.func_70097_a(damagesource, i)) {
				LivingEntity livingentity = (LivingEntity) entity;
				if (!this.level.isClientSide && entity1 instanceof LivingEntity) {
					EnchantmentHelper.func_151384_a(livingentity, entity1);
					EnchantmentHelper.func_151385_b((LivingEntity) entity1, (Entity) livingentity);
				}
				playSound(SoundEvents.field_187731_t, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
				if (func_213874_s() <= 0)
					func_70106_y();
			} else {
				func_213317_d(func_213322_ci().func_186678_a(-0.1D));
				this.yRot += 180.0F;
				this.field_70126_B += 180.0F;
				if (!this.level.isClientSide && func_213322_ci().func_189985_c() < 1.0E-7D) {
					if (this.field_70251_a == PickupStatus.ALLOWED)
						func_70099_a(func_184550_j(), 0.1F);
					func_70106_y();
				}
			}
		} else if (entity instanceof LivingEntity) {
			LivingEntity livingentity = (LivingEntity) entity;
			func_184548_a(livingentity);
			playSound(SoundEvents.field_187731_t, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
		}
		func_70106_y();
	}

	protected void func_184548_a(LivingEntity p_184548_1_) {
		super.func_184548_a(p_184548_1_);
		if (!this.level.isClientSide() &&
				p_184548_1_.func_200600_R() == TofuEntityTypes.TOFUSLIME) {
			ItemEntity itemEntity = new ItemEntity(this.level, getX(), getY(), getZ(), new ItemStack(TofuItems.TOFUZUNDA, 2));
			this.level.addFreshEntity(itemEntity);
			p_184548_1_.func_70106_y();
		}
		EffectInstance effectinstance = new EffectInstance(Effects.field_76428_l, this.duration, 0);
		p_184548_1_.addEffect(effectinstance);
	}

	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		if (p_70037_1_.contains("Duration"))
			this.duration = p_70037_1_.getInt("Duration");
	}

	public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		super.addAdditionalSaveData(p_213281_1_);
		p_213281_1_.putInt("Duration", this.duration);
	}

	public IPacket<?> func_213297_N() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

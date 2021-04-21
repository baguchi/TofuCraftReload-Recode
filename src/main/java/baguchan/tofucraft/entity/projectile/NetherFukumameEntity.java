package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class NetherFukumameEntity extends ThrowableEntity {
	public NetherFukumameEntity(EntityType<? extends NetherFukumameEntity> p_i50154_1_, World p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public NetherFukumameEntity(World worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.NETHER_FUKUMAME, throwerIn, worldIn);
	}

	public NetherFukumameEntity(World worldIn, double x, double y, double z) {
		super(TofuEntityTypes.NETHER_FUKUMAME, x, y, z, worldIn);
	}

	public NetherFukumameEntity(EntityType<? extends NetherFukumameEntity> p_i50154_1_, World worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	protected void func_70088_a() {
	}

	@OnlyIn(Dist.CLIENT)
	public void func_70103_a(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level.func_195594_a((IParticleData) new ItemParticleData(ParticleTypes.field_197591_B, new ItemStack((IItemProvider) TofuItems.SEEDS_SOYBEANS_NETHER)), func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), (this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, (this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, (this.field_70146_Z.nextFloat() - 0.5D) * 0.08D);
		} else if (id == 4) {
			this.level.func_195594_a((IParticleData) ParticleTypes.field_239814_an_, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), (this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, (this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, (this.field_70146_Z.nextFloat() - 0.5D) * 0.08D);
		}
	}

	public void func_70071_h_() {
		super.func_70071_h_();
		if (!this.level.field_72995_K)
			this.level.func_72960_a((Entity) this, (byte) 4);
	}

	protected void func_213868_a(EntityRayTraceResult p_213868_1_) {
		super.func_213868_a(p_213868_1_);
		p_213868_1_.func_216348_a().func_70097_a(DamageSource.func_76356_a((Entity) this, func_234616_v_()), 1.0F);
		(p_213868_1_.func_216348_a()).field_70172_ad = 5;
		if (!p_213868_1_.func_216348_a().func_230279_az_() && func_70027_ad())
			p_213868_1_.func_216348_a().func_241209_g_(80);
	}

	protected void func_70227_a(RayTraceResult result) {
		super.func_70227_a(result);
		playSound(TofuSounds.SOYBEAN_CRACK, 0.8F, 0.8F + this.level.field_73012_v.nextFloat() * 0.4F);
		if (!this.level.field_72995_K) {
			this.level.func_72960_a((Entity) this, (byte) 3);
			func_70106_y();
		}
	}

	public IPacket<?> func_213297_N() {
		return NetworkHooks.getEntitySpawningPacket((Entity) this);
	}
}

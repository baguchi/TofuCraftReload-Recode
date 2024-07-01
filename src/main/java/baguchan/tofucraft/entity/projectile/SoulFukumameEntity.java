package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SoulFukumameEntity extends FukumameEntity {
	public SoulFukumameEntity(EntityType<? extends SoulFukumameEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public SoulFukumameEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.SOUL_FUKUMAME.get(), throwerIn, worldIn);
	}

	public SoulFukumameEntity(Level worldIn, LivingEntity throwerIn, ItemStack stack) {
		super(TofuEntityTypes.SOUL_FUKUMAME.get(), throwerIn, worldIn);
		this.firedFromWeapon = stack.copy();
	}

	public SoulFukumameEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.SOUL_FUKUMAME.get(), worldIn, x, y, z);
	}

	public SoulFukumameEntity(EntityType<? extends SoulFukumameEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, worldIn, x, y, z);
	}


	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++) {
				this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(TofuItems.SEEDS_SOYBEANS_SOUL.get())), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
			}
		}
		if (id == 4) {
			this.level().addParticle(ParticleTypes.WARPED_SPORE, getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.level().broadcastEntityEvent(this, (byte) 4);
	}

}
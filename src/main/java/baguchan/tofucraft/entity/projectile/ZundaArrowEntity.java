package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
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
		if (this.level.isClientSide && !this.isOnGround()) {

		}
		//this.level.addParticle((IParticleData) ParticleTypes., getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(TofuItems.ZUNDA_ARROW);
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

	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

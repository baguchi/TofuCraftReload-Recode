package baguchan.tofucraft.entity;

import baguchan.tofucraft.network.HurtMultipartPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class ShuDofuSpiderPart extends net.neoforged.neoforge.entity.PartEntity<ShuDofuSpider> {
	public final ShuDofuSpider parentMob;
	public final String name;
	private final EntityDimensions size;

	public ShuDofuSpiderPart(ShuDofuSpider p_31014_, String p_31015_, float p_31016_, float p_31017_) {
		super(p_31014_);
		this.size = EntityDimensions.scalable(p_31016_, p_31017_);
		this.refreshDimensions();
		this.parentMob = p_31014_;
		this.name = p_31015_;
	}

	protected AABB makeBoundingBox() {
		if (this.size != null) {
			return this.size.makeBoundingBox(this.position());
		} else {
			return super.makeBoundingBox();
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_325943_) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_31025_) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_31028_) {
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Nullable
	@Override
	public ItemStack getPickResult() {
		return this.parentMob.getPickResult();
	}

	@Override
	public boolean hurt(DamageSource damageSource, float damage) {
		if (!this.isInvulnerableTo(damageSource)) {
				if (this.level().isClientSide) {
					Entity entity = damageSource.getEntity();
					if (entity != null) {
						if (this == parentMob.body) {
							PacketDistributor.sendToServer(new HurtMultipartPacket(entity.getId(), parentMob.getId(), damage * 1.2F, this.damageSources().damageTypes.getKey(damageSource.type()).toString()));
						} else {
							PacketDistributor.sendToServer(new HurtMultipartPacket(entity.getId(), parentMob.getId(), damage * 0.85F, this.damageSources().damageTypes.getKey(damageSource.type()).toString()));
						}
					}
				}
				return true;

		} else {
			return false;
		}
	}

	@Override
	public boolean is(Entity p_31031_) {
		return this == p_31031_ || this.parentMob == p_31031_;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_31023_) {
		return this.size;
	}

	@Override
	public boolean shouldBeSaved() {
		return false;
	}
}

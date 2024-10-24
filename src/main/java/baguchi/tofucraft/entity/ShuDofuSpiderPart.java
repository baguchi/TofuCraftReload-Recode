package baguchi.tofucraft.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class ShuDofuSpiderPart extends net.neoforged.neoforge.entity.PartEntity<ShuDofuSpider> {
	public final ShuDofuSpider parentMob;
	public final String name;
	private final EntityDimensions size;
	private float appliedScale;

	public ShuDofuSpiderPart(ShuDofuSpider p_31014_, String p_31015_, float p_31016_, float p_31017_) {
		super(p_31014_);
		this.size = EntityDimensions.scalable(p_31016_, p_31017_);
		this.refreshDimensions();
		this.parentMob = p_31014_;
		this.name = p_31015_;
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
	public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float damage) {
			if (this == parentMob.body) {
				return this.parentMob.hurt(this, damageSource, damage * 1.2F);
			} else {
				return this.parentMob.hurt(this, damageSource, damage * 0.8F);
			}

	}


	@Override
	public boolean is(Entity p_31031_) {
		return this == p_31031_ || this.parentMob == p_31031_;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
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

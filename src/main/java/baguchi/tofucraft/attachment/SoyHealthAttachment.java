package baguchi.tofucraft.attachment;

import baguchi.tofucraft.network.SoyMilkDrinkedPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;

public class SoyHealthAttachment implements INBTSerializable<CompoundTag> {
	private int soyHealthLevel;
	private int soyHealthBaseLevel;
	private long lastTick = -12000L;
	private long lastChangedTick = -12000L;

	//when can update is true. update last tick
	public void setSoyHealthLevel(LivingEntity entity, int level, boolean canUpdate) {
		if (canUpdate) {
			this.lastTick = entity.level().getGameTime();
			this.lastChangedTick = entity.level().getGameTime();
			if (entity instanceof Player) {
				((Player) entity).displayClientMessage(Component.translatable("item.tofucraft.soymilk.drink_day", level), true);
			}
		}
		if (!entity.level().isClientSide()) {
			SoyMilkDrinkedPacket message = new SoyMilkDrinkedPacket(entity, level, canUpdate);
			PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, message);
		}
		this.soyHealthLevel = Mth.clamp(level, 0, 20);
	}

	public void setSoyHealthBaseLevel(int level) {
		this.soyHealthBaseLevel = Mth.clamp(level, 0, 100);
	}

	public void removeAllSoyHealth(LivingEntity entity) {
		this.soyHealthLevel = 0;
		if (!entity.level().isClientSide()) {
			SoyMilkDrinkedPacket message = new SoyMilkDrinkedPacket(entity, this.soyHealthLevel, true);
			PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, message);
		}
	}

	public long getRemainTick() {
		return this.lastTick;
	}

	public int getSoyHealthLevel() {
		return this.soyHealthLevel;
	}

	public int getSoyHealthBaseLevel() {
		return soyHealthBaseLevel;
	}

	public void tick(LivingEntity livingEntity) {
		if (!livingEntity.level().isClientSide()) {
			if (livingEntity.level().getGameTime() > this.lastChangedTick + 24000L) {
				if (this.soyHealthLevel > 1) {
					this.setSoyHealthLevel(livingEntity, this.soyHealthLevel - 2, false);
					this.lastChangedTick = livingEntity.level().getGameTime();
				}
			}
		}
	}

	@Override
	public CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag nbt = new CompoundTag();
		nbt.putLong("RemainTick", this.lastTick);
		nbt.putLong("RemainChangedTick", this.lastChangedTick);
		nbt.putInt("SoyHealthLevel", this.soyHealthLevel);
		nbt.putInt("SoyHealthBaseLevel", this.soyHealthBaseLevel);
		return nbt;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
		this.lastTick = nbt.getLong("RemainTick");
		this.lastChangedTick = nbt.getLong("RemainChangedTick");
		this.soyHealthLevel = nbt.getInt("SoyHealthLevel");
		this.soyHealthBaseLevel = nbt.getInt("SoyHealthBaseLevel");
	}
}

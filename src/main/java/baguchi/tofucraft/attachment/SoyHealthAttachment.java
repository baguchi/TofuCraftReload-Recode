package baguchi.tofucraft.attachment;

import baguchi.tofucraft.network.SoyMilkDrinkedPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.network.PacketDistributor;

public class SoyHealthAttachment implements ValueIOSerializable {
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
				((Player) entity).sendOverlayMessage(Component.translatable("item.tofucraft.soymilk_bottle.drink_day", level));
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
	public void serialize(ValueOutput output) {
		output.putLong("RemainTick", this.lastTick);
		output.putLong("RemainChangedTick", this.lastChangedTick);
		output.putInt("SoyHealthLevel", this.soyHealthLevel);
		output.putInt("SoyHealthBaseLevel", this.soyHealthBaseLevel);
	}

	@Override
	public void deserialize(ValueInput nbt) {
		this.lastTick = nbt.getLongOr("RemainTick", 0);
		this.lastChangedTick = nbt.getLongOr("RemainChangedTick", 0);
		this.soyHealthLevel = nbt.getIntOr("SoyHealthLevel", 0);
		this.soyHealthBaseLevel = nbt.getIntOr("SoyHealthBaseLevel", 0);
	}
}

package baguchan.tofucraft.capability;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SoyHealthCapability implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
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
			SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(entity, level, canUpdate);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
		}
		this.soyHealthLevel = Mth.clamp(level, 0, 20);
	}

	public void setSoyHealthBaseLevel(int level) {
		this.soyHealthBaseLevel = Mth.clamp(level, 0, 100);
	}

	public void removeAllSoyHealth(LivingEntity entity) {
		this.soyHealthLevel = 0;
		if (!entity.level().isClientSide()) {
			SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(entity, this.soyHealthLevel, true);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
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
				if (this.soyHealthLevel > 0) {
					this.setSoyHealthLevel(livingEntity, this.soyHealthLevel - 2, false);
					this.lastChangedTick = livingEntity.level().getGameTime();
				}
			}
		}
	}

	@Nonnull
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return (capability == TofuCraftReload.SOY_HEALTH_CAPABILITY) ? LazyOptional.of(() -> this).cast() : LazyOptional.empty();
	}

	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putLong("RemainTick", this.lastTick);
		nbt.putLong("RemainChangedTick", this.lastChangedTick);
		nbt.putInt("SoyHealthLevel", this.soyHealthLevel);
		nbt.putInt("SoyHealthBaseLevel", this.soyHealthBaseLevel);
		return nbt;
	}

	public void deserializeNBT(CompoundTag nbt) {
		this.lastTick = nbt.getLong("RemainTick");
		this.lastChangedTick = nbt.getLong("RemainChangedTick");
		this.soyHealthLevel = nbt.getInt("SoyHealthLevel");
		this.soyHealthBaseLevel = nbt.getInt("SoyHealthBaseLevel");
	}
}

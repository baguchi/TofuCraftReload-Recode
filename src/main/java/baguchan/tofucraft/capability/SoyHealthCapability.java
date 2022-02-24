package baguchan.tofucraft.capability;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
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
	private int remainTick;

	private int soyHealthLevel;

	public void setSoyHealth(LivingEntity entity, int level) {
		this.remainTick = 48000;
		this.soyHealthLevel = Mth.clamp(level, 1, 20);
		if (!entity.level.isClientSide()) {
			SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(entity, level);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
			if (entity instanceof Player) {
				((Player) entity).displayClientMessage(new TranslatableComponent("item.tofucraft.soymilk.drink_day", this.soyHealthLevel), true);
			}
		}
	}

	public void removeAllSoyHealth(LivingEntity entity) {
		this.soyHealthLevel = 0;
		if (!entity.level.isClientSide()) {
			SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(entity, this.soyHealthLevel);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
		}
	}

	public int getRemainTick() {
		return this.remainTick;
	}

	public int getSoyHealthLevel() {
		return this.soyHealthLevel;
	}

	public void tick() {
		if (this.remainTick > 0) {
			this.remainTick--;
		} else if (this.remainTick <= 0 && this.soyHealthLevel > 0) {
			this.soyHealthLevel -= 2;
			this.remainTick = 24000;
		}
	}

	@Nonnull
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return (capability == TofuCraftReload.SOY_HEALTH_CAPABILITY) ? LazyOptional.of(() -> this).cast() : LazyOptional.empty();
	}

	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("RemainTick", this.remainTick);
		nbt.putInt("SoyHealthLevel", this.soyHealthLevel);
		return nbt;
	}

	public void deserializeNBT(CompoundTag nbt) {
		this.remainTick = nbt.getInt("RemainTick");
		this.soyHealthLevel = nbt.getInt("SoyHealthLevel");
	}
}

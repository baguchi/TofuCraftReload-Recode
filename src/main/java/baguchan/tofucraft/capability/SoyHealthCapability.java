package baguchan.tofucraft.capability;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class SoyHealthCapability implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {
	private int remainTick;

	private int soyHealthLevel;

	public void setSoyHealth(LivingEntity entity, int level) {
		this.remainTick = 48000;
		this.soyHealthLevel = MathHelper.func_76125_a(level, 0, 20);
		if (!entity.level.isClientSide()) {
			SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(entity, level);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
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

	public CompoundNBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.func_74768_a("RemainTick", this.remainTick);
		nbt.func_74768_a("SoyHealthLevel", this.soyHealthLevel);
		return nbt;
	}

	public void deserializeNBT(CompoundNBT nbt) {
		this.remainTick = nbt.func_74762_e("RemainTick");
		this.soyHealthLevel = nbt.func_74762_e("SoyHealthLevel");
	}
}

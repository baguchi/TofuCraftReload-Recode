package baguchan.tofucraft.capability;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.message.SoyHealthMessage;
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
	private long lastTick = -12000L;

	private float soyHealth;
	private float soyMaxHealth;

	//when can update is true. update last tick
	public void setSoyHealthLevel(LivingEntity entity, int level, boolean canUpdate) {
		if (canUpdate) {
			this.lastTick = entity.level.getDayTime();
			if (entity instanceof Player) {
				((Player) entity).displayClientMessage(Component.translatable("item.tofucraft.soymilk.drink_day", level), true);
			}
		}
		if (!entity.level.isClientSide()) {
			SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(entity, level, canUpdate);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
		}
		this.soyHealthLevel = Mth.clamp(level, 1, 20);
	}

	public void setSoyHealth(LivingEntity entity, float health, float maxHealth) {
		if (!entity.level.isClientSide()) {
			SoyHealthMessage message = new SoyHealthMessage(entity, health, maxHealth);
			TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
		}
		this.soyMaxHealth = Mth.clamp(maxHealth, 0, 20);
		this.soyHealth = Mth.clamp(health, 0, maxHealth);

	}

	public void removeAllSoyHealth(LivingEntity entity) {
		this.soyHealthLevel = 0;
		if (!entity.level.isClientSide()) {
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

	public float getSoyHealth() {
		return soyHealth;
	}

	public float getSoyMaxHealth() {
		return soyMaxHealth;
	}

	public void tick(LivingEntity livingEntity) {
		if (!livingEntity.level.isClientSide()) {
			if (livingEntity.level.getDayTime() > this.lastTick + 24000L) {
				if (this.soyHealthLevel > 0) {
					this.setSoyHealthLevel(livingEntity, this.soyHealthLevel - 2, false);
					this.lastTick = livingEntity.level.getDayTime();
				}
				this.setSoyHealth(livingEntity, this.soyHealth - 1, this.soyMaxHealth - 1);
			}
		}

		if (livingEntity.tickCount % 600 == 0) {
			this.setSoyHealth(livingEntity, this.soyHealth + 1, this.soyMaxHealth);
		}
	}

	@Nonnull
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return (capability == TofuCraftReload.SOY_HEALTH_CAPABILITY) ? LazyOptional.of(() -> this).cast() : LazyOptional.empty();
	}

	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putLong("RemainTick", this.lastTick);
		nbt.putInt("SoyHealthLevel", this.soyHealthLevel);
		nbt.putFloat("SoyHealth", this.soyHealth);
		nbt.putFloat("SoyMaxHealth", this.soyMaxHealth);
		return nbt;
	}

	public void deserializeNBT(CompoundTag nbt) {
		this.lastTick = nbt.getLong("RemainTick");
		this.soyHealthLevel = nbt.getInt("SoyHealthLevel");
		this.soyHealth = nbt.getInt("SoyHealth");
		this.soyMaxHealth = nbt.getInt("SoyMaxHealth");
	}
}

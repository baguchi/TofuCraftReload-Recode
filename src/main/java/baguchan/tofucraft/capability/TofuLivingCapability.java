package baguchan.tofucraft.capability;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class TofuLivingCapability implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
	private static final UUID MODIFIER_SPEED_BOOST_UUID = UUID.fromString("4c1289d9-c549-14ae-5384-b9ed850b44a1");
	private static final UUID MODIFIER_HORSE_JUMP_BOOST_UUID = UUID.fromString("98f128f6-3043-bb90-2e49-b584f3de02f9");

	public boolean isInTofuPortal = false;
	public int tofuPortalTimer = 0;
	public int tofuPortalCooldown = 200;
	public float prevPortalAnimTime, portalAnimTime = 0.0F;

	public int saltEatCounter = 0;
	public boolean resetEating;
	public int saltBoostCooldown = 0;
	public int saltBoost = 0;

	public void tick(Entity entity) {
		if (entity.level().isClientSide) {
			this.prevPortalAnimTime = this.portalAnimTime;
			Minecraft mc = Minecraft.getInstance();
			if (this.isInTofuPortal) {
				if (mc.screen != null && !mc.screen.isPauseScreen()) {
					if (mc.screen instanceof ContainerScreen && mc.player != null) {
						mc.player.closeContainer();
					}

					mc.setScreen(null);
				}

				if (this.portalAnimTime == 0.0F) {
					playPortalSound(mc);
				}
			}
		} else {
			this.handleSaltBoost(entity);
		}

		if (this.tofuPortalCooldown > 0) {
			--this.tofuPortalCooldown;
		}

		if (this.isInTofuPortal) {
			++this.tofuPortalTimer;
			if (entity.level().isClientSide) {
				this.portalAnimTime += 0.0125F;
				if (this.portalAnimTime > 1.0F) {
					this.portalAnimTime = 1.0F;
				}
			}
			this.isInTofuPortal = false;
		} else {
			if (entity.level().isClientSide) {
				if (this.portalAnimTime > 0.0F) {
					this.portalAnimTime -= 0.05F;
				}

				if (this.portalAnimTime < 0.0F) {
					this.portalAnimTime = 0.0F;
				}
			}
			if (this.tofuPortalTimer > 0) {
				this.tofuPortalTimer -= 4;
			}
		}
	}

	private void handleSaltBoost(Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			if (this.saltBoost == 0) {
				this.removeBoost(livingEntity);
				this.saltBoost = -1;
			} else if (this.saltBoost > 0) {
				--this.saltBoost;
			}
			if (this.saltBoostCooldown > 0) {
				--this.saltBoostCooldown;
			}
			if (entity instanceof AbstractHorse horse) {
				if (this.saltEatCounter > 0) {
					horse.setEating(true);
					this.resetEating = true;
				} else {
					if (this.resetEating && horse.isEating()) {
						horse.setEating(false);
						this.resetEating = false;
					}
				}
			}
		}
	}

	protected void removeBoost(LivingEntity entity) {
		AttributeInstance attributeinstance = entity.getAttribute(Attributes.JUMP_STRENGTH);
		if (attributeinstance != null) {
			if (attributeinstance.getModifier(MODIFIER_HORSE_JUMP_BOOST_UUID) != null) {
				attributeinstance.removeModifier(MODIFIER_HORSE_JUMP_BOOST_UUID);
			}

		}
		AttributeInstance attributeinstance2 = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		if (attributeinstance2 != null) {
			if (attributeinstance2.getModifier(MODIFIER_SPEED_BOOST_UUID) != null) {
				attributeinstance2.removeModifier(MODIFIER_SPEED_BOOST_UUID);
			}

		}
	}

	public void tryAddSaltBoost(LivingEntity entity) {
		if (!entity.level().isClientSide) {
			AttributeInstance attributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attributeinstance2 = entity.getAttribute(Attributes.JUMP_STRENGTH);
			if (attributeinstance == null || attributeinstance2 == null) {
				return;
			}
			attributeinstance.addTransientModifier(new AttributeModifier(MODIFIER_SPEED_BOOST_UUID, "SaltBoost", (double) 0.015, AttributeModifier.Operation.ADDITION));
			attributeinstance2.addTransientModifier(new AttributeModifier(MODIFIER_HORSE_JUMP_BOOST_UUID, "SaltBoostJump", (double) 0.1, AttributeModifier.Operation.ADDITION));
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void playPortalSound(Minecraft mc) {
		if (mc.player != null) {
			mc.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, mc.player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
		}
	}

	public void setSaltBoost(int saltBoost, int cooldown, LivingEntity entity) {
		this.saltBoost = saltBoost;
		this.saltBoostCooldown = cooldown;
		this.tryAddSaltBoost(entity);
		this.saltEatCounter = 10;
	}

	public int getSaltBoost() {
		return saltBoost;
	}

	public int getSaltBoostCooldown() {
		return saltBoostCooldown;
	}

	public boolean isInPortal() {
		return this.isInTofuPortal;
	}

	public void setInPortal(boolean inPortal) {
		this.isInTofuPortal = inPortal;
	}

	public void setPortalTimer(int timer) {
		this.tofuPortalTimer = timer;
	}

	public int getPortalTimer() {
		return this.tofuPortalTimer;
	}

	public float getPortalAnimTime() {
		return this.portalAnimTime;
	}

	public float getPrevPortalAnimTime() {
		return this.prevPortalAnimTime;
	}

	@Nonnull
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return (capability == TofuCraftReload.TOFU_LIVING_CAPABILITY) ? LazyOptional.of(() -> this).cast() : LazyOptional.empty();
	}

	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("SaltBoost", this.saltBoost);
		nbt.putInt("SaltBoostCooldown", this.saltBoostCooldown);
		return nbt;
	}

	public void deserializeNBT(CompoundTag nbt) {
		this.saltBoost = nbt.getInt("SaltBoost");
		this.saltBoostCooldown = nbt.getInt("SaltBoostCooldown");
	}
}

package baguchan.tofucraft.capability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class TofuLivingCapability implements INBTSerializable<CompoundTag> {

	public boolean isInTofuPortal = false;
	public int tofuPortalTimer = 0;
	public int tofuPortalCooldown = 200;
	public float prevPortalAnimTime, portalAnimTime = 0.0F;

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

	@OnlyIn(Dist.CLIENT)
	private void playPortalSound(Minecraft mc) {
		if (mc.player != null) {
			mc.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, mc.player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
		}
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

	@Override
	public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag nbt = new CompoundTag();
		return nbt;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {

	}
}

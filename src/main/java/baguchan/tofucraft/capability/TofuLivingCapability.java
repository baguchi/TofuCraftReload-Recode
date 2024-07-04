package baguchan.tofucraft.capability;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class TofuLivingCapability implements INBTSerializable<CompoundTag> {

	public boolean isInTofuPortal = false;
	public int tofuPortalTimer = 0;
	public float portalIntensity;
	public float oPortalIntensity;

	public void tick(Entity entity) {
		this.handleTofuPortal(entity);
	}

	private void handleTofuPortal(Entity player) {
		if (player instanceof LocalPlayer localPlayer) {
			if (!(Minecraft.getInstance().screen instanceof ReceivingLevelScreen)) {
				this.oPortalIntensity = this.portalIntensity;
				float f = 0.0F;
				if (localPlayer.portalProcess != null && localPlayer.portalProcess.isInsidePortalThisTick() && localPlayer.portalProcess.isSamePortal(TofuBlocks.TOFU_PORTAL.get())) {
					if (Minecraft.getInstance().screen != null
							&& !Minecraft.getInstance().screen.isPauseScreen()
							&& !(Minecraft.getInstance().screen instanceof DeathScreen)
							&& !(Minecraft.getInstance().screen instanceof WinScreen)) {
						if (Minecraft.getInstance().screen instanceof AbstractContainerScreen) {
							localPlayer.closeContainer();
						}

						Minecraft.getInstance().setScreen(null);
					}

					if (this.portalIntensity == 0.0F) {
						Minecraft.getInstance()
								.getSoundManager()
								.play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, localPlayer.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
					}

					f = 0.0125F;
					localPlayer.portalProcess.setAsInsidePortalThisTick(false);
				} else if (this.portalIntensity > 0.0F) {
					f = -0.05F;
				}

				this.portalIntensity = Mth.clamp(this.portalIntensity + f, 0.0F, 1.0F);
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

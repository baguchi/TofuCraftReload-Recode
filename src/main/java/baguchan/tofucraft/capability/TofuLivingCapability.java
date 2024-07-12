package baguchan.tofucraft.capability;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class TofuLivingCapability implements INBTSerializable<CompoundTag> {
	public float portalIntensity;
	public float oPortalIntensity;

	public void tick(Entity entity) {
		this.handleTofuPortal(entity);
	}

	private void handleTofuPortal(Entity entity) {
		if (entity instanceof Player player) {
			if (player.level().isClientSide()) {
				this.oPortalIntensity = this.portalIntensity;
				float f = 0.0F;
				if (player.portalProcess != null && player.portalProcess.isInsidePortalThisTick() && player.portalProcess.isSamePortal(TofuBlocks.TOFU_PORTAL.get())) {
					if (Minecraft.getInstance().screen != null
							&& !Minecraft.getInstance().screen.isPauseScreen()
							&& !(Minecraft.getInstance().screen instanceof DeathScreen)
							&& !(Minecraft.getInstance().screen instanceof WinScreen)) {
						if (Minecraft.getInstance().screen instanceof AbstractContainerScreen) {
							player.closeContainer();
						}

						Minecraft.getInstance().setScreen(null);
					}

					if (this.portalIntensity == 0.0F) {
						ClientUtils.playPortalSound(player);
					}

					f = 0.0125F;
					player.portalProcess.setAsInsidePortalThisTick(false);
				} else if (this.portalIntensity > 0.0F) {
					f = -0.05F;
				}

				this.portalIntensity = Mth.clamp(this.portalIntensity + f, 0.0F, 1.0F);
			}
		}
	}


	@Override
	public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag nbt = new CompoundTag();
		return nbt;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {

	}

	public float getPrevPortalAnimTime() {
		return this.oPortalIntensity;
	}

	public float getPortalAnimTime() {
		return this.portalIntensity;
	}
}

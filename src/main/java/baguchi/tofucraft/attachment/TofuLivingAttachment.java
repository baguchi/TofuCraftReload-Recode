package baguchi.tofucraft.attachment;

import baguchi.tofucraft.network.RecoverHealthPacket;
import baguchi.tofucraft.network.ZundafiedPacket;
import baguchi.tofucraft.registry.TofuAnimations;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

public class TofuLivingAttachment implements INBTSerializable<CompoundTag> {
	public boolean isInsidePortal = false;
	public int portalTimer = 0;
	public float portalAnimTime = 0;
	public float prevPortalAnimTime = 0;
	public float recoverHealth = 0;
	public int wolfEatCooldown;
	public boolean zundafied = false;
	private final TofuAnimationData thrownRightAnimationData = new TofuAnimationData(TofuAnimations.THROWN_RIGHT, 10);
	private final TofuAnimationData thrownLeftAnimationData = new TofuAnimationData(TofuAnimations.THROWN_LEFT, 10);


	public void tick(Entity entity) {
		if (entity instanceof Player player) {
			this.handlePortal(player);
		}
		this.handleFood(entity);
		thrownRightAnimationData.tick(entity);
		thrownLeftAnimationData.tick(entity);
	}

	public void thrownAnimation(Entity entity, InteractionHand interactionHand) {
		thrownRightAnimationData.stop(entity);
		thrownLeftAnimationData.stop(entity);

		if (interactionHand == InteractionHand.MAIN_HAND) {
			thrownLeftAnimationData.start(entity);
		} else {
			thrownRightAnimationData.start(entity);
		}
	}

	private void handleFood(Entity entity) {
		if (wolfEatCooldown > 0) {
			wolfEatCooldown--;
		}
	}

	public boolean isWolfEatCooldown() {
		return this.wolfEatCooldown > 0;
	}

	public void setRecoverHealth(Entity entity, float recoverHealth) {
		this.recoverHealth = recoverHealth;
		if (!entity.level().isClientSide() && entity instanceof LivingEntity living) {
			PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new RecoverHealthPacket(living, recoverHealth));
		}
	}

	public float getRecoverHealth() {
		return recoverHealth;
	}

	public void setZundafied(Entity entity, boolean zundafied) {
		this.zundafied = zundafied;
		if (!entity.level().isClientSide() && entity instanceof LivingEntity living) {
			PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new ZundafiedPacket(living, zundafied));
		}
	}

	public boolean isZundafied() {
		return zundafied;
	}

	public void setInPortal(boolean inPortal) {
		this.isInsidePortal = inPortal;
	}

	public boolean isInsidePortal() {
		return this.isInsidePortal;
	}

	public void setPortalTimer(int timer) {
		this.portalTimer = timer;
	}

	public int getPortalTimer() {
		return this.portalTimer;
	}

	public float getPortalAnimTime() {
		return this.portalAnimTime;
	}

	public float getPrevPortalAnimTime() {
		return this.prevPortalAnimTime;
	}

	public void handlePortal(Player player) {
		if (player.level().isClientSide()) {
			this.prevPortalAnimTime = this.portalAnimTime;
			if (this.isInsidePortal()) {
				if (Minecraft.getInstance().screen != null
						&& !Minecraft.getInstance().screen.isPauseScreen()
						&& !(Minecraft.getInstance().screen instanceof DeathScreen)
						&& !(Minecraft.getInstance().screen instanceof WinScreen)) {
					if (Minecraft.getInstance().screen instanceof AbstractContainerScreen) {
						player.closeContainer();
					}

					Minecraft.getInstance().setScreen(null);
				}

				if (this.portalAnimTime == 0.0F) {
					ClientUtils.playPortalSound(player);
				}
			}
		}

		if (this.isInsidePortal()) {
			++this.portalTimer;
			if (player.level().isClientSide()) {
				this.portalAnimTime += 0.0125F;
				if (this.getPortalAnimTime() > 1.0F) {
					this.portalAnimTime = 1.0F;
				}
			}
			this.isInsidePortal = false;
		} else {
			if (player.level().isClientSide()) {
				if (this.getPortalAnimTime() > 0.0F) {
					this.portalAnimTime -= 0.05F;
				}

				if (this.getPortalAnimTime() < 0.0F) {
					this.portalAnimTime = 0.0F;
				}
			}
			if (this.getPortalTimer() > 0) {
				this.portalTimer -= 4;
			}
		}
	}

	public void setWolfEatCooldown(int wolfEatCooldown) {
		this.wolfEatCooldown = wolfEatCooldown;
	}

	@Override
	public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("recover_health", this.recoverHealth);
		nbt.putBoolean("zundafied", this.zundafied);
		if (wolfEatCooldown > 0) {
			nbt.putInt("wolf_eat_cooldown", this.wolfEatCooldown);
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
		this.recoverHealth = nbt.getFloatOr("recover_health", 0);
		this.zundafied = nbt.getBooleanOr("zundafied", false);
		if (nbt.contains("wolf_eat_cooldown")) {
			this.wolfEatCooldown = nbt.getIntOr("wolf_eat_cooldown", 0);
		}
	}
}

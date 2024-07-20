package baguchan.tofucraft.attachment;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class TofuLivingAttachment implements INBTSerializable<CompoundTag> {
	private static final ResourceLocation MODIFIER_SPEED_BOOST = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_speed_boost");
	private static final ResourceLocation MODIFIER_HORSE_JUMP_BOOST = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_horse_jump_boost");
	public boolean isInsidePortal = false;
	public int portalTimer = 0;
	public float portalAnimTime = 0;
	public float prevPortalAnimTime = 0;

	public int saltBoostCooldown = 0;
	public int saltBoost = 0;

	public void tick(Entity entity) {
		if (entity instanceof Player player) {
			this.handlePortal(player);
		}

		if (!entity.level().isClientSide) {
			this.handleSaltBoost(entity);
		}
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
		}
	}

	protected void removeBoost(LivingEntity entity) {
		AttributeInstance attributeinstance = entity.getAttribute(Attributes.JUMP_STRENGTH);
		if (attributeinstance != null) {
			if (attributeinstance.getModifier(MODIFIER_HORSE_JUMP_BOOST) != null) {
				attributeinstance.removeModifier(MODIFIER_HORSE_JUMP_BOOST);
			}

		}
		AttributeInstance attributeinstance2 = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		if (attributeinstance2 != null) {
			if (attributeinstance2.getModifier(MODIFIER_SPEED_BOOST) != null) {
				attributeinstance2.removeModifier(MODIFIER_SPEED_BOOST);
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
			attributeinstance.addTransientModifier(new AttributeModifier(MODIFIER_SPEED_BOOST, 0.015, AttributeModifier.Operation.ADD_VALUE));
			attributeinstance2.addTransientModifier(new AttributeModifier(MODIFIER_HORSE_JUMP_BOOST, 0.125, AttributeModifier.Operation.ADD_VALUE));
		}
	}

	public void setSaltBoost(int saltBoost, int cooldown, LivingEntity entity) {
		if (this.saltBoostCooldown <= 0) {
			this.saltBoost = saltBoost;
			this.saltBoostCooldown = cooldown;
			this.tryAddSaltBoost(entity);
		}
	}

	public int getSaltBoost() {
		return saltBoost;
	}

	public int getSaltBoostCooldown() {
		return saltBoostCooldown;
	}



	@Override
	public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("SaltBoost", this.saltBoost);
		nbt.putInt("SaltBoostCooldown", this.saltBoostCooldown);
		return nbt;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
		this.saltBoost = nbt.getInt("SaltBoost");
		this.saltBoostCooldown = nbt.getInt("SaltBoostCooldown");
	}
}

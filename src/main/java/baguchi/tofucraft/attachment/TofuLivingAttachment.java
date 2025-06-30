package baguchi.tofucraft.attachment;

import baguchi.bagus_lib.util.data.BagusAnimationData;
import baguchi.tofucraft.entity.projectile.ZundaBuster;
import baguchi.tofucraft.network.RecoverHealthPacket;
import baguchi.tofucraft.network.ZundafiedPacket;
import baguchi.tofucraft.registry.TofuAnimations;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.network.PacketDistributor;

public class TofuLivingAttachment implements ValueIOSerializable {
	public boolean isInsidePortal = false;
	public int portalTimer = 0;
	public float portalAnimTime = 0;
	public float prevPortalAnimTime = 0;
	public float recoverHealth = 0;
	public int eatCooldown;
	public boolean zundafied = false;
	private final BagusAnimationData thrownRightAnimationData = new BagusAnimationData(TofuAnimations.THROWN_RIGHT, 10);
	private final BagusAnimationData thrownLeftAnimationData = new BagusAnimationData(TofuAnimations.THROWN_LEFT, 10);
	private final BagusAnimationData busterRightAnimationData = new BagusAnimationData(TofuAnimations.BUSTER_RIGHT, 20);
	private final BagusAnimationData busterLeftAnimationData = new BagusAnimationData(TofuAnimations.BUSTER_LEFT, 20);



	public void tick(Entity entity) {
		if (entity instanceof Player player) {
			this.handlePortal(player);
		}
		this.handleFood(entity);
		thrownRightAnimationData.tick(entity);
		thrownLeftAnimationData.tick(entity);
		busterRightAnimationData.tick(entity);
		busterLeftAnimationData.tick(entity);
		if (!entity.level().isClientSide()) {
			if (busterRightAnimationData.started && busterRightAnimationData.animationTick == 8) {
				if (entity instanceof LivingEntity living) {
					ItemStack stack = living.getMainHandItem();
					ZundaBuster zundaBuster = new ZundaBuster(living.level(), living, stack);
					zundaBuster.shootFromRotation(living, living.getXRot(), living.getYRot(), 0.0F, 3.0F, 1F);
					living.level().addFreshEntity(zundaBuster);
					entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.25F, 1.25F + entity.getRandom().nextFloat() * 0.4F);

				}
			}
			if (busterLeftAnimationData.started && busterLeftAnimationData.animationTick == 8) {
				if (entity instanceof LivingEntity living) {
					ItemStack stack = living.getOffhandItem();
					ZundaBuster zundaBuster = new ZundaBuster(living.level(), living, stack);
					zundaBuster.shootFromRotation(living, living.getXRot(), living.getYRot(), 0.0F, 3.0F, 1F);
					living.level().addFreshEntity(zundaBuster);
					entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.25F, 1.25F + entity.getRandom().nextFloat() * 0.4F);

				}
			}
		}
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

	public void busterAnimation(Entity entity, InteractionHand interactionHand) {
		busterRightAnimationData.stop(entity);
		busterLeftAnimationData.stop(entity);

		if (interactionHand == InteractionHand.MAIN_HAND) {
			busterRightAnimationData.start(entity);
		} else {
			busterLeftAnimationData.start(entity);
		}
	}

	private void handleFood(Entity entity) {
		if (eatCooldown > 0) {
			eatCooldown--;
		}
	}

	public boolean isEatCooldown() {
		return this.eatCooldown > 0;
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

	public void setEatCooldown(int eatCooldown) {
		this.eatCooldown = eatCooldown;
	}

	@Override
	public void serialize(ValueOutput valueOutput) {
		valueOutput.putFloat("recover_health", this.recoverHealth);
		valueOutput.putBoolean("zundafied", this.zundafied);
		if (eatCooldown > 0) {
			valueOutput.putInt("eat_cooldown", this.eatCooldown);
		}
	}

	@Override
	public void deserialize(ValueInput nbt) {
		this.recoverHealth = nbt.getFloatOr("recover_health", 0);
		this.zundafied = nbt.getBooleanOr("zundafied", false);
		this.eatCooldown = nbt.getIntOr("eat_cooldown", 0);
	}
}

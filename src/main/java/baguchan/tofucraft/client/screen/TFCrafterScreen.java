package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.inventory.TFCrafterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.CrafterSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TFCrafterScreen extends AbstractContainerScreen<TFCrafterMenu> {
	private static final ResourceLocation DISABLED_SLOT_LOCATION_SPRITE = ResourceLocation.parse("container/crafter/disabled_slot");
	private static final ResourceLocation POWERED_REDSTONE_LOCATION_SPRITE = ResourceLocation.parse("container/crafter/powered_redstone");
	private static final ResourceLocation UNPOWERED_REDSTONE_LOCATION_SPRITE = ResourceLocation.parse("container/crafter/unpowered_redstone");

	private static final ResourceLocation CONTAINER_LOCATION = ResourceLocation.parse("textures/gui/container/crafter.png");
	private static final Component DISABLED_SLOT_TOOLTIP = Component.translatable("gui.togglable_slot");
	private final Player player;

	public TFCrafterScreen(TFCrafterMenu p_307225_, Inventory p_307403_, Component p_307629_) {
		super(p_307225_, p_307403_, p_307629_);
		this.player = p_307403_.player;
	}

	protected void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	protected void slotClicked(Slot p_307465_, int p_307203_, int p_307325_, ClickType p_307680_) {
		if (p_307465_ instanceof CrafterSlot && !p_307465_.hasItem() && !this.player.isSpectator()) {
			switch (p_307680_) {
				case PICKUP:
					if (this.menu.isSlotDisabled(p_307203_)) {
						this.enableSlot(p_307203_);
					} else if (this.menu.getCarried().isEmpty()) {
						this.disableSlot(p_307203_);
					}
					break;
				case SWAP:
					ItemStack itemstack = this.player.getInventory().getItem(p_307325_);
					if (this.menu.isSlotDisabled(p_307203_) && !itemstack.isEmpty()) {
						this.enableSlot(p_307203_);
					}
			}
		}

		super.slotClicked(p_307465_, p_307203_, p_307325_, p_307680_);
	}

	private void enableSlot(int p_309075_) {
		this.updateSlotState(p_309075_, true);
	}

	private void disableSlot(int p_308981_) {
		this.updateSlotState(p_308981_, false);
	}

	private void updateSlotState(int p_308878_, boolean p_309127_) {
		this.menu.setSlotState(p_308878_, p_309127_);
		super.handleSlotStateChanged(p_308878_, this.menu.containerId, p_309127_);
		float f = p_309127_ ? 1.0F : 0.75F;
		this.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.4F, f);
	}

	@Override
	public void renderSlot(GuiGraphics p_307608_, Slot p_307570_) {
		if (p_307570_ instanceof CrafterSlot crafterslot && this.menu.isSlotDisabled(p_307570_.index)) {
			this.renderDisabledSlot(p_307608_, crafterslot);
			return;
		}

		super.renderSlot(p_307608_, p_307570_);
	}

	private void renderDisabledSlot(GuiGraphics p_307416_, CrafterSlot p_307247_) {
		p_307416_.blitSprite(DISABLED_SLOT_LOCATION_SPRITE, p_307247_.x - 1, p_307247_.y - 1, 18, 18);
	}

	@Override
	public void render(GuiGraphics p_307196_, int p_307586_, int p_307288_, float p_307623_) {
		super.render(p_307196_, p_307586_, p_307288_, p_307623_);
		this.renderRedstone(p_307196_);
		this.renderTooltip(p_307196_, p_307586_, p_307288_);
		if (this.hoveredSlot instanceof CrafterSlot
				&& !this.menu.isSlotDisabled(this.hoveredSlot.index)
				&& this.menu.getCarried().isEmpty()
				&& !this.hoveredSlot.hasItem()) {
			p_307196_.renderTooltip(this.font, DISABLED_SLOT_TOOLTIP, p_307586_, p_307288_);
		}
	}

	private void renderRedstone(GuiGraphics p_307600_) {
		int i = this.width / 2 + 9;
		int j = this.height / 2 - 48;
		ResourceLocation resourcelocation;
		if (this.menu.isPowered()) {
			resourcelocation = POWERED_REDSTONE_LOCATION_SPRITE;
		} else {
			resourcelocation = UNPOWERED_REDSTONE_LOCATION_SPRITE;
		}

		p_307600_.blitSprite(resourcelocation, i, j, (int) (16 * this.menu.getProgress()), 16);
	}

	@Override
	protected void renderBg(GuiGraphics p_307513_, float p_307580_, int p_307561_, int p_307248_) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		p_307513_.blit(CONTAINER_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
	}
}

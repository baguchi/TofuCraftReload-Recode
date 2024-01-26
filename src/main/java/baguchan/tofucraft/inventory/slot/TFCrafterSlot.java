package baguchan.tofucraft.inventory.slot;

import baguchan.tofucraft.inventory.TFCrafterMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TFCrafterSlot extends Slot {
	private final TFCrafterMenu menu;

	public TFCrafterSlot(Container p_307192_, int p_307545_, int p_307618_, int p_307649_, TFCrafterMenu p_307432_) {
		super(p_307192_, p_307545_, p_307618_, p_307649_);
		this.menu = p_307432_;
	}

	public boolean mayPlace(ItemStack p_307320_) {
		return !this.menu.isSlotDisabled(this.index) && super.mayPlace(p_307320_);
	}

	public void setChanged() {
		super.setChanged();
		this.menu.slotsChanged(this.container);
	}
}

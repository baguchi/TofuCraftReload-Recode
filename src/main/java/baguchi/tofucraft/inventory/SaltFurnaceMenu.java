package baguchi.tofucraft.inventory;

import baguchi.tofucraft.inventory.slot.SaltFurnaceResultSlot;
import baguchi.tofucraft.registry.TofuMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SaltFurnaceMenu extends AbstractContainerMenu {
	private final Container container;

	private final ContainerData data;

	protected final Level level;

	public SaltFurnaceMenu(int i, Inventory playerInventory) {
		this(i, playerInventory, new SimpleContainer(4), new SimpleContainerData(4));
	}

	public SaltFurnaceMenu(int id, Inventory playerInventoryIn, Container furnaceInventoryIn, ContainerData dataIn) {
		super(TofuMenus.SALT_FURNACE.get(), id);
		this.container = furnaceInventoryIn;
		this.data = dataIn;
		this.level = playerInventoryIn.player.level();
		furnaceInventoryIn.startOpen(playerInventoryIn.player);
		addSlot(new Slot(this.container, 0, 23, 53));
		addSlot(new SaltFurnaceResultSlot(playerInventoryIn.player, this.container, 1, 80, 53));
		addSlot(new Slot(this.container, 2, 102, 17));
		addSlot(new SaltFurnaceResultSlot(playerInventoryIn.player, this.container, 3, 102, 53));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++)
				addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		}
		for (int k = 0; k < 9; k++)
			addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
		this.addDataSlots(dataIn);
	}

	@Override
	public boolean stillValid(Player p_75145_1_) {
		return this.container.stillValid(p_75145_1_);
	}

	@Override
	public ItemStack quickMoveStack(Player p_82846_1_, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		int indexOutput = 1;
		int indexOutput2 = 3;
		int startPlayerInv = indexOutput2 + 1;
		int endPlayerInv = startPlayerInv + 36;
		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			itemstack = slotStack.copy();
			if (index == indexOutput || index == indexOutput2) {
				if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index > indexOutput2) {
				if (!this.moveItemStackTo(slotStack, 0, indexOutput2, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(slotStack, 4, 40, false)) {
				return ItemStack.EMPTY;
			}

			slot.onQuickCraft(slotStack, itemstack);
			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			if (slotStack.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(p_82846_1_, slotStack);
		}
		return itemstack;
	}


	public int getBurnProgress() {
		int i = this.data.get(2);
		int j = this.data.get(3);
		return (j != 0 && i != 0) ? (i * 24 / j) : 0;
	}


	public int getLitProgress() {
		int i = this.data.get(1);
		if (i == 0)
			i = 200;
		return this.data.get(0) * 13 / i;
	}


	public boolean isLit() {
		return (this.data.get(0) > 0);
	}
}

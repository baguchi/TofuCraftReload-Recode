package baguchan.tofucraft.inventory;

import baguchan.tofucraft.registry.TofuContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SaltFurnaceContainer extends Container {
	private final IInventory container;

	private final IIntArray data;

	protected final World level;

	public SaltFurnaceContainer(int i, PlayerInventory playerInventory) {
		this(i, playerInventory, new Inventory(4), new IntArray(4));
	}

	public SaltFurnaceContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray dataIn) {
		super(TofuContainers.SALT_FURNACE, id);
		this.container = furnaceInventoryIn;
		this.data = dataIn;
		this.level = playerInventoryIn.player.level;
		addSlot(new Slot(this.container, 0, 23, 53));
		addSlot(new Slot(this.container, 1, 80, 53) {
			public boolean func_75214_a(ItemStack p_75214_1_) {
				return false;
			}
		});
		addSlot(new Slot(this.container, 2, 124, 20));
		addSlot(new Slot(this.container, 3, 102, 53) {
			public boolean func_75214_a(ItemStack p_75214_1_) {
				return false;
			}
		});
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++)
				addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		}
		for (int k = 0; k < 9; k++)
			addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
		this.addDataSlots(dataIn);
	}

	public boolean stillValid(PlayerEntity p_75145_1_) {
		return this.container.stillValid(p_75145_1_);
	}


	public ItemStack quickMoveStack(PlayerEntity p_82846_1_, int slotIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIndex);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			switch (slotIndex) {
				case 0:
					if (!moveItemStackTo(itemstack1, 4, 40, true))
						return ItemStack.EMPTY;
					break;
			}
			if (!moveItemStackTo(itemstack1, 0, 0, false))
				return ItemStack.EMPTY;
			slot.onQuickCraft(itemstack1, itemstack);
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(p_82846_1_, itemstack1);
		}
		return itemstack;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBurnProgress() {
		int i = this.data.get(2);
		int j = this.data.get(3);
		return (j != 0 && i != 0) ? (i * 24 / j) : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getLitProgress() {
		int i = this.data.get(1);
		if (i == 0)
			i = 200;
		return this.data.get(0) * 13 / i;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isLit() {
		return (this.data.get(0) > 0);
	}
}

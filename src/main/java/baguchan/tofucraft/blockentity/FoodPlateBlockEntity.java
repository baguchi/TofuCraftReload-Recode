package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FoodPlateBlockEntity extends SyncedBlockEntity {
	private final ItemStackHandler inventory;

	public FoodPlateBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.FOODPLATE.get(), pos, state);
		inventory = createHandler();
	}
	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider p_338445_) {
		super.loadAdditional(compound, p_338445_);
		inventory.deserializeNBT(p_338445_, compound.getCompound("Inventory"));
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider p_338445_) {
		super.saveAdditional(compound, p_338445_);
		compound.put("Inventory", inventory.serializeNBT(p_338445_));
	}

	public boolean addItem(ItemStack itemStack) {
		if (isEmpty() && !itemStack.isEmpty()) {
			inventory.setStackInSlot(0, itemStack.split(1));
			inventoryChanged();
			return true;
		}
		return false;
	}

	public boolean addAllItem(ItemStack itemStack) {
		if (isEmpty() && !itemStack.isEmpty()) {
			inventory.setStackInSlot(0, itemStack.split(64));
			inventoryChanged();
			return true;
		}
		return false;
	}

	public ItemStack removeItem() {
		if (!isEmpty()) {
			ItemStack item = getStoredItem().split(1);
			inventoryChanged();
			return item;
		}
		return ItemStack.EMPTY;
	}

	public IItemHandler getInventory() {
		return inventory;
	}

	public ItemStack getStoredItem() {
		return inventory.getStackInSlot(0);
	}

	public boolean isEmpty() {
		return inventory.getStackInSlot(0).isEmpty();
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler() {
			@Override
			public int getSlotLimit(int slot) {
				return 64;
			}

			@Override
			protected void onContentsChanged(int slot) {
				inventoryChanged();
			}
		};
	}
}

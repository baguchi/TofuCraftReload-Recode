package baguchi.tofucraft.blockentity;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FoodPlateBlockEntity extends SyncedBlockEntity {
	private final ItemStackHandler inventory;

	private boolean fire;

	public FoodPlateBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.FOODPLATE.get(), pos, state);
		inventory = createHandler();
	}
	@Override
	public void loadAdditional(ValueInput compound) {
		super.loadAdditional(compound);
		inventory.deserialize(compound.childOrEmpty("Item"));
		fire = compound.getBooleanOr("Fire", false);
	}

	@Override
	public void saveAdditional(ValueOutput compound) {
		super.saveAdditional(compound);
		inventory.serialize(compound.child("Item"));
		compound.putBoolean("Fire", fire);
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
			if (isFire()) {
				this.setFire(false);
			}
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

	public void setFire(boolean fire) {
		this.fire = fire;
		inventoryChanged();
	}

	public boolean isFire() {
		return fire;
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

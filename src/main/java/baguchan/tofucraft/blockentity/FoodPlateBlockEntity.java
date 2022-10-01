package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FoodPlateBlockEntity extends SyncedBlockEntity {
	private final ItemStackHandler inventory;
	private final LazyOptional<IItemHandler> inputHandler;

	public FoodPlateBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.FOODPLATE.get(), pos, state);
		inventory = createHandler();
		inputHandler = LazyOptional.of(() -> inventory);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		inventory.deserializeNBT(compound.getCompound("Inventory"));
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		compound.put("Inventory", inventory.serializeNBT());
	}

	public boolean addItem(ItemStack itemStack) {
		if (isEmpty() && !itemStack.isEmpty()) {
			inventory.setStackInSlot(0, itemStack.split(1));
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


	@Override
	@Nonnull
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if (cap.equals(ForgeCapabilities.ITEM_HANDLER)) {
			return inputHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		inputHandler.invalidate();
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler() {
			@Override
			public int getSlotLimit(int slot) {
				return 1;
			}

			@Override
			protected void onContentsChanged(int slot) {
				inventoryChanged();
			}
		};
	}
}

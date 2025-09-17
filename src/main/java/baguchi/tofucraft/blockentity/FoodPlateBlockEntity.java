package baguchi.tofucraft.blockentity;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class FoodPlateBlockEntity extends SyncedBlockEntity implements Container {
	protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	@Nullable
	private Component name;


	private boolean fire;

	public FoodPlateBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.FOODPLATE.get(), pos, state);
	}
	@Override
	public void loadAdditional(ValueInput compound) {
		super.loadAdditional(compound);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.items);
		fire = compound.getBooleanOr("Fire", false);
		this.name = parseCustomNameSafe(compound, "CustomName");

	}

	@Override
	public void saveAdditional(ValueOutput compound) {
		super.saveAdditional(compound);
		ContainerHelper.saveAllItems(compound, this.items);
		compound.putBoolean("Fire", fire);
		compound.storeNullable("CustomName", ComponentSerialization.CODEC, this.name);

	}

	public boolean addItem(ItemStack itemStack) {
		if (isEmpty() && !itemStack.isEmpty()) {
			this.items.set(0, itemStack.split(1));
			inventoryChanged();
			return true;
		}
		return false;
	}

	public boolean addAllItem(ItemStack itemStack) {
		if (isEmpty() && !itemStack.isEmpty()) {
			this.items.set(0, itemStack.split(64));
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

	public ItemStack getStoredItem() {
		if (isEmpty()) {
			return ItemStack.EMPTY;
		}
		return this.items.get(0);
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getItem(int i) {
		return this.items.get(i);
	}

	@Override
	public ItemStack removeItem(int p_332707_, int p_332672_) {
		ItemStack itemstack = ContainerHelper.removeItem(this.items, p_332707_, p_332672_);
		if (!itemstack.isEmpty()) {
			this.setChanged();
		}

		return itemstack;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_332812_) {
		return ContainerHelper.takeItem(this.items, p_332812_);
	}


	@Override
	public void setItem(int i, ItemStack itemStack) {

		this.items.set(i, itemStack);
		this.setChanged();
	}

	@Override
	public boolean stillValid(Player p_332791_) {
		return Container.stillValidBlockEntity(this, p_332791_);
	}


	public void setFire(boolean fire) {
		this.fire = fire;
		inventoryChanged();
	}

	public boolean isFire() {
		return fire;
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}


	@Override
	protected void applyImplicitComponents(DataComponentGetter p_397486_) {
		super.applyImplicitComponents(p_397486_);
		this.name = p_397486_.get(DataComponents.CUSTOM_NAME);
		p_397486_.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.items);
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder p_338252_) {
		super.collectImplicitComponents(p_338252_);
		p_338252_.set(DataComponents.CUSTOM_NAME, this.name);
		p_338252_.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.items));
	}

	@Override
	public void removeComponentsFromTag(ValueOutput p_421741_) {
		p_421741_.discard("CustomName");
		p_421741_.discard("Items");
	}
}

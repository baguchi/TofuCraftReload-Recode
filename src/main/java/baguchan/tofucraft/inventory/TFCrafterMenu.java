package baguchan.tofucraft.inventory;

import baguchan.tofucraft.inventory.slot.TFCrafterSlot;
import baguchan.tofucraft.registry.TofuMenus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.NonInteractiveResultSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CrafterBlock;

public class TFCrafterMenu extends AbstractContainerMenu implements ContainerListener {
	protected static final int SLOT_COUNT = 9;
	private static final int INV_SLOT_START = 9;
	private static final int INV_SLOT_END = 36;
	private static final int USE_ROW_SLOT_START = 36;
	private static final int USE_ROW_SLOT_END = 45;
	private final ResultContainer resultContainer = new ResultContainer();
	private final ContainerData containerData;
	private final Player player;
	private final CraftingContainer container;

	public TFCrafterMenu(int p_307357_, Inventory p_307257_) {
		super(TofuMenus.TF_CRAFTER.get(), p_307357_);
		this.player = p_307257_.player;
		this.containerData = new SimpleContainerData(11);
		this.container = new TransientCraftingContainer(this, 3, 3);
		this.addSlots(p_307257_);

		this.addSlotListener(this);
	}

	public TFCrafterMenu(int p_307363_, Inventory p_307517_, CraftingContainer p_307449_, ContainerData p_307285_) {
		super(TofuMenus.TF_CRAFTER.get(), p_307363_);
		this.player = p_307517_.player;
		this.containerData = p_307285_;
		this.container = p_307449_;
		checkContainerSize(p_307449_, 9);
		p_307449_.startOpen(p_307517_.player);
		this.addSlots(p_307517_);
		this.addSlotListener(this);
	}

	private void addSlots(Inventory p_307214_) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int k = j + i * 3;
				this.addSlot(new TFCrafterSlot(this.container, k, 26 + j * 18, 17 + i * 18, this));
			}
		}

		for (int l = 0; l < 3; l++) {
			for (int j1 = 0; j1 < 9; j1++) {
				this.addSlot(new Slot(p_307214_, j1 + l * 9 + 9, 8 + j1 * 18, 84 + l * 18));
			}
		}

		for (int i1 = 0; i1 < 9; i1++) {
			this.addSlot(new Slot(p_307214_, i1, 8 + i1 * 18, 142));
		}

		this.addSlot(new NonInteractiveResultSlot(this.resultContainer, 0, 134, 35));
		this.addDataSlots(this.containerData);
		this.refreshRecipeResult();
	}

	public void setSlotState(int p_307384_, boolean p_307499_) {
		TFCrafterSlot crafterslot = (TFCrafterSlot) this.getSlot(p_307384_);
		this.containerData.set(crafterslot.index, p_307499_ ? 0 : 1);
		this.broadcastChanges();
	}

	public boolean isSlotDisabled(int p_307609_) {
		return p_307609_ > -1 && p_307609_ < 9 ? this.containerData.get(p_307609_) == 1 : false;
	}

	public boolean isPowered() {
		return this.containerData.get(9) == 1;
	}

	public int getProgress() {
		return (int) ((this.containerData.get(10) / 40F) * 16);
	}

	@Override
	public ItemStack quickMoveStack(Player p_307459_, int p_307204_) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(p_307204_);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (p_307204_ < 9) {
				if (!this.moveItemStackTo(itemstack1, 9, 45, true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, 9, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(p_307459_, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean stillValid(Player p_307229_) {
		return this.container.stillValid(p_307229_);
	}

	private void refreshRecipeResult() {
		if (this.player instanceof ServerPlayer serverplayer) {
			ServerLevel level = serverplayer.serverLevel();
			CraftingInput craftinginput = this.container.asCraftInput();
			ItemStack itemstack = CrafterBlock.getPotentialResults(level, craftinginput)
					.map(p_344359_ -> p_344359_.value().assemble(craftinginput, level.registryAccess()))
					.orElse(ItemStack.EMPTY);
			this.resultContainer.setItem(0, itemstack);
		}
	}

	public Container getContainer() {
		return this.container;
	}

	@Override
	public void slotChanged(AbstractContainerMenu p_307332_, int p_307437_, ItemStack p_307233_) {
		this.refreshRecipeResult();
	}

	@Override
	public void dataChanged(AbstractContainerMenu p_307424_, int p_307646_, int p_307221_) {
	}
}
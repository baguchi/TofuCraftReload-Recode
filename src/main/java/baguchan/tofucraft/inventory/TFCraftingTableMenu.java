package baguchan.tofucraft.inventory;

import baguchan.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import baguchan.tofucraft.inventory.slot.TofuPotResultSlot;
import baguchan.tofucraft.registry.TofuMenus;
import baguchan.tofucraft.registry.TofuRecipeBookTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class TFCraftingTableMenu extends RecipeBookMenu<Container> {
	public final Container inventory;
	private final ContainerData data;
	protected final Level level;

	public TFCraftingTableMenu(int windowId, Inventory playerInventory) {
		this(windowId, playerInventory, new SimpleContainer(10), new SimpleContainerData(4));
	}

	public TFCraftingTableMenu(int windowId, Inventory playerInventory, Container furnaceInventoryIn, ContainerData cookingPotDataIn) {
		super(TofuMenus.TF_CRAFTING_TABLE.get(), windowId);
		this.inventory = furnaceInventoryIn;
		this.data = cookingPotDataIn;
		this.level = playerInventory.player.level();

		// Ingredient Slots - 2 Rows x 3 Columns
		int startX = 8;
		int startY = 18;
		int inputStartX = 30;
		int inputStartY = 17;
		int borderSlotSize = 18;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 3; ++column) {
				this.addSlot(new Slot(inventory, (row * 3) + column,
						inputStartX + (column * borderSlotSize),
						inputStartY + (row * borderSlotSize)));
			}
		}

		// Output
		this.addSlot(new TofuPotResultSlot(playerInventory.player, inventory, 9, 123, 34) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});

		int startPlayerInvY = 84;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * borderSlotSize),
						startPlayerInvY + (row * borderSlotSize)));
			}
		}

		// Hotbar
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startX + (column * borderSlotSize), 142));
		}

		this.addDataSlots(cookingPotDataIn);
	}

	private static TFCraftingTableBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof TFCraftingTableBlockEntity) {
			return (TFCraftingTableBlockEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	@Override
	public boolean stillValid(Player p_75145_1_) {
		return this.inventory.stillValid(p_75145_1_);
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		int indexMealDisplay = 6;
		int indexContainerInput = 7;
		int indexOutput = 8;
		int startPlayerInv = indexOutput + 1;
		int endPlayerInv = startPlayerInv + 36;
		ItemStack slotStackCopy = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			slotStackCopy = slotStack.copy();
			if (index != indexOutput) {
				if (index >= startPlayerInv && index < endPlayerInv - 9) {
					if (!this.moveItemStackTo(slotStack, endPlayerInv - 9, endPlayerInv, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= endPlayerInv - 9 && index < endPlayerInv && !this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv - 9, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (slotStack.getCount() == slotStackCopy.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, slotStack);
		}
		return slotStackCopy;
	}

	public int getCookProgressionScaled() {
		int i = this.data.get(0);
		int j = this.data.get(1);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	public int getTFEnergy() {
		int i = this.data.get(2);
		return i;
	}

	public int getTFMaxEnergy() {
		int i = this.data.get(3);
		return i;
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedContents helper) {
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			helper.accountSimpleStack(inventory.getItem(i));
		}
	}

	@Override
	public void clearCraftingContent() {
		for (int i = 0; i < 8; i++) {
			this.inventory.setItem(i, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean recipeMatches(Recipe<? super Container> p_38980_) {
		return p_38980_.matches(this.inventory, this.level);
	}

	@Override
	public int getResultSlotIndex() {
		return 9;
	}

	@Override
	public int getGridWidth() {
		return 3;
	}

	@Override
	public int getGridHeight() {
		return 3;
	}

	@Override
	public int getSize() {
		return 9;
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return TofuRecipeBookTypes.TF_CRAFT;
	}

	@Override
	public boolean shouldMoveToInventory(int slot) {
		return slot < (getGridWidth() * getGridHeight());
	}
}
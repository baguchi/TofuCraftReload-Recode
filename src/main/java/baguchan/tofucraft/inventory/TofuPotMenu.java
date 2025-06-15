package baguchan.tofucraft.inventory;

import baguchan.tofucraft.blockentity.TofuPotBlockEntity;
import baguchan.tofucraft.inventory.slot.TofuPotResultSlot;
import baguchan.tofucraft.recipe.TofuPotRecipe;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class TofuPotMenu extends RecipeBookMenu<CraftingInput, TofuPotRecipe> {
	public final TofuPotBlockEntity blockEntity;
	public final Container inventory;
	private final ContainerData cookingPotData;
	private final ContainerLevelAccess canInteractWithCallable;
	protected final Level level;

	public TofuPotMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(2));
	}

	public TofuPotMenu(int windowId, Inventory playerInventory, TofuPotBlockEntity blockEntity, ContainerData cookingPotDataIn) {
		super(TofuMenus.TOFU_POT.get(), windowId);
		this.blockEntity = blockEntity;
		this.inventory = blockEntity;
		this.cookingPotData = cookingPotDataIn;
		this.level = playerInventory.player.level();
		this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

		// Ingredient Slots - 2 Rows x 3 Columns
		int startX = 8;
		int startY = 18;
		int inputStartX = 44;
		int inputStartY = 8;
		int borderSlotSize = 18;
		for (int row = 0; row < 4; ++row) {
			for (int column = 0; column < 3; ++column) {
				this.addSlot(new Slot(inventory, (row * 3) + column,
						inputStartX + (column * borderSlotSize),
						inputStartY + (row * borderSlotSize)));
			}
		}

		// Output
		this.addSlot(new TofuPotResultSlot(playerInventory.player, inventory, 13, 130, 37) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});

		int startPlayerInvY = 96;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * borderSlotSize),
						startPlayerInvY + (row * borderSlotSize)));
			}
		}

		// Hotbar
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startX + (column * borderSlotSize), 154));
		}

		this.addDataSlots(cookingPotDataIn);
	}

	private static TofuPotBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof TofuPotBlockEntity) {
			return (TofuPotBlockEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return stillValid(canInteractWithCallable, playerIn, TofuBlocks.TOFU_POT.get());
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		int indexMealDisplay = 6;
		int indexContainerInput = 7;
		int indexOutput = 11;
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
		int i = this.cookingPotData.get(0);
		int j = this.cookingPotData.get(1);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	public boolean isHeated() {
		return blockEntity.isHeated();
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedContents helper) {
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			helper.accountSimpleStack(inventory.getItem(i));
		}
	}

	@Override
	public void clearCraftingContent() {
		for (int i = 0; i < 12; i++) {
			this.inventory.setItem(i, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean recipeMatches(RecipeHolder<TofuPotRecipe> recipe) {
		return recipe.value().matches(CraftingInput.of(4, 3, blockEntity.inventory), level);
	}

	@Override
	public int getResultSlotIndex() {
		return 13;
	}

	@Override
	public int getGridWidth() {
		return 3;
	}

	@Override
	public int getGridHeight() {
		return 4;
	}

	@Override
	public int getSize() {
		return 12;
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.valueOf("TOFUCRAFT_COOKING");
	}

	@Override
	public boolean shouldMoveToInventory(int slot) {
		return slot < (getGridWidth() * getGridHeight());
	}
}
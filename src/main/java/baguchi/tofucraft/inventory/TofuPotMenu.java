package baguchi.tofucraft.inventory;

import baguchi.tofucraft.blockentity.TofuPotBlockEntity;
import baguchi.tofucraft.inventory.slot.TofuPotResultSlot;
import baguchi.tofucraft.recipe.TofuPotRecipe;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
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

import java.util.List;
import java.util.Objects;

public class TofuPotMenu extends RecipeBookMenu {
	public final TofuPotBlockEntity blockEntity;
	public final Container inventory;
	private final ContainerData cookingPotData;
	private final ContainerLevelAccess canInteractWithCallable;
	protected final Level level;
	private boolean placingRecipe;

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
		int indexOutput = 12;
		int startPlayerInv = indexOutput + 1;
		int endPlayerInv = startPlayerInv + 36;
		ItemStack slotStackCopy = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			slotStackCopy = slotStack.copy();
			if (index == indexOutput) {
				if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
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


	public void slotsChanged(Container p_39366_) {
		if (!this.placingRecipe) {
		}

	}

	@Override
	public RecipeBookMenu.PostPlaceAction handlePlacement(boolean p_361638_, boolean p_361841_, RecipeHolder<?> p_364981_, ServerLevel p_379885_, Inventory p_361078_) {
		RecipeHolder<TofuPotRecipe> recipeholder = (RecipeHolder<TofuPotRecipe>) p_364981_;
		this.beginPlacingRecipe();

		RecipeBookMenu.PostPlaceAction recipebookmenu$postplaceaction;
		try {
			List<Slot> list = this.getInputGridSlots();
			recipebookmenu$postplaceaction = ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<TofuPotRecipe>() {
				public void fillCraftSlotsStackedContents(StackedItemContents p_363395_) {
					TofuPotMenu.this.fillCraftSlotsStackedContents(p_363395_);
				}

				public void clearCraftingContent() {
					for (int i = 0; i < 12; ++i) {
						inventory.setItem(i, ItemStack.EMPTY);
					}
				}

				public boolean recipeMatches(RecipeHolder<TofuPotRecipe> p_365206_) {
					return p_365206_.value().matches(CraftingInput.ofPositioned(4, 3, TofuPotMenu.this.blockEntity.getInventory()).input(), TofuPotMenu.this.level);
				}
			}, 4, 3, list, list, p_361078_, recipeholder, p_361638_, p_361841_);
		} finally {
			this.finishPlacingRecipe(p_379885_, (RecipeHolder<TofuPotRecipe>) p_364981_);
		}

		return recipebookmenu$postplaceaction;
	}

	public void beginPlacingRecipe() {
		this.placingRecipe = true;
	}

	public void finishPlacingRecipe(ServerLevel p_380098_, RecipeHolder<TofuPotRecipe> p_345915_) {
		this.placingRecipe = false;
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedItemContents p_363436_) {
		this.blockEntity.fillStackedContents(p_363436_);
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.valueOf("TOFUCRAFT_COOKING");
	}

	public Slot getResultSlot() {
		return this.slots.get(12);
	}

	public List<Slot> getInputGridSlots() {
		return this.slots.subList(0, 12);
	}
}
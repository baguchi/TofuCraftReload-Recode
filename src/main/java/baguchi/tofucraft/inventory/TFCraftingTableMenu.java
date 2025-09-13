package baguchi.tofucraft.inventory;

import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import baguchi.tofucraft.inventory.slot.TofuPotResultSlot;
import baguchi.tofucraft.recipe.TFCraftingRecipe;
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
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;
import java.util.Objects;

public class TFCraftingTableMenu extends RecipeBookMenu {
	public final TFCraftingTableBlockEntity blockEntity;
	public final Container inventory;
	private final ContainerData data;
	private final ContainerLevelAccess canInteractWithCallable;
	protected final Level level;

	public TFCraftingTableMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
	}

	public TFCraftingTableMenu(int windowId, Inventory playerInventory, TFCraftingTableBlockEntity blockEntity, ContainerData cookingPotDataIn) {
		super(TofuMenus.TF_CRAFTING_TABLE.get(), windowId);
		this.blockEntity = blockEntity;
		this.inventory = blockEntity;
		this.data = cookingPotDataIn;
		this.level = playerInventory.player.level();
		this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

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
	public boolean stillValid(Player playerIn) {
		return stillValid(canInteractWithCallable, playerIn, TofuBlocks.TF_CRAFTING_TABLE.get());
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
	public RecipeBookMenu.PostPlaceAction handlePlacement(boolean p_361638_, boolean p_361841_, RecipeHolder<?> p_364981_, ServerLevel p_379885_, Inventory p_361078_) {
		if (p_364981_.value() instanceof CraftingRecipe) {
			return this.handlePlacementDefaultCraft(p_361638_, p_361841_, p_364981_, p_379885_, p_361078_);
		} else {
			RecipeHolder<TFCraftingRecipe> recipeholder = (RecipeHolder<TFCraftingRecipe>) p_364981_;
			this.beginPlacingRecipe();

			RecipeBookMenu.PostPlaceAction recipebookmenu$postplaceaction;
			try {
				List<Slot> list = this.getInputGridSlots();
				recipebookmenu$postplaceaction = ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<TFCraftingRecipe>() {
					@Override
					public void fillCraftSlotsStackedContents(StackedItemContents p_363395_) {
						TFCraftingTableMenu.this.fillCraftSlotsStackedContents(p_363395_);
					}

					@Override
					public void clearCraftingContent() {
						for (int i = 0; i < 9; ++i) {
							inventory.setItem(i, ItemStack.EMPTY);
							blockEntity.getFakeInventory().set(i, ItemStack.EMPTY);
						}
					}

					@Override
					public boolean recipeMatches(RecipeHolder<TFCraftingRecipe> p_365206_) {
						return p_365206_.value().matches(CraftingInput.ofPositioned(3, 3, TFCraftingTableMenu.this.blockEntity.getInventory()).input(), TFCraftingTableMenu.this.level);
					}
				}, 3, 3, list, list, p_361078_, recipeholder, p_361638_, p_361841_);
			} finally {
				this.finishPlacingRecipe(p_379885_, (RecipeHolder<TFCraftingRecipe>) p_364981_);
			}

			return recipebookmenu$postplaceaction;
		}
	}

	public RecipeBookMenu.PostPlaceAction handlePlacementDefaultCraft(boolean p_361638_, boolean p_361841_, RecipeHolder<?> p_364981_, ServerLevel p_379885_, Inventory p_361078_) {
		RecipeHolder<CraftingRecipe> recipeholder = (RecipeHolder<CraftingRecipe>) p_364981_;
		this.beginPlacingRecipe();

		RecipeBookMenu.PostPlaceAction recipebookmenu$postplaceaction;
		try {
			List<Slot> list = this.getInputGridSlots();
			recipebookmenu$postplaceaction = ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<CraftingRecipe>() {
				@Override
				public void fillCraftSlotsStackedContents(StackedItemContents p_363395_) {
					TFCraftingTableMenu.this.fillCraftSlotsStackedContents(p_363395_);
				}

				@Override
				public void clearCraftingContent() {
					for (int i = 0; i < 9; ++i) {
						inventory.setItem(i, ItemStack.EMPTY);
						blockEntity.getFakeInventory().set(i, ItemStack.EMPTY);
					}
				}

				@Override
				public boolean recipeMatches(RecipeHolder<CraftingRecipe> p_365206_) {
					return p_365206_.value().matches(CraftingInput.ofPositioned(3, 3, TFCraftingTableMenu.this.blockEntity.getInventory()).input(), TFCraftingTableMenu.this.level);
				}
			}, 3, 3, list, list, p_361078_, recipeholder, p_361638_, p_361841_);
		} finally {
			//this.finishPlacingRecipe(p_379885_, p_364981_);
		}

		return recipebookmenu$postplaceaction;
	}

	public void beginPlacingRecipe() {
	}

	public void finishPlacingRecipe(ServerLevel p_380098_, RecipeHolder<TFCraftingRecipe> p_345915_) {
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedItemContents p_363436_) {
		this.blockEntity.fillStackedContents(p_363436_);
	}

	public Slot getResultSlot() {
		return this.slots.get(9);
	}

	public List<Slot> getInputGridSlots() {
		return this.slots.subList(0, 9);
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.valueOf("TOFUCRAFT_TF_CRAFT");
	}
}
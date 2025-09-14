package baguchi.tofucraft.blockentity.tfenergy;

import baguchi.tofucraft.block.tfenergy.TFCraftingTableBlock;
import baguchi.tofucraft.blockentity.tfenergy.base.WorkerBaseBlockEntity;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import baguchi.tofucraft.recipe.TFCraftingRecipe;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.recipebook.PlaceRecipeHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT;

public class TFCraftingTableBlockEntity extends WorkerBaseBlockEntity implements WorldlyContainer, StackedContentsCompatible, RecipeCraftingHolder, MenuProvider {

	protected NonNullList<ItemStack> inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

	@Nullable
	protected RecipeDisplay recipeDisplay;
	private int progress = 0;
	private int progressMax = 0;

	private int refreshTime = 0;
	public static final int MAX_CRAFT_TIME = 200;
	private final RecipeManager.CachedCheck<CraftingInput, ? extends TFCraftingRecipe> quickCheck;
	private final RecipeManager.CachedCheck<CraftingInput, ? extends CraftingRecipe> quickNormalCheck;

	private static final int[] SLOTS_FOR_UP = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
	private static final int[] SLOTS_FOR_DOWN = new int[]{9};

	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
				case 0:
					return TFCraftingTableBlockEntity.this.progress;
				case 1:
					return TFCraftingTableBlockEntity.this.progressMax;
				case 2:
					return TFCraftingTableBlockEntity.this.energy;
				case 3:
					return TFCraftingTableBlockEntity.this.energyMax;
			}
			return 0;
		}

		@Override
		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
				case 0:
					TFCraftingTableBlockEntity.this.progress = p_221477_2_;
					break;
				case 1:
					TFCraftingTableBlockEntity.this.progressMax = p_221477_2_;
					break;
				case 2:
					TFCraftingTableBlockEntity.this.energy = p_221477_2_;
					break;
				case 3:
					TFCraftingTableBlockEntity.this.energyMax = p_221477_2_;
					break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	public TFCraftingTableBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_CRAFTING_TABLE.get(), p_155229_, p_155230_, 10000);
		this.quickCheck = RecipeManager.createCheck(TofuRecipes.RECIPETYPE_TF_CRAFT.get());
		this.quickNormalCheck = RecipeManager.createCheck(RecipeType.CRAFTING);
	}

	public void setRecipeDisplay(@Nullable RecipeDisplay recipeDisplay) {
		this.recipeDisplay = recipeDisplay;
		this.setChanged();
	}

	public @Nullable RecipeDisplay getRecipeDisplay() {
		return recipeDisplay;
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFCraftingTableBlockEntity tfoven) {
		if (level.isClientSide() || !blockState.getValue(TFCraftingTableBlock.ENABLED)) return;

		boolean worked = false;
		if (tfoven.getEnergyStored() > 0 && level instanceof ServerLevel serverLevel) {
			if (tfoven.refreshTime <= 0) {

				Optional<? extends RecipeHolder<? extends TFCraftingRecipe>> optional = tfoven.quickCheck.getRecipeFor(CraftingInput.of(3, 3, tfoven.inventory), serverLevel);
				Optional<? extends RecipeHolder<? extends CraftingRecipe>> optional2 = tfoven.quickNormalCheck.getRecipeFor(CraftingInput.of(3, 3, tfoven.inventory), serverLevel);


				ItemStack tfStack = optional.isEmpty() ? ItemStack.EMPTY : optional.get().value().assemble(CraftingInput.of(3, 3, tfoven.inventory), serverLevel.registryAccess());
				ItemStack craftStack = optional2.isEmpty() ? ItemStack.EMPTY : optional2.get().value().assemble(CraftingInput.of(3, 3, tfoven.inventory), serverLevel.registryAccess());

				if (optional.isPresent() && tfoven.canProcess(tfStack) && (tfoven.recipeDisplay == null || !optional.get().value().display().isEmpty() && optional.get().value().display().contains(tfoven.recipeDisplay))) {
					tfoven.progressMax = optional.get().value().getNeedTF() / 10;
					++tfoven.progress;
					if (tfoven.progress >= tfoven.progressMax) {
						tfoven.progress = 0;
						if (tfoven.craftTF(tfStack, tfoven.inventory)) {
							tfoven.setRecipeUsed(optional.get());
						}
					}
					worked = true;

					tfoven.drain(10, false);
					tfoven.setChanged();
				} else if (optional2.isPresent() && tfoven.canProcess(craftStack) && (tfoven.recipeDisplay == null || !optional2.get().value().display().isEmpty() && optional2.get().value().display().contains(tfoven.recipeDisplay))) {
					tfoven.progressMax = 100 / 10;
					++tfoven.progress;
					if (tfoven.progress >= tfoven.progressMax) {
						tfoven.progress = 0;
						if (tfoven.craft(craftStack, tfoven.inventory)) {
							tfoven.setRecipeUsed(optional2.get());
						}
					}
					worked = true;

					tfoven.drain(10, false);
					tfoven.setChanged();
				} else {
					tfoven.progress = 0;
					tfoven.refreshTime = 10 + tfoven.level.random.nextInt(20);
					tfoven.setChanged();
				}
			} else {
				tfoven.progress = 0;
				tfoven.refreshTime--;
				tfoven.setChanged();
			}
		}

		if (blockState.getValue(LIT) != worked) {
			level.setBlock(blockPos, blockState.setValue(LIT, worked), 2);

		}
	}

	private boolean craft(ItemStack result, NonNullList<ItemStack> p_267073_) {
		if (!result.isEmpty()) {
			ItemStack itemstack1 = result.copy();
			ItemStack itemstack2 = p_267073_.get(9);
			if (itemstack2.isEmpty()) {
				p_267073_.set(9, itemstack1.copy());
			} else if (itemstack2.is(itemstack1.getItem())) {
				itemstack2.grow(itemstack1.getCount());
			}

			for (int i = 0; i < 9; ++i) {
				ItemStack slotStack = inventory.get(i);
				if (!slotStack.getCraftingRemainder().isEmpty()) {
					ejectIngredientRemainder(slotStack.getCraftingRemainder());
				}
				if (!slotStack.isEmpty())
					slotStack.shrink(1);
			}
			return true;
		} else {
			return false;
		}
	}

	protected boolean canProcess(ItemStack result) {
		int i = this.getMaxStackSize();

		ItemStack resultStack = result.copy();
		if (resultStack.isEmpty()) {
			return false;
		} else {
			ItemStack storedMealStack = inventory.get(9);
			if (storedMealStack.isEmpty()) {
				return true;
			} else if (!ItemStack.isSameItem(storedMealStack, resultStack)) {
				return false;
			} else if (storedMealStack.getCount() + resultStack.getCount() <= i && storedMealStack.getCount() + resultStack.getCount() <= storedMealStack.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
				return true;
			} else {
				return storedMealStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
			}
		}
	}

	private boolean craftTF(ItemStack result, NonNullList<ItemStack> p_267073_) {
		if (!result.isEmpty()) {
			ItemStack itemstack1 = result.copy();
			ItemStack itemstack2 = p_267073_.get(9);
			if (itemstack2.isEmpty()) {
				p_267073_.set(9, itemstack1.copy());
			} else if (itemstack2.is(itemstack1.getItem())) {
				itemstack2.grow(itemstack1.getCount());
			}

			for (int i = 0; i < 9; ++i) {
				ItemStack slotStack = inventory.get(i);
				if (!slotStack.getCraftingRemainder().isEmpty()) {
					ejectIngredientRemainder(slotStack.getCraftingRemainder());
				}
				if (!slotStack.isEmpty())
					slotStack.shrink(1);
			}
			return true;
		} else {
			return false;
		}
	}

	protected void ejectIngredientRemainder(ItemStack remainderStack) {
		Direction direction = getBlockState().getValue(TFCraftingTableBlock.HORIZONTAL_FACING).getCounterClockWise();
		double x = worldPosition.getX() + 0.5 + (direction.getStepX() * 0.25);
		double y = worldPosition.getY() + 0.7;
		double z = worldPosition.getZ() + 0.5 + (direction.getStepZ() * 0.25);
		ItemEntity entity = new ItemEntity(level, x, y, z, remainderStack);
		entity.setDeltaMovement(direction.getStepX() * 0.08F, 0.25F, direction.getStepZ() * 0.08F);
		level.addFreshEntity(entity);
	}

	@Override
	public int getContainerSize() {
		return 10;
	}

	@Override
	public ItemStack getItem(int index) {
		return inventory.get(index);
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}


	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		return ContainerHelper.takeItem(this.inventory, p_70304_1_);
	}


	@Override
	public void setItem(int index, ItemStack stack) {
		inventory.set(index, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}

		this.setChanged();
	}

	@Override
	public ItemStack removeItem(int p_59613_, int p_59614_) {
		ItemStack itemstack = ContainerHelper.removeItem(this.inventory, p_59613_, p_59614_);
		if (!itemstack.isEmpty()) {
			this.setChanged();
		}

		return itemstack;
	}

	@Override
	public boolean stillValid(Player p_18946_) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return p_18946_.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	public NonNullList<ItemStack> getInventory() {
		return inventory;
	}


	@Override
	public void saveAdditional(ValueOutput cmp) {
		super.saveAdditional(cmp);
		ContainerHelper.saveAllItems(cmp, this.inventory);
		cmp.putInt("progress", this.progress);
		cmp.putInt("progress_max", this.progressMax);
		cmp.putInt("RefreshTime", this.refreshTime);
		if (this.recipeDisplay != null) {
			cmp.store("saved_recipe_display", RecipeDisplay.CODEC, this.recipeDisplay);
		}
	}

	@Override
	public void loadAdditional(ValueInput cmp) {
		super.loadAdditional(cmp);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

		this.progress = cmp.getIntOr("progress", 0);
		this.progressMax = cmp.getIntOr("progress_max", 0);
		this.refreshTime = cmp.getIntOr("RefreshTime", 0);
		this.recipeDisplay = cmp.read("saved_recipe_display", RecipeDisplay.CODEC).orElse(null);
	}

	@Override
	public int[] getSlotsForFace(Direction p_58363_) {
		if (p_58363_ == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return SLOTS_FOR_UP;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int p_58336_, ItemStack p_58337_, @javax.annotation.Nullable Direction p_58338_) {
		return this.canPlaceItem(p_58336_, p_58337_);
	}

	@Override
	public boolean canTakeItemThroughFace(int p_58392_, ItemStack p_58393_, Direction p_58394_) {
		return true;
	}

	@Override
	public boolean canPlaceItem(int p_58389_, ItemStack p_58390_) {
		if (p_58389_ == 9) {
			return false;
		} else {
			return recipeDisplay == null || resolveWithRecipePlace(p_58389_, p_58390_);
		}
	}

	private boolean smallerStackExistWithGrid(int p_307396_, ItemStack p_307520_, int slot, int width, int height) {
		for (int i = slot + 1; i < 9; ++i) {
			//check if Slot is outbound than recipe grid

			ItemStack itemstack = this.getItem(i);
			if (itemstack.isEmpty() || itemstack.getCount() < p_307396_ && ItemStack.isSameItemSameComponents(itemstack, p_307520_)) {
				return true;
			}
		}

		return false;
	}

	private boolean smallerStackExist(int p_307396_, ItemStack p_307520_, int slot) {
		for (int i = slot + 1; i < 9; ++i) {
			ItemStack itemstack = this.getItem(i);
			if (itemstack.isEmpty() || itemstack.getCount() < p_307396_ && ItemStack.isSameItemSameComponents(itemstack, p_307520_)) {
				return true;
			}
		}

		return false;
	}

	protected boolean fillRecipe(int slotIndex, ItemStack slotItem, RecipeDisplay recipeDisplay, ContextMap contextMap) {
		final boolean[] flag = {false};

		switch (recipeDisplay) {
			case ShapedCraftingRecipeDisplay shapedcraftingrecipedisplay:
				PlaceRecipeHelper.placeRecipe(
						3,
						3,
						shapedcraftingrecipedisplay.width(),
						shapedcraftingrecipedisplay.height(),
						shapedcraftingrecipedisplay.ingredients(),
						(p_380786_, p_380787_, p_380788_, p_380789_) -> {
							List<ItemStack> list = p_380786_.resolveForStacks(contextMap);
							if (!list.isEmpty() && list.stream().anyMatch(stack -> {
								return ItemStack.isSameItemSameComponents(stack, slotItem);
							})) {
								if (p_380787_ == slotIndex) {
									flag[0] = true;
								}
							}
						}
				);
				break;
			case ShapelessCraftingRecipeDisplay shapelesscraftingrecipedisplay:
				label15:
				{
					int i = Math.min(shapelesscraftingrecipedisplay.ingredients().size(), 9);

					for (int j = 0; j < i; j++) {
						List<ItemStack> list = shapelesscraftingrecipedisplay.ingredients().get(j).resolveForStacks(contextMap);
						if (!list.isEmpty() && list.stream().anyMatch(stack -> {
							return ItemStack.isSameItemSameComponents(stack, slotItem);
						})) {
							if (j == slotIndex) {
								flag[0] = true;
							}
						}
					}
					break label15;
				}
			default:
		}
		return flag[0];
	}

	private boolean resolveWithRecipePlace(int slot, ItemStack stack) {
		if (level == null || this.recipeDisplay == null) {
			return false;
		}

		ContextMap contextMap = SlotDisplayContext.fromLevel(this.level);

		if (fillRecipe(slot, stack, this.recipeDisplay, contextMap)) {
			ItemStack itemstack = this.inventory.get(slot);
			int i = itemstack.getCount();
			if (i >= itemstack.getMaxStackSize()) {
				return false;
			} else {
				return itemstack.isEmpty() || !this.smallerStackExist(i, itemstack, slot);
			}
		}
		return false;
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
	}


	@Override
	public void setRecipeUsed(@javax.annotation.Nullable RecipeHolder<?> p_301245_) {
	}

	@javax.annotation.Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void awardUsedRecipes(Player p_58396_, List<ItemStack> p_282202_) {
	}


	@Override
	protected void applyImplicitComponents(DataComponentGetter p_397929_) {
		super.applyImplicitComponents(p_397929_);
		p_397929_.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getInventory());

	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder p_338252_) {
		super.collectImplicitComponents(p_338252_);
		p_338252_.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getInventory()));
	}


	@Override
	public void removeComponentsFromTag(ValueOutput p_331127_) {
		super.removeComponentsFromTag(p_331127_);
		p_331127_.discard("Items");
		p_331127_.discard("progress");
		p_331127_.discard("progress_max");
		p_331127_.discard("RefreshTime");
		p_331127_.discard("RecipesUsed");
		p_331127_.discard("saved_recipe");
		p_331127_.discard("saved_recipe_display");
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.tofucraft.tf_crafting_table.name");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		return new TFCraftingTableMenu(p_39954_, p_39955_, this, this.dataAccess);
	}

	@Override
	public void fillStackedContents(StackedItemContents stackedItemContents) {
		for (ItemStack itemstack : this.inventory) {
			stackedItemContents.accountSimpleStack(itemstack);
		}
	}

	@Override
	@javax.annotation.Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider p_323910_) {
		return saveCustomOnly(p_323910_);
	}

	@Override
	public void onDataPacket(Connection net, ValueInput valueInput) {
		super.onDataPacket(net, valueInput);
		loadAdditional(valueInput);
	}

	public void inventoryChanged() {
		super.setChanged();
		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
	}
}
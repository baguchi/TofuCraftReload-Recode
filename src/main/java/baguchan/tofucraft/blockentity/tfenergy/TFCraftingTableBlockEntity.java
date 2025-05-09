package baguchan.tofucraft.blockentity.tfenergy;

import baguchan.tofucraft.block.utils.TofuPotBlock;
import baguchan.tofucraft.blockentity.tfenergy.base.WorkerBaseBlockEntity;
import baguchan.tofucraft.inventory.TFCraftingTableMenu;
import baguchan.tofucraft.recipe.TFCraftingRecipe;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT;

public class TFCraftingTableBlockEntity extends WorkerBaseBlockEntity implements WorldlyContainer, StackedContentsCompatible, RecipeCraftingHolder, MenuProvider {

	protected NonNullList<ItemStack> inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	private int progress = 0;
	private int progressMax = 0;

	private int refreshTime = 0;
	public static final int MAX_CRAFT_TIME = 200;
	private final RecipeManager.CachedCheck<CraftingInput, ? extends TFCraftingRecipe> quickCheck;

	private static final int[] SLOTS_FOR_UP = new int[]{0};
	private static final int[] SLOTS_FOR_DOWN = new int[]{1};

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
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFCraftingTableBlockEntity tfoven) {
		if (level.isClientSide()) return;

		boolean worked = false;
		if (tfoven.getEnergyStored() > 0) {
			if (tfoven.refreshTime <= 0) {

				Optional<? extends RecipeHolder<? extends TFCraftingRecipe>> optional = tfoven.quickCheck.getRecipeFor(CraftingInput.of(3, 3, tfoven.inventory), level);

				if (optional.isPresent()) {
					tfoven.progressMax = optional.get().value().getNeedTF() / 10;
					++tfoven.progress;
					if (tfoven.progress >= tfoven.progressMax) {
						tfoven.progress = 0;
						if (tfoven.burn(level.registryAccess(), optional.get(), tfoven.inventory)) {
							tfoven.setRecipeUsed(optional.get());
						}
					}
					worked = true;

					tfoven.drain(10, false);

				} else {
					tfoven.refreshTime = 30 + tfoven.level.random.nextInt(30);
				}
			} else {
				tfoven.progress = 0;
				tfoven.refreshTime--;
			}
		}

		if (blockState.getValue(LIT) != worked) {
			level.setBlock(blockPos, blockState.setValue(LIT, worked), 2);

		}
		if (worked) {
			tfoven.setChanged();
		}
	}

	private boolean burn(RegistryAccess p_266740_, @javax.annotation.Nullable RecipeHolder<? extends TFCraftingRecipe> p_300910_, NonNullList<ItemStack> p_267073_) {
		if (p_300910_ != null) {
			ItemStack itemstack1 = (p_300910_).value().assemble(CraftingInput.of(3, 3, p_267073_), p_266740_);
			ItemStack itemstack2 = p_267073_.get(9);
			if (itemstack2.isEmpty()) {
				p_267073_.set(9, itemstack1.copy());
			} else if (itemstack2.is(itemstack1.getItem())) {
				itemstack2.grow(itemstack1.getCount());
			}

			for (int i = 0; i < 9; ++i) {
				ItemStack slotStack = inventory.get(i);
				if (slotStack.hasCraftingRemainingItem()) {
					ejectIngredientRemainder(slotStack.getCraftingRemainingItem());
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
		Direction direction = getBlockState().getValue(TofuPotBlock.FACING).getCounterClockWise();
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
	public void saveAdditional(CompoundTag cmp, HolderLookup.Provider p_338445_) {
		super.saveAdditional(cmp, p_338445_);
		ContainerHelper.saveAllItems(cmp, this.inventory, p_338445_);
		cmp.putInt("progress", this.progress);
		cmp.putInt("progress_max", this.progressMax);
		cmp.putInt("RefreshTime", this.refreshTime);
	}

	@Override
	public void loadAdditional(CompoundTag cmp, HolderLookup.Provider p_338445_) {
		super.loadAdditional(cmp, p_338445_);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(cmp, this.inventory, p_338445_);

		this.progress = cmp.getInt("progress");
		this.progressMax = cmp.getInt("progress_max");
		this.refreshTime = cmp.getInt("RefreshTime");

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
		return p_58392_ == 1;
	}

	@Override
	public boolean canPlaceItem(int p_58389_, ItemStack p_58390_) {
		if (p_58389_ == 1) {
			return false;
		} else {
			return true;
		}
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
	public void fillStackedContents(StackedContents p_40281_) {
		for (ItemStack itemstack : this.inventory) {
			p_40281_.accountSimpleStack(itemstack);
		}
	}

	@Override
	protected void applyImplicitComponents(BlockEntity.DataComponentInput p_338855_) {
		super.applyImplicitComponents(p_338855_);
		p_338855_.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getInventory());
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder p_338252_) {
		super.collectImplicitComponents(p_338252_);
		p_338252_.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getInventory()));
	}


	@Override
	public void removeComponentsFromTag(CompoundTag p_331127_) {
		super.removeComponentsFromTag(p_331127_);
		p_331127_.remove("Items");
		p_331127_.remove("progress");
		p_331127_.remove("progress_max");
		p_331127_.remove("RefreshTime");
		p_331127_.remove("RecipesUsed");
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
}
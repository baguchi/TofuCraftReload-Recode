package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.block.utils.TofuPotBlock;
import baguchan.tofucraft.inventory.TofuPotMenu;
import baguchan.tofucraft.recipe.TofuPotRecipe;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipes;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TofuPotBlockEntity extends SyncedBlockEntity implements MenuProvider, RecipeHolder, Nameable, Container {
	public static final int CONTAINER_SLOT = 12;
	public static final int OUTPUT_SLOT = 13;
	public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;

	public NonNullList<ItemStack> inventory = NonNullList.withSize(14, ItemStack.EMPTY);

	private int cookTime;
	private int cookTimeTotal;
	private Component customName;

	protected final ContainerData cookingPotData;
	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

	private final RecipeManager.CachedCheck<Container, TofuPotRecipe> quickCheck;

	public FluidTank fluidTank = new FluidTank(2000) {

		@Override
		protected void onContentsChanged() {
			inventoryChanged();
		}

		public boolean isFluidValid(FluidStack stack) {
			return true;
		}
	};

	private final LazyOptional<IFluidHandler> holder;


	public TofuPotBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.TOFU_POT.get(), pos, state);
		this.cookingPotData = createIntArray();
		this.quickCheck = RecipeManager.createCheck(TofuRecipes.RECIPETYPE_TOFU_POT.get());
		this.holder = LazyOptional.of(() -> this.fluidTank);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		this.inventory = NonNullList.withSize(14, ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.inventory);

		cookTime = compound.getInt("CookTime");
		cookTimeTotal = compound.getInt("CookTimeTotal");
		if (compound.contains("CustomName", 8)) {
			customName = Component.Serializer.fromJson(compound.getString("CustomName"));
		}
		CompoundTag compoundRecipes = compound.getCompound("RecipesUsed");
		for (String key : compoundRecipes.getAllKeys()) {
			recipesUsed.put(ResourceLocation.tryParse(key), compoundRecipes.getInt(key));
		}
		this.fluidTank = this.fluidTank.readFromNBT(compound.getCompound("Tank"));
	}


	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		compound.putInt("CookTime", cookTime);
		compound.putInt("CookTimeTotal", cookTimeTotal);
		if (customName != null) {
			compound.putString("CustomName", Component.Serializer.toJson(customName));
		}
		ContainerHelper.saveAllItems(compound, this.inventory);
		CompoundTag compoundRecipes = new CompoundTag();
		recipesUsed.forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
		compound.put("RecipesUsed", compoundRecipes);
		CompoundTag tankTag = this.fluidTank.writeToNBT(new CompoundTag());

		compound.put("Tank", tankTag);
	}

	private CompoundTag writeItems(CompoundTag compound) {
		super.saveAdditional(compound);
		CompoundTag tankTag = this.fluidTank.writeToNBT(new CompoundTag());

		compound.put("Tank", tankTag);
		ContainerHelper.saveAllItems(compound, inventory);
		return compound;
	}

	public ItemStack getAsItem() {
		ItemStack stack = new ItemStack(TofuBlocks.TOFU_POT.get());
		return stack;
	}

	public static void cookingTick(Level level, BlockPos pos, BlockState state, TofuPotBlockEntity cookingPot) {
		boolean isHeated = cookingPot.isHeated(level, pos);
		boolean didInventoryChange = false;

		if (isHeated && cookingPot.hasInput()) {
			Optional<TofuPotRecipe> recipe = cookingPot.getMatchingRecipe();
			if (recipe.isPresent() && cookingPot.canCook(recipe.get()) &&
					(recipe.get().matchesWithFluid(cookingPot.fluidTank.getFluid(), cookingPot, level))) {
				didInventoryChange = cookingPot.processCooking(recipe.get(), cookingPot);
			} else {
				cookingPot.cookTime = 0;
			}
		} else if (cookingPot.cookTime > 0) {
			cookingPot.cookTime = Mth.clamp(cookingPot.cookTime - 2, 0, cookingPot.cookTimeTotal);
		}

		if (didInventoryChange) {
			cookingPot.inventoryChanged();
		}
	}


	public static void animationTick(Level level, BlockPos pos, BlockState state, TofuPotBlockEntity cookingPot) {
		if (cookingPot.isHeated(level, pos)) {
			RandomSource random = level.random;
			if (random.nextFloat() < 0.2F) {
				double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
				double y = (double) pos.getY() + 1.2D;
				double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
				level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0D, 0.0D, 0.0D);
			}
		}

	}

	private Optional<TofuPotRecipe> getMatchingRecipe() {
		if (level == null) return Optional.empty();
		return hasInput() ? quickCheck.getRecipeFor(new SimpleContainer(this.inventory.stream().limit(12).toArray(ItemStack[]::new)), this.level) : Optional.empty();
	}

	private boolean hasInput() {
		for (int i = 0; i < OUTPUT_SLOT; ++i) {
			if (!inventory.get(i).isEmpty()) return true;
		}
		return false;
	}

	protected boolean canCook(TofuPotRecipe recipe) {
		int i = this.getMaxStackSize();
		if (hasInput()) {
			ItemStack resultStack = recipe.getResultItem(this.level.registryAccess());
			if (resultStack.isEmpty()) {
				return false;
			} else {
				ItemStack storedMealStack = inventory.get(OUTPUT_SLOT);
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
		} else {
			return false;
		}
	}

	private boolean processCooking(TofuPotRecipe recipe, TofuPotBlockEntity cookingPot) {
		if (level == null) return false;

		++cookTime;
		cookTimeTotal = recipe.getCookTime();
		if (cookTime < cookTimeTotal) {
			return false;
		}

		cookTime = 0;
		ItemStack resultStack = recipe.getResultItem(this.level.registryAccess());
		ItemStack storedMealStack = inventory.get(OUTPUT_SLOT);
		if (storedMealStack.isEmpty()) {
			inventory.set(OUTPUT_SLOT, resultStack.copy());
		} else if (ItemStack.isSameItem(storedMealStack, resultStack)) {
			storedMealStack.grow(resultStack.getCount());
		}
		if (!recipe.matchesWithFluid(fluidTank.getFluid(), this, level)) {
			cookingPot.fluidTank.drain(250, IFluidHandler.FluidAction.EXECUTE);
		}

		cookingPot.setRecipeUsed(recipe);

		for (int i = 0; i < OUTPUT_SLOT; ++i) {
			ItemStack slotStack = inventory.get(i);
			if (slotStack.hasCraftingRemainingItem()) {
				ejectIngredientRemainder(slotStack.getCraftingRemainingItem());
			}
			if (!slotStack.isEmpty())
				slotStack.shrink(1);
		}
		return true;
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
	public void setRecipeUsed(@org.jetbrains.annotations.Nullable Recipe<?> recipe) {
		if (recipe != null) {
			ResourceLocation recipeID = recipe.getId();
			recipesUsed.addTo(recipeID, 1);
		}
	}

	@Nullable
	public Recipe<?> getRecipeUsed() {
		return null;
	}

	public void awardUsedRecipesAndPopExperience(ServerPlayer p_155004_) {
		List<Recipe<?>> list = this.getRecipesToAwardAndPopExperience(p_155004_.serverLevel(), p_155004_.position());
		p_155004_.awardRecipes(list);

		for (Recipe<?> recipe : list) {
			if (recipe != null) {
				p_155004_.triggerRecipeCrafted(recipe, this.inventory);
			}
		}

		this.recipesUsed.clear();
	}

	public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel p_154996_, Vec3 p_154997_) {
		List<Recipe<?>> list = Lists.newArrayList();

		for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
			p_154996_.getRecipeManager().byKey(entry.getKey()).ifPresent((p_155023_) -> {
				list.add(p_155023_);
				createExperience(p_154996_, p_154997_, entry.getIntValue(), ((TofuPotRecipe) p_155023_).getExperience());
			});
		}

		return list;
	}

	private static void createExperience(ServerLevel p_154999_, Vec3 p_155000_, int p_155001_, float p_155002_) {
		int i = Mth.floor((float) p_155001_ * p_155002_);
		float f = Mth.frac((float) p_155001_ * p_155002_);
		if (f != 0.0F && Math.random() < (double) f) {
			++i;
		}

		ExperienceOrb.award(p_154999_, p_155000_, i);
	}


	public boolean isHeated() {
		if (level == null) return false;
		return this.isHeated(level, worldPosition);
	}

	private boolean isHeated(Level level, BlockPos worldPosition) {
		return level.getBlockState(worldPosition.below()).is(BlockTags.FIRE) || level.getBlockState(worldPosition.below()).is(BlockTags.CAMPFIRES);
	}

	public NonNullList<ItemStack> getInventory() {
		return inventory;
	}

	public NonNullList<ItemStack> getDroppableInventory() {
		NonNullList<ItemStack> drops = NonNullList.create();
		for (int i = 0; i < INVENTORY_SIZE; ++i) {
			if (i != OUTPUT_SLOT) {
				drops.add(inventory.get(i));
			}
		}
		return drops;
	}


	@Override
	public Component getName() {
		return customName != null ? customName : Component.translatable("container.tofucraft.tofu_pot");
	}

	@Override
	public Component getDisplayName() {
		return getName();
	}

	@Override
	@Nullable
	public Component getCustomName() {
		return customName;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
		return new TofuPotMenu(id, player, this, cookingPotData);
	}


	@Override
	public CompoundTag getUpdateTag() {
		return writeItems(new CompoundTag());
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(INVENTORY_SIZE) {
			@Override
			protected void onContentsChanged(int slot) {
				inventoryChanged();
			}
		};
	}

	private ContainerData createIntArray() {
		return new ContainerData() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> TofuPotBlockEntity.this.cookTime;
					case 1 -> TofuPotBlockEntity.this.cookTimeTotal;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> TofuPotBlockEntity.this.cookTime = value;
					case 1 -> TofuPotBlockEntity.this.cookTimeTotal = value;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		};
	}

	@Override
	public int getContainerSize() {
		return INVENTORY_SIZE;
	}

	@Override
	public boolean isEmpty() {
		return this.inventory.isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.inventory.get(slot);
	}

	@Override
	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		return ContainerHelper.removeItem(this.inventory, p_70298_1_, p_70298_2_);
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		return ContainerHelper.takeItem(this.inventory, p_70304_1_);
	}


	@Override
	public void setItem(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
	}

	@Override
	public boolean stillValid(Player p_70300_1_) {
		return Container.stillValidBlockEntity(this, p_70300_1_);
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
	}

	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (capability == ForgeCapabilities.FLUID_HANDLER) {
			if (facing == Direction.UP) {
				return this.holder.cast();
			} else {
				return this.holder.cast();
			}
		}

		return super.getCapability(capability, facing);
	}

	public void invalidateCaps() {
		super.invalidateCaps();

		this.holder.invalidate();
	}

}
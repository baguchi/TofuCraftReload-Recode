package baguchi.tofucraft.blockentity;

import baguchi.tofucraft.block.TofuPotBlock;
import baguchi.tofucraft.blockentity.fluid.FluidContainer;
import baguchi.tofucraft.inventory.TofuPotMenu;
import baguchi.tofucraft.recipe.TofuPotRecipe;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuRecipes;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class TofuPotBlockEntity extends SyncedBlockEntity implements MenuProvider, Nameable, RecipeCraftingHolder, Container, StackedContentsCompatible {
	private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> RECIPES_USED_CODEC = Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
	private static final Codec<ResourceKey<Recipe<?>>> RECIPE_CODEC = ResourceKey.codec(Registries.RECIPE);

	public static final int CONTAINER_SLOT = 12;
	public static final int OUTPUT_SLOT = 13;
	public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;

	public NonNullList<ItemStack> inventory = NonNullList.withSize(14, ItemStack.EMPTY);

	private int cookTime;
	private int cookTimeTotal;
	private Component customName;

	protected final ContainerData cookingPotData;
	private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();

	private final RecipeManager.CachedCheck<CraftingInput, TofuPotRecipe> quickCheck;
	public FluidContainer fluidTank = new FluidContainer(3000) {

		@Override
		protected void onContentsChanged() {
			inventoryChanged();
		}
	};

	public TofuPotBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.TOFU_POT.get(), pos, state);
		this.cookingPotData = createIntArray();
		this.quickCheck = RecipeManager.createCheck(TofuRecipes.RECIPETYPE_TOFU_POT.get());
	}

	@Override
	public void loadAdditional(ValueInput compound) {
		super.loadAdditional(compound);
		this.inventory = NonNullList.withSize(14, ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.inventory);

		cookTime = compound.getIntOr("CookTime", 0);
		cookTimeTotal = compound.getIntOr("CookTimeTotal", 0);
		this.customName = parseCustomNameSafe(compound, "CustomName");
		this.recipesUsed.clear();
		this.recipesUsed.putAll(compound.read("RecipesUsed", RECIPES_USED_CODEC).orElse(Map.of()));

		this.fluidTank.deserialize(compound.childOrEmpty("Tank"));
	}

	@Override
	public void saveAdditional(ValueOutput compound) {
		super.saveAdditional(compound);
		compound.putInt("CookTime", cookTime);
		compound.putInt("CookTimeTotal", cookTimeTotal);
		compound.storeNullable("CustomName", ComponentSerialization.CODEC, this.customName);
		ContainerHelper.saveAllItems(compound, this.inventory);

		compound.store("RecipesUsed", RECIPES_USED_CODEC, this.recipesUsed);
		this.fluidTank.serialize(compound.child("Tank"));
	}

	public ItemStack getAsItem() {
		ItemStack stack = new ItemStack(TofuBlocks.TOFU_POT.get());
		stack.applyComponents(collectComponents());
		return stack;
	}

	public static void cookingTick(Level level, BlockPos pos, BlockState state, TofuPotBlockEntity cookingPot) {
		boolean isHeated = cookingPot.isHeated(level, pos);
		boolean didInventoryChange = false;

		if (isHeated && cookingPot.hasInput()) {
			Optional<RecipeHolder<TofuPotRecipe>> recipe = cookingPot.getMatchingRecipe(CraftingInput.of(4, 3, cookingPot.inventory));

			if (recipe.isPresent() && cookingPot.canCook(recipe.get().value()) && (recipe.get().value().fluidIngredient().isEmpty() || recipe.get().value().fluidIngredient().get().test(new FluidStack(cookingPot.fluidTank.getResource(0).getFluid(), cookingPot.fluidTank.getAmountAsInt(0))))) {
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
			RandomSource random = level.getRandom();
			if (random.nextFloat() < 0.2F) {
				double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
				double y = (double) pos.getY() + 1.2D;
				double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
				level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0D, 0.0D, 0.0D);
			}
		}

	}

	private Optional<RecipeHolder<TofuPotRecipe>> getMatchingRecipe(CraftingInput inventoryWrapper) {
		if (level == null) return Optional.empty();
		if (level instanceof ServerLevel serverLevel) {
			return hasInput() ? quickCheck.getRecipeFor(inventoryWrapper, serverLevel) : Optional.empty();
		}
		return Optional.empty();
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
			ItemStack resultStack = recipe.getResult();
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

	private boolean processCooking(RecipeHolder<TofuPotRecipe> recipe, TofuPotBlockEntity cookingPot) {
		if (level == null) return false;

		++cookTime;
		cookTimeTotal = recipe.value().getCookTime();
		if (cookTime < cookTimeTotal) {
			return false;
		}

		cookTime = 0;
		ItemStack resultStack = recipe.value().getResult();
		ItemStack storedMealStack = inventory.get(OUTPUT_SLOT);
		if (storedMealStack.isEmpty()) {
			inventory.set(OUTPUT_SLOT, resultStack.copy());
		} else if (ItemStack.isSameItem(storedMealStack, resultStack)) {
			storedMealStack.grow(resultStack.getCount());
		}
		if (recipe.value().fluidIngredient().isPresent()) {
			try (Transaction tx = Transaction.openRoot()) {
				cookingPot.fluidTank.extract(this.fluidTank.getResource(0), recipe.value().fluidIngredient().get().amount(), tx);
				tx.commit();
			}
		}

		cookingPot.setRecipeUsed(recipe);

		for (int i = 0; i < OUTPUT_SLOT; ++i) {
			ItemStack slotStack = inventory.get(i);
			if (!slotStack.getCraftingRemainder().isEmpty()) {
				ejectIngredientRemainder(slotStack.getCraftingRemainder().copy());
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
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceKey<Recipe<?>> recipeID = recipe.id();
			recipesUsed.addTo(recipeID, 1);
		}
	}

	@Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void awardUsedRecipes(Player player, List<ItemStack> items) {
		recipesUsed.clear();
	}

	public void awardUsedRecipesAndPopExperience(ServerPlayer p_155004_) {
		List<RecipeHolder<?>> list = this.getUsedRecipesAndPopExperience(p_155004_.level(), p_155004_.position());
		p_155004_.awardRecipes(list);

		for (RecipeHolder<?> recipeholder : list) {
			if (recipeholder != null) {
				p_155004_.triggerRecipeCrafted(recipeholder, this.inventory);
			}
		}

		this.recipesUsed.clear();
	}

	public List<RecipeHolder<?>> getUsedRecipesAndPopExperience(ServerLevel level, Vec3 pos) {
		List<RecipeHolder<?>> list = Lists.newArrayList();

		for (Reference2IntMap.Entry<ResourceKey<Recipe<?>>> entry : this.recipesUsed.reference2IntEntrySet()) {
			level.recipeAccess().byKey(entry.getKey()).ifPresent(p_379268_ -> {
				list.add(p_379268_);
				splitAndSpawnExperience(level, pos, entry.getIntValue(), ((TofuPotRecipe) p_379268_.value()).getExperience());
			});
		}

		return list;
	}

	private static void splitAndSpawnExperience(ServerLevel level, Vec3 pos, int craftedAmount, float experience) {
		int expTotal = Mth.floor((float) craftedAmount * experience);
		float expFraction = Mth.frac((float) craftedAmount * experience);
		if (expFraction != 0.0F && Math.random() < (double) expFraction) {
			++expTotal;
		}

		ExperienceOrb.award(level, pos, expTotal);
	}

	@Override
	public void preRemoveSideEffects(BlockPos p_393693_, BlockState p_393780_) {
		super.preRemoveSideEffects(p_393693_, p_393780_);
		if (this.level instanceof ServerLevel serverlevel) {
			this.getUsedRecipesAndPopExperience(serverlevel, Vec3.atCenterOf(p_393693_));
		}
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
	public CompoundTag getUpdateTag(HolderLookup.Provider p_324313_) {
		return this.saveCustomOnly(p_324313_);
	}
	@Override
	protected void applyImplicitComponents(DataComponentGetter p_397929_) {
		super.applyImplicitComponents(p_397929_);
		this.customName = p_397929_.get(DataComponents.CUSTOM_NAME);
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder components) {
		super.collectImplicitComponents(components);
		components.set(DataComponents.CUSTOM_NAME, this.customName);
	}


	@Override
	public void removeComponentsFromTag(ValueOutput p_422208_) {
		super.removeComponentsFromTag(p_422208_);
		p_422208_.discard("CustomName");
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

	@Override
	public void fillStackedContents(StackedItemContents stackedItemContents) {
		for (ItemStack itemstack : this.inventory) {
			stackedItemContents.accountSimpleStack(itemstack);
		}
	}
}
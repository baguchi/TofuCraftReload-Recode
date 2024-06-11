package baguchan.tofucraft.blockentity.tfenergy;

import baguchan.tofucraft.blockentity.tfenergy.base.WorkerBaseBlockEntity;
import baguchan.tofucraft.inventory.TFOvenMenu;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT;

public class TFOvenBlockEntity extends WorkerBaseBlockEntity implements WorldlyContainer, StackedContentsCompatible, RecipeCraftingHolder, MenuProvider {

	protected NonNullList<ItemStack> inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	private int progress = 0;

	private int refreshTime = 0;
	public static final int MAX_CRAFT_TIME = 200;
	private final RecipeType<? extends SmeltingRecipe> recipeType;

	private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends Recipe> quickCheck;

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();


	private static final int[] SLOTS_FOR_UP = new int[]{0};
	private static final int[] SLOTS_FOR_DOWN = new int[]{1};

	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
				case 0:
					return TFOvenBlockEntity.this.progress;
				case 1:
					return TFOvenBlockEntity.this.energy;
				case 2:
					return TFOvenBlockEntity.this.energyMax;
			}
			return 0;
		}

		@Override
		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
				case 0:
					TFOvenBlockEntity.this.progress = p_221477_2_;
					break;
				case 1:
					TFOvenBlockEntity.this.energy = p_221477_2_;
					break;
				case 2:
					TFOvenBlockEntity.this.energyMax = p_221477_2_;
					break;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	};

	public TFOvenBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_OVEN.get(), p_155229_, p_155230_, 5000);
		this.recipeType = RecipeType.SMELTING;
		this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFOvenBlockEntity tfoven) {
		if (level.isClientSide()) return;

		boolean worked = false;
			if (tfoven.getEnergyStored() > 0) {
				if (tfoven.refreshTime <= 0) {

					Optional<? extends RecipeHolder<? extends Recipe>> optional = tfoven.quickCheck.getRecipeFor(new SingleRecipeInput(tfoven.inventory.get(0)), level);

					if (optional.isPresent()) {
						++tfoven.progress;
						if (tfoven.progress == MAX_CRAFT_TIME) {
							tfoven.progress = 0;
							if (tfoven.burn(level.registryAccess(), optional.get(), tfoven.inventory)) {
								tfoven.setRecipeUsed(optional.get());
							}
						}
						worked = true;

						tfoven.drain(20, false);

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

	private boolean burn(RegistryAccess p_266740_, @javax.annotation.Nullable RecipeHolder<?> p_300910_, NonNullList<ItemStack> p_267073_) {
		if (p_300910_ != null) {
			ItemStack itemstack = p_267073_.get(0);
			ItemStack itemstack1 = ((RecipeHolder<net.minecraft.world.item.crafting.Recipe<SingleRecipeInput>>) p_300910_).value().assemble(new SingleRecipeInput(p_267073_.get(0)), p_266740_);
			ItemStack itemstack2 = p_267073_.get(1);
			if (itemstack2.isEmpty()) {
				p_267073_.set(1, itemstack1.copy());
			} else if (itemstack2.is(itemstack1.getItem())) {
				itemstack2.grow(itemstack1.getCount());
			}

			itemstack.shrink(1);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getContainerSize() {
		return 2;
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
		cmp.putInt("RefreshTime", this.refreshTime);
		CompoundTag compoundtag = new CompoundTag();
		this.recipesUsed.forEach((p_187449_, p_187450_) -> compoundtag.putInt(p_187449_.toString(), p_187450_));
		cmp.put("RecipesUsed", compoundtag);
	}

	@Override
	public void loadAdditional(CompoundTag cmp, HolderLookup.Provider p_338445_) {
		super.loadAdditional(cmp, p_338445_);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(cmp, this.inventory, p_338445_);

		this.progress = cmp.getInt("progress");
		this.refreshTime = cmp.getInt("RefreshTime");
		CompoundTag compoundtag = cmp.getCompound("RecipesUsed");

		for (String s : compoundtag.getAllKeys()) {
			this.recipesUsed.put(ResourceLocation.parse(s), compoundtag.getInt(s));
		}
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
		if (p_58394_ == Direction.DOWN && p_58392_ == 1) {
			return p_58393_.is(Items.WATER_BUCKET) || p_58393_.is(Items.BUCKET);
		} else {
			return true;
		}
	}

	@Override
	public boolean canPlaceItem(int p_58389_, ItemStack p_58390_) {
		if (p_58389_ == 1) {
			return false;
		} else {
			ItemStack itemstack = this.inventory.get(0);
			return p_58390_.getBurnTime(this.recipeType) > 0 || p_58390_.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
		}
	}
	@Override
	public void clearContent() {
		this.inventory.clear();
	}


	@Override
	public void setRecipeUsed(@javax.annotation.Nullable RecipeHolder<?> p_301245_) {
		if (p_301245_ != null) {
			ResourceLocation resourcelocation = p_301245_.id();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	@javax.annotation.Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void awardUsedRecipes(Player p_58396_, List<ItemStack> p_282202_) {
	}

	public void awardUsedRecipesAndPopExperience(ServerPlayer p_155004_) {
		List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(p_155004_.serverLevel(), p_155004_.position());
		p_155004_.awardRecipes(list);

		for (RecipeHolder<?> recipeholder : list) {
			if (recipeholder != null) {
				p_155004_.triggerRecipeCrafted(recipeholder, this.inventory);
			}
		}

		this.recipesUsed.clear();
	}

	public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel p_154996_, Vec3 p_154997_) {
		List<RecipeHolder<?>> list = Lists.newArrayList();

		for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
			p_154996_.getRecipeManager().byKey(entry.getKey()).ifPresent(p_300839_ -> {
				list.add(p_300839_);
				createExperience(p_154996_, p_154997_, entry.getIntValue(), ((AbstractCookingRecipe) p_300839_.value()).getExperience());
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

	@Override
	public void fillStackedContents(StackedContents p_40281_) {
		for (ItemStack itemstack : this.inventory) {
			p_40281_.accountSimpleStack(itemstack);
		}
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.tofucraft.tfoven.name");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		return new TFOvenMenu(p_39954_, p_39955_, this, this.dataAccess);
	}
}

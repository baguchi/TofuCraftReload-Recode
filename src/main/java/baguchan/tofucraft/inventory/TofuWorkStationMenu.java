package baguchan.tofucraft.inventory;

import baguchan.tofucraft.recipe.TofuWorkStationRecipe;
import baguchan.tofucraft.registry.TofuMenus;
import baguchan.tofucraft.registry.TofuRecipes;
import com.google.common.collect.Lists;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class TofuWorkStationMenu extends AbstractContainerMenu {
	public static final int INPUT_SLOT = 0;
	public static final int RESULT_SLOT = 1;
	private static final int INV_SLOT_START = 2;
	private static final int INV_SLOT_END = 29;
	private static final int USE_ROW_SLOT_START = 29;
	private static final int USE_ROW_SLOT_END = 38;
	private final ContainerLevelAccess access;
	private final DataSlot selectedRecipeIndex = DataSlot.standalone();
	private final Level level;
	private List<RecipeHolder<TofuWorkStationRecipe>> recipes = Lists.newArrayList();
	private ItemStack input = ItemStack.EMPTY;
	long lastSoundTime;
	final Slot inputSlot;
	final Slot inputSlot2;
	final Slot inputSlot3;
	final Slot resultSlot;
	Runnable slotUpdateListener = () -> {
	};
	public final Container container = new SimpleContainer(1) {
		public void setChanged() {
			super.setChanged();
			TofuWorkStationMenu.this.slotsChanged(this);
			TofuWorkStationMenu.this.slotUpdateListener.run();
		}
	};
	final ResultContainer resultContainer = new ResultContainer();

	public TofuWorkStationMenu(int p_40294_, Inventory p_40295_) {
		this(p_40294_, p_40295_, ContainerLevelAccess.NULL);
	}

	public TofuWorkStationMenu(int p_40297_, Inventory p_40298_, final ContainerLevelAccess p_40299_) {
		super(TofuMenus.TOFU_WORK_STATION.get(), p_40297_);
		this.access = p_40299_;
		this.level = p_40298_.player.level();
		this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33 - 18) {
			@Override
			public void set(ItemStack p_40240_) {
				super.set(p_40240_);
				TofuWorkStationMenu.this.setupResultSlot();
			}
		});
		this.inputSlot2 = this.addSlot(new Slot(this.container, 1, 20, 33) {
			@Override
			public void set(ItemStack p_40240_) {
				super.set(p_40240_);
				TofuWorkStationMenu.this.setupResultSlot();
			}
		});
		this.inputSlot3 = this.addSlot(new Slot(this.container, 2, 20, 33 + 18));
		this.resultSlot = this.addSlot(new Slot(this.resultContainer, 3, 143, 33) {
			public boolean mayPlace(ItemStack p_40362_) {
				return false;
			}

			public void onTake(Player p_150672_, ItemStack p_150673_) {
				p_150673_.onCraftedBy(p_150672_.level(), p_150672_, p_150673_.getCount());
				TofuWorkStationMenu.this.resultContainer.awardUsedRecipes(p_150672_, this.getRelevantItems());
				ItemStack itemstack = TofuWorkStationMenu.this.inputSlot.remove(1);
				TofuWorkStationMenu.this.inputSlot2.remove(1);
				TofuWorkStationMenu.this.inputSlot3.remove(1);
				if (!itemstack.isEmpty()) {
					TofuWorkStationMenu.this.setupResultSlot();
				}

				p_40299_.execute((p_40364_, p_40365_) -> {
					long l = p_40364_.getGameTime();
					if (TofuWorkStationMenu.this.lastSoundTime != l) {
						p_40364_.playSound((Player) null, p_40365_, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
						TofuWorkStationMenu.this.lastSoundTime = l;
					}

				});
				super.onTake(p_150672_, p_150673_);
			}

			private List<ItemStack> getRelevantItems() {
				return List.of(TofuWorkStationMenu.this.inputSlot.getItem());
			}
		});

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(p_40298_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(p_40298_, k, 8 + k * 18, 142));
		}

		this.addDataSlot(this.selectedRecipeIndex);
	}

	public int getSelectedRecipeIndex() {
		return this.selectedRecipeIndex.get();
	}

	public List<RecipeHolder<TofuWorkStationRecipe>> getRecipes() {
		return this.recipes;
	}

	public int getNumRecipes() {
		return this.recipes.size();
	}

	public boolean hasInputItem() {
		return this.inputSlot.hasItem() && !this.recipes.isEmpty();
	}

	public boolean stillValid(Player p_40307_) {
		return stillValid(this.access, p_40307_, Blocks.STONECUTTER);
	}

	public boolean clickMenuButton(Player p_40309_, int p_40310_) {
		if (this.isValidRecipeIndex(p_40310_)) {
			this.selectedRecipeIndex.set(p_40310_);
			this.setupResultSlot();
		}

		return true;
	}

	private boolean isValidRecipeIndex(int p_40335_) {
		return p_40335_ >= 0 && p_40335_ < this.recipes.size();
	}

	public void slotsChanged(Container p_40302_) {
		ItemStack itemstack = this.inputSlot.getItem();
		if (!itemstack.is(this.input.getItem())) {
			this.input = itemstack.copy();
			this.setupRecipeList(p_40302_, itemstack);
		}

	}

	private void setupRecipeList(Container p_40304_, ItemStack p_40305_) {
		this.recipes.clear();
		this.selectedRecipeIndex.set(-1);
		this.resultSlot.set(ItemStack.EMPTY);
		if (!p_40305_.isEmpty()) {
			this.recipes = this.level.getRecipeManager().getRecipesFor(TofuRecipes.RECIPETYPE_TOFU_WORK_STATION.get(), p_40304_, this.level);
		}

	}

	void setupResultSlot() {
		if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
			RecipeHolder<TofuWorkStationRecipe> recipeholder = this.recipes.get(this.selectedRecipeIndex.get());
			ItemStack itemstack = recipeholder.value().assemble(this.container, this.level.registryAccess());
			if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
				this.resultContainer.setRecipeUsed(recipeholder);
				this.resultSlot.set(itemstack);
			} else {
				this.resultSlot.set(ItemStack.EMPTY);
			}
		} else {
			this.resultSlot.set(ItemStack.EMPTY);
		}

		this.broadcastChanges();
	}

	public MenuType<?> getType() {
		return MenuType.STONECUTTER;
	}

	public void registerUpdateListener(Runnable p_40324_) {
		this.slotUpdateListener = p_40324_;
	}

	public boolean canTakeItemForPickAll(ItemStack p_40321_, Slot p_40322_) {
		return p_40322_.container != this.resultContainer && super.canTakeItemForPickAll(p_40321_, p_40322_);
	}

	public ItemStack quickMoveStack(Player p_40328_, int p_40329_) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(p_40329_);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			if (p_40329_ == 3) {
				item.onCraftedBy(itemstack1, p_40328_.level(), p_40328_);
				if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (p_40329_ == 0 || p_40329_ == 1 || p_40329_ == 2) {
				if (!this.moveItemStackTo(itemstack1, 4, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (this.level.getRecipeManager().getRecipeFor(TofuRecipes.RECIPETYPE_TOFU_WORK_STATION.get(), new SimpleContainer(itemstack1), this.level).isPresent()) {
				if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (p_40329_ >= 5 && p_40329_ < 29) {
				if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (p_40329_ >= 29 && p_40329_ < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			}

			slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(p_40328_, itemstack1);
			this.broadcastChanges();
		}

		return itemstack;
	}

	public void removed(Player p_40326_) {
		super.removed(p_40326_);
		this.resultContainer.removeItemNoUpdate(1);
		this.access.execute((p_40313_, p_40314_) -> {
			this.clearContainer(p_40326_, this.container);
		});
	}
}
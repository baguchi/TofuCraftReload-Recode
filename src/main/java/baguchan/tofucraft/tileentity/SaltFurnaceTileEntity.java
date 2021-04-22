package baguchan.tofucraft.tileentity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.utils.SaltFurnaceBlock;
import baguchan.tofucraft.inventory.SaltFurnaceContainer;
import baguchan.tofucraft.message.SaltFurnaceBitternMessage;
import baguchan.tofucraft.message.SaltFurnaceWaterMessage;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTileEntitys;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class SaltFurnaceTileEntity extends LockableTileEntity implements ITickableTileEntity, ISidedInventory, INamedContainerProvider {
	private static final int[] SLOTS_FOR_DOWN = new int[]{3, 1};

	private static final int[] SLOTS_FOR_SIDES = new int[]{0, 2};

	protected NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

	public FluidTank waterTank = new FluidTank(3000) {
		public boolean isFluidValid(FluidStack stack) {
			return (stack.getFluid() == Fluids.WATER);
		}
	};

	public FluidTank bitternTank = new FluidTank(2000) {
		public boolean isFluidValid(FluidStack stack) {
			return (stack.getFluid() == TofuFluids.BITTERN);
		}
	};

	private int litTime;

	private int litDuration;

	private int cookingProgress;

	private int cookingTotalTime;

	private int prevWaterFluid;

	private int prevBitternFluid;

	protected final IIntArray dataAccess = new IIntArray() {
		@Override
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
				case 0:
					return SaltFurnaceTileEntity.this.litTime;
				case 1:
					return SaltFurnaceTileEntity.this.litDuration;
				case 2:
					return SaltFurnaceTileEntity.this.cookingProgress;
				case 3:
					return SaltFurnaceTileEntity.this.cookingTotalTime;
			}
			return 0;
		}

		@Override
		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
				case 0:
					SaltFurnaceTileEntity.this.litTime = p_221477_2_;
					break;
				case 1:
					SaltFurnaceTileEntity.this.litDuration = p_221477_2_;
					break;
				case 2:
					SaltFurnaceTileEntity.this.cookingProgress = p_221477_2_;
					break;
				case 3:
					SaltFurnaceTileEntity.this.cookingTotalTime = p_221477_2_;
					break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	LazyOptional<? extends IItemHandler>[] handlers;

	private final LazyOptional<IFluidHandler> holder;

	public SaltFurnaceTileEntity() {
		super(TofuTileEntitys.SALT_FURNACE);
		this
				.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
		this.holder = LazyOptional.of(() -> this.waterTank);
	}

	public SaltFurnaceTileEntity(TileEntityType<?> p_i49964_1_) {
		super(p_i49964_1_);
		this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
		this.holder = LazyOptional.of(() -> this.waterTank);
	}

	private boolean isLit() {
		return (this.litTime > 0);
	}

	public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.load(p_230337_1_, p_230337_2_);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		if (p_230337_2_.contains("WaterTank", 10)) {
			CompoundNBT nbt = p_230337_2_.getCompound("WaterTank");
			this.waterTank.readFromNBT(nbt);
		}
		if (p_230337_2_.contains("BitternTank", 10)) {
			CompoundNBT nbt = p_230337_2_.getCompound("BitternTank");
			this.bitternTank.readFromNBT(nbt);
		}
		ItemStackHelper.loadAllItems(p_230337_2_, this.items);
		this.litTime = p_230337_2_.getInt("BurnTime");
		this.cookingProgress = p_230337_2_.getInt("CookTime");
		this.cookingTotalTime = p_230337_2_.getInt("CookTimeTotal");
		this.litDuration = getBurnDuration(this.items.get(1));
	}

	public CompoundNBT save(CompoundNBT p_189515_1_) {
		super.save(p_189515_1_);
		CompoundNBT nbt = new CompoundNBT();
		CompoundNBT nbt2 = new CompoundNBT();
		this.waterTank.writeToNBT(nbt);
		this.bitternTank.writeToNBT(nbt2);
		p_189515_1_.put("WaterTank", nbt);
		p_189515_1_.put("BitternTank", nbt2);
		p_189515_1_.putInt("BurnTime", this.litTime);
		p_189515_1_.putInt("CookTime", this.cookingProgress);
		p_189515_1_.putInt("CookTimeTotal", this.cookingTotalTime);
		ItemStackHelper.saveAllItems(p_189515_1_, this.items);
		return p_189515_1_;
	}

	@Override
	public void tick() {
		boolean flag = isLit();
		boolean flag1 = false;
		if (isLit())
			this.litTime--;
		if (!this.level.isClientSide) {
			if (this.prevWaterFluid != this.waterTank.getFluidAmount()) {
				Chunk chunk = this.level.getChunkAt(getBlockPos());
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new SaltFurnaceWaterMessage(getBlockPos(), this.waterTank.getFluid()));
				this.prevWaterFluid = this.waterTank.getFluidAmount();
			}
			if (this.prevBitternFluid != this.bitternTank.getFluidAmount()) {
				Chunk chunk = this.level.getChunkAt(getBlockPos());
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new SaltFurnaceBitternMessage(getBlockPos(), this.bitternTank.getFluid()));
				this.prevBitternFluid = this.bitternTank.getFluidAmount();
			}
			ItemStack itemstack = this.items.get(0);
			if (isLit() || !this.items.get(0).isEmpty()) {
				if (!isLit() && hasWater()) {
					this.litTime = getBurnDuration(itemstack);
					this.litDuration = this.litTime;
					if (isLit()) {
						flag1 = true;
						if (itemstack.hasContainerItem()) {
							this.items.set(0, itemstack.getContainerItem());
						} else if (!itemstack.isEmpty()) {
							Item item = itemstack.getItem();
							itemstack.shrink(1);
							if (itemstack.isEmpty())
								this.items.set(0, itemstack.getContainerItem());
						}
					}
				}
				if (isLit() && hasWater()) {
					this.cookingProgress++;
					if (this.cookingProgress == this.cookingTotalTime) {
						this.cookingProgress = 0;
						this.cookingTotalTime = getTotalCookTime();
						makeSalt();
						flag1 = true;
					}
				} else {
					this.cookingProgress = 0;
				}
			} else if (!isLit() && this.cookingProgress > 0) {
				this.cookingProgress = MathHelper.clamp(this.cookingProgress - 2, 0, this.cookingTotalTime);
			}
			if (flag != isLit()) {
				flag1 = true;
				this.level.setBlock(this.getBlockPos(), this.level.getBlockState(this.getBlockPos()).setValue(SaltFurnaceBlock.LIT, Boolean.valueOf(isLit())), 3);
			}
			makeBittern();
		}
		if (flag1)
			setChanged();
	}

	protected boolean hasWater() {
		boolean flag = (this.waterTank.getFluid().getFluid() == Fluids.WATER && this.waterTank.getFluid().getAmount() >= 200);
		ItemStack itemstack1 = this.items.get(1);
		if (itemstack1.isEmpty())
			return flag;
		if (itemstack1.getCount() + 2 <= getMaxStackSize() && itemstack1.getCount() + 2 <= itemstack1.getMaxStackSize())
			return flag;
		return false;
	}

	protected boolean hasBittern() {
		boolean flag = (this.bitternTank.getFluid().getFluid() == TofuFluids.BITTERN && this.bitternTank.getFluid().getAmount() >= 200);
		ItemStack itemstack1 = this.items.get(3);
		ItemStack itemstack2 = this.items.get(2);
		if (itemstack2.getItem() == Items.GLASS_BOTTLE) {
			if (itemstack1.isEmpty())
				return flag;
			if (itemstack1.getCount() + 1 <= getMaxStackSize() && itemstack1.getCount() + 1 <= itemstack1.getMaxStackSize())
				return flag;
			return false;
		}
		return false;
	}

	private void makeBittern() {
		if (hasBittern()) {
			ItemStack itemstack1 = new ItemStack(TofuItems.BITTERN, 1);
			ItemStack itemstack2 = this.items.get(3);
			ItemStack itemstack3 = this.items.get(2);
			itemstack3.shrink(1);
			if (itemstack2.isEmpty()) {
				this.items.set(3, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}
			this.bitternTank.drain(200, IFluidHandler.FluidAction.EXECUTE);
		}
	}

	private void makeSalt() {
		if (hasWater()) {
			ItemStack itemstack1 = new ItemStack(TofuItems.SALT, 2);
			ItemStack itemstack2 = this.items.get(1);
			if (itemstack2.isEmpty()) {
				this.items.set(1, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}
			this.waterTank.drain(200, IFluidHandler.FluidAction.EXECUTE);
			this.bitternTank.fill(new FluidStack(TofuFluids.BITTERN, 200), IFluidHandler.FluidAction.EXECUTE);
		}
	}

	protected int getBurnDuration(ItemStack p_213997_1_) {
		if (p_213997_1_.isEmpty())
			return 0;
		return ForgeHooks.getBurnTime(p_213997_1_);
	}

	protected int getTotalCookTime() {
		return 200;
	}

	public static boolean isFuel(ItemStack p_213991_0_) {
		return (ForgeHooks.getBurnTime(p_213991_0_) > 0);
	}

	@Override
	public int[] getSlotsForFace(Direction p_180463_1_) {
		if (p_180463_1_ == Direction.DOWN)
			return SLOTS_FOR_DOWN;
		return SLOTS_FOR_SIDES;
	}


	@Override
	public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
		return canPlaceItem(p_180462_1_, p_180462_2_);
	}

	@Override
	public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
		if (p_180461_3_ == Direction.DOWN && p_180461_1_ == 1) {
			Item item = p_180461_2_.getItem();
			return item == Items.WATER_BUCKET || item == Items.BUCKET;
		}
		return true;
	}

	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public ItemStack getItem(int p_70301_1_) {
		return this.items.get(p_70301_1_);
	}

	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		return ItemStackHelper.removeItem(this.items, p_70298_1_, p_70298_2_);
	}

	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		return ItemStackHelper.takeItem(this.items, p_70304_1_);
	}

	public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
		ItemStack itemstack = this.items.get(p_70299_1_);
		boolean flag = !p_70299_2_.isEmpty() && p_70299_2_.sameItem(itemstack) && ItemStack.tagMatches(p_70299_2_, itemstack);
		this.items.set(p_70299_1_, p_70299_2_);
		if (p_70299_2_.getCount() > this.getMaxStackSize()) {
			p_70299_2_.setCount(this.getMaxStackSize());
		}

		if (p_70299_1_ == 0 && !flag) {
			this.cookingTotalTime = this.getTotalCookTime();
			this.cookingProgress = 0;
			this.setChanged();
		}

	}

	public boolean stillValid(PlayerEntity p_70300_1_) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return p_70300_1_.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	public boolean canPlaceItem(int p_94041_1_, ItemStack p_94041_2_) {
		if (p_94041_1_ == 3 || p_94041_1_ == 1)
			return false;
		if (p_94041_1_ != 0)
			return (p_94041_2_.getItem() == Items.GLASS_BOTTLE);
		ItemStack itemstack = this.items.get(0);
		return (isFuel(p_94041_2_) || (p_94041_2_.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET));
	}

	public void clearContent() {
		this.items.clear();
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.tofucraft.salt_furnace");
	}

	@Override
	protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
		return new SaltFurnaceContainer(p_213906_1_, p_213906_2_, this, this.dataAccess);
	}

	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP)
				return this.handlers[0].cast();
			if (facing == Direction.DOWN)
				return this.handlers[1].cast();
			return this.handlers[2].cast();
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return this.holder.cast();
		return super.getCapability(capability, facing);
	}

	protected void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < this.handlers.length; x++)
			this.handlers[x].invalidate();
	}
}

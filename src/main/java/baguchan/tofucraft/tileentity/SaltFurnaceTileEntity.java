package baguchan.tofucraft.tileentity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.utils.SaltFurnaceBlock;
import baguchan.tofucraft.inventory.SaltFurnaceContainer;
import baguchan.tofucraft.message.SaltFurnaceBitternMessage;
import baguchan.tofucraft.message.SaltFurnaceWaterMessage;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTileEntitys;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.state.Property;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
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

public class SaltFurnaceTileEntity extends LockableTileEntity implements ITickableTileEntity, ISidedInventory, INamedContainerProvider {
	private static final int[] SLOTS_FOR_DOWN = new int[]{3, 1};

	private static final int[] SLOTS_FOR_SIDES = new int[]{0, 2};

	protected NonNullList<ItemStack> items = NonNullList.func_191197_a(4, ItemStack.field_190927_a);

	public FluidTank waterTank = new FluidTank(3000) {
		public boolean isFluidValid(FluidStack stack) {
			return (stack.getFluid() == Fluids.field_204546_a);
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
		public int func_221476_a(int p_221476_1_) {
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

		public void func_221477_a(int p_221477_1_, int p_221477_2_) {
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

		public int func_221478_a() {
			return 4;
		}
	};

	LazyOptional<? extends IItemHandler>[] handlers;

	private final LazyOptional<IFluidHandler> holder;

	public SaltFurnaceTileEntity() {
		super(TofuTileEntitys.SALT_FURNACE);
		this
				.handlers = (LazyOptional<? extends IItemHandler>[]) SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN, Direction.NORTH});
		this.holder = LazyOptional.of(() -> this.waterTank);
	}

	public SaltFurnaceTileEntity(TileEntityType<?> p_i49964_1_) {
		super(p_i49964_1_);
		this.handlers = (LazyOptional<? extends IItemHandler>[]) SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN, Direction.NORTH});
		this.holder = LazyOptional.of(() -> this.waterTank);
	}

	private boolean isLit() {
		return (this.litTime > 0);
	}

	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.func_230337_a_(p_230337_1_, p_230337_2_);
		this.items = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
		if (p_230337_2_.func_150297_b("WaterTank", 10)) {
			CompoundNBT nbt = p_230337_2_.func_74775_l("WaterTank");
			this.waterTank.readFromNBT(nbt);
		}
		if (p_230337_2_.func_150297_b("BitternTank", 10)) {
			CompoundNBT nbt = p_230337_2_.func_74775_l("BitternTank");
			this.bitternTank.readFromNBT(nbt);
		}
		ItemStackHelper.func_191283_b(p_230337_2_, this.items);
		this.litTime = p_230337_2_.func_74762_e("BurnTime");
		this.cookingProgress = p_230337_2_.func_74762_e("CookTime");
		this.cookingTotalTime = p_230337_2_.func_74762_e("CookTimeTotal");
		this.litDuration = getBurnDuration((ItemStack) this.items.get(1));
	}

	public CompoundNBT func_189515_b(CompoundNBT p_189515_1_) {
		super.func_189515_b(p_189515_1_);
		CompoundNBT nbt = new CompoundNBT();
		CompoundNBT nbt2 = new CompoundNBT();
		this.waterTank.writeToNBT(nbt);
		this.bitternTank.writeToNBT(nbt2);
		p_189515_1_.func_218657_a("WaterTank", (INBT) nbt);
		p_189515_1_.func_218657_a("BitternTank", (INBT) nbt2);
		p_189515_1_.func_74768_a("BurnTime", this.litTime);
		p_189515_1_.func_74768_a("CookTime", this.cookingProgress);
		p_189515_1_.func_74768_a("CookTimeTotal", this.cookingTotalTime);
		ItemStackHelper.func_191282_a(p_189515_1_, this.items);
		return p_189515_1_;
	}

	public void func_73660_a() {
		boolean flag = isLit();
		boolean flag1 = false;
		if (isLit())
			this.litTime--;
		if (!this.field_145850_b.field_72995_K) {
			if (this.prevWaterFluid != this.waterTank.getFluidAmount()) {
				Chunk chunk = this.field_145850_b.func_175726_f(func_174877_v());
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new SaltFurnaceWaterMessage(func_174877_v(), this.waterTank.getFluid()));
				this.prevWaterFluid = this.waterTank.getFluidAmount();
			}
			if (this.prevBitternFluid != this.bitternTank.getFluidAmount()) {
				Chunk chunk = this.field_145850_b.func_175726_f(func_174877_v());
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new SaltFurnaceBitternMessage(func_174877_v(), this.bitternTank.getFluid()));
				this.prevBitternFluid = this.bitternTank.getFluidAmount();
			}
			ItemStack itemstack = (ItemStack) this.items.get(0);
			if (isLit() || !((ItemStack) this.items.get(0)).func_190926_b()) {
				if (!isLit() && hasWater()) {
					this.litTime = getBurnDuration(itemstack);
					this.litDuration = this.litTime;
					if (isLit()) {
						flag1 = true;
						if (itemstack.hasContainerItem()) {
							this.items.set(0, itemstack.getContainerItem());
						} else if (!itemstack.func_190926_b()) {
							Item item = itemstack.func_77973_b();
							itemstack.func_190918_g(1);
							if (itemstack.func_190926_b())
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
				this.cookingProgress = MathHelper.func_76125_a(this.cookingProgress - 2, 0, this.cookingTotalTime);
			}
			if (flag != isLit()) {
				flag1 = true;
				this.field_145850_b.func_180501_a(this.field_174879_c, (BlockState) this.field_145850_b.getBlockState(this.field_174879_c).func_206870_a((Property) SaltFurnaceBlock.LIT, Boolean.valueOf(isLit())), 3);
			}
			makeBittern();
		}
		if (flag1)
			func_70296_d();
	}

	protected boolean hasWater() {
		boolean flag = (this.waterTank.getFluid().getFluid() == Fluids.field_204546_a && this.waterTank.getFluid().getAmount() >= 200);
		ItemStack itemstack1 = (ItemStack) this.items.get(1);
		if (itemstack1.func_190926_b())
			return flag;
		if (itemstack1.func_190916_E() + 2 <= func_70297_j_() && itemstack1.func_190916_E() + 2 <= itemstack1.func_77976_d())
			return flag;
		return false;
	}

	protected boolean hasBittern() {
		boolean flag = (this.bitternTank.getFluid().getFluid() == TofuFluids.BITTERN && this.bitternTank.getFluid().getAmount() >= 200);
		ItemStack itemstack1 = (ItemStack) this.items.get(3);
		ItemStack itemstack2 = (ItemStack) this.items.get(2);
		if (itemstack2.func_77973_b() == Items.field_151069_bo) {
			if (itemstack1.func_190926_b())
				return flag;
			if (itemstack1.func_190916_E() + 1 <= func_70297_j_() && itemstack1.func_190916_E() + 1 <= itemstack1.func_77976_d())
				return flag;
			return false;
		}
		return false;
	}

	private void makeBittern() {
		if (hasBittern()) {
			ItemStack itemstack1 = new ItemStack((IItemProvider) TofuItems.BITTERN, 1);
			ItemStack itemstack2 = (ItemStack) this.items.get(3);
			ItemStack itemstack3 = (ItemStack) this.items.get(2);
			itemstack3.func_190918_g(1);
			if (itemstack2.func_190926_b()) {
				this.items.set(3, itemstack1.func_77946_l());
			} else if (itemstack2.func_77973_b() == itemstack1.func_77973_b()) {
				itemstack2.func_190917_f(itemstack1.func_190916_E());
			}
			this.bitternTank.drain(200, IFluidHandler.FluidAction.EXECUTE);
		}
	}

	private void makeSalt() {
		if (hasWater()) {
			ItemStack itemstack1 = new ItemStack((IItemProvider) TofuItems.SALT, 2);
			ItemStack itemstack2 = (ItemStack) this.items.get(1);
			if (itemstack2.func_190926_b()) {
				this.items.set(1, itemstack1.func_77946_l());
			} else if (itemstack2.func_77973_b() == itemstack1.func_77973_b()) {
				itemstack2.func_190917_f(itemstack1.func_190916_E());
			}
			this.waterTank.drain(200, IFluidHandler.FluidAction.EXECUTE);
			this.bitternTank.fill(new FluidStack((Fluid) TofuFluids.BITTERN, 200), IFluidHandler.FluidAction.EXECUTE);
		}
	}

	protected int getBurnDuration(ItemStack p_213997_1_) {
		if (p_213997_1_.func_190926_b())
			return 0;
		return ForgeHooks.getBurnTime(p_213997_1_);
	}

	protected int getTotalCookTime() {
		return 200;
	}

	public static boolean isFuel(ItemStack p_213991_0_) {
		return (ForgeHooks.getBurnTime(p_213991_0_) > 0);
	}

	public int[] func_180463_a(Direction p_180463_1_) {
		if (p_180463_1_ == Direction.DOWN)
			return SLOTS_FOR_DOWN;
		return SLOTS_FOR_SIDES;
	}

	public boolean func_180462_a(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
		return func_94041_b(p_180462_1_, p_180462_2_);
	}

	public boolean func_180461_b(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
		if (p_180461_3_ == Direction.DOWN && p_180461_1_ == 1) {
			Item item = p_180461_2_.func_77973_b();
			if (item != Items.field_151131_as && item != Items.field_151133_ar)
				return false;
		}
		return true;
	}

	public int func_70302_i_() {
		return this.items.size();
	}

	public boolean func_191420_l() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.func_190926_b())
				return false;
		}
		return true;
	}

	public ItemStack func_70301_a(int p_70301_1_) {
		return (ItemStack) this.items.get(p_70301_1_);
	}

	public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
		return ItemStackHelper.func_188382_a((List) this.items, p_70298_1_, p_70298_2_);
	}

	public ItemStack func_70304_b(int p_70304_1_) {
		return ItemStackHelper.func_188383_a((List) this.items, p_70304_1_);
	}

	public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
		ItemStack itemstack = (ItemStack) this.items.get(p_70299_1_);
		boolean flag = (!p_70299_2_.func_190926_b() && p_70299_2_.func_77969_a(itemstack) && ItemStack.func_77970_a(p_70299_2_, itemstack));
		this.items.set(p_70299_1_, p_70299_2_);
		if (p_70299_2_.func_190916_E() > func_70297_j_())
			p_70299_2_.func_190920_e(func_70297_j_());
		if (p_70299_1_ == 0 && !flag) {
			this.cookingTotalTime = getTotalCookTime();
			this.cookingProgress = 0;
			func_70296_d();
		}
	}

	public boolean func_70300_a(PlayerEntity p_70300_1_) {
		if (this.field_145850_b.func_175625_s(this.field_174879_c) != this)
			return false;
		return (p_70300_1_.func_70092_e(this.field_174879_c.getX() + 0.5D, this.field_174879_c.getY() + 0.5D, this.field_174879_c.getZ() + 0.5D) <= 64.0D);
	}

	public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
		if (p_94041_1_ == 3 || p_94041_1_ == 1)
			return false;
		if (p_94041_1_ != 0)
			return (p_94041_2_.func_77973_b() == Items.field_151069_bo);
		ItemStack itemstack = (ItemStack) this.items.get(0);
		return (isFuel(p_94041_2_) || (p_94041_2_.func_77973_b() == Items.field_151133_ar && itemstack.func_77973_b() != Items.field_151133_ar));
	}

	public void func_174888_l() {
		this.items.clear();
	}

	private static void createExperience(World p_235641_0_, Vector3d p_235641_1_, int p_235641_2_, float p_235641_3_) {
		int i = MathHelper.func_76141_d(p_235641_2_ * p_235641_3_);
		float f = MathHelper.func_226164_h_(p_235641_2_ * p_235641_3_);
		if (f != 0.0F && Math.random() < f)
			i++;
		while (i > 0) {
			int j = ExperienceOrbEntity.func_70527_a(i);
			i -= j;
			p_235641_0_.func_217376_c((Entity) new ExperienceOrbEntity(p_235641_0_, p_235641_1_.field_72450_a, p_235641_1_.field_72448_b, p_235641_1_.field_72449_c, j));
		}
	}

	public void fillStackedContents(RecipeItemHelper p_194018_1_) {
		for (ItemStack itemstack : this.items)
			p_194018_1_.func_194112_a(itemstack);
	}

	protected ITextComponent func_213907_g() {
		return (ITextComponent) new TranslationTextComponent("container.tofucraft.salt_furnace");
	}

	protected Container func_213906_a(int p_213906_1_, PlayerInventory p_213906_2_) {
		return (Container) new SaltFurnaceContainer(p_213906_1_, p_213906_2_, (IInventory) this, this.dataAccess);
	}

	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.field_145846_f && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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

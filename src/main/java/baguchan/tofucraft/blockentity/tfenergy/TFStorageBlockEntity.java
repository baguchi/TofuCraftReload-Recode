package baguchan.tofucraft.blockentity.tfenergy;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.api.tfenergy.IEnergyExtractable;
import baguchan.tofucraft.api.tfenergy.ITofuEnergy;
import baguchan.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchan.tofucraft.block.tfenergy.TFStorageBlock;
import baguchan.tofucraft.blockentity.tfenergy.base.EnergyBaseBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.base.SenderBaseBlockEntity;
import baguchan.tofucraft.inventory.TFStorageMenu;
import baguchan.tofucraft.message.TFStorageSoymilkMessage;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TFStorageBlockEntity extends SenderBaseBlockEntity implements WorldlyContainer, StackedContentsCompatible, Container, MenuProvider {

	private static final int POWER = 20;
	private FluidTank tank = new TFStorageTank(2000);
	protected NonNullList<ItemStack> inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	private int workload = 0;
	private int current_workload = 0;
	private int prevFluid;
	private final LazyOptional<IFluidHandler> holder;

	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
				case 0:
					return TFStorageBlockEntity.this.workload;
				case 1:
					return TFStorageBlockEntity.this.current_workload;
				case 2:
					return TFStorageBlockEntity.this.energy;
				case 3:
					return TFStorageBlockEntity.this.energyMax;
			}
			return 0;
		}

		@Override
		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
				case 0:
					TFStorageBlockEntity.this.workload = p_221477_2_;
					break;
				case 1:
					TFStorageBlockEntity.this.current_workload = p_221477_2_;
					break;
				case 2:
					TFStorageBlockEntity.this.energy = p_221477_2_;
					break;
				case 3:
					TFStorageBlockEntity.this.energyMax = p_221477_2_;
					break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	public TFStorageBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_STORAGE.get(), p_155229_, p_155230_, 10000);
		this.holder = LazyOptional.of(() -> this.tank);
	}

	public static void tick(Level level, BlockPos p_155015_, BlockState p_155016_, TFStorageBlockEntity tfStorageBlockEntity) {
		if (!level.isClientSide() && tfStorageBlockEntity.getEnergyStored() > 0) {
			if (tfStorageBlockEntity.isValid()) {
				if (!tfStorageBlockEntity.isCached) tfStorageBlockEntity.onCache();
				if (tfStorageBlockEntity.cache.size() > 0) {
					List<BlockEntity> toSend = new ArrayList<>();

					tfStorageBlockEntity.cache.forEach(tileEntity -> {
						if (((EnergyBaseBlockEntity) tileEntity).getEnergyStored() < ((EnergyBaseBlockEntity) tileEntity).getMaxEnergyStored())
							toSend.add(tileEntity);
					});
					if (toSend.size() > 0) {
						int packSize = Math.max(Math.min(tfStorageBlockEntity.getTransferPower(),
								tfStorageBlockEntity.getEnergyStored()) / toSend.size(), 1);
						for (BlockEntity te : toSend) {
							tfStorageBlockEntity.drain(((ITofuEnergy) te).receive(Math.min(packSize, tfStorageBlockEntity.getEnergyStored()), false), false);
						}
					}

				}
			} else {
				tfStorageBlockEntity.cache.clear();
				tfStorageBlockEntity.isCached = false;
			}
		}

		boolean worked = false;


		if (level.isClientSide()) return;

		if (!level.isClientSide) {
			if (tfStorageBlockEntity.prevFluid != tfStorageBlockEntity.tank.getFluidAmount()) {
				LevelChunk chunk = level.getChunkAt(p_155015_);
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new TFStorageSoymilkMessage(p_155015_, tfStorageBlockEntity.tank.getFluid()));
				tfStorageBlockEntity.prevFluid = tfStorageBlockEntity.tank.getFluidAmount();
			}
		}

		//Transform workload to power
		if (tfStorageBlockEntity.workload > 0 && tfStorageBlockEntity.getEnergyStored() < tfStorageBlockEntity.getMaxEnergyStored()) {
			tfStorageBlockEntity.workload -= tfStorageBlockEntity.receive(Math.min(tfStorageBlockEntity.workload, POWER), false);
			worked = true;
		}

		//Consume beans inside machine
		ItemStack from = tfStorageBlockEntity.inventory.get(0);
		FluidStack milk = tfStorageBlockEntity.getTank().getFluid();
		if (tfStorageBlockEntity.workload == 0) {
			if (from.getItem() instanceof IEnergyExtractable) {
				IEnergyExtractable symbol = (IEnergyExtractable) from.getItem();
				tfStorageBlockEntity.workload += symbol.drain(from, POWER * 20, false);
			} else if (TofuEnergyMap.getFuel(from) != -1) {
				tfStorageBlockEntity.workload += TofuEnergyMap.getFuel(from);
				from.shrink(1);
			}

			if (milk != null) {
				Map.Entry<FluidStack, Integer> recipe = TofuEnergyMap.getLiquidFuel(milk);
				if (recipe != null) {
					tfStorageBlockEntity.tank.drain(recipe.getValue(), IFluidHandler.FluidAction.EXECUTE);
					tfStorageBlockEntity.workload += recipe.getValue();
				}
			}
			tfStorageBlockEntity.current_workload = tfStorageBlockEntity.workload;
		}

		final BlockState state = level.getBlockState(p_155015_);
		level.setBlock(p_155015_, state.setValue(TFStorageBlock.LIT, worked), 3);
	}

	public FluidTank getTank() {
		return this.tank;
	}

	protected void refresh() {
		if (!getLevel().isClientSide()) {
			BlockState state = getLevel().getBlockState(getBlockPos());
			getLevel().markAndNotifyBlock(getBlockPos(), getLevel().getChunkAt(getBlockPos()), state, state, 11, 512);
		}
	}

	@Override
	public int getContainerSize() {
		return 1;
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

	@Override
	public ItemStack getItem(int index) {
		return inventory.get(index);
	}

	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		return ContainerHelper.removeItem(this.inventory, p_70298_1_, p_70298_2_);
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
	}

	@Override
	public boolean stillValid(Player p_18946_) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return p_18946_.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressScaled(int par1) {
		return this.current_workload == 0 ? 0 : ((this.current_workload - this.workload) * par1 / this.current_workload);
	}

	public NonNullList<ItemStack> getInventory() {
		return inventory;
	}

	@Override
	@Nullable
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return this.holder.cast();
		}

		return super.getCapability(capability, facing);
	}

	public void saveAdditional(CompoundTag cmp) {
		super.saveAdditional(cmp);
		ContainerHelper.saveAllItems(cmp, this.inventory);
		cmp.putInt("workload", this.workload);
		cmp.putInt("current", this.current_workload);

		CompoundTag tankTag = this.tank.writeToNBT(new CompoundTag());

		cmp.put("Tank", tankTag);
	}

	public void load(CompoundTag cmp) {
		super.load(cmp);
		ContainerHelper.loadAllItems(cmp, this.inventory);

		this.workload = cmp.getInt("workload");
		this.current_workload = cmp.getInt("current");

		this.tank = this.tank.readFromNBT(cmp.getCompound("Tank"));
	}

	@Override
	public int[] getSlotsForFace(Direction p_19238_) {
		return new int[0];
	}

	@Override
	public boolean canPlaceItemThroughFace(int p_19235_, ItemStack p_19236_, @org.jetbrains.annotations.Nullable Direction p_19237_) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int p_19239_, ItemStack p_19240_, Direction p_19241_) {
		return false;
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
	}

	@Override
	public int getMaxStackSize() {
		return WorldlyContainer.super.getMaxStackSize();
	}

	@Override
	public void fillStackedContents(StackedContents p_40281_) {
		for (ItemStack itemstack : this.inventory) {
			p_40281_.accountStack(itemstack);
		}
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.tofucraft.tf_storage");
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		return new TFStorageMenu(p_39954_, p_39955_, this, this.dataAccess);
	}


	private class TFStorageTank extends FluidTank {
		TFStorageTank(int capacity) {
			super(capacity);
		}

		@Override
		protected void onContentsChanged() {
			TFStorageBlockEntity.this.refresh();
		}
	}

}

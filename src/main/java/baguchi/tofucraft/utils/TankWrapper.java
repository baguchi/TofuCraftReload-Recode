package baguchi.tofucraft.utils;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class TankWrapper extends FluidStacksResourceHandler {

	private final BlockEntity blockEntity;
	private final FluidTank fluidTank;

	public TankWrapper(BlockEntity blockEntity, FluidTank fluidTank) {
		super(1, fluidTank.getCapacity());
		this.blockEntity = blockEntity;
		this.fluidTank = fluidTank;
	}


	public FluidResource getResourceFrom(FluidStack stack) {
		return FluidResource.of(stack.getFluid());
	}

	public int getAmountFrom(FluidStack stack) {
		return stack.getAmount();
	}

	protected FluidStack getStackFrom(FluidResource resource, int amount) {
		return resource.toStack(amount);
	}

	protected int getCapacity(int index, FluidResource resource) {
		return this.fluidTank.getCapacity();
	}

	protected FluidStack copyOf(FluidStack stack) {
		return stack.copy();
	}

	public boolean matches(FluidStack stack, FluidResource resource) {
		return resource.matches(this.fluidTank.getFluid());
	}

	@Override
	public void set(int index, FluidResource resource, int amount) {
		super.set(index, resource, amount);
		this.fluidTank.setFluid(getStackFrom(resource, amount));
		this.blockEntity.setChanged();
	}

	@Override
	public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
		FluidStack currentStack = this.stacks.get(index);
		int currentAmount = this.getAmountFrom(currentStack);
		int inserted = Math.min(amount, this.getCapacity(index, resource) - currentAmount);
		if (inserted > 0) {
			this.fluidTank.fill(this.getStackFrom(resource, inserted), IFluidHandler.FluidAction.EXECUTE);
		}
		int i = super.insert(index, resource, amount, transaction);

		return i;
	}

	@Override
	public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {

		FluidStack currentStack = this.stacks.get(index);
		int currentAmount = this.getAmountFrom(currentStack);
		int extracted = Math.min(amount, currentAmount);
		if (extracted > 0) {
			this.fluidTank.drain(this.getStackFrom(resource, extracted), IFluidHandler.FluidAction.EXECUTE);
		}
		int i = super.extract(index, resource, amount, transaction);
		return i;
	}
}

package baguchan.tofucraft.capability.wrapper;

import baguchan.tofucraft.api.IFluidBottle;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;

public class FluidBottleWrapper implements IFluidHandlerItem {
	public static final int BOTTLE_VOLUME = 250;
	protected ItemStack container;

	public FluidBottleWrapper(ItemStack container) {
		this.container = container;
	}

	@Override
	public ItemStack getContainer() {
		return container;
	}

	public boolean canFillFluidType(FluidStack fluid) {
		return false;
	}

	public FluidStack getFluid() {
		Item item = container.getItem();
		if (item instanceof IFluidBottle fluidBottle) {
			return new FluidStack(fluidBottle.getFluid(), BOTTLE_VOLUME);
		} else {
			return FluidStack.EMPTY;
		}
	}

	protected void setFluid(FluidStack fluidStack) {
		if (fluidStack.isEmpty())
			container = new ItemStack(Items.BUCKET);
		else
			container = FluidUtil.getFilledBucket(fluidStack);
	}

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public FluidStack getFluidInTank(int tank) {
		return getFluid();
	}

	@Override
	public int getTankCapacity(int tank) {
		return BOTTLE_VOLUME;
	}

	@Override
	public boolean isFluidValid(int tank, FluidStack stack) {
		return true;
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		if (container.getCount() != 1 || resource.getAmount() < BOTTLE_VOLUME || container.getItem() instanceof IFluidBottle || !getFluid().isEmpty() || !canFillFluidType(resource)) {
			return 0;
		}

		if (action.execute()) {
			setFluid(resource);
		}

		return BOTTLE_VOLUME;
	}

	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		if (container.getCount() != 1 || resource.getAmount() < BOTTLE_VOLUME) {
			return FluidStack.EMPTY;
		}

		FluidStack fluidStack = getFluid();
		if (!fluidStack.isEmpty() && FluidStack.isSameFluidSameComponents(fluidStack, resource)) {
			if (action.execute()) {
				setFluid(FluidStack.EMPTY);
			}
			return fluidStack;
		}

		return FluidStack.EMPTY;
	}

	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		if (container.getCount() != 1 || maxDrain < BOTTLE_VOLUME) {
			return FluidStack.EMPTY;
		}

		FluidStack fluidStack = getFluid();
		if (!fluidStack.isEmpty()) {
			if (action.execute()) {
				setFluid(FluidStack.EMPTY);
			}
			return fluidStack;
		}

		return FluidStack.EMPTY;
	}
}

package baguchi.tofucraft.blockentity.fluid;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

import java.util.function.Predicate;

public class FluidContainer extends FluidStacksResourceHandler {
	protected Predicate<FluidStack> validator;
	protected FluidStack fluid = FluidStack.EMPTY;
	protected int capacity;

	public FluidContainer(int capacity) {
		this(capacity, e -> true);
	}

	public FluidContainer(int capacity, Predicate<FluidStack> validator) {
		super(1, capacity);
		this.capacity = capacity;
		this.validator = validator;
	}

	@Override
	public boolean matches(FluidStack stack, FluidResource resource) {
		return resource.matches(stack) && validator.test(stack);
	}

	@Override
	public void set(int index, FluidResource resource, int amount) {
		super.set(index, resource, amount);
		this.onContentsChanged();
	}

	protected void onContentsChanged() {
	}
}

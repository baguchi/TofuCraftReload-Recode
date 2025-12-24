package baguchi.tofucraft.utils.transfer.fluid;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ItemAccessResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;

import java.util.Objects;

public class BottleResourceHandler extends ItemAccessResourceHandler<FluidResource> {

	public final Fluid content;

	public BottleResourceHandler(ItemAccess itemAccess, Fluid content) {
		super(itemAccess, 1);
		this.content = content;
	}

	@Override
	protected FluidResource getResourceFrom(ItemResource accessResource, int index) {
		return FluidResource.of(new FluidStack(this.content, 250));
	}

	@Override
	protected int getAmountFrom(ItemResource accessResource, int index) {
		FluidResource resource = this.getResourceFrom(accessResource, index);
		return resource.isEmpty() ? 0 : 250;
	}

	@Override
	protected ItemResource update(ItemResource accessResource, int index, FluidResource newResource, int newAmount) {
		if (newAmount == 0) {
			return ItemResource.of(Items.GLASS_BOTTLE);
		} else if (newAmount != 250) {
			return ItemResource.EMPTY;
		} else {
			return accessResource;
		}
	}

	@Override
	protected int getCapacity(int index, FluidResource resource) {
		Objects.checkIndex(index, this.size());
		return 250;
	}

	@Override
	public boolean isValid(int index, FluidResource resource) {
		return true;
	}
}

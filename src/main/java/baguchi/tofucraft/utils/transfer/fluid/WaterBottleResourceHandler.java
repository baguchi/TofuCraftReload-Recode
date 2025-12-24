package baguchi.tofucraft.utils.transfer.fluid;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ItemAccessResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;

import java.util.Objects;

public class WaterBottleResourceHandler extends ItemAccessResourceHandler<FluidResource> {
	public final Fluid content;
	public final ItemStack stack;

	public WaterBottleResourceHandler(ItemAccess itemAccess, ItemStack stack, Fluid content) {
		super(itemAccess, 1);
		this.content = content;
		this.stack = stack;
	}

	protected FluidResource getResourceFrom(ItemResource accessResource, int index) {
		return FluidResource.of(this.content);
	}

	@Override
	public boolean isValid(int index, FluidResource resource) {
		return super.isValid(index, resource) && this.stack.get(DataComponents.POTION_CONTENTS) != null && this.stack.get(DataComponents.POTION_CONTENTS).is(Potions.WATER);
	}

	protected int getAmountFrom(ItemResource accessResource, int index) {
		FluidResource resource = this.getResourceFrom(accessResource, index);
		return resource.isEmpty() ? 0 : 250;
	}

	protected ItemResource update(ItemResource accessResource, int index, FluidResource newResource, int newAmount) {
		if (newAmount == 0) {
			return ItemResource.of(Items.GLASS_BOTTLE);
		} else if (newAmount != 250) {
			return ItemResource.EMPTY;
		} else {
			FluidStack newStack = newResource.toStack(newAmount);
			return ItemResource.of(newStack.getFluidType().getBucket(newStack));
		}
	}

	protected int getCapacity(int index, FluidResource resource) {
		Objects.checkIndex(index, this.size());
		return 250;
	}
}

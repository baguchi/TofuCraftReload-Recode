package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RollingPinItem extends Item {
	public RollingPinItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		return new ItemStack(TofuItems.ROLLINGPIN.get());
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}
}

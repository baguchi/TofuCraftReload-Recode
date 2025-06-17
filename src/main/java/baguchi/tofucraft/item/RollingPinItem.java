package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RollingPinItem extends Item {
	public RollingPinItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemStack) {
		return TofuItems.ROLLINGPIN.get().getDefaultInstance();
	}
}

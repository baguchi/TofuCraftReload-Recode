package baguchi.tofucraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RollingPinItem extends Item {
	public RollingPinItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemStack) {
		return itemStack;
	}
}

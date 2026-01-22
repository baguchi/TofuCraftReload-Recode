package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStackTemplate;
import org.jspecify.annotations.Nullable;

public class RollingPinItem extends Item {
	public RollingPinItem(Item.Properties properties) {
		super(properties);
	}


	@Override
	public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
		return new ItemStackTemplate(TofuItems.ROLLINGPIN.get());
	}

}

package baguchi.tofucraft.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ZundaIngotItem extends Item {
	public ZundaIngotItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
		return 400;
	}
}

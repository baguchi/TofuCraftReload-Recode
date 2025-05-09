package baguchan.tofucraft.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ZundaIngotItem extends Item {
	public ZundaIngotItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 400;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_BLOCK_BREAK;
	}
}

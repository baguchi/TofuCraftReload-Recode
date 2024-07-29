package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SaltFoodItem extends Item {
	public SaltFoodItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemStack, Level p_41410_, LivingEntity entity) {
		entity.addEffect(new MobEffectInstance(TofuEffects.SALT_BOOST, 1200));

		return super.finishUsingItem(itemStack, p_41410_, entity);
	}
}

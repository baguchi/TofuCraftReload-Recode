package baguchan.tofucraft.item;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.capability.TofuLivingCapability;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SaltFoodItem extends Item {
	public SaltFoodItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity entity) {
		TofuLivingCapability capability = entity.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).orElse(null);
		if (capability != null && p_41409_.isEdible()) {
			capability.setSaltBoost(p_41409_.getFoodProperties(entity).getNutrition() * 20 * 20, p_41409_.getFoodProperties(entity).getNutrition() * 20 * 60, entity);
		}

		return super.finishUsingItem(p_41409_, p_41410_, entity);
	}
}

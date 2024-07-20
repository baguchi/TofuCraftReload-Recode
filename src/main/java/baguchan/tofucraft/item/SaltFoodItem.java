package baguchan.tofucraft.item;

import baguchan.tofucraft.attachment.TofuLivingAttachment;
import baguchan.tofucraft.registry.TofuAttachments;
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
		TofuLivingAttachment capability = entity.getData(TofuAttachments.TOFU_LIVING);
		if (capability != null && itemStack.getFoodProperties(entity) != null) {
			capability.setSaltBoost(itemStack.getFoodProperties(entity).nutrition() * 20 * 20, itemStack.getFoodProperties(entity).nutrition() * 20 * 40, entity);
		}

		return super.finishUsingItem(itemStack, p_41410_, entity);
	}
}

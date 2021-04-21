package baguchan.tofucraft.item;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class TofuShieldItem extends ShieldItem {
	public TofuShieldItem(Properties properties) {
		super(properties);
	}

	public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
		return true;
	}
}

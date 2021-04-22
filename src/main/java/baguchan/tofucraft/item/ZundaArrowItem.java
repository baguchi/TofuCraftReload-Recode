package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.ZundaArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ZundaArrowItem extends ArrowItem {
	public ZundaArrowItem(Properties tab) {
		super(tab);
	}

	public AbstractArrowEntity func_200887_a(World p_200887_1_, ItemStack p_200887_2_, LivingEntity p_200887_3_) {
		return new ZundaArrowEntity(p_200887_1_, p_200887_3_);
	}
}

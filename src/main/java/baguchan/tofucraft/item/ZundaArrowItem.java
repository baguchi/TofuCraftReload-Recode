package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ZundaArrowItem extends ArrowItem implements ProjectileItem {
	public ZundaArrowItem(Item.Properties p_43235_) {
		super(p_43235_);
	}

	@Override
	public AbstractArrow createArrow(Level p_43237_, ItemStack p_43238_, LivingEntity p_43239_, @Nullable ItemStack p_345773_) {
		return new ZundaArrow(p_43237_, p_43239_, p_43238_.copyWithCount(1), p_345773_);
	}

	@Override
	public Projectile asProjectile(Level p_338332_, Position p_338313_, ItemStack p_338304_, Direction p_338842_) {
		ZundaArrow spectralarrow = new ZundaArrow(p_338332_, p_338313_.x(), p_338313_.y(), p_338313_.z(), p_338304_.copyWithCount(1), null);
		spectralarrow.pickup = AbstractArrow.Pickup.ALLOWED;
		return spectralarrow;
	}
}
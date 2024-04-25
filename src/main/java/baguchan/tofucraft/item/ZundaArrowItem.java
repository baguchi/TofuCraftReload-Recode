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

public class ZundaArrowItem extends ArrowItem implements ProjectileItem {
	public ZundaArrowItem(Item.Properties p_43235_) {
		super(p_43235_);
	}

	public AbstractArrow createArrow(Level p_43237_, ItemStack p_43238_, LivingEntity p_43239_) {
		return new ZundaArrow(p_43237_, p_43239_, p_43238_);
	}

	@Override
	public Projectile asProjectile(Level p_338330_, Position p_338329_, ItemStack p_338197_, Direction p_338469_) {
		ZundaArrow arrow = new ZundaArrow(p_338330_, p_338329_.x(), p_338329_.y(), p_338329_.z(), p_338197_.copyWithCount(1));
		arrow.pickup = AbstractArrow.Pickup.ALLOWED;
		return arrow;
	}
}
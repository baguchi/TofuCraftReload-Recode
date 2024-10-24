package baguchan.tofucraft.item.tool;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ZundaBowItem extends BowItem implements IEnergyInsertable {
	public ZundaBowItem(Properties tab) {
		super(tab);
	}

	@Override
	public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
		if (!isTooDamagedToUse(p_40667_)) {
			super.releaseUsing(p_40667_, p_40668_, p_40669_, p_40670_);
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
		if (!isTooDamagedToUse(p_40673_.getItemInHand(p_40674_))) {
			return super.use(p_40672_, p_40673_, p_40674_);
		} else {
			return InteractionResultHolder.fail(p_40673_.getItemInHand(p_40674_));
		}
	}

	private static boolean isTooDamagedToUse(ItemStack p_353073_) {
		return p_353073_.getDamageValue() >= p_353073_.getMaxDamage() - 1;
	}


	@Override
	protected Projectile createProjectile(Level p_331008_, LivingEntity p_330781_, ItemStack p_330846_, ItemStack p_331497_, boolean p_331305_) {
		Projectile projectile = super.createProjectile(p_331008_, p_330781_, p_330846_, p_331497_, p_331305_);

		if (projectile instanceof ZundaArrow zundaArrow) {
			zundaArrow.setBaseDamage(zundaArrow.getBaseDamage() + 3);
		}

		return projectile;
	}

	@Override
	public int getEnchantmentValue() {
		return 5;
	}

	@Override
	public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
		return 70000;
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return calculated * 5;
			}
		}
		return 0;
	}
}

package baguchi.tofucraft.item.tool;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchi.tofucraft.registry.TofuEnchantments;
import baguchi.tofucraft.registry.TofuToolMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class TofuSwordItem extends SwordItem implements IEnergyInsertable {
	public TofuSwordItem(TofuToolMaterials.TofuToolMaterial tofuItemTier, float p_362481_, float p_364182_, Properties properties) {
		super(tofuItemTier.applySwordProperties(properties, p_362481_, p_364182_));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity targetEntity, LivingEntity attacker) {
		// Drain
		float healthBeforeAttack = (float) attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float damage = healthBeforeAttack - targetEntity.getHealth();
		if (damage > 0.0f) {
			int lvl = EnchantmentHelper.getEnchantmentLevel(attacker.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(TofuEnchantments.DRAIN), attacker);
			attacker.heal(damage * (lvl * 0.1f + 0.1f));
		}
		return super.hurtEnemy(itemStack, targetEntity, attacker);
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

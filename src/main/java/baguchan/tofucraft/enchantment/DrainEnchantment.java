package baguchan.tofucraft.enchantment;

import baguchan.tofucraft.registry.TofuEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class DrainEnchantment extends Enchantment {
	public DrainEnchantment(Rarity p_i50019_1_, EquipmentSlot... p_i50019_2_) {
		super(p_i50019_1_, TofuEnchantments.TOFU_DIAMOND_SWORD_CATEGORY, p_i50019_2_);
	}

	public int getMinCost(int p_77321_1_) {
		return 10 + (p_77321_1_ - 1) * 10;
	}

	public int getMaxCost(int p_45002_) {
		return super.getMinCost(p_45002_) + 50;
	}

	public boolean isTreasureOnly() {
		return true;
	}

	public boolean isTradeable() {
		return false;
	}

	public boolean isDiscoverable() {
		return false;
	}

	public int getMaxLevel() {
		return 1;
	}
}
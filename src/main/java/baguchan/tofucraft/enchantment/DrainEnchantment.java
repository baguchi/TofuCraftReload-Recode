package baguchan.tofucraft.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;

public class DrainEnchantment extends Enchantment {

	public DrainEnchantment(EnchantmentDefinition p_335940_) {
		super(p_335940_);
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
}
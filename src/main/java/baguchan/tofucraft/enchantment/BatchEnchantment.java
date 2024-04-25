package baguchan.tofucraft.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;

public class BatchEnchantment extends Enchantment {


	public BatchEnchantment(EnchantmentDefinition p_335940_) {
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
package baguchan.tofucraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public class SeasoningItem extends Item {
	public SeasoningItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isEnchantable(ItemStack p_41456_) {
		return false;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

	@Override
	public boolean canBeDepleted() {
		return false;
	}

	@Override
	public boolean isDamageable(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		itemStack.setDamageValue(itemStack.getDamageValue() + 1);
		if (itemStack.getDamageValue() > itemStack.getMaxDamage()) {
			itemStack.shrink(1);
			return new ItemStack(Items.GLASS_BOTTLE);
		}

		return itemStack.copy();
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}
}

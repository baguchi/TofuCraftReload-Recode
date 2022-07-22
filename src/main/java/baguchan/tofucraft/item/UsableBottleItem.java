package baguchan.tofucraft.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class UsableBottleItem extends Item {
	public UsableBottleItem(Properties properties) {
		super(properties);
	}

	@Override
	public void onCraftedBy(ItemStack p_41447_, Level p_41448_, Player p_41449_) {
		super.onCraftedBy(p_41447_, p_41448_, p_41449_);
		p_41447_.hurtAndBreak(1, p_41449_, player -> {
			player.broadcastBreakEvent(InteractionHand.MAIN_HAND);
		});
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canBeDepleted() {
		return false;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		return itemStack;
	}
}


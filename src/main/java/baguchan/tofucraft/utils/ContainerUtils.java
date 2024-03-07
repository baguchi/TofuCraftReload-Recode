package baguchan.tofucraft.utils;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ContainerUtils {

	//creative behavior or add item
	public static void addWithContainer(Player player, InteractionHand hand, ItemStack itemHand, ItemStack bottle, boolean canAdd) {
		if (!player.isCreative() || canAdd) {
			itemHand.shrink(1);
		}
		if (itemHand.isEmpty()) {
			player.setItemInHand(hand, bottle);
		} else if ((!player.isCreative() || canAdd) && !player.getInventory().add(bottle)) {
			player.drop(bottle, false);
		}
	}
}

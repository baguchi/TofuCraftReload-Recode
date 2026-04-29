package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuDataComponents;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KoujiBaseItem extends Item {
	public KoujiBaseItem(Properties tab) {
		super(tab);
	}


	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity p_41406_, int p_41407_, boolean p_41408_) {
		super.inventoryTick(stack, level, p_41406_, p_41407_, p_41408_);
		long minutes = (level.getGameTime() / 1200);

		long fermentationData = stack.getOrDefault(TofuDataComponents.FERMENTATION_DATA, minutes);
		if (p_41406_ instanceof Player player) {
			long storedMinutes = fermentationData;
			if (storedMinutes > minutes + 1200 * 5) {
				ItemStack newstack = new ItemStack(TofuItems.KOUJI.get(), 1);
				stack.shrink(1);
				player.getInventory().add(newstack);
			}

			//Prevent immediate syncing when items are added to the inventory.
			if (stack.getPopTime() <= 0) {
				if (level.getGameTime() % 20 == 0 && (!stack.has(TofuDataComponents.FERMENTATION_DATA) || storedMinutes - 1 > minutes)) {
					stack.set(TofuDataComponents.FERMENTATION_DATA, storedMinutes);
				}
			}
		}
	}

	@Override
	public void onCraftedPostProcess(ItemStack stack, Level level) {
		super.onCraftedPostProcess(stack, level);
		long minutes = (level.getGameTime() / 1200);

		stack.set(TofuDataComponents.FERMENTATION_DATA, minutes);
	}
}
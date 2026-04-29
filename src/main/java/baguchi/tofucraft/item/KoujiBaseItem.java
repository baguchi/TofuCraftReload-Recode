package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class KoujiBaseItem extends Item {
	public KoujiBaseItem(Properties tab) {
		super(tab);
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel serverLevel, Entity p_41406_, @Nullable EquipmentSlot p_401900_) {
		super.inventoryTick(stack, serverLevel, p_41406_, p_401900_);
		long minutes = (serverLevel.getGameTime() / 1200);

		long fermentationData = stack.getOrDefault(TofuDataComponents.FERMENTATION_DATA, minutes);
		if (p_41406_ instanceof Player player) {
			long storedMinutes = fermentationData;
			if (storedMinutes > minutes + 5) {
				ItemStack newstack = new ItemStack(TofuItems.KOUJI.get(), 1);
				stack.shrink(1);
				player.getInventory().add(newstack);
			}

			//Prevent immediate syncing when items are added to the inventory.
			if (stack.getPopTime() <= 0) {
				if (serverLevel.getGameTime() % 20 == 0 && (!stack.has(TofuDataComponents.FERMENTATION_DATA) || storedMinutes - 1 > minutes)) {
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

	private void updateTags(int tick, CompoundTag p_40735_) {
		p_40735_.putInt("Fermentation", tick);
	}
}

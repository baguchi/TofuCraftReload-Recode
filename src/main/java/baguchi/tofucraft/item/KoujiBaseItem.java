package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.nbt.CompoundTag;
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
	public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
		super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
		Integer fermentationData = p_41404_.getOrDefault(TofuDataComponents.FERMENTATION_DATA, 0);
		if (p_41406_ instanceof Player) {
			Player player = (Player) p_41406_;
			int ticks = fermentationData;
				if (ticks > 2400) {
					ItemStack newstack = new ItemStack(TofuItems.KOUJI.get(), 1);
					p_41404_.shrink(1);
					player.getInventory().add(newstack);
				}
			p_41404_.set(TofuDataComponents.FERMENTATION_DATA, ticks + 1);
		}
	}

	private void updateTags(int tick, CompoundTag p_40735_) {
		p_40735_.putInt("Fermentation", tick);
	}
}

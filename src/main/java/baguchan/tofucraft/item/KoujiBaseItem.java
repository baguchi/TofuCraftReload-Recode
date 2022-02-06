package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuItems;
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
		CompoundTag compoundtag = p_41404_.getOrCreateTag();
		if (p_41406_ instanceof Player) {
			Player player = (Player) p_41406_;
			if (compoundtag.contains("Fermentation")) {
				int ticks = compoundtag.getInt("Fermentation");
				if (ticks > 2400) {
					ItemStack newstack = new ItemStack(TofuItems.KOUJI, 1);
					p_41404_.shrink(1);
					player.getInventory().add(newstack);
				}
				updateTags(ticks + 1, compoundtag);
			} else {
				updateTags(0, compoundtag);
			}
		}
	}

	private void updateTags(int tick, CompoundTag p_40735_) {
		p_40735_.putInt("Fermentation", tick);
	}
}

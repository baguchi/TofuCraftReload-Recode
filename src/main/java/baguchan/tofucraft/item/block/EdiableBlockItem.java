package baguchan.tofucraft.item.block;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class EdiableBlockItem extends BlockItem {
	public EdiableBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
		return 100;
	}
}

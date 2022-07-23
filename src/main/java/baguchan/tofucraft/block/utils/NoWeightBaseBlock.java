package baguchan.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class NoWeightBaseBlock extends WeightBaseBlock {
	public NoWeightBaseBlock(Properties properties) {
		super(properties);
	}

	public boolean isUnderWeight(Level world, BlockPos pos) {
		return true;
	}
}

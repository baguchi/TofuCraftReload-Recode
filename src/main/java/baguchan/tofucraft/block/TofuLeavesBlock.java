package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TofuLeavesBlock extends LeavesBlock {
	public TofuLeavesBlock(Properties properties) {
		super(properties);
	}

	public BlockState updateShape(BlockState p_54440_, Direction p_54441_, BlockState p_54442_, LevelAccessor p_54443_, BlockPos p_54444_, BlockPos p_54445_) {
		int i = getDistanceAt(p_54442_) + 1;
		if (i != 1 || p_54440_.getValue(DISTANCE) != i) {
			p_54443_.getBlockTicks().scheduleTick(p_54444_, this, 1);
		}

		return p_54440_;
	}

	private static BlockState updateDistance(BlockState p_54436_, LevelAccessor p_54437_, BlockPos p_54438_) {
		int i = 7;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (Direction direction : Direction.values()) {
			blockpos$mutableblockpos.setWithOffset(p_54438_, direction);
			i = Math.min(i, getDistanceAt(p_54437_.getBlockState(blockpos$mutableblockpos)) + 1);
			if (i == 1) {
				break;
			}
		}

		return p_54436_.setValue(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistanceAt(BlockState p_54464_) {
		if (p_54464_.is(TofuBlocks.ISHITOFU)) {
			return 0;
		} else {
			return p_54464_.getBlock() instanceof LeavesBlock ? p_54464_.getValue(DISTANCE) : 7;
		}
	}
}

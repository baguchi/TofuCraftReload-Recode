package baguchan.tofucraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TofuGrilledBlock extends RotatedPillarBlock {
	public TofuGrilledBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
		return true;
	}
}

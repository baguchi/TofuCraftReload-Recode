package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LeekBlock extends BushBlock {
	public LeekBlock(Properties p_51021_) {
		super(p_51021_);
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(TofuBlocks.TOFU_TERRAIN.get()) || p_51042_.is(TofuBlocks.MOMENTOFU.get());
	}
}

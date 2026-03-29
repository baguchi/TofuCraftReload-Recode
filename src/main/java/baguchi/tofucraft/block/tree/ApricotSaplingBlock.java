package baguchi.tofucraft.block.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class ApricotSaplingBlock extends SaplingBlock {
	public ApricotSaplingBlock(TreeGrower p_55978_, Properties p_55979_) {
		super(p_55978_, p_55979_);
	}

	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return blockState.is(Blocks.DIRT) || blockState.is(Blocks.GRASS_BLOCK);
	}
}

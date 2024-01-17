package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TofuMushroomBlock extends TofuSaplingBlock {
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 11.0D, 12.0D);


	public TofuMushroomBlock(TreeGrower tofuTreeGrowers, Properties properties) {
		super(tofuTreeGrowers, properties);
	}

	@Override
	public void randomTick(BlockState p_222011_, ServerLevel p_222012_, BlockPos p_222013_, RandomSource p_222014_) {
	}

	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
		return SHAPE;
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}
}

package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.api.tfenergy.IAnntena;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TFAntennaBlock extends Block implements IAnntena {
	private static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);


	public TFAntennaBlock(Properties properties) {
		super(properties);
	}

	@Override
	public double getRadius(BlockPos pos, Level world) {
		return 16;
	}

	@Override
	public int getPower(BlockPos pos, Level world) {
		return 20;
	}

	@Override
	protected VoxelShape getShape(BlockState p_48816_, BlockGetter p_48817_, BlockPos p_48818_, CollisionContext p_48819_) {
		return SHAPE;
	}

	@Override
	protected BlockState updateShape(BlockState p_60541_, LevelReader p_374332_, ScheduledTickAccess p_374457_, BlockPos p_60545_, Direction p_60542_, BlockPos p_60546_, BlockState p_60543_, RandomSource p_374120_) {
		return p_60542_ == Direction.DOWN && !this.canSurvive(p_60541_, p_374332_, p_60545_)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(p_60541_, p_374332_, p_374457_, p_60545_, p_60542_, p_60546_, p_60543_, p_374120_);
	}

	@Override
	protected boolean canSurvive(BlockState p_304413_, LevelReader p_304885_, BlockPos p_304808_) {
		return canSupportCenter(p_304885_, p_304808_.below(), Direction.UP);
	}
}

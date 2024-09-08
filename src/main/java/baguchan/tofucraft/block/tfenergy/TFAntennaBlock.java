package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.api.tfenergy.IAnntena;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
}

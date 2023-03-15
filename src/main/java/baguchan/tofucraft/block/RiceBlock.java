package baguchan.tofucraft.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class RiceBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public RiceBlock(BlockBehaviour.Properties p_53976_) {
		super(p_53976_);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

}

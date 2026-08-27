package baguchi.tofucraft.block.tfenergy;

import baguchi.tofucraft.blockentity.tfenergy.TFSaturatorBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;

public class TFSaturatorBlock extends TFBaseEntityBlock {
	public static final MapCodec<TFSaturatorBlock> CODEC = simpleCodec(TFSaturatorBlock::new);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	public TFSaturatorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
		return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
		return p_48722_.setValue(FACING, p_48723_.rotate(p_48722_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
		return p_48719_.rotate(p_48720_.getRotation(p_48719_.getValue(FACING)));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	public RenderShape getRenderShape(BlockState p_48727_) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
		p_48725_.add(FACING, LIT);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TFSaturatorBlockEntity(p_153215_, p_153216_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152382_, BlockState p_152383_, BlockEntityType<T> p_152384_) {
		return createTicker(p_152382_, p_152384_, TofuBlockEntitys.TF_SATURATOR.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends TFSaturatorBlockEntity> p_151990_) {
		return createTickerHelper(p_151989_, p_151990_, TFSaturatorBlockEntity::tick);
	}
}
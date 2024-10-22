package baguchi.tofucraft.block;

import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class FoodPlateBlock extends BaseEntityBlock {
	public static final MapCodec<FoodPlateBlock> CODEC = simpleCodec(FoodPlateBlock::new);
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);

	public FoodPlateBlock(Properties p_49224_) {
		super(p_49224_);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		BlockEntity tileEntity = level.getBlockEntity(blockPos);
		if (tileEntity instanceof FoodPlateBlockEntity) {
			FoodPlateBlockEntity plateBlockEntity = (FoodPlateBlockEntity) tileEntity;
			ItemStack heldStack = player.getItemInHand(hand);

			if (plateBlockEntity.isEmpty()) {
				if (heldStack.isEmpty()) {
					return InteractionResult.TRY_WITH_EMPTY_HAND;
				} else if (plateBlockEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
					level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
					return InteractionResult.SUCCESS;
				}
			} else if (!heldStack.isEmpty()) {
				return InteractionResult.CONSUME;
			} else if (hand.equals(InteractionHand.MAIN_HAND)) {
				if (!player.isCreative()) {
					if (!player.getInventory().add(plateBlockEntity.removeItem())) {
						Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), plateBlockEntity.removeItem());
					}
				} else {
					plateBlockEntity.removeItem();
				}
				level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.WOOD_HIT, SoundSource.BLOCKS, 0.25F, 0.5F);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}

	@Override
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileEntity = worldIn.getBlockEntity(pos);
			if (tileEntity instanceof FoodPlateBlockEntity) {
				Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((FoodPlateBlockEntity) tileEntity).getStoredItem());
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof FoodPlateBlockEntity) {
			return !((FoodPlateBlockEntity) tileEntity).isEmpty() ? 15 : 0;
		}
		return 0;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return TofuBlockEntitys.FOODPLATE.get().create(pos, state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess p_374457_, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource randomSource) {
		return facing == Direction.DOWN && !state.canSurvive(worldIn, currentPos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, worldIn, p_374457_, currentPos, facing, facingPos, facingState, randomSource);
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState pState, Rotation pRot) {
		return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}

}

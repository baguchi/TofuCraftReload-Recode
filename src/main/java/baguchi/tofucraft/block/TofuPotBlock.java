package baguchi.tofucraft.block;

import baguchi.tofucraft.blockentity.TofuPotBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

import javax.annotation.Nullable;


public class TofuPotBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<TofuPotBlock> CODEC = simpleCodec(TofuPotBlock::new);

	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0D, -1.0D, 0.0D, 16.0D, 0.0D, 16.0D));

	public TofuPotBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public InteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

		boolean flag = false;
		BlockEntity tileEntity = level.getBlockEntity(pos);
		if (tileEntity instanceof TofuPotBlockEntity) {

			IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldStack.copyWithCount(1)).orElse(null);
			if (handler instanceof FluidBucketWrapper) {
				FluidUtil.interactWithFluidHandler(player, hand, level, pos, null);

				return InteractionResult.SUCCESS;
			}
		}

		if (!level.isClientSide) {
			if (tileEntity instanceof TofuPotBlockEntity cookingPotEntity) {

				player.openMenu(cookingPotEntity, pos);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();
		FluidState fluid = level.getFluidState(context.getClickedPos());

		BlockState state = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);

		return state;
	}


	@Override
	protected BlockState updateShape(
			BlockState p_53160_,
			LevelReader p_374322_,
			ScheduledTickAccess p_374149_,
			BlockPos p_53164_,
			Direction p_53161_,
			BlockPos p_53165_,
			BlockState p_53162_,
			RandomSource p_374199_
	) {
		if (p_53160_.getValue(WATERLOGGED)) {
			p_374149_.scheduleTick(p_53164_, Fluids.WATER, Fluids.WATER.getTickDelay(p_374322_));
		}

		return super.updateShape(p_53160_, p_374322_, p_374149_, p_53164_, p_53161_, p_53165_, p_53162_, p_374199_);
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState p_394424_, ServerLevel p_394241_, BlockPos p_393520_, boolean p_394545_) {
		super.affectNeighborsAfterRemoval(p_394424_, p_394241_, p_393520_, p_394545_);

		BlockEntity blockentity = p_394241_.getBlockEntity(p_393520_);
		if (blockentity instanceof TofuPotBlockEntity tofuPotBlockEntity) {
			if (p_394241_ instanceof ServerLevel) {
				Containers.dropContents(p_394241_, p_393520_, (TofuPotBlockEntity) blockentity);
				tofuPotBlockEntity.getUsedRecipesAndPopExperience(p_394241_, Vec3.atCenterOf(p_393520_));

			}

			p_394241_.updateNeighbourForOutputSignal(p_393520_, this);
		}

	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		BlockEntity tileEntity = level.getBlockEntity(pos);
		if (tileEntity instanceof TofuPotBlockEntity cookingPotEntity && cookingPotEntity.isHeated()) {
			SoundEvent boilSound = !cookingPotEntity.getItem(12).isEmpty()
					? SoundEvents.LAVA_POP
					: SoundEvents.BUBBLE_COLUMN_BUBBLE_POP;
			double x = (double) pos.getX() + 0.5D;
			double y = pos.getY();
			double z = (double) pos.getZ() + 0.5D;
			if (random.nextInt(10) == 0) {
				level.playLocalSound(x, y, z, boilSound, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.2F + 0.9F, false);
			}
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	/*@Override
	public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
		BlockEntity tileEntity = level.getBlockEntity(pos);
		if (tileEntity instanceof TofuPotBlockEntity) {
			ItemStackHandler inventory = ((TofuPotBlockEntity) tileEntity).getInventory();
			return MathUtils.calcRedstoneFromItemHandler(inventory);
		}
		return 0;
	}*/

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return TofuBlockEntitys.TOFU_POT.get().create(pos, state);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
		if (level.isClientSide) {
			return createTickerHelper(blockEntity, TofuBlockEntitys.TOFU_POT.get(), TofuPotBlockEntity::animationTick);
		}
		return createTickerHelper(blockEntity, TofuBlockEntitys.TOFU_POT.get(), TofuPotBlockEntity::cookingTick);
	}
}
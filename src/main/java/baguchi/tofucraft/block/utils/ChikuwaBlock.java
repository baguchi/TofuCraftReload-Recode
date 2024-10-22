package baguchi.tofucraft.block.utils;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ChikuwaBlock extends Block implements Fallable, SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	private static final VoxelShape TIP_X = Block.box(0.0, 0.0, 5.0, 16.0, 5.0, 11.0);
	private static final VoxelShape TIP_2_X = Block.box(0.0, 11.0, 5.0, 16.0, 16.0, 11.0);
	private static final VoxelShape BASE_X = Block.box(0, 0, 0, 16.0, 16.0, 5.0);
	private static final VoxelShape BASE_2_X = Block.box(0, 0, 11, 16, 16, 16);
	private static final VoxelShape X_AXIS_AABB = Shapes.or(TIP_X, TIP_2_X, BASE_X, BASE_2_X);

	private static final VoxelShape TIP_Z = Block.box(5.0, 0.0, 0.0, 11.0, 5.0, 16.0);
	private static final VoxelShape TIP_2_Z = Block.box(5.0, 11.0, 0.0, 11.0, 16.0, 16.0);
	private static final VoxelShape BASE_Z = Block.box(0, 0, 0, 5.0, 16.0, 16.0);
	private static final VoxelShape BASE_2_Z = Block.box(11, 0, 0, 16, 16, 16);
	private static final VoxelShape Z_AXIS_AABB = Shapes.or(TIP_Z, TIP_2_Z, BASE_Z, BASE_2_Z);

	public ChikuwaBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	protected VoxelShape getShape(BlockState p_48816_, BlockGetter p_48817_, BlockPos p_48818_, CollisionContext p_48819_) {
		Direction direction = p_48816_.getValue(FACING);
		return direction.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
	}

	public boolean canChikuwaConnectTo(Level level, BlockPos blockPos, BlockState blockState, Direction dir) {
		int anotherX = blockPos.getX() + dir.getStepX();
		int anotherY = blockPos.getY() + dir.getStepY();
		int anotherZ = blockPos.getZ() + dir.getStepZ();

		BlockState anotherBlock = level.getBlockState(new BlockPos(anotherX, anotherY, anotherZ));
		if (anotherBlock.getBlock() == blockState.getBlock()) {
			if (anotherBlock.hasProperty(FACING)) {
				return anotherBlock.getValue(FACING) == blockState.getValue(FACING);
			}
		}
		return false;
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState blockState, Entity p_152434_) {
		super.stepOn(level, pos, blockState, p_152434_);
		if (!p_152434_.isSteppingCarefully() && !p_152434_.getType().is(TofuTags.EntityTypes.WALKABLE_WITHOUT_TRIGGER)) {
			if (isFree(level.getBlockState(pos.below()))) {
				if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
					this.triggerChikuwa(level, pos, blockState);
				}
			}
		}
	}

	private void triggerChikuwa(Level level, BlockPos pos, BlockState blockState) {
		if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
			level.scheduleTick(pos, this, 10);
			if (this.canChikuwaConnectTo(level, pos, blockState, blockState.getValue(FACING))) {
				if (!level.getBlockTicks().hasScheduledTick(pos.relative(blockState.getValue(FACING)), this)) {
					level.scheduleTick(pos.relative(blockState.getValue(FACING)), this, 11);
				}
			}
			if (this.canChikuwaConnectTo(level, pos, blockState, blockState.getValue(FACING).getOpposite())) {
				if (!level.getBlockTicks().hasScheduledTick(pos.relative(blockState.getValue(FACING).getOpposite()), this)) {
					level.scheduleTick(pos.relative(blockState.getValue(FACING).getOpposite()), this, 11);
				}
			}

		}
	}

	@Override
	protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource p_221127_) {
		if (isFree(serverLevel.getBlockState(blockPos.below())) && blockPos.getY() >= serverLevel.getMinY()) {
			if (this.canChikuwaConnectTo(serverLevel, blockPos, blockState, blockState.getValue(FACING))) {
				if (!serverLevel.getBlockTicks().hasScheduledTick(blockPos.relative(blockState.getValue(FACING)), this)) {
					serverLevel.scheduleTick(blockPos.relative(blockState.getValue(FACING)), this, 1);
				}
			}
			if (this.canChikuwaConnectTo(serverLevel, blockPos, blockState, blockState.getValue(FACING).getOpposite())) {
				if (!serverLevel.getBlockTicks().hasScheduledTick(blockPos.relative(blockState.getValue(FACING).getOpposite()), this)) {
					serverLevel.scheduleTick(blockPos.relative(blockState.getValue(FACING).getOpposite()), this, 1);
				}
			}
			FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, blockPos, blockState);
			this.falling(fallingblockentity);
		}
	}

	protected void falling(FallingBlockEntity p_53206_) {
	}

	public static boolean isFree(BlockState p_53242_) {
		return p_53242_.isAir() || p_53242_.is(BlockTags.FIRE) || p_53242_.liquid() || p_53242_.canBeReplaced();
	}

	@Override
	public void animateTick(BlockState p_221129_, Level p_221130_, BlockPos p_221131_, RandomSource p_221132_) {
		if (p_221130_.getBlockTicks().hasScheduledTick(p_221131_, this)) {
			BlockPos blockpos = p_221131_.below();
			if (isFree(p_221130_.getBlockState(blockpos))) {
				ParticleUtils.spawnParticleBelow(p_221130_, p_221131_, p_221132_, new BlockParticleOption(ParticleTypes.BLOCK, p_221129_));
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_277652_) {
		super.createBlockStateDefinition(p_277652_);
		p_277652_.add(FACING);
		p_277652_.add(WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
		FluidState fluidstate = p_48689_.getLevel().getFluidState(p_48689_.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.WATER;
		return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
	}

	@Override
	protected BlockState updateShape(
			BlockState p_296123_,
			LevelReader p_374369_,
			ScheduledTickAccess p_374403_,
			BlockPos p_294499_,
			Direction p_294509_,
			BlockPos p_295044_,
			BlockState p_296367_,
			RandomSource p_374441_
	) {
		if (p_296123_.getValue(WATERLOGGED)) {
			p_374403_.scheduleTick(p_294499_, Fluids.WATER, Fluids.WATER.getTickDelay(p_374369_));
		}

		return super.updateShape(p_296123_, p_374369_, p_374403_, p_294499_, p_294509_, p_295044_, p_296367_, p_374441_);
	}


	@Override
	protected FluidState getFluidState(BlockState p_152158_) {
		return p_152158_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152158_);
	}

	@Override
	public BlockState rotate(BlockState p_277545_, Rotation p_277482_) {
		return p_277545_.setValue(FACING, p_277482_.rotate(p_277545_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_277615_, Mirror p_277916_) {
		return p_277615_.rotate(p_277916_.getRotation(p_277615_.getValue(FACING)));
	}

	@Override
	public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return PathType.DAMAGE_OTHER;
	}

	@Override
	public @Nullable PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
		return PathType.DANGER_OTHER;
	}
}

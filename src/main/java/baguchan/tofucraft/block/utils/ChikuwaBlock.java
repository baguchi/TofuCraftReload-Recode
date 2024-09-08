package baguchan.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class ChikuwaBlock extends Block implements Fallable {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public ChikuwaBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected void onPlace(BlockState p_53233_, Level p_53234_, BlockPos p_53235_, BlockState p_53236_, boolean p_53237_) {
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
	public void fallOn(Level level, BlockState p_152427_, BlockPos pos, Entity p_152429_, float p_152430_) {
		super.fallOn(level, p_152427_, pos, p_152429_, p_152430_);
		if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
			this.triggerChikuwa(level, pos, p_152427_);
		}
	}

	private void triggerChikuwa(Level level, BlockPos pos, BlockState blockState) {
		if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
			level.scheduleTick(pos, this, 10);
		}
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			if (this.canChikuwaConnectTo(level, pos, blockState, dir)) {
				BlockState blockState1 = level.getBlockState(pos.relative(dir));
				this.triggerChikuwa(level, pos.relative(dir), blockState1);
			}
			if (this.canChikuwaConnectTo(level, pos, blockState, dir.getOpposite())) {
				BlockState blockState1 = level.getBlockState(pos.relative(dir.getOpposite()));
				this.triggerChikuwa(level, pos.relative(dir.getOpposite()), blockState1);
			}
		}
	}

	@Override
	protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource p_221127_) {
		if (isFree(serverLevel.getBlockState(blockPos.below())) && blockPos.getY() >= serverLevel.getMinBuildHeight()) {
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
	}

	@Override
	public BlockState rotate(BlockState p_277545_, Rotation p_277482_) {
		return p_277545_.setValue(FACING, p_277482_.rotate(p_277545_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_277615_, Mirror p_277916_) {
		return p_277615_.rotate(p_277916_.getRotation(p_277615_.getValue(FACING)));
	}

}

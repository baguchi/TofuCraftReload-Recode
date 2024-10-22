package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;

public class TofuDetectorBlock extends DirectionalBlock {
	public static final MapCodec<TofuDetectorBlock> CODEC = simpleCodec(TofuDetectorBlock::new);
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public TofuDetectorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH).setValue(POWERED, Boolean.valueOf(false)));
	}

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return CODEC;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55125_) {
		p_55125_.add(FACING, POWERED);
	}

	public BlockState rotate(BlockState p_55115_, Rotation p_55116_) {
		return p_55115_.setValue(FACING, p_55116_.rotate(p_55115_.getValue(FACING)));
	}

	public BlockState mirror(BlockState p_55112_, Mirror p_55113_) {
		return p_55112_.rotate(p_55113_.getRotation(p_55112_.getValue(FACING)));
	}

	public void tick(BlockState p_221840_, ServerLevel p_221841_, BlockPos p_221842_, RandomSource p_221843_) {
		if (p_221840_.getValue(POWERED)) {
			p_221841_.setBlock(p_221842_, p_221840_.setValue(POWERED, Boolean.valueOf(false)), 2);
		} else {
			p_221841_.setBlock(p_221842_, p_221840_.setValue(POWERED, Boolean.valueOf(true)), 2);
		}

		this.updateNeighborsInFront(p_221841_, p_221842_, p_221840_);
	}

	@Override
	protected BlockState updateShape(BlockState p_60541_, LevelReader p_374332_, ScheduledTickAccess p_374457_, BlockPos p_60545_, Direction p_60542_, BlockPos p_60546_, BlockState p_60543_, RandomSource p_374120_) {
		if (p_60541_.getValue(FACING) == p_60542_) {
			Direction direction = p_60541_.getValue(FACING);
			BlockPos blockpos = p_60545_.relative(direction);
			this.startSignal(p_374332_, p_374457_, p_60545_, p_60541_, p_374332_.getBlockState(blockpos));
		}

		return super.updateShape(p_60541_, p_374332_, p_374457_, p_60545_, p_60542_, p_60546_, p_60543_, p_374120_);
	}

	private void startSignal(LevelReader p_55093_, ScheduledTickAccess p_374457_, BlockPos p_55094_, BlockState p_55118_, BlockState p_55120_) {
		if (!p_55093_.isClientSide() && !p_374457_.getBlockTicks().hasScheduledTick(p_55094_, this)) {
			if (!p_55118_.getValue(POWERED) && (p_55120_.getBlock() instanceof TofuBlock || p_55120_.is(TofuTags.Blocks.SOFT_TOFU))) {
				p_374457_.scheduleTick(p_55094_, this, 2);
			} else if (p_55118_.getValue(POWERED) && !(p_55120_.getBlock() instanceof TofuBlock || p_55120_.is(TofuTags.Blocks.SOFT_TOFU))) {
				p_374457_.scheduleTick(p_55094_, this, 2);
			}
		}

	}

	protected void updateNeighborsInFront(Level p_55089_, BlockPos p_55090_, BlockState p_55091_) {
		Direction direction = p_55091_.getValue(FACING);
		BlockPos blockpos = p_55090_.relative(direction.getOpposite());
		Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(p_55089_, direction.getOpposite(), null);

		p_55089_.neighborChanged(blockpos, this, orientation);
		p_55089_.updateNeighborsAtExceptFromFacing(blockpos, this, direction, orientation);
	}

	public boolean isSignalSource(BlockState p_55138_) {
		return true;
	}

	public int getDirectSignal(BlockState p_55127_, BlockGetter p_55128_, BlockPos p_55129_, Direction p_55130_) {
		return p_55127_.getSignal(p_55128_, p_55129_, p_55130_);
	}

	public int getSignal(BlockState p_55101_, BlockGetter p_55102_, BlockPos p_55103_, Direction p_55104_) {
		return p_55101_.getValue(POWERED) && p_55101_.getValue(FACING) == p_55104_ ? 15 : 0;
	}

	public void onPlace(BlockState p_55132_, Level p_55133_, BlockPos p_55134_, BlockState p_55135_, boolean p_55136_) {
		if (!p_55132_.is(p_55135_.getBlock())) {
			if (!p_55133_.isClientSide() && p_55132_.getValue(POWERED) && !p_55133_.getBlockTicks().hasScheduledTick(p_55134_, this)) {
				BlockState blockstate = p_55132_.setValue(POWERED, Boolean.valueOf(false));
				p_55133_.setBlock(p_55134_, blockstate, 18);
				this.updateNeighborsInFront(p_55133_, p_55134_, blockstate);
			}

		}
	}

	public void onRemove(BlockState p_55106_, Level p_55107_, BlockPos p_55108_, BlockState p_55109_, boolean p_55110_) {
		if (!p_55106_.is(p_55109_.getBlock())) {
			if (!p_55107_.isClientSide && p_55106_.getValue(POWERED) && p_55107_.getBlockTicks().hasScheduledTick(p_55108_, this)) {
				this.updateNeighborsInFront(p_55107_, p_55108_, p_55106_.setValue(POWERED, Boolean.valueOf(false)));
			}

		}
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_55087_) {
		return this.defaultBlockState().setValue(FACING, p_55087_.getNearestLookingDirection().getOpposite().getOpposite());
	}
}
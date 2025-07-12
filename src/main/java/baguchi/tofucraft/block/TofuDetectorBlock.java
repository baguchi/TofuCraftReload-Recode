package baguchi.tofucraft.block;

import baguchi.tofucraft.datamap.TofuHarden;
import baguchi.tofucraft.registry.TofuDataMaps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;

public class TofuDetectorBlock extends DirectionalBlock {
	public static final MapCodec<TofuDetectorBlock> CODEC = simpleCodec(TofuDetectorBlock::new);
	public static final IntegerProperty POWER = BlockStateProperties.POWER;

	public TofuDetectorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH).setValue(POWER, 0));
	}

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55125_) {
		p_55125_.add(FACING, POWER);
	}

	@Override
	public BlockState rotate(BlockState p_55115_, Rotation p_55116_) {
		return p_55115_.setValue(FACING, p_55116_.rotate(p_55115_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_55112_, Mirror p_55113_) {
		return p_55112_.rotate(p_55113_.getRotation(p_55112_.getValue(FACING)));
	}

	@Override
	public void tick(BlockState p_221840_, ServerLevel p_221841_, BlockPos p_221842_, RandomSource p_221843_) {
		Direction direction = p_221840_.getValue(FACING);
		BlockPos blockpos = p_221842_.relative(direction);
		updateSignalStrength(p_221840_, p_221841_.getBlockState(blockpos), p_221841_, p_221842_);

	}

	@Override
	protected BlockState updateShape(BlockState p_60541_, LevelReader p_374332_, ScheduledTickAccess p_374457_, BlockPos p_60545_, Direction p_60542_, BlockPos p_60546_, BlockState p_60543_, RandomSource p_374120_) {
		if (p_60541_.getValue(FACING) == p_60542_ && !p_374457_.getBlockTicks().hasScheduledTick(p_60545_, this)) {
			p_374457_.scheduleTick(p_60545_, this, 2);
		}

		return super.updateShape(p_60541_, p_374332_, p_374457_, p_60545_, p_60542_, p_60546_, p_60543_, p_374120_);
	}

	@Override
	public boolean isSignalSource(BlockState p_55138_) {
		return true;
	}

	@Override
	protected int getDirectSignal(BlockState p_55127_, BlockGetter p_55128_, BlockPos p_55129_, Direction p_55130_) {
		return p_55127_.getSignal(p_55128_, p_55129_, p_55130_);
	}

	@Override
	protected int getSignal(BlockState p_52386_, BlockGetter p_52387_, BlockPos p_52388_, Direction p_52389_) {
		return p_52386_.getValue(FACING) == p_52389_ ? p_52386_.getValue(POWER) : 0;
	}

	private void updateSignalStrength(BlockState p_52411_, BlockState faced, Level p_52412_, BlockPos p_52413_) {
		TofuHarden harden = TofuDataMaps.HARDEN_DATA.get(faced.getBlock());

		int i = harden != null ? harden.level() : 0;
		i = Mth.clamp(i, 0, 15);
		if (p_52411_.getValue(POWER) != i) {
			p_52412_.setBlock(p_52413_, p_52411_.setValue(POWER, i), 18);
			Direction direction = p_52411_.getValue(FACING);
			BlockPos blockpos = p_52413_.relative(direction.getOpposite());

			Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(p_52412_, direction.getOpposite(), null);

			p_52412_.neighborChanged(blockpos, this, orientation);
			p_52412_.updateNeighborsAtExceptFromFacing(blockpos, this, direction, orientation);

		}

	}

	protected void updateNeighborsInFront(Level p_55089_, BlockPos p_55090_, BlockState p_55091_) {
		Direction direction = p_55091_.getValue(FACING);
		BlockPos blockpos = p_55090_.relative(direction.getOpposite());
		Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(p_55089_, direction.getOpposite(), null);
		p_55089_.neighborChanged(blockpos, this, orientation);
		p_55089_.updateNeighborsAtExceptFromFacing(blockpos, this, direction, orientation);
	}

	@Override
	protected void onPlace(BlockState p_55132_, Level p_55133_, BlockPos p_55134_, BlockState p_55135_, boolean p_55136_) {
		if (!p_55132_.is(p_55135_.getBlock())) {
			if (!p_55133_.isClientSide()) {
				Direction direction = p_55132_.getValue(FACING);
				BlockPos blockpos = p_55134_.relative(direction);
				updateSignalStrength(p_55132_, p_55133_.getBlockState(blockpos), p_55133_, p_55134_);

			}
		}
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState p_393998_, ServerLevel p_394664_, BlockPos p_394449_, boolean p_394257_) {
		if (p_393998_.getValue(POWER) > 0 && !p_394664_.getBlockTicks().hasScheduledTick(p_394449_, this)) {
			this.updateNeighborsInFront(p_394664_, p_394449_, p_393998_.setValue(POWER, 0));
		}
	}


	public BlockState getStateForPlacement(BlockPlaceContext p_55087_) {
		return this.defaultBlockState().setValue(FACING, p_55087_.getNearestLookingDirection().getOpposite().getOpposite());
	}
}
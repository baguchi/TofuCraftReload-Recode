package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.FarmlandWaterManager;

public class TofuFarmlandBlock extends Block {
	public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	public static final int MAX_MOISTURE = 7;

	public TofuFarmlandBlock(BlockBehaviour.Properties p_53247_) {
		super(p_53247_);
		this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)));
	}

	@Override
	protected BlockState updateShape(BlockState p_60541_, LevelReader p_374332_, ScheduledTickAccess p_374457_, BlockPos p_60545_, Direction p_60542_, BlockPos p_60546_, BlockState p_60543_, RandomSource p_374120_) {
		if (p_60542_ == Direction.UP && !p_60541_.canSurvive(p_374332_, p_60545_) && !p_374457_.getBlockTicks().hasScheduledTick(p_60545_, this)) {
			p_374457_.scheduleTick(p_60545_, this, 1);
		}
		return super.updateShape(p_60541_, p_374332_, p_374457_, p_60545_, p_60542_, p_60546_, p_60543_, p_374120_);
	}

	public boolean canSurvive(BlockState p_53272_, LevelReader p_53273_, BlockPos p_53274_) {
		BlockState blockstate = p_53273_.getBlockState(p_53274_.above());
		return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_53249_) {
		return !this.defaultBlockState().canSurvive(p_53249_.getLevel(), p_53249_.getClickedPos()) ? TofuBlocks.TOFU_TERRAIN.get().defaultBlockState() : super.getStateForPlacement(p_53249_);
	}

	public boolean useShapeForLightOcclusion(BlockState p_53295_) {
		return true;
	}

	public VoxelShape getShape(BlockState p_53290_, BlockGetter p_53291_, BlockPos p_53292_, CollisionContext p_53293_) {
		return SHAPE;
	}

	public void tick(BlockState p_53262_, ServerLevel p_53263_, BlockPos p_53264_, RandomSource p_53265_) {
		if (!p_53262_.canSurvive(p_53263_, p_53264_)) {
			turnToDirt(p_53262_, p_53263_, p_53264_);
		}

	}

	public void randomTick(BlockState p_53285_, ServerLevel p_53286_, BlockPos p_53287_, RandomSource p_53288_) {
		int i = p_53285_.getValue(MOISTURE);
		if (!isNearWater(p_53286_, p_53287_) && !p_53286_.isRainingAt(p_53287_.above())) {
			if (i > 0) {
				p_53286_.setBlock(p_53287_, p_53285_.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
			} else if (!shouldMaintainFarmland(p_53286_, p_53287_)) {
				turnToDirt(p_53285_, p_53286_, p_53287_);
			}
		} else if (i < 7) {
			p_53286_.setBlock(p_53287_, p_53285_.setValue(MOISTURE, Integer.valueOf(7)), 2);
		}

	}

	@Override
	public void fallOn(Level p_153227_, BlockState p_153228_, BlockPos p_153229_, Entity p_153230_, float p_153231_) {
		if (!p_153227_.isClientSide && CommonHooks.onFarmlandTrample((ServerLevel) p_153227_, p_153229_, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), p_153231_, p_153230_)) { // Forge: Move logic to Entity#canTrample
			turnToDirt(p_153228_, p_153227_, p_153229_);
		}

		super.fallOn(p_153227_, p_153228_, p_153229_, p_153230_, p_153231_);
	}

	public static void turnToDirt(BlockState p_53297_, Level p_53298_, BlockPos p_53299_) {
		p_53298_.setBlockAndUpdate(p_53299_, pushEntitiesUp(p_53297_, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), p_53298_, p_53299_));
	}

	private static boolean shouldMaintainFarmland(BlockGetter p_279219_, BlockPos p_279209_) {
		return p_279219_.getBlockState(p_279209_.above()).is(BlockTags.MAINTAINS_FARMLAND);
	}

	private static boolean isNearWater(LevelReader p_53259_, BlockPos p_53260_) {
		for (BlockPos blockpos : BlockPos.betweenClosed(p_53260_.offset(-4, 0, -4), p_53260_.offset(4, 1, 4))) {
			if (p_53259_.getFluidState(blockpos).is(FluidTags.WATER) || p_53259_.getFluidState(blockpos).is(TofuTags.Fluids.SOYMILK)) {
				return true;
			}
		}

		return FarmlandWaterManager.hasBlockWaterTicket(p_53259_, p_53260_);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53283_) {
		p_53283_.add(MOISTURE);
	}

	@Override
	protected boolean isPathfindable(BlockState p_60475_, PathComputationType p_60478_) {
		return false;
	}
}

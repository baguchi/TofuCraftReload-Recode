package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TofuFarmlandBlock extends Block {
	public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;

	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public TofuFarmlandBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)));
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && !stateIn.canSurvive(worldIn, currentPos))
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
		BlockState blockstate = p_196260_2_.getBlockState(p_196260_3_.above());
		return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
	}

	public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
		return !this.defaultBlockState().canSurvive(p_196258_1_.getLevel(), p_196258_1_.getClickedPos()) ? Blocks.DIRT.defaultBlockState() : super.getStateForPlacement(p_196258_1_);
	}

	public boolean useShapeForLightOcclusion(BlockState p_220074_1_) {
		return true;
	}

	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}

	public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
		if (!p_225534_1_.canSurvive(p_225534_2_, p_225534_3_)) {
			turnToDirt(p_225534_1_, p_225534_2_, p_225534_3_);
		}

	}

	public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
		int i = p_225542_1_.getValue(MOISTURE);
		if (!isNearWater(p_225542_2_, p_225542_3_) && !p_225542_2_.isRainingAt(p_225542_3_.above())) {
			if (i > 0) {
				p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
			} else if (!isUnderCrops(p_225542_2_, p_225542_3_)) {
				turnToDirt(p_225542_1_, p_225542_2_, p_225542_3_);
			}
		} else if (i < 7) {
			p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(MOISTURE, Integer.valueOf(7)), 2);
		}

	}

	public void fallOn(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
		if (!p_180658_1_.isClientSide && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(p_180658_1_, p_180658_2_, Blocks.DIRT.defaultBlockState(), p_180658_4_, p_180658_3_)) { // Forge: Move logic to Entity#canTrample
			turnToDirt(p_180658_1_.getBlockState(p_180658_2_), p_180658_1_, p_180658_2_);
		}

		super.fallOn(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_);
	}

	public static void turnToDirt(BlockState p_199610_0_, World p_199610_1_, BlockPos p_199610_2_) {
		p_199610_1_.setBlockAndUpdate(p_199610_2_, pushEntitiesUp(p_199610_0_, TofuBlocks.TOFU_TERRAIN.defaultBlockState(), p_199610_1_, p_199610_2_));
	}

	private boolean isUnderCrops(IBlockReader p_176529_0_, BlockPos p_176529_1_) {
		BlockState plant = p_176529_0_.getBlockState(p_176529_1_.above());
		BlockState state = p_176529_0_.getBlockState(p_176529_1_);
		return plant.getBlock() instanceof net.minecraftforge.common.IPlantable && state.canSustainPlant(p_176529_0_, p_176529_1_, Direction.UP, (net.minecraftforge.common.IPlantable) plant.getBlock());
	}

	private static boolean isNearWater(IWorldReader p_176530_0_, BlockPos p_176530_1_) {
		for (BlockPos blockpos : BlockPos.betweenClosed(p_176530_1_.offset(-4, 0, -4), p_176530_1_.offset(4, 1, 4))) {
			if (p_176530_0_.getFluidState(blockpos).is(FluidTags.WATER)) {
				return true;
			}
		}

		return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(p_176530_0_, p_176530_1_);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.add(MOISTURE);
	}

	public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}
}

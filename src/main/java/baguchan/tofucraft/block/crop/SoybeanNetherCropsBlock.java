package baguchan.tofucraft.block.crop;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class SoybeanNetherCropsBlock extends CropsBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)};

	public SoybeanNetherCropsBlock(Properties builder) {
		super(builder);
	}

	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.is(Blocks.SOUL_SAND) || state.is(Blocks.NETHERRACK) || state.is(Blocks.CRIMSON_NYLIUM));
	}

	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1))
			return;
		int i = getAge(state);
		if (i < getMaxAge()) {
			float f = getGrowthChance(this, worldIn, pos);
			if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, (random.nextInt((int) (25.0F / f) + 1) == 0))) {
				worldIn.setBlock(pos, this.getStateForAge(i + 1), 2);
				ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
		float f = 1.0F;
		BlockPos blockpos = pos.below();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				float f1 = 0.0F;
				BlockState blockstate = worldIn.getBlockState(blockpos.offset(i, 0, j));
				if (blockstate.canSustainPlant(worldIn, blockpos.offset(i, 0, j), Direction.UP, (IPlantable) blockIn))
					f1 = 4.0F;
				if (i != 0 || j != 0)
					f1 /= 4.0F;
				f += f1;
			}
		}

		BlockPos blockpos1 = pos.north();
		BlockPos blockpos2 = pos.south();
		BlockPos blockpos3 = pos.west();
		BlockPos blockpos4 = pos.east();
		boolean flag = (blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock());
		boolean flag1 = (blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock());
		if (flag && flag1) {
			f /= 2.0F;
		} else {
			boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
			if (flag2) {
				f /= 2.0F;
			}
		}
		return f;
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		if (state.getBlock() == this)
			return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
		return mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.NETHER;
	}

	protected IItemProvider getBaseSeedId() {
		return TofuItems.SEEDS_SOYBEANS_NETHER;
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}

package baguchan.tofucraft.block.crop;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class RiceCropsBlock extends CropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)};

	public RiceCropsBlock(Properties builder) {
		super(builder);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return TofuItems.SEEDS_RICE.get();
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
		return p_52302_.is(TofuBlocks.RICE_ROOT.get());
	}

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
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

	protected static float getGrowthChance(Block blockIn, LevelReader worldIn, BlockPos pos) {
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

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.WATER;
	}

	@Override
	public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
		return SHAPES[p_52297_.getValue(AGE)];
	}
}

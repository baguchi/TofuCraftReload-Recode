package baguchi.tofucraft.block.crop;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class SoybeanNetherCropsBlock extends CropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)};

	public SoybeanNetherCropsBlock(Properties builder) {
		super(builder);
	}

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isAreaLoaded(pos, 1))
			return;
		int i = getAge(state);
		if (i < getMaxAge()) {
			float f = getGrowthSpeed(state, worldIn, pos);
			if (CommonHooks.canCropGrow(worldIn, pos, state, (random.nextInt((int) (25.0F / f) + 1) == 0))) {
				worldIn.setBlock(pos, this.getStateForAge(i + 1), 2);
				CommonHooks.fireCropGrowPost(worldIn, pos, state);
			}
		}
	}

	protected static float getGrowthSpeed(BlockState plant, ServerLevel p_52274_, BlockPos p_52275_) {
		Block p_52273_ = plant.getBlock();
		float f = 1.0F;
		BlockPos blockpos = p_52275_.below();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				float f1 = 0.0F;
				BlockState blockstate = p_52274_.getBlockState(blockpos.offset(i, 0, j));
				net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(p_52274_, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, plant);
				if (soilDecision.isDefault() ? plant.canSurvive(p_52274_, blockpos.above().offset(i, 0, j)) : soilDecision.isTrue()) {
					f1 = 1.0F;
					if (blockstate.is(Blocks.SOUL_SAND)) {
						f1 = 3.0F;
					}
				}

				if (i != 0 || j != 0) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		BlockPos blockpos1 = p_52275_.north();
		BlockPos blockpos2 = p_52275_.south();
		BlockPos blockpos3 = p_52275_.west();
		BlockPos blockpos4 = p_52275_.east();
		boolean flag = p_52274_.getBlockState(blockpos3).is(p_52273_) || p_52274_.getBlockState(blockpos4).is(p_52273_);
		boolean flag1 = p_52274_.getBlockState(blockpos1).is(p_52273_) || p_52274_.getBlockState(blockpos2).is(p_52273_);
		if (flag && flag1) {
			f /= 2.0F;
		} else {
			boolean flag2 = p_52274_.getBlockState(blockpos3.north()).is(p_52273_)
					|| p_52274_.getBlockState(blockpos4.north()).is(p_52273_)
					|| p_52274_.getBlockState(blockpos4.south()).is(p_52273_)
					|| p_52274_.getBlockState(blockpos3.south()).is(p_52273_);
			if (flag2) {
				f /= 2.0F;
			}
		}

		return f;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter p_52303_, BlockPos p_52304_) {
		return state.is(Blocks.SOUL_SAND) || state.is(Blocks.NETHERRACK) || state.is(Blocks.CRIMSON_NYLIUM);
	}

	@Override
	public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
		BlockPos blockpos = p_51030_.below();
		return this.mayPlaceOn(p_51029_.getBlockState(blockpos), p_51029_, blockpos);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return TofuItems.SEEDS_SOYBEANS_NETHER.get();
	}


	@Override
	public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
		return SHAPES[p_52297_.getValue(AGE)];
	}
}

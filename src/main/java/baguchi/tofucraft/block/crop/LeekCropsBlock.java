package baguchi.tofucraft.block.crop;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LeekCropsBlock extends CropBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)};

	public LeekCropsBlock(Properties builder) {
		super(builder);
	}

	@Override
	public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
		BlockPos blockpos = p_51030_.below();
		return this.mayPlaceOn(p_51029_.getBlockState(blockpos), p_51029_, blockpos);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter p_52303_, BlockPos p_52304_) {
		return state.is(TofuBlocks.TOFU_FARMLAND.get());
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return TofuItems.LEEK.get();
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return 3;
	}

	public void randomTick(BlockState p_49667_, ServerLevel p_49668_, BlockPos p_49669_, RandomSource p_49670_) {
		if (p_49670_.nextInt(3) != 0) {
			super.randomTick(p_49667_, p_49668_, p_49669_, p_49670_);
		}

	}

	public void growCrops(Level p_52264_, BlockPos p_52265_, BlockState p_52266_) {
		int i = this.getAge(p_52266_) + this.getBonemealAgeIncrease(p_52264_);
		int j = this.getMaxAge();
		if (i > j) {
			i = j;
		}
		p_52264_.setBlock(p_52265_, this.getStateForAge(i), 2);
	}

	protected int getBonemealAgeIncrease(Level p_49663_) {
		return super.getBonemealAgeIncrease(p_49663_) / 3;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49665_) {
		p_49665_.add(AGE);
	}

	public VoxelShape getShape(BlockState p_49672_, BlockGetter p_49673_, BlockPos p_49674_, CollisionContext p_49675_) {
		return SHAPE_BY_AGE[p_49672_.getValue(this.getAgeProperty())];
	}
}

package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TofuMushroomBlock extends TofuBushBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);

	public TofuMushroomBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public boolean placeMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
		ConfiguredFeature<?, ?> configuredfeature;
		world.removeBlock(pos, false);
		if (rand.nextInt(3) == 0) {
			configuredfeature = TofuFeatures.ZUNDA_MUSHROOM_BIG.configured(NoFeatureConfig.NONE);
		} else {
			configuredfeature = TofuFeatures.ZUNDA_MUSHROOM_SMALL.configured(NoFeatureConfig.NONE);
		}
		if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos))
			return true;
		world.setBlock(pos, state, 3);
		return false;
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
		return (p_180670_1_.random.nextFloat() < 0.45D);
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		placeMushroom(worldIn, pos, state, rand);
	}
}

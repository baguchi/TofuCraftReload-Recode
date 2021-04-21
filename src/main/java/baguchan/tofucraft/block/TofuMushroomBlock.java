package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuFeatures;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

public class TofuMushroomBlock extends TofuBushBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.func_208617_a(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);

	public TofuMushroomBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public boolean placeMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
		ConfiguredFeature<?, ?> configuredfeature;
		world.func_217377_a(pos, false);
		if (rand.nextInt(3) == 0) {
			configuredfeature = TofuFeatures.ZUNDA_MUSHROOM_BIG.func_225566_b_((IFeatureConfig) NoFeatureConfig.field_236559_b_);
		} else {
			configuredfeature = TofuFeatures.ZUNDA_MUSHROOM_SMALL.func_225566_b_((IFeatureConfig) NoFeatureConfig.field_236559_b_);
		}
		if (configuredfeature.func_242765_a((ISeedReader) world, world.func_72863_F().func_201711_g(), rand, pos))
			return true;
		world.func_180501_a(pos, state, 3);
		return false;
	}

	public boolean func_176473_a(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	public boolean func_180670_a(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return (worldIn.field_73012_v.nextFloat() < 0.45D);
	}

	public void func_225535_a_(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		placeMushroom(worldIn, pos, state, rand);
	}
}

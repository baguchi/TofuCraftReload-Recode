package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class TofuTerrainBlock extends Block implements IGrowable {
	public TofuTerrainBlock(Properties properties) {
		super(properties);
	}


	public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
		return p_176473_1_.getBlockState(p_176473_2_.above()).isAir();
	}

	public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
		return true;
	}

	public void performBonemeal(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
		BlockPos blockpos = p_225535_3_.above();
		BlockState blockstate = TofuBlocks.BLOCKLEEK.defaultBlockState();

		label48:
		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(p_225535_2_.nextInt(3) - 1, (p_225535_2_.nextInt(3) - 1) * p_225535_2_.nextInt(3) / 2, p_225535_2_.nextInt(3) - 1);
				if (!p_225535_1_.getBlockState(blockpos1.below()).is(this) || p_225535_1_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_225535_1_, blockpos1)) {
					continue label48;
				}
			}

			BlockState blockstate2 = p_225535_1_.getBlockState(blockpos1);
			if (blockstate2.is(blockstate.getBlock()) && p_225535_2_.nextInt(10) == 0) {
				((IGrowable) blockstate.getBlock()).performBonemeal(p_225535_1_, p_225535_2_, blockpos1, blockstate2);
			}

			if (blockstate2.isAir()) {
				BlockState blockstate1;
				if (p_225535_2_.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = p_225535_1_.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					ConfiguredFeature<?, ?> configuredfeature = list.get(0);
					FlowersFeature flowersfeature = (FlowersFeature) configuredfeature.feature;
					blockstate1 = flowersfeature.getRandomFlower(p_225535_2_, blockpos1, configuredfeature.config());
				} else {

					if (this == TofuBlocks.ZUNDATOFU_TERRAIN && p_225535_2_.nextInt(8) == 0) {
						blockstate1 = TofuBlocks.ZUNDATOFU_MUSHROOM.defaultBlockState();
					} else {
						blockstate1 = blockstate;
					}
				}

				if (blockstate1.canSurvive(p_225535_1_, blockpos1)) {
					p_225535_1_.setBlock(blockpos1, blockstate1, 3);
				}
			}
		}

	}
}

package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;

public class TofuTerrainBlock extends Block implements IGrowable {
	public TofuTerrainBlock(Properties properties) {
		super(properties);
	}

	public boolean func_176473_a(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.func_177984_a()).func_196958_f();
	}

	public boolean func_180670_a(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void func_225535_a_(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.func_177984_a();
		BlockState blockstate = TofuBlocks.BLOCKLEEK.func_176223_P();
		int i;
		label28:
		for (i = 0; i < 128; i++) {
			BlockPos blockpos1 = blockpos;
			for (int j = 0; j < i / 16; ) {
				blockpos1 = blockpos1.func_177982_a(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (worldIn.getBlockState(blockpos1.func_177977_b()).func_203425_a(this)) {
					if (worldIn.getBlockState(blockpos1).func_235785_r_((IBlockReader) worldIn, blockpos1))
						continue label28;
					j++;
				}
				continue label28;
			}
			BlockState blockstate2 = worldIn.getBlockState(blockpos1);
			if (blockstate2.func_196958_f()) {
				BlockState blockstate1;
				if (rand.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = worldIn.func_226691_t_(blockpos1).func_242440_e().func_242496_b();
					if (list.isEmpty())
						continue;
					ConfiguredFeature<?, ?> configuredfeature = list.get(0);
					FlowersFeature flowersfeature = (FlowersFeature) configuredfeature.field_222737_a;
					blockstate1 = flowersfeature.func_225562_b_(rand, blockpos1, configuredfeature.func_242767_c());
				} else {
					blockstate1 = blockstate;
				}
				if (blockstate1.func_196955_c((IWorldReader) worldIn, blockpos1))
					worldIn.func_180501_a(blockpos1, blockstate1, 3);
			}
			continue;
		}
	}
}

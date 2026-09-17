package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;

import java.util.List;
import java.util.Optional;

public class TofuTerrainBlock extends Block implements BonemealableBlock {
	public TofuTerrainBlock(Properties properties) {
		super(properties);
	}


	public static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = levelReader.getBlockState(blockpos);
		if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		} else if (blockstate.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LightEngine.getLightDampeningInto(state, blockstate, Direction.UP, blockstate.getLightDampening());
			return i < 15;
		}
	}


	@Override
	public void randomTick(BlockState p_222508_, ServerLevel p_222509_, BlockPos p_222510_, RandomSource p_222511_) {
		if (!canBeGrass(p_222508_, p_222509_, p_222510_) && p_222508_.is(TofuBlocks.TOFU_TERRAIN_ZUNDA.get())) {
			if (!p_222509_.isAreaLoaded(p_222510_, 1))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			p_222509_.setBlockAndUpdate(p_222510_, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState());
		}
	}


	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, BonemealSource bonemealSource) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState, BonemealSource bonemealSource) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState, BonemealSource bonemealSource) {
		BlockPos blockpos = blockPos.above();
		BlockState blockstate = TofuBlocks.LEEK.get().defaultBlockState();
		Optional<Holder.Reference<PlacedFeature>> grassFeature = serverLevel.registryAccess()
				.lookupOrThrow(Registries.PLACED_FEATURE)
				.get(VegetationPlacements.GRASS_BONEMEAL);

		label49:
		for (int i = 0; i < 128; ++i) {
			BlockPos testPos = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				testPos = testPos.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
				if (!serverLevel.getBlockState(testPos.below()).is(this) || serverLevel.getBlockState(testPos).isCollisionShapeFullBlock(serverLevel, testPos)) {
					continue label49;
				}
			}

			BlockState testState = serverLevel.getBlockState(testPos);
			if (testState.is(blockstate.getBlock()) && randomSource.nextInt(10) == 0) {
				BonemealableBlock bonemealableblock = (BonemealableBlock) blockstate.getBlock();
				if (bonemealableblock.isValidBonemealTarget(serverLevel, testPos, testState, bonemealSource)) {
					bonemealableblock.performBonemeal(serverLevel, randomSource, testPos, testState, bonemealSource);
				}
			}

			if (testState.isAir() && !serverLevel.isOutsideBuildHeight(testPos)) {
				if (randomSource.nextInt(8) == 0) {
					List<Feature> features = serverLevel.getBiome(testPos).value().getGenerationSettings().getBoneMealFeatures();
					if (!features.isEmpty()) {
						Feature placementFeature = Util.getRandom(features, randomSource);
						placementFeature.place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, testPos);
					}
				} else if (grassFeature.isPresent()) {
					grassFeature.get().value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, testPos);
				}
			}
		}
	}
}
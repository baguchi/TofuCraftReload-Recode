package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.world.gen.placement.TofuWorldPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
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
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
		return level.getBlockState(pos.above()).isAir() && level.isInsideBuildHeight(pos.above());
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		return true;
	}


	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		BlockPos above = pos.above();

		label24:
		for (int attempt = 0; attempt < 128; attempt++) {
			BlockPos testPos = above;
			int randomizeCount = attempt / 16;

			for (int i = 0; i < randomizeCount; i++) {
				int dx = random.nextIntBetweenInclusive(-1, 1);
				int dy = random.nextIntBetweenInclusive(-1, 1) * random.nextInt(3) / 2;
				int dz = random.nextIntBetweenInclusive(-1, 1);
				testPos = testPos.offset(dx, dy, dz);
				if (this.stopBonemealSpread(level, testPos)) {
					continue label24;
				}
			}

			placeBonemealEffect(level, random, testPos, source);
		}
	}

	private static void placeBonemealEffect(ServerLevel level, RandomSource random, BlockPos testPos, BonemealSource source) {
		BlockState grass = TofuBlocks.LEEK.get().defaultBlockState();
		Optional<Holder.Reference<PlacedFeature>> grassFeature = level.registryAccess()
				.lookupOrThrow(Registries.PLACED_FEATURE)
				.get(TofuWorldPlacements.LEEK_BONEMEAL);
		BlockState testState = level.getBlockState(testPos);
		if (testState.is(grass.getBlock()) && random.nextFloat() < 0.1F) {
			BonemealableBlock bonemealableBlock = (BonemealableBlock) grass.getBlock();
			if (bonemealableBlock.isValidBonemealTarget(level, testPos, testState, source)) {
				bonemealableBlock.performBonemeal(level, random, testPos, testState, source);
			}
		}

		if (testState.isAir() && !level.isOutsideBuildHeight(testPos)) {
			if (random.nextFloat() < 0.125F) {
				List<Feature> features = level.getBiome(testPos).value().getGenerationSettings().getBoneMealFeatures();
				if (features.isEmpty()) {
					return;
				}

				Feature placementFeature = Util.getRandom(features, random);
				placementFeature.place(level, level.getChunkSource().getGenerator(), random, testPos);
			} else if (grassFeature.isPresent()) {
				grassFeature.get().value().place(level, level.getChunkSource().getGenerator(), random, testPos);
			}
		}
	}

	private boolean stopBonemealSpread(ServerLevel level, BlockPos testPos) {
		return !level.getBlockState(testPos.below()).is(this) || level.getBlockState(testPos).isCollisionShapeFullBlock(level, testPos);
	}
}
package baguchi.tofucraft.world.gen.feature;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public record BigLeekFeature() implements Feature {
	public static final MapCodec<BigLeekFeature> CODEC = MapCodec.unit(BigLeekFeature::new);

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(WorldGenLevel worldGenLevel, ChunkGenerator chunkGenerator, RandomSource randomSource, BlockPos blockPos) {
		if (!worldGenLevel.isEmptyBlock(blockPos))
			return false;
		BlockState blockstate = worldGenLevel.getBlockState(blockPos.below());
		if (!blockstate.is(TofuTags.Blocks.SUPPORTS_TOFU_PLANT))
			return false;
		if (randomSource.nextInt(6) == 0) {
			setBigLeekBlock(worldGenLevel, randomSource, blockPos);
		} else {
			setLeekBlock(worldGenLevel, randomSource, blockPos);
		}

		return true;
	}

	private void setBigLeekBlock(WorldGenLevel level, RandomSource rand, BlockPos pos) {
		int height = 10 + rand.nextInt(8);
		for (int i = 0; i < height; i++) {
			for (BlockPos blockpos1 : BlockPos.betweenClosed(pos.offset(-1, -0, -1), pos.offset(1, 0, 1))) {
				if (level.isEmptyBlock(blockpos1.above(i)) || level.getBlockState(blockpos1.above(i)).is(TofuBlocks.LEEK.get()) || level.getBlockState(blockpos1.above(i)).canBeReplaced()) {
					if ((height - i) < height / 2.5D) {
						level.setBlock(blockpos1.above(i), TofuBlocks.LEEK_GREEN_STEM.get().defaultBlockState(), 2);
					} else {
						level.setBlock(blockpos1.above(i), TofuBlocks.LEEK_STEM.get().defaultBlockState(), 2);
					}
				} else {
					break;
				}
			}
		}
	}

	private void setLeekBlock(WorldGenLevel level, RandomSource rand, BlockPos pos) {
		int height = 5 + rand.nextInt(5);
		for (int i = 0; i < height; i++) {
			if (level.isEmptyBlock(pos.above(i))) {
				if ((height - i) < height / 2.5D) {
					level.setBlock(pos.above(i), TofuBlocks.LEEK_GREEN_STEM.get().defaultBlockState(), 2);
				} else {
					level.setBlock(pos.above(i), TofuBlocks.LEEK_STEM.get().defaultBlockState(), 2);
				}
			} else {
				break;
			}
		}
	}
}

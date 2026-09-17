package baguchi.tofucraft.world.gen.feature;

import baguchi.tofucraft.registry.TofuTags;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TofuBlobFeature(Holder<BlockStateProvider> stateProvider) implements Feature {
	public static final MapCodec<TofuBlobFeature> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(TofuBlobFeature::stateProvider)).apply(i, TofuBlobFeature::new));

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
		BlockPos blockpos = origin;

		if (!level.isEmptyBlock(blockpos.below())) {
			BlockState blockstate = level.getBlockState(blockpos.below());
			if (!isTofu(blockstate)) {
				return false;
			}
		}

		if (blockpos.getY() <= level.getMinY() + 3) {
			return false;
		} else {
			for (int l = 0; l < 3; ++l) {
				int i = random.nextInt(2);
				int j = random.nextInt(2);
				int k = random.nextInt(2);
				float f = (float) (i + j + k) * 0.333F + 0.5F;

				for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i, -j, -k), blockpos.offset(i, j, k))) {
					if (blockpos1.distSqr(blockpos) <= (double) (f * f)) {
						level.setBlock(blockpos1, stateProvider.value().getState(level, random, blockpos1), 4);
					}
				}

				blockpos = blockpos.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
			}

			return true;
		}
	}

	public static boolean isTofu(BlockState p_159760_) {
		return p_159760_.is(TofuTags.Blocks.SUPPORTS_TOFU_PLANT);
	}
}

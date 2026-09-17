package baguchi.tofucraft.world.gen.feature;

import baguchi.tofucraft.registry.TofuBlocks;
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

public record TofuBuildingFeature(Holder<BlockStateProvider> stateProvider) implements Feature {
	public static final MapCodec<TofuBuildingFeature> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(TofuBuildingFeature::stateProvider)).apply(i, TofuBuildingFeature::new));


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
			int i = random.nextInt(3) + 2;
			int i2 = random.nextInt(2) + 1;
			float f = (float) (i2 + i + i2) * 0.35F + 0.5F;
			for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i2, -i, -i2), blockpos.offset(i2, i, i2))) {
				if (blockpos1.distSqr(blockpos) <= (double) (f * f)) {
					level.setBlock(blockpos1.above(i / 2), stateProvider.value().getState(level, random, blockpos1), 4);
				}

			}

			return true;
		}
	}

	public static boolean isTofu(BlockState p_159760_) {
		return p_159760_.is(TofuTags.Blocks.SUPPORTS_TOFU_PLANT) || p_159760_.is(TofuBlocks.OKARA_BLOCK);
	}

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}
}

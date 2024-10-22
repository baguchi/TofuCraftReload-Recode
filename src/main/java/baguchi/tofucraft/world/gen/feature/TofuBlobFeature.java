package baguchi.tofucraft.world.gen.feature;

import baguchi.tofucraft.registry.TofuTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class TofuBlobFeature extends Feature<BlockStateConfiguration> {
	public TofuBlobFeature(Codec<BlockStateConfiguration> codec) {
		super(codec);
	}


	public boolean place(FeaturePlaceContext<BlockStateConfiguration> p_159471_) {
		BlockPos blockpos = p_159471_.origin();
		WorldGenLevel worldgenlevel = p_159471_.level();
		RandomSource random = p_159471_.random();

		BlockStateConfiguration blockstateconfiguration;
		for (blockstateconfiguration = p_159471_.config(); blockpos.getY() > worldgenlevel.getMinY() + 3; blockpos = blockpos.below()) {
			if (!worldgenlevel.isEmptyBlock(blockpos.below())) {
				BlockState blockstate = worldgenlevel.getBlockState(blockpos.below());
				if (isTofu(blockstate)) {
					break;
				}
			}
		}

		if (blockpos.getY() <= worldgenlevel.getMinY() + 3) {
			return false;
		} else {
			for (int l = 0; l < 3; ++l) {
				int i = random.nextInt(2);
				int j = random.nextInt(2);
				int k = random.nextInt(2);
				float f = (float) (i + j + k) * 0.333F + 0.5F;

				for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i, -j, -k), blockpos.offset(i, j, k))) {
					if (blockpos1.distSqr(blockpos) <= (double) (f * f)) {
						worldgenlevel.setBlock(blockpos1, blockstateconfiguration.state, 4);
					}
				}

				blockpos = blockpos.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
			}

			return true;
		}
	}

	public static boolean isTofu(BlockState p_159760_) {
		return p_159760_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}
}

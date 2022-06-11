package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.registry.TofuTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class TofuBuildingFeature extends Feature<BlockStateConfiguration> {
	public TofuBuildingFeature(Codec<BlockStateConfiguration> codec) {
		super(codec);
	}


	public boolean place(FeaturePlaceContext<BlockStateConfiguration> p_159471_) {
		BlockPos blockpos = p_159471_.origin();
		WorldGenLevel worldgenlevel = p_159471_.level();
		RandomSource random = p_159471_.random();

		BlockStateConfiguration blockstateconfiguration;
		for (blockstateconfiguration = p_159471_.config(); blockpos.getY() > worldgenlevel.getMinBuildHeight() + 3; blockpos = blockpos.below()) {
			if (!worldgenlevel.isEmptyBlock(blockpos.below())) {
				BlockState blockstate = worldgenlevel.getBlockState(blockpos.below());
				if (isTofu(blockstate)) {
					break;
				}
			}
		}

		if (blockpos.getY() <= worldgenlevel.getMinBuildHeight() + 3) {
			return false;
		} else {
			int i = random.nextInt(3) + 2;
			int i2 = random.nextInt(2) + 1;
			float f = (float) (i2 + i + i2) * 0.35F + 0.5F;
			for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i2, -i, -i2), blockpos.offset(i2, i, i2))) {
				if (blockpos1.distSqr(blockpos) <= (double) (f * f)) {
					worldgenlevel.setBlock(blockpos1.above(i / 2), blockstateconfiguration.state, 4);
				}

			}

			return true;
		}
	}

	public static boolean isTofu(BlockState p_159760_) {
		return p_159760_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}
}

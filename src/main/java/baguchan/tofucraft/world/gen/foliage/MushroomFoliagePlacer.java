package baguchan.tofucraft.world.gen.foliage;

import baguchan.tofucraft.registry.TofuFoliagePlacerType;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class MushroomFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<MushroomFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((p_68427_) -> {
		return blobParts(p_68427_).apply(p_68427_, MushroomFoliagePlacer::new);
	});
	protected final int height;

	protected static <P extends MushroomFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> p_68414_) {
		return foliagePlacerParts(p_68414_).and(Codec.intRange(0, 16).fieldOf("height").forGetter((p_68412_) -> {
			return p_68412_.height;
		}));
	}

	public MushroomFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
		super(p_161356_, p_161357_);
		this.height = p_161358_;
	}

	protected FoliagePlacerType<?> type() {
		return TofuFoliagePlacerType.MUSHROOM_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int height, int radius, int offset) {
		BlockPos blockpos = attachment.pos();
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		this.makeCap(level, radius, random, blockpos, height, offset, blockpos$mutableblockpos, config, attachment, blockSetter);

	}

	private void makeCap(LevelSimulatedReader level, int radius, RandomSource random, BlockPos blockpos, int height, int offset, BlockPos.MutableBlockPos blockpos$mutableblockpos, TreeConfiguration config, FoliageAttachment attachment, FoliageSetter blockSetter) {
		for (int i = offset; i > offset - height; --i) {
			int j = i < offset ? radius : radius - 1;
			int k = radius - 2;

			for (int l = -j; l <= j; ++l) {
				for (int i1 = -j; i1 <= j; ++i1) {
					boolean flag = l == -j;
					boolean flag1 = l == j;
					boolean flag2 = i1 == -j;
					boolean flag3 = i1 == j;
					boolean flag4 = flag || flag1;
					boolean flag5 = flag2 || flag3;
					if (i >= offset || flag4 != flag5) {
						blockpos$mutableblockpos.setWithOffset(blockpos, l, i, i1);
						tryPlaceLeaf(level, blockSetter, random, config, blockpos$mutableblockpos);
					}
				}
			}
		}
	}

	public int foliageHeight(RandomSource p_68423_, int p_68424_, TreeConfiguration p_68425_) {
		return this.height;
	}

	protected boolean shouldSkipLocation(RandomSource p_68416_, int p_68417_, int p_68418_, int p_68419_, int p_68420_, boolean p_68421_) {
		return false;
	}
}
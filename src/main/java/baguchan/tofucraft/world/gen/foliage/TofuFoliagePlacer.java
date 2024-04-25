package baguchan.tofucraft.world.gen.foliage;

import baguchan.tofucraft.registry.TofuFoliagePlacerType;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class TofuFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<TofuFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((p_68427_) -> {
		return blobParts(p_68427_).apply(p_68427_, TofuFoliagePlacer::new);
	});
	protected final int height;

	protected static <P extends TofuFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> p_68414_) {
		return foliagePlacerParts(p_68414_).and(Codec.intRange(0, 16).fieldOf("height").forGetter((p_68412_) -> {
			return p_68412_.height;
		}));
	}

	public TofuFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
		super(p_161356_, p_161357_);
		this.height = p_161358_;
	}

	protected FoliagePlacerType<?> type() {
		return TofuFoliagePlacerType.TOFU_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int height, int radius, int offset) {
		for (int i = offset; i >= offset - height; i--) {
			int j = Math.max(radius + attachment.radiusOffset(), 0);
			this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), j, i, attachment.doubleTrunk());
		}

	}

	public int foliageHeight(RandomSource p_68423_, int p_68424_, TreeConfiguration p_68425_) {
		return this.height;
	}

	protected boolean shouldSkipLocation(RandomSource p_68416_, int p_68417_, int p_68418_, int p_68419_, int p_68420_, boolean p_68421_) {
		return false;
	}
}
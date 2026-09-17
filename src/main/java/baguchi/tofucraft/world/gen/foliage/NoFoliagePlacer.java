package baguchi.tofucraft.world.gen.foliage;

import baguchi.tofucraft.registry.TofuFoliagePlacerType;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class NoFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<NoFoliagePlacer> CODEC = MapCodec.unit(NoFoliagePlacer::new);

	public NoFoliagePlacer() {
		super(ConstantInt.of(0), ConstantInt.of(0));
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TofuFoliagePlacerType.NO_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeFeature tree, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {

	}

	@Override
	public int foliageHeight(RandomSource random, int treeHeight, TreeFeature tree) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource p_68416_, int p_68417_, int p_68418_, int p_68419_, int p_68420_, boolean p_68421_) {
		return true;
	}
}
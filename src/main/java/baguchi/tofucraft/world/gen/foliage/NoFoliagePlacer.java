package baguchi.tofucraft.world.gen.foliage;

import baguchi.tofucraft.registry.TofuFoliagePlacerType;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
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
	protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int height, int radius, int offset) {
	}

	@Override
	public int foliageHeight(RandomSource p_68423_, int p_68424_, TreeConfiguration p_68425_) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource p_68416_, int p_68417_, int p_68418_, int p_68419_, int p_68420_, boolean p_68421_) {
		return true;
	}
}
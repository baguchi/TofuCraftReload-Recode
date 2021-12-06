package baguchan.tofucraft.registry;

import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class TofuFoliagePlacerType {

	public static final FoliagePlacerType<TofuFoliagePlacer> TOFU_FOLIAGE_PLACER = register("tofucraft:tofu_foliage_placer", TofuFoliagePlacer.CODEC);

	public static void init() {

	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String p_68606_, Codec<P> p_68607_) {
		return Registry.register(Registry.FOLIAGE_PLACER_TYPES, p_68606_, new FoliagePlacerType<>(p_68607_));
	}
}

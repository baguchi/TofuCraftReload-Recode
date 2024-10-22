package baguchi.tofucraft.world.carver;

import baguchi.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CanyonWorldCarver;
import net.minecraft.world.level.material.Fluids;

public class TofuCanyonCarver extends CanyonWorldCarver {
	public TofuCanyonCarver(Codec<CanyonCarverConfiguration> codec) {
		super(codec);
		this.liquids = ImmutableSet.of(Fluids.WATER, TofuFluids.SOYMILK.get());
	}
}

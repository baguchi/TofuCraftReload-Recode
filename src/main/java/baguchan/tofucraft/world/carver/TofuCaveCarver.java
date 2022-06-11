package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.material.Fluids;

public class TofuCaveCarver extends CaveWorldCarver {
	public TofuCaveCarver(Codec<CaveCarverConfiguration> p_159194_) {
		super(p_159194_);
		this.liquids = ImmutableSet.of(Fluids.WATER, TofuFluids.SOYMILK.get());
	}
}
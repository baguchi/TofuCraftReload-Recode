package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.material.Fluids;

public class TofuCaveCarver extends CaveWorldCarver {
	public TofuCaveCarver(Codec<CaveCarverConfiguration> p_159194_) {
		super(p_159194_);
		this.replaceableBlocks = ImmutableSet.of(TofuBlocks.TOFU_TERRAIN, TofuBlocks.TOFUSLATE, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT);
		this.liquids = ImmutableSet.of(Fluids.WATER, TofuFluids.SOYMILK);
	}
}
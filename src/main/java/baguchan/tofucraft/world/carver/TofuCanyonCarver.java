package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CanyonWorldCarver;
import net.minecraft.world.level.material.Fluids;

public class TofuCanyonCarver extends CanyonWorldCarver {
	public TofuCanyonCarver(Codec<CanyonCarverConfiguration> codec) {
		super(codec);
		this.replaceableBlocks = ImmutableSet.of(TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.TOFUSLATE.get(), Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT);
		this.liquids = ImmutableSet.of(Fluids.WATER, TofuFluids.SOYMILK.get());
	}
}

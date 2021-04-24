package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class TofuCaveCarver extends CaveWorldCarver {
	public TofuCaveCarver(Codec<ProbabilityConfig> p_i231917_1_, int p_i231917_2_) {
		super(p_i231917_1_, p_i231917_2_);
		this.replaceableBlocks = ImmutableSet.of(TofuBlocks.TOFU_TERRAIN, TofuBlocks.ZUNDATOFU_TERRAIN);
		this.liquids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER, TofuFluids.SOYMILK);
	}

	protected boolean carveBlock(IChunk p_230358_1_, Function<BlockPos, Biome> p_230358_2_, BitSet p_230358_3_, Random p_230358_4_, BlockPos.Mutable p_230358_5_, BlockPos.Mutable p_230358_6_, BlockPos.Mutable p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
		int i = p_230358_13_ | p_230358_15_ << 4 | p_230358_14_ << 8;
		if (p_230358_3_.get(i)) {
			return false;
		} else {
			p_230358_3_.set(i);
			p_230358_5_.set(p_230358_11_, p_230358_14_, p_230358_12_);
			BlockState blockstate = p_230358_1_.getBlockState(p_230358_5_);
			BlockState blockstate1 = p_230358_1_.getBlockState(p_230358_6_.setWithOffset(p_230358_5_, Direction.UP));
			if (blockstate.is(TofuBlocks.ZUNDATOFU_TERRAIN)) {
				p_230358_16_.setTrue();
			}

			if (!this.canReplaceBlock(blockstate, blockstate1)) {
				return false;
			} else {
				if (p_230358_14_ < 11) {
					p_230358_1_.setBlockState(p_230358_5_, TofuBlocks.SOYMILK.defaultBlockState(), false);
				} else {
					p_230358_1_.setBlockState(p_230358_5_, CAVE_AIR, false);
					if (p_230358_16_.isTrue()) {
						p_230358_7_.setWithOffset(p_230358_5_, Direction.DOWN);
						if (p_230358_1_.getBlockState(p_230358_7_).is(TofuBlocks.TOFU_TERRAIN)) {
							p_230358_1_.setBlockState(p_230358_7_, p_230358_2_.apply(p_230358_5_).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
						}
					}
				}

				return true;
			}
		}
	}
}

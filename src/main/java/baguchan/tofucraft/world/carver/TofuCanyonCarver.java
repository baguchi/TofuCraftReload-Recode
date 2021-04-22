package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.BitSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class TofuCanyonCarver extends CanyonWorldCarver {
	public TofuCanyonCarver(Codec<ProbabilityConfig> codec) {
		super(codec);
		this.field_222718_j = (Set) ImmutableSet.of(Blocks.field_150348_b, Blocks.field_196650_c, Blocks.field_196654_e, Blocks.field_196656_g, Blocks.field_150346_d, Blocks.field_196660_k, (Object[]) new Block[]{Blocks.field_196661_l, Blocks.field_196658_i, TofuBlocks.TOFU_TERRAIN, TofuBlocks.ZUNDATOFU_TERRAIN});
		this.field_222719_k = (Set) ImmutableSet.of(Fluids.field_204547_b, Fluids.WATER, TofuFluids.SOYMILK);
	}

	protected boolean func_230358_a_(IChunk p_230358_1_, Function<BlockPos, Biome> p_230358_2_, BitSet p_230358_3_, Random p_230358_4_, BlockPos.Mutable p_230358_5_, BlockPos.Mutable p_230358_6_, BlockPos.Mutable p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
		int i = p_230358_13_ | p_230358_15_ << 4 | p_230358_14_ << 8;
		if (p_230358_3_.get(i))
			return false;
		p_230358_3_.set(i);
		p_230358_5_.func_181079_c(p_230358_11_, p_230358_14_, p_230358_12_);
		BlockState blockstate = p_230358_1_.getBlockState(p_230358_5_);
		BlockState blockstate1 = p_230358_1_.getBlockState((BlockPos) p_230358_6_.func_239622_a_((Vector3i) p_230358_5_, Direction.UP));
		if (blockstate.is(TofuBlocks.ZUNDATOFU_TERRAIN))
			p_230358_16_.setTrue();
		if (!func_222707_a(blockstate, blockstate1))
			return false;
		if (p_230358_14_ < 11) {
			p_230358_1_.func_177436_a((BlockPos) p_230358_5_, TofuBlocks.SOYMILK.defaultBlockState(), false);
		} else {
			p_230358_1_.func_177436_a((BlockPos) p_230358_5_, field_222715_g, false);
			if (p_230358_16_.isTrue()) {
				p_230358_7_.func_239622_a_((Vector3i) p_230358_5_, Direction.DOWN);
				if (p_230358_1_.getBlockState(p_230358_7_).is(TofuBlocks.TOFU_TERRAIN))
					p_230358_1_.func_177436_a((BlockPos) p_230358_7_, ((Biome) p_230358_2_.apply(p_230358_5_)).func_242440_e().func_242502_e().func_204108_a(), false);
			}
		}
		return true;
	}
}

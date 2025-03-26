package baguchi.tofucraft.block.tree;

import baguchi.tofucraft.block.TofuLeavesBlock;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;

public class ApricotLeavesBlock extends LeavesBlock {
	public static final MapCodec<TofuLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(
			p_399854_ -> p_399854_.group(
							propertiesCodec()
					)
					.apply(p_399854_, TofuLeavesBlock::new)
	);
	public ApricotLeavesBlock(Properties p_54422_) {
		super(0.01F, p_54422_);
	}

	@Override
	public MapCodec<? extends LeavesBlock> codec() {
		return CODEC;
	}

	@Override
	protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {

	}
}

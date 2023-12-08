package baguchan.tofucraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;

public class MincedTofuBlock extends FallingBlock {

	public static final MapCodec<MincedTofuBlock> CODEC = simpleCodec(MincedTofuBlock::new);
	public MincedTofuBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return CODEC;
	}
}
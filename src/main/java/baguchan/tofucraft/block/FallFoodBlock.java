package baguchan.tofucraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;

public class FallFoodBlock extends FallingBlock {

	public static final MapCodec<FallFoodBlock> CODEC = simpleCodec(FallFoodBlock::new);

	public FallFoodBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return CODEC;
	}
}
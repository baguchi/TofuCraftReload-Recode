package baguchan.tofucraft.block;

import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.enums.BubbleColumnDirection;

public class TofuMagmaBlock extends MagmaBlock {
	public TofuMagmaBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BubbleColumnDirection getBubbleColumnDirection(BlockState state) {
		return BubbleColumnDirection.DOWNWARD;
	}
}

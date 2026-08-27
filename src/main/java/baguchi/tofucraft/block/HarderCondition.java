package baguchi.tofucraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface HarderCondition {
	void harder(Level level, BlockState state, BlockPos pos);

	default void tryHarder(Level level, BlockState state, BlockPos pos) {
		if (canHarder(level, state, pos)) {
			harder(level, state, pos);
		}
	}

	boolean canHarder(Level level, BlockState state, BlockPos pos);
}

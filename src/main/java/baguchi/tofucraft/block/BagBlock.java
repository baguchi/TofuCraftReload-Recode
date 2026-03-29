package baguchi.tofucraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BagBlock extends Block {

	public BagBlock(Properties p_53976_) {
		super(p_53976_);
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
		super.fallOn(level, state, pos, entity, fallDistance * 0.5F);
	}
}

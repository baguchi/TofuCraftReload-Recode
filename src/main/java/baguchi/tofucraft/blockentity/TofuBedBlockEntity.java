package baguchi.tofucraft.blockentity;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TofuBedBlockEntity extends BlockEntity {

	public TofuBedBlockEntity(BlockPos p_155115_, BlockState p_155116_) {
		super(TofuBlockEntitys.TOFUBED.get(), p_155115_, p_155116_);
	}
}

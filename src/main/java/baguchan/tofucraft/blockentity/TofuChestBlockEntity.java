package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TofuChestBlockEntity extends ChestBlockEntity {
	protected TofuChestBlockEntity(BlockEntityType<?> p_155327_, BlockPos p_155328_, BlockState p_155329_) {
		super(p_155327_, p_155328_, p_155329_);
	}

	public TofuChestBlockEntity(BlockPos p_155331_, BlockState p_155332_) {
		super(TofuBlockEntitys.TOFUCHEST, p_155331_, p_155332_);
	}
}

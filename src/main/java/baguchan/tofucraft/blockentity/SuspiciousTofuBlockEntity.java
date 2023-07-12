package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuspiciousTofuBlockEntity extends BrushableBlockEntity {
	public SuspiciousTofuBlockEntity(BlockPos p_272892_, BlockState p_273759_) {
		super(p_272892_, p_273759_);
	}

	@Override
	public BlockEntityType<?> getType() {
		return TofuBlockEntitys.SUSPICIOUS_TOFU.get();
	}
}
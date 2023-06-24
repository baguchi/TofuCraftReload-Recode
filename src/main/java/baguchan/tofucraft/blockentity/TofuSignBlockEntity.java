package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TofuSignBlockEntity extends SignBlockEntity {
	public TofuSignBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(pWorldPosition, pBlockState);
	}

	@Override
	public BlockEntityType<?> getType() {
		return TofuBlockEntitys.TOFU_SIGN.get();
	}
}
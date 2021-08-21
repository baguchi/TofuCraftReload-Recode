package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TofuBedBlockEntity extends BlockEntity {

	public TofuBedBlockEntity(BlockPos p_155115_, BlockState p_155116_) {
		super(TofuBlockEntitys.TOFUBED, p_155115_, p_155116_);
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return new ClientboundBlockEntityDataPacket(this.worldPosition, 11, this.getUpdateTag());
	}
}

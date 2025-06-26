package baguchi.tofucraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;

import javax.annotation.Nullable;

public class SyncedBlockEntity extends BlockEntity {
	public SyncedBlockEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
		super(tileEntityTypeIn, pos, state);
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider p_323910_) {
		return saveCustomOnly(p_323910_);
	}

	@Override
	public void onDataPacket(Connection net, ValueInput valueInput) {
		super.onDataPacket(net, valueInput);
		loadAdditional(valueInput);
	}

	protected void inventoryChanged() {
		super.setChanged();
		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
	}
}

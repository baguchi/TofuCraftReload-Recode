package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class TFCraftingTableResetFakeSlotPacket implements CustomPacketPayload, IPayloadHandler<TFCraftingTableResetFakeSlotPacket> {

	public static final StreamCodec<FriendlyByteBuf, TFCraftingTableResetFakeSlotPacket> STREAM_CODEC = CustomPacketPayload.codec(
			TFCraftingTableResetFakeSlotPacket::write, TFCraftingTableResetFakeSlotPacket::new
	);
	public static final Type<TFCraftingTableResetFakeSlotPacket> TYPE = new Type<>(TofuCraftReload.prefix("reset_fake_slot"));

	public BlockPos blockPos;

	public TFCraftingTableResetFakeSlotPacket(BlockPos blockPos) {
		this.blockPos = blockPos;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
	}

	public TFCraftingTableResetFakeSlotPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos());
	}

	public void handle(TFCraftingTableResetFakeSlotPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = context.player().level().getBlockEntity(message.blockPos);
			if (tileentity instanceof TFCraftingTableBlockEntity blockEntity) {
				blockEntity.getFakeInventory().clear();
			}
		});
	}
}

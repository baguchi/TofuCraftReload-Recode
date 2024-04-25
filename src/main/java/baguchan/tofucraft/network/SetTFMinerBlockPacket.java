package baguchan.tofucraft.network;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.tfenergy.TFMinerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class SetTFMinerBlockPacket implements CustomPacketPayload, IPayloadHandler<SetTFMinerBlockPacket> {

	public static final StreamCodec<FriendlyByteBuf, SetTFMinerBlockPacket> STREAM_CODEC = CustomPacketPayload.codec(
			SetTFMinerBlockPacket::write, SetTFMinerBlockPacket::new
	);
	public static final CustomPacketPayload.Type<SetTFMinerBlockPacket> TYPE = CustomPacketPayload.createType(TofuCraftReload.prefix("set_miner_block").toString());

	public BlockPos blockPos;
	private BlockPos offset;
	private Vec3i size;
	private boolean working;

	public SetTFMinerBlockPacket(BlockPos blockPos, BlockPos offset, Vec3i size, boolean working) {
		this.blockPos = blockPos;
		this.offset = offset;
		this.size = size;
		this.working = working;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		buffer.writeBlockPos(this.offset);
		buffer.writeJsonWithCodec(Vec3i.CODEC, this.size);
		buffer.writeBoolean(this.working);
	}

	public SetTFMinerBlockPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), buffer.readBlockPos(), buffer.readJsonWithCodec(Vec3i.CODEC), buffer.readBoolean());
	}

	@Override
	public void handle(SetTFMinerBlockPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.player().level() != null) {
				BlockEntity tileentity = context.player().level().getBlockEntity(message.blockPos);
				if (tileentity instanceof TFMinerBlockEntity minerBlockEntity) {
					minerBlockEntity.setOffset(message.offset);
					minerBlockEntity.setSize(message.size);
					if (message.working) {
						minerBlockEntity.setWorking(!minerBlockEntity.isWorking());
						minerBlockEntity.setWorkingBlockPos(message.blockPos.offset(message.offset.getX(), message.offset.getY(), message.offset.getZ()));
					} else {
						minerBlockEntity.setWorking(false);
					}
					minerBlockEntity.setChanged();
					BlockState blockstate = context.player().level().getBlockState(message.blockPos);
					context.player().level().sendBlockUpdated(message.blockPos, blockstate, blockstate, 3);
				}
			}
		});
	}
}

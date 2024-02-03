package baguchan.tofucraft.message;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.tfenergy.TFMinerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class SetTFMinerBlockPacket implements CustomPacketPayload {
	public static final ResourceLocation ID = TofuCraftReload.prefix("set_miner_block");

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
	public ResourceLocation id() {
		return ID;
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

	public static void handle(SetTFMinerBlockPacket message, PlayPayloadContext context) {
		context.workHandler().execute(() -> {
			if (context.level().isPresent()) {
				BlockEntity tileentity = context.level().get().getBlockEntity(message.blockPos);
				if (tileentity instanceof TFMinerBlockEntity minerBlockEntity) {
					minerBlockEntity.setOffset(message.offset);
					minerBlockEntity.setSize(message.size);
					if (message.working) {
						minerBlockEntity.setWorking(!minerBlockEntity.isWorking());
					}
					minerBlockEntity.setChanged();
					BlockState blockstate = context.level().get().getBlockState(message.blockPos);
					context.level().get().sendBlockUpdated(message.blockPos, blockstate, blockstate, 3);
				}
			}
		});
	}
}

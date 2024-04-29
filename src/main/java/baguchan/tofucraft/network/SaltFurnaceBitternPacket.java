package baguchan.tofucraft.network;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class SaltFurnaceBitternPacket implements CustomPacketPayload, IPayloadHandler<SaltFurnaceBitternPacket> {

	public static final StreamCodec<FriendlyByteBuf, SaltFurnaceBitternPacket> STREAM_CODEC = CustomPacketPayload.codec(
			SaltFurnaceBitternPacket::write, SaltFurnaceBitternPacket::new
	);
	public static final Type<SaltFurnaceBitternPacket> TYPE = CustomPacketPayload.createType(TofuCraftReload.prefix("salt_furnace_bittern").toString());

	public BlockPos blockPos;

	public FluidStack fluid;

	public SaltFurnaceBitternPacket(BlockPos blockPos, FluidStack fluid) {
		this.blockPos = blockPos;
		this.fluid = fluid;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		buffer.writeJsonWithCodec(FluidStack.CODEC, fluid);
	}

	public SaltFurnaceBitternPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), buffer.readJsonWithCodec(FluidStack.CODEC));
	}

	public void handle(SaltFurnaceBitternPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
			if (tileentity instanceof SaltFurnaceBlockEntity) {
				SaltFurnaceBlockEntity tileentity1 = (SaltFurnaceBlockEntity) tileentity;
				tileentity1.bitternTank.setFluid(message.fluid);
			}
		});
	}
}

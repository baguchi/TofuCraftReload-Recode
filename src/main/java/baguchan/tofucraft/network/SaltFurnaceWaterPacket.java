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

public class SaltFurnaceWaterPacket implements CustomPacketPayload, IPayloadHandler<SaltFurnaceWaterPacket> {

	public static final StreamCodec<FriendlyByteBuf, SaltFurnaceWaterPacket> STREAM_CODEC = CustomPacketPayload.codec(
			SaltFurnaceWaterPacket::write, SaltFurnaceWaterPacket::new
	);
	public static final CustomPacketPayload.Type<SaltFurnaceWaterPacket> TYPE = CustomPacketPayload.createType(TofuCraftReload.prefix("salt_furnace_water").toString());

	public BlockPos blockPos;

	public FluidStack fluid;

	public SaltFurnaceWaterPacket(BlockPos blockPos, FluidStack fluid) {
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

	public SaltFurnaceWaterPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), buffer.readJsonWithCodec(FluidStack.CODEC));
	}

	public void handle(SaltFurnaceWaterPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
			if (tileentity instanceof SaltFurnaceBlockEntity) {
				SaltFurnaceBlockEntity tileentity1 = (SaltFurnaceBlockEntity) tileentity;
				tileentity1.waterTank.setFluid(message.fluid);
			}
		});
	}
}

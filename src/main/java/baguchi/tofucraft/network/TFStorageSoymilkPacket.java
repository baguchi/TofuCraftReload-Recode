package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class TFStorageSoymilkPacket implements CustomPacketPayload, IPayloadHandler<TFStorageSoymilkPacket> {

	public static final StreamCodec<FriendlyByteBuf, TFStorageSoymilkPacket> STREAM_CODEC = CustomPacketPayload.codec(
			TFStorageSoymilkPacket::write, TFStorageSoymilkPacket::new
	);
	public static final Type<TFStorageSoymilkPacket> TYPE = new Type<>(TofuCraftReload.prefix("storage_soymilk"));

	public BlockPos blockPos;

	public FluidStack fluid;

	public TFStorageSoymilkPacket(BlockPos blockPos, FluidStack fluid) {
		this.blockPos = blockPos;
		this.fluid = fluid;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		buffer.writeJsonWithCodec(FluidStack.OPTIONAL_CODEC, fluid);
	}

	public TFStorageSoymilkPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), buffer.readJsonWithCodec(FluidStack.OPTIONAL_CODEC));
	}

	public void handle(TFStorageSoymilkPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
			if (tileentity instanceof TFStorageBlockEntity) {
				TFStorageBlockEntity tileentity1 = (TFStorageBlockEntity) tileentity;
				tileentity1.getTank().setFluid(message.fluid);
			}
		});
	}
}

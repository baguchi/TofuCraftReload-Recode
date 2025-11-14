package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

public class TFStorageSoymilkPacket implements CustomPacketPayload, IPayloadHandler<TFStorageSoymilkPacket> {

	public static final StreamCodec<FriendlyByteBuf, TFStorageSoymilkPacket> STREAM_CODEC = CustomPacketPayload.codec(
			TFStorageSoymilkPacket::write, TFStorageSoymilkPacket::new
	);
	public static final CustomPacketPayload.Type<TFStorageSoymilkPacket> TYPE = new Type<>(TofuCraftReload.prefix("storage_soymilk"));

	public BlockPos blockPos;

	public Fluid fluid;
	public int amount;

	public TFStorageSoymilkPacket(BlockPos blockPos, Fluid fluid, int amount) {
		this.blockPos = blockPos;
		this.fluid = fluid;
		this.amount = amount;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		buffer.writeResourceLocation(BuiltInRegistries.FLUID.getKey(fluid));
		buffer.writeInt(this.amount);
	}

	public TFStorageSoymilkPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), BuiltInRegistries.FLUID.getValue(buffer.readResourceLocation()), buffer.readInt());
	}

	public void handle(TFStorageSoymilkPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
			if (tileentity instanceof TFStorageBlockEntity) {
				TFStorageBlockEntity tileentity1 = (TFStorageBlockEntity) tileentity;
				tileentity1.getTank().set(0, FluidResource.of(message.fluid), amount);
			}
		});
	}
}

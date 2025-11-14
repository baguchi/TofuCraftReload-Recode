package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.SaltFurnaceBlockEntity;
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

public class SaltFurnaceBitternPacket implements CustomPacketPayload, IPayloadHandler<SaltFurnaceBitternPacket> {

	public static final StreamCodec<FriendlyByteBuf, SaltFurnaceBitternPacket> STREAM_CODEC = CustomPacketPayload.codec(
			SaltFurnaceBitternPacket::write, SaltFurnaceBitternPacket::new
	);
	public static final CustomPacketPayload.Type<SaltFurnaceBitternPacket> TYPE = new Type<>(TofuCraftReload.prefix("salt_furnace_bittern"));

	public BlockPos blockPos;

	public Fluid fluid;
	public int amount;

	public SaltFurnaceBitternPacket(BlockPos blockPos, Fluid fluid, int amount) {
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

	public SaltFurnaceBitternPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), BuiltInRegistries.FLUID.getValue(buffer.readResourceLocation()), buffer.readInt());
	}

	public void handle(SaltFurnaceBitternPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
			if (tileentity instanceof SaltFurnaceBlockEntity) {
				SaltFurnaceBlockEntity tileentity1 = (SaltFurnaceBlockEntity) tileentity;
				tileentity1.bitternTank.set(0, FluidResource.of(message.fluid), amount);
			}
		});
	}
}

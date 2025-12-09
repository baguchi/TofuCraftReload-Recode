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

public class SaltFurnaceWaterPacket implements CustomPacketPayload, IPayloadHandler<SaltFurnaceWaterPacket> {

	public static final StreamCodec<FriendlyByteBuf, SaltFurnaceWaterPacket> STREAM_CODEC = CustomPacketPayload.codec(
			SaltFurnaceWaterPacket::write, SaltFurnaceWaterPacket::new
	);
	public static final CustomPacketPayload.Type<SaltFurnaceWaterPacket> TYPE = new Type<>(TofuCraftReload.prefix("salt_furnace_water"));

	public BlockPos blockPos;

	public Fluid fluid;
	public int amount;

	public SaltFurnaceWaterPacket(BlockPos blockPos, Fluid fluid, int amount) {
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
		buffer.writeIdentifier(BuiltInRegistries.FLUID.getKey(fluid));
		buffer.writeInt(this.amount);
	}

	public SaltFurnaceWaterPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), BuiltInRegistries.FLUID.getValue(buffer.readIdentifier()), buffer.readInt());
	}

	public void handle(SaltFurnaceWaterPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
			if (tileentity instanceof SaltFurnaceBlockEntity) {
				SaltFurnaceBlockEntity tileentity1 = (SaltFurnaceBlockEntity) tileentity;
				tileentity1.waterTank.set(0, FluidResource.of(message.fluid), amount);
			}
		});
	}
}

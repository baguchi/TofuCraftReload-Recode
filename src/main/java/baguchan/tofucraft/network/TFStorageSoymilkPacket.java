package baguchan.tofucraft.network;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class TFStorageSoymilkPacket implements CustomPacketPayload {
	public static final ResourceLocation ID = TofuCraftReload.prefix("tf_storage");

	public BlockPos blockPos;

	public FluidStack fluid;

	public TFStorageSoymilkPacket(BlockPos blockPos, FluidStack fluid) {
		this.blockPos = blockPos;
		this.fluid = fluid;
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		CompoundTag fluidTag = new CompoundTag();
		if (this.fluid != null)
			this.fluid.writeToNBT(fluidTag);
		buffer.writeNbt(fluidTag);
	}

	public TFStorageSoymilkPacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), FluidStack.loadFluidStackFromNBT(buffer.readNbt()));
	}

	public static void handle(TFStorageSoymilkPacket message, PlayPayloadContext context) {
		context.workHandler().execute(() -> {
			BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
				if (tileentity instanceof TFStorageBlockEntity) {
					TFStorageBlockEntity tileentity1 = (TFStorageBlockEntity) tileentity;
					tileentity1.getTank().setFluid(message.fluid);
				}
			});
	}
}

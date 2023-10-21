package baguchan.tofucraft.message;

import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TFStorageSoymilkMessage {
	public BlockPos blockPos;

	public FluidStack fluid;

	public TFStorageSoymilkMessage(BlockPos blockPos, FluidStack fluid) {
		this.blockPos = blockPos;
		this.fluid = fluid;
	}

	public void writePacketData(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		CompoundTag fluidTag = new CompoundTag();
		if (this.fluid != null)
			this.fluid.writeToNBT(fluidTag);
		buffer.writeNbt(fluidTag);
	}

	public static TFStorageSoymilkMessage readPacketData(FriendlyByteBuf buffer) {
		BlockPos blockPos = buffer.readBlockPos();
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(buffer.readNbt());
		return new TFStorageSoymilkMessage(blockPos, fluid);
	}

	public static boolean handle(TFStorageSoymilkMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(message.blockPos);
				if (tileentity instanceof TFStorageBlockEntity) {
					TFStorageBlockEntity tileentity1 = (TFStorageBlockEntity) tileentity;
					tileentity1.getTank().setFluid(message.fluid);
				}
			});
		}
		context.setPacketHandled(true);
		return true;
	}
}

package baguchan.tofucraft.message;

import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.NetworkEvent;

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

	public boolean handle(NetworkEvent.Context context) {
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(blockPos);
				if (tileentity instanceof TFStorageBlockEntity) {
					TFStorageBlockEntity tileentity1 = (TFStorageBlockEntity) tileentity;
					tileentity1.getTank().setFluid(fluid);
				}
			});
		}
		context.setPacketHandled(true);
		return true;
	}
}

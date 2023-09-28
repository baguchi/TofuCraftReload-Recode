package baguchan.tofucraft.message;

import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.LogicalSide;

public class SaltFurnaceBitternMessage {
	public BlockPos blockPos;

	public FluidStack fluid;

	public SaltFurnaceBitternMessage(BlockPos blockPos, FluidStack fluid) {
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

	public static SaltFurnaceBitternMessage readPacketData(FriendlyByteBuf buffer) {
		BlockPos blockPos = buffer.readBlockPos();
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(buffer.readNbt());
		return new SaltFurnaceBitternMessage(blockPos, fluid);
	}

	public void handle(CustomPayloadEvent.Context context) {
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(blockPos);
				if (tileentity instanceof SaltFurnaceBlockEntity) {
					SaltFurnaceBlockEntity tileentity1 = (SaltFurnaceBlockEntity) tileentity;
					tileentity1.bitternTank.setFluid(fluid);
				}
			});
		}
		context.setPacketHandled(true);
	}
}

package baguchan.tofucraft.message;

import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SaltFurnaceWaterMessage {
	public BlockPos blockPos;

	public FluidStack fluid;

	public SaltFurnaceWaterMessage(BlockPos blockPos, FluidStack fluid) {
		this.blockPos = blockPos;
		this.fluid = fluid;
	}

	public void writePacketData(PacketBuffer buffer) {
		buffer.writeBlockPos(this.blockPos);
		CompoundNBT fluidTag = new CompoundNBT();
		if (this.fluid != null)
			this.fluid.writeToNBT(fluidTag);
		buffer.writeNbt(fluidTag);
	}

	public static SaltFurnaceWaterMessage readPacketData(PacketBuffer buffer) {
		BlockPos blockPos = buffer.func_179259_c();
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(buffer.func_150793_b());
		return new SaltFurnaceWaterMessage(blockPos, fluid);
	}

	public static boolean handle(SaltFurnaceWaterMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT)
			context.enqueueWork(() -> {
				TileEntity tileentity = (Minecraft.func_71410_x()).field_71439_g.getLevel().func_175625_s(message.blockPos);
				if (tileentity instanceof SaltFurnaceTileEntity) {
					SaltFurnaceTileEntity tileentity1 = (SaltFurnaceTileEntity) tileentity;
					tileentity1.waterTank.setFluid(message.fluid);
				}
			});
		return true;
	}
}

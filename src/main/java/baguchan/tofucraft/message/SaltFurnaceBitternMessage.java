package baguchan.tofucraft.message;

import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class SaltFurnaceBitternMessage {
	public BlockPos blockPos;

	public FluidStack fluid;

	public SaltFurnaceBitternMessage(BlockPos blockPos, FluidStack fluid) {
		this.blockPos = blockPos;
		this.fluid = fluid;
	}

	public void writePacketData(PacketBuffer buffer) {
		buffer.func_179255_a(this.blockPos);
		CompoundNBT fluidTag = new CompoundNBT();
		if (this.fluid != null)
			this.fluid.writeToNBT(fluidTag);
		buffer.func_150786_a(fluidTag);
	}

	public static SaltFurnaceBitternMessage readPacketData(PacketBuffer buffer) {
		BlockPos blockPos = buffer.func_179259_c();
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(buffer.func_150793_b());
		return new SaltFurnaceBitternMessage(blockPos, fluid);
	}

	public static boolean handle(SaltFurnaceBitternMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT)
			context.enqueueWork(() -> {
				TileEntity tileentity = (Minecraft.func_71410_x()).field_71439_g.func_130014_f_().func_175625_s(message.blockPos);
				if (tileentity instanceof SaltFurnaceTileEntity) {
					SaltFurnaceTileEntity tileentity1 = (SaltFurnaceTileEntity) tileentity;
					tileentity1.bitternTank.setFluid(message.fluid);
				}
			});
		return true;
	}
}

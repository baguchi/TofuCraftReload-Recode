package baguchan.tofucraft.message;

import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.NetworkEvent;

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

	public boolean handle(NetworkEvent.Context context) {
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				BlockEntity tileentity = (Minecraft.getInstance()).player.level().getBlockEntity(blockPos);
				if (tileentity instanceof SaltFurnaceBlockEntity) {
					SaltFurnaceBlockEntity tileentity1 = (SaltFurnaceBlockEntity) tileentity;
					tileentity1.bitternTank.setFluid(fluid);
				}
			});
		}
		return true;
	}
}

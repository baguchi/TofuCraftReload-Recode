package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class TFCraftingTableResetSavedRecipePacket implements CustomPacketPayload, IPayloadHandler<TFCraftingTableResetSavedRecipePacket> {

	public static final StreamCodec<FriendlyByteBuf, TFCraftingTableResetSavedRecipePacket> STREAM_CODEC = CustomPacketPayload.codec(
			TFCraftingTableResetSavedRecipePacket::write, TFCraftingTableResetSavedRecipePacket::new
	);
	public static final Type<TFCraftingTableResetSavedRecipePacket> TYPE = new Type<>(TofuCraftReload.prefix("reset_saved_recipe"));

	public BlockPos blockPos;

	public TFCraftingTableResetSavedRecipePacket(BlockPos blockPos) {
		this.blockPos = blockPos;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
	}

	public TFCraftingTableResetSavedRecipePacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos());
	}

	public void handle(TFCraftingTableResetSavedRecipePacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			BlockEntity tileentity = context.player().level().getBlockEntity(message.blockPos);
			if (tileentity instanceof TFCraftingTableBlockEntity blockEntity) {
				blockEntity.setRecipeDisplay(null);
				blockEntity.setRecipe(null);
			}
		});
	}
}

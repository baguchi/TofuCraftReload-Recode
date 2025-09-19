package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class TFCraftingTableSavedRecipePacket implements CustomPacketPayload, IPayloadHandler<TFCraftingTableSavedRecipePacket> {

	public static final StreamCodec<FriendlyByteBuf, TFCraftingTableSavedRecipePacket> STREAM_CODEC = CustomPacketPayload.codec(
			TFCraftingTableSavedRecipePacket::write, TFCraftingTableSavedRecipePacket::new
	);
	public static final Type<TFCraftingTableSavedRecipePacket> TYPE = new Type<>(TofuCraftReload.prefix("saved_recipe"));

	public BlockPos blockPos;
	public RecipeDisplayId recipe;

	public TFCraftingTableSavedRecipePacket(BlockPos blockPos, RecipeDisplayId recipe) {
		this.blockPos = blockPos;
		this.recipe = recipe;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.blockPos);
		RecipeDisplayId.STREAM_CODEC.encode(buffer, this.recipe);
	}

	public TFCraftingTableSavedRecipePacket(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), RecipeDisplayId.STREAM_CODEC.decode(buffer));
	}


	public void handle(TFCraftingTableSavedRecipePacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.player().level() instanceof ServerLevel serverLevel) {
				BlockEntity tileentity = serverLevel.getBlockEntity(message.blockPos);

				RecipeManager.ServerDisplayInfo recipemanager$serverdisplayinfo = serverLevel.getServer().getRecipeManager().getRecipeFromDisplay(message.recipe);

				if (tileentity instanceof TFCraftingTableBlockEntity blockEntity) {
					blockEntity.setRecipeDisplay(recipemanager$serverdisplayinfo.display().display());
				}
			}
		});
	}
}

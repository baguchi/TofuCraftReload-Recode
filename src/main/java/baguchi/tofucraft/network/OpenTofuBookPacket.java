package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class OpenTofuBookPacket implements CustomPacketPayload, IPayloadHandler<OpenTofuBookPacket> {

	public static final StreamCodec<FriendlyByteBuf, OpenTofuBookPacket> STREAM_CODEC = CustomPacketPayload.codec(
			OpenTofuBookPacket::write, OpenTofuBookPacket::new
	);
	public static final Type<OpenTofuBookPacket> TYPE = new Type<>(TofuCraftReload.prefix("open_tofu_book"));

	public int entityID;

	public OpenTofuBookPacket(LivingEntity entity) {
		this.entityID = entity.getId();
	}

	public OpenTofuBookPacket(int entityID) {
		this.entityID = entityID;
	}

	public OpenTofuBookPacket(FriendlyByteBuf buffer) {
		this(buffer.readInt());
	}


	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityID);
	}

	public void handle(OpenTofuBookPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Entity tileentity = (Minecraft.getInstance()).player.level().getEntity(message.entityID);
			if (tileentity instanceof Player player) {
				ClientProxy.handleOpenPageTest(player);
			}
		});
	}
}

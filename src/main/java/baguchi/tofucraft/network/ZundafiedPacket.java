package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.attachment.TofuLivingAttachment;
import baguchi.tofucraft.registry.TofuAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class ZundafiedPacket implements CustomPacketPayload, IPayloadHandler<ZundafiedPacket> {

	public static final StreamCodec<FriendlyByteBuf, ZundafiedPacket> STREAM_CODEC = CustomPacketPayload.codec(
			ZundafiedPacket::write, ZundafiedPacket::new
	);
	public static final Type<ZundafiedPacket> TYPE = new Type<>(TofuCraftReload.prefix("zundafied"));

	private final int entityId;

	private final boolean zundafied;

	public ZundafiedPacket(LivingEntity entity, boolean zundafied) {
		this.entityId = entity.getId();
		this.zundafied = zundafied;
	}

	public ZundafiedPacket(int id, boolean zundafied) {
		this.entityId = id;
		this.zundafied = zundafied;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeBoolean(this.zundafied);
	}

	public ZundafiedPacket(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readBoolean());
	}

	public void handle(ZundafiedPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Entity entity = (Minecraft.getInstance()).player.level().getEntity(message.entityId);
			if (entity != null && entity instanceof LivingEntity living) {
				TofuLivingAttachment cap = living.getData(TofuAttachments.TOFU_LIVING);
				cap.setZundafied(entity, message.zundafied);
			}
		});

	}
}

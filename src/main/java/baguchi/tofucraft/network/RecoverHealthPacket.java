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

public class RecoverHealthPacket implements CustomPacketPayload, IPayloadHandler<RecoverHealthPacket> {

	public static final StreamCodec<FriendlyByteBuf, RecoverHealthPacket> STREAM_CODEC = CustomPacketPayload.codec(
			RecoverHealthPacket::write, RecoverHealthPacket::new
	);
	public static final Type<RecoverHealthPacket> TYPE = new Type<>(TofuCraftReload.prefix("recover_health"));

	private final int entityId;

	private final float health;

	public RecoverHealthPacket(LivingEntity entity, float health) {
		this.entityId = entity.getId();
		this.health = health;
	}

	public RecoverHealthPacket(int id, float health) {
		this.entityId = id;
		this.health = health;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeFloat(this.health);
	}

	public RecoverHealthPacket(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readFloat());
	}

	public void handle(RecoverHealthPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Entity entity = (Minecraft.getInstance()).player.level().getEntity(message.entityId);
			if (entity != null && entity instanceof LivingEntity living) {
				TofuLivingAttachment cap = living.getData(TofuAttachments.TOFU_LIVING);
				cap.setRecoverHealth(entity, message.health);
			}
		});

	}
}

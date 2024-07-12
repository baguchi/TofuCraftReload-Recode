package baguchan.tofucraft.network;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.attachment.SoyHealthAttachment;
import baguchan.tofucraft.registry.TofuAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class SoyMilkDrinkedPacket implements CustomPacketPayload, IPayloadHandler<SoyMilkDrinkedPacket> {

	public static final StreamCodec<FriendlyByteBuf, SoyMilkDrinkedPacket> STREAM_CODEC = CustomPacketPayload.codec(
			SoyMilkDrinkedPacket::write, SoyMilkDrinkedPacket::new
	);
	public static final CustomPacketPayload.Type<SoyMilkDrinkedPacket> TYPE = new Type<>(TofuCraftReload.prefix("soy_milk_drinked"));

	private final int entityId;

	private final int level;
	private final boolean canUpdate;

	public SoyMilkDrinkedPacket(LivingEntity entity, int level, boolean canUpdate) {
		this.entityId = entity.getId();
		this.level = level;
		this.canUpdate = canUpdate;
	}

	public SoyMilkDrinkedPacket(int id, int level, boolean canUpdate) {
		this.entityId = id;
		this.level = level;
		this.canUpdate = canUpdate;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeInt(this.level);
		buffer.writeBoolean(this.canUpdate);
	}

	public SoyMilkDrinkedPacket(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readBoolean());
	}

	public void handle(SoyMilkDrinkedPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Entity entity = (Minecraft.getInstance()).player.level().getEntity(message.entityId);
			if (entity != null && entity instanceof LivingEntity living) {
				SoyHealthAttachment cap = living.getData(TofuAttachments.SOY_HEALTH);
				cap.setSoyHealthLevel((LivingEntity) entity, message.level, message.canUpdate);
			}
		});

	}
}

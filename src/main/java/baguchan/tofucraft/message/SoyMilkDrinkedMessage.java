package baguchan.tofucraft.message;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.LogicalSide;

public class SoyMilkDrinkedMessage {
	private final int entityId;

	private final int level;
	private final boolean canUpdate;

	public SoyMilkDrinkedMessage(LivingEntity entity, int level, boolean canUpdate) {
		this.entityId = entity.getId();
		this.level = level;
		this.canUpdate = canUpdate;
	}

	public SoyMilkDrinkedMessage(int id, int level, boolean canUpdate) {
		this.entityId = id;
		this.level = level;
		this.canUpdate = canUpdate;
	}

	public void serialize(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeInt(this.level);
		buffer.writeBoolean(this.canUpdate);
	}

	public static SoyMilkDrinkedMessage deserialize(FriendlyByteBuf buffer) {
		int entityId = buffer.readInt();
		int level = buffer.readInt();
		boolean update = buffer.readBoolean();
		return new SoyMilkDrinkedMessage(entityId, level, update);
	}

	public void handle(CustomPayloadEvent.Context context) {
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Entity entity = (Minecraft.getInstance()).player.level().getEntity(entityId);
				if (entity != null && entity instanceof LivingEntity)
					entity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent((cap) -> {
						cap.setSoyHealthLevel((LivingEntity) entity, level, canUpdate);
					});
			});
		}
		context.setPacketHandled(true);
	}
}

package baguchan.tofucraft.message;

import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.registry.TofuCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.network.NetworkEvent;

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

	public boolean handle(NetworkEvent.Context context) {
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Entity entity = (Minecraft.getInstance()).player.level().getEntity(entityId);
				if (entity != null && entity instanceof LivingEntity living) {
					SoyHealthCapability cap = living.getData(TofuCapability.SOY_HEALTH);
					cap.setSoyHealthLevel((LivingEntity) entity, level, canUpdate);
				}
			});
		}
		return true;
	}
}

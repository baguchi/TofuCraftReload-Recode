package baguchan.tofucraft.network;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.registry.TofuCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class SoyMilkDrinkedPacket implements CustomPacketPayload {
	public static final ResourceLocation ID = TofuCraftReload.prefix("soy_milk_drinked");

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
	public ResourceLocation id() {
		return ID;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeInt(this.level);
		buffer.writeBoolean(this.canUpdate);
	}

	public SoyMilkDrinkedPacket(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readBoolean());
	}

	public static void handle(SoyMilkDrinkedPacket message, PlayPayloadContext context) {
		context.workHandler().execute(() -> {
			Entity entity = (Minecraft.getInstance()).player.level().getEntity(message.entityId);
			if (entity != null && entity instanceof LivingEntity living) {
				SoyHealthCapability cap = living.getData(TofuCapability.SOY_HEALTH);
				cap.setSoyHealthLevel((LivingEntity) entity, message.level, message.canUpdate);
			}
		});

	}
}

package baguchan.tofucraft.message;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SoyHealthMessage {
	private final int entityId;

	private final float health;
	private final float maxHealth;

	public SoyHealthMessage(LivingEntity entity, float health, float maxHealth) {
		this.entityId = entity.getId();
		this.health = health;
		this.maxHealth = maxHealth;
	}

	public SoyHealthMessage(int id, float health, float maxHealth) {
		this.entityId = id;
		this.health = health;
		this.maxHealth = maxHealth;
	}

	public void serialize(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeFloat(this.health);
		buffer.writeFloat(this.maxHealth);
	}

	public static SoyHealthMessage deserialize(FriendlyByteBuf buffer) {
		int entityId = buffer.readInt();
		float health = buffer.readFloat();
		float maxHealth = buffer.readFloat();
		return new SoyHealthMessage(entityId, health, maxHealth);
	}

	public static boolean handle(SoyHealthMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Entity entity = (Minecraft.getInstance()).player.level.getEntity(message.entityId);
				if (entity != null && entity instanceof LivingEntity)
					entity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent((cap) -> {
						cap.setSoyHealth((LivingEntity) entity, message.health, message.maxHealth);
					});
			});
		}
		return true;
	}
}

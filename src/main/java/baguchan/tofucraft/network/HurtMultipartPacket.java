package baguchan.tofucraft.network;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class HurtMultipartPacket implements CustomPacketPayload, IPayloadHandler<HurtMultipartPacket> {

	public static final StreamCodec<FriendlyByteBuf, HurtMultipartPacket> STREAM_CODEC = CustomPacketPayload.codec(
			HurtMultipartPacket::write, HurtMultipartPacket::new
	);
	public static final Type<HurtMultipartPacket> TYPE = CustomPacketPayload.createType(TofuCraftReload.prefix("hurt_multipart").toString());

	public int parent;
	public float damage;
	public String damageType;

	public HurtMultipartPacket(int parent, float damage) {
		this.parent = parent;
		this.damage = damage;
		this.damageType = "";
	}

	public HurtMultipartPacket(int parent, float damage, String damageType) {
		this.parent = parent;
		this.damage = damage;
		this.damageType = damageType;
	}

	public HurtMultipartPacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readFloat(), buf.readUtf());
	}

	public void write(FriendlyByteBuf buf) {
		buf.writeInt(this.parent);
		buf.writeFloat(this.damage);
		buf.writeUtf(this.damageType);
	}


	public void handle(HurtMultipartPacket message, IPayloadContext context) {
		Player player = context.player();

		if (player != null) {
			if (player.level() != null) {
				Entity parent2 = player.level().getEntity(message.parent);
				Registry<DamageType> registry = player.level().registryAccess().registry(Registries.DAMAGE_TYPE).get();
				DamageType dmg = registry.get(new ResourceLocation(message.damageType));
				if (dmg != null) {
					Holder<DamageType> holder = registry.getHolder(registry.getId(dmg)).orElseGet(null);
					if (holder != null) {
						DamageSource source = new DamageSource(registry.getHolder(registry.getId(dmg)).get());
						if (parent2 != null) {
							parent2.hurt(source, message.damage);
						}

					}
				}

			}
		}
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
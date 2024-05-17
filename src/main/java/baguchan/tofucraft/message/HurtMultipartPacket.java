package baguchan.tofucraft.message;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HurtMultipartPacket {
	public int attacker;
	public int parent;
	public float damage;
	public String damageType;

	public HurtMultipartPacket(int attacker, int parent, float damage, String damageType) {
		this.attacker = attacker;
		this.parent = parent;
		this.damage = damage;
		this.damageType = damageType;
	}

	public static HurtMultipartPacket read(FriendlyByteBuf buf) {
		return new HurtMultipartPacket(buf.readInt(), buf.readInt(), buf.readFloat(), buf.readUtf());
	}

	public void write(FriendlyByteBuf buf) {
		buf.writeInt(this.attacker);
		buf.writeInt(this.parent);
		buf.writeFloat(this.damage);
		buf.writeUtf(this.damageType);
	}

	public static boolean handle(HurtMultipartPacket message, Supplier<NetworkEvent.Context> context) {
		Player player = context.get().getSender();

		if (player != null) {
			if (player.level() != null) {
				Entity attacker = player.level().getEntity(message.attacker);

				Entity parent2 = player.level().getEntity(message.parent);
				Registry<DamageType> registry = player.level().registryAccess().registry(Registries.DAMAGE_TYPE).get();
				DamageType dmg = registry.get(new ResourceLocation(message.damageType));
				if (dmg != null) {
					Holder<DamageType> holder = registry.getHolder(registry.getId(dmg)).orElseGet(null);
					if (holder != null) {
						DamageSource source = new DamageSource(registry.getHolder(registry.getId(dmg)).get());
						if (parent2 != null) {
							parent2.hurt(source, message.damage);
							if (attacker instanceof Player player1 && parent2 instanceof LivingEntity livingEntity) {
								player1.getMainHandItem().hurtEnemy(livingEntity, player1);
							}
						}

					}
				}

			}
		}
		return true;
	}

}

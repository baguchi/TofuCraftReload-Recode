package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.TofuPig;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ZundaOnAStickItem<T extends Entity & ItemSteerable> extends Item {
	private final EntityType<T> canInteractWith;
	private final int consumeItemDamage;

	public ZundaOnAStickItem(Properties p_41383_, EntityType<T> canInteractWith, int consumeItemDamage) {
		super(p_41383_);
		this.canInteractWith = canInteractWith;
		this.consumeItemDamage = consumeItemDamage;
	}

	public InteractionResultHolder<ItemStack> use(Level p_41314_, Player p_41315_, InteractionHand p_41316_) {
		ItemStack itemstack = p_41315_.getItemInHand(p_41316_);
		if (p_41314_.isClientSide) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			Entity entity = p_41315_.getVehicle();
			if (p_41315_.isPassenger() && entity instanceof ItemSteerable itemsteerable && entity.getType() == this.canInteractWith && entity instanceof TofuPig pig) {
				if (pig.getTofuPigType() == TofuPig.TofuPigType.ZUNDA) {
					float radius = 3;
					float healPosX = (float) (entity.getX() + radius * Math.cos(Math.toRadians(entity.getYRot() + 90)));
					float healPosZ = (float) (entity.getZ() + radius * Math.sin(Math.toRadians(entity.getYRot() + 90)));
					int particleCount = 15;
					for (int i = 1; i <= particleCount; i++) {
						double yaw = i * 360.f / particleCount;
						double speed = 0.9;
						double xSpeed = speed * Math.cos(Math.toRadians(yaw));
						double zSpeed = speed * Math.sin(Math.toRadians(yaw));
						//entity.level.addParticle(new ParticleZundaCloud.CloudData(TofuParticleTypes.ZUNDA_CLOUD.get(), 0.75f, 0.75f, 1f, 40f, 22, ParticleZundaCloud.EnumCloudBehavior.GROW, 1f), entity.getX(), entity.getY() + 1f, entity.getZ(), xSpeed, 0, zSpeed);
					}
					for (int i = 1; i <= particleCount; i++) {
						double yaw = i * 360.f / particleCount;
						double speed = 0.65;
						double xSpeed = speed * Math.cos(Math.toRadians(yaw));
						double zSpeed = speed * Math.sin(Math.toRadians(yaw));
						//entity.level.addParticle(new ParticleZundaCloud.CloudData(TofuParticleTypes.ZUNDA_CLOUD.get(), 0.75f, 0.75f, 1f, 35f, 22, ParticleZundaCloud.EnumCloudBehavior.GROW, 1f), entity.getX(), entity.getY() + 1f, entity.getZ(), xSpeed, 0, zSpeed);
					}
					AABB hitBox = new AABB(new BlockPos(healPosX - 0.5f, entity.getY(), healPosZ - 0.5f)).inflate(3, 3, 3);
					List<LivingEntity> entitiesHit = entity.level.getEntitiesOfClass(LivingEntity.class, hitBox);
					for (LivingEntity hitEntity : entitiesHit) {
						hitEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200));
						hitEntity.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
					}
					itemstack.hurtAndBreak(this.consumeItemDamage, p_41315_, (p_41312_) -> {
						p_41312_.broadcastBreakEvent(p_41316_);
					});
					if (itemstack.isEmpty()) {
						ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);
						itemstack1.setTag(itemstack.getTag());
						return InteractionResultHolder.success(itemstack1);
					}

					return InteractionResultHolder.success(itemstack);
				}
			}

			p_41315_.awardStat(Stats.ITEM_USED.get(this));
			return InteractionResultHolder.pass(itemstack);
		}
	}
}

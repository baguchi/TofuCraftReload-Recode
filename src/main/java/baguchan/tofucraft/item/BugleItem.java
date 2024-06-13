package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.TofuSpider;
import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.List;

public class BugleItem extends Item {
	private static final TargetingConditions TARGETING = TargetingConditions.forNonCombat().range(32.0D).ignoreInvisibilityTesting();


	public BugleItem(Properties tab) {
		super(tab);
	}

	@Override
	public void releaseUsing(ItemStack p_41412_, Level p_41413_, LivingEntity p_41414_, int p_41415_) {
		int i = getUseDuration(p_41412_, p_41414_) - p_41415_;

		if (p_41414_ instanceof Player) {
			Player playerentity = (Player) p_41414_;
			if (i >= 20) {
				playerentity.getCooldowns().addCooldown(this, 80);
			}
		}
		if (i >= 20) {
			p_41413_.gameEvent(p_41414_, GameEvent.INSTRUMENT_PLAY, p_41414_.position());
			if (p_41414_.getOffhandItem().is(Items.ECHO_SHARD)) {
				if (p_41414_ instanceof Player) {
					Player playerentity = (Player) p_41414_;
					List<TofuSpider> entities = p_41413_.getNearbyEntities(TofuSpider.class, TARGETING, p_41414_, p_41414_.getBoundingBox().inflate(6.0D));


					if (!entities.isEmpty()) {
						Collections.shuffle(entities);

						if (hasLineOfSight(playerentity, entities.get(0))) {
							playerentity.getCooldowns().addCooldown(this, 600);
							p_41414_.getOffhandItem().shrink(1);

							entities.get(0).startConverting(300);
							p_41413_.levelEvent(3007, entities.get(0).blockPosition(), 0);
						}
					}
				}
			} else {
				p_41414_.playSound(TofuSounds.TOFUBUGLE.get(), 3.0F, 1.0F);
			}
		}
	}

	//Prevent change in hasLineOfSight mixin
	public static boolean hasLineOfSight(LivingEntity livingEntity, Entity p_147185_) {
		if (p_147185_.level() != livingEntity.level()) {
			return false;
		} else {
			Vec3 vec3 = new Vec3(livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ());
			Vec3 vec31 = new Vec3(p_147185_.getX(), p_147185_.getEyeY(), p_147185_.getZ());
			if (vec31.distanceTo(vec3) > 128.0D) {
				return false;
			} else {
				return livingEntity.level().clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, livingEntity)).getType() == HitResult.Type.MISS;
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		ItemStack itemstack = p_41433_.getItemInHand(p_41434_);
		p_41433_.startUsingItem(p_41434_);

		return InteractionResultHolder.success(itemstack);
	}

	@Override
	public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.BOW;
	}
}

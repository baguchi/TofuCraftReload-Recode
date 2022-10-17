package baguchan.tofucraft.item;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SoymilkBottleItem extends Item {
	private final MobEffect effect;

	private final MobEffect secondEffect;

	public SoymilkBottleItem(MobEffect effect, MobEffect secondEffect, Item.Properties properties) {
		super(properties);
		this.effect = effect;
		this.secondEffect = secondEffect;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
		super.finishUsingItem(p_41409_, p_41410_, p_41411_);
		p_41411_.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
			if (!p_41410_.isClientSide) {
				if (p_41410_.getDayTime() > cap.getRemainTick() + 12000L) {
					if (cap.getSoyHealthLevel() < 1) {
						cap.setSoyHealthLevel(p_41411_, 1, true);
					} else {
						cap.setSoyHealthLevel(p_41411_, cap.getSoyHealthLevel() + 1, true);
					}
					if (cap.getSoyHealthLevel() > 4) {
						p_41411_.addEffect(new MobEffectInstance(this.getSecondEffect(), 24000, 0));
					}
					if (cap.getSoyHealthLevel() > 1) {
						cap.setSoyHealth(p_41411_, cap.getSoyHealth() + 2, cap.getSoyMaxHealth() + 2);
					}
				}

				p_41411_.addEffect(new MobEffectInstance(this.getEffect(), 200 * cap.getSoyHealthLevel(), 0));
			}
		});
		if (p_41411_ instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) p_41411_;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, p_41409_);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		if (!(p_41411_ instanceof Player) || !((Player) p_41411_).getAbilities().instabuild) {
			p_41409_.shrink(1);
		}
		if (p_41409_.isEmpty())
			return new ItemStack(Items.GLASS_BOTTLE);
		if (p_41411_ instanceof Player && !((Player) p_41411_).getAbilities().instabuild) {
			ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
			Player playerentity = (Player) p_41411_;
			if (!playerentity.getInventory().add(itemstack)) {
				playerentity.drop(itemstack, false);
			}
		}
		return p_41409_;
	}


	public int getUseDuration(ItemStack p_77626_1_) {
		return 32;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.DRINK;
	}

	public SoundEvent getDrinkingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	public SoundEvent getEatingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		return ItemUtils.startUsingInstantly(p_41432_, p_41433_, p_41434_);
	}

	public MobEffect getEffect() {
		return effect;
	}

	public MobEffect getSecondEffect() {
		return secondEffect;
	}
}


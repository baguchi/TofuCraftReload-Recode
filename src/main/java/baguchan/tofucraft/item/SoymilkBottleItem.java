package baguchan.tofucraft.item;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class SoymilkBottleItem extends Item {
	private final Effect effect;

	private final Effect secondEffect;

	public SoymilkBottleItem(Effect effect, Effect secondEffect, Properties properties) {
		super(properties);
		this.effect = effect;
		this.secondEffect = secondEffect;
	}

	public ItemStack finishUsingItem(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
		super.finishUsingItem(p_77654_1_, p_77654_2_, p_77654_3_);
		p_77654_3_.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
			p_77654_3_.addEffect(new EffectInstance(this.effect, 200 * cap.getSoyHealthLevel(), 0));
			if (cap.getRemainTick() < 24000) {
				cap.setSoyHealth(p_77654_3_, cap.getSoyHealthLevel() + 1);
				if (cap.getSoyHealthLevel() > 4)
					p_77654_3_.addEffect(new EffectInstance(this.secondEffect, 24000, 0));
			}
		});
		if (p_77654_3_ instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) p_77654_3_;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, p_77654_1_);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		p_77654_1_.shrink(1);
		if (p_77654_1_.isEmpty())
			return new ItemStack(Items.GLASS_BOTTLE);
		if (p_77654_3_ instanceof PlayerEntity && !((PlayerEntity) p_77654_3_).abilities.instabuild) {
			ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
			PlayerEntity playerentity = (PlayerEntity) p_77654_3_;
			if (!playerentity.inventory.add(itemstack)) {
				playerentity.drop(itemstack, false);
			}
		}
		return p_77654_1_;
	}

	public int getUseDuration(ItemStack p_77626_1_) {
		return 32;
	}

	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.DRINK;
	}

	public SoundEvent getDrinkingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	public SoundEvent getEatingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
		return DrinkHelper.useDrink(p_77659_1_, p_77659_2_, p_77659_3_);
	}
}

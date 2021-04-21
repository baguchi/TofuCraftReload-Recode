package baguchan.tofucraft.item;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.capability.SoyHealthCapability;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SoymilkBottleItem extends Item {
	private final Effect effect;

	private final Effect secondEffect;

	public SoymilkBottleItem(Effect effect, Effect secondEffect, Properties properties) {
		super(properties);
		this.effect = effect;
		this.secondEffect = secondEffect;
	}

	public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
		super.func_77654_b(p_77654_1_, p_77654_2_, p_77654_3_);
		p_77654_3_.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
			p_77654_3_.func_195064_c(new EffectInstance(this.effect, 200 * cap.getSoyHealthLevel(), 0));
			if (cap.getRemainTick() < 24000) {
				cap.setSoyHealth(p_77654_3_, cap.getSoyHealthLevel() + 1);
				if (cap.getSoyHealthLevel() > 4)
					p_77654_3_.func_195064_c(new EffectInstance(this.secondEffect, 24000, 0));
			}
		});
		if (p_77654_3_ instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) p_77654_3_;
			CriteriaTriggers.field_193138_y.func_193148_a(serverplayerentity, p_77654_1_);
			serverplayerentity.func_71029_a(Stats.field_75929_E.func_199076_b(this));
		}
		p_77654_1_.func_190918_g(1);
		if (p_77654_1_.func_190926_b())
			return new ItemStack((IItemProvider) Items.field_151069_bo);
		if (p_77654_3_ instanceof PlayerEntity && !((PlayerEntity) p_77654_3_).field_71075_bZ.field_75098_d) {
			ItemStack itemstack = new ItemStack((IItemProvider) Items.field_151069_bo);
			PlayerEntity playerentity = (PlayerEntity) p_77654_3_;
			if (!playerentity.field_71071_by.func_70441_a(itemstack))
				playerentity.func_71019_a(itemstack, false);
		}
		return p_77654_1_;
	}

	public int func_77626_a(ItemStack p_77626_1_) {
		return 32;
	}

	public UseAction func_77661_b(ItemStack p_77661_1_) {
		return UseAction.DRINK;
	}

	public SoundEvent func_225520_U__() {
		return SoundEvents.field_187664_bz;
	}

	public SoundEvent func_225519_S__() {
		return SoundEvents.field_187664_bz;
	}

	public ActionResult<ItemStack> func_77659_a(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
		return DrinkHelper.func_234707_a_(p_77659_1_, p_77659_2_, p_77659_3_);
	}
}

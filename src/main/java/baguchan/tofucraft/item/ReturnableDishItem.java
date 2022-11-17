package baguchan.tofucraft.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ReturnableDishItem extends Item {
	private final Item dishItem;
	private final boolean comfortable;

	public ReturnableDishItem(Item dishItem, Properties p_41383_) {
		this(dishItem, p_41383_, true);
	}

	public ReturnableDishItem(Item dishItem, Properties p_41383_, boolean comfortable) {
		super(p_41383_);
		this.dishItem = dishItem;
		this.comfortable = comfortable;
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
		var resultItem = super.finishUsingItem(itemStack, level, livingEntity);
		if (livingEntity instanceof ServerPlayer serverPlayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
		}

		if (this.comfortable) {
			MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("farmersdelight", "comfort"));
			if (effect != null) {
				livingEntity.addEffect(new MobEffectInstance(effect, 2400));
			}
		}

		if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
			if (itemStack.isEmpty()) {
				resultItem = new ItemStack(dishItem);
			} else {
				ItemStack itemstack = new ItemStack(dishItem);
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}
		}
		return resultItem;
	}
}

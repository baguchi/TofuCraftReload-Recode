package baguchan.tofucraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ReturnableDishItem extends Item {
	private final Supplier<Item> dishItem;
	private final boolean comfortable;

	public ReturnableDishItem(Supplier<Item> dishItem, Properties p_41383_) {
		this(dishItem, p_41383_, true);
	}

	public ReturnableDishItem(Supplier<Item> dishItem, Properties p_41383_, boolean comfortable) {
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
			Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.getHolder(new ResourceLocation("farmersdelight", "comfort"));
			FoodProperties foodProperties = this.getFoodProperties(itemStack, livingEntity);
			if (foodProperties != null && effect.isPresent()) {
				livingEntity.addEffect(new MobEffectInstance(effect.get(), 600 * foodProperties.nutrition()));
			}
		}

		if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
			if (itemStack.isEmpty()) {
				resultItem = new ItemStack(dishItem.get());
			} else {
				ItemStack itemstack = new ItemStack(dishItem.get());
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}
		}
		return resultItem;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
		MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(new ResourceLocation("farmersdelight", "comfort"));
		if (effect != null) {
			p_41423_.add(Component.translatable("tofucraft.has_comfort").withStyle(ChatFormatting.GOLD));
		}
	}
}

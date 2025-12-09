package baguchi.tofucraft.item;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class DishItem extends Item {
	private final boolean comfortable;
	private final boolean salt;

	public DishItem(Properties p_41383_) {
		this(p_41383_, true, false);
	}

	public DishItem(Properties p_41383_, boolean comfortable) {
		this(p_41383_, comfortable, false);
	}

	public DishItem(Properties p_41383_, boolean comfortable, boolean salt) {
		super(p_41383_);
		this.comfortable = comfortable;
		this.salt = salt;
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
		var resultItem = super.finishUsingItem(itemStack, level, livingEntity);

		if (this.comfortable) {
			Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.get(Identifier.fromNamespaceAndPath("farmersdelight", "comfort"));
			FoodProperties foodProperties = itemStack.get(DataComponents.FOOD);
			if (foodProperties != null && effect.isPresent()) {
				livingEntity.addEffect(new MobEffectInstance(effect.get(), 600 * foodProperties.nutrition()));
			}
		}

		if (this.salt) {
			FoodProperties foodProperties = itemStack.get(DataComponents.FOOD);
			if (foodProperties != null) {
				livingEntity.addEffect(new MobEffectInstance(TofuEffects.SALT_BOOST, foodProperties.nutrition() * 20 * 60));
			}
		}
		return resultItem;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_399884_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_399884_, p_41424_);
		Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.get(Identifier.fromNamespaceAndPath("farmersdelight", "comfort"));
		if (effect.isPresent()) {
			p_399884_.accept(Component.translatable("tofucraft.has_comfort").withStyle(ChatFormatting.GOLD));
		}
		if (p_41421_.is(TofuItems.YUDOFU)) {
			p_399884_.accept(Component.translatable("tofucraft.has_soy_wolf_food").withStyle(ChatFormatting.GRAY));
		}
	}

	@EventBusSubscriber(modid = TofuCraftReload.MODID)
	public static class SoyFoodEvent {
		@SubscribeEvent
		@SuppressWarnings("unused")
		public static void onSoyFoodForWolf(PlayerInteractEvent.EntityInteract event) {
			Player player = event.getEntity();
			Entity target = event.getTarget();
			ItemStack itemStack = event.getItemStack();

			if (target instanceof LivingEntity entity && target instanceof Wolf wolf) {
				if (entity.isAlive() && wolf.isTame() && itemStack.is(TofuItems.YUDOFU)) {
					if (!wolf.getData(TofuAttachments.TOFU_LIVING).isEatCooldown()) {
						entity.heal(1);
						entity.addEffect(new MobEffectInstance(TofuEffects.SOY_HEALTHY, 3200, 1));
						wolf.getData(TofuAttachments.TOFU_LIVING).setEatCooldown(3200);
						entity.level().playSound(null, target.blockPosition(), SoundEvents.GENERIC_EAT.value(), SoundSource.PLAYERS, 0.8F, 0.8F);

						if (itemStack.has(DataComponents.USE_REMAINDER) && !player.isCreative()) {
							player.addItem(itemStack.get(DataComponents.USE_REMAINDER).convertInto());
							itemStack.shrink(1);
						}

						event.setCancellationResult(InteractionResult.SUCCESS);
						event.setCanceled(true);
					} else {
						player.displayClientMessage(Component.translatable("tofucraft.soy_food.cannot_give_on_wolf", wolf.getName()), true);
						event.setCancellationResult(InteractionResult.FAIL);
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
		if (target instanceof Wolf wolf) {
			if (wolf.isAlive() && wolf.isTame()) {
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}
}

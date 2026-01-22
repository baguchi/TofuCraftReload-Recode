package baguchi.tofucraft.item;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuAdvancements;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.function.Consumer;

public class GhastFoodItem extends Item {
	public GhastFoodItem(Properties p_41383_) {
		super(p_41383_);
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_399884_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_399884_, p_41424_);
		if (p_41421_.is(TofuItems.SOUL_HIYAYAKKO_GLASS)) {
			p_399884_.accept(Component.translatable("tofucraft.has_soul_for_happy_ghast").withStyle(ChatFormatting.AQUA));
		}
	}

	@EventBusSubscriber(modid = TofuCraftReload.MODID)
	public static class SoyFoodEvent {
		@SubscribeEvent
		@SuppressWarnings("unused")
		public static void onSoyFoodForHappyGhast(PlayerInteractEvent.EntityInteract event) {
			Player player = event.getEntity();
			Entity target = event.getTarget();
			ItemStack itemStack = event.getItemStack();

			if (target instanceof LivingEntity entity && target instanceof HappyGhast happyGhast) {
				if (entity.isAlive() && itemStack.is(TofuItems.SOUL_HIYAYAKKO_GLASS)) {
					if (!happyGhast.getData(TofuAttachments.TOFU_LIVING).isEatCooldown()) {
						entity.heal(3);
						entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 3200, 3));

						entity.addEffect(new MobEffectInstance(TofuEffects.SOY_HEALTHY, 3200, 1));
						happyGhast.getData(TofuAttachments.TOFU_LIVING).setEatCooldown(3200);
						entity.level().playSound(null, target.blockPosition(), SoundEvents.GENERIC_EAT.value(), SoundSource.PLAYERS, 0.8F, 0.8F);

						if (itemStack.has(DataComponents.USE_REMAINDER) && !player.isCreative()) {
							player.addItem(itemStack.get(DataComponents.USE_REMAINDER).convertInto().create());
							itemStack.shrink(1);
						}
						if (event.getEntity() instanceof ServerPlayer serverPlayer) {
							TofuAdvancements.TOO_COLD.get().trigger(serverPlayer);
						}

						event.setCancellationResult(InteractionResult.SUCCESS);
						event.setCanceled(true);
					} else {
						player.displayClientMessage(Component.translatable("tofucraft.soy_food.cannot_give_on_wolf", happyGhast.getName()), true);
						event.setCancellationResult(InteractionResult.FAIL);
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
		if (target instanceof HappyGhast wolf) {
			if (wolf.isAlive()) {
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}
}

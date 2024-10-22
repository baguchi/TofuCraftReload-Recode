package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

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
			Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath("farmersdelight", "comfort"));
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
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
		Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath("farmersdelight", "comfort"));
		if (effect.isPresent()) {
			p_41423_.add(Component.translatable("tofucraft.has_comfort").withStyle(ChatFormatting.GOLD));
		}
	}
}

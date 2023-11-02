package baguchan.tofucraft.item;

import net.minecraft.ChatFormatting;
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
import net.neoforged.neoforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NourishmentItem extends Item {
	public NourishmentItem(Properties p_41383_) {
		super(p_41383_);
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
		var resultItem = super.finishUsingItem(itemStack, level, livingEntity);
		MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("farmersdelight", "nourishment"));
		if (effect != null) {
			FoodProperties foodProperties = this.getFoodProperties(itemStack, livingEntity);
			if (foodProperties != null && effect != null) {
				livingEntity.addEffect(new MobEffectInstance(effect, 600 * foodProperties.getNutrition()));
			}
		}

		return resultItem;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
		MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("farmersdelight", "nourishment"));
		if (effect != null) {
			p_41423_.add(Component.translatable("tofucraft.has_nourishment").withStyle(ChatFormatting.GOLD));
		}
	}
}

package baguchi.tofucraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class HoneySoymilkBottleItem extends SoymilkBottleItem {
	public HoneySoymilkBottleItem(Properties properties) {
		super(MobEffects.REGENERATION, MobEffects.ABSORPTION, properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		super.finishUsingItem(stack, level, livingEntity);
		if (!level.isClientSide) {
			livingEntity.removeEffect(MobEffects.POISON);
		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, tooltipContext, components, flag);
		components.add(Component.translatable("item.tofucraft.soymilk_honey.desc").withStyle(ChatFormatting.GRAY));
	}
}

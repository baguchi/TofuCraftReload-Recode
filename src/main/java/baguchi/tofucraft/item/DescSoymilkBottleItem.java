package baguchi.tofucraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class DescSoymilkBottleItem extends SoymilkBottleItem {
	public DescSoymilkBottleItem(Holder<MobEffect> effect, Holder<MobEffect> secondEffect, Properties properties) {
		super(effect, secondEffect, properties);
	}


	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_399884_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_399884_, p_41424_);
		p_399884_.accept(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));

	}
}

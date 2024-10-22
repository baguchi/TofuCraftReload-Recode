package baguchan.tofucraft.item.tool;

import baguchan.tofucraft.registry.TofuToolMaterials;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class TofuDiggerItem extends Item {
	public TofuDiggerItem(TofuToolMaterials.TofuToolMaterial p_362799_, TagKey<Block> p_204111_, float p_364972_, float p_364718_, Item.Properties p_204112_) {
		super(p_362799_.applyToolProperties(p_204112_, p_204111_, p_364972_, p_364718_));
	}

	/**
	 * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise the damage on the stack.
	 */
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return true;
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
	}
}
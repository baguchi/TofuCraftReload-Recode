package baguchan.tofucraft.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HoneySoymilkBottleItem extends SoymilkBottleItem {
    public HoneySoymilkBottleItem(Properties properties) {
        super(MobEffects.REGENERATION, MobEffects.ABSORPTION, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity user) {
        super.finishUsingItem(itemStack, world, user);
        if (!world.isClientSide) {
            user.removeEffect(MobEffects.POISON);
        }
        return itemStack;
    }
}

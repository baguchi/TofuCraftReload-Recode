package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RollingPinItem extends Item {
    public RollingPinItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(TofuItems.ROLLINGPIN.get());
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }
}

package baguchan.tofucraft.inventory.slot;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import baguchan.tofucraft.blockentity.tfenergy.TFAggregatorBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

@ParametersAreNonnullByDefault
public class TFAggreatorResultSlot extends SlotItemHandler {
    public final TFAggregatorBlockEntity tileEntity;
    private final Player player;
    private int removeCount;

    public TFAggreatorResultSlot(Player player, TFAggregatorBlockEntity tile, IItemHandler inventoryIn, int index,
            int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.tileEntity = tile;
        this.player = player;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    @Nonnull
    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    @Override
    public void onTake(Player thePlayer, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(thePlayer, stack);
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
        stack.onCraftedBy(this.player.level, this.player, this.removeCount);

        if (!this.player.level.isClientSide) {
            tileEntity.clearUsedRecipes(this.player);
        }

        this.removeCount = 0;
    }
}

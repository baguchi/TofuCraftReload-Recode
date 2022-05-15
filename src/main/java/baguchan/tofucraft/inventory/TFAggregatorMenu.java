package baguchan.tofucraft.inventory;

import java.util.Objects;

import baguchan.tofucraft.blockentity.tfenergy.TFAggregatorBlockEntity;
import baguchan.tofucraft.inventory.slot.TFAggreatorResultSlot;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TFAggregatorMenu extends AbstractContainerMenu {

    public final TFAggregatorBlockEntity tileEntity;
    public final ItemStackHandler inventory;
    private final ContainerData containerData;
    private final ContainerLevelAccess canInteractWithCallable;

    
    
    public TFAggregatorMenu(final int windowId, final Inventory playerInventory,
            final TFAggregatorBlockEntity tileEntity, ContainerData cookingPotDataIn) {
        super(TofuContainers.TF_AGGREGATOR, windowId);
        this.tileEntity = tileEntity;
        this.inventory = tileEntity.getInventory();
        this.containerData = cookingPotDataIn;
        this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());
        int startX = 8;
        int startY = 18;
        
        this.addSlot(new SlotItemHandler(inventory, 0, 45, 32));
        this.addSlot(new TFAggreatorResultSlot(playerInventory.player, tileEntity, inventory, 1, 115, 32));

        // Main Player Inventory
        int startPlayerInvY = startY * 4 + 12;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * 18),
                        startPlayerInvY + (row * 18)));
            }
        }

        // Hotbar
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * 18), 142));
        }

        this.addDataSlots(cookingPotDataIn);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        // 0-1: Contain inventory
        // 2-28: Player inventory
        // 29-38: Hot bar in the player inventory

        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();

            if (index >= 0 && index <= 1) {
                if (!this.moveItemStackTo(itemStack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 2) {
                if (index >= 2 && index < 29) {
                    if (!this.moveItemStackTo(itemStack1, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 29 && index < 38 && !this.moveItemStackTo(itemStack1, 2, 28, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemStack1);
        }
        return itemStack;
    }

    private static TFAggregatorBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof TFAggregatorBlockEntity) {
            return (TFAggregatorBlockEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    public TFAggregatorMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(canInteractWithCallable, playerIn, TofuBlocks.TF_AGGREGATOR.get());
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressionRoll() {
        int i = this.containerData.get(0);
        int j = this.containerData.get(1);
        return j != 0 && i != 0 ? i * 28 / j : 0;
    }
    
    @OnlyIn(Dist.CLIENT)
    public int getTFEnergy() {
        int i = this.containerData.get(2);
        return i;
    }

    @OnlyIn(Dist.CLIENT)
    public int getTFMaxEnergy() {
        int i = this.containerData.get(3);
        return i;
    }

}

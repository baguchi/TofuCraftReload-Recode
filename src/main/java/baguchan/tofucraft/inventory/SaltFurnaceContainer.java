package baguchan.tofucraft.inventory;

import baguchan.tofucraft.registry.TofuContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SaltFurnaceContainer extends Container {
	private final IInventory container;

	private final IIntArray data;

	protected final World level;

	public SaltFurnaceContainer(int i, PlayerInventory playerInventory) {
		this(i, playerInventory, (IInventory) new Inventory(4), (IIntArray) new IntArray(4));
	}

	public SaltFurnaceContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray dataIn) {
		super(TofuContainers.SALT_FURNACE, id);
		this.container = furnaceInventoryIn;
		this.data = dataIn;
		this.level = playerInventoryIn.field_70458_d.level;
		func_75146_a(new Slot(this.container, 0, 23, 53));
		func_75146_a(new Slot(this.container, 1, 80, 53) {
			public boolean func_75214_a(ItemStack p_75214_1_) {
				return false;
			}
		});
		func_75146_a(new Slot(this.container, 2, 124, 20));
		func_75146_a(new Slot(this.container, 3, 102, 53) {
			public boolean func_75214_a(ItemStack p_75214_1_) {
				return false;
			}
		});
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++)
				func_75146_a(new Slot((IInventory) playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		}
		for (int k = 0; k < 9; k++)
			func_75146_a(new Slot((IInventory) playerInventoryIn, k, 8 + k * 18, 142));
		func_216961_a(dataIn);
	}

	public boolean func_75145_c(PlayerEntity p_75145_1_) {
		return this.container.func_70300_a(p_75145_1_);
	}

	public ItemStack func_82846_b(PlayerEntity p_82846_1_, int slotIndex) {
		ItemStack itemstack = ItemStack.field_190927_a;
		Slot slot = this.field_75151_b.get(slotIndex);
		if (slot != null && slot.func_75216_d()) {
			ItemStack itemstack1 = slot.func_75211_c();
			itemstack = itemstack1.func_77946_l();
			switch (slotIndex) {
				case 0:
					if (!func_75135_a(itemstack1, 4, 40, true))
						return ItemStack.field_190927_a;
					break;
			}
			if (!func_75135_a(itemstack1, 0, 0, false))
				return ItemStack.field_190927_a;
			slot.func_75220_a(itemstack1, itemstack);
			if (itemstack1.func_190926_b()) {
				slot.func_75215_d(ItemStack.field_190927_a);
			} else {
				slot.func_75218_e();
			}
			if (itemstack1.func_190916_E() == itemstack.func_190916_E())
				return ItemStack.field_190927_a;
			slot.func_190901_a(p_82846_1_, itemstack1);
		}
		return itemstack;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBurnProgress() {
		int i = this.data.func_221476_a(2);
		int j = this.data.func_221476_a(3);
		return (j != 0 && i != 0) ? (i * 24 / j) : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getLitProgress() {
		int i = this.data.func_221476_a(1);
		if (i == 0)
			i = 200;
		return this.data.func_221476_a(0) * 13 / i;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isLit() {
		return (this.data.func_221476_a(0) > 0);
	}
}

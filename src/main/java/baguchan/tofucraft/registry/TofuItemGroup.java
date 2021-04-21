package baguchan.tofucraft.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class TofuItemGroup {
	public static final ItemGroup TOFUCRAFT = new ItemGroup("tofucraft") {
		public ItemStack func_78016_d() {
			return new ItemStack((IItemProvider) TofuItems.TOFUMOMEN);
		}
	};
}

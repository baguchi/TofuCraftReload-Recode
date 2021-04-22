package baguchan.tofucraft.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TofuItemGroup {
	public static final ItemGroup TOFUCRAFT = new ItemGroup("tofucraft") {
		public ItemStack func_78016_d() {
			return new ItemStack(TofuItems.TOFUMOMEN);
		}
	};
}

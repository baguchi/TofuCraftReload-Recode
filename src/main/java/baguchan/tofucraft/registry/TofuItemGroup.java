package baguchan.tofucraft.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TofuItemGroup {
	public static final ItemGroup TOFUCRAFT = new ItemGroup("tofucraft") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TofuItems.TOFUMOMEN);
		}
	};
}

package baguchan.tofucraft.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TofuItemGroup {
	public static final CreativeModeTab TOFUCRAFT = new CreativeModeTab("tofucraft") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TofuItems.TOFUMOMEN);
		}
	};
}

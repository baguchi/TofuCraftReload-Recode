package baguchan.tofucraft.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TofuCreativeModeTab {
	public static final CreativeModeTab TOFUCRAFT = new CreativeModeTab("tofucraft") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TofuItems.TOFUMOMEN);
		}
	};

	public static final CreativeModeTab TOFU_DELIGHT = new CreativeModeTab("tofu_delight") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TofuItems.TOFUMOMEN);
		}
	};
}

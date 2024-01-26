package baguchan.tofucraft.inventory;

import baguchan.tofucraft.registry.TofuMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CrafterMenu;
import net.minecraft.world.inventory.MenuType;

public class TFCrafterMenu extends CrafterMenu {

	public TFCrafterMenu(int p_307357_, Inventory p_307257_) {
		super(p_307357_, p_307257_);
	}

	@Override
	public MenuType<?> getType() {
		return TofuMenus.TF_CRAFTER.get();
	}
}

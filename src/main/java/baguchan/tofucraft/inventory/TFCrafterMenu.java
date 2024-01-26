package baguchan.tofucraft.inventory;

import baguchan.tofucraft.registry.TofuMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.CrafterMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;

public class TFCrafterMenu extends CrafterMenu {

	public TFCrafterMenu(int p_307357_, Inventory p_307257_) {
		super(p_307357_, p_307257_);
	}

	public TFCrafterMenu(int p_307363_, Inventory p_307517_, CraftingContainer p_307449_, ContainerData p_307285_) {
		super(p_307363_, p_307517_, p_307449_, p_307285_);
	}

	@Override
	public MenuType<?> getType() {
		return TofuMenus.TF_CRAFTER.get();
	}
}

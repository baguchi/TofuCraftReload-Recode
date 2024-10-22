package baguchi.tofucraft.entity;

import net.minecraft.world.entity.ItemSteerable;

public interface ItemInteractable extends ItemSteerable {
	boolean canHeal();
}

package baguchan.tofucraft.item.tool;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;

public class TofuShovelItem extends ShovelItem implements IEnergyInsertable {
	public TofuShovelItem(ToolMaterial tofuItemTier, float p_362481_, float p_364182_, Properties properties) {
		super(tofuItemTier, p_362481_, p_364182_, properties);
	}


	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return calculated * 5;
			}
		}
		return 0;
	}
}

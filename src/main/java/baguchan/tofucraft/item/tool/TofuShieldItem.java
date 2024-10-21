package baguchan.tofucraft.item.tool;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;

public class TofuShieldItem extends ShieldItem implements IEnergyInsertable {
	public static final int EFFECTIVE_BLOCK_DELAY = 5;
	public static final float MINIMUM_DURABILITY_DAMAGE = 3.0F;
	public static final String TAG_BASE_COLOR = "Base";

	public TofuShieldItem(Properties p_43089_) {
		super(p_43089_);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
		return itemAbility == ItemAbilities.SHIELD_BLOCK;
	}

	@Override
	public void appendHoverText(ItemStack p_43094_, TooltipContext p_339613_, List<Component> p_43096_, TooltipFlag p_43097_) {
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
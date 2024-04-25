package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuItemTier;
import baguchan.tofucraft.utils.TofuDiamondToolUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

public class TofuPickaxeItem extends PickaxeItem implements IEnergyInsertable {
	public TofuPickaxeItem(Tier tofuItemTier, Properties properties) {
		super(tofuItemTier, properties);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		if (this.getTier() == TofuItemTier.TOFUDIAMOND) {
			Block blockDestroyed = player.level().getBlockState(pos).getBlock();
			TofuDiamondToolUtil.onBlockStartBreak(itemstack, player.level(), blockDestroyed, pos, player);
		}
		return false;
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return calculated;
			}
		}
		return 0;
	}
}

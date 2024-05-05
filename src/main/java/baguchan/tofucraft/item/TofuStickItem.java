package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class TofuStickItem extends Item implements IEnergyInsertable {
	public TofuStickItem(Properties tab) {
		super(tab);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getLevel().getBlockState(context.getClickedPos()).getBlock() == TofuBlocks.GRILLEDTOFU.get() &&
				TofuBlocks.TOFU_PORTAL.get().trySpawnPortal(context.getLevel(), context.getClickedPos().above())) {
			if (!context.getPlayer().isCreative())
				context.getItemInHand().hurtAndBreak(1, (LivingEntity) context.getPlayer(), LivingEntity.getSlotForHand(context.getHand()));
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}

	@Override
	public boolean isFoil(ItemStack p_77636_1_) {
		return true;
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

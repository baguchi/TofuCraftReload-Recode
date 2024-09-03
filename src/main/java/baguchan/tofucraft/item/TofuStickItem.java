package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.block.TofuPortalBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class TofuStickItem extends Item implements IEnergyInsertable {
	public TofuStickItem(Properties tab) {
		super(tab);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getPlayer() != null) {
			if (context.getPlayer().level().dimension() == TofuDimensions.tofu_world || context.getPlayer().level().dimension() == Level.OVERWORLD) {
				for (Direction direction : Direction.Plane.VERTICAL) {
					BlockPos framePos = context.getClickedPos().relative(direction);
					if (((TofuPortalBlock) TofuBlocks.TOFU_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
						context.getLevel().playSound(context.getPlayer(), framePos, SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.BLOCKS, 1.0F, 1.0F);
						return InteractionResult.CONSUME;
					} else return InteractionResult.FAIL;
				}
			}
		}
		return InteractionResult.FAIL;
	}

	@Override
	public Rarity getRarity(ItemStack p_77613_1_) {
		return Rarity.RARE;
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

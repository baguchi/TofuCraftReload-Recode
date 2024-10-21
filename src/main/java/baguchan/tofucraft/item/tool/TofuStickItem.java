package baguchan.tofucraft.item.tool;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.world.TofuPortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalShape;

import java.util.Optional;

public class TofuStickItem extends Item implements IEnergyInsertable {
	public TofuStickItem(Properties tab) {
		super(tab);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		BlockPos pos = context.getClickedPos();
		if (inPortalDimension(level)) {
			Optional<TofuPortalShape> optional = TofuPortalShape.findEmptyPortalShape(level, pos.offset(context.getClickedFace().getUnitVec3i()), Direction.Axis.X);
			if (optional.isPresent()) {
				optional.get().createPortalBlocks();
				if (!player.isCreative()) {
					context.getItemInHand().hurtAndBreak(1, (LivingEntity) player, LivingEntity.getSlotForHand(context.getHand()));
				}
				level.playSound(player, pos, SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.BLOCKS, 2.0F, 1.0F);

				return InteractionResult.SUCCESS;
			}
		}

		return super.useOn(context);
	}

	private static boolean inPortalDimension(Level p_49249_) {
		return p_49249_.dimension() == Level.OVERWORLD || p_49249_.dimension() == TofuDimensions.tofu_world;
	}

	private static boolean isPortal(Level p_49270_, BlockPos p_49271_, Direction p_49272_) {
		if (!inPortalDimension(p_49270_)) {
			return false;
		} else {
			BlockPos.MutableBlockPos blockpos$mutableblockpos = p_49271_.mutable();
			boolean flag = false;

			for (Direction direction : Direction.values()) {
				if (p_49270_.getBlockState(blockpos$mutableblockpos.set(p_49271_).move(direction)).isPortalFrame(p_49270_, blockpos$mutableblockpos)) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				return false;
			} else {
				Direction.Axis direction$axis = p_49272_.getAxis().isHorizontal()
						? p_49272_.getCounterClockWise().getAxis()
						: Direction.Plane.HORIZONTAL.getRandomAxis(p_49270_.random);
				return PortalShape.findEmptyPortalShape(p_49270_, p_49271_, direction$axis).isPresent();
			}
		}
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

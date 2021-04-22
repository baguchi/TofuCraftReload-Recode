package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TofuHoeItem extends Item {
	public TofuHoeItem(Properties tab) {
		super(tab);
	}

	public ActionResultType useOn(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.getLevel();
		BlockPos blockpos = p_195939_1_.getClickedPos();
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(p_195939_1_);
		if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		if (p_195939_1_.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
			if (world.getBlockState(blockpos).is(TofuTags.Blocks.TOFU_TERRAIN)) {
				PlayerEntity playerentity = p_195939_1_.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isClientSide) {
					world.setBlock(blockpos, TofuBlocks.TOFU_FARMLAND.defaultBlockState(), 11);
					if (playerentity != null) {
						p_195939_1_.getItemInHand().hurtAndBreak(1, playerentity, (p_220043_1_) -> {
							p_220043_1_.broadcastBreakEvent(p_195939_1_.getHand());
						});
					}
				}

				return ActionResultType.sidedSuccess(world.isClientSide);
			}
		}

		return ActionResultType.PASS;
	}
}

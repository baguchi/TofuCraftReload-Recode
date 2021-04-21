package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class TofuHoeItem extends Item {
	public TofuHoeItem(Properties tab) {
		super(tab);
	}

	public ActionResultType func_195939_a(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.func_195991_k();
		BlockPos blockpos = p_195939_1_.func_195995_a();
		int hook = ForgeEventFactory.onHoeUse(p_195939_1_);
		if (hook != 0)
			return (hook > 0) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		if (p_195939_1_.func_196000_l() != Direction.DOWN && world.func_175623_d(blockpos.func_177984_a())) {
			BlockState blockstate = world.getBlockState(blockpos);
			if (blockstate.getBlock() == TofuBlocks.TOFU_TERRAIN || blockstate.getBlock() == TofuBlocks.MOMENTOFU) {
				PlayerEntity playerentity = p_195939_1_.func_195999_j();
				world.func_184133_a(playerentity, blockpos, SoundEvents.field_187693_cj, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.field_72995_K) {
					world.func_180501_a(blockpos, TofuBlocks.TOFU_FARMLAND.func_176223_P(), 11);
					if (playerentity != null)
						p_195939_1_.func_195996_i().func_222118_a(1, (LivingEntity) playerentity, p_220043_1_ -> p_220043_1_.func_213334_d(p_195939_1_.func_221531_n()));
				}
				return ActionResultType.func_233537_a_(world.field_72995_K);
			}
		}
		return ActionResultType.PASS;
	}
}

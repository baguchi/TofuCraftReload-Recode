package baguchan.tofucraft.item;

import baguchan.tofucraft.api.BitternRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Map;

public class BitternItem extends Item {
	public BitternItem(Properties group) {
		super(group);
	}

	public ActionResultType useOn(ItemUseContext p_195939_1_) {
		return ActionResultType.PASS;
	}

	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
		BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos());
		if (blockraytraceresult.getType() == RayTraceResult.Type.MISS)
			return new ActionResult(ActionResultType.PASS, itemstack);
		if (blockraytraceresult.getType() == RayTraceResult.Type.BLOCK) {
			FluidState fluidState = worldIn.getFluidState(blockraytraceresult1.getBlockPos());
			Map.Entry<Fluid, Block> result = BitternRecipes.getResult(fluidState.getType().getFluid());
			if (result != null) {
				worldIn.setBlock(blockraytraceresult1.getBlockPos(), result.getValue().defaultBlockState(), 11);
				worldIn.globalLevelEvent(2001, blockraytraceresult1.getBlockPos(), Block.getId(worldIn.getBlockState(blockraytraceresult1.getBlockPos())));
				if (!playerIn.isCreative())
					itemstack.shrink(1);
				ItemStack itemstack2 = new ItemStack(Items.GLASS_BOTTLE);
				if (itemstack.isEmpty()) {
					playerIn.setItemInHand(handIn, itemstack2);
				} else if (!playerIn.isCreative() &&
						!playerIn.inventory.add(itemstack2)) {
					playerIn.drop(itemstack2, false);
				}
				return new ActionResult(ActionResultType.SUCCESS, playerIn.getItemInHand(handIn));
			}
		}
		return new ActionResult(ActionResultType.FAIL, itemstack);
	}
}

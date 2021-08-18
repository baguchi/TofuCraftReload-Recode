package baguchan.tofucraft.item;

import baguchan.tofucraft.utils.RecipeHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class BitternItem extends Item {
	public BitternItem(Properties group) {
		super(group);
	}

	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		BlockHitResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos());
		if (blockraytraceresult.getType() == HitResult.Type.MISS)
			return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
		if (blockraytraceresult.getType() == HitResult.Type.BLOCK) {
			FluidState fluidState = worldIn.getFluidState(blockraytraceresult1.getBlockPos());
			ItemStack result = RecipeHelper.getBitternResult(fluidState.getType());
			if (result != null) {
				worldIn.setBlock(blockraytraceresult1.getBlockPos(), Block.byItem(result.getItem()).defaultBlockState(), 11);
				worldIn.levelEvent(2001, blockraytraceresult1.getBlockPos(), Block.getId(worldIn.getBlockState(blockraytraceresult1.getBlockPos())));
				if (!playerIn.isCreative())
					itemstack.shrink(1);
				ItemStack itemstack2 = new ItemStack(Items.GLASS_BOTTLE);
				if (itemstack.isEmpty()) {
					playerIn.setItemInHand(handIn, itemstack2);
				} else if (!playerIn.isCreative() &&
						!playerIn.getInventory().add(itemstack2)) {
					playerIn.drop(itemstack2, false);
				}
				return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
			}
		}
		return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
	}
}

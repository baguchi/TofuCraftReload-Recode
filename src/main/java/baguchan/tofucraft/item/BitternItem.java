package baguchan.tofucraft.item;

import baguchan.tofucraft.utils.ContainerUtils;
import baguchan.tofucraft.utils.RecipeHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

		if (worldIn instanceof ServerLevel serverLevel) {
			if (blockraytraceresult.getType() == HitResult.Type.MISS)
				return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
			if (blockraytraceresult.getType() == HitResult.Type.BLOCK) {
				FluidState fluidState = worldIn.getFluidState(blockraytraceresult1.getBlockPos());
				ItemStack result = RecipeHelper.getBitternResult(serverLevel, fluidState.getType(), itemstack);
				if (result != null) {
					worldIn.setBlock(blockraytraceresult1.getBlockPos(), Block.byItem(result.getItem()).defaultBlockState(), 11);
					worldIn.levelEvent(2001, blockraytraceresult1.getBlockPos(), Block.getId(worldIn.getBlockState(blockraytraceresult1.getBlockPos())));
					worldIn.playSound(null, blockraytraceresult1.getBlockPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1F, 1F);

					ContainerUtils.addWithContainer(playerIn, handIn, itemstack, new ItemStack(Items.GLASS_BOTTLE), false);
					return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
				}
			}
		}
		return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);

	}
}

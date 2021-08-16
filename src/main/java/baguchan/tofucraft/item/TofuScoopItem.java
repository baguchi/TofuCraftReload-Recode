package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class TofuScoopItem extends Item {
	public TofuScoopItem(Properties group) {
		super(group);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		if (context.getLevel().getBlockState(context.getClickedPos()).is(TofuTags.Blocks.SOFT_TOFU)) {
			ItemStack stack = new ItemStack(Item.BY_BLOCK.get(context.getLevel().getBlockState(context.getClickedPos()).getBlock()));
			worldIn.removeBlock(context.getClickedPos(), false);
			if (!worldIn.isClientSide()) {
				if (context.getPlayer() != null)
					stack.hurtAndBreak(1, (LivingEntity) context.getPlayer(), p_220036_0_ -> p_220036_0_.broadcastBreakEvent(context.getHand()));
				double d0 = (worldIn.random.nextFloat() * 0.5F) + 0.25D;
				double d1 = (worldIn.random.nextFloat() * 0.5F);
				double d2 = (worldIn.random.nextFloat() * 0.5F) + 0.25D;
				ItemEntity itementity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, stack);
				itementity.setDefaultPickUpDelay();
				worldIn.addFreshEntity(itementity);
			}
			worldIn.playSound(null, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}
}

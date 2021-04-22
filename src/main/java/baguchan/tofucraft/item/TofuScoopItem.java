package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TofuScoopItem extends Item {
	public TofuScoopItem(Properties group) {
		super(group);
	}

	public ActionResultType useOn(ItemUseContext context) {
		World worldIn = context.getLevel();
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
			worldIn.playSound(null, pos, SoundEvents.SNOW_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return ActionResultType.SUCCESS;
		}
		return super.useOn(context);
	}
}

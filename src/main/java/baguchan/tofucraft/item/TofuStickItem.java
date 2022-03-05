package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;

public class TofuStickItem extends Item {
	public TofuStickItem(Properties tab) {
		super(tab);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getLevel().getBlockState(context.getClickedPos()).getBlock() == TofuBlocks.GRILLEDTOFU.get() &&
				TofuBlocks.TOFU_PORTAL.get().trySpawnPortal(context.getLevel(), context.getClickedPos().above())) {
			if (!context.getPlayer().isCreative())
				context.getItemInHand().hurtAndBreak(1, (LivingEntity) context.getPlayer(), p_213625_1_ -> p_213625_1_.broadcastBreakEvent(context.getHand()));
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}

	@Override
	public Rarity getRarity(ItemStack p_77613_1_) {
		return Rarity.RARE;
	}

	@Override
	public boolean isFoil(ItemStack p_77636_1_) {
		return true;
	}
}

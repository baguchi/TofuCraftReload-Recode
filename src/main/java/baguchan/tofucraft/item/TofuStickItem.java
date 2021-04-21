package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;

public class TofuStickItem extends Item {
	public TofuStickItem(Properties group) {
		super(group);
	}

	public ActionResultType func_195939_a(ItemUseContext context) {
		if (context.func_195991_k().getBlockState(context.func_195995_a()).getBlock() == TofuBlocks.GRILLEDTOFU &&
				TofuBlocks.TOFU_PORTAL.trySpawnPortal(context.func_195991_k(), context.func_195995_a().func_177984_a())) {
			if (!context.func_195999_j().func_184812_l_())
				context.func_195996_i().func_222118_a(1, (LivingEntity) context.func_195999_j(), p_213625_1_ -> p_213625_1_.func_213334_d(context.func_221531_n()));
			return ActionResultType.SUCCESS;
		}
		return super.func_195939_a(context);
	}

	public Rarity func_77613_e(ItemStack stack) {
		return Rarity.RARE;
	}

	public boolean func_77636_d(ItemStack stack) {
		return true;
	}
}

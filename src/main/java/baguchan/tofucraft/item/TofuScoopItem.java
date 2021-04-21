package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tags.ITag;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TofuScoopItem extends Item {
	public TofuScoopItem(Properties group) {
		super(group);
	}

	public ActionResultType func_195939_a(ItemUseContext context) {
		World worldIn = context.func_195991_k();
		BlockPos pos = context.func_195995_a();
		if (context.func_195991_k().getBlockState(context.func_195995_a()).func_235714_a_((ITag) TofuTags.Blocks.SOFT_TOFU)) {
			ItemStack stack = new ItemStack((IItemProvider) Item.field_179220_a.get(context.func_195991_k().getBlockState(context.func_195995_a()).getBlock()));
			worldIn.func_217377_a(context.func_195995_a(), false);
			if (!worldIn.isClientSide()) {
				if (context.func_195999_j() != null)
					stack.func_222118_a(1, (LivingEntity) context.func_195999_j(), p_220036_0_ -> p_220036_0_.func_213334_d(context.func_221531_n()));
				double d0 = (worldIn.field_73012_v.nextFloat() * 0.5F) + 0.25D;
				double d1 = (worldIn.field_73012_v.nextFloat() * 0.5F);
				double d2 = (worldIn.field_73012_v.nextFloat() * 0.5F) + 0.25D;
				ItemEntity itementity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, stack);
				itementity.func_174869_p();
				worldIn.func_217376_c((Entity) itementity);
			}
			worldIn.func_184133_a(null, pos, SoundEvents.field_187807_fF, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return ActionResultType.SUCCESS;
		}
		return super.func_195939_a(context);
	}
}

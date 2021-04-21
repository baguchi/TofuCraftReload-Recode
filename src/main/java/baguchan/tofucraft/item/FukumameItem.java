package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.FukumameEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class FukumameItem extends Item {
	public FukumameItem(Properties group) {
		super(group);
	}

	public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.func_184586_b(handIn);
		worldIn.func_184148_a((PlayerEntity) null, playerIn.func_226277_ct_(), playerIn.func_226278_cu_(), playerIn.func_226281_cx_(), SoundEvents.field_187511_aA, SoundCategory.PLAYERS, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
		if (!worldIn.field_72995_K)
			for (int i = 0; i < 5; i++) {
				FukumameEntity fukumamentity = new FukumameEntity(worldIn, (LivingEntity) playerIn);
				float d0 = worldIn.field_73012_v.nextFloat() * 20.0F - 10.0F;
				fukumamentity.func_234612_a_((Entity) playerIn, playerIn.field_70125_A + d0 * 0.25F, playerIn.field_70177_z + d0, 0.0F, 1.5F, 0.8F);
				worldIn.func_217376_c((Entity) fukumamentity);
			}
		playerIn.func_71029_a(Stats.field_75929_E.func_199076_b(this));
		playerIn.func_184811_cZ().func_185145_a(itemstack.func_77973_b(), 10);
		if (!playerIn.field_71075_bZ.field_75098_d)
			itemstack.func_222118_a(1, (LivingEntity) playerIn, playerEntity -> playerEntity.func_213334_d(handIn));
		return ActionResult.func_233538_a_(itemstack, worldIn.isClientSide());
	}
}

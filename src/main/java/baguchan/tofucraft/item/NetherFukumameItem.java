package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
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

public class NetherFukumameItem extends Item {
	private final boolean burning;

	public NetherFukumameItem(Properties group, boolean burning) {
		super(group);
		this.burning = burning;
	}

	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!worldIn.isClientSide)
			for (int i = 0; i < 5; i++) {
				NetherFukumameEntity fukumamentity = new NetherFukumameEntity(worldIn, playerIn);
				float d0 = worldIn.random.nextFloat() * 20.0F - 10.0F;
				if (this.burning)
					fukumamentity.setRemainingFireTicks(200);
				fukumamentity.shootFromRotation(playerIn, playerIn.xRot + d0 * 0.25F, playerIn.yRot + d0, 0.0F, 1.5F, 0.8F);
				worldIn.addFreshEntity(fukumamentity);
			}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(itemstack.getItem(), 10);
		if (!playerIn.level.isClientSide)
			itemstack.hurtAndBreak(1, (LivingEntity) playerIn, playerEntity -> playerEntity.broadcastBreakEvent(handIn));
		return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
	}
}

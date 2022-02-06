package baguchan.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class MisoBarrelBlock extends WorkedBarrelBaseBlock {

	public final Item finishedBottleItem;

	public MisoBarrelBlock(Item finishedBottleItem, Properties properties) {
		super(properties);
		this.finishedBottleItem = finishedBottleItem;
	}

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		Stat stat = getStat(state);
		int time = state.getValue(TIME);

		if (time < 5) {
			state.setValue(TIME, time + 1);
		}

		if (time >= 5 && stat == Stat.USING) {
			state.setValue(STAT, Stat.FLUID);
		}
	}

	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack itemHeld = player.getItemInHand(handIn);
		Stat stat = getStat(state);

		if (stat == Stat.FLUID && itemHeld != null && itemHeld.getItem() == Items.GLASS_BOTTLE) {
			ItemStack nigari = new ItemStack(finishedBottleItem);
			worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			if (itemHeld.getCount() == 1) {
				player.setItemInHand(handIn, nigari);
			} else {
				if (!player.getInventory().add(nigari))
					worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, nigari));
				itemHeld.shrink(1);
			}
			worldIn.setBlock(pos, state.setValue(STAT, Stat.USED), 3);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}

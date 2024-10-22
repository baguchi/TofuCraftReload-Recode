package baguchi.tofucraft.block.utils;

import baguchi.tofucraft.registry.TofuParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class MisoBarrelBlock extends WeightBaseBlock {
	public final Supplier<Item> finishedBottleItem;
	public static final IntegerProperty FLUIDS = IntegerProperty.create("fluids", 0, 6);


	public MisoBarrelBlock(Supplier<Item> finishedBottleItem, Properties properties) {
		super(properties);
		this.finishedBottleItem = finishedBottleItem;
		registerDefaultState(this.stateDefinition.any().setValue(STAT, Stat.USING).setValue(TIME, 0).setValue(FLUIDS, 0));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
		if (isUnderWeight(worldIn, pos)) {
			if (rand.nextInt(3) == 0) {
				double d4 = rand.nextBoolean() ? 0.8D : -0.8D;
				double d0 = pos.getX() + 0.5D + rand.nextFloat() * d4;
				double d1 = (pos.getY() + rand.nextFloat());
				double d2 = pos.getZ() + 0.5D + rand.nextFloat() * d4;
				ParticleOptions particle = TofuParticleTypes.DRIP_SOYSAUCE_HANG.get();

				worldIn.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		Stat stat = getStat(state);
		int time = state.getValue(TIME);

		if (isUnderWeight(worldIn, pos)) {
			if (time < 5 && random.nextInt(4) == 0) {
				worldIn.setBlock(pos, state.setValue(TIME, time + 1), 3);
			}

			if (time >= 5 && stat == Stat.USING) {
				worldIn.setBlock(pos, state.setValue(STAT, Stat.USED).setValue(FLUIDS, 6), 3);
			}
		}
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemHeld, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult p_316140_) {
		Stat stat = getStat(state);
		int fluidsAmounts = state.getValue(FLUIDS);

		if (stat == Stat.USED && fluidsAmounts > 0 && itemHeld != null && itemHeld.getItem() == Items.GLASS_BOTTLE) {
			ItemStack nigari = new ItemStack(finishedBottleItem.get());
			worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			if (itemHeld.getCount() == 1) {
				player.setItemInHand(handIn, nigari);
			} else {
				if (!player.getInventory().add(nigari))
					worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, nigari));
				itemHeld.shrink(1);
			}
			worldIn.setBlock(pos, state.setValue(STAT, Stat.USED).setValue(FLUIDS, fluidsAmounts - 1), 3);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAT, TIME, FLUIDS);
	}
}

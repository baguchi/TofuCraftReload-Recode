package baguchan.tofucraft.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class SpecialBitternItem extends Item {
	public final Supplier<? extends Fluid> fluidSupplier;
	public final Supplier<? extends Block> blockSupplier;

	public SpecialBitternItem(Supplier<? extends Fluid> fluidSupplier, Supplier<? extends Block> blockSupplier, Properties group) {
		super(group);
		this.fluidSupplier = fluidSupplier;
		this.blockSupplier = blockSupplier;
	}

	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		BlockHitResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos());

		if (worldIn instanceof ServerLevel serverLevel) {
			if (blockraytraceresult.getType() == HitResult.Type.MISS)
				return InteractionResult.PASS;
			if (blockraytraceresult.getType() == HitResult.Type.BLOCK) {
				FluidState fluidState = worldIn.getFluidState(blockraytraceresult1.getBlockPos());
				if (fluidState.is(this.fluidSupplier.get())) {
					worldIn.setBlock(blockraytraceresult1.getBlockPos(), this.blockSupplier.get().defaultBlockState(), 11);
					worldIn.levelEvent(2001, blockraytraceresult1.getBlockPos(), Block.getId(worldIn.getBlockState(blockraytraceresult1.getBlockPos())));
					worldIn.playSound(null, blockraytraceresult1.getBlockPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1F, 1F);
					if (!playerIn.isCreative()) {
						itemstack.shrink(1);
					}
					ItemStack itemstack2 = new ItemStack(Items.GLASS_BOTTLE);
					if (itemstack.isEmpty()) {
						playerIn.setItemInHand(handIn, itemstack2);
					} else if (!playerIn.isCreative() &&
							!playerIn.getInventory().add(itemstack2)) {
						playerIn.drop(itemstack2, false);
					}
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;

	}
}

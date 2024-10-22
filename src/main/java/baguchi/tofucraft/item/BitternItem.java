package baguchi.tofucraft.item;

import baguchi.tofucraft.api.IFluidBottle;
import baguchi.tofucraft.capability.wrapper.FluidBottleWrapper;
import baguchi.tofucraft.utils.ContainerUtils;
import baguchi.tofucraft.utils.RecipeHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;

public class BitternItem extends Item implements IFluidBottle {

	protected final Holder<Fluid> fluidHolder;

	public BitternItem(Holder<Fluid> fluidHolder, Properties group) {
		super(group);
		this.fluidHolder = fluidHolder;
	}

	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		BlockHitResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos());

		IFluidHandlerItem handler = FluidUtil.getFluidHandler(itemstack.copyWithCount(1)).orElse(null);
		if (worldIn instanceof ServerLevel serverLevel && handler instanceof FluidBottleWrapper fluidBottleWrapper) {
			if (blockraytraceresult.getType() == HitResult.Type.MISS)
				return InteractionResult.PASS;
			if (blockraytraceresult.getType() == HitResult.Type.BLOCK) {
				FluidState fluidState = worldIn.getFluidState(blockraytraceresult1.getBlockPos());

				ItemStack result = RecipeHelper.getBitternResult(serverLevel, fluidState.getType(), fluidBottleWrapper.getFluid());
				if (result != null) {
					if (playerIn instanceof ServerPlayer serverPlayer) {
						CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockraytraceresult1.getBlockPos(), itemstack);
					}

					worldIn.setBlock(blockraytraceresult1.getBlockPos(), Block.byItem(result.getItem()).defaultBlockState(), 11);
					worldIn.levelEvent(2001, blockraytraceresult1.getBlockPos(), Block.getId(worldIn.getBlockState(blockraytraceresult1.getBlockPos())));
					worldIn.playSound(null, blockraytraceresult1.getBlockPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1F, 1F);
					ContainerUtils.addWithContainer(playerIn, handIn, itemstack, new ItemStack(Items.GLASS_BOTTLE), false);
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;

	}

	@Override
	public Holder<Fluid> getFluid() {
		return this.fluidHolder;
	}
}

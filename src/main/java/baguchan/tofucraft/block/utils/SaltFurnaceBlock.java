package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class SaltFurnaceBlock extends ContainerBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public SaltFurnaceBlock(Properties p_i50000_1_) {
		super(p_i50000_1_);
		registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(false)));
	}

	public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
		boolean flag = false;
		ItemStack stack = p_225533_4_.getItemInHand(p_225533_5_);
		TileEntity tileentity = p_225533_2_.getBlockEntity(p_225533_3_);
		if (tileentity instanceof SaltFurnaceTileEntity) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(stack, 1)).orElse(null);
			if (handler != null && handler instanceof net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper) {
				FluidUtil.interactWithFluidHandler(p_225533_4_, p_225533_5_, tileentity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null));
				flag = true;
			}
		}
		if (!flag)
			if (p_225533_2_.isClientSide()) {
				TofuCraftReload.PROXY.setRefrencedTE(p_225533_2_.getBlockEntity(p_225533_3_));
			} else {
				INamedContainerProvider inamedcontainerprovider = getMenuProvider(p_225533_1_, p_225533_2_, p_225533_3_);
				if (inamedcontainerprovider != null)
					p_225533_4_.openMenu(inamedcontainerprovider);
			}
		return ActionResultType.SUCCESS;
	}

	public void setPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, LivingEntity p_180633_4_, ItemStack p_180633_5_) {
		if (p_180633_5_.hasCustomHoverName()) {
			TileEntity tileentity = p_180633_1_.getBlockEntity(p_180633_2_);
			if (tileentity instanceof AbstractFurnaceTileEntity) {
				((AbstractFurnaceTileEntity) tileentity).setCustomName(p_180633_5_.getHoverName());
			}
		}

	}

	public void onRemove(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
		if (!p_196243_1_.is(p_196243_4_.getBlock())) {
			TileEntity tileentity = p_196243_2_.getBlockEntity(p_196243_3_);
			if (tileentity instanceof AbstractFurnaceTileEntity) {
				InventoryHelper.dropContents(p_196243_2_, p_196243_3_, (AbstractFurnaceTileEntity) tileentity);
				((AbstractFurnaceTileEntity) tileentity).getRecipesToAwardAndPopExperience(p_196243_2_, Vector3d.atCenterOf(p_196243_3_));
				p_196243_2_.updateNeighbourForOutputSignal(p_196243_3_, this);
			}

			super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
		}
	}

	public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
		return true;
	}

	public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
		return Container.getRedstoneSignalFromBlockEntity(p_180641_2_.getBlockEntity(p_180641_3_));
	}

	public BlockRenderType getRenderShape(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.add(LIT);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
		if (((Boolean) p_180655_1_.getValue((Property) LIT)).booleanValue()) {
			double d0 = p_180655_3_.getX() + 0.5D;
			double d1 = p_180655_3_.getY() + 0.1D;
			double d2 = p_180655_3_.getZ() + 0.5D;
			if (p_180655_4_.nextDouble() < 0.1D)
				p_180655_2_.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			for (Direction direction : Direction.values()) {
				Direction.Axis direction$axis = direction.getAxis();
				double d3 = 0.52D;
				double d4 = p_180655_4_.nextDouble() * 0.6D - 0.3D;
				double d5 = (direction$axis == Direction.Axis.X) ? (direction.getStepX() * 0.52D) : d4;
				double d6 = p_180655_4_.nextDouble() * 6.0D / 16.0D;
				double d7 = (direction$axis == Direction.Axis.Z) ? (direction.getStepZ() * 0.52D) : d4;
				p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
				p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
		return new SaltFurnaceTileEntity();
	}
}

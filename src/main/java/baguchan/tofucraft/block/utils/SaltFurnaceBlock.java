package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
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
	public static final BooleanProperty LIT = BlockStateProperties.field_208190_q;

	public SaltFurnaceBlock(Properties p_i50000_1_) {
		super(p_i50000_1_);
		func_180632_j((BlockState) ((BlockState) this.field_176227_L.func_177621_b()).setValue(LIT, Boolean.valueOf(false)));
	}

	public ActionResultType func_225533_a_(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
		boolean flag = false;
		ItemStack stack = p_225533_4_.getItemInHand(p_225533_5_);
		TileEntity tileentity = p_225533_2_.func_175625_s(p_225533_3_);
		if (tileentity instanceof SaltFurnaceTileEntity) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(stack, 1)).orElse(null);
			if (handler != null && handler instanceof net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper) {
				FluidUtil.interactWithFluidHandler(p_225533_4_, p_225533_5_, tileentity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null));
				flag = true;
			}
		}
		if (!flag)
			if (p_225533_2_.isClientSide()) {
				TofuCraftReload.PROXY.setRefrencedTE(p_225533_2_.func_175625_s(p_225533_3_));
			} else {
				INamedContainerProvider inamedcontainerprovider = func_220052_b(p_225533_1_, p_225533_2_, p_225533_3_);
				if (inamedcontainerprovider != null)
					p_225533_4_.func_213829_a(inamedcontainerprovider);
			}
		return ActionResultType.SUCCESS;
	}

	public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, LivingEntity p_180633_4_, ItemStack p_180633_5_) {
		if (p_180633_5_.func_82837_s()) {
			TileEntity tileentity = p_180633_1_.func_175625_s(p_180633_2_);
			if (tileentity instanceof SaltFurnaceTileEntity)
				((SaltFurnaceTileEntity) tileentity).func_213903_a(p_180633_5_.func_200301_q());
		}
	}

	public void func_196243_a(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
		if (!p_196243_1_.is(p_196243_4_.getBlock())) {
			TileEntity tileentity = p_196243_2_.func_175625_s(p_196243_3_);
			if (tileentity instanceof SaltFurnaceTileEntity) {
				InventoryHelper.func_180175_a(p_196243_2_, p_196243_3_, (IInventory) tileentity);
				p_196243_2_.func_175666_e(p_196243_3_, (Block) this);
			}
			super.func_196243_a(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
		}
	}

	public boolean func_149740_M(BlockState p_149740_1_) {
		return true;
	}

	public int func_180641_l(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
		return Container.func_178144_a(p_180641_2_.func_175625_s(p_180641_3_));
	}

	public BlockRenderType func_149645_b(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}

	protected void func_206840_a(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.func_206894_a(new Property[]{LIT});
	}

	@OnlyIn(Dist.CLIENT)
	public void func_180655_c(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
		if (((Boolean) p_180655_1_.func_177229_b((Property) LIT)).booleanValue()) {
			double d0 = p_180655_3_.getX() + 0.5D;
			double d1 = p_180655_3_.getY() + 0.1D;
			double d2 = p_180655_3_.getZ() + 0.5D;
			if (p_180655_4_.nextDouble() < 0.1D)
				p_180655_2_.func_184134_a(d0, d1, d2, SoundEvents.field_187652_bv, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			for (Direction direction : Direction.values()) {
				Direction.Axis direction$axis = direction.func_176740_k();
				double d3 = 0.52D;
				double d4 = p_180655_4_.nextDouble() * 0.6D - 0.3D;
				double d5 = (direction$axis == Direction.Axis.X) ? (direction.func_82601_c() * 0.52D) : d4;
				double d6 = p_180655_4_.nextDouble() * 6.0D / 16.0D;
				double d7 = (direction$axis == Direction.Axis.Z) ? (direction.func_82599_e() * 0.52D) : d4;
				p_180655_2_.func_195594_a((IParticleData) ParticleTypes.field_197601_L, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
				p_180655_2_.func_195594_a((IParticleData) ParticleTypes.field_197631_x, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public TileEntity func_196283_a_(IBlockReader p_196283_1_) {
		return new SaltFurnaceTileEntity();
	}
}

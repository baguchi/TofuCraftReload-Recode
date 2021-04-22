package baguchan.tofucraft.fluid;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.ITag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;

import java.util.Random;

public abstract class SoyMilkSoulFluid extends WaterFluid {
	public Fluid func_210197_e() {
		return TofuFluids.SOYMILK_SOUL_FLOW;
	}

	public Fluid func_210198_f() {
		return TofuFluids.SOYMILK_SOUL;
	}

	public Item func_204524_b() {
		return TofuItems.BUCKET_SOYMILK_SOUL;
	}

	@OnlyIn(Dist.CLIENT)
	public void func_204522_a(World worldIn, BlockPos pos, FluidState state, Random random) {
	}

	protected boolean func_205579_d() {
		return false;
	}

	protected void func_205580_a(IWorld p_205580_1_, BlockPos p_205580_2_, BlockState p_205580_3_) {
		TileEntity tileentity = p_205580_3_.hasTileEntity() ? p_205580_1_.func_175625_s(p_205580_2_) : null;
		Block.func_220059_a(p_205580_3_, p_205580_1_, p_205580_2_, tileentity);
	}

	public int func_185698_b(IWorldReader p_185698_1_) {
		return 4;
	}

	public BlockState func_204527_a(FluidState p_204527_1_) {
		return (BlockState) TofuBlocks.SOYMILK_SOUL.defaultBlockState().setValue((Property) FlowingFluidBlock.field_176367_b, Integer.valueOf(func_207205_e(p_204527_1_)));
	}

	public boolean func_207187_a(Fluid p_207187_1_) {
		return (p_207187_1_ == TofuFluids.SOYMILK_SOUL || p_207187_1_ == TofuFluids.SOYMILK_SOUL_FLOW);
	}

	public int func_204528_b(IWorldReader p_204528_1_) {
		return 1;
	}

	public int func_205569_a(IWorldReader p_205569_1_) {
		return 5;
	}

	public boolean func_215665_a(FluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
		return (p_215665_5_ == Direction.DOWN && !p_215665_4_.func_207185_a((ITag) TofuTags.Fluids.SOYMILK));
	}

	protected float func_210195_d() {
		return 100.0F;
	}

	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder(new ResourceLocation("tofucraft", "block/soymilk_soul"), new ResourceLocation("tofucraft", "block/soymilk_soul_flow"))

				.overlay(new ResourceLocation("tofucraft", "block/soymilk_soul_overlay")).build(this);
	}

	public static class Flowing extends SoyMilkSoulFluid {
		protected void func_207184_a(StateContainer.Builder<Fluid, FluidState> p_207184_1_) {
			super.func_207184_a(p_207184_1_);
			p_207184_1_.func_206894_a(new Property[]{(Property) field_207210_b});
		}

		public int func_207192_d(FluidState p_207192_1_) {
			return ((Integer) p_207192_1_.func_177229_b((Property) field_207210_b)).intValue();
		}

		public boolean func_207193_c(FluidState p_207193_1_) {
			return false;
		}
	}

	public static class Source extends SoyMilkSoulFluid {
		public int func_207192_d(FluidState p_207192_1_) {
			return 8;
		}

		public boolean func_207193_c(FluidState p_207193_1_) {
			return true;
		}
	}
}

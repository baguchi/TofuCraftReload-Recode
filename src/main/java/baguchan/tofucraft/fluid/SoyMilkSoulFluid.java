package baguchan.tofucraft.fluid;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
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
	public Fluid getFlowing() {
		return TofuFluids.SOYMILK_SOUL_FLOW;
	}

	public Fluid getSource() {
		return TofuFluids.SOYMILK_SOUL;
	}

	public Item getBucket() {
		return TofuItems.BUCKET_SOYMILK_SOUL;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(World p_204522_1_, BlockPos p_204522_2_, FluidState p_204522_3_, Random p_204522_4_) {
	}

	protected boolean canConvertToSource() {
		return true;
	}

	protected void beforeDestroyingBlock(IWorld p_205580_1_, BlockPos p_205580_2_, BlockState p_205580_3_) {
		TileEntity tileentity = p_205580_3_.hasTileEntity() ? p_205580_1_.getBlockEntity(p_205580_2_) : null;
		Block.dropResources(p_205580_3_, p_205580_1_, p_205580_2_, tileentity);
	}

	public int getSlopeFindDistance(IWorldReader p_185698_1_) {
		return 4;
	}

	public BlockState createLegacyBlock(FluidState p_204527_1_) {
		return TofuBlocks.SOYMILK_SOUL.defaultBlockState().setValue(FlowingFluidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_204527_1_)));
	}

	public boolean isSame(Fluid p_207187_1_) {
		return p_207187_1_ == TofuFluids.SOYMILK_SOUL || p_207187_1_ == TofuFluids.SOYMILK_SOUL_FLOW;
	}

	public int getDropOff(IWorldReader p_204528_1_) {
		return 1;
	}

	public int getTickDelay(IWorldReader p_205569_1_) {
		return 5;
	}

	public boolean canBeReplacedWith(FluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
		return p_215665_5_ == Direction.DOWN && !p_215665_4_.is(FluidTags.WATER);
	}

	protected float getExplosionResistance() {
		return 100.0F;
	}

	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder(new ResourceLocation(TofuCraftReload.MODID, "block/soymilk_soul"), new ResourceLocation(TofuCraftReload.MODID, "block/soymilk_soul_flow"))

				.build(this);
	}

	public static class Flowing extends SoyMilkSoulFluid {
		protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> p_207184_1_) {
			super.createFluidStateDefinition(p_207184_1_);
			p_207184_1_.add(LEVEL);
		}

		public int getAmount(FluidState p_207192_1_) {
			return p_207192_1_.getValue(LEVEL);
		}

		public boolean isSource(FluidState p_207193_1_) {
			return false;
		}
	}

	public static class Source extends SoyMilkSoulFluid {
		public int getAmount(FluidState p_207192_1_) {
			return 8;
		}

		public boolean isSource(FluidState p_207193_1_) {
			return true;
		}
	}
}

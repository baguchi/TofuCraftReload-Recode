package baguchan.tofucraft.fluid;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
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
	public void animateTick(Level p_204522_1_, BlockPos p_204522_2_, FluidState p_204522_3_, Random p_204522_4_) {
		if (!p_204522_3_.isSource() && !p_204522_3_.getValue(FALLING)) {
			if (p_204522_4_.nextInt(64) == 0) {
				p_204522_1_.playLocalSound((double) p_204522_2_.getX() + 0.5D, (double) p_204522_2_.getY() + 0.5D, (double) p_204522_2_.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, p_204522_4_.nextFloat() * 0.25F + 0.75F, p_204522_4_.nextFloat() + 0.5F, false);
			}
		}
	}

	protected boolean canConvertToSource() {
		return false;
	}


	public BlockState createLegacyBlock(FluidState p_204527_1_) {
		return TofuBlocks.SOYMILK_SOUL.defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_204527_1_)));
	}

	public boolean isSame(Fluid p_207187_1_) {
		return p_207187_1_ == TofuFluids.SOYMILK_SOUL || p_207187_1_ == TofuFluids.SOYMILK_SOUL_FLOW;
	}

	protected float getExplosionResistance() {
		return 100.0F;
	}

	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder(new ResourceLocation(TofuCraftReload.MODID, "block/soymilk_soul"), new ResourceLocation(TofuCraftReload.MODID, "block/soymilk_soul_flow"))

				.build(this);
	}

	public static class Flowing extends SoyMilkSoulFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> p_207184_1_) {
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

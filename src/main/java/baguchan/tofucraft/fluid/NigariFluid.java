package baguchan.tofucraft.fluid;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidType;

public abstract class NigariFluid extends WaterFluid {
	@Override
	public Fluid getFlowing() {
		return TofuFluids.BITTERN_FLOW.get();
	}

	@Override
	public Fluid getSource() {
		return TofuFluids.BITTERN.get();
	}

	@Override
	public Item getBucket() {
		return TofuItems.BUCKET_BITTERN.get();
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(Level p_204522_1_, BlockPos p_204522_2_, FluidState p_204522_3_, RandomSource p_204522_4_) {
		if (!p_204522_3_.isSource() && !p_204522_3_.getValue(FALLING)) {
			if (p_204522_4_.nextInt(64) == 0) {
				p_204522_1_.playLocalSound((double) p_204522_2_.getX() + 0.5D, (double) p_204522_2_.getY() + 0.5D, (double) p_204522_2_.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, p_204522_4_.nextFloat() * 0.25F + 0.75F, p_204522_4_.nextFloat() + 0.5F, false);
			}
		}
	}

	@Override
	public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
		return false;
	}

	@Override
	public BlockState createLegacyBlock(FluidState p_204527_1_) {
		return TofuBlocks.BITTERN.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_204527_1_)));
	}

	@Override
	public boolean isSame(Fluid p_207187_1_) {
		return p_207187_1_ == TofuFluids.BITTERN.get() || p_207187_1_ == TofuFluids.BITTERN_FLOW.get();
	}

	@Override
	public boolean canBeReplacedWith(FluidState p_76233_, BlockGetter p_76234_, BlockPos p_76235_, Fluid p_76236_, Direction p_76237_) {
		return !this.isSame(p_76236_);
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0F;
	}

	@Override
	public FluidType getFluidType() {
		return TofuFluidTypes.BITTERN.get();
	}

	public static class Flowing extends NigariFluid {
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

	public static class Source extends NigariFluid {
		public int getAmount(FluidState p_207192_1_) {
			return 8;
		}

		public boolean isSource(FluidState p_207193_1_) {
			return true;
		}
	}
}

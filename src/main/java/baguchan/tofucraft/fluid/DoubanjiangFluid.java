package baguchan.tofucraft.fluid;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidType;

public abstract class DoubanjiangFluid extends WaterFluid {
	@Override
	public Fluid getFlowing() {
		return TofuFluids.DOUBANJIANG_FLOW.get();
	}

	@Override
	public Fluid getSource() {
		return TofuFluids.DOUBANJIANG.get();
	}

	@Override
	public Item getBucket() {
		return TofuItems.BUCKET_DOUBANJIANG.get();
	}


	@OnlyIn(Dist.CLIENT)
	public void animateTick(Level level, BlockPos blockPos, FluidState p_204522_3_, RandomSource randomSource) {
		if (!p_204522_3_.isSource() && !p_204522_3_.getValue(FALLING)) {
			if (randomSource.nextInt(64) == 0) {
				level.addParticle(ParticleTypes.BUBBLE_POP, blockPos.getX() + randomSource.nextFloat(), (double) blockPos.getY() + 0.95F + randomSource.nextFloat() * 0.1F, (double) blockPos.getZ() + randomSource.nextFloat(), 0, 0, 0);
				level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, SoundEvents.LAVA_POP, SoundSource.BLOCKS, randomSource.nextFloat() * 0.25F + 0.75F, randomSource.nextFloat() * 0.25F + 1.25F, false);
			}
		}
	}

	@Override
	public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
		return false;
	}

	@Override
	public BlockState createLegacyBlock(FluidState p_204527_1_) {
		return TofuBlocks.DOUBANJIANG.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_204527_1_)));
	}

	@Override
	public boolean isSame(Fluid p_207187_1_) {
		return p_207187_1_ == TofuFluids.DOUBANJIANG.get() || p_207187_1_ == TofuFluids.DOUBANJIANG_FLOW.get();
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
	public int getSlopeFindDistance(LevelReader level) {
		return 2;
	}

	@Override
	public int getDropOff(LevelReader level) {
		return 2;
	}

	@Override
	public FluidType getFluidType() {
		return TofuFluidTypes.DOUBANJIANG.get();
	}

	public static class Flowing extends DoubanjiangFluid {
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

	public static class Source extends DoubanjiangFluid {
		public int getAmount(FluidState p_207192_1_) {
			return 8;
		}

		public boolean isSource(FluidState p_207193_1_) {
			return true;
		}
	}
}

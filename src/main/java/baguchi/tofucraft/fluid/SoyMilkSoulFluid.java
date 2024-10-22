package baguchi.tofucraft.fluid;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidType;

public abstract class SoyMilkSoulFluid extends WaterFluid {
	@Override
	public Fluid getFlowing() {
		return TofuFluids.SOYMILK_SOUL_FLOW.get();
	}

	@Override
	public Fluid getSource() {
		return TofuFluids.SOYMILK_SOUL.get();
	}

	@Override
	public Item getBucket() {
		return TofuItems.BUCKET_SOYMILK_SOUL.get();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
		if (!fluidState.isSource() && !fluidState.getValue(FALLING)) {
			if (randomSource.nextInt(64) == 0) {
				level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, randomSource.nextFloat() * 0.25F + 0.75F, randomSource.nextFloat() + 0.5F, false);
			}
		}
		if (fluidState.isSource()) {
			if (randomSource.nextInt(5) == 0) {
				if (level.getBlockState(blockPos.below()).is(Blocks.MAGMA_BLOCK)) {
					level.addParticle(ParticleTypes.BUBBLE_POP, blockPos.getX() + randomSource.nextFloat(), (double) blockPos.getY() + 0.95F + randomSource.nextFloat() * 0.1F, (double) blockPos.getZ() + randomSource.nextFloat(), 0, 0, 0);
					level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, randomSource.nextFloat() * 0.25F + 0.75F, randomSource.nextFloat() + 0.5F, false);

					if (randomSource.nextInt(2) == 0) {
						level.addParticle(ParticleTypes.SOUL, blockPos.getX() + randomSource.nextFloat(), (double) blockPos.getY() + 0.95F + randomSource.nextFloat() * 0.1F, (double) blockPos.getZ() + randomSource.nextFloat(), 0, 0.05F, 0);
						level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, TofuSounds.SOUL_BREATH.get(), SoundSource.BLOCKS, randomSource.nextFloat() * 0.25F + 0.75F, randomSource.nextFloat() * 0.25F + 0.75F, false);
					}
				}
			}
		}
	}

	@Override
	public boolean canBeReplacedWith(FluidState p_76233_, BlockGetter p_76234_, BlockPos p_76235_, Fluid p_76236_, Direction p_76237_) {
		return !this.isSame(p_76236_);
	}

	@Override
	protected boolean canConvertToSource(ServerLevel p_376722_) {
		return false;
	}
	@Override
	public BlockState createLegacyBlock(FluidState p_204527_1_) {
		return TofuBlocks.SOYMILK_SOUL.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_204527_1_)));
	}

	@Override
	public boolean isSame(Fluid p_207187_1_) {
		return p_207187_1_ == TofuFluids.SOYMILK_SOUL.get() || p_207187_1_ == TofuFluids.SOYMILK_SOUL_FLOW.get();
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0F;
	}

	@Override
	public FluidType getFluidType() {
		return TofuFluidTypes.SOYMILK_SOUL.get();
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

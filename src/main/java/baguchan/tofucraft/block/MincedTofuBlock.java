package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class MincedTofuBlock extends FallingBlock {
	public MincedTofuBlock(Properties properties) {
		super(properties);
	}

	public void entityInside(BlockState p_154263_, Level p_154264_, BlockPos p_154265_, Entity p_154266_) {
		if (!(p_154266_ instanceof LivingEntity) || p_154266_.getFeetBlockState().is(this)) {
			p_154266_.makeStuckInBlock(p_154263_, new Vec3((double) 0.9F, 1.25D, (double) 0.9F));
			if (p_154264_.isClientSide) {
				RandomSource randomsource = p_154264_.getRandom();
				boolean flag = p_154266_.xOld != p_154266_.getX() || p_154266_.zOld != p_154266_.getZ();
				if (flag && randomsource.nextBoolean()) {
					p_154264_.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, p_154263_), p_154266_.getX(), (double) (p_154265_.getY() + 1), p_154266_.getZ(), (double) (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double) 0.05F, (double) (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
				}
			}
		}
	}

	@Override
	public void fallOn(Level p_152426_, BlockState p_152427_, BlockPos p_152428_, Entity p_152429_, float p_152430_) {
		super.fallOn(p_152426_, p_152427_, p_152428_, p_152429_, p_152430_ * 0.5F);
	}

	@Override
	public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return TofuEntityTypes.MINCED_TOFU_PATH;
	}

	@Override
	public @Nullable BlockPathTypes getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, BlockPathTypes originalType) {
		return TofuEntityTypes.MINCED_TOFU_PATH;
	}
}
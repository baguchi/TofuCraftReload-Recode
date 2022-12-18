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
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.PowderSnowBlock.canEntityWalkOnPowderSnow;

public class MincedTofuBlock extends FallingBlock {
	private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double) 0.9F, 1.0D);

	public MincedTofuBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState p_154285_, BlockGetter p_154286_, BlockPos p_154287_, CollisionContext p_154288_) {
		if (p_154288_ instanceof EntityCollisionContext entitycollisioncontext) {
			Entity entity = entitycollisioncontext.getEntity();
			if (entity != null) {
				if (entity.fallDistance > 2.5F) {
					return FALLING_COLLISION_SHAPE;
				}

				boolean flag = entity instanceof FallingBlockEntity;
				if (flag || canEntityWalkOnPowderSnow(entity) && p_154288_.isAbove(Shapes.block(), p_154287_, false) && !p_154288_.isDescending()) {
					return super.getCollisionShape(p_154285_, p_154286_, p_154287_, p_154288_);
				}
			}
		}

		return Shapes.empty();
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
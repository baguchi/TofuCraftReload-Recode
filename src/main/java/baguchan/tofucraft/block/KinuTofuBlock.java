package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class KinuTofuBlock extends Block {
	public KinuTofuBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
		if (isUnderWeight(worldIn, pos) &&
				rand.nextInt(4) == 0) {
			double d4 = rand.nextBoolean() ? 0.8D : -0.8D;
			double d0 = pos.getX() + 0.5D + rand.nextFloat() * d4;
			double d1 = (pos.getY() + rand.nextFloat());
			double d2 = pos.getZ() + 0.5D + rand.nextFloat() * d4;
			worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void fallOn(Level p_152426_, BlockState p_152427_, BlockPos p_152428_, Entity p_152429_, float p_152430_) {
		super.fallOn(p_152426_, p_152427_, p_152428_, p_152429_, p_152430_ * 0.25F);
		if (p_152430_ > 0.5F && !p_152429_.getType().is(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
			p_152426_.destroyBlock(p_152428_, true);
		}
	}

	@Override
	public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, RandomSource p_225542_4_) {
		super.randomTick(p_225542_1_, p_225542_2_, p_225542_3_, p_225542_4_);

		if (isUnderWeight(p_225542_2_, p_225542_3_))
			p_225542_2_.destroyBlock(p_225542_3_, true);
	}

	public boolean isUnderWeight(Level world, BlockPos pos) {
		BlockState weightBlock = world.getBlockState(pos.above());
		BlockState baseBlock = world.getBlockState(pos.below());
		float weightHardness = weightBlock.getDestroySpeed(world, pos.above());
		boolean isWeightValid = (weightBlock.isFaceSturdy(world, pos, Direction.DOWN) && (weightHardness >= 1.0F || weightHardness < 0.0F)) && !(weightBlock.getBlock() instanceof TofuBlock);
		float baseHardness = baseBlock.getDestroySpeed(world, pos.below());
		boolean isBaseValid = (baseBlock.isFaceSturdy(world, pos, Direction.UP) && (baseHardness >= 1.0F || baseHardness < 0.0F)) && !(baseBlock.getBlock() instanceof TofuBlock);
		return (isWeightValid && isBaseValid);
	}


	@Override
	public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return TofuEntityTypes.UNSTABLE_TOFU_PATH;
	}

	@Override
	public @Nullable BlockPathTypes getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, BlockPathTypes originalType) {
		return TofuEntityTypes.UNSTABLE_TOFU_PATH;
	}
}

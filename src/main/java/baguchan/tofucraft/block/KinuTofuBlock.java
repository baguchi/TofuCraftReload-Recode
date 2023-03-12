package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class KinuTofuBlock extends Block {
	public KinuTofuBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
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
		p_152426_.destroyBlock(p_152428_, true);
	}

	@Override
	public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
		super.randomTick(p_225542_1_, p_225542_2_, p_225542_3_, p_225542_4_);

		if (isUnderWeight(p_225542_2_, p_225542_3_))
			p_225542_2_.destroyBlock(p_225542_3_, true);
	}

	public boolean isUnderWeight(Level world, BlockPos pos) {
		BlockState weightBlock = world.getBlockState(pos.above());
		BlockState baseBlock = world.getBlockState(pos.below());
		boolean isWeightValid = (weightBlock != null && (weightBlock.getMaterial() == Material.STONE || weightBlock.getMaterial() == Material.METAL || weightBlock.getMaterial() == Material.HEAVY_METAL));
		float baseHardness = baseBlock.getDestroySpeed(world, pos.below());
		boolean isBaseValid = (baseBlock.isCollisionShapeFullBlock(world, pos) && (baseBlock.getMaterial() == Material.STONE || baseBlock.getMaterial() == Material.METAL || baseHardness >= 1.0F || baseHardness < 0.0F));
		return (isWeightValid && isBaseValid);
	}
}

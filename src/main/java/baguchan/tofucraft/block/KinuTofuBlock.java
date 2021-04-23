package baguchan.tofucraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class KinuTofuBlock extends Block {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

	public KinuTofuBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
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

	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		super.fallOn(worldIn, pos, entityIn, fallDistance * 0.75F);
		worldIn.destroyBlock(pos, true);
	}

	@Override
	public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
		super.randomTick(p_225542_1_, p_225542_2_, p_225542_3_, p_225542_4_);

		if (isUnderWeight(p_225542_2_, p_225542_3_))
			p_225542_2_.destroyBlock(p_225542_3_, true);
	}

	public boolean isUnderWeight(World world, BlockPos pos) {
		BlockState weightBlock = world.getBlockState(pos.above());
		BlockState baseBlock = world.getBlockState(pos.below());
		boolean isWeightValid = (weightBlock != null && (weightBlock.getMaterial() == Material.STONE || weightBlock.getMaterial() == Material.METAL || weightBlock.getMaterial() == Material.HEAVY_METAL));
		float baseHardness = baseBlock.getDestroySpeed(world, pos.below());
		boolean isBaseValid = (baseBlock.isCollisionShapeFullBlock(world, pos) && (baseBlock.getMaterial() == Material.STONE || baseBlock.getMaterial() == Material.METAL || baseHardness >= 1.0F || baseHardness < 0.0F));
		return (isWeightValid && isBaseValid);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}
}

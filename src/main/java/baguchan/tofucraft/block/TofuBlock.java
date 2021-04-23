package baguchan.tofucraft.block;

import baguchan.tofucraft.api.HardenRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;
import java.util.Random;

public class TofuBlock extends Block {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

	public TofuBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
		if (isUnderWeight(worldIn, pos) &&
				rand.nextInt(5) == 0) {
			double d4 = rand.nextBoolean() ? 0.8D : -0.8D;
			double d0 = pos.getX() + 0.5D + rand.nextFloat() * d4;
			double d1 = (pos.getY() + rand.nextFloat());
			double d2 = pos.getZ() + 0.5D + rand.nextFloat() * d4;
			worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.randomTick(state, worldIn, pos, random);
		if (isUnderWeight(worldIn, pos)) {
			int i = ((Integer) state.getValue((Property) AGE)).intValue();
			if (random.nextInt(5) == 0)
				if (i < 7) {
					worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
				} else {
					Map.Entry<Block, Block> result = HardenRecipes.getResult(state.getBlock());
					if (result != null)
						worldIn.setBlock(pos, result.getValue().defaultBlockState(), 2);
				}
		}
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

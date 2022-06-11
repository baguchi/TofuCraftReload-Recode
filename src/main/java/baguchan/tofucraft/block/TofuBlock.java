package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.utils.RecipeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TofuBlock extends Block {
	public static final IntegerProperty HARDNESS = IntegerProperty.create("hardness", 0, 7);

	public TofuBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
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

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		super.randomTick(state, worldIn, pos, random);
		if (isDriedCondition(worldIn, pos)) {
			if (random.nextInt(8) == 0) {
				if (this == TofuBlocks.MOMENTOFU.get()) {
					worldIn.setBlock(pos, TofuBlocks.DRIEDTOFU.get().defaultBlockState(), 2);
				}
			}
		} else if (isUnderWeight(worldIn, pos)) {
			int i = ((Integer) state.getValue((Property) HARDNESS)).intValue();
			if (random.nextInt(4) == 0)
				if (i < 7) {
					worldIn.setBlock(pos, state.setValue(HARDNESS, Integer.valueOf(i + 1)), 2);
				} else {
					ItemStack result = RecipeHelper.getTofu(state.getBlock());
					if (result != null)
						worldIn.setBlock(pos, Block.byItem(result.getItem()).defaultBlockState(), 2);
				}
		}
	}

	public boolean isUnderWeight(Level world, BlockPos pos) {
		BlockState weightBlock = world.getBlockState(pos.above());
		BlockState baseBlock = world.getBlockState(pos.below());
		boolean isWeightValid = (this != weightBlock.getBlock() && weightBlock != null && (weightBlock.getMaterial() == Material.STONE || weightBlock.getMaterial() == Material.METAL || weightBlock.getMaterial() == Material.HEAVY_METAL));
		float baseHardness = baseBlock.getDestroySpeed(world, pos.below());
		boolean isBaseValid = (baseBlock.isCollisionShapeFullBlock(world, pos) && (baseBlock.getMaterial() == Material.STONE || baseBlock.getMaterial() == Material.METAL || baseHardness >= 1.0F || baseHardness < 0.0F));
		return (isWeightValid && isBaseValid);
	}

	public boolean isDriedCondition(Level world, BlockPos pos) {
		BlockState upperBlock = world.getBlockState(pos.above(1));
		return (world.getBiome(pos).value().getBaseTemperature() < 0.15F
				&& !world.canSeeSky(pos.above()) && upperBlock.isAir());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
		p_49915_.add(HARDNESS);
	}
}

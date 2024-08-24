package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.utils.RecipeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TofuBlock extends Block {
	public static final IntegerProperty HARDNESS = IntegerProperty.create("hardness", 0, 7);

	private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);


	public TofuBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
		if (isUnderWeight(worldIn, pos)) {
			if (rand.nextInt(5) == 0) {
				double d4 = rand.nextBoolean() ? 0.8D : -0.8D;
				double d0 = pos.getX() + 0.5D + rand.nextFloat() * d4;
				double d1 = (pos.getY() + rand.nextFloat());
				double d2 = pos.getZ() + 0.5D + rand.nextFloat() * d4;
				ParticleOptions particle = rand.nextInt(3) == 0 ? TofuParticleTypes.DRIP_SOYMILK_HANG.get() : ParticleTypes.DRIPPING_WATER;

				worldIn.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}

			if (isMoreHardenCondition(worldIn, pos)) {
				float f = rand.nextFloat();
				if (!(f > 0.15F)) {
					spawnDripParticle(worldIn, pos.below(2), stateIn);
				}
			}
		}
	}

	private static void spawnDripParticle(Level p_154072_, BlockPos p_154073_, BlockState p_154074_) {
		Vec3 vec3 = p_154074_.getOffset(p_154072_, p_154073_);
		double d0 = 0.0625D;
		double d1 = (double) p_154073_.getX() + 0.5D + vec3.x;
		double d2 = (double) ((float) (p_154073_.getY() + 1) - 0.6875F) - 0.0625D;
		double d3 = (double) p_154073_.getZ() + 0.5D + vec3.z;
		ParticleOptions particleoptions = ParticleTypes.DRIPPING_DRIPSTONE_WATER;
		p_154072_.addParticle(particleoptions, d1, d2, d3, 0.0D, 0.0D, 0.0D);
	}

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		super.randomTick(state, worldIn, pos, random);
		if (isDriedCondition(worldIn, pos)) {
			if (random.nextInt(8) == 0) {
				if (this == TofuBlocks.MOMENTOFU.get()) {
					worldIn.setBlock(pos, TofuBlocks.DRIEDTOFU.get().defaultBlockState(), 2);
				}
			}
		} else if (isUnderWeight(worldIn, pos) && state.hasProperty(HARDNESS)) {
			boolean dripStoneFlag = this.isMoreHardenCondition(worldIn, pos);
			int i = state.getValue(HARDNESS);
			int hardenSpeed = dripStoneFlag ? 1 : 0;

			int hardness = this == TofuBlocks.MOMENTOFU.get() ? 3 - hardenSpeed : 4 - hardenSpeed;

			/*if(dripStoneFlag){
				BlockPos blockpos2 = pos.below(2);
				BlockPos blockpos1 = findFillableCauldronBelowStalactiteTip(worldIn, blockpos2, Fluids.WATER);

				if (blockpos1 != null) {
					worldIn.levelEvent(1504, blockpos2, 0);
					int i2 = blockpos2.getY() - blockpos1.getY();
					int j = 50 + i2;
					BlockState blockstate = worldIn.getBlockState(blockpos1);
					worldIn.scheduleTick(blockpos1, blockstate.getBlock(), j);
				}
			}*/

			if (random.nextInt(hardness) == 0) {
				if (i < 7) {
					worldIn.setBlock(pos, state.setValue(HARDNESS, i + 1), 2);
				} else {
					ItemStack result = RecipeHelper.getTofu(worldIn, state.getBlock());
					if (result != null)
						worldIn.setBlock(pos, Block.byItem(result.getItem()).defaultBlockState(), 2);
				}
			}
		}
	}

	/*
	 * copy from Vanilla
	 */
	private static Optional<BlockPos> findBlockVertical(LevelAccessor p_202007_, BlockPos p_202008_, Direction.AxisDirection p_202009_, BiPredicate<BlockPos, BlockState> p_202010_, Predicate<BlockState> p_202011_, int p_202012_) {
		Direction direction = Direction.get(p_202009_, Direction.Axis.Y);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = p_202008_.mutable();

		for (int i = 1; i < p_202012_; ++i) {
			blockpos$mutableblockpos.move(direction);
			BlockState blockstate = p_202007_.getBlockState(blockpos$mutableblockpos);
			if (p_202011_.test(blockstate)) {
				return Optional.of(blockpos$mutableblockpos.immutable());
			}

			if (p_202007_.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !p_202010_.test(blockpos$mutableblockpos, blockstate)) {
				return Optional.empty();
			}
		}

		return Optional.empty();
	}

	@Nullable
	private static BlockPos findFillableCauldronBelowStalactiteTip(Level p_154077_, BlockPos p_154078_, Fluid p_154079_) {
		Predicate<BlockState> predicate = (p_154162_) -> {
			return p_154162_.getBlock() instanceof CauldronBlock;
		};
		BiPredicate<BlockPos, BlockState> bipredicate = (p_202034_, p_202035_) -> {
			return canDripThrough(p_154077_, p_202034_, p_202035_);
		};
		return findBlockVertical(p_154077_, p_154078_, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse((BlockPos) null);
	}

	private static boolean canDripThrough(BlockGetter p_202018_, BlockPos p_202019_, BlockState p_202020_) {
		if (p_202020_.isAir()) {
			return true;
		} else if (p_202020_.isSolidRender(p_202018_, p_202019_)) {
			return false;
		} else if (!p_202020_.getFluidState().isEmpty()) {
			return false;
		} else {
			VoxelShape voxelshape = p_202020_.getCollisionShape(p_202018_, p_202019_);
			return !Shapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, voxelshape, BooleanOp.AND);
		}
	}

	public boolean isMoreHardenCondition(Level world, BlockPos pos) {
		BlockState dripstoneBlock = world.getBlockState(pos.below(2));
		return dripstoneBlock.is(Blocks.POINTED_DRIPSTONE) && dripstoneBlock.getValue(PointedDripstoneBlock.TIP_DIRECTION) == Direction.DOWN;
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

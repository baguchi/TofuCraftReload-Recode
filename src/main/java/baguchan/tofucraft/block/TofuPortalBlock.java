package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuParticles;
import baguchan.tofucraft.world.dimension.TofuWorldTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class TofuPortalBlock extends BreakableBlock {
	private static final VoxelShape AABB = VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D));

	public TofuPortalBlock(Properties props) {
		super(props);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB;
	}

	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		Size size1 = new Size(worldIn, pos);
		if (size1.isValid()) {
			size1.placePortalBlocks();
			worldIn.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	@Override
	@Deprecated
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		boolean good = world.getBlockState(pos.below()).canOcclude();

		for (Direction facing : Direction.Plane.HORIZONTAL) {
			if (!good) break;

			BlockState neighboringState = world.getBlockState(pos.relative(facing));

			good = neighboringState.getBlock() == TofuBlocks.GRILLEDTOFU || neighboringState == state;
		}

		if (!good) {
			world.globalLevelEvent(2001, pos, Block.getId(state));
			world.setBlock(pos, TofuBlocks.SOYMILK.defaultBlockState(), 0b11);
		}
	}

	@Override
	public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
		super.entityInside(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);

		attemptSendPlayer(p_196262_4_, false);
	}

	private static RegistryKey<World> getDestination(Entity entity) {
		return !entity.level.dimension().getRegistryName().equals(TofuDimensions.tofu_world.getRegistryName())
				? TofuDimensions.tofu_world : RegistryKey.create(Registry.DIMENSION_REGISTRY, Dimension.OVERWORLD.getRegistryName());
	}

	public static void attemptSendPlayer(Entity entity, boolean forcedEntry) {
		if (!entity.isAlive() || entity.level.isClientSide) {
			return;
		}

		if (entity.isPassenger() || entity.isVehicle() || !entity.canChangeDimensions()) {
			return;
		}

		if (!forcedEntry && entity.isOnPortalCooldown()) {
			entity.setPortalCooldown();

			return;
		}

		RegistryKey<World> destination = getDestination(entity);
		ServerWorld serverWorld = entity.level.getServer().getLevel(destination);

		if (serverWorld == null)
			return;

		entity.changeDimension(serverWorld, new TofuWorldTeleporter());
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		int random = rand.nextInt(100);
		if (random == 0)
			worldIn.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		for (int i = 0; i < 4; i++) {
			double xPos = (pos.getX() + rand.nextFloat());
			double yPos = pos.getY() + 1.0D;
			double zPos = (pos.getZ() + rand.nextFloat());
			double xSpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			double ySpeed = rand.nextFloat();
			double zSpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			worldIn.addParticle(TofuParticles.TOFU_PORTAL, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
		}
	}

	public static class Size {
		private static final int MAX_SIZE = 12;

		private static final int MIN_SIZE = 1;

		private final IWorld world;

		private boolean valid = false;

		private BlockPos nw;

		private BlockPos se;

		public Size(IWorld world, BlockPos pos) {
			this.world = world;
			int east = getDistanceUntilEdge(pos, Direction.EAST);
			int west = getDistanceUntilEdge(pos, Direction.WEST);
			int north = getDistanceUntilEdge(pos, Direction.NORTH);
			int south = getDistanceUntilEdge(pos, Direction.SOUTH);
			int width = east + west - 1;
			int length = north + south - 1;
			if (width > 12 || length > 12)
				return;
			if (width < 1 || length < 1)
				return;
			BlockPos neCorner = pos.east(east).north(north);
			BlockPos nwCorner = pos.west(west).north(north);
			BlockPos seCorner = pos.east(east).south(south);
			BlockPos swCorner = pos.west(west).south(south);
			this.nw = nwCorner.offset(1, 0, 1);
			this.se = seCorner.offset(-1, 0, -1);
			int wallWidth = width + 2;
			int wallLength = length + 2;
			for (int y = 0; y <= 1; y++) {
				for (int x = 0; x < wallWidth; x++) {
					for (int z = 0; z < wallLength; z++) {
						if (((y == 0 && x != 0 && z != 0 && x != wallWidth - 1 && z != wallLength - 1) || (y == 1 && (x == 0 || z == 0 || x == wallWidth - 1 || z == wallLength - 1))) &&
								!isTofuBlock(world.getBlockState(nwCorner.below().offset(x, y, z))))
							//TODO
							//!isTofuBlock(world.getBlockState(nwCorner.above().offset(x, y, z))))
							return;
					}
				}
			}
			this.valid = true;
		}

		int getDistanceUntilEdge(BlockPos pos, Direction facing) {
			int i;
			for (i = 0; i < 9; i++) {
				BlockPos blockpos = pos.relative(facing, i);
				if (!isEmptyBlock(this.world.getBlockState(blockpos)) || !isTofuBlock(this.world.getBlockState(blockpos.below())))
					break;
			}
			BlockState state = this.world.getBlockState(pos.relative(facing, i));
			return isTofuBlock(state) ? i : 0;
		}

		boolean isEmptyBlock(BlockState state) {
			return (state.getBlock() == TofuBlocks.SOYMILK);
		}

		boolean isTofuBlock(BlockState state) {
			return (state.getBlock() == TofuBlocks.GRILLEDTOFU);
		}

		public boolean isValid() {
			return this.valid;
		}

		void placePortalBlocks() {
			for (BlockPos portalPos : BlockPos.Mutable.betweenClosed(this.nw, this.se))
				this.world.setBlock(portalPos, TofuBlocks.TOFU_PORTAL.defaultBlockState(), 2);
		}
	}
}

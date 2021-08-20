package baguchan.tofucraft.block;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.world.TofuLevelTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class TofuPortalBlock extends Block {
	public TofuPortalBlock(Properties props) {
		super(props);
	}

	@Override
	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	public boolean trySpawnPortal(Level worldIn, BlockPos pos) {
		Size size1 = new Size(worldIn, pos);
		if (size1.isValid()) {
			size1.placePortalBlocks();
			worldIn.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	@Override
	@Deprecated
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		boolean good = world.getBlockState(pos.below()).canOcclude();

		for (Direction facing : Direction.Plane.HORIZONTAL) {
			if (!good) break;

			BlockState neighboringState = world.getBlockState(pos.relative(facing));

			good = facing == Direction.UP || neighboringState.getBlock() == TofuBlocks.GRILLEDTOFU || neighboringState == state;
		}

		if (!good) {
			world.levelEvent(2001, pos, Block.getId(state));
			world.setBlock(pos, TofuBlocks.SOYMILK.defaultBlockState(), 0b11);
		}
	}

	@Override
	public void entityInside(BlockState p_196262_1_, Level p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
		super.entityInside(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);

		p_196262_4_.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).ifPresent(handler -> {
			handler.setInPortal(true);
			int waitTime = handler.getPortalTimer();
			if (waitTime >= 80) {
				attemptSendPlayer(p_196262_4_, p_196262_2_);
				handler.setPortalTimer(0);
			}
		});

	}

	private static ResourceKey<Level> getDestination(Entity entity) {
		return entity.level.dimension() == Level.OVERWORLD
				? TofuDimensions.tofu_world : Level.OVERWORLD;
	}

	public static void attemptSendPlayer(Entity entity, Level oldworld) {
		if (!entity.isAlive()) {
			return;
		}

		if (entity.isPassenger() || entity.isVehicle() || !entity.canChangeDimensions()) {
			return;
		}
		if (oldworld != null) {
			MinecraftServer minecraftserver = oldworld.getServer();
			if (minecraftserver != null) {

				ResourceKey<Level> destination = getDestination(entity);
				ServerLevel serverLevel = minecraftserver.getLevel(destination);

				if (serverLevel == null)
					return;

				entity.level.getProfiler().push("portal");
				entity.changeDimension(serverLevel, new TofuLevelTeleporter());
				entity.level.getProfiler().pop();
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		int random = rand.nextInt(100);
		if (random == 0)
			worldIn.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
	}

	public static class Size {
		private static final int MAX_SIZE = 12;

		private static final int MIN_SIZE = 1;

		private final LevelAccessor world;

		private boolean valid = false;

		private BlockPos nw;

		private BlockPos se;

		public Size(LevelAccessor world, BlockPos pos) {
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
			for (BlockPos portalPos : BlockPos.MutableBlockPos.betweenClosed(this.nw, this.se))
				this.world.setBlock(portalPos, TofuBlocks.TOFU_PORTAL.defaultBlockState(), 2);
		}
	}
}

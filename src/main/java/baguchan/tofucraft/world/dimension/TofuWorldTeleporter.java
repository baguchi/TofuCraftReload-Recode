package baguchan.tofucraft.world.dimension;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PortalInfo;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class TofuWorldTeleporter implements ITeleporter {
	private static final Map<ResourceLocation, Map<ColumnPos, PortalPosition>> destinationCoordinateCache = new HashMap<>();
	private static final Object2LongMap<ColumnPos> columnMap = new Object2LongOpenHashMap<>();

	@Nullable
	@Override
	public PortalInfo getPortalInfo(Entity entity, ServerWorld dest, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
		PortalInfo pos;
		if ((pos = placeInExistingPortal(dest, entity, entity.blockPosition(), entity instanceof PlayerEntity)) == null) {
			pos = moveToSafeCoords(dest, entity);
			makePortal(entity, dest, pos.pos);
			pos = placeInExistingPortal(dest, entity, new BlockPos(pos.pos), entity instanceof PlayerEntity);
		}
		return pos;
	}

	@Nullable
	private static PortalInfo placeInExistingPortal(ServerWorld world, Entity entity, BlockPos pos, boolean isPlayer) {
		int i = 200; // scan radius up to 200, and also un-inline this variable back into below
		boolean flag = true;
		BlockPos blockpos = BlockPos.ZERO;
		ColumnPos columnPos = new ColumnPos(pos);

		if (!isPlayer && columnMap.containsKey(columnPos)) {
			return null;
		} else {
			PortalPosition portalPosition = destinationCoordinateCache.containsKey(world.dimension().getRegistryName()) ? destinationCoordinateCache.get(world.dimension().getRegistryName()).get(columnPos) : null;
			if (portalPosition != null) {
				blockpos = portalPosition.pos;
				portalPosition.lastUpdateTime = world.getGameTime();
				flag = false;
			} else {
				//BlockPos blockpos3 = new BlockPos(entity);
				double d0 = Double.MAX_VALUE;

				for (int i1 = -i; i1 <= i; ++i1) {
					BlockPos blockpos2;

					for (int j1 = -i; j1 <= i; ++j1) {

						// skip positions outside current world border (MC-114796)
						if (!world.getWorldBorder().isWithinBounds(pos.offset(i1, 0, j1))) {
							continue;
						}

						// skip chunks that aren't generated
						ChunkPos chunkPos = new ChunkPos(pos.offset(i1, 0, j1));
						if (!world.getChunkSource().chunkMap.isExistingChunkFull(chunkPos)) {
							continue;
						}

						// explicitly fetch chunk so it can be unloaded if needed
						Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z);

						for (BlockPos blockpos1 = pos.offset(i1, getScanHeight(world, pos) - pos.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
							blockpos2 = blockpos1.below();

							// don't lookup state if inner condition would fail
							if (d0 >= 0.0D && blockpos1.distSqr(pos) >= d0) {
								continue;
							}

							// use our portal block
							if (isPortal(chunk.getBlockState(blockpos1))) {
								for (blockpos2 = blockpos1.below(); isPortal(chunk.getBlockState(blockpos2)); blockpos2 = blockpos2.below()) {
									blockpos1 = blockpos2;
								}

								double d1 = blockpos1.distSqr(pos);
								if (d0 < 0.0D || d1 < d0) {
									d0 = d1;
									blockpos = blockpos1;
									// restrict search radius to new distance
									i = MathHelper.ceil(MathHelper.sqrt(d1));
								}
							}
						}

						// mark unwatched chunks for unload
						//						if (!this.world.getPlayerChunkMap().contains(chunkPos.x, chunkPos.z)) {
						//							this.world.getChunkProvider().queueUnload(chunk);
						//						}
					}
				}
			}
		}

		if (blockpos.equals(BlockPos.ZERO)) {
			long factor = world.getGameTime() + 300L;
			columnMap.put(columnPos, factor);
			return null;
		} else {
			if (flag) {
				destinationCoordinateCache.putIfAbsent(world.dimension().getRegistryName(), Maps.newHashMapWithExpectedSize(4096));
				destinationCoordinateCache.get(world.dimension().getRegistryName()).put(columnPos, new PortalPosition(blockpos, world.getGameTime()));
				world.getChunkSource().registerTickingTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, new BlockPos(columnPos.x, blockpos.getY(), columnPos.z));
			}

			// replace with our own placement logic
			BlockPos[] portalBorder = getBoundaryPositions(world, blockpos).toArray(new BlockPos[0]);
			BlockPos borderPos = portalBorder[0/*random.nextInt(portalBorder.length)*/];

			double portalX = borderPos.getX() + 0.5;
			double portalY = borderPos.getY() + 1.0;
			double portalZ = borderPos.getZ() + 0.5;

			return makePortalInfo(entity, portalX, portalY, portalZ);
		}
	}

	private static int getScanHeight(ServerWorld world, BlockPos pos) {
		return getScanHeight(world, pos.getX(), pos.getZ());
	}

	private static int getScanHeight(ServerWorld world, int x, int z) {
		int worldHeight = world.getHeight() - 1;
		int chunkHeight = world.getChunk(x >> 4, z >> 4).getHighestSectionPosition() + 15;
		return Math.min(worldHeight, chunkHeight);
	}

	private static boolean isPortal(BlockState state) {
		return state.getBlock() == TofuBlocks.TOFU_PORTAL;
	}

	// from the start point, builds a set of all directly adjacent non-portal blocks
	private static Set<BlockPos> getBoundaryPositions(ServerWorld world, BlockPos start) {
		Set<BlockPos> result = new HashSet<>(), checked = new HashSet<>();
		checked.add(start);
		checkAdjacent(world, start, checked, result);
		return result;
	}

	private static void checkAdjacent(ServerWorld world, BlockPos pos, Set<BlockPos> checked, Set<BlockPos> result) {
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			BlockPos offset = pos.relative(facing);
			if (!checked.add(offset))
				continue;
			if (isPortalAt(world, offset)) {
				checkAdjacent(world, offset, checked, result);
			} else {
				result.add(offset);
			}
		}
	}

	private static boolean isPortalAt(ServerWorld world, BlockPos pos) {
		return isPortal(world.getBlockState(pos));
	}

	private static PortalInfo moveToSafeCoords(ServerWorld world, Entity entity) {
		BlockPos pos = entity.blockPosition();
		if (isSafeAround(world, pos, entity)) {
			return makePortalInfo(entity, entity.position());
		}
		BlockPos safeCoords = findSafeCoords(world, 200, pos, entity);
		if (safeCoords != null) {
			return makePortalInfo(entity, safeCoords.getX(), entity.getY(), safeCoords.getZ());
		}

		safeCoords = findSafeCoords(world, 400, pos, entity);

		if (safeCoords != null) {
			return makePortalInfo(entity, safeCoords.getX(), entity.getY(), safeCoords.getZ());
		}

		return makePortalInfo(entity, entity.position());
	}

	public static boolean isSafeAround(World world, BlockPos pos, Entity entity) {

		if (!isSafe(world, pos, entity)) {
			return false;
		}

		for (Direction facing : Direction.Plane.HORIZONTAL) {
			if (!isSafe(world, pos.relative(facing, 16), entity)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isSafe(World world, BlockPos pos, Entity entity) {
		return checkPos(world, pos);
	}

	private static boolean checkPos(World world, BlockPos pos) {
		return world.getWorldBorder().isWithinBounds(pos);
	}


	@Nullable
	private static BlockPos findSafeCoords(ServerWorld world, int range, BlockPos pos, Entity entity) {
		int attempts = range / 8;
		for (int x = 0; x < attempts; x++) {
			for (int z = 0; z < attempts; z++) {
				BlockPos dPos = new BlockPos(pos.getX() + (x * attempts) - (range / 2), 100, pos.getZ() + (z * attempts) - (range / 2));

				if (isSafeAround(world, dPos, entity)) {
					return dPos;
				}
			}
		}
		return null;
	}

	private static void makePortal(Entity entity, ServerWorld world, Vector3d pos) {
		loadSurroundingArea(world, pos);

		BlockPos spot = findPortalCoords(world, pos, blockPos -> isPortalAt(world, blockPos));
		String name = entity.getName().getString();

		if (spot != null) {
			cachePortalCoords(world, pos, spot);
			return;
		}

		spot = findPortalCoords(world, pos, blockpos -> isIdealForPortal(world, blockpos));

		if (spot != null) {
			cachePortalCoords(world, pos, makePortalAt(world, spot));
			return;
		}

		spot = findPortalCoords(world, pos, blockPos -> isOkayForPortal(world, blockPos));

		if (spot != null) {
			cachePortalCoords(world, pos, makePortalAt(world, spot));
			return;
		}

		double yFactor = getYFactor(world);
		cachePortalCoords(world, pos, makePortalAt(world, new BlockPos(entity.getX(), (entity.getY() * yFactor) - 1.0, entity.getZ())));
	}

	private static boolean isOkayForPortal(ServerWorld world, BlockPos pos) {
		for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
			for (int potentialX = 0; potentialX < 4; potentialX++) {
				for (int potentialY = 0; potentialY < 4; potentialY++) {
					BlockPos tPos = pos.offset(potentialX - 1, potentialY, potentialZ - 1);
					Material material = world.getBlockState(tPos).getMaterial();
					if (potentialY == 0 && !material.isSolid() && !material.isLiquid() || potentialY >= 1 && !material.isReplaceable()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static void loadSurroundingArea(ServerWorld world, Vector3d pos) {

		int x = MathHelper.floor(pos.x) >> 4;
		int z = MathHelper.floor(pos.y) >> 4;

		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				world.getChunk(x + dx, z + dz);
			}
		}
	}

	@Nullable
	private static BlockPos findPortalCoords(ServerWorld world, Vector3d loc, Predicate<BlockPos> predicate) {
		// adjust the height based on what world we're traveling to
		double yFactor = getYFactor(world);
		// modified copy of base Teleporter method:
		int entityX = MathHelper.floor(loc.x);
		int entityZ = MathHelper.floor(loc.z);

		BlockPos.Mutable pos = new BlockPos.Mutable();

		double spotWeight = -1D;
		BlockPos spot = null;

		int range = 16;
		for (int rx = entityX - range; rx <= entityX + range; rx++) {
			double xWeight = (rx + 0.5D) - loc.x;
			for (int rz = entityZ - range; rz <= entityZ + range; rz++) {
				double zWeight = (rz + 0.5D) - loc.z;

				for (int ry = getScanHeight(world, rx, rz); ry >= 0; ry--) {

					if (!world.isEmptyBlock(pos.set(rx, ry, rz))) {
						continue;
					}

					while (ry > 0 && world.isEmptyBlock(pos.set(rx, ry - 1, rz))) {
						ry--;
					}

					double yWeight = (ry + 0.5D) - loc.y * yFactor;
					double rPosWeight = xWeight * xWeight + yWeight * yWeight + zWeight * zWeight;

					if (spotWeight < 0.0D || rPosWeight < spotWeight) {
						// check from the "in ground" pos
						if (predicate.test(pos)) {
							spotWeight = rPosWeight;
							spot = pos.immutable();
						}
					}
				}
			}
		}

		return spot;
	}

	private static double getYFactor(ServerWorld world) {
		return world.dimension().getRegistryName().equals(World.OVERWORLD.getRegistryName()) ? 2.0 : 0.5;
	}

	private static void cachePortalCoords(ServerWorld world, Vector3d loc, BlockPos pos) {
		int x = MathHelper.floor(loc.x), z = MathHelper.floor(loc.z);
		destinationCoordinateCache.putIfAbsent(world.dimension().getRegistryName(), Maps.newHashMapWithExpectedSize(4096));
		destinationCoordinateCache.get(world.dimension().getRegistryName()).put(new ColumnPos(x, z), new PortalPosition(pos, world.getGameTime()));
	}

	private static boolean isIdealForPortal(ServerWorld world, BlockPos pos) {
		for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
			for (int potentialX = 0; potentialX < 4; potentialX++) {
				for (int potentialY = 0; potentialY < 4; potentialY++) {
					BlockPos tPos = pos.offset(potentialX - 1, potentialY, potentialZ - 1);
					Material material = world.getBlockState(tPos).getMaterial();
					if (potentialY == 0 && material != Material.REPLACEABLE_PLANT || potentialY >= 1 && !material.isReplaceable()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static BlockPos makePortalAt(World world, BlockPos pos) {
		BlockState portalState = TofuBlocks.TOFU_PORTAL.defaultBlockState();
		while (pos.getX() > 1 && world.isEmptyBlock(pos))
			pos = pos.below();
		while (!world.isEmptyBlock(pos.below()) && world.getBlockState(pos).getBlock() != TofuBlocks.TOFU_TERRAIN)
			pos = pos.above();
		BlockState snowstate = TofuBlocks.GRILLEDTOFU.defaultBlockState();
		for (BlockPos basePos : BlockPos.Mutable.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 1, 2)))
			world.setBlock(basePos, snowstate, 2);
		for (BlockPos airPos : BlockPos.Mutable.betweenClosed(pos.offset(-2, 2, -1), pos.offset(2, 3, 1)))
			world.setBlock(airPos, Blocks.AIR.defaultBlockState(), 2);
		for (BlockPos portalPos : BlockPos.Mutable.betweenClosed(pos.offset(-1, 1, -1), pos.offset(1, 1, 1)))
			world.setBlock(portalPos, portalState, 2);
		return pos;
	}

	private static PortalInfo makePortalInfo(Entity entity, double x, double y, double z) {
		return makePortalInfo(entity, new Vector3d(x, y, z));
	}

	private static PortalInfo makePortalInfo(Entity entity, Vector3d pos) {
		return new PortalInfo(pos, Vector3d.ZERO, entity.xRot, entity.yRot);
	}

	static class PortalPosition {
		public final BlockPos pos;

		public long lastUpdateTime;

		public PortalPosition(BlockPos pos, long time) {
			this.pos = pos;
			this.lastUpdateTime = time;
		}
	}
}

package baguchan.tofucraft.world;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ColumnPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class TofuLevelTeleporter implements ITeleporter {
	private static final Map<ResourceLocation, Map<ColumnPos, PortalPosition>> destinationCoordinateCache = new HashMap<>();
	private static final Object2LongMap<ColumnPos> columnMap = new Object2LongOpenHashMap<>();

	@Nullable
	@Override
	public PortalInfo getPortalInfo(Entity entity, ServerLevel dest, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		PortalInfo pos;
		if ((pos = placeInExistingPortal(dest, entity, entity.blockPosition(), entity instanceof Player)) == null) {
			pos = moveToSafeCoords(dest, entity);
			makePortal(entity, dest, pos.pos);
			pos = placeInExistingPortal(dest, entity, BlockPos.containing(pos.pos), entity instanceof Player);
		}
		return pos;
	}

	@Nullable
	private static PortalInfo placeInExistingPortal(ServerLevel world, Entity entity, BlockPos pos, boolean isPlayer) {
		int i = 200; // scan radius up to 200, and also un-inline this variable back into below
		boolean flag = true;
		BlockPos blockpos = BlockPos.ZERO;
		ColumnPos columnPos = new ColumnPos(pos.getX(), pos.getZ());

		if (!isPlayer && columnMap.containsKey(columnPos)) {
			return null;
		} else {
			PortalPosition portalPosition = destinationCoordinateCache.containsKey(world.dimension().location()) ? destinationCoordinateCache.get(world.dimension().location()).get(columnPos) : null;
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
						if (!world.getChunkSource().hasChunk(chunkPos.x, chunkPos.z)) {
							continue;
						}

						// explicitly fetch chunk so it can be unloaded if needed
						LevelChunk chunk = world.getChunk(chunkPos.x, chunkPos.z);

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
									i = Mth.ceil(Math.sqrt(d1));
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
				destinationCoordinateCache.putIfAbsent(world.dimension().location(), Maps.newHashMapWithExpectedSize(4096));
				destinationCoordinateCache.get(world.dimension().location()).put(columnPos, new PortalPosition(blockpos, world.getGameTime()));
				world.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, new BlockPos(columnPos.x(), blockpos.getY(), columnPos.z()));
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

	private static int getScanHeight(ServerLevel world, BlockPos pos) {
		return getScanHeight(world, pos.getX(), pos.getZ());
	}

	private static int getScanHeight(ServerLevel world, int x, int z) {
		int worldHeight = world.getHeight() - 1;
		int chunkHeight = world.getChunk(x >> 4, z >> 4).getHeight() + 15;
		return Math.min(worldHeight, chunkHeight);
	}

	private static boolean isPortal(BlockState state) {
		return state.getBlock() == TofuBlocks.TOFU_PORTAL.get();
	}

	// from the start point, builds a set of all directly adjacent non-portal blocks
	private static Set<BlockPos> getBoundaryPositions(ServerLevel world, BlockPos start) {
		Set<BlockPos> result = new HashSet<>(), checked = new HashSet<>();
		checked.add(start);
		checkAdjacent(world, start, checked, result);
		return result;
	}

	private static void checkAdjacent(ServerLevel world, BlockPos pos, Set<BlockPos> checked, Set<BlockPos> result) {
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

	private static boolean isPortalAt(ServerLevel world, BlockPos pos) {
		return isPortal(world.getBlockState(pos));
	}

	private static PortalInfo moveToSafeCoords(ServerLevel world, Entity entity) {
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

	public static boolean isSafeAround(Level world, BlockPos pos, Entity entity) {

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

	private static boolean isSafe(Level world, BlockPos pos, Entity entity) {
		return checkPos(world, pos);
	}

	private static boolean checkPos(Level world, BlockPos pos) {
		return world.getWorldBorder().isWithinBounds(pos);
	}


	@Nullable
	private static BlockPos findSafeCoords(ServerLevel world, int range, BlockPos pos, Entity entity) {
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

	private static void makePortal(Entity entity, ServerLevel world, Vec3 pos) {
		loadSurroundingArea(world, pos);

		BlockPos spot = findPortalCoords(world, pos, blockPos -> isPortalAt(world, blockPos));
		
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
		cachePortalCoords(world, pos, makePortalAt(world, BlockPos.containing(entity.getX(), (entity.getY() * yFactor) - 1.0, entity.getZ())));
	}

	private static boolean isOkayForPortal(ServerLevel world, BlockPos pos) {
		for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
			for (int potentialX = 0; potentialX < 4; potentialX++) {
				for (int potentialY = 0; potentialY < 4; potentialY++) {
					BlockPos tPos = pos.offset(potentialX - 1, potentialY, potentialZ - 1);
					BlockState blockState = world.getBlockState(tPos);
					if (potentialY == 0 && !blockState.isSolid() && !blockState.getFluidState().isEmpty() || potentialY >= 1 && !blockState.canBeReplaced()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static void loadSurroundingArea(ServerLevel world, Vec3 pos) {

		int x = Mth.floor(pos.x) >> 4;
		int z = Mth.floor(pos.y) >> 4;

		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				world.getChunk(x + dx, z + dz);
			}
		}
	}

	@Nullable
	private static BlockPos findPortalCoords(ServerLevel world, Vec3 loc, Predicate<BlockPos> predicate) {
		// adjust the height based on what world we're traveling to
		double yFactor = getYFactor(world);
		// modified copy of base Teleporter method:
		int entityX = Mth.floor(loc.x);
		int entityZ = Mth.floor(loc.z);

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		double spotWeight = -1D;
		BlockPos spot = null;

		int range = 16;
		for (int rx = entityX - range; rx <= entityX + range; rx++) {
			double xWeight = (rx + 0.5D) - loc.x;
			for (int rz = entityZ - range; rz <= entityZ + range; rz++) {
				double zWeight = (rz + 0.5D) - loc.z;

				for (int ry = getScanHeight(world, rx, rz); ry >= world.getMinBuildHeight(); ry--) {

					if (!world.isEmptyBlock(pos.set(rx, ry, rz))) {
						continue;
					}

					while (ry > world.getMinBuildHeight() && world.isEmptyBlock(pos.set(rx, ry - 1, rz))) {
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

	private static double getYFactor(ServerLevel world) {
		return 2.0;
	}

	private static void cachePortalCoords(ServerLevel world, Vec3 loc, BlockPos pos) {
		int x = Mth.floor(loc.x), z = Mth.floor(loc.z);
		destinationCoordinateCache.putIfAbsent(world.dimension().location(), Maps.newHashMapWithExpectedSize(4096));
		destinationCoordinateCache.get(world.dimension().location()).put(new ColumnPos(x, z), new PortalPosition(pos, world.getGameTime()));
	}

	private static boolean isIdealForPortal(ServerLevel world, BlockPos pos) {
		for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
			for (int potentialX = 0; potentialX < 4; potentialX++) {
				for (int potentialY = 0; potentialY < 4; potentialY++) {
					BlockPos tPos = pos.offset(potentialX - 1, potentialY, potentialZ - 1);
					BlockState blockState = world.getBlockState(tPos);

					if (potentialY == 0 && !blockState.canBeReplaced() || potentialY >= 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static BlockPos makePortalAt(Level world, BlockPos pos) {
		BlockState portalState = TofuBlocks.TOFU_PORTAL.get().defaultBlockState();
		BlockState snowstate = TofuBlocks.GRILLEDTOFU.get().defaultBlockState();
		for (BlockPos basePos : BlockPos.MutableBlockPos.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 1, 2)))
			world.setBlock(basePos, snowstate, 2);
		for (BlockPos airPos : BlockPos.MutableBlockPos.betweenClosed(pos.offset(-2, 2, -1), pos.offset(2, 3, 1)))
			world.setBlock(airPos, Blocks.AIR.defaultBlockState(), 2);
		for (BlockPos portalPos : BlockPos.MutableBlockPos.betweenClosed(pos.offset(-1, 1, -1), pos.offset(1, 1, 1)))
			world.setBlock(portalPos, portalState, 2);
		return pos;
	}

	private static PortalInfo makePortalInfo(Entity entity, double x, double y, double z) {
		return makePortalInfo(entity, new Vec3(x, y, z));
	}

	private static PortalInfo makePortalInfo(Entity entity, Vec3 pos) {
		return new PortalInfo(pos, Vec3.ZERO, entity.getXRot(), entity.getYRot());
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
package baguchan.tofucraft.world.dimension;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PortalInfo;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;

public class TofuWorldTeleporter implements ITeleporter {
	private static final Map<ResourceLocation, Map<ColumnPos, PortalPosition>> destinationCoordinateCache = new HashMap<>();

	private static final Object2LongMap<ColumnPos> columnMap = (Object2LongMap<ColumnPos>) new Object2LongOpenHashMap();

	@Nullable
	public PortalInfo getPortalInfo(Entity entity, ServerWorld dest, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
		PortalInfo pos;
		if ((pos = placeInExistingPortal(dest, entity, entity.func_233580_cy_(), entity instanceof net.minecraft.entity.player.PlayerEntity)) == null) {
			pos = moveToSafeCoords(dest, entity);
			makePortal(entity, dest, pos.field_222505_a);
			pos = placeInExistingPortal(dest, entity, new BlockPos(pos.field_222505_a), entity instanceof net.minecraft.entity.player.PlayerEntity);
		}
		return pos;
	}

	@Nullable
	private static PortalInfo placeInExistingPortal(ServerWorld world, Entity entity, BlockPos pos, boolean isPlayer) {
		int i = 200;
		boolean flag = true;
		BlockPos blockpos = BlockPos.field_177992_a;
		ColumnPos columnPos = new ColumnPos(pos);
		if (!isPlayer && columnMap.containsKey(columnPos))
			return null;
		PortalPosition portalPosition = destinationCoordinateCache.containsKey(world.func_234923_W_().func_240901_a_()) ? (PortalPosition) ((Map) destinationCoordinateCache.get(world.func_234923_W_().func_240901_a_())).get(columnPos) : null;
		if (portalPosition != null) {
			blockpos = portalPosition.pos;
			portalPosition.lastUpdateTime = world.func_82737_E();
			flag = false;
		} else {
			double d0 = Double.MAX_VALUE;
			for (int i1 = -i; i1 <= i; i1++) {
				for (int j1 = -i; j1 <= i; j1++) {
					if (world.func_175723_af().func_177746_a(pos.func_177982_a(i1, 0, j1))) {
						ChunkPos chunkPos = new ChunkPos(pos.func_177982_a(i1, 0, j1));
						if ((world.func_72863_F()).field_217237_a.func_241090_h_(chunkPos)) {
							Chunk chunk = world.func_212866_a_(chunkPos.field_77276_a, chunkPos.field_77275_b);
							for (BlockPos blockpos1 = pos.func_177982_a(i1, getScanHeight(world, pos) - pos.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
								BlockPos blockpos2 = blockpos1.func_177977_b();
								if (d0 < 0.0D || blockpos1.func_177951_i((Vector3i) pos) < d0)
									if (isPortal(chunk.getBlockState(blockpos1))) {
										for (blockpos2 = blockpos1.func_177977_b(); isPortal(chunk.getBlockState(blockpos2)); blockpos2 = blockpos2.func_177977_b())
											blockpos1 = blockpos2;
										double d1 = blockpos1.func_177951_i((Vector3i) pos);
										if (d0 < 0.0D || d1 < d0) {
											d0 = d1;
											blockpos = blockpos1;
											i = MathHelper.func_76123_f(MathHelper.func_76133_a(d1));
										}
									}
							}
						}
					}
				}
			}
		}
		if (blockpos.equals(BlockPos.field_177992_a)) {
			long factor = world.func_82737_E() + 300L;
			columnMap.put(columnPos, factor);
			return null;
		}
		if (flag) {
			destinationCoordinateCache.putIfAbsent(world.func_234923_W_().func_240901_a_(), Maps.newHashMapWithExpectedSize(4096));
			((Map<ColumnPos, PortalPosition>) destinationCoordinateCache.get(world.func_234923_W_().func_240901_a_())).put(columnPos, new PortalPosition(blockpos, world.func_82737_E()));
			world.func_72863_F().registerTickingTicket(TicketType.field_219493_f, new ChunkPos(blockpos), 3, new BlockPos(columnPos.field_219439_a, blockpos.getY(), columnPos.field_219440_b));
		}
		BlockPos[] portalBorder = getBoundaryPositions(world, blockpos).<BlockPos>toArray(new BlockPos[0]);
		BlockPos borderPos = portalBorder[0];
		double portalX = borderPos.getX() + 0.5D;
		double portalY = borderPos.getY() + 1.0D;
		double portalZ = borderPos.getZ() + 0.5D;
		return makePortalInfo(entity, portalX, portalY, portalZ);
	}

	private static int getScanHeight(ServerWorld world, BlockPos pos) {
		return getScanHeight(world, pos.getX(), pos.getZ());
	}

	private static int getScanHeight(ServerWorld world, int x, int z) {
		int worldHeight = world.func_234938_ad_() - 1;
		int chunkHeight = world.func_212866_a_(x >> 4, z >> 4).func_76625_h() + 15;
		return Math.min(worldHeight, chunkHeight);
	}

	private static boolean isPortal(BlockState state) {
		return (state.getBlock() == TofuBlocks.TOFU_PORTAL.getBlock());
	}

	private static Set<BlockPos> getBoundaryPositions(ServerWorld world, BlockPos start) {
		Set<BlockPos> result = new HashSet<>(), checked = new HashSet<>();
		checked.add(start);
		checkAdjacent(world, start, checked, result);
		return result;
	}

	private static void checkAdjacent(ServerWorld world, BlockPos pos, Set<BlockPos> checked, Set<BlockPos> result) {
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			BlockPos offset = pos.func_177972_a(facing);
			if (!checked.add(offset))
				continue;
			if (isPortalAt(world, offset)) {
				checkAdjacent(world, offset, checked, result);
				continue;
			}
			result.add(offset);
		}
	}

	private static boolean isPortalAt(ServerWorld world, BlockPos pos) {
		return isPortal(world.getBlockState(pos));
	}

	private static PortalInfo moveToSafeCoords(ServerWorld world, Entity entity) {
		BlockPos pos = entity.func_233580_cy_();
		if (isSafeAround((World) world, pos, entity))
			return makePortalInfo(entity, entity.func_213303_ch());
		BlockPos safeCoords = findSafeCoords(world, 200, pos, entity);
		if (safeCoords != null)
			return makePortalInfo(entity, safeCoords.getX(), entity.func_226278_cu_(), safeCoords.getZ());
		safeCoords = findSafeCoords(world, 400, pos, entity);
		if (safeCoords != null)
			return makePortalInfo(entity, safeCoords.getX(), entity.func_226278_cu_(), safeCoords.getZ());
		return makePortalInfo(entity, entity.func_213303_ch());
	}

	public static boolean isSafeAround(World world, BlockPos pos, Entity entity) {
		if (!isSafe(world, pos, entity))
			return false;
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			if (!isSafe(world, pos.func_177967_a(facing, 16), entity))
				return false;
		}
		return true;
	}

	private static boolean isSafe(World world, BlockPos pos, Entity entity) {
		return checkPos(world, pos);
	}

	private static boolean checkPos(World world, BlockPos pos) {
		return world.func_175723_af().func_177746_a(pos);
	}

	@Nullable
	private static BlockPos findSafeCoords(ServerWorld world, int range, BlockPos pos, Entity entity) {
		int attempts = range / 8;
		for (int i = 0; i < attempts; i++) {
			BlockPos dPos = new BlockPos(pos.getX(), 100, pos.getZ());
			if (isSafeAround((World) world, dPos, entity))
				return dPos;
		}
		return null;
	}

	private static void makePortal(Entity entity, ServerWorld world, Vector3d pos) {
		loadSurroundingArea(world, pos);
		BlockPos spot = findPortalCoords(world, pos, blockPos -> isPortalAt(world, blockPos));
		String name = entity.func_200200_C_().toString();
		if (spot != null) {
			cachePortalCoords(world, pos, spot);
			return;
		}
		spot = findPortalCoords(world, pos, blockpos -> isIdealForPortal(world, blockpos));
		if (spot != null) {
			cachePortalCoords(world, pos, makePortalAt((World) world, spot));
			return;
		}
		spot = findPortalCoords(world, pos, blockPos -> isOkayForPortal(world, blockPos));
		if (spot != null) {
			cachePortalCoords(world, pos, makePortalAt((World) world, spot));
			return;
		}
		double yFactor = getYFactor(world);
		cachePortalCoords(world, pos, makePortalAt((World) world, new BlockPos(entity.func_226277_ct_(), entity.func_226278_cu_() * yFactor - 1.0D, entity.func_226281_cx_())));
	}

	private static void loadSurroundingArea(ServerWorld world, Vector3d pos) {
		int x = MathHelper.func_76128_c(pos.field_72450_a) >> 4;
		int z = MathHelper.func_76128_c(pos.field_72448_b) >> 4;
		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++)
				world.func_212866_a_(x + dx, z + dz);
		}
	}

	@Nullable
	private static BlockPos findPortalCoords(ServerWorld world, Vector3d loc, Predicate<BlockPos> predicate) {
		double yFactor = getYFactor(world);
		int entityX = MathHelper.func_76128_c(loc.field_72450_a);
		int entityZ = MathHelper.func_76128_c(loc.field_72449_c);
		BlockPos.Mutable pos = new BlockPos.Mutable();
		double spotWeight = -1.0D;
		BlockPos spot = null;
		int range = 16;
		for (int rx = entityX - range; rx <= entityX + range; rx++) {
			double xWeight = rx + 0.5D - loc.field_72450_a;
			for (int rz = entityZ - range; rz <= entityZ + range; rz++) {
				double zWeight = rz + 0.5D - loc.field_72449_c;
				for (int ry = getScanHeight(world, rx, rz); ry >= 0; ry--) {
					if (world.func_175623_d((BlockPos) pos.func_181079_c(rx, ry, rz))) {
						while (ry > 0 && world.func_175623_d((BlockPos) pos.func_181079_c(rx, ry - 1, rz)))
							ry--;
						double yWeight = ry + 0.5D - loc.field_72448_b * yFactor;
						double rPosWeight = xWeight * xWeight + yWeight * yWeight + zWeight * zWeight;
						if (spotWeight < 0.0D || rPosWeight < spotWeight)
							if (predicate.test(pos)) {
								spotWeight = rPosWeight;
								spot = pos.func_185334_h();
							}
					}
				}
			}
		}
		return spot;
	}

	private static double getYFactor(ServerWorld world) {
		return world.func_234923_W_().func_240901_a_().equals(World.field_234918_g_.func_240901_a_()) ? 2.0D : 0.5D;
	}

	private static void cachePortalCoords(ServerWorld world, Vector3d loc, BlockPos pos) {
		int x = MathHelper.func_76128_c(loc.field_72450_a), z = MathHelper.func_76128_c(loc.field_72449_c);
		destinationCoordinateCache.putIfAbsent(world.func_234923_W_().func_240901_a_(), Maps.newHashMapWithExpectedSize(4096));
		((Map<ColumnPos, PortalPosition>) destinationCoordinateCache.get(world.func_234923_W_().func_240901_a_())).put(new ColumnPos(x, z), new PortalPosition(pos, world.func_82737_E()));
	}

	private static boolean isIdealForPortal(ServerWorld world, BlockPos pos) {
		for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
			for (int potentialX = 0; potentialX < 4; potentialX++) {
				for (int potentialY = 0; potentialY < 4; potentialY++) {
					BlockPos tPos = pos.func_177982_a(potentialX - 1, potentialY, potentialZ - 1);
					Material material = world.getBlockState(tPos).func_185904_a();
					if ((potentialY == 0 && material != Material.field_151582_l) || (potentialY >= 1 && !material.func_76222_j()))
						return false;
				}
			}
		}
		return true;
	}

	private static boolean isOkayForPortal(ServerWorld world, BlockPos pos) {
		for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
			for (int potentialX = 0; potentialX < 4; potentialX++) {
				for (int potentialY = 0; potentialY < 4; potentialY++) {
					BlockPos tPos = pos.func_177982_a(potentialX - 1, potentialY, potentialZ - 1);
					Material material = world.getBlockState(tPos).func_185904_a();
					if ((potentialY == 0 && !material.func_76220_a() && !material.func_76224_d()) || (potentialY >= 1 && !material.func_76222_j()))
						return false;
				}
			}
		}
		return true;
	}

	private static PortalInfo makePortalInfo(Entity entity, double x, double y, double z) {
		return makePortalInfo(entity, new Vector3d(x, y, z));
	}

	private static PortalInfo makePortalInfo(Entity entity, Vector3d pos) {
		return new PortalInfo(pos, Vector3d.field_186680_a, entity.field_70125_A, entity.field_70177_z);
	}

	public static BlockPos makePortalAt(World world, BlockPos pos) {
		BlockState portalState = TofuBlocks.TOFU_PORTAL.func_176223_P();
		while (pos.getX() > 1 && world.func_175623_d(pos))
			pos = pos.func_177977_b();
		while (!world.func_175623_d(pos.func_177977_b()) && world.getBlockState(pos).getBlock() != TofuBlocks.TOFU_TERRAIN)
			pos = pos.func_177984_a();
		BlockState snowstate = TofuBlocks.GRILLEDTOFU.func_176223_P();
		for (BlockPos basePos : BlockPos.Mutable.func_218278_a(pos.func_177982_a(-2, 0, -2), pos.func_177982_a(2, 1, 2)))
			world.func_180501_a(basePos, snowstate, 2);
		for (BlockPos airPos : BlockPos.Mutable.func_218278_a(pos.func_177982_a(-2, 2, -1), pos.func_177982_a(2, 3, 1)))
			world.func_180501_a(airPos, Blocks.field_150350_a.func_176223_P(), 2);
		for (BlockPos portalPos : BlockPos.Mutable.func_218278_a(pos.func_177982_a(-1, 1, -1), pos.func_177982_a(1, 1, 1)))
			world.func_180501_a(portalPos, portalState, 2);
		return pos;
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

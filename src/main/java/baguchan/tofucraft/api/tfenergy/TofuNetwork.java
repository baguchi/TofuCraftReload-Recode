package baguchan.tofucraft.api.tfenergy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Mod.EventBusSubscriber
public class TofuNetwork {
	/*
	 * Feature:
	 * 1. Maintains a hashmap of all TofuEnergyStorageBlockEntity(TESTE)
	 * 2. Provides a set of helper methods to access TESTE.
	 *
	 * Tofu Network rules:
	 * 1. Every tile is registered to the network, no matter what it is.
	 *
	 * 2. Machines are not able to radiate energy themselves, and not likely the production
	 *    ones will too. The machines which should give out energy needs a antenna to act as
	 *    a medium to radiate energy. But actually the working part is only the machine.
	 *
	 * 3. The consumer should be passive, as the network load will be much higher if every
	 *    machine tried to access the map, the antenna should distribute the energy to other
	 *    machines.
	 * */
	public static final TofuNetwork Instance = new TofuNetwork();

	private HashMap<String, BlockEntity> reference = new HashMap<>();

	public static List<String> toUUIDs(Stream<Map.Entry<String, BlockEntity>> map) {
		List<String> uids = new ArrayList<>();
		map.forEach(entry -> uids.add(entry.getKey()));
		return uids;
	}

	public static List<BlockEntity> toTiles(Stream<Map.Entry<String, BlockEntity>> map) {
		List<BlockEntity> tes = new ArrayList<>();
		map.forEach(entry -> tes.add(entry.getValue()));
		return tes;
	}

	@SubscribeEvent
	public static void onUnloadLevel(LevelEvent.Unload event) {
		LevelAccessor world = event.getLevel();
		for (String uid : toUUIDs(Instance.getTEWithinDim(world.dimensionType()))) {
			//It is a world unload, so isSystem is here to prevent bugs from misdetailed event.
			Instance.unload(uid, true);
		}
	}

	public BlockEntity find(String uid) {
		return reference.get(uid);
	}

	public void register(String uid, BlockEntity te) {
		register(uid, te, false);
	}

	public void register(String uid, BlockEntity te, boolean isSystem) {
		if (!(te instanceof ITofuEnergy))
			throw new IllegalArgumentException("Can't register machine which is not Tofu Energy Tile!");
		reference.put(uid, te);
		MinecraftForge.EVENT_BUS.post(new TofuNetworkChangedEvent.NetworkLoaded(uid, te, isSystem));
	}

	public HashMap<String, BlockEntity> getReference() {
		return reference;
	}

	public Stream<Map.Entry<String, BlockEntity>> getTEWithinDim(DimensionType dimid) {
		return reference
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue().getLevel().dimensionType().equals(dimid));
	}

	public Stream<Map.Entry<String, BlockEntity>> getTEWithinRadius(Level world, BlockPos pos, double radius) {
		return getTEWithinDim(world.dimensionType())
				.filter(entry -> entry.getValue().getBlockPos().distSqr(pos) <= radius * radius);
	}

	public Stream<Map.Entry<String, BlockEntity>> getTEWithinRadius(BlockEntity center, double radius) {
		BlockPos pos = center.getBlockPos();
		return getTEWithinDim(center.getLevel().dimensionType())
				.filter(entry -> entry.getValue().getBlockPos().distSqr(pos) <= radius * radius);
	}

	public Stream<Map.Entry<String, BlockEntity>> getExtractableWithinRadius(Level world, BlockPos pos, double radius) {
		return getTEWithinRadius(world, pos, radius)
				.filter(entry -> ((ITofuEnergy) entry.getValue()).canDrain(null));
	}

	public Stream<Map.Entry<String, BlockEntity>> getExtractableWithinRadius(BlockEntity center, double radius) {
		if (!(center instanceof ITofuEnergy))
			throw new IllegalArgumentException("The center tile is not able to transfer energy!");
		return getTEWithinRadius(center, radius)
				.filter(entry -> ((ITofuEnergy) entry.getValue()).canDrain(center) && ((ITofuEnergy) center).canReceive(entry.getValue()));
	}

	public Stream<Map.Entry<String, BlockEntity>> getInsertableWithinRadius(Level world, BlockPos pos, double radius) {
		return getTEWithinRadius(world, pos, radius)
				.filter(entry -> ((ITofuEnergy) entry.getValue()).canReceive(null));
	}

	public Stream<Map.Entry<String, BlockEntity>> getInsertableWithinRadius(BlockEntity center, double radius) {
		if (!(center instanceof ITofuEnergy))
			throw new IllegalArgumentException("The center tile is not able to transfer energy!");
		return getTEWithinRadius(center, radius)
				.filter(entry -> ((ITofuEnergy) entry.getValue()).canReceive(center) && ((ITofuEnergy) center).canDrain(entry.getValue()));
	}

	public void unload(String uid) {
		unload(uid, false);
	}

	public void unload(String uid, boolean isSystem) {
		reference.remove(uid);
		MinecraftForge.EVENT_BUS.post(new TofuNetworkChangedEvent.NetworkRemoved(uid, reference.get(uid), isSystem));
	}

	public void clear() {
		clear(false);
	}

	public void clear(boolean isSystem) {
		reference.clear();
		MinecraftForge.EVENT_BUS.post(new TofuNetworkChangedEvent.NetworkCleared(isSystem));
	}
}

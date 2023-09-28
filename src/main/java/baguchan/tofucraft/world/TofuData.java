package baguchan.tofucraft.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TofuData extends SavedData {

	private static final String IDENTIFIER = "tofucraft_world_data";
	private int travelerSpawnDelay;
	private float travelerSpawnChance;
	private UUID travelerUUID;
	private static Map<Level, TofuData> dataMap = new HashMap<>();
	private final ServerLevel server;

	public TofuData(ServerLevel p_300199_) {
		super();
		this.server = p_300199_;
	}

	public static TofuData get(Level world) {
		if (world instanceof ServerLevel serverLevel) {
			ServerLevel overworld = world.getServer().getLevel(Level.OVERWORLD);
			TofuData fromMap = dataMap.get(overworld);
			if (fromMap == null) {
				DimensionDataStorage storage = overworld.getDataStorage();
				TofuData data = storage.computeIfAbsent(TofuData.factory(serverLevel), IDENTIFIER);
				if (data != null) {
					data.setDirty();
				}
				dataMap.put(world, data);
				return data;
			}
			return fromMap;
		}
		return null;
	}

	public static SavedData.Factory<TofuData> factory(ServerLevel p_300199_) {
		return new SavedData.Factory<>(() -> {
			return new TofuData(p_300199_);
		}, (p_296865_) -> {
			return load(p_300199_, p_296865_);
		}, DataFixTypes.SAVED_DATA_RAIDS);
	}

	public static TofuData load(ServerLevel p_300199_, CompoundTag nbt) {
		TofuData data = new TofuData(p_300199_);
		if (nbt.contains("TravelerSpawnDelay", 99)) {
			data.travelerSpawnDelay = nbt.getInt("TravelerSpawnDelay");
		}
		if (nbt.contains("TravelerSpawnChance", 99)) {
			data.travelerSpawnChance = nbt.getFloat("TravelerSpawnChance");
		}
		if (nbt.contains("TravelerUUID", 8)) {
			data.travelerUUID = UUID.fromString(nbt.getString("TravelerUUID"));
		}
		return data;
	}

	public int getTravelerSpawnDelay() {
		return this.travelerSpawnDelay;
	}

	public void setTravelerSpawnDelay(int delay) {
		this.travelerSpawnDelay = delay;
	}

	public float getTravelerSpawnChance() {
		return this.travelerSpawnChance;
	}

	public void setTravelerSpawnChance(float chance) {
		this.travelerSpawnChance = chance;
	}

	public void setTravelerUUID(UUID id) {
		this.travelerUUID = id;
	}


	@Override
	public CompoundTag save(CompoundTag compound) {
		compound.putInt("TravelerSpawnDelay", this.travelerSpawnDelay);
		compound.putFloat("TravelerSpawnChance", this.travelerSpawnChance);
		if (this.travelerUUID != null) {
			compound.putString("TravelerUUID", this.travelerUUID.toString());
		}
		return compound;
	}
}
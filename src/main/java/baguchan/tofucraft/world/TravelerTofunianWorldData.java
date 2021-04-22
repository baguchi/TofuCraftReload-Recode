package baguchan.tofucraft.world;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.UUID;

public class TravelerTofunianWorldData extends WorldSavedData {
	private static final String IDENTIFIER = "tofucraft_travelling_tofunian";

	private World world;

	private int tickCounter;

	private int tofunianSpawnDelay;

	private int tofunianSpawnChance;

	private UUID tofunianID;

	public TravelerTofunianWorldData() {
		super("tofucraft_travelling_tofunian");
	}

	public static TravelerTofunianWorldData get(World world) {
		if (world instanceof ServerWorld) {
			ServerWorld overworld = world.func_73046_m().func_71218_a(world.func_234923_W_());
			DimensionSavedDataManager storage = overworld.func_217481_x();
			TravelerTofunianWorldData data = (TravelerTofunianWorldData) storage.func_215753_b(TravelerTofunianWorldData::new, "tofucraft_travelling_tofunian");
			if (data != null) {
				data.world = world;
				data.func_76185_a();
				return data;
			}
		}
		return new TravelerTofunianWorldData();
	}

	public int getTofunianSpawnDelay() {
		return this.tofunianSpawnDelay;
	}

	public void setTofunianSpawnDelay(int delay) {
		this.tofunianSpawnDelay = delay;
	}

	public int getTofunianSpawnChance() {
		return this.tofunianSpawnChance;
	}

	public void setTofunianSpawnChance(int chance) {
		this.tofunianSpawnChance = chance;
	}

	public void setTravelerTofunianID(UUID id) {
		this.tofunianID = id;
	}

	public void tick() {
		this.tickCounter++;
	}

	public void func_76184_a(CompoundNBT nbt) {
		if (nbt.contains("TravelerTofunianSpawnDelay", 99))
			this.tofunianSpawnDelay = nbt.getInt("TravelerTofunianSpawnDelay");
		if (nbt.contains("TravelerTofunianSpawnChance", 99))
			this.tofunianSpawnChance = nbt.getInt("TravelerTofunianSpawnChance");
		if (nbt.contains("TravelerTofunianId", 8))
			this.tofunianID = UUID.fromString(nbt.func_74779_i("TravelerTofunianId"));
	}

	public CompoundNBT func_189551_b(CompoundNBT compound) {
		compound.putInt("TravelerTofunianSpawnDelay", this.tofunianSpawnDelay);
		compound.putInt("TravelerTofunianSpawnChance", this.tofunianSpawnChance);
		if (this.tofunianID != null)
			compound.putString("TravelerTofunianId", this.tofunianID.toString());
		return compound;
	}
}

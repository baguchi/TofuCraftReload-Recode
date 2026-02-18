package baguchi.tofucraft.world;

import baguchi.tofucraft.TofuCraftReload;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.storage.SavedDataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TofuData extends SavedData {
	public static final Codec<TofuData> CODEC = RecordCodecBuilder.create(
			p_400930_ -> p_400930_.group(
							Codec.INT.fieldOf("traveler_spawn_delay").forGetter(p_400933_ -> p_400933_.travelerSpawnDelay),
							Codec.FLOAT.fieldOf("traveler_spawn_chance").forGetter(p_400933_ -> p_400933_.travelerSpawnChance),
						BoundingBox.CODEC.listOf().fieldOf("beated_bounding_box").xmap(ArrayList::new, Function.identity()).forGetter(p_400933_ -> p_400933_.beatenDungeons)

					)
					.apply(p_400930_, TofuData::new)
	);
	private static final String IDENTIFIER = "tofucraft_world_data";
	public static final SavedDataType<TofuData> TYPE = new SavedDataType<>(
			Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "tofucraft_world_data"),
			TofuData::new,
			CODEC);

	private int travelerSpawnDelay;
	private float travelerSpawnChance;
	private static Map<Level, TofuData> dataMap = new HashMap<>();

	public ArrayList<BoundingBox> beatenDungeons = new ArrayList<>();

	public TofuData() {
		this(6000, 0.1F, Lists.newArrayList());
	}

	public TofuData(int travelerSpawnDelay, float travelerSpawnChance, ArrayList<BoundingBox> beatenDungeons) {
		this.travelerSpawnDelay = travelerSpawnDelay;
		this.travelerSpawnChance = travelerSpawnChance;
		this.beatenDungeons = beatenDungeons;
	}

	public static TofuData get(Level world) {
		if (world instanceof ServerLevel serverLevel) {
			ServerLevel overworld = world.getServer().getLevel(world.dimension());
			TofuData fromMap = dataMap.get(overworld);
			if (fromMap == null) {
				SavedDataStorage storage = overworld.getDataStorage();
				TofuData data = storage.computeIfAbsent(TYPE);
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

	public int getTravelerSpawnDelay() {
		return this.travelerSpawnDelay;
	}

	public void setTravelerSpawnDelay(int delay) {
		this.travelerSpawnDelay = delay;
		this.setDirty();
	}

	public float getTravelerSpawnChance() {
		return this.travelerSpawnChance;
	}

	public void setTravelerSpawnChance(float chance) {
		this.travelerSpawnChance = chance;
		this.setDirty();
	}


	public void addBeatenDungeons(BoundingBox box) {
		this.beatenDungeons.add(box);
		this.setDirty();
	}

	public List<BoundingBox> getBeatenDungeons() {
		return beatenDungeons;
	}
}
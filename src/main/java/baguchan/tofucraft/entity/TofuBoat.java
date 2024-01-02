package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class TofuBoat extends Boat {

	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(TofuBoat.class, EntityDataSerializers.INT);

	public TofuBoat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
		this.blocksBuilding = true;
	}

	public TofuBoat(Level level, double x, double y, double z) {
		this(TofuEntityTypes.TOFU_BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public TofuBoat.Type getTofuBoatType() {
		return TofuBoat.Type.byId(this.getEntityData().get(BOAT_TYPE));
	}

	@Override
	public Item getDropItem() {
		return switch (this.getTofuBoatType()) {
			case LEEK -> TofuItems.LEEK_BOAT.get();
			case LEEK_GREEN -> TofuItems.LEEK_GREEN_BOAT.get();
			case TOFU_STEM -> TofuItems.TOFU_STEM_BOAT.get();
		};
	}

	public void setTofuBoatType(TofuBoat.Type boatType) {
		this.getEntityData().set(BOAT_TYPE, boatType.ordinal());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(BOAT_TYPE, Type.TOFU_STEM.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
		tag.putString("Type", this.getTofuBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains("Type", 8)) {
			this.setTofuBoatType(TofuBoat.Type.getTypeFromString(tag.getString("Type")));
		}
	}
	public enum Type {
		LEEK(TofuBlocks.LEEK_PLANKS.get(), "leek"),
		LEEK_GREEN(TofuBlocks.LEEK_GREEN_PLANKS.get(), "leek_green"),
		TOFU_STEM(TofuBlocks.TOFU_STEM_PLANKS.get(), "tofu_stem");

		private final String name;
		private final Block block;

		Type(Block block, String name) {
			this.name = name;
			this.block = block;
		}

		public String getName() {
			return this.name;
		}

		public Block asPlank() {
			return this.block;
		}

		public String toString() {
			return this.name;
		}

		public static TofuBoat.Type byId(int id) {
			TofuBoat.Type[] types = values();
			if (id < 0 || id >= types.length) {
				id = 0;
			}

			return types[id];
		}

		public static TofuBoat.Type getTypeFromString(String nameIn) {
			TofuBoat.Type[] types = values();

			for (Type type : types) {
				if (type.getName().equals(nameIn)) {
					return type;
				}
			}

			return types[0];
		}
	}
}

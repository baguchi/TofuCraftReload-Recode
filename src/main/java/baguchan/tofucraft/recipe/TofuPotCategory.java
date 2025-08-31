package baguchan.tofucraft.recipe;

import net.minecraft.util.StringRepresentable;

public enum TofuPotCategory implements StringRepresentable {
	FAST_FOOD("fast_food"),
	MEAL("meal"),
	DRINK("meal"),
	MISC("misc");

	public static final EnumCodec<TofuPotCategory> CODEC = StringRepresentable.fromEnum(TofuPotCategory::values);
	private final String name;

	private TofuPotCategory(String name) {
		this.name = name;
	}


	public static TofuPotCategory get(String nameIn) {
		for (TofuPotCategory role : values()) {
			if (role.getSerializedName().equals(nameIn))
				return role;
		}
		return MISC;
	}


	@Override
	public String getSerializedName() {
		return this.name;
	}
}

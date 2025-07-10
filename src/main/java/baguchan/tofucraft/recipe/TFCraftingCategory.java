package baguchan.tofucraft.recipe;

import net.minecraft.util.StringRepresentable;

public enum TFCraftingCategory implements StringRepresentable {
	TF_MECHA("tf_mecha"),
	MISC("misc");

	public static final EnumCodec<TFCraftingCategory> CODEC = StringRepresentable.fromEnum(TFCraftingCategory::values);
	private final String name;

	private TFCraftingCategory(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}

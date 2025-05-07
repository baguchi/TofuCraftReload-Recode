package baguchi.tofucraft.recipe;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.crafting.ExtendedRecipeBookCategory;

public enum TofuPotCategory implements StringRepresentable, ExtendedRecipeBookCategory {
	FAST_FOOD("fast_food"),
	MEAL("meal"),
	DRINK("drink"),
	MISC("misc");

	public static final EnumCodec<TofuPotCategory> CODEC = StringRepresentable.fromEnum(TofuPotCategory::values);
	private final String name;

	private TofuPotCategory(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}

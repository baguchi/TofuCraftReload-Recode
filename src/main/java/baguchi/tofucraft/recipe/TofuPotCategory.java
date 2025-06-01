package baguchi.tofucraft.recipe;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.crafting.ExtendedRecipeBookCategory;

import java.util.function.IntFunction;

public enum TofuPotCategory implements StringRepresentable, ExtendedRecipeBookCategory {
	FAST_FOOD("fast_food", 0),
	MEAL("meal", 1),
	DRINK("drink", 2),
	MISC("misc", 3);

	public static final EnumCodec<TofuPotCategory> CODEC = StringRepresentable.fromEnum(TofuPotCategory::values);
	public static final IntFunction<TofuPotCategory> BY_ID = ByIdMap.continuous(TofuPotCategory::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

	public static final StreamCodec<ByteBuf, TofuPotCategory> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, TofuPotCategory::id);

	private final String name;
	private final int id;

	private TofuPotCategory(String name, int id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	private int id() {
		return this.id;
	}
}

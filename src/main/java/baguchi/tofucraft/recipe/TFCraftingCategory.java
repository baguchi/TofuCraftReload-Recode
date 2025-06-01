package baguchi.tofucraft.recipe;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum TFCraftingCategory implements StringRepresentable {
	TF_MECHA("tf_mecha", 0),
	MISC("misc", 1);

	public static final EnumCodec<TFCraftingCategory> CODEC = StringRepresentable.fromEnum(TFCraftingCategory::values);
	public static final IntFunction<TFCraftingCategory> BY_ID = ByIdMap.continuous(TFCraftingCategory::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

	public static final StreamCodec<ByteBuf, TFCraftingCategory> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, TFCraftingCategory::id);

	private final String name;
	private final int id;

	private TFCraftingCategory(String name, int id) {
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

package baguchi.tofucraft;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TofuEnumExtensions {


	public static Object TOFU_STEM_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> TofuBlocks.TOFU_STEM_PLANKS;
			case 1 -> "tofucraft:tofu_stem";
			case 2 -> TofuBlocks.TOFU_STEM_PLANKS;
			case 3 -> TofuItems.TOFU_STEM_CHEST_BOAT;
			case 4 -> (Supplier<Item>) () -> Items.STICK;
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object LEEK_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> TofuBlocks.LEEK_PLANKS;
			case 1 -> "tofucraft:leek";
			case 2 -> TofuItems.LEEK_BOAT;
			case 3 -> TofuItems.LEEK_CHEST_BOAT;
			case 4 -> (Supplier<Item>) () -> Items.STICK;
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object LEEK_GREEN_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> TofuBlocks.LEEK_GREEN_PLANKS;
			case 1 -> "tofucraft:leek_green";
			case 2 -> TofuItems.LEEK_GREEN_BOAT;
			case 3 -> TofuItems.LEEK_GREEN_CHEST_BOAT;
			case 4 -> (Supplier<Item>) () -> Items.STICK;
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
}
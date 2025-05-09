package baguchan.tofucraft;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.List;
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

	public static final EnumProxy<RecipeBookCategories> PROXY_COOKING_SEARCH = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS))
	);
	public static final EnumProxy<RecipeBookCategories> PROXY_COOKING_FAST_FOODS = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(TofuItems.YUDOFU.get()))
	);
	public static final EnumProxy<RecipeBookCategories> PROXY_COOKING_MEALS = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(TofuItems.NIKUJAGA.get()))
	);
	public static final EnumProxy<RecipeBookCategories> PROXY_COOKING_DRINKS = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(TofuItems.SOYMILK_RAMUNE.get()))
	);
	public static final EnumProxy<RecipeBookCategories> PROXY_COOKING_MISC = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(TofuItems.SOY_CHEESE), new ItemStack(TofuItems.PUDDING_SOYMILK))
	);

	public static final EnumProxy<RecipeBookCategories> PROXY_TF_SEARCH = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS))
	);

	public static final EnumProxy<RecipeBookCategories> PROXY_TF_MISC = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(TofuItems.TF_CIRCUIT))
	);
	public static final EnumProxy<RecipeBookCategories> PROXY_TF_MECHA = new EnumProxy<>(
			RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(TofuItems.TF_BATTERY))
	);
}
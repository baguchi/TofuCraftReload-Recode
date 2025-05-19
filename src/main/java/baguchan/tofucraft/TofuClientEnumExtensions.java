package baguchan.tofucraft;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TofuClientEnumExtensions {
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
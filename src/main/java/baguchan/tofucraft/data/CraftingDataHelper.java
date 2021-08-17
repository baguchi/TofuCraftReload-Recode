package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class CraftingDataHelper extends RecipeProvider {
	public CraftingDataHelper(DataGenerator generator) {
		super(generator);
	}

	protected final Ingredient itemWithNBT(RegistryObject<? extends ItemLike> item, Consumer<CompoundTag> nbtSetter) {
		return itemWithNBT(item.get(), nbtSetter);
	}

	protected final Ingredient itemWithNBT(ItemLike item, Consumer<CompoundTag> nbtSetter) {
		ItemStack stack = new ItemStack(item);

		CompoundTag nbt = new CompoundTag();
		nbtSetter.accept(nbt);
		stack.setTag(nbt);

		try {
			Constructor<NBTIngredient> constructor = NBTIngredient.class.getDeclaredConstructor(ItemStack.class);

			constructor.setAccessible(true);

			return constructor.newInstance(stack);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// This will just defer to the regular Ingredient method instead of some overridden thing, but whatever.
		// Forge PRs are too slow to even feel motivated about fixing it on the Forge end.
		return Ingredient.of(stack);
	}

	protected final Ingredient multipleIngredients(Ingredient... ingredientArray) {
		List<Ingredient> ingredientList = ImmutableList.copyOf(ingredientArray);

		try {
			Constructor<CompoundIngredient> constructor = CompoundIngredient.class.getDeclaredConstructor(List.class);

			constructor.setAccessible(true);

			return constructor.newInstance(ingredientList);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// This will just defer to the regular Ingredient method instead of some overridden thing, but whatever.
		// Forge PRs are too slow to even feel motivated about fixing it on the Forge end.
		return Ingredient.merge(ingredientList);
	}

	protected final void stairsBlock(Consumer<FinishedRecipe> consumer, ResourceLocation loc, Supplier<? extends Block> result, Supplier<? extends Block> criteria, ItemLike... ingredients) {
		ShapedRecipeBuilder.shaped(result.get(), 8)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(ingredients))
				.unlockedBy("has_item", has(criteria.get()))
				.save(consumer, loc);
	}

	protected final void stairsRightBlock(Consumer<FinishedRecipe> consumer, ResourceLocation loc, Supplier<? extends Block> result, Supplier<? extends Block> criteria, ItemLike... ingredients) {
		ShapedRecipeBuilder.shaped(result.get(), 8)
				.pattern("###")
				.pattern(" ##")
				.pattern("  #")
				.define('#', Ingredient.of(ingredients))
				.unlockedBy("has_item", has(criteria.get()))
				.save(consumer, loc);
	}

	protected final void reverseStairsBlock(Consumer<FinishedRecipe> consumer, ResourceLocation loc, Supplier<? extends Block> result, Supplier<? extends Block> criteria, ItemLike ingredient) {
		ShapelessRecipeBuilder.shapeless(result.get(), 3)
				.requires(ingredient)
				.requires(ingredient)
				.requires(ingredient)
				.requires(ingredient)
				.unlockedBy("has_item", has(criteria.get()))
				.save(consumer, loc);
	}

	protected final void helmetItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("###")
				.pattern("# #")
				.define('#', material)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void chestplateItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("# #")
				.pattern("###")
				.pattern("###")
				.define('#', material)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void leggingsItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("###")
				.pattern("# #")
				.pattern("# #")
				.define('#', material)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void bootsItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("# #")
				.pattern("# #")
				.define('#', material)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void pickaxeItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material, Tag.Named<Item> handle) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("###")
				.pattern(" X ")
				.pattern(" X ")
				.define('#', material)
				.define('X', handle)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void swordItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material, Tag.Named<Item> handle) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("#")
				.pattern("#")
				.pattern("X")
				.define('#', material)
				.define('X', handle)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void axeItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material, Tag.Named<Item> handle) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("##")
				.pattern("#X")
				.pattern(" X")
				.define('#', material)
				.define('X', handle)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void shovelItem(Consumer<FinishedRecipe> consumer, String name, Item result, Item material, Tag.Named<Item> handle) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('#', material)
				.define('X', handle)
				.unlockedBy("has_item", has(material))
				.save(consumer, locEquip(name));
	}

	protected final void tofuBlockItem(Consumer<FinishedRecipe> consumer, Item result, Item material) {
		ShapedRecipeBuilder.shaped(result)
				.pattern("##")
				.pattern("##")
				.define('#', material)
				.unlockedBy("has_item", has(material))
				.save(consumer);
	}


	protected final ResourceLocation locEquip(String name) {
		return TofuCraftReload.prefix("equipment/" + name);
	}
}
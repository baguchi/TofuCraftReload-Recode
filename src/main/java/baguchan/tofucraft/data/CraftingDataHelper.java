package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Constructor;
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

	protected final void foodCooking(RegistryObject<? extends ItemLike> material, RegistryObject<? extends ItemLike> result, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + result.get().asItem().getRegistryName().getPath()));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + result.get().asItem().getRegistryName().getPath()));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material.get()), result.get(), xp, 600).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("campfire_cooking_" + result.get().asItem().getRegistryName().getPath()));
	}

	protected final void foodCooking(RegistryObject<? extends ItemLike> material, RegistryObject<? extends ItemLike> result, float xp, Consumer<FinishedRecipe> consumer, String recipeName) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + recipeName));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + recipeName));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material.get()), result.get(), xp, 600).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("campfire_cooking_" + recipeName));
	}

	public static void cuttingRecipe(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> cuttingItem, RegistryObject<? extends ItemLike> result) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(cuttingItem.get()), result.get()).unlockedBy("has_item", has(cuttingItem.get())).save(consumer, TofuCraftReload.prefix("cutting_" + result.get().asItem().getRegistryName().getPath()));
	}

	public static void tofuDiamondSmithing(Consumer<FinishedRecipe> consumer, ItemLike smithItem, RegistryObject<Item> result) {
		UpgradeRecipeBuilder.smithing(Ingredient.of(smithItem), Ingredient.of(TofuBlocks.DIAMONDTOFU.get()), result.get()).unlocks("has_tofudiamond", has(TofuBlocks.DIAMONDTOFU.get())).save(consumer, TofuCraftReload.prefix("smithing_" + result.get().getRegistryName().getPath()));
	}

	protected final void helmetItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("###")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void chestplateItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("# #")
				.pattern("###")
				.pattern("###")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void leggingsItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("###")
				.pattern("# #")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void bootsItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("# #")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void pickaxeItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("###")
				.pattern(" X ")
				.pattern(" X ")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void swordItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("#")
				.pattern("#")
				.pattern("X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void axeItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern("#X")
				.pattern(" X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void shovelItem(Consumer<FinishedRecipe> consumer, String name, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void tofuBlockItem(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	protected final void tofuBlockItem(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material, String name) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, TofuCraftReload.prefix(name));
	}

	protected final void ladderItem(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get(), 6)
				.pattern("# #")
				.pattern("###")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	protected final void decorationTofuBlockItem(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> result, RegistryObject<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	public void makeStairs(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> stairsOut, RegistryObject<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(stairsOut.get(), 4)
				.pattern("M  ")
				.pattern("MM ")
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeStairsCraftingOrCutting(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> stairsOut, RegistryObject<? extends ItemLike> blockIn) {
		makeStairs(consumer, stairsOut, blockIn);
		cuttingRecipe(consumer, blockIn, stairsOut);
	}

	public void makeSlab(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> slabOut, RegistryObject<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(slabOut.get(), 6)
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeSlabCraftingOrCutting(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> slabOut, RegistryObject<? extends ItemLike> blockIn) {
		makeSlab(consumer, slabOut, blockIn);
		cuttingRecipe(consumer, blockIn, slabOut);
	}

	public void makeFence(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> fenceOut, RegistryObject<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(fenceOut.get(), 6)
				.pattern("MMM")
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeTorch(Consumer<FinishedRecipe> consumer, RegistryObject<? extends ItemLike> torchOut, RegistryObject<? extends ItemLike> itemIn) {
		ShapedRecipeBuilder.shaped(torchOut.get(), 4)
				.pattern("C")
				.pattern("M")
				.define('C', ItemTags.COALS)
				.define('M', itemIn.get())
				.unlockedBy("has_item", has(itemIn.get())).save(consumer);
	}


	protected final ResourceLocation locEquip(String name) {
		return TofuCraftReload.prefix("equipment/" + name);
	}
}
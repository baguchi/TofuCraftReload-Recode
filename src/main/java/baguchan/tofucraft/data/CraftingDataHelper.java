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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Constructor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class CraftingDataHelper extends RecipeProvider {
	public CraftingDataHelper(DataGenerator generator) {
		super(generator);
	}

	protected final Ingredient itemWithNBT(Supplier<? extends ItemLike> item, Consumer<CompoundTag> nbtSetter) {
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

	protected final void foodCookingButNoCampfire(Supplier<? extends ItemLike> material, Supplier<? extends ItemLike> result, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + ForgeRegistries.ITEMS.getKey(result.get().asItem()).getPath()));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + ForgeRegistries.ITEMS.getKey(result.get().asItem()).getPath()));
	}

	protected final void foodCooking(Supplier<? extends ItemLike> material, Supplier<? extends ItemLike> result, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + result.get().asItem().getRegistryName().getPath()));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + result.get().asItem().getRegistryName().getPath()));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material.get()), result.get(), xp, 600).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("campfire_cooking_" + result.get().asItem().getRegistryName().getPath()));
	}

	protected final void foodCooking(Supplier<? extends ItemLike> material, Supplier<? extends ItemLike> result, float xp, Consumer<FinishedRecipe> consumer, String recipeName) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + recipeName));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + recipeName));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material.get()), result.get(), xp, 600).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("campfire_cooking_" + recipeName));
	}

	public static void cuttingRecipe(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> cuttingItem, Supplier<? extends ItemLike> result, int count) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(cuttingItem.get()), result.get(), count).unlockedBy("has_item", has(cuttingItem.get())).save(consumer, TofuCraftReload.prefix("cutting_" + ForgeRegistries.ITEMS.getKey(result.get().asItem()).getPath()));
	}

	public static void tofuDiamondSmithing(Consumer<FinishedRecipe> consumer, ItemLike smithItem, Supplier<Item> result) {
		UpgradeRecipeBuilder.smithing(Ingredient.of(smithItem), Ingredient.of(TofuBlocks.DIAMONDTOFU.get()), result.get()).unlocks("has_tofudiamond", has(TofuBlocks.DIAMONDTOFU.get())).save(consumer, TofuCraftReload.prefix("smithing_" + result.get().getRegistryName().getPath()));
	}

	protected final void helmetItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("###")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void chestplateItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("# #")
				.pattern("###")
				.pattern("###")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void leggingsItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("###")
				.pattern("# #")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void bootsItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("# #")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void pickaxeItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("###")
				.pattern(" X ")
				.pattern(" X ")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void swordItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("#")
				.pattern("#")
				.pattern("X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void axeItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern("#X")
				.pattern(" X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void shovelItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void tofuBlockItem(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	protected final void tofuBlockItem(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, String name) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, TofuCraftReload.prefix(name));
	}

	protected final void ladderItem(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get(), 6)
				.pattern("# #")
				.pattern("###")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	protected final void decorationTofuBlockItem(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(result.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	public void makeStairs(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> stairsOut, Supplier<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(stairsOut.get(), 4)
				.pattern("M  ")
				.pattern("MM ")
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeStairsCraftingOrCutting(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> stairsOut, Supplier<? extends ItemLike> blockIn) {
		makeStairs(consumer, stairsOut, blockIn);
		cuttingRecipe(consumer, blockIn, stairsOut, 2);
	}

	public void makeSlab(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> slabOut, Supplier<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(slabOut.get(), 6)
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeSlabCraftingOrCutting(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> slabOut, Supplier<? extends ItemLike> blockIn) {
		makeSlab(consumer, slabOut, blockIn);
		cuttingRecipe(consumer, blockIn, slabOut, 2);
	}

	public void makeFence(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> fenceOut, Supplier<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(fenceOut.get(), 6)
				.pattern("MMM")
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	protected final void hoeItem(Consumer<FinishedRecipe> consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(result.get())
				.pattern("##")
				.pattern(" X")
				.pattern(" X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	public void makeTorch(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> torchOut, Supplier<? extends ItemLike> itemIn) {
		ShapedRecipeBuilder.shaped(torchOut.get(), 4)
				.pattern("C")
				.pattern("M")
				.define('C', ItemTags.COALS)
				.define('M', itemIn.get())
				.unlockedBy("has_item", has(itemIn.get())).save(consumer);
	}

	public void makeDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
		ShapedRecipeBuilder.shaped(doorOut.get(), 3)
				.pattern("PP")
				.pattern("PP")
				.pattern("PP")
				.define('P', plankIn.get())
				.unlockedBy("has_" + plankIn.get().getRegistryName().getPath(), has(plankIn.get())).save(consumer);
	}

	public void makeTrapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends Block> trapdoorOut, Supplier<? extends ItemLike> plankIn) {
		ShapedRecipeBuilder.shaped(trapdoorOut.get(), 2)
				.pattern("PPP")
				.pattern("PPP")
				.define('P', plankIn.get())
				.unlockedBy("has_" + plankIn.get().asItem().getRegistryName().getPath(), has(plankIn.get())).save(consumer);
	}


	protected final ResourceLocation locEquip(String name) {
		return TofuCraftReload.prefix("equipment/" + name);
	}
}
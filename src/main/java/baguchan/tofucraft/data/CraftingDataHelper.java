package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.SignBlock;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.PartialNBTIngredient;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class CraftingDataHelper extends RecipeProvider {
	public CraftingDataHelper(PackOutput generator, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(generator, lookupProvider);
	}

	public ShapedRecipeBuilder makeSign(Supplier<? extends SignBlock> signOut, Supplier<? extends Block> planksIn) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, signOut.get(), 3)
				.pattern("PPP")
				.pattern("PPP")
				.pattern(" / ")
				.define('P', planksIn.get())
				.define('/', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(planksIn.get()).getPath(), has(planksIn.get()));
	}

	protected ShapedRecipeBuilder makeHangingSign(Supplier<? extends CeilingHangingSignBlock> result, Supplier<? extends Block> log) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
				.pattern("| |")
				.pattern("###")
				.pattern("###")
				.define('#', log.get())
				.define('|', Items.CHAIN)
				.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(log.get()).getPath(), has(log.get()));
	}


	public final PartialNBTIngredient potion(Potion potion) {
		return PartialNBTIngredient.of(Items.POTION, Util.make(() -> {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("Potion", BuiltInRegistries.POTION.getKey(potion).toString());
			return nbt;
		}));
	}

	protected final void foodCooking(Supplier<? extends ItemLike> material, Supplier<? extends ItemLike> result, float xp, RecipeOutput consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath()));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath()));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 600).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("campfire_cooking_" + BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath()));
	}

	protected final void foodCookingButNoCampfire(Supplier<? extends ItemLike> material, Supplier<? extends ItemLike> result, float xp, RecipeOutput consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath()));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath()));
	}

	protected final void foodCooking(Supplier<? extends ItemLike> material, Supplier<? extends ItemLike> result, float xp, RecipeOutput consumer, String recipeName) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 200).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smelting_" + recipeName));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 100).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("smoking_" + recipeName));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material.get()), RecipeCategory.FOOD, result.get(), xp, 600).unlockedBy("has_item", has(material.get())).save(consumer, TofuCraftReload.prefix("campfire_cooking_" + recipeName));
	}

	public static void cuttingRecipe(RecipeOutput consumer, Supplier<? extends ItemLike> cuttingItem, Supplier<? extends ItemLike> result, int count) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(cuttingItem.get()), RecipeCategory.BUILDING_BLOCKS, result.get(), count).unlockedBy("has_item", has(cuttingItem.get())).save(consumer, TofuCraftReload.prefix("cutting_" + getItemName(result.get()) + "_from_" + getItemName(cuttingItem.get())));
	}

	public static void tofuDiamondSmithing(RecipeOutput consumer, ItemLike smithItem, RecipeCategory recipeCategory, Supplier<? extends Item> result) {
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(smithItem), Ingredient.of(TofuBlocks.DIAMONDTOFU.get()), recipeCategory, result.get()).unlocks("has_item", has(TofuBlocks.DIAMONDTOFU.get())).save(consumer, TofuCraftReload.prefix(BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath() + "_smithing"));
	}

	public static void zundaSmithing(RecipeOutput consumer, ItemLike smithItem, RecipeCategory recipeCategory, Supplier<? extends Item> result) {
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(smithItem), Ingredient.of(TofuItems.ZUNDAMA.get()), recipeCategory, result.get()).unlocks("has_item", has(TofuItems.ZUNDAMA.get())).save(consumer, TofuCraftReload.prefix(BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath() + "_smithing"));
	}

	public static void sculkSmithing(RecipeOutput consumer, ItemLike smithItem, RecipeCategory recipeCategory, Supplier<? extends Item> result) {
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(smithItem), Ingredient.of(TofuItems.SOY_SCULK_BONE.get()), recipeCategory, result.get()).unlocks("has_item", has(TofuItems.SOY_SCULK_BONE.get())).save(consumer, TofuCraftReload.prefix(BuiltInRegistries.ITEM.getKey(result.get().asItem()).getPath() + "_smithing"));
	}

	protected final void helmetItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
				.pattern("###")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void chestplateItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
				.pattern("# #")
				.pattern("###")
				.pattern("###")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void leggingsItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
				.pattern("###")
				.pattern("# #")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void bootsItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
				.pattern("# #")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void pickaxeItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
				.pattern("###")
				.pattern(" X ")
				.pattern(" X ")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void swordItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
				.pattern("#")
				.pattern("#")
				.pattern("X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void axeItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
				.pattern("##")
				.pattern("#X")
				.pattern(" X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void shovelItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void hoeItem(RecipeOutput consumer, String name, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, TagKey<Item> handle) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
				.pattern("##")
				.pattern(" X")
				.pattern(" X")
				.define('#', material.get())
				.define('X', handle)
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, locEquip(name));
	}

	protected final void tofuBlockItem(RecipeOutput consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get())
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	protected final void tofuBlockItem(RecipeOutput consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material, String name) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get())
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer, TofuCraftReload.prefix(name));
	}

	protected final void ladderItem(RecipeOutput consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
				.pattern("# #")
				.pattern("###")
				.pattern("# #")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	protected final void decorationTofuBlockItem(RecipeOutput consumer, Supplier<? extends ItemLike> result, Supplier<? extends ItemLike> material) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', material.get())
				.unlockedBy("has_item", has(material.get()))
				.save(consumer);
	}

	public void makeStairs(RecipeOutput consumer, Supplier<? extends ItemLike> stairsOut, Supplier<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
				.pattern("M  ")
				.pattern("MM ")
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeStairsCraftingOrCutting(RecipeOutput consumer, Supplier<? extends ItemLike> stairsOut, Supplier<? extends ItemLike> blockIn) {
		makeStairs(consumer, stairsOut, blockIn);
		cuttingRecipe(consumer, blockIn, stairsOut, 2);
	}

	public void makeSlab(RecipeOutput consumer, Supplier<? extends ItemLike> slabOut, Supplier<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeSlabCraftingOrCutting(RecipeOutput consumer, Supplier<? extends ItemLike> slabOut, Supplier<? extends ItemLike> blockIn) {
		makeSlab(consumer, slabOut, blockIn);
		cuttingRecipe(consumer, blockIn, slabOut, 2);
	}

	public void makeSolidFence(RecipeOutput consumer, Supplier<? extends ItemLike> fenceOut, Supplier<? extends ItemLike> blockIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fenceOut.get(), 6)
				.pattern("MMM")
				.pattern("MMM")
				.define('M', blockIn.get())
				.unlockedBy("has_item", has(blockIn.get())).save(consumer);
	}

	public void makeTorch(RecipeOutput consumer, Supplier<? extends ItemLike> torchOut, Supplier<? extends ItemLike> itemIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, torchOut.get(), 4)
				.pattern("C")
				.pattern("M")
				.define('C', ItemTags.COALS)
				.define('M', itemIn.get())
				.unlockedBy("has_item", has(itemIn.get())).save(consumer);
	}

	public void makeDoor(RecipeOutput consumer, Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, doorOut.get(), 3)
				.pattern("PP")
				.pattern("PP")
				.pattern("PP")
				.define('P', plankIn.get())
				.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(plankIn.get().asItem()).getPath(), has(plankIn.get())).save(consumer);
	}

	public void makeTrapdoor(RecipeOutput consumer, Supplier<? extends Block> trapdoorOut, Supplier<? extends ItemLike> plankIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, trapdoorOut.get(), 2)
				.pattern("PPP")
				.pattern("PPP")
				.define('P', plankIn.get())
				.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(plankIn.get().asItem()).getPath(), has(plankIn.get())).save(consumer);
	}

	public void makeWoodFence(RecipeOutput consumer, Block fenceOut, Block blockIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fenceOut, 3)
				.pattern("MSM")
				.pattern("MSM")
				.define('M', blockIn)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(blockIn).getPath(), has(blockIn)).save(consumer);
	}

	public void makeFenceGate(RecipeOutput consumer, Block fenceOut, Block blockIn) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fenceOut)
				.pattern("SMS")
				.pattern("SMS")
				.define('M', blockIn)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_" + BuiltInRegistries.BLOCK.getKey(blockIn).getPath(), has(blockIn)).save(consumer);
	}


	protected final ResourceLocation locEquip(String name) {
		return TofuCraftReload.prefix("equipment/" + name);
	}
}
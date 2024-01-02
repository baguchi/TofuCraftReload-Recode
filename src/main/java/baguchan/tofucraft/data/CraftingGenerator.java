package baguchan.tofucraft.data;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static baguchan.tofucraft.TofuCraftReload.prefix;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(PackOutput generator, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(generator, lookupProvider);
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {
		/*helmetItem(consumer, "tofu_diamond_helmet", TofuItems.ARMOR_TOFU_DIAMONDHELMET, TofuBlocks.DIAMONDTOFU);
		chestplateItem(consumer, "tofu_diamond_chestplate", TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, TofuBlocks.DIAMONDTOFU);
		leggingsItem(consumer, "tofu_diamond_leggings", TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, TofuBlocks.DIAMONDTOFU);
		bootsItem(consumer, "tofu_diamond_boots", TofuItems.ARMOR_TOFU_DIAMONDBOOTS, TofuBlocks.DIAMONDTOFU);
		*/

		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_SWORD.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_SWORD);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_PICKAXE.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_PICKAXE);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_AXE.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_AXE);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_SHOVEL.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_SHOVEL);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_HOE.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_HOE);


		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_HELMET.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_HELMET);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_CHESTPLATE.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_CHESTPLATE);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_LEGGINGS.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_LEGGINGS);
		tofuDiamondSmithing(consumer, TofuItems.TOFU_METAL_BOOTS.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_BOOTS);

		sculkSmithing(consumer, TofuItems.TOFU_DIAMOND_HELMET.get(), RecipeCategory.COMBAT, TofuItems.SCULK_BONE_HELMET);
		sculkSmithing(consumer, TofuItems.TOFU_DIAMOND_CHESTPLATE.get(), RecipeCategory.COMBAT, TofuItems.SCULK_BONE_CHESTPLATE);
		sculkSmithing(consumer, TofuItems.TOFU_DIAMOND_LEGGINGS.get(), RecipeCategory.COMBAT, TofuItems.SCULK_BONE_LEGGINGS);
		sculkSmithing(consumer, TofuItems.TOFU_DIAMOND_BOOTS.get(), RecipeCategory.COMBAT, TofuItems.SCULK_BONE_BOOTS);

		zundaSmithing(consumer, Items.BOW, RecipeCategory.COMBAT, TofuItems.ZUNDA_BOW);


		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TofuBlocks.TOFU_METAL_LANTERN.get(), 1)
				.pattern(" M ")
				.pattern("MTM")
				.pattern(" M ")
				.define('M', TofuItems.TOFUMETAL.get())
				.define('T', Items.TORCH)
				.unlockedBy("has_item", has(TofuItems.TOFUMETAL.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TofuBlocks.TOFU_METAL_SOUL_LANTERN.get(), 1)
				.pattern(" M ")
				.pattern("MSM")
				.pattern(" M ")
				.define('M', TofuItems.TOFUMETAL.get())
				.define('S', Items.SOUL_TORCH)
				.unlockedBy("has_item", has(TofuItems.TOFUMETAL.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TofuBlocks.TOFU_METAL_CHAIN.get(), 4)
				.pattern("M")
				.pattern("M")
				.pattern("M")
				.define('M', TofuItems.TOFUMETAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFUMETAL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, TofuItems.TOFU_STEM_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.TOFU_STEM_PLANKS.get())
				.unlockedBy("has_item", has(TofuBlocks.TOFU_STEM_PLANKS.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, TofuItems.LEEK_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.LEEK_PLANKS.get())
				.unlockedBy("has_item", has(TofuBlocks.LEEK_PLANKS.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, TofuItems.LEEK_GREEN_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.LEEK_GREEN_PLANKS.get())
				.unlockedBy("has_item", has(TofuBlocks.LEEK_GREEN_PLANKS.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, TofuItems.TOFU_STEM_CHEST_BOAT.get(), 1)
				.requires(TofuItems.TOFU_STEM_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.TOFU_STEM_BOAT.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, TofuItems.LEEK_CHEST_BOAT.get(), 1)
				.requires(TofuItems.LEEK_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.LEEK_BOAT.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, TofuItems.LEEK_GREEN_CHEST_BOAT.get(), 1)
				.requires(TofuItems.LEEK_GREEN_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.LEEK_GREEN_BOAT.get()))
				.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get(), 2)
				.pattern("GTG")
				.pattern("G#G")
				.pattern("GDG")
				.define('D', TofuItems.TOFUDIAMOND.get())
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('G', Items.DIAMOND)
				.define('#', TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get()))
				.save(consumer, prefix("copy_tofu_template"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE.get(), 2)
				.pattern("B#B")
				.pattern("BZB")
				.pattern("BDB")
				.define('Z', TofuItems.ZUNDAMA.get())
				.define('B', Items.DIAMOND)
				.define('D', TofuItems.TOFUDIAMOND.get())
				.define('#', TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE.get()))
				.save(consumer, prefix("copy_zunda_template"));

		/*swordItem(consumer, "tofu_diamond_sword", TofuItems.TOFU_DIAMOND_SWORD, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_diamond_pickaxe", TofuItems.TOFU_DIAMOND_PICKAXE, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_diamond_axe", TofuItems.TOFU_DIAMOND_AXE, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_diamond_shovel", TofuItems.TOFU_DIAMOND_SHOVEL, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		*/
		helmetItem(consumer, "tofu_metal_helmet", TofuItems.TOFU_METAL_HELMET, TofuBlocks.METALTOFU);
		chestplateItem(consumer, "tofu_metal_chestplate", TofuItems.TOFU_METAL_CHESTPLATE, TofuBlocks.METALTOFU);
		leggingsItem(consumer, "tofu_metal_leggings", TofuItems.TOFU_METAL_LEGGINGS, TofuBlocks.METALTOFU);
		bootsItem(consumer, "tofu_metal_boots", TofuItems.TOFU_METAL_BOOTS, TofuBlocks.METALTOFU);

		swordItem(consumer, "tofu_metal_sword", TofuItems.TOFU_METAL_SWORD, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_metal_pickaxe", TofuItems.TOFU_METAL_PICKAXE, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_metal_axe", TofuItems.TOFU_METAL_AXE, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_metal_shovel", TofuItems.TOFU_METAL_SHOVEL, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		hoeItem(consumer, "tofu_metal_hoe", TofuItems.TOFU_METAL_HOE, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);

		helmetItem(consumer, "tofu_solid_helmet", TofuItems.ARMOR_TOFU_SOLIDHELMET, TofuBlocks.ISHITOFU);
		chestplateItem(consumer, "tofu_solid_chestplate", TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE, TofuBlocks.ISHITOFU);
		leggingsItem(consumer, "tofu_solid_leggings", TofuItems.ARMOR_TOFU_SOLIDLEGGINGS, TofuBlocks.ISHITOFU);
		bootsItem(consumer, "tofu_solid_boots", TofuItems.ARMOR_TOFU_SOLIDBOOTS, TofuBlocks.ISHITOFU);

		swordItem(consumer, "tofu_solid_sword", TofuItems.TOFU_SOLID_SWORD, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_solid_pickaxe", TofuItems.TOFU_SOLID_PICKAXE, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_solid_axe", TofuItems.TOFU_SOLID_AXE, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_solid_shovel", TofuItems.TOFU_SOLID_SHOVEL, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		hoeItem(consumer, "tofu_solid_hoe", TofuItems.TOFU_SOLID_HOE, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);


		helmetItem(consumer, "tofu_momen_helmet", TofuItems.TOFU_MOMEN_HELMET, TofuBlocks.MOMENTOFU);
		chestplateItem(consumer, "tofu_momen_chestplate", TofuItems.TOFU_MOMEN_CHESTPLATE, TofuBlocks.MOMENTOFU);
		leggingsItem(consumer, "tofu_momen_leggings", TofuItems.TOFU_MOMEN_LEGGINGS, TofuBlocks.MOMENTOFU);
		bootsItem(consumer, "tofu_momen_boots", TofuItems.TOFU_MOMEN_BOOTS, TofuBlocks.MOMENTOFU);

		swordItem(consumer, "tofu_momen_sword", TofuItems.TOFU_MOMEN_SWORD, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_momen_pickaxe", TofuItems.TOFU_MOMEN_PICKAXE, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_momen_axe", TofuItems.TOFU_MOMEN_AXE, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_momen_shovel", TofuItems.TOFU_MOMEN_SHOVEL, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		hoeItem(consumer, "tofu_momen_hoe", TofuItems.TOFU_MOMEN_HOE, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.TOFU_METAL_SHEARS.get())
				.pattern(" #")
				.pattern("# ")
				.define('#', TofuItems.TOFUMETAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFUMETAL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.BUGLE.get())
				.pattern("  #")
				.pattern("###")
				.define('#', Items.IRON_INGOT)
				.unlockedBy("has_item", has(Items.IRON_INGOT))
				.save(consumer);


		helmetItem(consumer, "tofu_kinu_helmet", TofuItems.TOFU_KINU_HELMET, TofuBlocks.KINUTOFU);
		chestplateItem(consumer, "tofu_kinu_chestplate", TofuItems.TOFU_KINU_CHESTPLATE, TofuBlocks.KINUTOFU);
		leggingsItem(consumer, "tofu_kinu_leggings", TofuItems.TOFU_KINU_LEGGINGS, TofuBlocks.KINUTOFU);
		bootsItem(consumer, "tofu_kinu_boots", TofuItems.TOFU_KINU_BOOTS, TofuBlocks.KINUTOFU);

		swordItem(consumer, "tofu_kinu_sword", TofuItems.TOFU_KINU_SWORD, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_kinu_pickaxe", TofuItems.TOFU_KINU_PICKAXE, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_kinu_axe", TofuItems.TOFU_KINU_AXE, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_kinu_shovel", TofuItems.TOFU_KINU_SHOVEL, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		hoeItem(consumer, "tofu_kinu_hoe", TofuItems.TOFU_KINU_HOE, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);


		ladderItem(consumer, TofuBlocks.TOFULADDER_KINU, TofuItems.TOFUKINU);
		ladderItem(consumer, TofuBlocks.TOFULADDER_MOMEN, TofuItems.TOFUMOMEN);
		ladderItem(consumer, TofuBlocks.TOFULADDER_ISHI, TofuItems.TOFUISHI);
		ladderItem(consumer, TofuBlocks.TOFULADDER_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		ladderItem(consumer, TofuBlocks.TOFULADDER_METAL, TofuItems.TOFUMETAL);
		ladderItem(consumer, TofuBlocks.TOFULADDER_GRILLED, TofuItems.TOFUGRILLED);
		ladderItem(consumer, TofuBlocks.TOFULADDER_ZUNDA, TofuItems.TOFUZUNDA);

		tofuBlockItem(consumer, TofuBlocks.MOMENTOFU, TofuItems.TOFUKINU, "tofumomen_from_kinu");
		tofuBlockItem(consumer, TofuBlocks.MOMENTOFU, TofuItems.TOFUMOMEN);
		tofuBlockItem(consumer, TofuBlocks.ISHITOFU, TofuItems.TOFUISHI);
		decorationTofuBlockItem(consumer, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU);
		decorationTofuBlockItem(consumer, TofuBlocks.ISHITOFU_SMOOTH_BRICK, TofuBlocks.ISHITOFU_BRICK);
		tofuBlockItem(consumer, TofuBlocks.METALTOFU, TofuItems.TOFUMETAL);
		tofuBlockItem(consumer, TofuBlocks.DIAMONDTOFU, TofuItems.TOFUDIAMOND);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.TOFU_GEM_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.TOFUGEM.get(), 9)
				.requires(TofuBlocks.TOFU_GEM_BLOCK.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer, prefix("revert_to_tofu_gem"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.ZUNDAMA_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.ZUNDAMA.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDAMA.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.ZUNDAMA.get(), 9)
				.requires(TofuBlocks.ZUNDAMA_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.ZUNDAMA_BLOCK.get()))
				.save(consumer, prefix("revert_to_zundama"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuItems.TOFUDIAMOND.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFUDIAMOND_NUGGET.get())
				.unlockedBy("has_item", has(TofuItems.TOFUDIAMOND_NUGGET.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.TOFUDIAMOND_NUGGET.get(), 9)
				.requires(TofuItems.TOFUDIAMOND.get())
				.unlockedBy("has_item", has(TofuItems.TOFUDIAMOND.get()))
				.save(consumer);

		tofuBlockItem(consumer, TofuBlocks.GRILLEDTOFU, TofuItems.TOFUGRILLED);
		tofuBlockItem(consumer, TofuBlocks.ZUNDATOFU, TofuItems.TOFUZUNDA);
		tofuBlockItem(consumer, TofuBlocks.MISOTOFU, TofuItems.TOFUMISO);
		tofuBlockItem(consumer, TofuBlocks.DRIEDTOFU, TofuItems.TOFUDRIED);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUEGG.get(), 4)
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.YUDOFU.get(), 1)
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.requires(Items.BOWL)
				.requires(potion(Potions.WATER))
				.unlockedBy("has_item", has(TofuItems.TOFUKINU.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.EDAMAME_RICE.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.EDAMAME.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.EDAMAME.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.BOTTLE_SOYOIL.get(), 1)
				.requires(Ingredient.of(TofuTags.Items.SOYBEAN))
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUFRIED.get(), 1)
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(consumer);

		SimpleCookingRecipeBuilder.smoking(Ingredient.of(TofuItems.TOFUDRIED.get()), RecipeCategory.FOOD, TofuItems.TOFUSMOKE.get(), 0.1F, 600)
				.unlockedBy("has_item", has(TofuItems.TOFUDRIED.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUFRIED_POUCH.get(), 1)
				.requires(TofuItems.STARCH.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.AGEDASHI_TOFU.get(), 1)
				.requires(TofuItems.TOFUFRIED_POUCH.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFUFRIED_POUCH.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.OAGE.get(), 4)
				.requires(TofuBlocks.TOFUSLAB_MOMEN.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(consumer);

		foodCooking(TofuItems.TOFUISHI, TofuItems.TOFU_STEAK, 0.1F, consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFU_MINCED.get(), 1)
				.requires(TofuItems.TOFUMOMEN.get())
				.requires(TofuItems.ROLLINGPIN.get())
				.unlockedBy("has_item", has(TofuItems.ROLLINGPIN.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.STARCH_RAW.get(), 1)
				.requires(TofuItems.MINCEDPOTATO.get())
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(TofuItems.MINCEDPOTATO.get()))
				.save(consumer);

		foodCookingButNoCampfire(TofuItems.STARCH_RAW, TofuItems.STARCH, 0.1F, consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.GELATINRAW.get(), 1)
				.requires(Items.BONE)
				.requires(Ingredient.of(Items.LEATHER, Items.RABBIT_HIDE))
				.unlockedBy("has_item", has(Items.BONE))
				.save(consumer);

		foodCookingButNoCampfire(TofuItems.GELATINRAW, TofuItems.GELATIN, 0.1F, consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.APRICOTJERRY_BREAD.get(), 1)
				.requires(TofuItems.APRICOTJERRY_BOTTLE.get())
				.requires(Items.BREAD)
				.unlockedBy("has_item", has(TofuItems.APRICOTJERRY_BOTTLE.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.APRICOTSEED.get(), 1)
				.requires(TofuItems.APRICOT.get())
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.KYONINSO.get(), 1)
				.requires(TofuItems.APRICOTSEED.get())
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.FILTERCLOTH.get(), 32)
				.pattern("###")
				.define('#', Ingredient.of(ItemTags.WOOL))
				.unlockedBy("has_item", has(Items.WHITE_WOOL))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TofuBlocks.ISHITOFU_CHISELED_BRICK.get())
				.pattern("#")
				.pattern("#")
				.define('#', TofuBlocks.TOFUSLAB_ISHIBRICK.get())
				.unlockedBy("has_item", has(TofuBlocks.TOFUSLAB_ISHIBRICK.get()))
				.save(consumer);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_KINU, TofuBlocks.KINUTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_MOMEN, TofuBlocks.MOMENTOFU);
		makeStairsCraftingOrCutting(consumer, TofuBlocks.TOFUSTAIR_ISHI, TofuBlocks.ISHITOFU);
		makeStairsCraftingOrCutting(consumer, TofuBlocks.TOFUSTAIR_METAL, TofuBlocks.METALTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_GRILLED, TofuBlocks.GRILLEDTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_HELL, TofuBlocks.HELLTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_SOUL, TofuBlocks.SOULTOFU);
		makeStairsCraftingOrCutting(consumer, TofuBlocks.TOFUSTAIR_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		makeStairsCraftingOrCutting(consumer, TofuBlocks.TOFUSTAIR_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		makeStairsCraftingOrCutting(consumer, TofuBlocks.TOFUSTAIR_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_MISO, TofuBlocks.MISOTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_DRIED, TofuBlocks.DRIEDTOFU);

		makeSlab(consumer, TofuBlocks.TOFUSLAB_KINU, TofuBlocks.KINUTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_MOMEN, TofuBlocks.MOMENTOFU);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_ISHI, TofuBlocks.ISHITOFU);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_METAL, TofuBlocks.METALTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_GRILLED, TofuBlocks.GRILLEDTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_HELL, TofuBlocks.HELLTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_SOUL, TofuBlocks.SOULTOFU);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_MISO, TofuBlocks.MISOTOFU);
		makeSlabCraftingOrCutting(consumer, TofuBlocks.TOFUSLAB_DRIED, TofuBlocks.DRIEDTOFU);


		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_HELL, TofuBlocks.HELLTOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_SOUL, TofuBlocks.SOULTOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_GRILLED, TofuBlocks.GRILLEDTOFU);
		makeSolidFence(consumer, TofuBlocks.TOFUFENCE_ZUNDA, TofuBlocks.ZUNDATOFU);

		makeDoor(consumer, TofuBlocks.TOFUDOOR_KINU, TofuBlocks.KINUTOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_MOMEN, TofuBlocks.MOMENTOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_ISHI, TofuBlocks.ISHITOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_METAL, TofuBlocks.METALTOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_HELL, TofuBlocks.HELLTOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_SOUL, TofuBlocks.SOULTOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_GRILLED, TofuBlocks.GRILLEDTOFU);
		makeDoor(consumer, TofuBlocks.TOFUDOOR_ZUNDA, TofuBlocks.ZUNDATOFU);

		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_KINU, TofuItems.TOFUKINU);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_MOMEN, TofuItems.TOFUMOMEN);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_ISHI, TofuItems.TOFUISHI);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_METAL, TofuItems.TOFUMETAL);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_HELL, TofuItems.TOFUHELL);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_SOUL, TofuItems.TOFUSOUL);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_GRILLED, TofuItems.TOFUGRILLED);
		makeTrapdoor(consumer, TofuBlocks.TOFUTRAPDOOR_ZUNDA, TofuItems.TOFUZUNDA);

		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFUTRAPDOOR_ISHI, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFUFENCE_ISHI, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFUDOOR_ISHI, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_BRICK, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFULADDER_ISHI, 3);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFULADDER_ISHIBRICK, 3);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_CHISELED_BRICK, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_SMOOTH_BRICK, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFUSTAIR_ISHIBRICK, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU, TofuBlocks.TOFUSLAB_ISHIBRICK, 1);

		cuttingRecipe(consumer, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.TOFULADDER_ISHI, 3);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.TOFULADDER_ISHIBRICK, 3);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU_CHISELED_BRICK, 1);
		cuttingRecipe(consumer, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU_SMOOTH_BRICK, 1);


		makeTorch(consumer, TofuBlocks.TOFUTORCH_KINU, TofuItems.TOFUKINU);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_MOMEN, TofuItems.TOFUMOMEN);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_ISHI, TofuItems.TOFUISHI);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_METAL, TofuItems.TOFUMETAL);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_GRILLED, TofuItems.TOFUGRILLED);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_ZUNDA, TofuItems.TOFUZUNDA);

		foodCooking(TofuItems.TOFUKINU, TofuItems.TOFUGRILLED, 0.1F, consumer, "grilled_from_kinu");
		foodCooking(TofuItems.TOFUMOMEN, TofuItems.TOFUGRILLED, 0.1F, consumer, "grilled_from_momen");
		foodCooking(TofuBlocks.KINUTOFU, TofuBlocks.GRILLEDTOFU, 0.1F, consumer, "grilled_block_from_kinu");
		foodCooking(TofuBlocks.MOMENTOFU, TofuBlocks.GRILLEDTOFU, 0.1F, consumer, "grilled_block_from_momen");

		tofuBlockItem(consumer, TofuBlocks.HELLTOFU, TofuItems.TOFUHELL);
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU_BRICK, TofuBlocks.HELLTOFU);
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU_SMOOTH_BRICK, TofuBlocks.HELLTOFU_BRICK);
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU, TofuItems.TOFUSOUL);
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU_BRICK, TofuBlocks.SOULTOFU);
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU_SMOOTH_BRICK, TofuBlocks.SOULTOFU_BRICK);

		cuttingRecipe(consumer, TofuBlocks.HELLTOFU_BRICK, TofuBlocks.HELLTOFU_SMOOTH_BRICK, 2);
		cuttingRecipe(consumer, TofuBlocks.SOULTOFU_BRICK, TofuBlocks.SOULTOFU_BRICK, 2);
		tofuBlockItem(consumer, TofuBlocks.MINCEDTOFU, TofuItems.TOFU_MINCED);


		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TofuItems.TOFUZUNDA.get(), 1)
				.requires(TofuItems.ZUNDA.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.STARCH.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TofuBlocks.TOFU_STEM_PLANKS.get(), 4)
				.requires(TofuBlocks.TOFU_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.TOFU_STEM.get()))
				.save(consumer);

		makeStairs(consumer, TofuBlocks.TOFU_STEM_PLANKS_STAIR, TofuBlocks.TOFU_STEM_PLANKS);
		makeSlab(consumer, TofuBlocks.TOFU_STEM_PLANKS_SLAB, TofuBlocks.TOFU_STEM_PLANKS);
		makeWoodFence(consumer, TofuBlocks.TOFU_STEM_FENCE.get(), TofuBlocks.TOFU_STEM_PLANKS.get());
		makeFenceGate(consumer, TofuBlocks.TOFU_STEM_FENCE_GATE.get(), TofuBlocks.TOFU_STEM_PLANKS.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TofuBlocks.LEEK_PLANKS.get(), 4)
				.requires(TofuBlocks.LEEK_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.LEEK_STEM.get()))
				.save(consumer);

		makeStairs(consumer, TofuBlocks.LEEK_PLANKS_STAIR, TofuBlocks.LEEK_PLANKS);
		makeSlab(consumer, TofuBlocks.LEEK_PLANKS_SLAB, TofuBlocks.LEEK_PLANKS);
		makeWoodFence(consumer, TofuBlocks.LEEK_FENCE.get(), TofuBlocks.LEEK_PLANKS.get());
		makeFenceGate(consumer, TofuBlocks.LEEK_FENCE_GATE.get(), TofuBlocks.LEEK_PLANKS.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TofuBlocks.LEEK_GREEN_PLANKS.get(), 4)
				.requires(TofuBlocks.LEEK_GREEN_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.LEEK_GREEN_STEM.get()))
				.save(consumer);

		makeStairs(consumer, TofuBlocks.LEEK_GREEN_PLANKS_STAIR, TofuBlocks.LEEK_GREEN_PLANKS);
		makeSlab(consumer, TofuBlocks.LEEK_GREEN_PLANKS_SLAB, TofuBlocks.LEEK_GREEN_PLANKS);
		makeWoodFence(consumer, TofuBlocks.LEEK_GREEN_FENCE.get(), TofuBlocks.LEEK_GREEN_PLANKS.get());
		makeFenceGate(consumer, TofuBlocks.LEEK_GREEN_FENCE_GATE.get(), TofuBlocks.LEEK_GREEN_PLANKS.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.SALT_FURNACE.get())
				.pattern("###")
				.pattern("# #")
				.pattern("SSS")
				.define('#', Items.IRON_INGOT)
				.define('S', Tags.Items.COBBLESTONE)
				.unlockedBy("has_item", has(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.MORIJIO.get(), 3)
				.pattern("S#S")
				.pattern("#D#")
				.pattern(" W ")
				.define('#', TofuTags.Items.SALT)
				.define('S', TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.define('D', Items.DIAMOND)
				.define('W', Items.BOWL)
				.unlockedBy("has_item", has(TofuTags.Items.SALT))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.BOTTLE_DASHI.get(), 1)
				.requires(potion(Potions.WATER))
				.requires(Items.DRIED_KELP)
				.unlockedBy("has_item", has(Items.KELP))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.BARREL_MISO.get(), 1)
				.pattern("SSS")
				.pattern("KKK")
				.pattern(" B ")
				.define('S', TofuTags.Items.SALT)
				.define('K', TofuItems.KOUJI.get())
				.define('B', Tags.Items.BARRELS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.KOUJI.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.BARREL_MISOTOFU.get(), 1)
				.pattern("MMM")
				.pattern("TTT")
				.pattern(" B ")
				.define('M', TofuItems.MISO.get())
				.define('T', TofuItems.TOFUMOMEN.get())
				.define('B', Tags.Items.BARRELS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.NATTOBED.get(), 1)
				.pattern("SSS")
				.pattern("SSS")
				.pattern("WWW")
				.define('S', TofuItems.SEEDS_SOYBEANS.get())
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.NETHER_NATTOBED.get(), 1)
				.pattern("SSS")
				.pattern("SSS")
				.pattern("WWW")
				.define('S', TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuBlocks.TOFUCAKE.get())
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.TOFUKINU.get())
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUKINU.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuBlocks.ZUNDATOFUCAKE.get())
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.TOFUZUNDA.get())
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUZUNDA.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuBlocks.SOYCHEESE_TART.get())
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.SOY_CHEESE.get())
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.SOY_CHEESE.get()))
				.save(consumer);

		//soimilk
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.BUCKET_SOYMILK.get())
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.BUCKET)
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.BUCKET_SOYMILK.get())
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.BUCKET)
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(consumer, prefix("bucket_soymilk_okara"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.BUCKET_SOYMILK_NETHER.get())
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.requires(Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.BUCKET_SOYMILK_SOUL.get())
				.requires(TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.requires(Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(consumer);

		//food
		foodCooking(TofuItems.SEEDS_SOYBEANS, TofuItems.SOYBEAN_PARCHED, 0.1F, consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.KINAKO.get())
				.requires(TofuItems.SOYBEAN_PARCHED.get())
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.SOYBEAN_PARCHED.get()))
				.save(consumer);

		foodCooking(TofuItems.EDAMAME, TofuItems.BOILED_EDAMAME, 0.1F, consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.CHIKUWA.get(), 4)
				.pattern(" X ")
				.pattern("YZI")
				.pattern(" X ")
				.define('X', ItemTags.FISHES)
				.define('Y', TofuTags.Items.DUST_SALT)
				.define('Z', Tags.Items.EGGS)
				.define('I', TofuItems.STARCH.get())
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFU_CHIKUWA.get(), 1)
				.requires(TofuItems.TOFUMOMEN.get())
				.requires(Items.COOKED_COD)
				.unlockedBy("has_item", has(TofuItems.TOFUMOMEN.get()))
				.save(consumer, prefix("tofu_chikuwa"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFU_CHIKUWA.get(), 1)
				.requires(TofuItems.COOKED_TOFU_FISH.get())
				.unlockedBy("has_item", has(TofuItems.RAW_TOFU_FISH.get()))
				.save(consumer, prefix("tofu_chikuwa_fish"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.ZUNDA.get(), 4)
				.requires(TofuItems.BOILED_EDAMAME.get(), 8)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.BOILED_EDAMAME.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.ZUNDAMA.get())
				.requires(TofuItems.ZUNDA.get(), 4)
				.requires(Items.GLOWSTONE_DUST)
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(consumer);
		foodCooking(TofuItems.TOFU_HAMBURG_RAW, TofuItems.TOFU_HAMBURG, 0.25F, consumer);
		foodCooking(TofuItems.RAW_TOFU_FISH, TofuItems.COOKED_TOFU_FISH, 0.2F, consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.MISODENGAKU.get(), 1)
				.pattern(" X ")
				.pattern(" Y ")
				.pattern(" Z ")
				.define('X', TofuItems.MISO.get())
				.define('Y', TofuBlocks.MOMENTOFU.get())
				.define('Z', Items.STICK)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.TOFUCOOKIE.get(), 8)
				.pattern("X#X")
				.define('#', TofuItems.TOFUKINU.get())
				.define('X', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUKINU.get()))
				.save(consumer, prefix("tofucookie_kinu"));
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.TOFUCOOKIE.get(), 8)
				.pattern("X#X")
				.define('#', TofuItems.TOFUMOMEN.get())
				.define('X', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUMOMEN.get()))
				.save(consumer, prefix("tofucookie_momen"));

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.TTTBURGER.get(), 1)
				.pattern(" X ")
				.pattern("###")
				.pattern(" X ")
				.define('#', TofuItems.TOFUFRIED_POUCH.get())
				.define('X', Items.BREAD)
				.unlockedBy("has_item", has(TofuItems.TOFUKINU.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.MEAT_WRAPPED_YUBA.get())
				.requires(Items.COOKED_CHICKEN)
				.requires(TofuItems.YUBA.get())
				.unlockedBy("has_item", has(TofuItems.YUBA.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYSTICK.get())
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.WHEAT)
				.requires(TofuTags.Items.SALT)
				.unlockedBy("has_item", has(TofuTags.Items.SALT))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.MISOSOUP.get())
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.requires(TofuItems.MISO.get())
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.MOYASHIITAME.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuTags.Items.DUST_SALT)
				.requires(TofuItems.SPROUTS.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SPROUTS.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.MOYASHIOHITASHI.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.SPROUTS.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SPROUTS.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SALTYMELON.get())
				.requires(TofuTags.Items.SALT)
				.requires(Items.MELON_SLICE)
				.unlockedBy("has_item", has(TofuTags.Items.SALT))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK.get(), 3)
				.requires(TofuItems.BUCKET_SOYMILK.get())
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_APPLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.APPLE)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_COCOA.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.COCOA_BEANS)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_FRUITS.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.APPLE)
				.requires(Items.SWEET_BERRIES)
				.requires(Items.GLOW_BERRIES)
				.requires(Items.CHORUS_FRUIT)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_HONEY.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.HONEY_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_KINAKO.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KINAKO.get())
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_PUDDING.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Tags.Items.EGGS)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_PUMPKIN.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.PUMPKIN)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_RAMUNE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.LIGHT_BLUE_DYE)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_SAKURA.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Blocks.CHERRY_LEAVES)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_ANNIN.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.KYONINSO.get())
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.CRIMSON_BOTTLE.get(), 3)
				.requires(TofuItems.SHROOM_BOTTLE.get())
				.requires(Items.CRIMSON_FUNGUS)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.SHROOM_BOTTLE.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.WARPED_BOTTLE.get(), 3)
				.requires(TofuItems.SHROOM_BOTTLE.get())
				.requires(Items.WARPED_FUNGUS)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.SHROOM_BOTTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.KINAKO_MANJU.get(), 2)
				.requires(TofuItems.KINAKO.get())
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.KINAKO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.ZUNDA_MANJU.get(), 2)
				.requires(TofuItems.ZUNDA.get())
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.NETHER_MANJU.get(), 2)
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOUL_MANJU.get(), 2)
				.requires(TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.KINAKO_MOCHI.get(), 1)
				.requires(TofuItems.KINAKO.get())
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.KINAKO.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.CRIMSON_SOUP.get(), 1)
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get(), 2)
				.requires(Items.CRIMSON_FUNGUS)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.ZUNDA_MOCHI.get(), 1)
				.requires(TofuItems.ZUNDA.get())
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.ZUNDA_MOCHI.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.PUDDING.get(), 1)
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.GELATIN.get())
				.requires(Items.MILK_BUCKET)
				.requires(Items.SUGAR)
				.requires(TofuItems.GLASSBOWL.get())
				.unlockedBy("has_item", has(TofuItems.GELATIN.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.PUDDING_SOYMILK.get(), 1)
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.GELATIN.get())
				.requires(TofuItems.SOYMILK.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.GLASSBOWL.get())
				.unlockedBy("has_item", has(TofuItems.GELATIN.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.NIKUJAGA.get(), 1)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(Items.SUGAR)
				.requires(Items.POTATO)
				.requires(Items.CARROT)
				.requires(Items.COOKED_BEEF)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.ONIGIRI.get(), 2)
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.RICE.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.ONIGIRI_SALT.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.SALT.get())
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.YAKIONIGIRI_MISO.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.MISO.get())
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.YAKIONIGIRI_SHOYU.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_BURGER.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(Items.COOKED_BEEF)
				.unlockedBy("has_item", has(TofuItems.ONIGIRI.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_NATTO.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NATTO.get())
				.unlockedBy("has_item", has(TofuItems.NATTO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_NATTO_LEEK.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NATTO.get())
				.requires(TofuItems.LEEK.get())
				.unlockedBy("has_item", has(TofuItems.NATTO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_NETHER_NATTO.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NETHER_NATTO.get())
				.unlockedBy("has_item", has(TofuItems.NETHER_NATTO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_NETHER_NATTO_LEEK.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NETHER_NATTO.get())
				.requires(TofuItems.LEEK.get())
				.unlockedBy("has_item", has(TofuItems.NETHER_NATTO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_TOFU.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.unlockedBy("has_item", has(TofuItems.ONIGIRI.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RICE_SOBORO_TOFU.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.TOFU_MINCED.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.LEEK.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_MINCED.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.GOHEIMOCHI.get())
				.pattern("#")
				.pattern("M")
				.pattern("S")
				.define('#', TofuItems.ONIGIRI.get())
				.define('M', TofuItems.MISO.get())
				.define('S', Items.STICK)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.INARI.get(), 1)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.OAGE.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.ONIGIRI.get())
				.unlockedBy("has_item", has(TofuItems.OAGE.get()))
				.save(consumer);

		//おからレシピを要実装
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.OKARASTICK.get(), 3)
				.pattern(" # ")
				.pattern(" E ")
				.pattern(" W ")
				.define('#', TofuItems.OKARA.get())
				.define('E', Tags.Items.EGGS)
				.define('W', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.OKARA_DONUT.get(), 4)
				.pattern("#W#")
				.pattern("WEW")
				.pattern("#W#")
				.define('#', TofuItems.OKARA.get())
				.define('E', Tags.Items.EGGS)
				.define('W', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOBOROTOFUSAUTE.get(), 1)
				.requires(TofuItems.TOFU_MINCED.get())
				.requires(Items.COOKED_PORKCHOP)
				.requires(Items.CARROT)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFU_MINCED.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOY_CHOCOLATE.get(), 6)
				.requires(TofuItems.BUCKET_SOYMILK.get())
				.requires(Items.COCOA_BEANS)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(), 6)
				.requires(TofuItems.BUCKET_SOYMILK.get())
				.requires(Items.COCOA_BEANS)
				.requires(Items.SUGAR)
				.requires(TofuItems.LEEK.get())
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.MINCEDPOTATO.get(), 1)
				.requires(Items.POTATO)
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(Items.POTATO))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SEEDS_CHILI.get(), 1)
				.requires(TofuItems.CHILI.get())
				.unlockedBy("has_item", has(TofuItems.CHILI.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.DOUBANJIANG.get(), 1)
				.requires(TofuItems.CHILI.get())
				.requires(Items.WHEAT)
				.requires(Items.SUGAR)
				.requires(TofuItems.SALT.get())
				.unlockedBy("has_item", has(TofuItems.CHILI.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.MABODOFU.get(), 1)
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.requires(TofuItems.STARCH.get())
				.requires(Items.PORKCHOP)
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.DOUBANJIANG.get())
				.unlockedBy("has_item", has(TofuItems.DOUBANJIANG.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.NANBAN.get(), 1)
				.requires(Items.COOKED_CHICKEN)
				.requires(Tags.Items.EGGS)
				.requires(Items.WHEAT)
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.CHILI.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.NANBANTOFU.get(), 1)
				.requires(Ingredient.of(TofuItems.TOFUKINU.get(), TofuItems.TOFUMOMEN.get()))
				.requires(Tags.Items.EGGS)
				.requires(Items.WHEAT)
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.CHILI.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.FUKUMENI.get(), 8)
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.TOFUDRIED.get())
				.unlockedBy("has_item", has(TofuItems.TOFUDRIED.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.KOYADOFUSTEW.get(), 1)
				.requires(TofuItems.TOFUDRIED.get())
				.requires(Items.BROWN_MUSHROOM)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuItems.GLASSBOWL.get(), 2)
				.pattern("# #")
				.pattern(" # ")
				.define('#', Items.GLASS_PANE)
				.unlockedBy("has_item", has(Items.GLASS))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUSOMEN.get(), 4)
				.requires(TofuItems.TOFUKINU.get())
				.requires(TofuItems.ROLLINGPIN.get())
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.STARCH.get())
				.unlockedBy("has_item", has(TofuItems.ROLLINGPIN.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUSOMENBOWL_GLASS.get(), 1)
				.requires(TofuItems.TOFUSOMEN.get())
				.requires(TofuItems.GLASSBOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUSOMEN.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TASTYBEEFSTEW.get(), 1)
				.requires(Items.BROWN_MUSHROOM)
				.requires(Items.RED_MUSHROOM)
				.requires(Items.COOKED_BEEF)
				.requires(TofuTags.Items.DUST_SALT)
				.requires(Items.MILK_BUCKET)
				.requires(Items.WHEAT)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TASTYSTEW.get(), 1)
				.requires(Items.BROWN_MUSHROOM)
				.requires(Items.RED_MUSHROOM)
				.requires(Ingredient.of(Items.COOKED_CHICKEN, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_RABBIT))
				.requires(TofuTags.Items.DUST_SALT)
				.requires(Items.MILK_BUCKET)
				.requires(Items.WHEAT)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.HIYAYAKKO_GLASS.get(), 1)
				.requires(TofuItems.TOFUKINU.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.GLASSBOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.NATTOHIYAYAKKO_GLASS.get(), 1)
				.requires(TofuItems.NATTO.get())
				.requires(TofuItems.TOFUKINU.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.GLASSBOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.TOFUANNIN.get(), 1)
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.GELATIN.get())
				.requires(Items.MILK_BUCKET)
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.SALTPAN.get())
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.COBBLESTONE_SLAB)
				.unlockedBy("has_item", has(Blocks.COBBLESTONE_SLAB))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.SALTPAN.get())
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.COBBLED_DEEPSLATE_SLAB)
				.unlockedBy("has_item", has(Blocks.COBBLED_DEEPSLATE_SLAB))
				.save(consumer, prefix("deepslate_saltpan"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.SALTPAN.get())
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.BLACKSTONE_SLAB)
				.unlockedBy("has_item", has(Blocks.BLACKSTONE_SLAB))
				.save(consumer, prefix("blackstone_saltpan"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.TOFUCHEST.get())
				.pattern("###")
				.pattern("# #")
				.pattern("###")
				.define('#', TofuItems.TOFUISHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUISHI.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.TOFUSCOOP.get())
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('#', Blocks.IRON_BARS)
				.unlockedBy("has_item", has(Blocks.IRON_BARS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.FUKUMAME.get())
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SOYBEAN_PARCHED.get())
				.unlockedBy("has_item", has(TofuItems.SOYBEAN_PARCHED.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.NETHER_FUKUMAME.get())
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, TofuItems.INFERNO_NETHER_FUKUMAME.get(), 1)
				.requires(TofuItems.NETHER_FUKUMAME.get())
				.requires(Items.BLAZE_POWDER)
				.unlockedBy("has_item", has(TofuItems.NETHER_FUKUMAME.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.SOUL_FUKUMAME.get())
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.ZUNDA_ARROW.get(), 8)
				.pattern("###")
				.pattern("#X#")
				.pattern("###")
				.define('X', TofuItems.ZUNDAMA.get())
				.define('#', Items.ARROW)
				.unlockedBy("has_item", has(TofuItems.ZUNDAMA.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get())
				.pattern("# ")
				.pattern(" M")
				.define('#', Items.FISHING_ROD)
				.define('M', TofuBlocks.ZUNDATOFU_MUSHROOM.get())
				.unlockedBy("has_item", has(TofuBlocks.ZUNDATOFU_MUSHROOM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.TOFUBED.get())
				.pattern("###")
				.pattern("PPP")
				.define('#', TofuItems.YUBA.get())
				.define('P', ItemTags.PLANKS)
				.unlockedBy("has_item", has(TofuItems.YUBA.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.FOODPLATE.get())
				.pattern("CCC")
				.define('C', Items.CLAY_BALL)
				.unlockedBy("has_item", has(Items.CLAY_BALL))
				.save(consumer);

		tofuBlockItem(consumer, TofuBlocks.EGGTOFU, TofuItems.TOFUEGG);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_EGG, TofuBlocks.EGGTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_EGG, TofuBlocks.EGGTOFU);

		tofuBlockItem(consumer, TofuBlocks.SESAMETOFU, TofuItems.TOFUSESAME);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_SESAME, TofuBlocks.SESAMETOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_SESAME, TofuBlocks.SESAMETOFU);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.KOUJI_BASE.get(), 1)
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuItems.TOFU_SHIELD.get())
				.pattern("###")
				.pattern("###")
				.pattern(" # ")
				.define('#', TofuItems.TOFUMETAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFUMETAL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuItems.ROLLINGPIN.get())
				.pattern("  S")
				.pattern(" # ")
				.pattern("S  ")
				.define('#', ItemTags.PLANKS)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_item", has(Items.STICK))
				.save(consumer);

		// Rice Block
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.RICE_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.RICE.get())
				.unlockedBy("has_item", has(TofuItems.RICE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TofuItems.RICE.get(), 9)
				.requires(TofuTags.Items.RICE_BLOCK)
				.unlockedBy("has_item", has(TofuTags.Items.RICE_BLOCK))
				.save(consumer);

		// kinako_bread
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.KINAKO_BREAD.get(), 3)
				.requires(TofuItems.KINAKO.get())
				.requires(Items.BREAD)
				.requires(Items.BREAD)
				.requires(Items.BREAD)
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(consumer);

		// edamame_tempura
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.EDAMAME_TEMPLA.get(), 6)
				.requires(TofuItems.BOILED_EDAMAME.get())
				.requires(TofuItems.BOILED_EDAMAME.get())
				.requires(Items.WHEAT)
				.requires(Items.WHEAT)
				.requires(Items.WHEAT)
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.NEGIMA.get(), 1)
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.SOYMEAT.get())
				.requires(Items.STICK)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.SOYMEAT.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOY_KARAAGE.get(), 1)
				.requires(TofuItems.SOYMEAT.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.SOYMEAT.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMEATDON.get(), 1)
				.requires(TofuItems.SOYMEAT.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.RICE.get())
				.unlockedBy("has_item", has(TofuItems.SOYMEAT.get()))
				.save(consumer);


		/*ShapedRecipeBuilder.shaped(TofuItems.TF_CAPACITOR.get())
				.pattern(" M ")
				.pattern("RGR")
				.pattern(" M ")
				.define('M', TofuItems.TOFUMETAL.get())
				.define('R', Items.REDSTONE)
				.define('G', TofuItems.TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.TF_CIRCUIT.get())
				.pattern("RIR")
				.pattern("TTT")
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('R', Items.REDSTONE)
				.define('I', TofuItems.TOFUISHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.TF_COIL.get())
				.pattern("RRR")
				.pattern("TTT")
				.pattern("RRR")
				.define('T', TofuItems.TOFUISHI.get())
				.define('R', Items.REDSTONE)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.TF_OSCILLATOR.get())
				.pattern("TCT")
				.pattern("M M")
				.define('M', TofuItems.TOFUMETAL.get())
				.define('T', TofuItems.TOFUKINU.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuBlocks.TF_STORAGE.get())
				.pattern("CCC")
				.pattern("GTG")
				.define('G', Blocks.GLASS)
				.define('T', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TF_CAPACITOR.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuBlocks.TF_AGGREGATOR.get())
				.pattern("COC")
				.pattern("DRD")
				.pattern(" T ")
				.define('D', TofuBlocks.DRIEDTOFU.get())
				.define('T', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TF_COIL.get())
				.define('O', TofuItems.TF_OSCILLATOR.get())
				.define('R', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuBlocks.ANTENNA_BASIC.get())
				.pattern(" P ")
				.pattern("LOR")
				.pattern("TCT")
				.define('P', TofuItems.LEEK.get())
				.define('R', TofuItems.TF_CAPACITOR.get())
				.define('O', TofuItems.TF_OSCILLATOR.get())
				.define('L', TofuItems.TF_COIL.get())
				.define('T', TofuItems.TOFUMETAL.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);*/

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TofuBlocks.SPROUTSJAR.get())
				.pattern("G")
				.pattern("W")
				.define('G', Blocks.GLASS)
				.define('W', ItemTags.WOOL)
				.unlockedBy("has_item", has(Blocks.GLASS))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.HELL_MABOU.get())
				.requires(TofuItems.TOFUHELL.get())
				.requires(TofuItems.DOUBANJIANG.get())
				.requires(TofuItems.CHILI.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.DOUBANJIANG.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.RED_SOUP.get())
				.requires(TofuItems.TOFUHELL.get())
				.requires(Items.COOKED_COD)
				.requires(Items.CARROT)
				.requires(Items.BEETROOT)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFUHELL.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.HELL_RED_SOUP.get())
				.requires(TofuItems.TOFUHELL.get())
				.requires(Items.COOKED_COD)
				.requires(Items.CARROT)
				.requires(Items.BEETROOT)
				.requires(Items.BOWL)
				.requires(TofuItems.DOUBANJIANG.get())
				.requires(TofuItems.CHILI.get(), 2)
				.unlockedBy("has_item", has(TofuItems.DOUBANJIANG.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SUKIYAKI.get())
				.requires(TofuItems.TOFUGRILLED.get(), 2)
				.requires(Items.COOKED_BEEF)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFUGRILLED.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, TofuItems.TOFU_BUNS_BURGER.get())
				.pattern("G")
				.pattern("M")
				.pattern("G")
				.define('G', TofuItems.TOFUGRILLED.get())
				.define('M', TofuItems.TOFU_HAMBURG.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_HAMBURG.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.STEAMED_BREAD.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Tags.Items.EGGS)
				.requires(Items.SUGAR)
				.requires(Items.WHEAT)
				.requires(Items.HONEY_BOTTLE)
				.unlockedBy("has_item", has(TofuTags.Items.MILK_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.STEAMED_BREAD_COCOA.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Tags.Items.EGGS)
				.requires(Items.SUGAR)
				.requires(Items.WHEAT)
				.requires(Items.COCOA_BEANS)
				.unlockedBy("has_item", has(TofuTags.Items.MILK_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_SOUL_BOTTLE.get(), 3)
				.requires(TofuItems.BUCKET_SOYMILK_SOUL.get())
				.requires(Items.GLASS_BOTTLE, 3)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK_SOUL.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TofuItems.SOYMILK_HELL_BOTTLE.get(), 3)
				.requires(TofuItems.BUCKET_SOYMILK_NETHER.get())
				.requires(Items.GLASS_BOTTLE, 3)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK_NETHER.get()))
				.save(consumer);

		makeSign(TofuBlocks.TOFU_STEM_SIGN, TofuBlocks.TOFU_STEM_PLANKS).save(consumer);
		makeSign(TofuBlocks.LEEK_SIGN, TofuBlocks.LEEK_PLANKS).save(consumer);
		makeSign(TofuBlocks.LEEK_GREEN_SIGN, TofuBlocks.LEEK_GREEN_PLANKS).save(consumer);

		makeHangingSign(TofuBlocks.TOFU_STEM_HANGING_SIGN, TofuBlocks.TOFU_STEM).save(consumer);
		makeHangingSign(TofuBlocks.LEEK_HANGING_SIGN, TofuBlocks.LEEK_STEM).save(consumer);
		makeHangingSign(TofuBlocks.LEEK_GREEN_HANGING_SIGN, TofuBlocks.LEEK_GREEN_STEM).save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, Blocks.CAULDRON, 1)
				.pattern("# #")
				.pattern("# #")
				.pattern("###")
				.define('#', TofuBlocks.METALTOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(consumer, prefix("tofumetal_with_cauldron"));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.SMITHING_TABLE, 1)
				.pattern("##")
				.pattern("PP")
				.pattern("PP")
				.define('#', TofuBlocks.METALTOFU.get())
				.define('P', ItemTags.PLANKS)
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(consumer, prefix("tofumetal_with_smithing_table"));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BLAST_FURNACE, 1)
				.pattern("###")
				.pattern("#F#")
				.pattern("SSS")
				.define('#', TofuBlocks.METALTOFU.get())
				.define('S', Blocks.SMOOTH_STONE)
				.define('F', Blocks.FURNACE)
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(consumer, prefix("tofumetal_with_blast_furnace"));

		//TF Energy Blocks Start
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuBlocks.TOFU_DETECTOR.get(), 1)
				.pattern("BBB")
				.pattern("GRR")
				.pattern("BBB")
				.define('G', TofuItems.TOFUGEM.get())
				.define('R', Items.REDSTONE)
				.define('B', Tags.Items.STONE)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuItems.TF_CAPACITOR.get())
				.pattern(" M ")
				.pattern("RGR")
				.pattern(" M ")
				.define('M', TofuItems.TOFUMETAL.get())
				.define('R', Items.REDSTONE)
				.define('G', TofuItems.TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuItems.TF_CIRCUIT.get())
				.pattern("RIR")
				.pattern("TTT")
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('R', Items.REDSTONE)
				.define('I', TofuItems.TOFUISHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuItems.TF_COIL.get())
				.pattern("RRR")
				.pattern("TTT")
				.pattern("RRR")
				.define('T', TofuItems.TOFUISHI.get())
				.define('R', Items.REDSTONE)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuItems.TF_OSCILLATOR.get())
				.pattern("TCT")
				.pattern("M M")
				.define('M', TofuItems.TOFUMETAL.get())
				.define('T', TofuItems.TOFUKINU.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuBlocks.ANTENNA_BASIC.get())
				.pattern(" P ")
				.pattern("LOR")
				.pattern("TCT")
				.define('P', TofuItems.LEEK.get())
				.define('R', TofuItems.TF_CAPACITOR.get())
				.define('O', TofuItems.TF_OSCILLATOR.get())
				.define('L', TofuItems.TF_COIL.get())
				.define('T', TofuItems.TOFUMETAL.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuBlocks.TF_STORAGE.get())
				.pattern("CCC")
				.pattern("GTG")
				.define('G', Blocks.GLASS)
				.define('T', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TF_CAPACITOR.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TofuBlocks.TOFU_WORK_STATION.get())
				.pattern("MM")
				.pattern("TT")
				.pattern("TT")
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('M', TofuItems.TOFUMETAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(consumer);
	}
}

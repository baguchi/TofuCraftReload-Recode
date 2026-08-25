package baguchi.tofucraft.data.generator.recipe;

import baguchi.tofucraft.data.generator.recipe.builder.BitternRecipeBuilder;
import baguchi.tofucraft.data.generator.recipe.builder.HardenRecipeBuilder;
import baguchi.tofucraft.data.generator.recipe.builder.TFShapedRecipeBuilder;
import baguchi.tofucraft.data.generator.recipe.builder.TFShapelessRecipeBuilder;
import baguchi.tofucraft.data.generator.recipe.builder.TFTofuMakeRecipeBuilder;
import baguchi.tofucraft.data.generator.recipe.builder.TofuPotShapelessRecipeBuilder;
import baguchi.tofucraft.recipe.BucketToBottleRecipe;
import baguchi.tofucraft.recipe.FluidBucketRecipe;
import baguchi.tofucraft.recipe.TFCraftingCategory;
import baguchi.tofucraft.recipe.TofuPotCategory;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.Optional;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(HolderLookup.Provider generator, RecipeOutput completableFuture) {
		super(generator, completableFuture);
	}

	@Override
	protected void buildRecipes() {
		HolderLookup<Item> lookup = this.registries.lookupOrThrow(Registries.ITEM);
		/*helmetItem(this.output, "tofu_diamond_helmet", TofuItems.ARMOR_TOFU_DIAMONDHELMET, TofuBlocks.DIAMONDTOFU);
		chestplateItem(this.output, "tofu_diamond_chestplate", TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, TofuBlocks.DIAMONDTOFU);
		leggingsItem(this.output, "tofu_diamond_leggings", TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, TofuBlocks.DIAMONDTOFU);
		bootsItem(this.output, "tofu_diamond_boots", TofuItems.ARMOR_TOFU_DIAMONDBOOTS, TofuBlocks.DIAMONDTOFU);
		*/

		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_SWORD.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_SWORD);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_PICKAXE.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_PICKAXE);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_AXE.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_AXE);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_SHOVEL.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_SHOVEL);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_HOE.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_HOE);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_SPEAR.get(), RecipeCategory.TOOLS, TofuItems.TOFU_DIAMOND_SPEAR);
		//tofuDiamondSmithing(this.output, TofuItems.TOFU_SHIELD.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_SHIELD);

		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_HELMET.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_HELMET);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_CHESTPLATE.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_CHESTPLATE);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_LEGGINGS.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_LEGGINGS);
		tofuDiamondSmithing(this.output, TofuItems.TOFU_METAL_BOOTS.get(), RecipeCategory.COMBAT, TofuItems.TOFU_DIAMOND_BOOTS);

		zundaSmithing(this.output, Items.BOW, RecipeCategory.COMBAT, TofuItems.ZUNDA_BOW);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.DECORATIONS, TofuBlocks.TOFU_METAL_LANTERN.get(), 1)
				.pattern("MMM")
				.pattern("MTM")
				.pattern("MMM")
				.define('M', TofuItems.TOFU_METAL_NUGGET.get())
				.define('T', Items.TORCH)
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.DECORATIONS, TofuBlocks.TOFU_METAL_SOUL_LANTERN.get(), 1)
				.pattern("MMM")
				.pattern("MSM")
				.pattern("MMM")
				.define('M', TofuItems.TOFU_METAL_NUGGET.get())
				.define('S', Items.SOUL_TORCH)
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL_NUGGET.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.DECORATIONS, TofuBlocks.TOFU_METAL_CHAIN.get(), 1)
				.pattern("N")
				.pattern("M")
				.pattern("N")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('N', TofuItems.TOFU_METAL_NUGGET.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TRANSPORTATION, TofuItems.SPROUT_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.SPROUT_PLANKS.get())
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TRANSPORTATION, TofuItems.TOFU_STEM_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.TOFU_STEM_PLANKS.get())
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TRANSPORTATION, TofuItems.LEEK_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.LEEK_PLANKS.get())
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TRANSPORTATION, TofuItems.LEEK_GREEN_BOAT.get(), 1)
				.pattern("S S")
				.pattern("SSS")
				.define('S', TofuBlocks.LEEK_GREEN_PLANKS.get())
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.TRANSPORTATION, TofuItems.SPROUT_CHEST_BOAT.get(), 1)
				.requires(TofuItems.SPROUT_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.TRANSPORTATION, TofuItems.TOFU_STEM_CHEST_BOAT.get(), 1)
				.requires(TofuItems.TOFU_STEM_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.TRANSPORTATION, TofuItems.LEEK_CHEST_BOAT.get(), 1)
				.requires(TofuItems.LEEK_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.TRANSPORTATION, TofuItems.LEEK_GREEN_CHEST_BOAT.get(), 1)
				.requires(TofuItems.LEEK_GREEN_BOAT.get())
				.requires(Tags.Items.CHESTS_WOODEN)
				.unlockedBy("inside_of", insideOf(Blocks.WATER))
				.save(this.output);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get(), 2)
				.pattern("GTG")
				.pattern("G#G")
				.pattern("GDG")
				.define('D', TofuItems.TOFU_DIAMOND.get())
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('G', Items.DIAMOND)
				.define('#', TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get()))
				.save(this.output, prefix("copy_tofu_template"));

		/*swordItem(this.output, "tofu_diamond_sword", TofuItems.TOFU_DIAMOND_SWORD, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(this.output, "tofu_diamond_pickaxe", TofuItems.TOFU_DIAMOND_PICKAXE, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		axeItem(this.output, "tofu_diamond_axe", TofuItems.TOFU_DIAMOND_AXE, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(this.output, "tofu_diamond_shovel", TofuItems.TOFU_DIAMOND_SHOVEL, TofuBlocks.DIAMONDTOFU, Tags.Items.RODS_WOODEN);
		*/
		helmetItem(this.output, "tofu_metal_helmet", TofuItems.TOFU_METAL_HELMET, TofuBlocks.METALTOFU);
		chestplateItem(this.output, "tofu_metal_chestplate", TofuItems.TOFU_METAL_CHESTPLATE, TofuBlocks.METALTOFU);
		leggingsItem(this.output, "tofu_metal_leggings", TofuItems.TOFU_METAL_LEGGINGS, TofuBlocks.METALTOFU);
		bootsItem(this.output, "tofu_metal_boots", TofuItems.TOFU_METAL_BOOTS, TofuBlocks.METALTOFU);

		swordItem(this.output, "tofu_metal_sword", TofuItems.TOFU_METAL_SWORD, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(this.output, "tofu_metal_pickaxe", TofuItems.TOFU_METAL_PICKAXE, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		axeItem(this.output, "tofu_metal_axe", TofuItems.TOFU_METAL_AXE, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(this.output, "tofu_metal_shovel", TofuItems.TOFU_METAL_SHOVEL, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		hoeItem(this.output, "tofu_metal_hoe", TofuItems.TOFU_METAL_HOE, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);
		spearItem(this.output, "tofu_metal_spear", TofuItems.TOFU_METAL_SPEAR, TofuBlocks.METALTOFU, Tags.Items.RODS_WOODEN);

		helmetItem(this.output, "tofu_solid_helmet", TofuItems.TOFU_SOLID_HELMET, TofuBlocks.ISHITOFU);
		chestplateItem(this.output, "tofu_solid_chestplate", TofuItems.TOFU_SOLID_CHESTPLATE, TofuBlocks.ISHITOFU);
		leggingsItem(this.output, "tofu_solid_leggings", TofuItems.TOFU_SOLID_LEGGINGS, TofuBlocks.ISHITOFU);
		bootsItem(this.output, "tofu_solid_boots", TofuItems.TOFU_SOLID_BOOTS, TofuBlocks.ISHITOFU);

		swordItem(this.output, "tofu_solid_sword", TofuItems.TOFU_SOLID_SWORD, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(this.output, "tofu_solid_pickaxe", TofuItems.TOFU_SOLID_PICKAXE, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		axeItem(this.output, "tofu_solid_axe", TofuItems.TOFU_SOLID_AXE, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		shovelItem(this.output, "tofu_solid_shovel", TofuItems.TOFU_SOLID_SHOVEL, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		hoeItem(this.output, "tofu_solid_hoe", TofuItems.TOFU_SOLID_HOE, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);
		spearItem(this.output, "tofu_solid_spear", TofuItems.TOFU_SOLID_SPEAR, TofuBlocks.ISHITOFU, Tags.Items.RODS_WOODEN);

		swordItem(this.output, "tofu_solid_sword_slate", TofuItems.TOFU_SOLID_SWORD, TofuBlocks.TOFUSLATE, Tags.Items.RODS_WOODEN);
		pickaxeItem(this.output, "tofu_solid_pickaxe_slate", TofuItems.TOFU_SOLID_PICKAXE, TofuBlocks.TOFUSLATE, Tags.Items.RODS_WOODEN);
		axeItem(this.output, "tofu_solid_axe_slate", TofuItems.TOFU_SOLID_AXE, TofuBlocks.TOFUSLATE, Tags.Items.RODS_WOODEN);
		shovelItem(this.output, "tofu_solid_shovel_slate", TofuItems.TOFU_SOLID_SHOVEL, TofuBlocks.TOFUSLATE, Tags.Items.RODS_WOODEN);
		hoeItem(this.output, "tofu_solid_hoe_slate", TofuItems.TOFU_SOLID_HOE, TofuBlocks.TOFUSLATE, Tags.Items.RODS_WOODEN);

		helmetItem(this.output, "tofu_momen_helmet", TofuItems.TOFU_MOMEN_HELMET, TofuBlocks.MOMENTOFU);
		chestplateItem(this.output, "tofu_momen_chestplate", TofuItems.TOFU_MOMEN_CHESTPLATE, TofuBlocks.MOMENTOFU);
		leggingsItem(this.output, "tofu_momen_leggings", TofuItems.TOFU_MOMEN_LEGGINGS, TofuBlocks.MOMENTOFU);
		bootsItem(this.output, "tofu_momen_boots", TofuItems.TOFU_MOMEN_BOOTS, TofuBlocks.MOMENTOFU);

		swordItem(this.output, "tofu_momen_sword", TofuItems.TOFU_MOMEN_SWORD, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(this.output, "tofu_momen_pickaxe", TofuItems.TOFU_MOMEN_PICKAXE, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		axeItem(this.output, "tofu_momen_axe", TofuItems.TOFU_MOMEN_AXE, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(this.output, "tofu_momen_shovel", TofuItems.TOFU_MOMEN_SHOVEL, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		hoeItem(this.output, "tofu_momen_hoe", TofuItems.TOFU_MOMEN_HOE, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);
		spearItem(this.output, "tofu_momen_spear", TofuItems.TOFU_MOMEN_SPEAR, TofuBlocks.MOMENTOFU, Tags.Items.RODS_WOODEN);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.TOFU_METAL_SHEARS.get())
				.pattern(" #")
				.pattern("# ")
				.define('#', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.TOFU_METAL_BUCKET.get())
				.pattern("# #")
				.pattern(" # ")
				.define('#', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.BUGLE.get())
				.pattern("  #")
				.pattern("###")
				.define('#', Items.IRON_INGOT)
				.unlockedBy("has_item", has(Items.IRON_INGOT))
				.save(this.output);


		helmetItem(this.output, "tofu_kinu_helmet", TofuItems.TOFU_KINU_HELMET, TofuBlocks.KINUTOFU);
		chestplateItem(this.output, "tofu_kinu_chestplate", TofuItems.TOFU_KINU_CHESTPLATE, TofuBlocks.KINUTOFU);
		leggingsItem(this.output, "tofu_kinu_leggings", TofuItems.TOFU_KINU_LEGGINGS, TofuBlocks.KINUTOFU);
		bootsItem(this.output, "tofu_kinu_boots", TofuItems.TOFU_KINU_BOOTS, TofuBlocks.KINUTOFU);

		swordItem(this.output, "tofu_kinu_sword", TofuItems.TOFU_KINU_SWORD, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		pickaxeItem(this.output, "tofu_kinu_pickaxe", TofuItems.TOFU_KINU_PICKAXE, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		axeItem(this.output, "tofu_kinu_axe", TofuItems.TOFU_KINU_AXE, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		shovelItem(this.output, "tofu_kinu_shovel", TofuItems.TOFU_KINU_SHOVEL, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		hoeItem(this.output, "tofu_kinu_hoe", TofuItems.TOFU_KINU_HOE, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);
		spearItem(this.output, "tofu_kinu_spear", TofuItems.TOFU_KINU_SPEAR, TofuBlocks.KINUTOFU, Tags.Items.RODS_WOODEN);


		ladderItem(this.output, TofuBlocks.TOFU_LADDER_KINU, TofuItems.TOFU_KINU);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_MOMEN, TofuItems.TOFU_MOMEN);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_ISHI, TofuItems.TOFU_ISHI);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_METAL, TofuItems.TOFU_METAL);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_GRILLED, TofuItems.TOFU_GRILLED);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_ZUNDA, TofuItems.TOFU_ZUNDA);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_HELL, TofuItems.TOFU_HELL);
		ladderItem(this.output, TofuBlocks.TOFU_LADDER_SOUL, TofuItems.TOFU_SOUL);

		tofuBlockItem(this.output, TofuBlocks.MOMENTOFU, TofuItems.TOFU_KINU, "tofumomen_from_kinu");
		tofuBlockItem(this.output, TofuBlocks.MOMENTOFU, TofuItems.TOFU_MOMEN);
		tofuBlockItem(this.output, TofuBlocks.ISHITOFU, TofuItems.TOFU_ISHI);
		buildingTofuBlockItem(this.output, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU);
		buildingTofuBlockItem(this.output, TofuBlocks.ISHITOFU_SMOOTH_BRICK, TofuBlocks.ISHITOFU_BRICK);
		buildingTofuChiseledItem(this.output, TofuBlocks.ISHITOFU_CHISELED_BRICK, TofuBlocks.TOFU_SLAB_ISHIBRICK);
		tofuBlockItem(this.output, TofuBlocks.METALTOFU, TofuItems.TOFU_METAL);
		tofuBlockItem(this.output, TofuBlocks.DIAMONDTOFU, TofuItems.TOFU_DIAMOND);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.TOFU_GEM_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.ADVANCE_TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.ADVANCE_TOFUGEM.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.TOFUGEM.get(), 9)
				.requires(TofuBlocks.TOFU_GEM_BLOCK.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output, prefix("revert_to_tofu_gem"));
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.ADVANCE_TOFUGEM.get(), 9)
				.requires(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get())
				.unlockedBy("has_item", has(TofuItems.ADVANCE_TOFUGEM.get()))
				.save(this.output, prefix("revert_to_adv_tofu_gem"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.ZUNDAMA_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.ZUNDAMA.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDAMA.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.ZUNDAMA.get(), 9)
				.requires(TofuBlocks.ZUNDAMA_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.ZUNDAMA_BLOCK.get()))
				.save(this.output, prefix("revert_to_zundama"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.TOFU_DIAMOND.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFU_DIAMOND_NUGGET.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_DIAMOND_NUGGET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.TOFU_DIAMOND_NUGGET.get(), 9)
				.requires(TofuItems.TOFU_DIAMOND.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_DIAMOND.get()))
				.save(this.output);

		tofuBlockItem(this.output, TofuBlocks.GRILLED_TOFU, TofuItems.TOFU_GRILLED);
		tofuBlockItem(this.output, TofuBlocks.ZUNDATOFU, TofuItems.TOFU_ZUNDA);
		tofuBlockItem(this.output, TofuBlocks.MISOTOFU, TofuItems.TOFU_MISO);
		tofuBlockItem(this.output, TofuBlocks.DRIEDTOFU, TofuItems.TOFU_DRIED);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_DRIED, TofuBlocks.DRIEDTOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_DRIED, TofuBlocks.DRIEDTOFU);

		buildingTofuBlockItem(this.output, TofuBlocks.DRIEDTOFU_BRICK, TofuBlocks.DRIEDTOFU);
		buildingTofuBlockItem(this.output, TofuBlocks.DRIEDTOFU_SMOOTH_BRICK, TofuBlocks.DRIEDTOFU_BRICK);
		buildingTofuChiseledItem(this.output, TofuBlocks.DRIEDTOFU_CHISELED_BRICK, TofuBlocks.TOFU_SLAB_DRIEDBRICK);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_DRIEDBRICK, TofuBlocks.DRIEDTOFU_BRICK);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_DRIEDBRICK, TofuBlocks.DRIEDTOFU_BRICK);


		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MISC, Optional.empty(), new ItemStackTemplate(TofuItems.TOFU_EGG.get(), 4), 200, 0.1F)
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 200)), TofuItems.YUDOFU.get(), 100, 0.1F)
				.requires(TofuTags.Items.TOFU)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFU_KINU.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.EDAMAME_RICE.get(), 200, 0.4F)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.EDAMAME.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.EDAMAME.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.BOTTLE_SOYOIL.get(), 1)
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_FRIED.get(), 1)
				.requires(TofuTags.Items.TOFU)
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(this.output);

		SimpleCookingRecipeBuilder.smoking(Ingredient.of(TofuItems.TOFU_DRIED.get()), RecipeCategory.FOOD, TofuItems.TOFU_SMOKE.get(), 0.1F, 600)
				.unlockedBy("has_item", has(TofuItems.TOFU_DRIED.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_FRIED_POUCH.get(), 1)
				.requires(TofuItems.STARCH.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.requires(TofuTags.Items.TOFU)
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.AGEDASHI_TOFU.get(), 200, 0.4F)
				.requires(TofuItems.TOFU_FRIED_POUCH.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFU_FRIED_POUCH.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.OAGE.get(), 4, 200, 0.4F)
				.requires(TofuBlocks.TOFU_SLAB_MOMEN.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(this.output);

		foodCooking(TofuItems.TOFU_ISHI, TofuItems.TOFU_STEAK, 0.1F, this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_MINCED.get(), 1)
				.requires(TofuItems.TOFU_MOMEN.get())
				.requires(TofuItems.ROLLINGPIN.get())
				.unlockedBy("has_item", has(TofuItems.ROLLINGPIN.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.STARCH_RAW.get(), 1)
				.requires(TofuItems.MINCEDPOTATO.get())
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(TofuItems.MINCEDPOTATO.get()))
				.save(this.output);

		foodCookingButNoCampfire(TofuItems.STARCH_RAW, TofuItems.STARCH, 0.1F, this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.GELATINRAW.get(), 1)
				.requires(Items.BONE)
				.requires(Ingredient.of(Items.LEATHER, Items.RABBIT_HIDE))
				.unlockedBy("has_item", has(Items.BONE))
				.save(this.output);

		foodCookingButNoCampfire(TofuItems.GELATINRAW, TofuItems.GELATIN, 0.1F, this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.APRICOTJERRY_BREAD.get(), 1)
				.requires(TofuItems.APRICOTJERRY_BOTTLE.get())
				.requires(Items.BREAD)
				.unlockedBy("has_item", has(TofuItems.APRICOTJERRY_BOTTLE.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.APRICOTSEED.get(), 1)
				.requires(TofuItems.APRICOT.get())
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.KYONINSO.get(), 1)
				.requires(TofuItems.APRICOTSEED.get())
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.FILTERCLOTH.get(), 25)
				.pattern("###")
				.define('#', ItemTags.WOOL_CARPETS)
				.unlockedBy("has_item", has(Items.WHITE_WOOL))
				.save(this.output);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_KINU, TofuBlocks.KINUTOFU);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_MOMEN, TofuBlocks.MOMENTOFU);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_ISHI, TofuBlocks.ISHITOFU);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_METAL, TofuBlocks.METALTOFU);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_GRILLED, TofuBlocks.GRILLED_TOFU);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_HELL, TofuBlocks.HELLTOFU);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_SOUL, TofuBlocks.SOULTOFU);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_MISO, TofuBlocks.MISOTOFU);

		makeSlab(this.output, TofuBlocks.TOFU_SLAB_KINU, TofuBlocks.KINUTOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_MOMEN, TofuBlocks.MOMENTOFU);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_ISHI, TofuBlocks.ISHITOFU);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_METAL, TofuBlocks.METALTOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_GRILLED, TofuBlocks.GRILLED_TOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_HELL, TofuBlocks.HELLTOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_SOUL, TofuBlocks.SOULTOFU);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_MISO, TofuBlocks.MISOTOFU);

		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_KINU, TofuBlocks.KINUTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_MOMEN, TofuBlocks.MOMENTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_ISHI, TofuBlocks.ISHITOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_METAL, TofuBlocks.METALTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_HELL, TofuBlocks.HELLTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_SOUL, TofuBlocks.SOULTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_GRILLED, TofuBlocks.GRILLED_TOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_MISO, TofuBlocks.MISOTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_DRIED, TofuBlocks.DRIEDTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_EGG, TofuBlocks.EGGTOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_SESAME, TofuBlocks.SESAMETOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_STRAWBERRY, TofuBlocks.STRAWBERRY_TOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_FRIED, TofuBlocks.FRIED_TOFU);
		makeSolidFence(this.output, TofuBlocks.TOFU_FENCE_FRIED_POUCH, TofuBlocks.FRIED_POUCH_TOFU);

		makeDoor(this.output, TofuBlocks.TOFU_DOOR_KINU, TofuBlocks.KINUTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_MOMEN, TofuBlocks.MOMENTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_ISHI, TofuBlocks.ISHITOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_METAL, TofuBlocks.METALTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_HELL, TofuBlocks.HELLTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_SOUL, TofuBlocks.SOULTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_GRILLED, TofuBlocks.GRILLED_TOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_MISO, TofuBlocks.MISOTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_DRIED, TofuBlocks.DRIEDTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_EGG, TofuBlocks.EGGTOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_SESAME, TofuBlocks.SESAMETOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_STRAWBERRY, TofuBlocks.STRAWBERRY_TOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_FRIED, TofuBlocks.FRIED_TOFU);
		makeDoor(this.output, TofuBlocks.TOFU_DOOR_FRIED_POUCH, TofuBlocks.FRIED_POUCH_TOFU);

		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_KINU, TofuItems.TOFU_KINU);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_MOMEN, TofuItems.TOFU_MOMEN);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_ISHI, TofuItems.TOFU_ISHI);
		makeMetalTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_METAL, TofuBlocks.METALTOFU);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_HELL, TofuItems.TOFU_HELL);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_SOUL, TofuItems.TOFU_SOUL);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_GRILLED, TofuItems.TOFU_GRILLED);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_ZUNDA, TofuItems.TOFU_ZUNDA);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_MISO, TofuBlocks.MISOTOFU);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_DRIED, TofuBlocks.DRIEDTOFU);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_EGG, TofuBlocks.EGGTOFU);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_SESAME, TofuBlocks.SESAMETOFU);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_STRAWBERRY, TofuItems.TOFU_STRAWBERRY);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_FRIED, TofuItems.TOFU_FRIED);
		makeTrapdoor(this.output, TofuBlocks.TOFU_TRAPDOOR_FRIED_POUCH, TofuItems.TOFU_FRIED_POUCH);

		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_TRAPDOOR_ISHI, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_FENCE_ISHI, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_DOOR_ISHI, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_LADDER_ISHI, 3);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_LADDER_ISHIBRICK, 3);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_CHISELED_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_SMOOTH_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_STAIR_ISHIBRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU, TofuBlocks.TOFU_SLAB_ISHIBRICK, 1);

		cuttingRecipe(this.output, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.TOFU_LADDER_ISHI, 3);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.TOFU_LADDER_ISHIBRICK, 3);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU_CHISELED_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU_SMOOTH_BRICK, 1);


		makeTorch(this.output, TofuBlocks.TOFU_TORCH_KINU, TofuItems.TOFU_KINU);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_MOMEN, TofuItems.TOFU_MOMEN);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_ISHI, TofuItems.TOFU_ISHI);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_METAL, TofuItems.TOFU_METAL);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_GRILLED, TofuItems.TOFU_GRILLED);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_ZUNDA, TofuItems.TOFU_ZUNDA);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_HELL, TofuItems.TOFU_HELL);
		makeTorch(this.output, TofuBlocks.TOFU_TORCH_SOUL, TofuItems.TOFU_SOUL);

		foodCooking(TofuItems.TOFU_KINU, TofuItems.TOFU_GRILLED, 0.1F, this.output, "grilled_from_kinu");
		foodCooking(TofuItems.TOFU_MOMEN, TofuItems.TOFU_GRILLED, 0.1F, this.output, "grilled_from_momen");
		foodCooking(TofuBlocks.KINUTOFU, TofuBlocks.GRILLED_TOFU, 0.1F, this.output, "grilled_block_from_kinu");
		foodCooking(TofuBlocks.MOMENTOFU, TofuBlocks.GRILLED_TOFU, 0.1F, this.output, "grilled_block_from_momen");

		tofuBlockItem(this.output, TofuBlocks.HELLTOFU, TofuItems.TOFU_HELL);
		buildingTofuBlockItem(this.output, TofuBlocks.HELLTOFU_BRICK, TofuBlocks.HELLTOFU);
		buildingTofuBlockItem(this.output, TofuBlocks.HELLTOFU_SMOOTH_BRICK, TofuBlocks.HELLTOFU_BRICK);
		buildingTofuChiseledItem(this.output, TofuBlocks.HELLTOFU_CHISELED_BRICK, TofuBlocks.TOFU_SLAB_HELLBRICK);

		tofuBlockItem(this.output, TofuBlocks.SOULTOFU, TofuItems.TOFU_SOUL);
		buildingTofuBlockItem(this.output, TofuBlocks.SOULTOFU_BRICK, TofuBlocks.SOULTOFU);
		buildingTofuBlockItem(this.output, TofuBlocks.SOULTOFU_SMOOTH_BRICK, TofuBlocks.SOULTOFU_BRICK);
		buildingTofuChiseledItem(this.output, TofuBlocks.SOULTOFU_CHISELED_BRICK, TofuBlocks.TOFU_SLAB_SOULBRICK);

		cuttingRecipe(this.output, TofuBlocks.HELLTOFU_BRICK, TofuBlocks.HELLTOFU_SMOOTH_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.SOULTOFU_BRICK, TofuBlocks.SOULTOFU_SMOOTH_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.HELLTOFU_BRICK, TofuBlocks.HELLTOFU_CHISELED_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.SOULTOFU_BRICK, TofuBlocks.SOULTOFU_CHISELED_BRICK, 1);

		cuttingRecipe(this.output, TofuBlocks.DRIEDTOFU_BRICK, TofuBlocks.DRIEDTOFU_SMOOTH_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.EGGTOFU_BRICK, TofuBlocks.EGGTOFU_SMOOTH_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.DRIEDTOFU_BRICK, TofuBlocks.DRIEDTOFU_CHISELED_BRICK, 1);
		cuttingRecipe(this.output, TofuBlocks.EGGTOFU_BRICK, TofuBlocks.EGGTOFU_CHISELED_BRICK, 1);
		tofuBlockItem(this.output, TofuBlocks.MINCEDTOFU, TofuItems.TOFU_MINCED);

		tofuBlockItem(this.output, TofuBlocks.EGGTOFU, TofuItems.TOFU_EGG);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_EGG, TofuBlocks.EGGTOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_EGG, TofuBlocks.EGGTOFU);

		buildingTofuBlockItem(this.output, TofuBlocks.EGGTOFU_BRICK, TofuBlocks.EGGTOFU);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_EGGBRICK, TofuBlocks.EGGTOFU_BRICK);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_EGGBRICK, TofuBlocks.EGGTOFU_BRICK);
		buildingTofuBlockItem(this.output, TofuBlocks.EGGTOFU_SMOOTH_BRICK, TofuBlocks.EGGTOFU_BRICK);
		buildingTofuChiseledItem(this.output, TofuBlocks.EGGTOFU_CHISELED_BRICK, TofuBlocks.TOFU_SLAB_EGGBRICK);


		buildingTofuBlockItem(this.output, TofuBlocks.ZUNDATOFU_BRICK, TofuBlocks.ZUNDATOFU);
		buildingTofuBlockItem(this.output, TofuBlocks.ZUNDATOFU_SMOOTH_BRICK, TofuBlocks.ZUNDATOFU_BRICK);
		makeStairsCraftingOrCutting(this.output, TofuBlocks.TOFU_STAIR_ZUNDABRICK, TofuBlocks.ZUNDATOFU_BRICK);
		makeSlabCraftingOrCutting(this.output, TofuBlocks.TOFU_SLAB_ZUNDABRICK, TofuBlocks.ZUNDATOFU_BRICK);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuItems.TOFU_ZUNDA.get(), 1)
				.requires(TofuItems.ZUNDA.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.STARCH.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_SESAME.get(), 1)
				.requires(TofuItems.SEEDS_SESAME.get())
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.STARCH.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SESAME.get()))
				.save(this.output);


		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.SPROUT_PLANKS.get(), 4)
				.requires(TofuBlocks.SPROUT_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.SPROUT_STEM.get()))
				.save(this.output);

		makeStairs(this.output, TofuBlocks.SPROUT_PLANKS_STAIR, TofuBlocks.SPROUT_PLANKS);
		makeSlab(this.output, TofuBlocks.SPROUT_PLANKS_SLAB, TofuBlocks.SPROUT_PLANKS);
		makeWoodFence(this.output, TofuBlocks.SPROUT_FENCE.get(), TofuBlocks.SPROUT_PLANKS.get());
		makeFenceGate(this.output, TofuBlocks.SPROUT_FENCE_GATE.get(), TofuBlocks.SPROUT_PLANKS.get());
		makeDoor(this.output, TofuBlocks.SPROUT_DOOR, TofuBlocks.SPROUT_PLANKS);
		makeTrapdoor(this.output, TofuBlocks.SPROUT_TRAPDOOR, TofuBlocks.SPROUT_PLANKS);



		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.TOFU_STEM_PLANKS.get(), 4)
				.requires(TofuBlocks.TOFU_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.TOFU_STEM.get()))
				.save(this.output);

		makeStairs(this.output, TofuBlocks.TOFU_STEM_PLANKS_STAIR, TofuBlocks.TOFU_STEM_PLANKS);
		makeSlab(this.output, TofuBlocks.TOFU_STEM_PLANKS_SLAB, TofuBlocks.TOFU_STEM_PLANKS);
		makeWoodFence(this.output, TofuBlocks.TOFU_STEM_FENCE.get(), TofuBlocks.TOFU_STEM_PLANKS.get());
		makeFenceGate(this.output, TofuBlocks.TOFU_STEM_FENCE_GATE.get(), TofuBlocks.TOFU_STEM_PLANKS.get());
		makeDoor(this.output, TofuBlocks.TOFU_STEM_DOOR, TofuBlocks.TOFU_STEM_PLANKS);
		makeTrapdoor(this.output, TofuBlocks.TOFU_STEM_TRAPDOOR, TofuBlocks.TOFU_STEM_PLANKS);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.LEEK_PLANKS.get(), 4)
				.requires(TofuBlocks.LEEK_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.LEEK_STEM.get()))
				.save(this.output);

		makeStairs(this.output, TofuBlocks.LEEK_PLANKS_STAIR, TofuBlocks.LEEK_PLANKS);
		makeSlab(this.output, TofuBlocks.LEEK_PLANKS_SLAB, TofuBlocks.LEEK_PLANKS);
		makeWoodFence(this.output, TofuBlocks.LEEK_FENCE.get(), TofuBlocks.LEEK_PLANKS.get());
		makeFenceGate(this.output, TofuBlocks.LEEK_FENCE_GATE.get(), TofuBlocks.LEEK_PLANKS.get());
		makeDoor(this.output, TofuBlocks.LEEK_DOOR, TofuBlocks.LEEK_PLANKS);
		makeTrapdoor(this.output, TofuBlocks.LEEK_TRAPDOOR, TofuBlocks.LEEK_PLANKS);



		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.LEEK_GREEN_PLANKS.get(), 4)
				.requires(TofuBlocks.LEEK_GREEN_STEM.get())
				.unlockedBy("has_item", has(TofuBlocks.LEEK_GREEN_STEM.get()))
				.save(this.output);

		makeStairs(this.output, TofuBlocks.LEEK_GREEN_PLANKS_STAIR, TofuBlocks.LEEK_GREEN_PLANKS);
		makeSlab(this.output, TofuBlocks.LEEK_GREEN_PLANKS_SLAB, TofuBlocks.LEEK_GREEN_PLANKS);
		makeWoodFence(this.output, TofuBlocks.LEEK_GREEN_FENCE.get(), TofuBlocks.LEEK_GREEN_PLANKS.get());
		makeFenceGate(this.output, TofuBlocks.LEEK_GREEN_FENCE_GATE.get(), TofuBlocks.LEEK_GREEN_PLANKS.get());
		makeDoor(this.output, TofuBlocks.LEEK_GREEN_DOOR, TofuBlocks.LEEK_GREEN_PLANKS);
		makeTrapdoor(this.output, TofuBlocks.LEEK_GREEN_TRAPDOOR, TofuBlocks.LEEK_GREEN_PLANKS);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.TOFU_METAL_NUGGET.get(), 9)
				.requires(TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.BUILDING_BLOCKS, TofuItems.TOFU_METAL.get(), 1)
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFU_METAL_NUGGET.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL_NUGGET.get()))
				.save(this.output);



		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.METAL_TOFU_GRATE.get(), 8)
				.pattern(" # ")
				.pattern("# #")
				.pattern(" # ")
				.define('#', TofuBlocks.METALTOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.METAL_TOFU_LUMP.get(), 4)
				.pattern(" # ")
				.pattern("#Z#")
				.pattern(" # ")
				.define('#', TofuBlocks.METALTOFU.get())
				.define('Z', TofuItems.ZUNDAMA.get())
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.METAL_TOFU_BARS.get(), 16)
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SALT_FURNACE.get())
				.pattern("###")
				.pattern("# #")
				.pattern("SSS")
				.define('#', Items.IRON_INGOT)
				.define('S', Tags.Items.COBBLESTONES)
				.unlockedBy("has_item", has(Items.IRON_INGOT))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.MORIJIO.get(), 3)
				.pattern("S#S")
				.pattern("#D#")
				.pattern(" W ")
				.define('#', TofuTags.Items.SALT)
				.define('S', TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.define('D', Items.DIAMOND)
				.define('W', Items.BOWL)
				.unlockedBy("has_item", has(TofuTags.Items.SALT))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MISC, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 250)), TofuItems.BOTTLE_DASHI.get(), 200, 0.1F)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.DRIED_KELP)
				.unlockedBy("has_item", has(Items.KELP))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.BARREL_MISO.get(), 1)
				.pattern("SSS")
				.pattern("KKK")
				.pattern(" B ")
				.define('S', TofuTags.Items.SALT)
				.define('K', TofuItems.KOUJI.get())
				.define('B', Tags.Items.BARRELS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.KOUJI.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.BARREL_MISOTOFU.get(), 1)
				.pattern("MMM")
				.pattern("TTT")
				.pattern(" B ")
				.define('M', TofuItems.MISO.get())
				.define('T', TofuItems.TOFU_MOMEN.get())
				.define('B', Tags.Items.BARRELS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.BARREL_ADV_TOFUGEM.get(), 1)
				.pattern("RRR")
				.pattern("GGG")
				.pattern(" B ")
				.define('R', Items.REDSTONE)
				.define('G', TofuItems.TOFUGEM.get())
				.define('B', Tags.Items.BARRELS_WOODEN)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.NATTOBED.get(), 1)
				.pattern("SSS")
				.pattern("SSS")
				.pattern("WWW")
				.define('S', TofuItems.SEEDS_SOYBEANS.get())
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.NETHER_NATTOBED.get(), 1)
				.pattern("SSS")
				.pattern("SSS")
				.pattern("WWW")
				.define('S', TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.TOFU_CHIKUWA_BLOCK.get(), 1)
				.pattern(" S ")
				.pattern("S S")
				.pattern(" S ")
				.define('S', TofuItems.TOFU_CHIKUWA.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_CHIKUWA.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.CHIKUWA_BLOCK.get(), 1)
				.pattern(" S ")
				.pattern("S S")
				.pattern(" S ")
				.define('S', TofuItems.CHIKUWA.get())
				.unlockedBy("has_item", has(TofuItems.CHIKUWA.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuBlocks.TOFU_CAKE.get())
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.TOFU_KINU.get())
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFU_KINU.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuBlocks.ZUNDA_TOFU_CAKE.get())
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.TOFU_ZUNDA.get())
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFU_ZUNDA.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuBlocks.SOYCHEESE_TART.get())
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.SOY_CHEESE.get())
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SOY_CHEESE.get()))
				.save(this.output);

		//soimilk
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOYMILK_BUCKET.get())
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.BUCKET)
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOYMILK_BUCKET.get())
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Items.BUCKET)
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(this.output, prefix("bucket_soymilk_okara"));
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOYMILK_NETHER_BUCKET.get())
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.requires(Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOYMILK_SOUL_BUCKET.get())
				.requires(TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.requires(Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_OMINOUS_BOTTLE.get(), 2)
				.requires(TofuItems.SOYMILK_SOUL_BOTTLE.get())
				.requires(Items.OMINOUS_BOTTLE)
				.requires(Items.GLASS_BOTTLE, 2)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_SOUL_BOTTLE.get()))
				.save(this.output);


		//food
		foodCooking(TofuItems.SEEDS_SOYBEANS, TofuItems.SOYBEAN_PARCHED, 0.1F, this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.KINAKO.get())
				.requires(TofuItems.SOYBEAN_PARCHED.get())
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.SOYBEAN_PARCHED.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.CHIKUWA.get(), 4)
				.pattern(" X ")
				.pattern("YZI")
				.pattern(" X ")
				.define('X', ItemTags.FISHES)
				.define('Y', TofuTags.Items.DUST_SALT)
				.define('Z', Tags.Items.EGGS)
				.define('I', TofuItems.STARCH.get())
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_CHIKUWA.get(), 1)
				.requires(TofuItems.TOFU_MOMEN.get())
				.requires(Items.COOKED_COD)
				.unlockedBy("has_item", has(TofuItems.TOFU_MOMEN.get()))
				.save(this.output, prefix("tofu_chikuwa"));
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_CHIKUWA.get(), 1)
				.requires(TofuItems.COOKED_TOFU_FISH.get())
				.unlockedBy("has_item", has(TofuItems.RAW_TOFU_FISH.get()))
				.save(this.output, prefix("tofu_chikuwa_fish"));
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.ZUNDA.get(), 4)
				.requires(TofuItems.BOILED_EDAMAME.get(), 8)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.BOILED_EDAMAME.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.ZUNDAMA.get())
				.requires(TofuItems.ZUNDA.get(), 4)
				.requires(Items.GLOWSTONE_DUST)
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(this.output);
		foodCooking(TofuItems.TOFU_HAMBURG_RAW, TofuItems.TOFU_HAMBURG, 0.25F, this.output);
		foodCooking(TofuItems.RAW_TOFU_FISH, TofuItems.COOKED_TOFU_FISH, 0.2F, this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.MISODENGAKU.get(), 1)
				.pattern(" X ")
				.pattern(" Y ")
				.pattern(" Z ")
				.define('X', TofuItems.MISO.get())
				.define('Y', TofuItems.TOFU_MOMEN.get())
				.define('Z', Items.STICK)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.MISO_CHEESE_DENGAKU.get(), 1)
				.pattern(" X ")
				.pattern("CYC")
				.pattern(" Z ")
				.define('X', TofuItems.MISO.get())
				.define('Y', TofuItems.TOFU_MOMEN.get())
				.define('C', TofuItems.SOY_CHEESE.get())
				.define('Z', Items.STICK)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.TOFUCOOKIE.get(), 8)
				.pattern("X#X")
				.define('#', TofuItems.TOFU_KINU.get())
				.define('X', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFU_KINU.get()))
				.save(this.output, prefix("tofucookie_kinu"));
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.TOFUCOOKIE.get(), 8)
				.pattern("X#X")
				.define('#', TofuItems.TOFU_MOMEN.get())
				.define('X', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFU_MOMEN.get()))
				.save(this.output, prefix("tofucookie_momen"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.TTTBURGER.get(), 1)
				.pattern(" X ")
				.pattern("###")
				.pattern(" X ")
				.define('#', TofuItems.TOFU_FRIED_POUCH.get())
				.define('X', Items.BREAD)
				.unlockedBy("has_item", has(TofuItems.TOFU_KINU.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.MEAT_WRAPPED_YUBA.get())
				.requires(Items.COOKED_CHICKEN)
				.requires(TofuItems.YUBA.get())
				.unlockedBy("has_item", has(TofuItems.YUBA.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYSTICK.get())
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(TofuTags.Items.SALT)
				.unlockedBy("has_item", has(TofuTags.Items.SALT))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 200)), TofuItems.MISOSOUP.get(), 300, 0.4F)
				.requires(TofuTags.Items.TOFU)
				.requires(TofuItems.MISO.get())
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.MOYASHIITAME.get(), 300, 0.6F)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuTags.Items.DUST_SALT)
				.requires(TofuItems.SPROUTS.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SPROUTS.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.MOYASHIOHITASHI.get(), 300, 0.6F)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.SPROUTS.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SPROUTS.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SALTYMELON.get())
				.requires(TofuTags.Items.SALT)
				.requires(Items.MELON_SLICE)
				.unlockedBy("has_item", has(TofuTags.Items.SALT))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_BOTTLE.get(), 3)
				.requires(TofuItems.SOYMILK_BUCKET.get())
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);


		SpecialRecipeBuilder.special(() -> new FluidBucketRecipe(Ingredient.of(lookup.getOrThrow(TofuTags.Items.SOYBEAN)), TofuFluids.SOYMILK))
				.save(this.output, prefix("fluid_bucket_soymilk"));
		SpecialRecipeBuilder.special(() -> new FluidBucketRecipe(Ingredient.of(TofuItems.SEEDS_SOYBEANS_NETHER.get()), TofuFluids.SOYMILK_HELL))
				.save(this.output, prefix("fluid_bucket_soymilk_hell"));
		SpecialRecipeBuilder.special(() -> new FluidBucketRecipe(Ingredient.of(TofuItems.SEEDS_SOYBEANS_SOUL.get()), TofuFluids.SOYMILK_SOUL))
				.save(this.output, prefix("fluid_bucket_soymilk_soul"));
		SpecialRecipeBuilder.special(() -> new BucketToBottleRecipe(new ItemStackTemplate(TofuItems.SOYMILK_BOTTLE, 3), TofuFluids.SOYMILK))
				.save(this.output, prefix("fluid_bucket_to_soymilk_bottle"));
		SpecialRecipeBuilder.special(() -> new BucketToBottleRecipe(new ItemStackTemplate(TofuItems.SOYMILK_HELL_BOTTLE, 3), TofuFluids.SOYMILK_HELL))
				.save(this.output, prefix("fluid_bucket_to_soymilk_hell_bottle"));
		SpecialRecipeBuilder.special(() -> new BucketToBottleRecipe(new ItemStackTemplate(TofuItems.SOYMILK_SOUL_BOTTLE, 3), TofuFluids.SOYMILK_SOUL))
				.save(this.output, prefix("fluid_bucket_to_soymilk_soul_bottle"));


		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_APPLE_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.APPLE)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_COCOA_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.COCOA_BEANS)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_FRUITS_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.APPLE)
				.requires(Items.SWEET_BERRIES)
				.requires(Items.GLOW_BERRIES)
				.requires(Items.CHORUS_FRUIT)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_HONEY_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.HONEY_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_KINAKO_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KINAKO.get())
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_PUDDING_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Tags.Items.EGGS)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_PUMPKIN_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.PUMPKIN)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_RAMUNE_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.LIGHT_BLUE_DYE)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_SAKURA_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Blocks.CHERRY_LEAVES)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_ANNIN_BOTTLE.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.KYONINSO.get())
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.CRIMSON_BOTTLE.get(), 3)
				.requires(TofuItems.SHROOM_BOTTLE.get())
				.requires(Items.CRIMSON_FUNGUS)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.SHROOM_BOTTLE.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.WARPED_BOTTLE.get(), 3)
				.requires(TofuItems.SHROOM_BOTTLE.get())
				.requires(Items.WARPED_FUNGUS)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.SHROOM_BOTTLE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.KINAKO_MANJU.get(), 2)
				.requires(TofuItems.KINAKO.get())
				.requires(Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.KINAKO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.ZUNDA_MANJU.get(), 2)
				.requires(TofuItems.ZUNDA.get())
				.requires(Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.NETHER_MANJU.get(), 2)
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.requires(Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOUL_MANJU.get(), 2)
				.requires(TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.requires(Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.KINAKO_MOCHI.get(), 1)
				.requires(TofuItems.KINAKO.get())
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.KINAKO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SESAME_OHAGI.get(), 1)
				.requires(TofuItems.SEEDS_SESAME.get())
				.requires(Items.SUGAR)
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.SESAME_OHAGI.get()))
				.save(this.output);



		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.CRIMSON_SOUP.get(), 1)
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get(), 2)
				.requires(Items.CRIMSON_FUNGUS)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.ZUNDA_MOCHI.get(), 1)
				.requires(TofuItems.ZUNDA.get())
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.ZUNDA_MOCHI.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.PUDDING.get(), 1)
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.GELATIN.get())
				.requires(Items.MILK_BUCKET)
				.requires(Items.SUGAR)
				.requires(TofuItems.GLASS_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.GELATIN.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.PUDDING_SOYMILK.get(), 1)
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.GELATIN.get())
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.SUGAR)
				.requires(TofuItems.GLASS_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.GELATIN.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.NIKUJAGA.get(), 400, 0.8F)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(Items.SUGAR)
				.requires(Items.POTATO)
				.requires(Items.CARROT)
				.requires(Items.COOKED_BEEF)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 200)), new ItemStackTemplate(TofuItems.ONIGIRI.get(), 2), 100, 0.05F)
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.RICE.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.ONIGIRI_SALT.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.SALT.get())
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.YAKIONIGIRI_MISO.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.MISO.get())
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.YAKIONIGIRI_SHOYU.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_BURGER.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(Items.COOKED_BEEF)
				.unlockedBy("has_item", has(TofuItems.ONIGIRI.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_NATTO.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NATTO.get())
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.NATTO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_NATTO_LEEK.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NATTO.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.NATTO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_NETHER_NATTO.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NETHER_NATTO.get())
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.NETHER_NATTO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_NETHER_NATTO_LEEK.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.NETHER_NATTO.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.NETHER_NATTO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_TOFU.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuTags.Items.TOFU)
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.ONIGIRI.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RICE_SOBORO_TOFU.get(), 1)
				.requires(TofuItems.ONIGIRI.get())
				.requires(TofuItems.TOFU_MINCED.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_MINCED.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.GOHEIMOCHI.get())
				.pattern("#")
				.pattern("M")
				.pattern("S")
				.define('#', TofuItems.ONIGIRI.get())
				.define('M', TofuItems.MISO.get())
				.define('S', Items.STICK)
				.unlockedBy("has_item", has(TofuItems.MISO.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.INARI.get(), 1)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.OAGE.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.ONIGIRI.get())
				.unlockedBy("has_item", has(TofuItems.OAGE.get()))
				.save(this.output);

		//おからレシピを要実装
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.OKARASTICK.get(), 3)
				.pattern(" # ")
				.pattern(" E ")
				.pattern(" W ")
				.define('#', TofuItems.OKARA.get())
				.define('E', Tags.Items.EGGS)
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.OKARA_DONUT.get(), 4)
				.pattern("#W#")
				.pattern("WEW")
				.pattern("#W#")
				.define('#', TofuItems.OKARA.get())
				.define('E', Tags.Items.EGGS)
				.define('W', Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOBOROTOFUSAUTE.get(), 1)
				.requires(TofuItems.TOFU_MINCED.get())
				.requires(Items.COOKED_PORKCHOP)
				.requires(Items.CARROT)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFU_MINCED.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOY_CHOCOLATE.get(), 6)
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.COCOA_BEANS)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(), 6)
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Items.COCOA_BEANS)
				.requires(Items.SUGAR)
				.requires(TofuItems.LEEK.get())
				.unlockedBy("has_item", has(TofuItems.SOYMILK_BUCKET.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.MINCEDPOTATO.get(), 1)
				.requires(Items.POTATO)
				.requires(TofuItems.FILTERCLOTH.get())
				.unlockedBy("has_item", has(Items.POTATO))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SEEDS_CHILI.get(), 1)
				.requires(TofuItems.CHILI.get())
				.unlockedBy("has_item", has(TofuItems.CHILI.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.DOUBANJIANG.get(), 1)
				.requires(TofuItems.CHILI.get())
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.SUGAR)
				.requires(TofuItems.SALT.get())
				.unlockedBy("has_item", has(TofuItems.CHILI.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.MABODOFU.get(), 400, 0.8F)
				.requires(TofuTags.Items.TOFU)
				.requires(TofuItems.STARCH.get())
				.requires(Items.PORKCHOP)
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.DOUBANJIANG.get())
				.unlockedBy("has_item", has(TofuItems.DOUBANJIANG.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.NANBAN.get(), 400, 0.8F)
				.requires(Items.COOKED_CHICKEN)
				.requires(Tags.Items.EGGS)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.CHILI.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.NANBANTOFU.get(), 400, 0.8F)
				.requires(TofuTags.Items.TOFU)
				.requires(Tags.Items.EGGS)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.CHILI.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.FUKUMENI.get(), 8)
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(TofuItems.TOFU_DRIED.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_DRIED.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 500)), TofuItems.KOYADOFUSTEW.get(), 400, 0.8F)
				.requires(TofuItems.TOFU_DRIED.get())
				.requires(Items.BROWN_MUSHROOM)
				.requires(TofuItems.BOTTLE_DASHI.get())
				.requires(Items.BOWL)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.GLASS_BOWL.get(), 2)
				.pattern("# #")
				.pattern(" # ")
				.define('#', Items.GLASS_PANE)
				.unlockedBy("has_item", has(Items.GLASS))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFUSOMEN.get(), 4)
				.requires(TofuItems.TOFU_KINU.get())
				.requires(TofuItems.ROLLINGPIN.get())
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.STARCH.get())
				.unlockedBy("has_item", has(TofuItems.ROLLINGPIN.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFUSOMENBOWL_GLASS.get(), 1)
				.requires(TofuItems.TOFUSOMEN.get())
				.requires(TofuItems.GLASS_BOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_DASHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUSOMEN.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.TASTYBEEFSTEW.get(), 300, 0.6F)
				.requires(Items.BROWN_MUSHROOM)
				.requires(Items.RED_MUSHROOM)
				.requires(Items.COOKED_BEEF)
				.requires(TofuTags.Items.DUST_SALT)
				.requires(Items.MILK_BUCKET)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.TASTYSTEW.get(), 300, 0.6F)
				.requires(Items.BROWN_MUSHROOM)
				.requires(Items.RED_MUSHROOM)
				.requires(Ingredient.of(Items.COOKED_CHICKEN, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_RABBIT))
				.requires(TofuTags.Items.DUST_SALT)
				.requires(Items.MILK_BUCKET)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOUL_HIYAYAKKO_GLASS.get(), 1)
				.requires(TofuItems.TOFU_SOUL.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.GLASS_BOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.HIYAYAKKO_GLASS.get(), 1)
				.requires(TofuItems.TOFU_KINU.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.GLASS_BOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.NATTOHIYAYAKKO_GLASS.get(), 1)
				.requires(TofuItems.NATTO.get())
				.requires(TofuItems.TOFU_KINU.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.GLASS_BOWL.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYSAUSE.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.WARABI_MOCHI.get(), 1)
				.requires(Items.SUGAR)
				.requires(TofuItems.KINAKO.get())
				.requires(TofuItems.STARCH.get())
				.requires(TofuItems.GLASS_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_ANNIN.get(), 1)
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.GELATIN.get())
				.requires(Items.MILK_BUCKET)
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SALTPAN.get())
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.COBBLESTONE_SLAB)
				.unlockedBy("has_item", has(Blocks.COBBLESTONE_SLAB))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SALTPAN.get())
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.COBBLED_DEEPSLATE_SLAB)
				.unlockedBy("has_item", has(Blocks.COBBLED_DEEPSLATE_SLAB))
				.save(this.output, prefix("deepslate_saltpan"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SALTPAN.get())
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.BLACKSTONE_SLAB)
				.unlockedBy("has_item", has(Blocks.BLACKSTONE_SLAB))
				.save(this.output, prefix("blackstone_saltpan"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.TOFUCHEST.get())
				.pattern("###")
				.pattern("# #")
				.pattern("###")
				.define('#', TofuItems.TOFU_ISHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_ISHI.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.TOFUSCOOP.get())
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('#', Blocks.IRON_BARS)
				.unlockedBy("has_item", has(Blocks.IRON_BARS))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.TOFUSCOOP.get())
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('#', TofuBlocks.METAL_TOFU_BARS)
				.unlockedBy("has_item", has(TofuBlocks.METAL_TOFU_BARS))
				.save(this.output, prefix("metal_tofu_scoop"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.FUKUMAME.get())
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SOYBEAN_PARCHED.get())
				.unlockedBy("has_item", has(TofuItems.SOYBEAN_PARCHED.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.NETHER_FUKUMAME.get())
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.TOOLS, TofuItems.INFERNO_NETHER_FUKUMAME.get(), 1)
				.requires(TofuItems.NETHER_FUKUMAME.get())
				.requires(Items.BLAZE_POWDER)
				.unlockedBy("has_item", has(TofuItems.NETHER_FUKUMAME.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.SOUL_FUKUMAME.get())
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.ZUNDA_ARROW.get(), 8)
				.pattern("###")
				.pattern("#X#")
				.pattern("###")
				.define('X', TofuItems.ZUNDAMA.get())
				.define('#', Items.ARROW)
				.unlockedBy("has_item", has(TofuItems.ZUNDAMA.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get())
				.pattern("# ")
				.pattern(" M")
				.define('#', Items.FISHING_ROD)
				.define('M', TofuBlocks.ZUNDA_TOFU_MUSHROOM.get())
				.unlockedBy("has_item", has(TofuBlocks.ZUNDA_TOFU_MUSHROOM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.TOFUBED.get())
				.pattern("###")
				.pattern("PPP")
				.define('#', TofuItems.YUBA.get())
				.define('P', ItemTags.PLANKS)
				.unlockedBy("has_item", has(TofuItems.YUBA.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.FOODPLATE.get())
				.pattern("CCC")
				.define('C', Items.CLAY_BALL)
				.unlockedBy("has_item", has(Items.CLAY_BALL))
				.save(this.output);

		tofuBlockItem(this.output, TofuBlocks.SESAMETOFU, TofuItems.TOFU_SESAME);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_SESAME, TofuBlocks.SESAMETOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_SESAME, TofuBlocks.SESAMETOFU);
		tofuBlockItem(this.output, TofuBlocks.STRAWBERRY_TOFU, TofuItems.TOFU_STRAWBERRY);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_STRAWBERRY, TofuBlocks.STRAWBERRY_TOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_STRAWBERRY, TofuBlocks.STRAWBERRY_TOFU);

		tofuBlockItem(this.output, TofuBlocks.FRIED_TOFU, TofuItems.TOFU_FRIED);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_FRIED, TofuBlocks.FRIED_TOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_FRIED, TofuBlocks.FRIED_TOFU);

		tofuBlockItem(this.output, TofuBlocks.FRIED_POUCH_TOFU, TofuItems.TOFU_FRIED_POUCH);
		makeStairs(this.output, TofuBlocks.TOFU_STAIR_FRIED_POUCH, TofuBlocks.FRIED_POUCH_TOFU);
		makeSlab(this.output, TofuBlocks.TOFU_SLAB_FRIED_POUCH, TofuBlocks.FRIED_POUCH_TOFU);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.KOUJI_BASE.get(), 1)
				.requires(TofuTags.Items.SOYBEAN)
				.requires(Tags.Items.CROPS_WHEAT)
				.unlockedBy("has_item", has(TofuTags.Items.SOYBEAN))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.TOFU_SHIELD.get())
				.pattern("###")
				.pattern("###")
				.pattern(" # ")
				.define('#', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.ROLLINGPIN.get())
				.pattern("  S")
				.pattern(" # ")
				.pattern("S  ")
				.define('#', ItemTags.PLANKS)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_item", has(Items.STICK))
				.save(this.output);

		// Rice Block
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.RICE_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.RICE.get())
				.unlockedBy("has_item", has(TofuItems.RICE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.RICE.get(), 9)
				.requires(TofuTags.Items.RICE_BLOCK)
				.unlockedBy("has_item", has(TofuTags.Items.RICE_BLOCK))
				.save(this.output);

		//soy beans
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SOYBEANS_SEEDS_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.SEEDS_SOYBEANS.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SEEDS_SOYBEANS.get(), 9)
				.requires(TofuBlocks.SOYBEANS_SEEDS_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.SOYBEANS_SEEDS_BLOCK.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SEEDS_SOYBEANS_NETHER.get(), 9)
				.requires(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SEEDS_SOYBEANS_SOUL.get(), 9)
				.requires(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get()))
				.save(this.output);

		//soy cheese block

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SOY_CHEESE_BLOCK.get())
				.pattern("##")
				.pattern("##")
				.define('#', TofuItems.SOY_CHEESE.get())
				.unlockedBy("has_item", has(TofuItems.SOY_CHEESE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOY_CHEESE.get(), 4)
				.requires(TofuBlocks.SOY_CHEESE_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.SOY_CHEESE_BLOCK.get()))
				.save(this.output, prefix("to_soy_cheese"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get())
				.pattern("##")
				.pattern("##")
				.define('#', TofuItems.SOY_NETHER_CHEESE.get())
				.unlockedBy("has_item", has(TofuItems.SOY_NETHER_CHEESE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOY_NETHER_CHEESE.get(), 4)
				.requires(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get()))
				.save(this.output, prefix("to_soy_cheese_nether"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get())
				.pattern("##")
				.pattern("##")
				.define('#', TofuItems.SOY_SOUL_CHEESE.get())
				.unlockedBy("has_item", has(TofuItems.SOY_SOUL_CHEESE.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.SOY_SOUL_CHEESE.get(), 4)
				.requires(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get())
				.unlockedBy("has_item", has(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get()))
				.save(this.output, prefix("to_soy_cheese_soul"));


		// kinako_bread
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.KINAKO_BREAD.get(), 3)
				.requires(TofuItems.KINAKO.get())
				.requires(Items.BREAD)
				.requires(Items.BREAD)
				.requires(Items.BREAD)
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(this.output);

		// edamame_tempura
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MISC, Optional.empty(), TofuItems.EDAMAME_TEMPLA.get(), 6, 300, 0.3F)
				.requires(TofuItems.BOILED_EDAMAME.get())
				.requires(TofuItems.BOILED_EDAMAME.get())
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.BOTTLE_SOYOIL.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.NEGIMA.get(), 1)
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.SOYMEAT.get())
				.requires(Items.STICK)
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.unlockedBy("has_item", has(TofuItems.SOYMEAT.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOY_KARAAGE.get(), 1)
				.requires(TofuItems.SOYMEAT.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.unlockedBy("has_item", has(TofuItems.SOYMEAT.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMEATDON.get(), 1)
				.requires(TofuItems.SOYMEAT.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.LEEK.get())
				.requires(TofuItems.RICE.get())
				.requires(TofuItems.CERAMIC_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.SOYMEAT.get()))
				.save(this.output);


		/*ShapedRecipeBuilder.shaped(TofuItems.TF_CAPACITOR.get())
				.pattern(" M ")
				.pattern("RGR")
				.pattern(" M ")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('R', Items.REDSTONE)
				.define('G', TofuItems.TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(TofuItems.TF_CIRCUIT.get())
				.pattern("RIR")
				.pattern("TTT")
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('R', Items.REDSTONE)
				.define('I', TofuItems.TOFU_ISHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(TofuItems.TF_COIL.get())
				.pattern("RRR")
				.pattern("TTT")
				.pattern("RRR")
				.define('T', TofuItems.TOFU_ISHI.get())
				.define('R', Items.REDSTONE)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(TofuItems.TF_OSCILLATOR.get())
				.pattern("TCT")
				.pattern("M M")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('T', TofuItems.TOFU_KINU.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(TofuBlocks.TF_STORAGE.get())
				.pattern("CCC")
				.pattern("GTG")
				.define('G', Blocks.GLASS)
				.define('T', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TF_CAPACITOR.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
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
				.save(this.output);

		ShapedRecipeBuilder.shaped(TofuBlocks.ANTENNA_BASIC.get())
				.pattern(" P ")
				.pattern("LOR")
				.pattern("TCT")
				.define('P', TofuItems.LEEK.get())
				.define('R', TofuItems.TF_CAPACITOR.get())
				.define('O', TofuItems.TF_OSCILLATOR.get())
				.define('L', TofuItems.TF_COIL.get())
				.define('T', TofuItems.TOFU_METAL.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);*/

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SPROUTSJAR.get())
				.pattern("G")
				.pattern("W")
				.define('G', Blocks.TINTED_GLASS)
				.define('W', ItemTags.WOOL_CARPETS)
				.unlockedBy("has_item", has(Blocks.TINTED_GLASS))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.HELL_MABOU.get())
				.requires(TofuItems.TOFU_HELL.get())
				.requires(TofuItems.DOUBANJIANG.get())
				.requires(TofuItems.CHILI.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.DOUBANJIANG.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.RED_SOUP.get())
				.requires(TofuItems.TOFU_HELL.get())
				.requires(Items.COOKED_COD)
				.requires(Items.CARROT)
				.requires(Items.BEETROOT)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFU_HELL.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.HELL_RED_SOUP.get())
				.requires(TofuItems.TOFU_HELL.get())
				.requires(Items.COOKED_COD)
				.requires(Items.CARROT)
				.requires(Items.BEETROOT)
				.requires(Items.BOWL)
				.requires(TofuItems.DOUBANJIANG.get())
				.requires(TofuItems.CHILI.get(), 2)
				.unlockedBy("has_item", has(TofuItems.DOUBANJIANG.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SUKIYAKI.get())
				.requires(TofuItems.TOFU_GRILLED.get(), 2)
				.requires(Items.COOKED_BEEF)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.TOFU_GRILLED.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.FOOD, TofuItems.TOFU_BUNS_BURGER.get())
				.pattern("G")
				.pattern("M")
				.pattern("G")
				.define('G', TofuItems.TOFU_GRILLED.get())
				.define('M', TofuItems.TOFU_HAMBURG.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_HAMBURG.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 50)), new ItemStackTemplate(TofuItems.STEAMED_BREAD.get()))
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Tags.Items.EGGS)
				.requires(Items.SUGAR)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.HONEY_BOTTLE)
				.unlockedBy("has_item", has(TofuTags.Items.MILK_SOYMILK))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 50)), new ItemStackTemplate(TofuItems.STEAMED_BREAD_COCOA.get()))
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Tags.Items.EGGS)
				.requires(Items.SUGAR)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Items.COCOA_BEANS)
				.unlockedBy("has_item", has(TofuTags.Items.MILK_SOYMILK))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 50)), new ItemStackTemplate(TofuItems.STEAMED_BREAD_SESAME.get()))
				.requires(TofuTags.Items.MILK_SOYMILK)
				.requires(Tags.Items.EGGS)
				.requires(Items.SUGAR)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(TofuItems.SEEDS_SESAME.get())
				.unlockedBy("has_item", has(TofuTags.Items.MILK_SOYMILK))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, TofuItems.SANBUZHAN.get())
				.requires(Tags.Items.EGGS)
				.requires(TofuItems.STARCH.get())
				.requires(Items.SUGAR)
				.requires(TofuItems.BOTTLE_SOYOIL.get())
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.STARCH.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_SOUL_BOTTLE.get(), 3)
				.requires(TofuItems.SOYMILK_SOUL_BUCKET.get())
				.requires(Items.GLASS_BOTTLE, 3)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_SOUL_BUCKET.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_HELL_BOTTLE.get(), 3)
				.requires(TofuItems.SOYMILK_NETHER_BUCKET.get())
				.requires(Items.GLASS_BOTTLE, 3)
				.unlockedBy("has_item", has(TofuItems.SOYMILK_NETHER_BUCKET.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_PALE_BOTTLE.get(), 3)
				.requires(TofuItems.SEEDS_SOYBEANS_PALE.get())
				.requires(Items.GLASS_BOTTLE, 3)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_PALE.get()))
				.save(this.output);
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SOYMILK_PALE_GLOW_BOTTLE.get(), 3)
				.requires(TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get())
				.requires(Items.GLASS_BOTTLE, 3)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get()))
				.save(this.output);

		makeSign(TofuBlocks.TOFU_STEM_SIGN, TofuBlocks.TOFU_STEM_PLANKS).save(this.output);
		makeSign(TofuBlocks.LEEK_SIGN, TofuBlocks.LEEK_PLANKS).save(this.output);
		makeSign(TofuBlocks.LEEK_GREEN_SIGN, TofuBlocks.LEEK_GREEN_PLANKS).save(this.output);

		makeHangingSign(TofuBlocks.SPROUT_HANGING_SIGN, TofuBlocks.SPROUT_STEM).save(this.output);
		makeHangingSign(TofuBlocks.TOFU_STEM_HANGING_SIGN, TofuBlocks.TOFU_STEM).save(this.output);
		makeHangingSign(TofuBlocks.LEEK_HANGING_SIGN, TofuBlocks.LEEK_STEM).save(this.output);
		makeHangingSign(TofuBlocks.LEEK_GREEN_HANGING_SIGN, TofuBlocks.LEEK_GREEN_STEM).save(this.output);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.BREWING, Blocks.CAULDRON, 1)
				.pattern("# #")
				.pattern("# #")
				.pattern("###")
				.define('#', TofuBlocks.METALTOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(this.output, prefix("tofumetal_with_cauldron"));
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.DECORATIONS, Blocks.SMITHING_TABLE, 1)
				.pattern("##")
				.pattern("PP")
				.pattern("PP")
				.define('#', TofuBlocks.METALTOFU.get())
				.define('P', ItemTags.PLANKS)
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(this.output, prefix("tofumetal_with_smithing_table"));
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.DECORATIONS, Blocks.BLAST_FURNACE, 1)
				.pattern("###")
				.pattern("#F#")
				.pattern("SSS")
				.define('#', TofuBlocks.METALTOFU.get())
				.define('S', Blocks.SMOOTH_STONE)
				.define('F', Blocks.FURNACE)
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(this.output, prefix("tofumetal_with_blast_furnace"));

		//TF Energy Blocks Start
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.TOFU_DETECTOR.get(), 1)
				.pattern("BBB")
				.pattern("GRR")
				.pattern("BBB")
				.define('G', TofuItems.TOFUGEM.get())
				.define('R', Items.REDSTONE)
				.define('B', Tags.Items.COBBLESTONES)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TF_CAPACITOR.get())
				.pattern(" M ")
				.pattern("RGR")
				.pattern(" M ")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('R', TofuItems.TOFU_GEM_DUST.get())
				.define('G', TofuItems.TOFUGEM.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TF_CIRCUIT.get())
				.pattern("RIR")
				.pattern("TTT")
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('R', TofuItems.TOFU_GEM_DUST.get())
				.define('I', TofuItems.TOFU_ISHI.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TF_COIL.get())
				.pattern("RRR")
				.pattern("TTT")
				.pattern("RRR")
				.define('T', TofuItems.TOFU_ISHI.get())
				.define('R', TofuItems.TOFU_GEM_DUST.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TF_OSCILLATOR.get())
				.pattern("TCT")
				.pattern("M M")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('T', TofuItems.TOFU_KINU.get())
				.define('C', Items.QUARTZ)
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.TOFU_GEM_DUST.get(), 2)
				.requires(TofuItems.TOFUGEM.get())
				.requires(TofuItems.TOFU_MINCED.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.ANTENNA_BASIC.get())
				.pattern(" P ")
				.pattern(" C ")
				.pattern("TTT")
				.define('P', TofuItems.LEEK.get())
				.define('C', TofuItems.TF_DEVICE.get())
				.define('T', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.ANTENNA_ADVANCE.get())
				.pattern("T")
				.pattern("G")
				.pattern("A")
				.define('G', TofuItems.ADVANCE_TOFUGEM.get())
				.define('A', TofuBlocks.ANTENNA_BASIC.get())
				.define('T', TofuItems.TOFU_DIAMOND_NUGGET.get())
				.unlockedBy("has_item", has(TofuBlocks.ANTENNA_BASIC.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.TF_STORAGE.get())
				.pattern("CCC")
				.pattern("GTG")
				.define('G', Blocks.GLASS)
				.define('T', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TF_CAPACITOR.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.TF_COLLECTOR.get())
				.pattern("HCH")
				.pattern("AMA")
				.define('A', TofuItems.ADVANCE_TOFUGEM.get())
				.define('M', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TF_DEVICE.get())
				.define('H', Blocks.HOPPER)
				.unlockedBy("has_item", has(TofuItems.ADVANCE_TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TOFU_CORE.get())
				.pattern("MCM")
				.pattern("TTT")
				.pattern("MRM")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('C', TofuItems.TF_CIRCUIT.get())
				.define('T', TofuItems.TOFU_GEM_DUST.get())
				.define('R', TofuItems.TF_CAPACITOR.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_GEM_DUST.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TF_DEVICE.get())
				.pattern("TTT")
				.pattern("LCO")
				.pattern("TTT")
				.define('O', TofuItems.TF_OSCILLATOR.get())
				.define('L', TofuItems.TF_COIL.get())
				.define('C', TofuItems.TOFU_CORE.get())
				.define('T', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_CORE.get()))
				.save(this.output);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuItems.TF_BATTERY.get())
				.pattern("MRM")
				.pattern("MTM")
				.pattern("MTM")
				.define('M', TofuItems.TOFU_METAL.get())
				.define('T', TofuItems.TOFUGEM.get())
				.define('R', TofuItems.TOFU_GEM_DUST.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.TF_OVEN.get())
				.pattern(" C ")
				.pattern("LML")
				.pattern(" T ")
				.define('M', TofuBlocks.METALTOFU.get())
				.define('T', TofuBlocks.HELLTOFU_BRICK.get())
				.define('C', TofuItems.TOFU_CORE.get())
				.define('L', TofuItems.TF_COIL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_CORE.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.TF_TOFU_MAKER.get())
				.pattern("SCS")
				.pattern("M M")
				.pattern("LML")
				.define('M', TofuBlocks.METALTOFU.get())
				.define('C', TofuItems.TOFU_CORE.get())
				.define('S', TofuItems.SOY_FORCE_SHARD.get())
				.define('L', TofuItems.TF_COIL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_CORE.get()))
				.save(this.output);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.REDSTONE, TofuBlocks.TOFU_WORK_STATION.get())
				.pattern("MM")
				.pattern("TT")
				.pattern("TT")
				.define('T', TofuBlocks.ISHITOFU.get())
				.define('M', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFUGEM.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.SALT_BLOCK.get())
				.pattern("SSS")
				.pattern("SSS")
				.pattern("SSS")
				.define('S', TofuItems.SALT.get())
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.SALT.get(), 9)
				.requires(TofuBlocks.SALT_BLOCK.get())
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.OKARA_BLOCK.get())
				.pattern("SS")
				.pattern("SS")
				.define('S', TofuItems.OKARA.get())
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.FOOD, TofuItems.OKARA.get(), 4)
				.requires(TofuBlocks.OKARA_BLOCK.get())
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), new ItemStackTemplate(TofuBlocks.GIANT_OKARA_DONUT.get().asItem(), 2), 400, 1)
				.requires(TofuBlocks.OKARA_BLOCK.get(), 2)
				.requires(Blocks.HAY_BLOCK, 1)
				.requires(Ingredient.of(lookup.getOrThrow(Tags.Items.EGGS)), 2)
				.unlockedBy("has_item", has(TofuItems.OKARA.get()))
				.save(this.output);


		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.TOFU_CRAFTERS_BOOK.get(), 1)
				.requires(TofuItems.SEEDS_SOYBEANS.get())
				.requires(Items.BOOK)
				.unlockedBy("inside_of", has(TofuItems.SEEDS_SOYBEANS.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.TOFU_POT.get())
				.pattern("S S")
				.pattern("# #")
				.pattern("###")
				.define('S', TofuItems.TOFU_ISHI.get())
				.define('#', TofuBlocks.METALTOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.METALTOFU.get()))
				.save(this.output);


		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.TF_CRAFTING_TABLE.get())
				.pattern("B")
				.pattern("T")
				.pattern("E")
				.define('T', TofuBlocks.METALTOFU)
				.define('B', Blocks.CRAFTING_TABLE)
				.define('E', TofuItems.TOFU_CORE.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_CORE.get()))
				.save(this.output);

		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, TofuItems.ZUNDAMA.get(), 1, 100)
				.requires(TofuItems.ZUNDA.get(), 4)
				.unlockedBy("has_item", has(TofuItems.ZUNDA.get()))
				.save(this.output, prefix("zundama_with_tf"));

		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, TofuItems.ZUNDA_INGOT.get(), 1, 400)
				.requires(TofuItems.ZUNDAMA.get(), 4)
				.requires(TofuItems.TOFU_ISHI.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDAMA.get()))
				.save(this.output);
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, TofuItems.ZUNDA_ALLOY_TOFU.get(), 1, 400)
				.requires(TofuItems.ZUNDA_INGOT.get(), 2)
				.requires(TofuItems.TOFU_METAL.get(), 2)
				.unlockedBy("has_item", has(TofuItems.ZUNDA_INGOT.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.get())
				.pattern("##")
				.pattern("##")
				.define('#', TofuItems.ZUNDA_ALLOY_TOFU.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDA_ALLOY_TOFU.get()))
				.save(this.output, "zunda_alloy_to_block");
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.MISC, TofuItems.ZUNDA_ALLOY_TOFU.get(), 9)
				.requires(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.get())
				.unlockedBy("has_item", has(TofuItems.ZUNDA_ALLOY_TOFU.get()))
				.save(this.output, prefix("revert_to_zunda_alloy"));


		TFShapedRecipeBuilder.shaped(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.ZUNDA_BOW.get()), 1000)
				.define('S', TofuItems.ZUNDAMA.get())
				.define('Z', TofuItems.ZUNDA_INGOT.get())
				.define('G', TofuItems.SOY_FORCE_SHARD.get())
				.pattern(" ZS")
				.pattern("ZGS")
				.pattern(" ZS")
				.unlockedBy("has_item", has(TofuItems.ZUNDA_INGOT.get()))
				.save(this.output);
		TFShapedRecipeBuilder.shaped(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.ZUNDA_TOTEM.get()), 1000)
				.define('D', TofuItems.TOFU_DIAMOND_NUGGET.get())
				.define('Z', TofuItems.ZUNDA_INGOT.get())
				.define('G', TofuItems.SOY_FORCE_SHARD.get())
				.pattern("ZDZ")
				.pattern("ZGZ")
				.pattern(" Z ")
				.unlockedBy("has_item", has(TofuItems.SOY_FORCE_SHARD.get()))
				.save(this.output);

		TFShapedRecipeBuilder.shaped(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.ZUNDA_ALLOY_TOFU_SWORD.get()), 1000)
				.define('Z', TofuItems.ZUNDA_ALLOY_TOFU.get())
				.define('G', TofuItems.SOY_FORCE_SHARD.get())
				.pattern("Z")
				.pattern("Z")
				.pattern("G")
				.unlockedBy("has_item", has(TofuItems.ZUNDA_ALLOY_TOFU.get()))
				.save(this.output);


		TFShapedRecipeBuilder.shaped(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.TOFUSTICK.get()), 1000)
				.define('T', TofuItems.TOFU_METAL.get())
				.define('G', TofuItems.TOFUGEM.get())
				.pattern("G")
				.pattern("T")
				.pattern("T")
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE.get()))
				.save(this.output);

		ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, TofuBlocks.ISHITOFU_BRICK.get(), 4)
				.pattern("MM")
				.pattern("MM")
				.define('M', TofuBlocks.TOFUSLATE.get())
				.unlockedBy("has_item", has(TofuBlocks.TOFUSLATE.get())).save(this.output, prefix("slate_tofu_ishi"));
		cuttingRecipe(this.output, () -> TofuBlocks.TOFUSLATE, () -> TofuBlocks.ISHITOFU_BRICK, 1);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, Optional.empty(), TofuItems.MONSTER_JERKY.get(), 340, 0.1F)
				.requires(Items.ROTTEN_FLESH)
				.requires(TofuItems.SALT.get(), 4)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.FAST_FOOD, Optional.empty(), TofuItems.MONSTER_PORK_JERKY.get(), 340, 0.1F)
				.requires(TofuItems.ROTTEN_PORK.get())
				.requires(TofuItems.SALT.get(), 4)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MISC, Optional.of(SizedFluidIngredient.of(Fluids.WATER, 200)), TofuItems.BOILED_EDAMAME.get(), 6, 200, 0.2F)
				.requires(TofuItems.EDAMAME.get(), 6)
				.unlockedBy("has_item", has(TofuItems.EDAMAME.get()))
				.save(this.output);

		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.KINUTOFU.get(), 1)
				.requires(TofuBlocks.WAXED_KINUTOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.WAXED_KINUTOFU.get()))
				.save(this.output, prefix("wax_out_kinu"));
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.MOMENTOFU.get(), 1)
				.requires(TofuBlocks.WAXED_MOMENTOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.WAXED_MOMENTOFU.get()))
				.save(this.output, prefix("wax_out_momen"));
		ShapelessRecipeBuilder.shapeless(lookup, RecipeCategory.BUILDING_BLOCKS, TofuBlocks.ISHITOFU.get(), 1)
				.requires(TofuBlocks.WAXED_ISHITOFU.get())
				.unlockedBy("has_item", has(TofuBlocks.WAXED_ISHITOFU.get()))
				.save(this.output, prefix("wax_out_ishi"));

		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.TOFU_METAL_BOWL.get(), 5)
				.pattern("# #")
				.pattern("# #")
				.pattern(" # ")
				.define('#', TofuItems.TOFU_METAL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL.get()))
				.save(this.output);
		ShapedRecipeBuilder.shaped(lookup, RecipeCategory.MISC, TofuItems.CERAMIC_BOWL.get(), 3)
				.pattern("# #")
				.pattern(" # ")
				.define('#', Items.CLAY_BALL)
				.unlockedBy("has_item", has(Items.CLAY_BALL))
				.save(this.output);


		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.GRATIN.get(), 1, 300, 0.6F)
				.requires(TofuItems.SOY_CHEESE.get())
				.requires(TofuItems.TOFU_KINU.get())
				.requires(Items.POTATO)
				.requires(Items.CHICKEN)
				.requires(TofuItems.TOFU_METAL_BOWL.get())
				.unlockedBy("has_item", has(TofuItems.TOFU_METAL_BOWL.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.MUSHROOM_ANKAKE.get(), 1, 300, 0.8F)
				.requires(TofuItems.BOTTLE_MIRIN.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.STARCH.get())
				.requires(Items.BROWN_MUSHROOM)
				.requires(Items.BROWN_MUSHROOM)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.BOTTLE_MIRIN.get()))
				.save(this.output);

		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.TOFU_ANKAKE.get(), 1, 300, 0.8F)
				.requires(TofuItems.BOTTLE_MIRIN.get())
				.requires(TofuItems.BOTTLE_SOYSAUSE.get())
				.requires(TofuItems.SALT.get())
				.requires(TofuItems.STARCH.get())
				.requires(TofuTags.Items.TOFU)
				.requires(Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.BOTTLE_MIRIN.get()))
				.save(this.output);


		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.SOY_CHEESE.get(), 2, 300, 0.3F)
				.requires(TofuTags.Items.SOYMILK)
				.requires(TofuItems.SALT.get())
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.SOY_NETHER_CHEESE.get(), 2, 300, 0.3F)
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER.get())
				.requires(TofuItems.SALT.get())
				.requires(Items.WARPED_FUNGUS)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MEAL, Optional.empty(), TofuItems.SOY_SOUL_CHEESE.get(), 2, 300, 0.3F)
				.requires(TofuItems.SEEDS_SOYBEANS_SOUL.get())
				.requires(TofuItems.SALT.get())
				.requires(Items.CRIMSON_FUNGUS)
				.unlockedBy("has_item", has(TofuItems.SALT.get()))
				.save(this.output);
		TofuPotShapelessRecipeBuilder.shapeless(lookup, TofuPotCategory.MISC, Optional.empty(), TofuItems.APRICOTJERRY_BOTTLE.get(), 1, 300, 0.3F)
				.requires(TofuItems.APRICOT.get())
				.requires(Items.SUGAR)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.APRICOT.get()))
				.save(this.output);


		//TF Craft with soymilk craft
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_PUMPKIN_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.PUMPKIN)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_pumpkin_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_PUDDING_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.EGG)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_paddle_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_HONEY_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.HONEY_BOTTLE)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_honey_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_RAMUNE_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.LIGHT_BLUE_DYE)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_ramune_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_KINAKO_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KINAKO.get())
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_kinako_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_APPLE_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.APPLE)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_apple_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_COCOA_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.COCOA_BEANS)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_cocoa_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_ANNIN_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KYONINSO.get())
				.requires(TofuItems.KYONINSO.get())
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_annin_tf"));
		TFShapelessRecipeBuilder.shapeless(lookup, TFCraftingCategory.MISC, new ItemStackTemplate(TofuItems.SOYMILK_FRUITS_BOTTLE.get()), 200)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.APPLE)
				.requires(Items.SWEET_BERRIES)
				.requires(Items.GLOW_BERRIES)
				.requires(Items.CHORUS_FRUIT)
				.unlockedBy("has_item", has(TofuBlocks.TF_CRAFTING_TABLE))
				.save(this.output, prefix("soymilk_fruits_tf"));


		BitternRecipeBuilder.bittern(new ItemStackTemplate(TofuBlocks.KINUTOFU.get().asItem()), new FluidStackTemplate(TofuFluids.SOYMILK.get(), 1000), Ingredient.of(TofuItems.BITTERN_BOTTLE.get())).unlockedBy("has_item", has(TofuItems.BITTERN_BOTTLE.get())).save(this.output, prefix("bittern_to_kinu"));
		BitternRecipeBuilder.bittern(new ItemStackTemplate(TofuBlocks.HELLTOFU.get().asItem()), new FluidStackTemplate(TofuFluids.SOYMILK_HELL.get(), 1000), Ingredient.of(TofuItems.WARPED_BOTTLE.get())).unlockedBy("has_item", has(TofuItems.WARPED_BOTTLE.get())).save(this.output, prefix("bittern_to_hell"));
		BitternRecipeBuilder.bittern(new ItemStackTemplate(TofuBlocks.SOULTOFU.get().asItem()), new FluidStackTemplate(TofuFluids.SOYMILK_SOUL.get(), 1000), Ingredient.of(TofuItems.CRIMSON_BOTTLE.get())).unlockedBy("has_item", has(TofuItems.CRIMSON_BOTTLE.get())).save(this.output, prefix("bittern_to_soul"));
		HardenRecipeBuilder.harden(new ItemStackTemplate(TofuBlocks.ISHITOFU.get().asItem()), Ingredient.of(TofuBlocks.MOMENTOFU.get())).unlockedBy("has_item", has(TofuBlocks.MOMENTOFU.get())).save(this.output, prefix("harden_to_ishi"));
		HardenRecipeBuilder.harden(new ItemStackTemplate(TofuBlocks.METALTOFU.get().asItem()), Ingredient.of(TofuBlocks.ISHITOFU.get())).unlockedBy("has_item", has(TofuBlocks.ISHITOFU.get())).save(this.output, prefix("harden_to_metal"));

		TFTofuMakeRecipeBuilder.tofuMake(new ItemStackTemplate(TofuBlocks.KINUTOFU.get().asItem()), Ingredient.of(items.getOrThrow(TofuTags.Items.SOYBEAN)), 0.1F, 100).unlockedBy("has_item", has(TofuBlocks.TF_TOFU_MAKER.get())).save(this.output, prefix("tf_tofu_maker_kinu"));
		TFTofuMakeRecipeBuilder.tofuMake(new ItemStackTemplate(TofuBlocks.HELLTOFU.get().asItem()), Ingredient.of(TofuItems.SEEDS_SOYBEANS_NETHER.get()), 0.1F, 100).unlockedBy("has_item", has(TofuBlocks.TF_TOFU_MAKER.get())).save(this.output, prefix("tf_tofu_maker_hell"));
		TFTofuMakeRecipeBuilder.tofuMake(new ItemStackTemplate(TofuBlocks.SOULTOFU.get().asItem()), Ingredient.of(TofuItems.SEEDS_SOYBEANS_SOUL.get()), 0.1F, 100).unlockedBy("has_item", has(TofuBlocks.TF_TOFU_MAKER.get())).save(this.output, prefix("tf_tofu_maker_soul"));
	}
}

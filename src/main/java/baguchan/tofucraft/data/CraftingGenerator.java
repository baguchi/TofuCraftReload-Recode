package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		/*helmetItem(consumer, "tofu_diamond_helmet", TofuItems.ARMOR_TOFU_DIAMONDHELMET, TofuBlocks.DIAMONDTOFU.asItem());
		chestplateItem(consumer, "tofu_diamond_chestplate", TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, TofuBlocks.DIAMONDTOFU.asItem());
		leggingsItem(consumer, "tofu_diamond_leggings", TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, TofuBlocks.DIAMONDTOFU.asItem());
		bootsItem(consumer, "tofu_diamond_boots", TofuItems.ARMOR_TOFU_DIAMONDBOOTS, TofuBlocks.DIAMONDTOFU.asItem());
*/

		tofuDiamondSmithing(consumer, Items.DIAMOND_SWORD, TofuItems.TOFU_DIAMOND_SWORD);
		tofuDiamondSmithing(consumer, Items.DIAMOND_PICKAXE, TofuItems.TOFU_DIAMOND_PICKAXE);
		tofuDiamondSmithing(consumer, Items.DIAMOND_AXE, TofuItems.TOFU_DIAMOND_AXE);
		tofuDiamondSmithing(consumer, Items.DIAMOND_SHOVEL, TofuItems.TOFU_DIAMOND_SHOVEL);

		tofuDiamondSmithing(consumer, Items.DIAMOND_HELMET, TofuItems.ARMOR_TOFU_DIAMONDHELMET);
		tofuDiamondSmithing(consumer, Items.DIAMOND_CHESTPLATE, TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE);
		tofuDiamondSmithing(consumer, Items.DIAMOND_LEGGINGS, TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS);
		tofuDiamondSmithing(consumer, Items.DIAMOND_BOOTS, TofuItems.ARMOR_TOFU_DIAMONDBOOTS);
		/*swordItem(consumer, "tofu_diamond_sword", TofuItems.TOFU_DIAMOND_SWORD, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_diamond_pickaxe", TofuItems.TOFU_DIAMOND_PICKAXE, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_diamond_axe", TofuItems.TOFU_DIAMOND_AXE, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_diamond_shovel", TofuItems.TOFU_DIAMOND_SHOVEL, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		*/
		helmetItem(consumer, "tofu_metal_helmet", TofuItems.ARMOR_TOFU_METALHELMET, TofuBlocks.METALTOFU.asItem());
		chestplateItem(consumer, "tofu_metal_chestplate", TofuItems.ARMOR_TOFU_METALCHESTPLATE, TofuBlocks.METALTOFU.asItem());
		leggingsItem(consumer, "tofu_metal_leggings", TofuItems.ARMOR_TOFU_METALLEGGINGS, TofuBlocks.METALTOFU.asItem());
		bootsItem(consumer, "tofu_metal_boots", TofuItems.ARMOR_TOFU_METALBOOTS, TofuBlocks.METALTOFU.asItem());

		swordItem(consumer, "tofu_metal_sword", TofuItems.TOFU_METAL_SWORD, TofuBlocks.METALTOFU.asItem(), Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_metal_pickaxe", TofuItems.TOFU_METAL_PICKAXE, TofuBlocks.METALTOFU.asItem(), Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_metal_axe", TofuItems.TOFU_METAL_AXE, TofuBlocks.METALTOFU.asItem(), Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_metal_shovel", TofuItems.TOFU_METAL_SHOVEL, TofuBlocks.METALTOFU.asItem(), Tags.Items.RODS_WOODEN);


		helmetItem(consumer, "tofu_solid_helmet", TofuItems.ARMOR_TOFU_SOLIDHELMET, TofuBlocks.ISHITOFU.asItem());
		chestplateItem(consumer, "tofu_solid_chestplate", TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE, TofuBlocks.ISHITOFU.asItem());
		leggingsItem(consumer, "tofu_solid_leggings", TofuItems.ARMOR_TOFU_SOLIDLEGGINGS, TofuBlocks.ISHITOFU.asItem());
		bootsItem(consumer, "tofu_solid_boots", TofuItems.ARMOR_TOFU_SOLIDBOOTS, TofuBlocks.ISHITOFU.asItem());

		swordItem(consumer, "tofu_solid_sword", TofuItems.TOFU_SOLID_SWORD, TofuBlocks.ISHITOFU.asItem(), Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_solid_pickaxe", TofuItems.TOFU_SOLID_PICKAXE, TofuBlocks.ISHITOFU.asItem(), Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_solid_axe", TofuItems.TOFU_SOLID_AXE, TofuBlocks.ISHITOFU.asItem(), Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_solid_shovel", TofuItems.TOFU_SOLID_SHOVEL, TofuBlocks.ISHITOFU.asItem(), Tags.Items.RODS_WOODEN);


		helmetItem(consumer, "tofu_momen_helmet", TofuItems.ARMOR_TOFU_MOMENHELMET, TofuBlocks.MOMENTOFU.asItem());
		chestplateItem(consumer, "tofu_momen_chestplate", TofuItems.ARMOR_TOFU_MOMENCHESTPLATE, TofuBlocks.MOMENTOFU.asItem());
		leggingsItem(consumer, "tofu_momen_leggings", TofuItems.ARMOR_TOFU_MOMENLEGGINGS, TofuBlocks.MOMENTOFU.asItem());
		bootsItem(consumer, "tofu_momen_boots", TofuItems.ARMOR_TOFU_MOMENBOOTS, TofuBlocks.MOMENTOFU.asItem());

		swordItem(consumer, "tofu_momen_sword", TofuItems.TOFU_MOMEN_SWORD, TofuBlocks.MOMENTOFU.asItem(), Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_momen_pickaxe", TofuItems.TOFU_MOMEN_PICKAXE, TofuBlocks.MOMENTOFU.asItem(), Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_momen_axe", TofuItems.TOFU_MOMEN_AXE, TofuBlocks.MOMENTOFU.asItem(), Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_momen_shovel", TofuItems.TOFU_MOMEN_SHOVEL, TofuBlocks.MOMENTOFU.asItem(), Tags.Items.RODS_WOODEN);

		helmetItem(consumer, "tofu_kinu_helmet", TofuItems.ARMOR_TOFU_KINUHELMET, TofuBlocks.KINUTOFU.asItem());
		chestplateItem(consumer, "tofu_kinu_chestplate", TofuItems.ARMOR_TOFU_KINUCHESTPLATE, TofuBlocks.KINUTOFU.asItem());
		leggingsItem(consumer, "tofu_kinu_leggings", TofuItems.ARMOR_TOFU_KINULEGGINGS, TofuBlocks.KINUTOFU.asItem());
		bootsItem(consumer, "tofu_kinu_boots", TofuItems.ARMOR_TOFU_KINUBOOTS, TofuBlocks.KINUTOFU.asItem());

		swordItem(consumer, "tofu_kinu_sword", TofuItems.TOFU_KINU_SWORD, TofuBlocks.KINUTOFU.asItem(), Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_kinu_pickaxe", TofuItems.TOFU_KINU_PICKAXE, TofuBlocks.KINUTOFU.asItem(), Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_kinu_axe", TofuItems.TOFU_KINU_AXE, TofuBlocks.KINUTOFU.asItem(), Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_kinu_shovel", TofuItems.TOFU_KINU_SHOVEL, TofuBlocks.KINUTOFU.asItem(), Tags.Items.RODS_WOODEN);

		ladderItem(consumer, TofuBlocks.TOFULADDER_KINU.asItem(), TofuItems.TOFUKINU);
		ladderItem(consumer, TofuBlocks.TOFULADDER_MOMEN.asItem(), TofuItems.TOFUMOMEN);
		ladderItem(consumer, TofuBlocks.TOFULADDER_ISHI.asItem(), TofuItems.TOFUISHI);
		ladderItem(consumer, TofuBlocks.TOFULADDER_ISHIBRICK.asItem(), TofuBlocks.ISHITOFU_BRICK.asItem());
		ladderItem(consumer, TofuBlocks.TOFULADDER_METAL.asItem(), TofuItems.TOFUMETAL);

		tofuBlockItem(consumer, TofuBlocks.MOMENTOFU.asItem(), TofuItems.TOFUKINU, "tofumomen_from_kinu");
		tofuBlockItem(consumer, TofuBlocks.MOMENTOFU.asItem(), TofuItems.TOFUMOMEN);
		tofuBlockItem(consumer, TofuBlocks.ISHITOFU.asItem(), TofuItems.TOFUISHI);
		decorationTofuBlockItem(consumer, TofuBlocks.ISHITOFU_BRICK.asItem(), TofuBlocks.ISHITOFU.asItem());
		decorationTofuBlockItem(consumer, TofuBlocks.ISHITOFU_SMOOTH_BRICK.asItem(), TofuBlocks.ISHITOFU_BRICK.asItem());
		tofuBlockItem(consumer, TofuBlocks.METALTOFU.asItem(), TofuItems.TOFUMETAL);
		tofuBlockItem(consumer, TofuBlocks.DIAMONDTOFU.asItem(), TofuItems.TOFUDIAMOND);
		ShapedRecipeBuilder.shaped(TofuItems.TOFUDIAMOND)
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.define('#', TofuItems.TOFUDIAMOND_NUGGET)
				.unlockedBy("has_item", has(TofuItems.TOFUDIAMOND_NUGGET))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.TOFUDIAMOND_NUGGET, 9)
				.requires(TofuItems.TOFUDIAMOND)
				.unlockedBy("has_item", has(TofuItems.TOFUDIAMOND))
				.save(consumer);

		tofuBlockItem(consumer, TofuBlocks.GRILLEDTOFU.asItem(), TofuItems.TOFUGRILLED);
		tofuBlockItem(consumer, TofuBlocks.ZUNDATOFU.asItem(), TofuItems.TOFUZUNDA);

		ShapedRecipeBuilder.shaped(TofuBlocks.ISHITOFU_CHISELED_BRICK)
				.pattern("#")
				.pattern("#")
				.define('#', TofuBlocks.TOFUSLAB_ISHIBRICK)
				.unlockedBy("has_item", has(TofuBlocks.TOFUSLAB_ISHIBRICK))
				.save(consumer);

		makeStairs(consumer, TofuBlocks.TOFUSTAIR_KINU, TofuBlocks.KINUTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_MOMEN, TofuBlocks.MOMENTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_ISHI, TofuBlocks.ISHITOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_METAL, TofuBlocks.METALTOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		makeStairs(consumer, TofuBlocks.TOFUSTAIR_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);

		makeSlab(consumer, TofuBlocks.TOFUSLAB_KINU, TofuBlocks.KINUTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_MOMEN, TofuBlocks.MOMENTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_ISHI, TofuBlocks.ISHITOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_METAL, TofuBlocks.METALTOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_ZUNDA, TofuBlocks.ZUNDATOFU);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		makeSlab(consumer, TofuBlocks.TOFUSLAB_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);

		makeFence(consumer, TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		makeFence(consumer, TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		makeFence(consumer, TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		makeFence(consumer, TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);

		makeTorch(consumer, TofuBlocks.TOFUTORCH_KINU, TofuBlocks.KINUTOFU);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_MOMEN, TofuBlocks.MOMENTOFU);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_ISHI, TofuBlocks.ISHITOFU);
		makeTorch(consumer, TofuBlocks.TOFUTORCH_METAL, TofuBlocks.METALTOFU);

		foodCooking(TofuItems.TOFUKINU, TofuItems.TOFUGRILLED, 0.1F, consumer, "grilled_from_kinu");
		foodCooking(TofuItems.TOFUMOMEN, TofuItems.TOFUGRILLED, 0.1F, consumer, "grilled_from_momen");
		foodCooking(TofuBlocks.KINUTOFU.asItem(), TofuBlocks.GRILLEDTOFU.asItem(), 0.1F, consumer, "grilled_block_from_kinu");
		foodCooking(TofuBlocks.MOMENTOFU.asItem(), TofuBlocks.GRILLEDTOFU.asItem(), 0.1F, consumer, "grilled_block_from_momen");

		tofuBlockItem(consumer, TofuBlocks.HELLTOFU.asItem(), TofuItems.TOFUHELL);
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU_BRICK.asItem(), TofuBlocks.HELLTOFU.asItem());
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU_SMOOTH_BRICK.asItem(), TofuBlocks.HELLTOFU_BRICK.asItem());
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU.asItem(), TofuItems.TOFUSOUL);
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU_BRICK.asItem(), TofuBlocks.SOULTOFU.asItem());
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU_SMOOTH_BRICK.asItem(), TofuBlocks.SOULTOFU_BRICK.asItem());

		ShapelessRecipeBuilder.shapeless(TofuBlocks.TOFU_STEM_PLANKS, 4)
				.requires(TofuBlocks.TOFU_STEM.asItem())
				.unlockedBy("has_item", has(TofuBlocks.TOFU_STEM))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuBlocks.SALT_FURNACE)
				.pattern("###")
				.pattern("# #")
				.pattern("SSS")
				.define('#', Items.IRON_INGOT)
				.define('S', Items.COBBLESTONE)
				.unlockedBy("has_item", has(TofuItems.TOFUDIAMOND_NUGGET))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuBlocks.MORIJIO, 3)
				.pattern("S#S")
				.pattern("#D#")
				.pattern(" W ")
				.define('#', TofuItems.SALT)
				.define('S', TofuItems.SEEDS_SOYBEANS_SOUL)
				.define('D', Items.DIAMOND)
				.define('W', Items.BOWL)
				.unlockedBy("has_item", has(TofuItems.SALT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuBlocks.TOFUCAKE)
				.pattern("###")
				.pattern("SES")
				.pattern("WWW")
				.define('#', TofuItems.TOFUKINU)
				.define('S', Items.SUGAR)
				.define('E', Tags.Items.EGGS)
				.define('W', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUKINU))
				.save(consumer);

		//soimilk
		ShapedRecipeBuilder.shaped(TofuItems.BUCKET_SOYMILK)
				.pattern("#")
				.pattern("X")
				.define('#', TofuItems.SEEDS_SOYBEANS)
				.define('X', Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.BUCKET_SOYMILK_NETHER)
				.pattern("#")
				.pattern("X")
				.define('#', TofuItems.SEEDS_SOYBEANS_NETHER)
				.define('X', Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.BUCKET_SOYMILK_SOUL)
				.pattern("#")
				.pattern("X")
				.define('#', TofuItems.SEEDS_SOYBEANS_SOUL)
				.define('X', Items.BUCKET)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL))
				.save(consumer);

		//food
		foodCooking(TofuItems.SEEDS_SOYBEANS, TofuItems.SOYBEAN_PARCHED, 0.1F, consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.KINAKO)
				.requires(TofuItems.SOYBEAN_PARCHED)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.SOYBEAN_PARCHED))
				.save(consumer);

		foodCooking(TofuItems.EDAMAME, TofuItems.BOILED_EDAMAME, 0.1F, consumer);

		ShapelessRecipeBuilder.shapeless(TofuItems.ZUNDA, 4)
				.requires(TofuItems.BOILED_EDAMAME, 8)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.BOILED_EDAMAME))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.ZUNDAMA)
				.requires(TofuItems.ZUNDA, 4)
				.requires(Items.GLOWSTONE_DUST)
				.unlockedBy("has_item", has(TofuItems.ZUNDA))
				.save(consumer);
		foodCooking(TofuItems.TOFU_HAMBURG_RAW, TofuItems.TOFU_HAMBURG, 0.25F, consumer);
		foodCooking(TofuItems.RAW_TOFU_FISH, TofuItems.COOKED_TOFU_FISH, 0.2F, consumer);
		ShapedRecipeBuilder.shaped(TofuItems.TOFUCOOKIE, 8)
				.pattern("X#X")
				.define('#', TofuItems.TOFUKINU)
				.define('X', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUKINU))
				.save(consumer, TofuCraftReload.prefix("tofucookie_kinu"));
		ShapedRecipeBuilder.shaped(TofuItems.TOFUCOOKIE, 8)
				.pattern("X#X")
				.define('#', TofuItems.TOFUMOMEN)
				.define('X', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUMOMEN))
				.save(consumer, TofuCraftReload.prefix("tofucookie_momen"));
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYSTICK)
				.requires(TofuItems.SEEDS_SOYBEANS)
				.requires(Items.WHEAT)
				.requires(TofuItems.SALT)
				.unlockedBy("has_item", has(TofuItems.SALT))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SALTYMELON)
				.requires(TofuItems.SALT)
				.requires(Items.MELON_SLICE)
				.unlockedBy("has_item", has(TofuItems.SALT))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYMILK)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYMILK_APPLE)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.APPLE)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYMILK_COCOA)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.COCOA_BEANS)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYMILK_KINAKO)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(TofuItems.KINAKO)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYMILK_PUDDING)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.SUGAR)
				.requires(Items.EGG)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYMILK_PUMPKIN)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.GLASS_BOTTLE)
				.requires(Items.PUMPKIN)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(TofuItems.KINAKO_MANJU, 2)
				.requires(TofuItems.KINAKO)
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.KINAKO))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.ZUNDA_MANJU, 2)
				.requires(TofuItems.ZUNDA)
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.ZUNDA))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.NETHER_MANJU, 2)
				.requires(TofuItems.SEEDS_SOYBEANS_NETHER)
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.SOUL_MANJU, 2)
				.requires(TofuItems.SEEDS_SOYBEANS_SOUL)
				.requires(Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(TofuItems.KINAKO_MOCHI, 1)
				.requires(TofuItems.KINAKO)
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.KINAKO))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.ZUNDA_MOCHI, 1)
				.requires(TofuItems.ZUNDA)
				.requires(TofuTags.Items.RICE)
				.unlockedBy("has_item", has(TofuItems.ZUNDA_MOCHI))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(TofuItems.SOY_CHOCOLATE, 6)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.COCOA_BEANS)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.TOFUNIAN_SOY_CHOCOLATE, 6)
				.requires(TofuItems.BUCKET_SOYMILK)
				.requires(Items.COCOA_BEANS)
				.requires(Items.SUGAR)
				.requires(TofuItems.LEEK)
				.unlockedBy("has_item", has(TofuItems.BUCKET_SOYMILK))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuBlocks.SALTPAN)
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.COBBLESTONE_SLAB)
				.unlockedBy("has_item", has(Blocks.COBBLESTONE_SLAB))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuBlocks.TOFUCHEST)
				.pattern("###")
				.pattern("# #")
				.pattern("###")
				.define('#', TofuItems.TOFUISHI)
				.unlockedBy("has_item", has(TofuItems.TOFUISHI))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuItems.TOFUSCOOP)
				.pattern("#")
				.pattern("X")
				.pattern("X")
				.define('X', Tags.Items.RODS_WOODEN)
				.define('#', Blocks.IRON_BARS)
				.unlockedBy("has_item", has(Blocks.IRON_BARS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(TofuItems.FUKUMAME)
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SOYBEAN_PARCHED)
				.unlockedBy("has_item", has(TofuItems.SOYBEAN_PARCHED))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.NETHER_FUKUMAME)
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SEEDS_SOYBEANS_NETHER)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_NETHER))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuItems.SOUL_FUKUMAME)
				.pattern("###")
				.pattern("###")
				.pattern(" X ")
				.define('X', Items.BOWL)
				.define('#', TofuItems.SEEDS_SOYBEANS_SOUL)
				.unlockedBy("has_item", has(TofuItems.SEEDS_SOYBEANS_SOUL))
				.save(consumer);
		ShapedRecipeBuilder.shaped(TofuBlocks.TOFUBED)
				.pattern("###")
				.pattern("PPP")
				.define('#', TofuItems.YUBA)
				.define('P', ItemTags.PLANKS)
				.unlockedBy("has_item", has(TofuItems.YUBA))
				.save(consumer);
	}
}

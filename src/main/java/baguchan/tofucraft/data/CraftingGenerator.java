package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.nio.file.Path;
import java.util.function.Consumer;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void saveAdvancement(HashCache p_208310_1_, JsonObject p_208310_2_, Path p_208310_3_) {
		//Silence. This just makes it so that we don't gen advancements
		//TODO Recipe advancements control the unlock of a recipe, so if we ever consider actually making them, recipes should unlock based on also possible prerequisite conditions, instead of ONLY obtaining the item itself
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		helmetItem(consumer, "tofu_diamond_helmet", TofuItems.ARMOR_TOFU_DIAMONDHELMET, TofuBlocks.DIAMONDTOFU.asItem());
		chestplateItem(consumer, "tofu_diamond_chestplate", TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, TofuBlocks.DIAMONDTOFU.asItem());
		leggingsItem(consumer, "tofu_diamond_leggings", TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, TofuBlocks.DIAMONDTOFU.asItem());
		bootsItem(consumer, "tofu_diamond_boots", TofuItems.ARMOR_TOFU_DIAMONDBOOTS, TofuBlocks.DIAMONDTOFU.asItem());

		swordItem(consumer, "tofu_diamond_sword", TofuItems.TOFU_DIAMOND_SWORD, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_diamond_pickaxe", TofuItems.TOFU_DIAMOND_PICKAXE, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_diamond_axe", TofuItems.TOFU_DIAMOND_AXE, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_diamond_shovel", TofuItems.TOFU_DIAMOND_SHOVEL, TofuBlocks.DIAMONDTOFU.asItem(), Tags.Items.RODS_WOODEN);

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

		tofuBlockItem(consumer, TofuBlocks.KINUTOFU.asItem(), TofuItems.TOFUKINU);
		tofuBlockItem(consumer, TofuBlocks.MOMENTOFU.asItem(), TofuItems.TOFUMOMEN);
		tofuBlockItem(consumer, TofuBlocks.ISHITOFU.asItem(), TofuItems.TOFUISHI);
		tofuBlockItem(consumer, TofuBlocks.METALTOFU.asItem(), TofuItems.TOFUMETAL);
		tofuBlockItem(consumer, TofuBlocks.DIAMONDTOFU.asItem(), TofuItems.TOFUDIAMOND);
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU.asItem(), TofuItems.TOFUHELL);
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU_BRICK.asItem(), TofuBlocks.HELLTOFU.asItem());
		tofuBlockItem(consumer, TofuBlocks.HELLTOFU_SMOOTH_BRICK.asItem(), TofuBlocks.HELLTOFU_BRICK.asItem());
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU.asItem(), TofuItems.TOFUSOUL);
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU_BRICK.asItem(), TofuBlocks.SOULTOFU.asItem());
		tofuBlockItem(consumer, TofuBlocks.SOULTOFU_SMOOTH_BRICK.asItem(), TofuBlocks.SOULTOFU_BRICK.asItem());

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

		ShapelessRecipeBuilder.shapeless(TofuItems.ZUNDA)
				.requires(TofuItems.BOILED_EDAMAME, 8)
				.requires(Items.SUGAR)
				.unlockedBy("has_item", has(TofuItems.BOILED_EDAMAME))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(TofuItems.ZUNDAMA)
				.requires(TofuItems.ZUNDA, 4)
				.requires(Items.GLOWSTONE)
				.unlockedBy("has_item", has(TofuItems.ZUNDA))
				.save(consumer);
		foodCooking(TofuItems.TOFU_HAMBURG_RAW, TofuItems.TOFU_HAMBURG, 0.25F, consumer);
		foodCooking(TofuItems.RAW_TOFU_FISH, TofuItems.COOKED_TOFU_FISH, 0.2F, consumer);
		ShapedRecipeBuilder.shaped(TofuItems.TOFUCOOKIE)
				.pattern("X#X")
				.define('#', TofuItems.TOFUKINU)
				.define('X', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUKINU))
				.save(consumer, TofuCraftReload.prefix("tofucookie_kinu"));
		ShapedRecipeBuilder.shaped(TofuItems.TOFUCOOKIE)
				.pattern("X#X")
				.define('#', TofuItems.TOFUMOMEN)
				.define('X', Items.WHEAT)
				.unlockedBy("has_item", has(TofuItems.TOFUMOMEN))
				.save(consumer, TofuCraftReload.prefix("tofucookie_momen"));
		ShapelessRecipeBuilder.shapeless(TofuItems.SOYSTICK)
				.requires(TofuItems.SEEDS_SOYBEANS)
				.requires(Items.SUGAR)
				.requires(TofuItems.SALT)
				.unlockedBy("has_item", has(TofuItems.SALT))
				.save(consumer);


		ShapedRecipeBuilder.shaped(TofuBlocks.SALTPAN)
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Blocks.COBBLESTONE_SLAB)
				.unlockedBy("has_item", has(Blocks.COBBLESTONE_SLAB))
				.save(consumer);
	}
}

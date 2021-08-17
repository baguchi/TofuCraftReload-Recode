package baguchan.tofucraft.data;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
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
		helmetItem(consumer, "tofu_diamond_helmet", TofuItems.ARMOR_TOFU_DIAMONDHELMET, TofuItems.TOFUDIAMOND);
		chestplateItem(consumer, "tofu_diamond_chestplate", TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, TofuItems.TOFUDIAMOND);
		leggingsItem(consumer, "tofu_diamond_leggings", TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, TofuItems.TOFUDIAMOND);
		bootsItem(consumer, "tofu_diamond_boots", TofuItems.ARMOR_TOFU_DIAMONDBOOTS, TofuItems.TOFUDIAMOND);

		swordItem(consumer, "tofu_diamond_sword", TofuItems.TOFU_DIAMOND_SWORD, TofuItems.TOFUDIAMOND, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_diamond_pickaxe", TofuItems.TOFU_DIAMOND_PICKAXE, TofuItems.TOFUDIAMOND, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_diamond_axe", TofuItems.TOFU_DIAMOND_AXE, TofuItems.TOFUDIAMOND, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_diamond_shovel", TofuItems.TOFU_DIAMOND_SHOVEL, TofuItems.TOFUDIAMOND, Tags.Items.RODS_WOODEN);

		helmetItem(consumer, "tofu_metal_helmet", TofuItems.ARMOR_TOFU_METALHELMET, TofuItems.TOFUMETAL);
		chestplateItem(consumer, "tofu_metal_chestplate", TofuItems.ARMOR_TOFU_METALCHESTPLATE, TofuItems.TOFUMETAL);
		leggingsItem(consumer, "tofu_metal_leggings", TofuItems.ARMOR_TOFU_METALLEGGINGS, TofuItems.TOFUMETAL);
		bootsItem(consumer, "tofu_metal_boots", TofuItems.ARMOR_TOFU_METALBOOTS, TofuItems.TOFUMETAL);

		swordItem(consumer, "tofu_metal_sword", TofuItems.TOFU_METAL_SWORD, TofuItems.TOFUMETAL, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_metal_pickaxe", TofuItems.TOFU_METAL_PICKAXE, TofuItems.TOFUMETAL, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_metal_axe", TofuItems.TOFU_METAL_AXE, TofuItems.TOFUMETAL, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_metal_shovel", TofuItems.TOFU_METAL_SHOVEL, TofuItems.TOFUMETAL, Tags.Items.RODS_WOODEN);


		helmetItem(consumer, "tofu_solid_helmet", TofuItems.ARMOR_TOFU_SOLIDHELMET, TofuItems.TOFUISHI);
		chestplateItem(consumer, "tofu_solid_chestplate", TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE, TofuItems.TOFUISHI);
		leggingsItem(consumer, "tofu_solid_leggings", TofuItems.ARMOR_TOFU_SOLIDLEGGINGS, TofuItems.TOFUISHI);
		bootsItem(consumer, "tofu_solid_boots", TofuItems.ARMOR_TOFU_SOLIDBOOTS, TofuItems.TOFUISHI);

		swordItem(consumer, "tofu_solid_sword", TofuItems.TOFU_SOLID_SWORD, TofuItems.TOFUISHI, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_solid_pickaxe", TofuItems.TOFU_SOLID_PICKAXE, TofuItems.TOFUISHI, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_solid_axe", TofuItems.TOFU_SOLID_AXE, TofuItems.TOFUISHI, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_solid_shovel", TofuItems.TOFU_SOLID_SHOVEL, TofuItems.TOFUISHI, Tags.Items.RODS_WOODEN);


		helmetItem(consumer, "tofu_momen_helmet", TofuItems.ARMOR_TOFU_MOMENHELMET, TofuItems.TOFUMOMEN);
		chestplateItem(consumer, "tofu_momen_chestplate", TofuItems.ARMOR_TOFU_MOMENCHESTPLATE, TofuItems.TOFUMOMEN);
		leggingsItem(consumer, "tofu_momen_leggings", TofuItems.ARMOR_TOFU_MOMENLEGGINGS, TofuItems.TOFUMOMEN);
		bootsItem(consumer, "tofu_momen_boots", TofuItems.ARMOR_TOFU_MOMENBOOTS, TofuItems.TOFUMOMEN);

		swordItem(consumer, "tofu_momen_sword", TofuItems.TOFU_MOMEN_SWORD, TofuItems.TOFUMOMEN, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_momen_pickaxe", TofuItems.TOFU_MOMEN_PICKAXE, TofuItems.TOFUMOMEN, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_momen_axe", TofuItems.TOFU_MOMEN_AXE, TofuItems.TOFUMOMEN, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_momen_shovel", TofuItems.TOFU_MOMEN_SHOVEL, TofuItems.TOFUMOMEN, Tags.Items.RODS_WOODEN);

		helmetItem(consumer, "tofu_kinu_helmet", TofuItems.ARMOR_TOFU_MOMENHELMET, TofuItems.TOFUMOMEN);
		chestplateItem(consumer, "tofu_kinu_chestplate", TofuItems.ARMOR_TOFU_MOMENCHESTPLATE, TofuItems.TOFUMOMEN);
		leggingsItem(consumer, "tofu_kinu_leggings", TofuItems.ARMOR_TOFU_MOMENLEGGINGS, TofuItems.TOFUMOMEN);
		bootsItem(consumer, "tofu_kinu_boots", TofuItems.ARMOR_TOFU_MOMENBOOTS, TofuItems.TOFUMOMEN);

		swordItem(consumer, "tofu_kinu_sword", TofuItems.TOFU_KINU_SWORD, TofuItems.TOFUKINU, Tags.Items.RODS_WOODEN);
		pickaxeItem(consumer, "tofu_kinu_pickaxe", TofuItems.TOFU_KINU_PICKAXE, TofuItems.TOFUKINU, Tags.Items.RODS_WOODEN);
		axeItem(consumer, "tofu_kinu_axe", TofuItems.TOFU_KINU_AXE, TofuItems.TOFUKINU, Tags.Items.RODS_WOODEN);
		shovelItem(consumer, "tofu_kinu_shovel", TofuItems.TOFU_KINU_SHOVEL, TofuItems.TOFUKINU, Tags.Items.RODS_WOODEN);

		tofuBlockItem(consumer, TofuBlocks.KINUTOFU.asItem(), TofuItems.TOFUKINU);
		tofuBlockItem(consumer, TofuBlocks.MOMENTOFU.asItem(), TofuItems.TOFUMOMEN);
		tofuBlockItem(consumer, TofuBlocks.ISHITOFU.asItem(), TofuItems.TOFUISHI);
		tofuBlockItem(consumer, TofuBlocks.METALTOFU.asItem(), TofuItems.TOFUMETAL);
		tofuBlockItem(consumer, TofuBlocks.DIAMONDTOFU.asItem(), TofuItems.TOFUDIAMOND);

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


		ShapedRecipeBuilder.shaped(TofuBlocks.SALTPAN)
				.pattern("# #")
				.pattern(" X ")
				.define('#', Tags.Items.RODS_WOODEN)
				.define('X', Tags.Items.COBBLESTONE)
				.unlockedBy("has_item", has(Tags.Items.COBBLESTONE))
				.save(consumer);
	}
}

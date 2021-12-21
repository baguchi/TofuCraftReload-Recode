package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static baguchan.tofucraft.TofuCraftReload.prefix;

public class ItemModelGenerator extends ItemModelProvider {
	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, TofuCraftReload.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//block
		toBlock(TofuBlocks.KINUTOFU);
		toBlock(TofuBlocks.MOMENTOFU);
		toBlock(TofuBlocks.ISHITOFU);
		toBlock(TofuBlocks.ISHITOFU_BRICK);
		toBlock(TofuBlocks.ISHITOFU_SMOOTH_BRICK);
		toBlock(TofuBlocks.ISHITOFU_CHISELED_BRICK);
		toBlock(TofuBlocks.METALTOFU);
		toBlock(TofuBlocks.DIAMONDTOFU);
		toBlock(TofuBlocks.GRILLEDTOFU);
		toBlock(TofuBlocks.ZUNDATOFU);

		toBlock(TofuBlocks.HELLTOFU);
		toBlock(TofuBlocks.HELLTOFU_BRICK);
		toBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK);
		toBlock(TofuBlocks.SOULTOFU);
		toBlock(TofuBlocks.SOULTOFU_BRICK);
		toBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK);

		toBlock(TofuBlocks.TOFUSTAIR_KINU);
		toBlock(TofuBlocks.TOFUSTAIR_MOMEN);
		toBlock(TofuBlocks.TOFUSTAIR_ISHI);
		toBlock(TofuBlocks.TOFUSTAIR_METAL);
		toBlock(TofuBlocks.TOFUSTAIR_ZUNDA);
		toBlock(TofuBlocks.TOFUSTAIR_ISHIBRICK);
		toBlock(TofuBlocks.TOFUSTAIR_HELLBRICK);
		toBlock(TofuBlocks.TOFUSTAIR_SOULBRICK);

		toBlock(TofuBlocks.TOFUSLAB_KINU);
		toBlock(TofuBlocks.TOFUSLAB_MOMEN);
		toBlock(TofuBlocks.TOFUSLAB_ISHI);
		toBlock(TofuBlocks.TOFUSLAB_METAL);
		toBlock(TofuBlocks.TOFUSLAB_ZUNDA);
		toBlock(TofuBlocks.TOFUSLAB_ISHIBRICK);
		toBlock(TofuBlocks.TOFUSLAB_HELLBRICK);
		toBlock(TofuBlocks.TOFUSLAB_SOULBRICK);

		torchItem(TofuBlocks.TOFUTORCH_KINU);
		torchItem(TofuBlocks.TOFUTORCH_MOMEN);
		torchItem(TofuBlocks.TOFUTORCH_ISHI);
		torchItem(TofuBlocks.TOFUTORCH_METAL);

		toBlock(TofuBlocks.TOFU_TERRAIN);
		toBlock(TofuBlocks.TOFUSLATE);
		toBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE);
		toBlock(TofuBlocks.ORE_TOFU_DIAMOND);
		toBlock(TofuBlocks.TOFU_BEDROCK);

		toBlock(TofuBlocks.LEEK_GREEN_STEM);
		toBlock(TofuBlocks.LEEK_STEM);
		itemBlockFlat(TofuBlocks.ZUNDATOFU_MUSHROOM);
		toBlock(TofuBlocks.TOFU_STEM);
		toBlock(TofuBlocks.TOFU_STEM_PLANKS);

		itemBlockFlat(TofuBlocks.SAPLING_TOFU);
		toBlock(TofuBlocks.LEAVES_TOFU);

		itemBlockFlat(TofuBlocks.LEEK);
		singleTex(TofuBlocks.TOFUCAKE.asItem());

		//item
		singleTex(TofuItems.TOFUKINU);
		singleTex(TofuItems.TOFUMOMEN);
		singleTex(TofuItems.TOFUISHI);
		singleTex(TofuItems.TOFUMETAL);
		singleTex(TofuItems.TOFUDIAMOND);
		singleTex(TofuItems.TOFUDIAMOND_NUGGET);

		singleTex(TofuItems.TOFUHELL);
		singleTex(TofuItems.TOFUSOUL);

		singleTex(TofuItems.TOFUGRILLED);
		singleTex(TofuItems.TOFUZUNDA);

		singleTex(TofuItems.BITTERN);
		singleTex(TofuItems.SALT);

		singleTex(TofuItems.SEEDS_SOYBEANS);
		singleTex(TofuItems.SEEDS_SOYBEANS_NETHER);
		singleTex(TofuItems.SEEDS_SOYBEANS_SOUL);
		singleTex(TofuItems.SOYBEAN_PARCHED);
		singleTex(TofuItems.KINAKO);
		singleTex(TofuItems.EDAMAME);
		singleTex(TofuItems.BOILED_EDAMAME);

		singleTex(TofuItems.LEEK);
		singleTex(TofuItems.YUBA);

		singleTex(TofuItems.ZUNDA);
		singleTex(TofuItems.ZUNDAMA);
		singleTex(TofuItems.ZUNDARUBY);

		singleTex(TofuItems.TOFU_HAMBURG_RAW);
		singleTex(TofuItems.TOFU_HAMBURG);
		singleTex(TofuItems.RAW_TOFU_FISH);
		singleTex(TofuItems.COOKED_TOFU_FISH);

		singleTex(TofuItems.TOFUCOOKIE);
		singleTex(TofuItems.SOYSTICK);
		singleTex(TofuItems.SALTYMELON);

		singleTex(TofuItems.SOYMILK);
		singleTex(TofuItems.SOYMILK_APPLE);
		singleTex(TofuItems.SOYMILK_COCOA);
		singleTex(TofuItems.SOYMILK_KINAKO);
		singleTex(TofuItems.SOYMILK_PUDDING);
		singleTex(TofuItems.SOYMILK_PUMPKIN);
		singleTex(TofuItems.KINAKO_MANJU);
		singleTex(TofuItems.ZUNDA_MANJU);
		singleTex(TofuItems.NETHER_MANJU);
		singleTex(TofuItems.SOUL_MANJU);

		singleTex(TofuItems.KINAKO_MOCHI);
		singleTex(TofuItems.ZUNDA_MOCHI);

		singleTex(TofuItems.SOY_CHOCOLATE);
		singleTex(TofuItems.TOFUNIAN_SOY_CHOCOLATE);

		singleTex(TofuItems.BUCKET_SOYMILK);
		singleTex(TofuItems.BUCKET_SOYMILK_NETHER);
		singleTex(TofuItems.BUCKET_SOYMILK_SOUL);
		singleTex(TofuItems.BUCKET_BITTERN);

		singleTex(TofuItems.TOFUFISH_BUCKET);
		singleTex(TofuItems.TOFUFISH_SOYMILK_BUCKET);

		singleTexTool(TofuItems.TOFUSCOOP);
		singleTexTool(TofuItems.TOFUSTICK);
		singleTexTool(TofuItems.TOFUSTICK);
		singleTexTool(TofuItems.BUGLE);
		singleTexTool(TofuItems.FUKUMAME);
		singleTexTool(TofuItems.NETHER_FUKUMAME);
		singleTexTool(TofuItems.SOUL_FUKUMAME);

		singleTexTool(TofuItems.TOFU_KINU_SWORD);
		singleTexTool(TofuItems.TOFU_KINU_PICKAXE);
		singleTexTool(TofuItems.TOFU_KINU_AXE);
		singleTexTool(TofuItems.TOFU_KINU_SHOVEL);

		singleTexTool(TofuItems.TOFU_MOMEN_SWORD);
		singleTexTool(TofuItems.TOFU_MOMEN_PICKAXE);
		singleTexTool(TofuItems.TOFU_MOMEN_AXE);
		singleTexTool(TofuItems.TOFU_MOMEN_SHOVEL);

		singleTexTool(TofuItems.TOFU_SOLID_SWORD);
		singleTexTool(TofuItems.TOFU_SOLID_PICKAXE);
		singleTexTool(TofuItems.TOFU_SOLID_AXE);
		singleTexTool(TofuItems.TOFU_SOLID_SHOVEL);

		singleTexTool(TofuItems.TOFU_METAL_SWORD);
		singleTexTool(TofuItems.TOFU_METAL_PICKAXE);
		singleTexTool(TofuItems.TOFU_METAL_AXE);
		singleTexTool(TofuItems.TOFU_METAL_SHOVEL);

		singleTexTool(TofuItems.TOFU_DIAMOND_SWORD);
		singleTexTool(TofuItems.TOFU_DIAMOND_PICKAXE);
		singleTexTool(TofuItems.TOFU_DIAMOND_AXE);
		singleTexTool(TofuItems.TOFU_DIAMOND_SHOVEL);

		singleTex(TofuItems.ARMOR_TOFU_KINUHELMET);
		singleTex(TofuItems.ARMOR_TOFU_KINUCHESTPLATE);
		singleTex(TofuItems.ARMOR_TOFU_KINULEGGINGS);
		singleTex(TofuItems.ARMOR_TOFU_KINUBOOTS);

		singleTex(TofuItems.ARMOR_TOFU_MOMENHELMET);
		singleTex(TofuItems.ARMOR_TOFU_MOMENCHESTPLATE);
		singleTex(TofuItems.ARMOR_TOFU_MOMENLEGGINGS);
		singleTex(TofuItems.ARMOR_TOFU_MOMENBOOTS);

		singleTex(TofuItems.ARMOR_TOFU_SOLIDHELMET);
		singleTex(TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE);
		singleTex(TofuItems.ARMOR_TOFU_SOLIDLEGGINGS);
		singleTex(TofuItems.ARMOR_TOFU_SOLIDBOOTS);

		singleTex(TofuItems.ARMOR_TOFU_METALHELMET);
		singleTex(TofuItems.ARMOR_TOFU_METALCHESTPLATE);
		singleTex(TofuItems.ARMOR_TOFU_METALLEGGINGS);
		singleTex(TofuItems.ARMOR_TOFU_METALBOOTS);

		singleTex(TofuItems.ARMOR_TOFU_DIAMONDHELMET);
		singleTex(TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE);
		singleTex(TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS);
		singleTex(TofuItems.ARMOR_TOFU_DIAMONDBOOTS);

		egg(TofuItems.TOFUNIAN_SPAWNEGG);
		egg(TofuItems.TOFUCOW_SPAWNEGG);
		egg(TofuItems.TOFUSLIME_SPAWNEGG);
		egg(TofuItems.TOFUSPIDER_SPAWNEGG);
	}

	public ItemModelBuilder torchItem(Block item) {
		return withExistingParent(item.getRegistryName().getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + item.getRegistryName().getPath()));
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/generated");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTexTool(Item item) {
		return tool(item.getRegistryName().getPath(), prefix("item/" + item.getRegistryName().getPath()));
	}

	private ItemModelBuilder tool(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/handheld");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTex(Item item) {
		return generated(item.getRegistryName().getPath(), prefix("item/" + item.getRegistryName().getPath()));
	}

	private ItemModelBuilder bowItem(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/bow");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private void woodenButton(Block button, String variant) {
		getBuilder(button.getRegistryName().getPath())
				.parent(getExistingFile(mcLoc("block/button_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	private void woodenFence(Block fence, String variant) {
		getBuilder(fence.getRegistryName().getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	private void toBlock(Block b) {
		toBlockModel(b, b.getRegistryName().getPath());
	}

	private void toBlockModel(Block b, String model) {
		toBlockModel(b, prefix("block/" + model));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(b.getRegistryName().getPath(), model);
	}

	public ItemModelBuilder itemBlockFlat(Block block) {
		return itemBlockFlat(block, blockName(block));
	}

	public ItemModelBuilder itemBlockFlat(Block block, String name) {
		return withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + name));
	}

	public ItemModelBuilder egg(Item item) {
		return withExistingParent(item.getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
	}

	public String blockName(Block block) {
		return block.getRegistryName().getPath();
	}

	@Override
	public String getName() {
		return "TofuCraftReload item and itemblock models";
	}
}
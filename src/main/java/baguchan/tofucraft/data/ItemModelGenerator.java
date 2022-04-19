package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static baguchan.tofucraft.TofuCraftReload.prefix;

public class ItemModelGenerator extends ItemModelProvider {
	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, TofuCraftReload.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//block
		toBlock(TofuBlocks.KINUTOFU.get());
		toBlock(TofuBlocks.MOMENTOFU.get());
		toBlock(TofuBlocks.ISHITOFU.get());
		toBlock(TofuBlocks.ISHITOFU_BRICK.get());
		toBlock(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		toBlock(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		toBlock(TofuBlocks.METALTOFU.get());
		toBlock(TofuBlocks.DIAMONDTOFU.get());
		toBlock(TofuBlocks.GRILLEDTOFU.get());
		toBlock(TofuBlocks.ZUNDATOFU.get());
		toBlock(TofuBlocks.MISOTOFU.get());

		toBlock(TofuBlocks.HELLTOFU.get());
		toBlock(TofuBlocks.HELLTOFU_BRICK.get());
		toBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		toBlock(TofuBlocks.SOULTOFU.get());
		toBlock(TofuBlocks.SOULTOFU_BRICK.get());
		toBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());

		toBlock(TofuBlocks.TOFUSTAIR_KINU.get());
		toBlock(TofuBlocks.TOFUSTAIR_MOMEN.get());
		toBlock(TofuBlocks.TOFUSTAIR_ISHI.get());
		toBlock(TofuBlocks.TOFUSTAIR_METAL.get());
		toBlock(TofuBlocks.TOFUSTAIR_ZUNDA.get());
		toBlock(TofuBlocks.TOFUSTAIR_ISHIBRICK.get());
		toBlock(TofuBlocks.TOFUSTAIR_HELLBRICK.get());
		toBlock(TofuBlocks.TOFUSTAIR_SOULBRICK.get());
		toBlock(TofuBlocks.TOFUSTAIR_MISO.get());

		toBlock(TofuBlocks.TOFUSLAB_KINU.get());
		toBlock(TofuBlocks.TOFUSLAB_MOMEN.get());
		toBlock(TofuBlocks.TOFUSLAB_ISHI.get());
		toBlock(TofuBlocks.TOFUSLAB_METAL.get());
		toBlock(TofuBlocks.TOFUSLAB_ZUNDA.get());
		toBlock(TofuBlocks.TOFUSLAB_ISHIBRICK.get());
		toBlock(TofuBlocks.TOFUSLAB_HELLBRICK.get());
		toBlock(TofuBlocks.TOFUSLAB_SOULBRICK.get());
		toBlock(TofuBlocks.TOFUSLAB_MISO.get());

		wall(TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		wall(TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		wall(TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		wall(TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);
		wall(TofuBlocks.TOFUFENCE_HELL, TofuBlocks.HELLTOFU);
		wall(TofuBlocks.TOFUFENCE_SOUL, TofuBlocks.SOULTOFU);

		singleTex(TofuBlocks.TOFUDOOR_KINU.get());
		singleTex(TofuBlocks.TOFUDOOR_MOMEN.get());
		singleTex(TofuBlocks.TOFUDOOR_ISHI.get());
		singleTex(TofuBlocks.TOFUDOOR_METAL.get());
		singleTex(TofuBlocks.TOFUDOOR_HELL.get());
		singleTex(TofuBlocks.TOFUDOOR_SOUL.get());

		torchItem(TofuBlocks.TOFUTORCH_KINU.get());
		torchItem(TofuBlocks.TOFUTORCH_MOMEN.get());
		torchItem(TofuBlocks.TOFUTORCH_ISHI.get());
		torchItem(TofuBlocks.TOFUTORCH_METAL.get());

		toBlock(TofuBlocks.TOFU_TERRAIN.get());
		toBlock(TofuBlocks.TOFUSLATE.get());
		toBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		toBlock(TofuBlocks.ORE_TOFU_DIAMOND.get());
		toBlock(TofuBlocks.TOFU_BEDROCK.get());

		toBlock(TofuBlocks.LEEK_GREEN_STEM.get());
		toBlock(TofuBlocks.LEEK_STEM.get());
		itemBlockFlat(TofuBlocks.ZUNDATOFU_MUSHROOM.get());
		toBlock(TofuBlocks.TOFU_STEM.get());
		toBlock(TofuBlocks.TOFU_STEM_PLANKS.get());

		itemBlockFlat(TofuBlocks.SAPLING_TOFU.get());
		toBlock(TofuBlocks.LEAVES_TOFU.get());

		itemBlockFlat(TofuBlocks.LEEK.get());
		singleTex(TofuBlocks.TOFUCAKE.get().asItem());

		toBlock(TofuBlocks.ANTENNA_BASIC.get());

		//item
		singleTex(TofuItems.TOFUKINU.get());
		singleTex(TofuItems.TOFUMOMEN.get());
		singleTex(TofuItems.TOFUISHI.get());
		singleTex(TofuItems.TOFUMETAL.get());
		singleTex(TofuItems.TOFUDIAMOND.get());
		singleTex(TofuItems.TOFUDIAMOND_NUGGET.get());

		singleTex(TofuItems.TOFUHELL.get());
		singleTex(TofuItems.TOFUSOUL.get());

		singleTex(TofuItems.TOFUGRILLED.get());
		singleTex(TofuItems.TOFUZUNDA.get());
		singleTex(TofuItems.TOFUMISO.get());

		singleTex(TofuItems.BITTERN_BOTTLE.get());
		singleTex(TofuItems.SALT.get());

		singleTex(TofuItems.SEEDS_SOYBEANS.get());
		singleTex(TofuItems.SEEDS_SOYBEANS_NETHER.get());
		singleTex(TofuItems.SEEDS_SOYBEANS_SOUL.get());
		singleTex(TofuItems.SOYBEAN_PARCHED.get());
		singleTex(TofuItems.KINAKO.get());
		singleTex(TofuItems.EDAMAME.get());
		singleTex(TofuItems.BOILED_EDAMAME.get());

		singleTex(TofuItems.KOUJI_BASE.get());
		singleTex(TofuItems.KOUJI.get());

		singleTex(TofuItems.MISO.get());
		singleTex(TofuItems.BOTTLE_SOYSAUSE.get());

		singleTex(TofuItems.LEEK.get());
		singleTex(TofuItems.YUBA.get());

		singleTex(TofuItems.ZUNDA.get());
		singleTex(TofuItems.ZUNDAMA.get());
		singleTex(TofuItems.ZUNDARUBY.get());

		singleTex(TofuItems.TOFU_HAMBURG_RAW.get());
		singleTex(TofuItems.TOFU_HAMBURG.get());
		singleTex(TofuItems.RAW_TOFU_FISH.get());
		singleTex(TofuItems.COOKED_TOFU_FISH.get());

		singleTex(TofuItems.TOFUCOOKIE.get());
		singleTex(TofuItems.SOYSTICK.get());
		singleTex(TofuItems.SALTYMELON.get());

		singleTex(TofuItems.SOYMILK.get());
		singleTex(TofuItems.SOYMILK_APPLE.get());
		singleTex(TofuItems.SOYMILK_COCOA.get());
		singleTex(TofuItems.SOYMILK_KINAKO.get());
		singleTex(TofuItems.SOYMILK_PUDDING.get());
		singleTex(TofuItems.SOYMILK_PUMPKIN.get());
		singleTex(TofuItems.KINAKO_MANJU.get());
		singleTex(TofuItems.ZUNDA_MANJU.get());
		singleTex(TofuItems.NETHER_MANJU.get());
		singleTex(TofuItems.SOUL_MANJU.get());

		singleTex(TofuItems.KINAKO_MOCHI.get());
		singleTex(TofuItems.ZUNDA_MOCHI.get());

		singleTex(TofuItems.SOY_CHOCOLATE.get());
		singleTex(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get());

		singleTex(TofuItems.BUCKET_SOYMILK.get());
		singleTex(TofuItems.BUCKET_SOYMILK_NETHER.get());
		singleTex(TofuItems.BUCKET_SOYMILK_SOUL.get());
		singleTex(TofuItems.BUCKET_BITTERN.get());

		singleTex(TofuItems.TOFUFISH_BUCKET.get());
		singleTex(TofuItems.TOFUFISH_SOYMILK_BUCKET.get());

		singleTexTool(TofuItems.TOFUSCOOP.get());
		singleTexTool(TofuItems.TOFUSTICK.get());
		singleTexTool(TofuItems.TOFUSTICK.get());
		singleTexTool(TofuItems.BUGLE.get());
		singleTexTool(TofuItems.FUKUMAME.get());
		singleTexTool(TofuItems.NETHER_FUKUMAME.get());
		singleTexTool(TofuItems.SOUL_FUKUMAME.get());


		singleTexTool(TofuItems.TOFUGEM.get());
		singleTexTool(TofuItems.TF_COIL.get());
		singleTexTool(TofuItems.TF_CIRCUIT.get());
		singleTexTool(TofuItems.TF_CAPACITOR.get());
		singleTexTool(TofuItems.TF_OSCILLATOR.get());

		singleTexTool(TofuItems.TOFU_KINU_SWORD.get());
		singleTexTool(TofuItems.TOFU_KINU_PICKAXE.get());
		singleTexTool(TofuItems.TOFU_KINU_AXE.get());
		singleTexTool(TofuItems.TOFU_KINU_SHOVEL.get());

		singleTexTool(TofuItems.TOFU_MOMEN_SWORD.get());
		singleTexTool(TofuItems.TOFU_MOMEN_PICKAXE.get());
		singleTexTool(TofuItems.TOFU_MOMEN_AXE.get());
		singleTexTool(TofuItems.TOFU_MOMEN_SHOVEL.get());

		singleTexTool(TofuItems.TOFU_SOLID_SWORD.get());
		singleTexTool(TofuItems.TOFU_SOLID_PICKAXE.get());
		singleTexTool(TofuItems.TOFU_SOLID_AXE.get());
		singleTexTool(TofuItems.TOFU_SOLID_SHOVEL.get());

		singleTexTool(TofuItems.TOFU_METAL_SWORD.get());
		singleTexTool(TofuItems.TOFU_METAL_PICKAXE.get());
		singleTexTool(TofuItems.TOFU_METAL_AXE.get());
		singleTexTool(TofuItems.TOFU_METAL_SHOVEL.get());

		singleTexTool(TofuItems.TOFU_DIAMOND_SWORD.get());
		singleTexTool(TofuItems.TOFU_DIAMOND_PICKAXE.get());
		singleTexTool(TofuItems.TOFU_DIAMOND_AXE.get());
		singleTexTool(TofuItems.TOFU_DIAMOND_SHOVEL.get());

		singleTex(TofuItems.ARMOR_TOFU_KINUHELMET.get());
		singleTex(TofuItems.ARMOR_TOFU_KINUCHESTPLATE.get());
		singleTex(TofuItems.ARMOR_TOFU_KINULEGGINGS.get());
		singleTex(TofuItems.ARMOR_TOFU_KINUBOOTS.get());

		singleTex(TofuItems.ARMOR_TOFU_MOMENHELMET.get());
		singleTex(TofuItems.ARMOR_TOFU_MOMENCHESTPLATE.get());
		singleTex(TofuItems.ARMOR_TOFU_MOMENLEGGINGS.get());
		singleTex(TofuItems.ARMOR_TOFU_MOMENBOOTS.get());

		singleTex(TofuItems.ARMOR_TOFU_SOLIDHELMET.get());
		singleTex(TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE.get());
		singleTex(TofuItems.ARMOR_TOFU_SOLIDLEGGINGS.get());
		singleTex(TofuItems.ARMOR_TOFU_SOLIDBOOTS.get());

		singleTex(TofuItems.ARMOR_TOFU_METALHELMET.get());
		singleTex(TofuItems.ARMOR_TOFU_METALCHESTPLATE.get());
		singleTex(TofuItems.ARMOR_TOFU_METALLEGGINGS.get());
		singleTex(TofuItems.ARMOR_TOFU_METALBOOTS.get());

		singleTex(TofuItems.ARMOR_TOFU_DIAMONDHELMET.get());
		singleTex(TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE.get());
		singleTex(TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS.get());
		singleTex(TofuItems.ARMOR_TOFU_DIAMONDBOOTS.get());

		egg(TofuItems.TOFUNIAN_SPAWNEGG.get());
		egg(TofuItems.TOFUCOW_SPAWNEGG.get());
		egg(TofuItems.TOFUSLIME_SPAWNEGG.get());
		egg(TofuItems.TOFUSPIDER_SPAWNEGG.get());

		singleTex(TofuItems.TOMATO_SOYBEAN_STEW.get());
		singleTex(TofuItems.YUDOFU.get());
		singleTex(TofuItems.EDAMAME_RICE.get());

		singleTex(TofuItems.BOTTLE_DASHI.get());
		singleTex(TofuItems.TOFUEGG.get());
		singleTex(TofuItems.SOYSAUSE_RAMEN.get());

		toBlock(TofuBlocks.EGGTOFU.get());
		toBlock(TofuBlocks.TOFUSTAIR_EGG.get());
		toBlock(TofuBlocks.TOFUSLAB_EGG.get());
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

	private ItemModelBuilder singleTex(ItemLike item) {
		return generated(item.asItem().getRegistryName().getPath(), prefix("item/" + item.asItem().getRegistryName().getPath()));
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

	public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		return wallInventory(wall.get().getRegistryName().getPath(), texture(blockName(fullBlock.get())));
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

	private ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}
}
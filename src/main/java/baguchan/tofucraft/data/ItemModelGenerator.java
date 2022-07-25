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
import net.minecraftforge.registries.ForgeRegistries;

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
		toBlock(TofuBlocks.DRIEDTOFU.get());
		toBlock(TofuBlocks.SCULK_BONE.get());
		toBlock(TofuBlocks.SCULKED_TOFU_SOUL.get());

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
		toBlock(TofuBlocks.TOFUSTAIR_HELL.get());
		toBlock(TofuBlocks.TOFUSTAIR_SOUL.get());
		toBlock(TofuBlocks.TOFUSTAIR_ISHIBRICK.get());
		toBlock(TofuBlocks.TOFUSTAIR_HELLBRICK.get());
		toBlock(TofuBlocks.TOFUSTAIR_SOULBRICK.get());
		toBlock(TofuBlocks.TOFUSTAIR_MISO.get());
		toBlock(TofuBlocks.TOFUSTAIR_DRIED.get());

		toBlock(TofuBlocks.TOFUSLAB_KINU.get());
		toBlock(TofuBlocks.TOFUSLAB_MOMEN.get());
		toBlock(TofuBlocks.TOFUSLAB_ISHI.get());
		toBlock(TofuBlocks.TOFUSLAB_METAL.get());
		toBlock(TofuBlocks.TOFUSLAB_ZUNDA.get());
		toBlock(TofuBlocks.TOFUSLAB_HELL.get());
		toBlock(TofuBlocks.TOFUSLAB_SOUL.get());
		toBlock(TofuBlocks.TOFUSLAB_ISHIBRICK.get());
		toBlock(TofuBlocks.TOFUSLAB_HELLBRICK.get());
		toBlock(TofuBlocks.TOFUSLAB_SOULBRICK.get());
		toBlock(TofuBlocks.TOFUSLAB_MISO.get());
		toBlock(TofuBlocks.TOFUSLAB_DRIED.get());

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

		toBlockModel(TofuBlocks.TOFUTRAPDOOR_KINU.get(), "tofutrapdoor_kinu_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_MOMEN.get(), "tofutrapdoor_momen_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_ISHI.get(), "tofutrapdoor_ishi_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_METAL.get(), "tofutrapdoor_metal_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_HELL.get(), "tofutrapdoor_hell_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_SOUL.get(), "tofutrapdoor_soul_bottom");

		torchItem(TofuBlocks.TOFUTORCH_KINU.get());
		torchItem(TofuBlocks.TOFUTORCH_MOMEN.get());
		torchItem(TofuBlocks.TOFUTORCH_ISHI.get());
		torchItem(TofuBlocks.TOFUTORCH_METAL.get());

		toBlock(TofuBlocks.TOFU_TERRAIN.get());
		toBlock(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
		toBlock(TofuBlocks.TOFUSLATE.get());
		toBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		toBlock(TofuBlocks.ORE_TOFU_DIAMOND.get());
		toBlock(TofuBlocks.ORE_TOFUGEM.get());
		toBlock(TofuBlocks.TOFU_BEDROCK.get());

		toBlock(TofuBlocks.LEEK_GREEN_STEM.get());
		toBlock(TofuBlocks.LEEK_STEM.get());
		itemBlockFlat(TofuBlocks.ZUNDATOFU_MUSHROOM.get());
		toBlock(TofuBlocks.TOFU_STEM.get());
		toBlock(TofuBlocks.TOFU_STEM_PLANKS.get());

		itemBlockFlat(TofuBlocks.SAPLING_TOFU.get());
		toBlock(TofuBlocks.LEAVES_TOFU.get());

		itemBlockFlat(TofuBlocks.SAPLING_APRICOT.get());
		toBlock(TofuBlocks.LEAVES_APRICOT.get());
		singleTex(TofuItems.APRICOT.get());
		singleTex(TofuItems.APRICOTJERRY_BOTTLE.get());
		singleTex(TofuItems.APRICOTJERRY_BREAD.get());
		singleTex(TofuItems.APRICOTSEED.get());
		singleTex(TofuItems.KYONINSO.get());

		itemBlockFlat(TofuBlocks.LEEK.get());
		singleTex(TofuBlocks.TOFUCAKE.get().asItem());
		singleTex(TofuBlocks.ZUNDATOFUCAKE.get().asItem());

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
		singleTex(TofuItems.TOFUDRIED.get());
		singleTex(TofuItems.TOFUFRIED.get());
		singleTex(TofuItems.TOFUSMOKE.get());
		singleTex(TofuItems.TOFUFRIED_POUCH.get());
		singleTex(TofuItems.AGEDASHI_TOFU.get());
		singleTex(TofuItems.TOFU_STEAK.get());
		singleTex(TofuItems.OAGE.get());
		singleTex(TofuItems.TOFU_MINCED.get());

		singleTex(TofuItems.TOFUANNIN.get());

		singleTex(TofuItems.BITTERN_BOTTLE.get());
		singleTex(TofuItems.SALT.get());

		singleTex(TofuItems.SEEDS_SOYBEANS.get());
		singleTex(TofuItems.SEEDS_SOYBEANS_NETHER.get());
		singleTex(TofuItems.SEEDS_SOYBEANS_SOUL.get());
		singleTex(TofuItems.SOYBEAN_PARCHED.get());
		singleTex(TofuItems.KINAKO.get());
		singleTex(TofuItems.EDAMAME.get());
		singleTex(TofuItems.BOILED_EDAMAME.get());
		singleTex(TofuItems.MINCEDPOTATO.get());

		singleTex(TofuItems.SEEDS_CHILI.get());
		singleTex(TofuItems.CHILI.get());
		singleTex(TofuItems.DOUBANJIANG.get());
		singleTex(TofuItems.MABODOFU.get());

		singleTex(TofuItems.FUKUMENI.get());
		singleTex(TofuItems.KOYADOFUSTEW.get());

		singleTex(TofuItems.KOUJI_BASE.get());
		singleTex(TofuItems.KOUJI.get());

		singleTex(TofuItems.MISO.get());
		singleTex(TofuItems.BOTTLE_SOYSAUSE.get());
		singleTex(TofuItems.NANBAN.get());
		singleTex(TofuItems.NANBANTOFU.get());
		singleTex(TofuItems.NATTO.get());

		singleTex(TofuItems.STARCH.get());
		singleTex(TofuItems.STARCH_RAW.get());
		singleTex(TofuItems.FILTERCLOTH.get());

		singleTex(TofuItems.GELATIN.get());
		singleTex(TofuItems.GELATINRAW.get());

		singleTex(TofuItems.LEEK.get());
		singleTex(TofuItems.RICE.get());
		singleTex(TofuItems.SEEDS_RICE.get());
		singleTex(TofuItems.CHIKUWA.get());
		singleTex(TofuItems.TOFU_CHIKUWA.get());
		singleTex(TofuItems.YUBA.get());

		singleTex(TofuItems.ZUNDA.get());
		singleTex(TofuItems.ZUNDAMA.get());
		singleTex(TofuItems.ZUNDARUBY.get());

		singleTex(TofuItems.TOFU_HAMBURG_RAW.get());
		singleTex(TofuItems.TOFU_HAMBURG.get());
		singleTex(TofuItems.RAW_TOFU_FISH.get());
		singleTex(TofuItems.COOKED_TOFU_FISH.get());
		singleTex(TofuItems.MISODENGAKU.get());

		singleTex(TofuItems.TOFUCOOKIE.get());
		singleTex(TofuItems.TTTBURGER.get());
		singleTex(TofuItems.MEAT_WRAPPED_YUBA.get());
		singleTex(TofuItems.SOYSTICK.get());
		singleTex(TofuItems.MISOSOUP.get());
		singleTex(TofuItems.SALTYMELON.get());

		singleTex(TofuItems.SOYMILK.get());
		singleTex(TofuItems.SOYMILK_ANNIN.get());
		singleTex(TofuItems.SOYMILK_APPLE.get());
		singleTex(TofuItems.SOYMILK_COCOA.get());
		singleTex(TofuItems.SOYMILK_FRUITS.get());
		singleTex(TofuItems.SOYMILK_HONEY.get());
		singleTex(TofuItems.SOYMILK_KINAKO.get());
		singleTex(TofuItems.SOYMILK_PUDDING.get());
		singleTex(TofuItems.SOYMILK_PUMPKIN.get());
		singleTex(TofuItems.SOYMILK_RAMUNE.get());
		singleTex(TofuItems.SOYMILK_SAKURA.get());
		singleTex(TofuItems.SOYMILK_STRAWBERRY.get());
		singleTex(TofuItems.SOYMILK_TEA.get());
		singleTex(TofuItems.KINAKO_MANJU.get());
		singleTex(TofuItems.ZUNDA_MANJU.get());
		singleTex(TofuItems.NETHER_MANJU.get());
		singleTex(TofuItems.SOUL_MANJU.get());

		singleTex(TofuItems.KINAKO_MOCHI.get());
		singleTex(TofuItems.CRIMSON_SOUP.get());
		singleTex(TofuItems.ZUNDA_MOCHI.get());

		singleTex(TofuItems.PUDDING.get());
		singleTex(TofuItems.PUDDING_SOYMILK.get());

		singleTex(TofuItems.NIKUJAGA.get());

		singleTex(TofuItems.ONIGIRI.get());
		singleTex(TofuItems.ONIGIRI_SALT.get());
		singleTex(TofuItems.YAKIONIGIRI_MISO.get());
		singleTex(TofuItems.YAKIONIGIRI_SHOYU.get());
		singleTex(TofuItems.RICE_BURGER.get());
		singleTex(TofuItems.RICE_NATTO.get());
		singleTex(TofuItems.RICE_NATTO_LEEK.get());
		singleTex(TofuItems.RICE_TOFU.get());
		singleTex(TofuItems.RICE_SOBORO_TOFU.get());
		singleTex(TofuItems.GOHEIMOCHI.get());

		singleTex(TofuItems.INARI.get());

		singleTex(TofuItems.OKARA.get());
		singleTex(TofuItems.OKARASTICK.get());

		singleTex(TofuItems.SOBOROTOFUSAUTE.get());

		singleTex(TofuItems.SOY_CHOCOLATE.get());
		singleTex(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get());

		singleTex(TofuItems.BUCKET_SOYMILK.get());
		singleTex(TofuItems.BUCKET_SOYMILK_NETHER.get());
		singleTex(TofuItems.BUCKET_SOYMILK_SOUL.get());
		singleTex(TofuItems.BUCKET_BITTERN.get());

		singleTex(TofuItems.TOFUFISH_BUCKET.get());
		singleTex(TofuItems.TOFUFISH_SOYMILK_BUCKET.get());

		singleTex(TofuItems.GLASSBOWL.get());

		singleTex(TofuItems.TOFUSOMEN.get());
		singleTex(TofuItems.TOFUSOMENBOWL_GLASS.get());

		singleTex(TofuItems.TASTYBEEFSTEW.get());
		singleTex(TofuItems.TASTYSTEW.get());

		singleTex(TofuItems.HIYAYAKKO_GLASS.get());
		singleTex(TofuItems.NATTOHIYAYAKKO_GLASS.get());

		singleTexTool(TofuItems.TOFUSCOOP.get());
		singleTexTool(TofuItems.TOFUSTICK.get());
		singleTexTool(TofuItems.ROLLINGPIN.get());
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
		singleTexTool(TofuItems.TOFU_METAL_SHEARS.get());

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
		singleTex(TofuItems.BOTTLE_SOYOIL.get());
		singleTex(TofuItems.TOFUEGG.get());
		singleTex(TofuItems.SOYSAUSE_RAMEN.get());
		singleTex(TofuItems.SOY_CHEESE.get());

		toBlock(TofuBlocks.EGGTOFU.get());
		toBlock(TofuBlocks.TOFUSTAIR_EGG.get());
		toBlock(TofuBlocks.TOFUSLAB_EGG.get());
	}

	public ItemModelBuilder torchItem(Block item) {
		return withExistingParent(ForgeRegistries.BLOCKS.getKey(item).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + ForgeRegistries.BLOCKS.getKey(item).getPath()));
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/generated");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTexTool(Item item) {
		return tool(ForgeRegistries.ITEMS.getKey(item).getPath(), prefix("item/" + ForgeRegistries.ITEMS.getKey(item).getPath()));
	}

	private ItemModelBuilder tool(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/handheld");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTex(ItemLike item) {
		return generated(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), prefix("item/" + ForgeRegistries.ITEMS.getKey(item.asItem()).getPath()));
	}

	private ItemModelBuilder bowItem(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/bow");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private void woodenButton(Block button, String variant) {
		getBuilder(ForgeRegistries.BLOCKS.getKey(button).getPath())
				.parent(getExistingFile(mcLoc("block/button_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	private void woodenFence(Block fence, String variant) {
		getBuilder(ForgeRegistries.BLOCKS.getKey(fence).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), texture(blockName(fullBlock.get())));
	}

	private void toBlock(Block b) {
		toBlockModel(b, ForgeRegistries.BLOCKS.getKey(b).getPath());
	}

	private void toBlockModel(Block b, String model) {
		toBlockModel(b, prefix("block/" + model));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(ForgeRegistries.BLOCKS.getKey(b).getPath(), model);
	}

	public ItemModelBuilder itemBlockFlat(Block block) {
		return itemBlockFlat(block, blockName(block));
	}

	public ItemModelBuilder itemBlockFlat(Block block, String name) {
		return withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + name));
	}

	public ItemModelBuilder egg(Item item) {
		return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), mcLoc("item/template_spawn_egg"));
	}

	public String blockName(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	@Override
	public String getName() {
		return "TofuCraftReload item and itemblock models";
	}

	private ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}
}
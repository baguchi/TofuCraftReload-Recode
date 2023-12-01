package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static baguchan.tofucraft.TofuCraftReload.prefix;

public class ItemModelGenerator extends ItemModelProvider {
	public ItemModelGenerator(PackOutput generator, ExistingFileHelper existingFileHelper) {
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
		toBlock(TofuBlocks.TOFU_GEM_BLOCK.get());
		toBlock(TofuBlocks.GRILLEDTOFU.get());
		toBlock(TofuBlocks.ZUNDATOFU.get());
		toBlock(TofuBlocks.MISOTOFU.get());
		toBlock(TofuBlocks.DRIEDTOFU.get());

		toBlock(TofuBlocks.HELLTOFU.get());
		toBlock(TofuBlocks.HELLTOFU_BRICK.get());
		toBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		toBlock(TofuBlocks.SOULTOFU.get());
		toBlock(TofuBlocks.SOULTOFU_BRICK.get());
		toBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		toBlock(TofuBlocks.MINCEDTOFU.get());

		toBlock(TofuBlocks.TOFUSTAIR_KINU.get());
		toBlock(TofuBlocks.TOFUSTAIR_MOMEN.get());
		toBlock(TofuBlocks.TOFUSTAIR_ISHI.get());
		toBlock(TofuBlocks.TOFUSTAIR_METAL.get());
		toBlock(TofuBlocks.TOFUSTAIR_GRILLED.get());
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
		toBlock(TofuBlocks.TOFUSLAB_GRILLED.get());
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
		wall(TofuBlocks.TOFUFENCE_GRILLED, TofuBlocks.GRILLEDTOFU);
		wall(TofuBlocks.TOFUFENCE_ZUNDA, TofuBlocks.ZUNDATOFU);

		singleTex(TofuBlocks.TOFUDOOR_KINU);
		singleTex(TofuBlocks.TOFUDOOR_MOMEN);
		singleTex(TofuBlocks.TOFUDOOR_ISHI);
		singleTex(TofuBlocks.TOFUDOOR_METAL);
		singleTex(TofuBlocks.TOFUDOOR_HELL);
		singleTex(TofuBlocks.TOFUDOOR_SOUL);
		singleTex(TofuBlocks.TOFUDOOR_GRILLED);
		singleTex(TofuBlocks.TOFUDOOR_ZUNDA);

		toBlockModel(TofuBlocks.TOFUTRAPDOOR_KINU.get(), "tofutrapdoor_kinu_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_MOMEN.get(), "tofutrapdoor_momen_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_ISHI.get(), "tofutrapdoor_ishi_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_METAL.get(), "tofutrapdoor_metal_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_HELL.get(), "tofutrapdoor_hell_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_SOUL.get(), "tofutrapdoor_soul_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_GRILLED.get(), "tofutrapdoor_grilled_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_ZUNDA.get(), "tofutrapdoor_zunda_bottom");

		torchItem(TofuBlocks.TOFUTORCH_KINU.get());
		torchItem(TofuBlocks.TOFUTORCH_MOMEN.get());
		torchItem(TofuBlocks.TOFUTORCH_ISHI.get());
		torchItem(TofuBlocks.TOFUTORCH_METAL.get());
		torchItem(TofuBlocks.TOFUTORCH_GRILLED.get());
		torchItem(TofuBlocks.TOFUTORCH_ZUNDA.get());

		toBlock(TofuBlocks.TOFU_TERRAIN.get());
		toBlock(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
		toBlock(TofuBlocks.TOFUSLATE.get());
		toBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		toBlock(TofuBlocks.ORE_TOFU_DIAMOND.get());
		toBlock(TofuBlocks.ORE_TOFUGEM.get());
		toBlock(TofuBlocks.TOFU_BEDROCK.get());

		toBlock(TofuBlocks.LEEK_GREEN_STEM.get());
		toBlock(TofuBlocks.LEEK_GREEN_PLANKS.get());
		toBlock(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		toBlock(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		this.woodenFence(TofuBlocks.LEEK_GREEN_FENCE, TofuBlocks.LEEK_GREEN_PLANKS);
		toBlock(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());
		sign(TofuBlocks.LEEK_GREEN_SIGN);
		sign(TofuBlocks.LEEK_GREEN_HANGING_SIGN);

		toBlock(TofuBlocks.LEEK_STEM.get());
		toBlock(TofuBlocks.LEEK_PLANKS.get());
		toBlock(TofuBlocks.LEEK_PLANKS_STAIR.get());
		toBlock(TofuBlocks.LEEK_PLANKS_SLAB.get());
		this.woodenFence(TofuBlocks.LEEK_FENCE, TofuBlocks.LEEK_PLANKS);
		toBlock(TofuBlocks.LEEK_FENCE_GATE.get());
		sign(TofuBlocks.LEEK_SIGN);
		sign(TofuBlocks.LEEK_HANGING_SIGN);

		itemBlockFlat(TofuBlocks.ZUNDATOFU_MUSHROOM.get());


		toBlock(TofuBlocks.TOFU_STEM.get());
		toBlock(TofuBlocks.TOFU_STEM_PLANKS.get());
		toBlock(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get());
		toBlock(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get());
		this.woodenFence(TofuBlocks.TOFU_STEM_FENCE, TofuBlocks.TOFU_STEM_PLANKS);
		toBlock(TofuBlocks.TOFU_STEM_FENCE_GATE.get());
		sign(TofuBlocks.TOFU_STEM_SIGN);
		sign(TofuBlocks.TOFU_STEM_HANGING_SIGN);

		itemBlockFlat(TofuBlocks.SAPLING_TOFU.get());
		toBlock(TofuBlocks.LEAVES_TOFU.get());

		itemBlockFlat(TofuBlocks.SAPLING_APRICOT.get());
		toBlock(TofuBlocks.LEAVES_APRICOT.get());
		singleTex(TofuItems.APRICOT);
		singleTex(TofuItems.APRICOTJERRY_BOTTLE);
		singleTex(TofuItems.APRICOTJERRY_BREAD);
		singleTex(TofuItems.APRICOTSEED);
		singleTex(TofuItems.KYONINSO);

		itemBlockFlat(TofuBlocks.LEEK.get());
		singleTex(TofuBlocks.TOFUCAKE);
		singleTex(TofuBlocks.ZUNDATOFUCAKE);
		singleTex(TofuBlocks.SOYCHEESE_TART);

		toBlock(TofuBlocks.ZUNDAMA_BLOCK.get());

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
		singleTex(TofuItems.TOFUMISO);
		singleTex(TofuItems.TOFUDRIED);
		singleTex(TofuItems.TOFUFRIED);
		singleTex(TofuItems.TOFUSMOKE);
		singleTex(TofuItems.SHUDOFU);
		singleTex(TofuItems.SOY_SCULK_BONE);
		singleTex(TofuItems.TOFUSESAME);
		singleTex(TofuItems.TOFUFRIED_POUCH);
		singleTex(TofuItems.TOFUANNIN);
		singleTex(TofuItems.TOFUSTRAWBERRY);

		singleTex(TofuItems.AGEDASHI_TOFU);
		singleTex(TofuItems.TOFU_STEAK);
		singleTex(TofuItems.OAGE);
		singleTex(TofuItems.TOFU_MINCED);


		singleTex(TofuItems.BITTERN_BOTTLE);
		singleTex(TofuItems.CRIMSON_BOTTLE);
		singleTex(TofuItems.WARPED_BOTTLE);
		singleTex(TofuItems.SHROOM_BOTTLE);
		singleTex(TofuItems.SALT);

		singleTex(TofuItems.SEEDS_SOYBEANS);
		singleTex(TofuItems.SEEDS_SOYBEANS_NETHER);
		singleTex(TofuItems.SEEDS_SOYBEANS_SOUL);
		singleTex(TofuItems.SOYBEAN_PARCHED);
		singleTex(TofuItems.KINAKO);
		singleTex(TofuItems.EDAMAME);
		singleTex(TofuItems.BOILED_EDAMAME);
		singleTex(TofuItems.MINCEDPOTATO);

		singleTex(TofuItems.SEEDS_CHILI);
		singleTex(TofuItems.CHILI);
		singleTex(TofuItems.DOUBANJIANG);
		singleTex(TofuItems.MABODOFU);

		singleTex(TofuItems.FUKUMENI);
		singleTex(TofuItems.KOYADOFUSTEW);

		singleTex(TofuItems.KOUJI_BASE);
		singleTex(TofuItems.KOUJI);

		singleTex(TofuItems.MISO);
		singleTex(TofuItems.BOTTLE_SOYSAUSE);
		singleTex(TofuItems.NANBAN);
		singleTex(TofuItems.NANBANTOFU);
		singleTex(TofuItems.NATTO);
		singleTex(TofuItems.NETHER_NATTO);

		singleTex(TofuItems.STARCH);
		singleTex(TofuItems.STARCH_RAW);
		singleTex(TofuItems.FILTERCLOTH);

		singleTex(TofuItems.GELATIN);
		singleTex(TofuItems.GELATINRAW);

		singleTex(TofuItems.LEEK);
		singleTex(TofuItems.RICE);
		singleTex(TofuItems.SEEDS_RICE);
		singleTex(TofuItems.SPROUTS);
		singleTex(TofuItems.CHIKUWA);
		singleTex(TofuItems.TOFU_CHIKUWA);
		singleTex(TofuItems.YUBA);

		singleTex(TofuItems.ZUNDA);
		emmisiveTex(TofuItems.ZUNDAMA);
		singleTex(TofuItems.ZUNDARUBY);

		singleTex(TofuItems.TOFU_HAMBURG_RAW);
		singleTex(TofuItems.TOFU_HAMBURG);
		singleTex(TofuItems.RAW_TOFU_FISH);
		singleTex(TofuItems.COOKED_TOFU_FISH);
		singleTex(TofuItems.MISODENGAKU);
		singleTex(TofuItems.SOYMEAT);

		singleTex(TofuItems.TOFUCOOKIE);
		singleTex(TofuItems.TTTBURGER);
		singleTex(TofuItems.MEAT_WRAPPED_YUBA);
		singleTex(TofuItems.SOYSTICK);
		singleTex(TofuItems.MISOSOUP);
		singleTex(TofuItems.MOYASHIITAME);
		singleTex(TofuItems.MOYASHIOHITASHI);
		singleTex(TofuItems.SALTYMELON);

		singleTex(TofuItems.SOYMILK);
		singleTex(TofuItems.SOYMILK_ANNIN);
		singleTex(TofuItems.SOYMILK_APPLE);
		singleTex(TofuItems.SOYMILK_COCOA);
		singleTex(TofuItems.SOYMILK_FRUITS);
		singleTex(TofuItems.SOYMILK_HONEY);
		singleTex(TofuItems.SOYMILK_KINAKO);
		singleTex(TofuItems.SOYMILK_PUDDING);
		singleTex(TofuItems.SOYMILK_PUMPKIN);
		singleTex(TofuItems.SOYMILK_RAMUNE);
		singleTex(TofuItems.SOYMILK_SAKURA);
		singleTex(TofuItems.SOYMILK_STRAWBERRY);
		singleTex(TofuItems.SOYMILK_TEA);
		singleTex(TofuItems.SOYMILK_HELL_BOTTLE);
		singleTex(TofuItems.SOYMILK_SOUL_BOTTLE);
		singleTex(TofuItems.KINAKO_MANJU);
		singleTex(TofuItems.ZUNDA_MANJU);
		singleTex(TofuItems.NETHER_MANJU);
		singleTex(TofuItems.SOUL_MANJU);

		singleTex(TofuItems.KINAKO_MOCHI);
		singleTex(TofuItems.CRIMSON_SOUP);
		singleTex(TofuItems.ZUNDA_MOCHI);

		singleTex(TofuItems.PUDDING);
		singleTex(TofuItems.PUDDING_SOYMILK);

		singleTex(TofuItems.NIKUJAGA);

		singleTex(TofuItems.ONIGIRI);
		singleTex(TofuItems.ONIGIRI_SALT);
		singleTex(TofuItems.YAKIONIGIRI_MISO);
		singleTex(TofuItems.YAKIONIGIRI_SHOYU);
		singleTex(TofuItems.RICE_BURGER);
		singleTex(TofuItems.RICE_NATTO);
		singleTex(TofuItems.RICE_NATTO_LEEK);
		singleTex(TofuItems.RICE_NETHER_NATTO);
		singleTex(TofuItems.RICE_NETHER_NATTO_LEEK);
		singleTex(TofuItems.RICE_TOFU);
		singleTex(TofuItems.RICE_SOBORO_TOFU);
		singleTex(TofuItems.GOHEIMOCHI);

		singleTex(TofuItems.INARI);

		singleTex(TofuItems.OKARA);
		singleTex(TofuItems.OKARASTICK);
		singleTex(TofuItems.OKARA_DONUT);

		singleTex(TofuItems.SOBOROTOFUSAUTE);

		singleTex(TofuItems.SOY_CHOCOLATE);
		singleTex(TofuItems.TOFUNIAN_SOY_CHOCOLATE);

		singleTex(TofuItems.BUCKET_SOYMILK);
		singleTex(TofuItems.BUCKET_SOYMILK_NETHER);
		singleTex(TofuItems.BUCKET_SOYMILK_SOUL);
		singleTex(TofuItems.BUCKET_BITTERN);

		singleTex(TofuItems.TOFUFISH_BUCKET);
		singleTex(TofuItems.TOFUFISH_SOYMILK_BUCKET);

		singleTex(TofuItems.GLASSBOWL);

		singleTex(TofuItems.TOFUSOMEN);
		singleTex(TofuItems.TOFUSOMENBOWL_GLASS);

		singleTex(TofuItems.TASTYBEEFSTEW);
		singleTex(TofuItems.TASTYSTEW);

		singleTex(TofuItems.HIYAYAKKO_GLASS);
		singleTex(TofuItems.NATTOHIYAYAKKO_GLASS);

		singleTexTool(TofuItems.TOFUSCOOP);
		singleTexTool(TofuItems.TOFUSTICK);
		singleTexTool(TofuItems.ROLLINGPIN);
		singleTexTool(TofuItems.BUGLE);
		singleTexTool(TofuItems.FUKUMAME);
		singleTexTool(TofuItems.NETHER_FUKUMAME);
		singleTexTool(TofuItems.INFERNO_NETHER_FUKUMAME);
		singleTexTool(TofuItems.SOUL_FUKUMAME);
		glowBowItem(TofuItems.ZUNDA_BOW);
		emmisiveTex(TofuItems.ZUNDA_ARROW);
		singleTexRodTool(TofuItems.ZUNDAMUSHROOM_ON_A_STICK);

		singleTex(TofuItems.KINAKO_BREAD);
		singleTex(TofuItems.EDAMAME_TEMPLA);
		singleTex(TofuItems.NEGIMA);
		singleTex(TofuItems.SOY_KARAAGE);
		singleTex(TofuItems.SOYMEATDON);

		singleTex(TofuItems.TOFUGEM);
		singleTex(TofuItems.TF_COIL);
		singleTex(TofuItems.TF_CIRCUIT);
		singleTex(TofuItems.TF_CAPACITOR);
		singleTex(TofuItems.TF_OSCILLATOR);
		singleTex(TofuItems.TOFU_CORE);

		singleTexTool(TofuItems.TOFU_KINU_SWORD);
		singleTexTool(TofuItems.TOFU_KINU_PICKAXE);
		singleTexTool(TofuItems.TOFU_KINU_AXE);
		singleTexTool(TofuItems.TOFU_KINU_SHOVEL);
		singleTexTool(TofuItems.TOFU_KINU_HOE);

		singleTexTool(TofuItems.TOFU_MOMEN_SWORD);
		singleTexTool(TofuItems.TOFU_MOMEN_PICKAXE);
		singleTexTool(TofuItems.TOFU_MOMEN_AXE);
		singleTexTool(TofuItems.TOFU_MOMEN_SHOVEL);
		singleTexTool(TofuItems.TOFU_MOMEN_HOE);

		singleTexTool(TofuItems.TOFU_SOLID_SWORD);
		singleTexTool(TofuItems.TOFU_SOLID_PICKAXE);
		singleTexTool(TofuItems.TOFU_SOLID_AXE);
		singleTexTool(TofuItems.TOFU_SOLID_SHOVEL);
		singleTexTool(TofuItems.TOFU_SOLID_HOE);

		singleTexTool(TofuItems.TOFU_METAL_SWORD);
		singleTexTool(TofuItems.TOFU_METAL_PICKAXE);
		singleTexTool(TofuItems.TOFU_METAL_AXE);
		singleTexTool(TofuItems.TOFU_METAL_SHOVEL);
		singleTexTool(TofuItems.TOFU_METAL_HOE);
		singleTexTool(TofuItems.TOFU_METAL_SHEARS);

		singleTexTool(TofuItems.TOFU_DIAMOND_SWORD);
		singleTexTool(TofuItems.TOFU_DIAMOND_PICKAXE);
		singleTexTool(TofuItems.TOFU_DIAMOND_AXE);
		singleTexTool(TofuItems.TOFU_DIAMOND_SHOVEL);
		singleTexTool(TofuItems.TOFU_DIAMOND_HOE);

		singleTex(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE);
		singleTex(TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE);

		trimmedArmor(TofuItems.TOFU_KINU_HELMET);
		trimmedArmor(TofuItems.TOFU_KINU_CHESTPLATE);
		trimmedArmor(TofuItems.TOFU_KINU_LEGGINGS);
		trimmedArmor(TofuItems.TOFU_KINU_BOOTS);

		trimmedArmor(TofuItems.TOFU_MOMEN_HELMET);
		trimmedArmor(TofuItems.TOFU_MOMEN_CHESTPLATE);
		trimmedArmor(TofuItems.TOFU_MOMEN_LEGGINGS);
		trimmedArmor(TofuItems.TOFU_MOMEN_BOOTS);

		trimmedArmor(TofuItems.ARMOR_TOFU_SOLIDHELMET);
		trimmedArmor(TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE);
		trimmedArmor(TofuItems.ARMOR_TOFU_SOLIDLEGGINGS);
		trimmedArmor(TofuItems.ARMOR_TOFU_SOLIDBOOTS);

		trimmedArmor(TofuItems.TOFU_METAL_HELMET);
		trimmedArmor(TofuItems.TOFU_METAL_CHESTPLATE);
		trimmedArmor(TofuItems.TOFU_METAL_LEGGINGS);
		trimmedArmor(TofuItems.TOFU_METAL_BOOTS);

		trimmedArmor(TofuItems.TOFU_DIAMOND_HELMET);
		trimmedArmor(TofuItems.TOFU_DIAMOND_CHESTPLATE);
		trimmedArmor(TofuItems.TOFU_DIAMOND_LEGGINGS);
		trimmedArmor(TofuItems.TOFU_DIAMOND_BOOTS);

		trimmedArmor(TofuItems.SCULK_BONE_HELMET);
		trimmedArmor(TofuItems.SCULK_BONE_CHESTPLATE);
		trimmedArmor(TofuItems.SCULK_BONE_LEGGINGS);
		trimmedArmor(TofuItems.SCULK_BONE_BOOTS);

		egg(TofuItems.TOFUNIAN_SPAWNEGG.get());
		egg(TofuItems.TRAVELER_TOFUNIAN_SPAWNEGG.get());
		egg(TofuItems.TOFU_GANDLEM_SPAWNEGG.get());
		egg(TofuItems.SHUDOFUSPIDER_SPAWNEGG.get());
		egg(TofuItems.TOFUCOW_SPAWNEGG.get());
		egg(TofuItems.TOFUPIG_SPAWNEGG.get());
		egg(TofuItems.TOFUSLIME_SPAWNEGG.get());
		egg(TofuItems.TOFUCREEPER_SPAWNEGG.get());
		egg(TofuItems.TOFUSPIDER_SPAWNEGG.get());
		egg(TofuItems.TOFUFISH_SPAWNEGG.get());
		egg(TofuItems.FUKUMAME_THOWER_SPAWNEGG.get());

		singleTex(TofuItems.TOMATO_SOYBEAN_STEW);
		singleTex(TofuItems.YUDOFU);
		singleTex(TofuItems.EDAMAME_RICE);

		singleTex(TofuItems.BOTTLE_DASHI);
		singleTex(TofuItems.BOTTLE_SOYOIL);
		singleTex(TofuItems.TOFUEGG);
		singleTex(TofuItems.SOYSAUSE_RAMEN);

		singleTex(TofuItems.HELL_MABOU);
		singleTex(TofuItems.RED_SOUP);
		singleTex(TofuItems.HELL_RED_SOUP);
		singleTex(TofuItems.SUKIYAKI);
		singleTex(TofuItems.TOFU_BUNS_BURGER);
		singleTex(TofuItems.STEAMED_BREAD);
		singleTex(TofuItems.STEAMED_BREAD_COCOA);

		singleTex(TofuItems.SOY_CHEESE);
		singleTex(TofuItems.SOY_NETHER_CHEESE);
		singleTex(TofuItems.SOY_SOUL_CHEESE);

		toBlock(TofuBlocks.EGGTOFU.get());
		toBlock(TofuBlocks.TOFUSTAIR_EGG.get());
		toBlock(TofuBlocks.TOFUSLAB_EGG.get());

		toBlock(TofuBlocks.SESAMETOFU.get());
		toBlock(TofuBlocks.TOFUSTAIR_SESAME.get());
		toBlock(TofuBlocks.TOFUSLAB_SESAME.get());

		singleTex(TofuItems.NATTO_COBWEB);
		toBlock(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());

		singleTex(TofuItems.TOFUNIAN_BANNER_PATTERN);
		singleTex(TofuItems.TOFU_STEM_BOAT);
		singleTex(TofuItems.LEEK_BOAT);
		singleTex(TofuItems.LEEK_GREEN_BOAT);
		singleTex(TofuItems.TOFU_STEM_CHEST_BOAT);
		singleTex(TofuItems.LEEK_CHEST_BOAT);
		singleTex(TofuItems.LEEK_GREEN_CHEST_BOAT);

		singleTex(TofuBlocks.TOFU_METAL_CHAIN);
		singleTex(TofuBlocks.TOFU_METAL_LANTERN);
		singleTex(TofuBlocks.TOFU_METAL_SOUL_LANTERN);
		toBlock(TofuBlocks.TOFU_DETECTOR.get());
		itemBlockFlat(TofuBlocks.ANTENNA_BASIC.get());
	}

	public void sign(Supplier<? extends SignBlock> sign) {
		withExistingParent(blockName(sign.get()), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + blockName(sign.get())));
	}

	private void woodenFence(Supplier<? extends Block> fence, Supplier<? extends Block> block) {
		getBuilder(BuiltInRegistries.BLOCK.getKey(fence.get()).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath());
	}

	public ItemModelBuilder torchItem(Block item) {
		return withExistingParent(BuiltInRegistries.BLOCK.getKey(item).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(item).getPath()));
	}


	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return buildItem(name, "item/generated", 0, layers);
	}


	private ItemModelBuilder buildItem(String name, String parent, int emissivity, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (emissivity > 0)
			builder = builder.customLoader(ItemLayerModelBuilder::begin).emissive(emissivity, emissivity, 0).renderType("minecraft:translucent", 0).end();
		return builder;
	}

	private ItemModelBuilder tool(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld", 0, layers);
	}

	private ItemModelBuilder singleTexTool(Supplier<? extends Item> item) {
		return tool(itemPath(item).getPath(), prefix("item/" + itemPath(item).getPath()));
	}

	private ItemModelBuilder singleTexRodTool(Supplier<? extends Item> item) {
		return toolRod(itemPath(item).getPath(), prefix("item/" + itemPath(item).getPath()));
	}

	private ItemModelBuilder toolRod(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld_rod", 0, layers);
	}

	private ItemModelBuilder singleTex(Supplier<? extends ItemLike> item) {
		return generated(itemPath(item).getPath(), prefix("item/" + itemPath(item).getPath()));
	}

	private ItemModelBuilder emmisiveTex(Supplier<? extends Item> item) {
		return singleTex(item).customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 0).renderType("minecraft:translucent", 0).end();
	}

	public ItemModelBuilder glowBowItem(Supplier<? extends Item> item) {
		ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());
		buildItem(id.getPath() + "_pulling_0", mcLoc("item/bow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_0"));
		buildItem(id.getPath() + "_pulling_1", mcLoc("item/bow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_1"));
		buildItem(id.getPath() + "_pulling_2", mcLoc("item/bow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_2"));
		return withExistingParent(id.getPath(), mcLoc("item/bow"))
				.customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 0).renderType("minecraft:translucent", 0).end()
				.texture("layer0", modLoc("item/" + id.getPath()))
				.override().predicate(new ResourceLocation("pulling"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_0"))).end()
				.override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_1"))).end()
				.override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_2"))).end();
	}

	public ItemModelBuilder bowItem(Supplier<? extends Item> item) {
		ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());
		withExistingParent(id.getPath() + "_pulling_0", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_0"));
		withExistingParent(id.getPath() + "_pulling_1", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_1"));
		withExistingParent(id.getPath() + "_pulling_2", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_2"));
		return withExistingParent(id.getPath(), mcLoc("item/bow"))
				.texture("layer0", modLoc("item/" + id.getPath()))
				.override().predicate(new ResourceLocation("pulling"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_0"))).end()
				.override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_1"))).end()
				.override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_2"))).end();
	}

	private void woodenButton(Block button, String variant) {
		getBuilder(BuiltInRegistries.BLOCK.getKey(button).getPath())
				.parent(getExistingFile(mcLoc("block/button_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	private void woodenFence(Block fence, String variant) {
		getBuilder(BuiltInRegistries.BLOCK.getKey(fence).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		return wallInventory(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), texture(blockName(fullBlock.get())));
	}

	private ItemModelBuilder toBlock(Block b) {
		return toBlockModel(b, BuiltInRegistries.BLOCK.getKey(b).getPath());
	}

	private ItemModelBuilder toBlockModel(Block b, String model) {
		return toBlockModel(b, prefix("block/" + model));
	}

	private ItemModelBuilder toBlockModel(Block b, ResourceLocation model) {
		return withExistingParent(BuiltInRegistries.BLOCK.getKey(b).getPath(), model);
	}

	public ItemModelBuilder itemBlockFlat(Block block) {
		return itemBlockFlat(block, blockName(block));
	}

	public ItemModelBuilder itemBlockFlat(Block block, String name) {
		return withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + name));
	}

	public ItemModelBuilder egg(Item item) {
		return withExistingParent(BuiltInRegistries.ITEM.getKey(item).getPath(), mcLoc("item/template_spawn_egg"));
	}

	public String blockName(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}

	@Override
	public String getName() {
		return "TofuCraftReload item and itemblock models";
	}

	private ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	public ResourceLocation itemPath(Supplier<? extends ItemLike> item) {
		return BuiltInRegistries.ITEM.getKey(item.get().asItem());
	}

	//Thanks Twilight Forest Team! https://github.com/TeamTwilight/twilightforest/blob/1.20.x/src/main/java/twilightforest/data/ItemModelGenerator.java#L827C23-L837
	private void trimmedArmor(Supplier<ArmorItem> armor) {
		ItemModelBuilder base = this.singleTex(armor);
		for (ItemModelGenerators.TrimModelData trim : ItemModelGenerators.GENERATED_TRIM_MODELS) {
			String material = trim.name();
			String name = itemPath(armor).getPath() + "_" + material + "_trim";
			ModelFile trimModel = this.withExistingParent(name, this.mcLoc("item/generated"))
					.texture("layer0", prefix("item/" + itemPath(armor).getPath()))
					.texture("layer1", this.mcLoc("trims/items/" + armor.get().getType().getName() + "_trim_" + material));
			base.override().predicate(new ResourceLocation("trim_type"), trim.itemModelIndex()).model(trimModel).end();
		}
	}
}
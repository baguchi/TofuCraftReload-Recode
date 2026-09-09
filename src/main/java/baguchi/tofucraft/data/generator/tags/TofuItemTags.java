package baguchi.tofucraft.data.generator.tags;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TofuItemTags extends ItemTagsProvider {
	public TofuItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(TofuTags.Items.DUST_SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.SOYBEAN).add(TofuItems.SEEDS_SOYBEANS.get());
		tag(TofuTags.Items.SOYMILK).addTag(TofuTags.Items.MILK_SOYMILK);
		tag(TofuTags.Items.MILK_SOYMILK).add(TofuItems.SOYMILK_BUCKET.get(), TofuItems.SOYMILK_BOTTLE.get());
		tag(TofuTags.Items.RICE).add(TofuItems.RICE.get());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.STORAGE_SALT).add(TofuBlocks.SALT_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_TOFUGEM).add(TofuBlocks.TOFU_GEM_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_ADV_TOFUGEM).add(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS_NETHER).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS_SOUL).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.asItem());


		tag(ItemTags.LOGS_THAT_BURN).add(TofuBlocks.SPROUT_STEM.get().asItem(), TofuBlocks.TOFU_STEM.get().asItem(), TofuBlocks.LEEK_STEM.get().asItem(), TofuBlocks.LEEK_GREEN_STEM.get().asItem());
		tag(ItemTags.LOGS).add(TofuBlocks.SPROUT_STEM.get().asItem(), TofuBlocks.TOFU_STEM.get().asItem(), TofuBlocks.LEEK_STEM.get().asItem(), TofuBlocks.LEEK_GREEN_STEM.get().asItem());
		tag(ItemTags.PLANKS).add(TofuBlocks.SPROUT_PLANKS.get().asItem()).add(TofuBlocks.TOFU_STEM_PLANKS.get().asItem()).add(TofuBlocks.LEEK_PLANKS.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS.get().asItem());
		tag(ItemTags.ARROWS).add(TofuItems.ZUNDA_ARROW.get());
		tag(Tags.Items.SEEDS).add(TofuItems.SEEDS_CHILI.get(), TofuItems.SEEDS_SESAME.get(), TofuItems.SEEDS_SOYBEANS.get(), TofuItems.SEEDS_SOYBEANS_NETHER.get(), TofuItems.SEEDS_SOYBEANS_SOUL.get(), TofuItems.SEEDS_SOYBEANS_PALE.get(), TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get(), TofuItems.SEEDS_RICE.get());

		tag(Tags.Items.FOODS).addTag(TofuTags.Items.COOKED_TOFU).add(TofuItems.TOFU_ANKAKE.get()).add(TofuItems.SANBUZHAN.get()).add(TofuItems.CHIKUWA.get()).add(TofuItems.TOFU_CHIKUWA.get())
				.add(TofuItems.TOFU_ISHI.get()).add(TofuItems.TOFU_STEAK.get()).add(TofuItems.OAGE.get())
				.add(TofuBlocks.GRILLED_TOFU.asItem()).add(TofuBlocks.GIANT_OKARA_DONUT.asItem()).add(TofuItems.AGEDASHI_TOFU.get()).add(TofuItems.TOFU_ANNIN.get())
				.add(TofuItems.SPROUTS.get()).add(TofuItems.NATTO.get()).add(TofuItems.NETHER_NATTO.get())
				.add(TofuItems.YUBA.get()).add(TofuItems.BOILED_EDAMAME.get()).add(TofuItems.TOFU_HAMBURG.get())
				.add(TofuItems.SOYMEAT.get()).add(TofuItems.RAW_TOFU_FISH.get()).add(TofuItems.COOKED_TOFU_FISH.get())
				.add(TofuItems.MISODENGAKU.get()).add(TofuItems.MISO_CHEESE_DENGAKU.get()).add(TofuItems.TOFUCOOKIE.get()).add(TofuItems.TTTBURGER.get())
				.add(TofuItems.MEAT_WRAPPED_YUBA.get()).add(TofuItems.SOYSTICK.get()).add(TofuItems.LONGER_SOYSTICK.get())
				.add(TofuItems.MISOSOUP.get()).add(TofuItems.MOYASHIITAME.get()).add(TofuItems.MOYASHIOHITASHI.get())
				.add(TofuItems.SALTYMELON.get()).add(TofuItems.CHILI.get()).add(TofuItems.MABODOFU.get()).add(TofuItems.NANBAN.get()).add(TofuItems.NANBANTOFU.get())
				.add(TofuItems.FUKUMENI.get()).add(TofuItems.KOYADOFUSTEW.get()).add(TofuItems.KINAKO_MANJU.get()).add(TofuItems.ZUNDA_MANJU.get()).add(TofuItems.NETHER_MANJU.get()).add(TofuItems.SOUL_MANJU.get())
				.add(TofuItems.SOY_CHOCOLATE.get()).add(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get())
				.add(TofuItems.ZUNDA_MOCHI.get()).add(TofuItems.KINAKO_MOCHI.get()).add(TofuItems.SESAME_OHAGI.get())
				.add(TofuItems.CRIMSON_SOUP.get()).add(TofuItems.ONIGIRI.get()).add(TofuItems.ONIGIRI_SALT.get()).add(TofuItems.YAKIONIGIRI_MISO.get()).add(TofuItems.YAKIONIGIRI_SHOYU.get())
				.add(TofuItems.RICE_BURGER.get()).add(TofuItems.RICE_NATTO.get()).add(TofuItems.RICE_NATTO_LEEK.get()).add(TofuItems.RICE_NETHER_NATTO.get()).add(TofuItems.RICE_NETHER_NATTO_LEEK.get())
				.add(TofuItems.RICE_TOFU.get()).add(TofuItems.RICE_SOBORO_TOFU.get()).add(TofuItems.GOHEIMOCHI.get())
				.add(TofuItems.INARI.get()).add(TofuItems.OKARASTICK.get()).add(TofuItems.OKARA_DONUT.get())
				.add(TofuItems.SOBOROTOFUSAUTE.get()).add(TofuItems.PUDDING.get()).add(TofuItems.PUDDING_SOYMILK.get())
				.add(TofuItems.NIKUJAGA.get()).add(TofuItems.APRICOT.get()).add(TofuItems.APRICOTJERRY_BREAD.get())
				.add(TofuItems.TOMATO_SOYBEAN_STEW.get()).add(TofuItems.YUDOFU.get()).add(TofuItems.EDAMAME_RICE.get())
				.add(TofuItems.TOFUSOMEN.get()).add(TofuItems.TASTYSTEW.get()).add(TofuItems.TASTYBEEFSTEW.get())
				.add(TofuItems.HIYAYAKKO_GLASS.get()).add(TofuItems.NATTOHIYAYAKKO_GLASS.get())
				.add(TofuItems.WARABI_MOCHI.get()).add(TofuItems.GRATIN.get()).add(TofuItems.CAPRESE.get())
				.add(TofuItems.MUSHROOM_ANKAKE.get()).add(TofuItems.TOFU_ANKAKE.get())
				.add(TofuItems.SOYSAUSE_RAMEN.get()).add(TofuItems.HELL_MABOU.get()).add(TofuItems.HELL_RED_SOUP.get()).add(TofuItems.RED_SOUP.get())
				.add(TofuItems.SUKIYAKI.get()).add(TofuItems.TOFU_BUNS_BURGER.get())
				.add(TofuItems.STEAMED_BREAD.get()).add(TofuItems.STEAMED_BREAD_COCOA.get()).add(TofuItems.STEAMED_BREAD_SESAME.get())
				.add(TofuItems.SANBUZHAN.get()).add(TofuItems.SOY_CHEESE.get()).add(TofuItems.SOY_NETHER_CHEESE.get()).add(TofuItems.SOY_SOUL_CHEESE.get())
				.add(TofuItems.KINAKO_BREAD.get()).add(TofuItems.EDAMAME_TEMPLA.get()).add(TofuItems.NEGIMA.get())
				.add(TofuItems.SOY_KARAAGE.get()).add(TofuItems.SOYMEATDON.get())
				.add(TofuItems.ZUNDA_INGOT.get())
				.add(TofuItems.MONSTER_JERKY.get()).add(TofuItems.ROTTEN_PORK.get()).add(TofuItems.MONSTER_PORK_JERKY.get());


		tag(TofuTags.Items.RICE_BLOCK).add(TofuBlocks.RICE_BLOCK.get().asItem());
		tag(TofuTags.Items.SOYBEANS_BLOCK).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.get().asItem()).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get().asItem()).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get().asItem());
		tag(Tags.Items.STORAGE_BLOCKS).addTags(TofuTags.Items.RICE_BLOCK).addTags(TofuTags.Items.SOYBEANS_BLOCK);
		tag(ItemTags.METAL_NUGGETS).add(TofuItems.TOFU_METAL_NUGGET.get()).add(TofuItems.TOFU_DIAMOND_NUGGET.get());

		tag(ItemTags.LANTERNS).add(TofuBlocks.TOFU_METAL_LANTERN.get().asItem()).add(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get().asItem());
		tag(ItemTags.CHAINS).add(TofuBlocks.TOFU_METAL_CHAIN.get().asItem());

		tag(ItemTags.SWORDS).add(TofuItems.ZUNDA_ALLOY_TOFU_SWORD.get()).add(TofuItems.TOFU_KINU_SWORD.get()).add(TofuItems.TOFU_MOMEN_SWORD.get()).add(TofuItems.TOFU_SOLID_SWORD.get()).add(TofuItems.TOFU_METAL_SWORD.get()).add(TofuItems.TOFU_DIAMOND_SWORD.get());
		tag(ItemTags.PICKAXES).add(TofuItems.TOFU_KINU_PICKAXE.get()).add(TofuItems.TOFU_MOMEN_PICKAXE.get()).add(TofuItems.TOFU_SOLID_PICKAXE.get()).add(TofuItems.TOFU_METAL_PICKAXE.get()).add(TofuItems.TOFU_DIAMOND_PICKAXE.get());
		tag(ItemTags.AXES).add(TofuItems.TOFU_KINU_AXE.get()).add(TofuItems.TOFU_MOMEN_AXE.get()).add(TofuItems.TOFU_SOLID_AXE.get()).add(TofuItems.TOFU_METAL_AXE.get()).add(TofuItems.TOFU_DIAMOND_AXE.get());
		tag(ItemTags.SHOVELS).add(TofuItems.TOFU_KINU_SHOVEL.get()).add(TofuItems.TOFU_MOMEN_SHOVEL.get()).add(TofuItems.TOFU_SOLID_SHOVEL.get()).add(TofuItems.TOFU_METAL_SHOVEL.get()).add(TofuItems.TOFU_DIAMOND_SHOVEL.get());
		tag(ItemTags.HOES).add(TofuItems.TOFU_KINU_HOE.get()).add(TofuItems.TOFU_MOMEN_HOE.get()).add(TofuItems.TOFU_SOLID_HOE.get()).add(TofuItems.TOFU_METAL_HOE.get()).add(TofuItems.TOFU_DIAMOND_HOE.get());
		this.tag(ItemTags.SPEARS)
				.add(TofuItems.TOFU_KINU_SPEAR.get()).add(TofuItems.TOFU_MOMEN_SPEAR.get()).add(TofuItems.TOFU_SOLID_SPEAR.get()).add(TofuItems.TOFU_METAL_SPEAR.get()).add(TofuItems.TOFU_DIAMOND_SPEAR.get());
		this.tag(Tags.Items.TOOLS_SHEAR).add(TofuItems.TOFU_METAL_SHEARS.get());

		this.tag(Tags.Items.TOOLS_SHEAR).add(TofuItems.TOFU_METAL_SHEARS.get());


		this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(TofuItems.ZUNDA_BOW.get());
		this.tag(ItemTags.BOW_ENCHANTABLE).add(TofuItems.ZUNDA_BOW.get());

		tag(ItemTags.HEAD_ARMOR).add(TofuItems.TOFU_DIAMOND_HELMET.get())
				.add(TofuItems.TOFU_METAL_HELMET.get())
				.add(TofuItems.TOFU_SOLID_HELMET.get())
				.add(TofuItems.TOFU_MOMEN_HELMET.get())
				.add(TofuItems.TOFU_KINU_HELMET.get());

		tag(ItemTags.CHEST_ARMOR).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.get())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.get())
				.add(TofuItems.TOFU_SOLID_CHESTPLATE.get())
				.add(TofuItems.TOFU_MOMEN_CHESTPLATE.get())
				.add(TofuItems.TOFU_KINU_CHESTPLATE.get());

		tag(ItemTags.LEG_ARMOR).add(TofuItems.TOFU_DIAMOND_LEGGINGS.get())
				.add(TofuItems.TOFU_METAL_LEGGINGS.get())
				.add(TofuItems.TOFU_SOLID_LEGGINGS.get())
				.add(TofuItems.TOFU_MOMEN_LEGGINGS.get())
				.add(TofuItems.TOFU_KINU_LEGGINGS.get());
		tag(ItemTags.FOOT_ARMOR).add(TofuItems.TOFU_DIAMOND_BOOTS.get())
				.add(TofuItems.TOFU_METAL_BOOTS.get())
				.add(TofuItems.TOFU_SOLID_BOOTS.get())
				.add(TofuItems.TOFU_MOMEN_BOOTS.get())
				.add(TofuItems.TOFU_KINU_BOOTS.get());

		tag(TofuTags.Items.NATTO).add(TofuItems.NATTO.get());
		tag(TofuTags.Items.MISO).add(TofuItems.MISO.get());
		tag(TofuTags.Items.SOYSAUCE_SOYSAUCE).add(TofuItems.BOTTLE_SOYSAUSE.get());
		tag(TofuTags.Items.SOYSAUCE).addTag(TofuTags.Items.SOYSAUCE_SOYSAUCE);
		tag(TofuTags.Items.TOFU).add(TofuItems.TOFU_KINU.get()).add(TofuItems.TOFU_MOMEN.get());
		tag(TofuTags.Items.COOKED_TOFU).add(TofuItems.TOFU_ISHI.get()).add(TofuItems.TOFU_ANNIN.get()).add(TofuItems.TOFU_SESAME.get())
				.add(TofuItems.TOFU_FRIED.get()).add(TofuItems.TOFU_FRIED_POUCH.get())
				.add(TofuItems.TOFU_ZUNDA.get()).add(TofuItems.TOFU_EGG.get()).add(TofuItems.TOFU_MISO.get()).add(TofuItems.TOFU_GRILLED.get()).add(TofuItems.TOFU_DRIED.get()).add(TofuItems.TOFU_STRAWBERRY.get()).add(TofuItems.TOFU_SMOKE.get())
				.add(TofuItems.TOFU_HELL.get()).add(TofuItems.TOFU_SOUL.get()).add(TofuItems.SHUDOFU.get());

		tag(TofuTags.Items.TOFU_FRIED).add(TofuItems.TOFU_FRIED.get());
		tag(ItemTags.PIGLIN_REPELLENTS).add(TofuItems.SOUL_FUKUMAME.get(), TofuItems.SOUL_MANJU.get(), TofuItems.SEEDS_SOYBEANS_SOUL.get());
		tag(ItemTags.TRIM_MATERIALS).add(TofuItems.ZUNDARUBY.get(), TofuItems.TOFU_METAL.get(), TofuItems.TOFU_DIAMOND.get());
		tag(ItemTags.BOATS).add(TofuItems.LEEK_BOAT.get(), TofuItems.LEEK_GREEN_BOAT.get(), TofuItems.TOFU_STEM_BOAT.get());
		tag(ItemTags.CHEST_BOATS).add(TofuItems.LEEK_CHEST_BOAT.get(), TofuItems.LEEK_GREEN_CHEST_BOAT.get(), TofuItems.TOFU_STEM_CHEST_BOAT.get());
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(TofuBlocks.TOFUSLATE.get().asItem());

		tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(TofuItems.SEEDS_CHILI.get(), TofuItems.SEEDS_SOYBEANS.get());
		tag(ItemTags.WOODEN_SLABS).add(TofuBlocks.SPROUT_PLANKS_SLAB.get().asItem()).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get().asItem()).add(TofuBlocks.LEEK_PLANKS_SLAB.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get().asItem());
		tag(ItemTags.WOODEN_STAIRS).add(TofuBlocks.SPROUT_PLANKS_STAIR.get().asItem()).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get().asItem()).add(TofuBlocks.LEEK_PLANKS_STAIR.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get().asItem());
		tag(ItemTags.WOODEN_FENCES).add(TofuBlocks.SPROUT_FENCE.get().asItem()).add(TofuBlocks.LEEK_FENCE.get().asItem()).add(TofuBlocks.LEEK_GREEN_FENCE.get().asItem()).add(TofuBlocks.TOFU_STEM_FENCE.get().asItem());
		tag(ItemTags.FENCE_GATES).add(TofuBlocks.SPROUT_FENCE_GATE.get().asItem()).add(TofuBlocks.TOFU_STEM_FENCE_GATE.get().asItem()).add(TofuBlocks.LEEK_FENCE_GATE.get().asItem()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.get().asItem());
		tag(ItemTags.WOODEN_DOORS).add(TofuBlocks.SPROUT_DOOR.get().asItem()).add(TofuBlocks.TOFU_STEM_DOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_DOOR.get().asItem()).add(TofuBlocks.LEEK_DOOR.get().asItem());
		tag(ItemTags.DOORS).add(TofuBlocks.SPROUT_DOOR.get().asItem()).add(TofuBlocks.TOFU_STEM_DOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_DOOR.get().asItem()).add(TofuBlocks.LEEK_DOOR.get().asItem());
		tag(ItemTags.WOODEN_TRAPDOORS).add(TofuBlocks.SPROUT_TRAPDOOR.get().asItem()).add(TofuBlocks.TOFU_STEM_TRAPDOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_TRAPDOOR.get().asItem()).add(TofuBlocks.LEEK_TRAPDOOR.get().asItem());
		tag(ItemTags.TRAPDOORS).add(TofuBlocks.SPROUT_TRAPDOOR.get().asItem()).add(TofuBlocks.TOFU_STEM_TRAPDOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_TRAPDOOR.get().asItem()).add(TofuBlocks.LEEK_TRAPDOOR.get().asItem());
		tag(ItemTags.WOODEN_PRESSURE_PLATES).add(TofuBlocks.SPROUT_PRESSURE_PLATE.get().asItem()).add(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get().asItem()).add(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get().asItem()).add(TofuBlocks.LEEK_PRESSURE_PLATE.get().asItem());
		tag(ItemTags.WOODEN_BUTTONS).add(TofuBlocks.SPROUT_BUTTON.get().asItem()).add(TofuBlocks.TOFU_STEM_BUTTON.get().asItem()).add(TofuBlocks.LEEK_GREEN_BUTTON.get().asItem()).add(TofuBlocks.LEEK_BUTTON.get().asItem());


		this.tag(ItemTags.WOODEN_SHELVES)
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.get().asItem(),
						TofuBlocks.LEEK_SHELF.get().asItem(),
						TofuBlocks.TOFU_STEM_SHELF.get().asItem(),
						TofuBlocks.SPROUT_SHELF.get().asItem()
				);
		this.tag(ItemTags.SIGNS)
				.add(
						TofuBlocks.LEEK_SIGN.get().asItem(),
						TofuBlocks.LEEK_GREEN_SIGN.get().asItem(),
						TofuBlocks.TOFU_STEM_SIGN.get().asItem(),
						TofuBlocks.SPROUT_SIGN.get().asItem()
				);
		this.tag(ItemTags.HANGING_SIGNS)
				.add(
						TofuBlocks.LEEK_HANGING_SIGN.get().asItem(),
						TofuBlocks.LEEK_GREEN_HANGING_SIGN.get().asItem(),
						TofuBlocks.TOFU_STEM_HANGING_SIGN.get().asItem(),
						TofuBlocks.SPROUT_HANGING_SIGN.get().asItem()
				);

		this.tag(TofuTags.Items.STATUE_HAPPY).add(TofuItems.EDAMAME_TEMPLA.get(), TofuItems.ZUNDA_MOCHI.get(), TofuItems.ZUNDA_MANJU.get()
				, TofuItems.KINAKO_MOCHI.get(), TofuItems.KINAKO_MANJU.get(), TofuItems.GOHEIMOCHI.get()
				, TofuItems.RICE_SOBORO_TOFU.get()
						, TofuItems.NANBANTOFU.get(), TofuItems.KOYADOFUSTEW.get(), TofuItems.MOYASHIITAME.get(), TofuItems.MOYASHIOHITASHI.get(), TofuItems.TOFUCOOKIE.get(), TofuItems.TOFU_ANNIN.get())
				.add(TofuItems.SESAME_OHAGI.get());

		this.tag(TofuTags.Items.TOFU_DIAMOND_ARMOR_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_HELMET.get(), TofuItems.TOFU_DIAMOND_CHESTPLATE.get(), TofuItems.TOFU_DIAMOND_LEGGINGS.get(), TofuItems.TOFU_DIAMOND_BOOTS.get());
		this.tag(TofuTags.Items.TOFU_DIAMOND_SWORD_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_SWORD.get());
		this.tag(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_PICKAXE.get()).add(TofuItems.TOFU_DIAMOND_SHOVEL.get()).add(TofuItems.TOFU_DIAMOND_HOE.get());

		this.tag(TofuTags.Items.FUKUMAME_ENCHANTABLE).add(TofuItems.FUKUMAME.get()).add(TofuItems.NETHER_FUKUMAME.get()).add(TofuItems.SOUL_FUKUMAME.get());

		this.tag(ItemTags.DURABILITY_ENCHANTABLE).addTag(TofuTags.Items.FUKUMAME_ENCHANTABLE);
		this.tag(ItemTags.HEAD_ARMOR).add(TofuItems.TOFU_KINU_HELMET.get()).add(TofuItems.TOFU_MOMEN_HELMET.get())
				.add(TofuItems.TOFU_METAL_HELMET.get()).add(TofuItems.TOFU_DIAMOND_HELMET.get());
		this.tag(ItemTags.CHEST_ARMOR).add(TofuItems.TOFU_KINU_CHESTPLATE.get()).add(TofuItems.TOFU_MOMEN_CHESTPLATE.get())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.get()).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.get());
		this.tag(ItemTags.LEG_ARMOR).add(TofuItems.TOFU_KINU_LEGGINGS.get()).add(TofuItems.TOFU_MOMEN_LEGGINGS.get())
				.add(TofuItems.TOFU_METAL_LEGGINGS.get()).add(TofuItems.TOFU_DIAMOND_LEGGINGS.get());
		this.tag(ItemTags.FOOT_ARMOR).add(TofuItems.TOFU_KINU_BOOTS.get()).add(TofuItems.TOFU_MOMEN_BOOTS.get())
				.add(TofuItems.TOFU_METAL_BOOTS.get()).add(TofuItems.TOFU_DIAMOND_BOOTS.get());
		this.tag(TofuTags.Items.TOFU_TOOL_MATERIAL).add(TofuItems.TOFU_MOMEN.get(), TofuItems.TOFU_KINU.get());
		this.tag(TofuTags.Items.TOFU_SOLID_TOOL_MATERIAL).add(TofuItems.TOFU_ISHI.get());
		this.tag(TofuTags.Items.TOFU_METAL_TOOL_MATERIAL).add(TofuItems.TOFU_METAL.get());
		this.tag(TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL).add(TofuItems.TOFU_DIAMOND.get());

		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_HELMET.get()).add(TofuItems.TOFU_MOMEN_HELMET.get())
				.add(TofuItems.TOFU_METAL_HELMET.get()).add(TofuItems.TOFU_DIAMOND_HELMET.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_CHESTPLATE.get()).add(TofuItems.TOFU_MOMEN_CHESTPLATE.get())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.get()).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_LEGGINGS.get()).add(TofuItems.TOFU_MOMEN_LEGGINGS.get())
				.add(TofuItems.TOFU_METAL_LEGGINGS.get()).add(TofuItems.TOFU_DIAMOND_LEGGINGS.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_BOOTS.get()).add(TofuItems.TOFU_MOMEN_BOOTS.get())
				.add(TofuItems.TOFU_METAL_BOOTS.get()).add(TofuItems.TOFU_DIAMOND_BOOTS.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_SWORD.get()).add(TofuItems.TOFU_MOMEN_SWORD.get())
				.add(TofuItems.TOFU_METAL_SWORD.get()).add(TofuItems.TOFU_DIAMOND_SWORD.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_AXE.get()).add(TofuItems.TOFU_MOMEN_AXE.get())
				.add(TofuItems.TOFU_METAL_AXE.get()).add(TofuItems.TOFU_DIAMOND_AXE.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_DIAMOND_PICKAXE.get()).add(TofuItems.TOFU_DIAMOND_PICKAXE.get())
				.add(TofuItems.TOFU_DIAMOND_PICKAXE.get()).add(TofuItems.TOFU_DIAMOND_PICKAXE.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_DIAMOND_SHOVEL.get()).add(TofuItems.TOFU_DIAMOND_SHOVEL.get())
				.add(TofuItems.TOFU_DIAMOND_SHOVEL.get()).add(TofuItems.TOFU_DIAMOND_SHOVEL.get());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.ZUNDA_BOW.get());

		this.tag(ItemTags.CHICKEN_FOOD).add(TofuItems.SEEDS_RICE.get(), TofuItems.SOYBEAN_PARCHED.get());
		this.tag(ItemTags.COW_FOOD).add(TofuItems.RICE.get());
		this.tag(ItemTags.PARROT_FOOD).add(TofuItems.SEEDS_RICE.get(), TofuItems.SOYBEAN_PARCHED.get());
		this.tag(ItemTags.SMALL_FLOWERS).add(TofuBlocks.TOFU_FLOWER.get().asItem());

		this.tag(ItemTags.FOX_FOOD).add(TofuItems.OAGE.get());
		this.tag(TofuTags.Items.TOFU_COW_FOOD).add(TofuItems.LEEK.get());
		this.tag(TofuTags.Items.TOFU_PIG_FOOD).add(TofuItems.LEEK.get(), TofuItems.NATTO.get());
		this.tag(TofuTags.Items.BITTERN).add(TofuItems.BITTERN_BOTTLE.get()).add(TofuItems.WARPED_BOTTLE.get()).add(TofuItems.CRIMSON_BOTTLE.get());
		this.tag(ItemTags.BOOKSHELF_BOOKS).add(TofuItems.TOFU_CRAFTERS_BOOK.get());
		this.tag(ItemTags.LECTERN_BOOKS).add(TofuItems.TOFU_CRAFTERS_BOOK.get());
		this.tag(Tags.Items.MUSIC_DISCS).add(TofuItems.MUSIC_DISC_GREEN_BRANCH.get());
	}
}
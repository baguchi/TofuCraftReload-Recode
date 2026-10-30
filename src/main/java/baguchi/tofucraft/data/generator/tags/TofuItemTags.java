package baguchi.tofucraft.data.generator.tags;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagCopyingItemTagProvider;

import java.util.concurrent.CompletableFuture;

public class TofuItemTags extends BlockTagCopyingItemTagProvider {
	public TofuItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
		super(packOutput, lookupProvider, blockTags, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		(new TofuBlockItemTags((tagId) -> TofuBlockItemTags.wrapForItems(this.tag(tagId.item())))).run();

		tag(TofuTags.Items.DUST_SALT).add(TofuItems.SALT.key());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.key());
		tag(TofuTags.Items.STORAGE_SALT).add(TofuBlocks.SALT_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_TOFUGEM).add(TofuBlocks.TOFU_GEM_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_ADV_TOFUGEM).add(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS_NETHER).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS_SOUL).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.asItem());

		tag(TofuTags.Items.SALT).add(TofuItems.SALT.key());
		tag(TofuTags.Items.SOYBEAN).add(TofuItems.SEEDS_SOYBEANS.key());
		tag(TofuTags.Items.SOYMILK).addTag(TofuTags.Items.MILK_SOYMILK);
		tag(TofuTags.Items.MILK_SOYMILK).add(TofuItems.SOYMILK_BUCKET.key(), TofuItems.SOYMILK_BOTTLE.key());
		tag(TofuTags.Items.RICE).add(TofuItems.RICE.key());
		tag(ItemTags.ARROWS).add(TofuItems.ZUNDA_ARROW.key());
		tag(Tags.Items.SEEDS).add(TofuItems.SEEDS_CHILI.key(), TofuItems.SEEDS_SESAME.key(), TofuItems.SEEDS_SOYBEANS.key(), TofuItems.SEEDS_SOYBEANS_NETHER.key(), TofuItems.SEEDS_SOYBEANS_SOUL.key(), TofuItems.SEEDS_SOYBEANS_PALE.key(), TofuItems.SEEDS_SOYBEANS_PALE_GLOW.key(), TofuItems.SEEDS_RICE.key());
		tag(Tags.Items.FOODS).addTag(TofuTags.Items.COOKED_TOFU).add(TofuItems.TOFU_ANKAKE.get()).add(TofuItems.SANBUZHAN.get()).add(TofuItems.CHIKUWA.get()).add(TofuItems.TOFU_CHIKUWA.get())
				.add(TofuItems.TOFU_ISHI.get()).add(TofuItems.TOFU_STEAK.get()).add(TofuItems.OAGE.get())
				.add(TofuBlocks.GRILLED_TOFU.asItem()).add(TofuBlocks.GIANT_OKARA_DONUT.asItem()).add(TofuItems.AGEDASHI_TOFU.key()).add(TofuItems.TOFU_ANNIN.key())
				.add(TofuItems.SPROUTS.key()).add(TofuItems.NATTO.key()).add(TofuItems.NETHER_NATTO.key())
				.add(TofuItems.YUBA.key()).add(TofuItems.BOILED_EDAMAME.key()).add(TofuItems.TOFU_HAMBURG.key())
				.add(TofuItems.SOYMEAT.key()).add(TofuItems.RAW_TOFU_FISH.key()).add(TofuItems.COOKED_TOFU_FISH.key())
				.add(TofuItems.MISODENGAKU.key()).add(TofuItems.MISO_CHEESE_DENGAKU.key()).add(TofuItems.TOFUCOOKIE.key()).add(TofuItems.TTTBURGER.key())
				.add(TofuItems.MEAT_WRAPPED_YUBA.key()).add(TofuItems.SOYSTICK.key()).add(TofuItems.LONGER_SOYSTICK.key())
				.add(TofuItems.MISOSOUP.key()).add(TofuItems.MOYASHIITAME.key()).add(TofuItems.MOYASHIOHITASHI.key())
				.add(TofuItems.SALTYMELON.key()).add(TofuItems.CHILI.key()).add(TofuItems.MABODOFU.key()).add(TofuItems.NANBAN.key()).add(TofuItems.NANBANTOFU.key())
				.add(TofuItems.FUKUMENI.key()).add(TofuItems.KOYADOFUSTEW.key()).add(TofuItems.KINAKO_MANJU.key()).add(TofuItems.ZUNDA_MANJU.key()).add(TofuItems.NETHER_MANJU.key()).add(TofuItems.SOUL_MANJU.key())
				.add(TofuItems.SOY_CHOCOLATE.key()).add(TofuItems.TOFUNIAN_SOY_CHOCOLATE.key())
				.add(TofuItems.ZUNDA_MOCHI.key()).add(TofuItems.KINAKO_MOCHI.key()).add(TofuItems.SESAME_OHAGI.key())
				.add(TofuItems.CRIMSON_SOUP.key()).add(TofuItems.ONIGIRI.key()).add(TofuItems.ONIGIRI_SALT.key()).add(TofuItems.YAKIONIGIRI_MISO.key()).add(TofuItems.YAKIONIGIRI_SHOYU.key())
				.add(TofuItems.RICE_BURGER.key()).add(TofuItems.RICE_NATTO.key()).add(TofuItems.RICE_NATTO_LEEK.key()).add(TofuItems.RICE_NETHER_NATTO.key()).add(TofuItems.RICE_NETHER_NATTO_LEEK.key())
				.add(TofuItems.RICE_TOFU.key()).add(TofuItems.RICE_SOBORO_TOFU.key()).add(TofuItems.GOHEIMOCHI.key())
				.add(TofuItems.INARI.key()).add(TofuItems.OKARASTICK.key()).add(TofuItems.OKARA_DONUT.key())
				.add(TofuItems.SOBOROTOFUSAUTE.key()).add(TofuItems.PUDDING.key()).add(TofuItems.PUDDING_SOYMILK.key())
				.add(TofuItems.NIKUJAGA.key()).add(TofuItems.APRICOT.key()).add(TofuItems.APRICOTJERRY_BREAD.key())
				.add(TofuItems.TOMATO_SOYBEAN_STEW.key()).add(TofuItems.YUDOFU.key()).add(TofuItems.EDAMAME_RICE.key())
				.add(TofuItems.TOFUSOMEN.key()).add(TofuItems.TASTYSTEW.key()).add(TofuItems.TASTYBEEFSTEW.key())
				.add(TofuItems.HIYAYAKKO_GLASS.key()).add(TofuItems.NATTOHIYAYAKKO_GLASS.key())
				.add(TofuItems.WARABI_MOCHI.key()).add(TofuItems.GRATIN.key()).add(TofuItems.CAPRESE.key())
				.add(TofuItems.MUSHROOM_ANKAKE.key()).add(TofuItems.TOFU_ANKAKE.key())
				.add(TofuItems.SOYSAUSE_RAMEN.key()).add(TofuItems.HELL_MABOU.key()).add(TofuItems.HELL_RED_SOUP.key()).add(TofuItems.RED_SOUP.key())
				.add(TofuItems.SUKIYAKI.key()).add(TofuItems.TOFU_BUNS_BURGER.key())
				.add(TofuItems.STEAMED_BREAD.key()).add(TofuItems.STEAMED_BREAD_COCOA.key()).add(TofuItems.STEAMED_BREAD_SESAME.key())
				.add(TofuItems.SANBUZHAN.key()).add(TofuItems.SOY_CHEESE.key()).add(TofuItems.SOY_NETHER_CHEESE.key()).add(TofuItems.SOY_SOUL_CHEESE.key())
				.add(TofuItems.KINAKO_BREAD.key()).add(TofuItems.EDAMAME_TEMPLA.key()).add(TofuItems.NEGIMA.key())
				.add(TofuItems.SOY_KARAAGE.key()).add(TofuItems.SOYMEATDON.key())
				.add(TofuItems.ZUNDA_INGOT.key())
				.add(TofuItems.MONSTER_JERKY.key()).add(TofuItems.ROTTEN_PORK.key()).add(TofuItems.MONSTER_PORK_JERKY.key());


		tag(TofuTags.Items.RICE_BLOCK).add(TofuBlocks.RICE_BLOCK.asItem());
		tag(TofuTags.Items.SOYBEANS_BLOCK).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.asItem()).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.asItem()).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.asItem());
		tag(Tags.BlockItems.STORAGE_BLOCKS.item()).addTags(TofuTags.Items.RICE_BLOCK).addTags(TofuTags.Items.SOYBEANS_BLOCK);
		tag(ItemTags.METAL_NUGGETS).add(TofuItems.TOFU_METAL_NUGGET.key()).add(TofuItems.TOFU_DIAMOND_NUGGET.key());

		tag(ItemTags.SWORDS).add(TofuItems.ZUNDA_ALLOY_TOFU_SWORD.key()).add(TofuItems.TOFU_KINU_SWORD.key()).add(TofuItems.TOFU_MOMEN_SWORD.key()).add(TofuItems.TOFU_SOLID_SWORD.key()).add(TofuItems.TOFU_METAL_SWORD.key()).add(TofuItems.TOFU_DIAMOND_SWORD.key());
		tag(ItemTags.PICKAXES).add(TofuItems.TOFU_KINU_PICKAXE.key()).add(TofuItems.TOFU_MOMEN_PICKAXE.key()).add(TofuItems.TOFU_SOLID_PICKAXE.key()).add(TofuItems.TOFU_METAL_PICKAXE.key()).add(TofuItems.TOFU_DIAMOND_PICKAXE.key());
		tag(ItemTags.AXES).add(TofuItems.TOFU_KINU_AXE.key()).add(TofuItems.TOFU_MOMEN_AXE.key()).add(TofuItems.TOFU_SOLID_AXE.key()).add(TofuItems.TOFU_METAL_AXE.key()).add(TofuItems.TOFU_DIAMOND_AXE.key());
		tag(ItemTags.SHOVELS).add(TofuItems.TOFU_KINU_SHOVEL.key()).add(TofuItems.TOFU_MOMEN_SHOVEL.key()).add(TofuItems.TOFU_SOLID_SHOVEL.key()).add(TofuItems.TOFU_METAL_SHOVEL.key()).add(TofuItems.TOFU_DIAMOND_SHOVEL.key());
		tag(ItemTags.HOES).add(TofuItems.TOFU_KINU_HOE.key()).add(TofuItems.TOFU_MOMEN_HOE.key()).add(TofuItems.TOFU_SOLID_HOE.key()).add(TofuItems.TOFU_METAL_HOE.key()).add(TofuItems.TOFU_DIAMOND_HOE.key());
		this.tag(ItemTags.SPEARS)
				.add(TofuItems.TOFU_KINU_SPEAR.key()).add(TofuItems.TOFU_MOMEN_SPEAR.key()).add(TofuItems.TOFU_SOLID_SPEAR.key()).add(TofuItems.TOFU_METAL_SPEAR.key()).add(TofuItems.TOFU_DIAMOND_SPEAR.key());
		this.tag(Tags.Items.TOOLS_SHEAR).add(TofuItems.TOFU_METAL_SHEARS.key());

		this.tag(Tags.Items.TOOLS_SHEAR).add(TofuItems.TOFU_METAL_SHEARS.key());


		this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(TofuItems.ZUNDA_BOW.key());
		this.tag(ItemTags.BOW_ENCHANTABLE).add(TofuItems.ZUNDA_BOW.key());

		tag(ItemTags.HEAD_ARMOR).add(TofuItems.TOFU_DIAMOND_HELMET.key())
				.add(TofuItems.TOFU_METAL_HELMET.key())
				.add(TofuItems.TOFU_SOLID_HELMET.key())
				.add(TofuItems.TOFU_MOMEN_HELMET.key())
				.add(TofuItems.TOFU_KINU_HELMET.key());

		tag(ItemTags.CHEST_ARMOR).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.key())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.key())
				.add(TofuItems.TOFU_SOLID_CHESTPLATE.key())
				.add(TofuItems.TOFU_MOMEN_CHESTPLATE.key())
				.add(TofuItems.TOFU_KINU_CHESTPLATE.key());

		tag(ItemTags.LEG_ARMOR).add(TofuItems.TOFU_DIAMOND_LEGGINGS.key())
				.add(TofuItems.TOFU_METAL_LEGGINGS.key())
				.add(TofuItems.TOFU_SOLID_LEGGINGS.key())
				.add(TofuItems.TOFU_MOMEN_LEGGINGS.key())
				.add(TofuItems.TOFU_KINU_LEGGINGS.key());
		tag(ItemTags.FOOT_ARMOR).add(TofuItems.TOFU_DIAMOND_BOOTS.key())
				.add(TofuItems.TOFU_METAL_BOOTS.key())
				.add(TofuItems.TOFU_SOLID_BOOTS.key())
				.add(TofuItems.TOFU_MOMEN_BOOTS.key())
				.add(TofuItems.TOFU_KINU_BOOTS.key());

		tag(TofuTags.Items.NATTO).add(TofuItems.NATTO.key());
		tag(TofuTags.Items.MISO).add(TofuItems.MISO.key());

		tag(TofuTags.Items.SOYSAUCE_SOYSAUCE).add(TofuItems.BOTTLE_SOYSAUSE.key());
		tag(TofuTags.Items.SOYSAUCE).addTag(TofuTags.Items.SOYSAUCE_SOYSAUCE);

		tag(TofuTags.Items.TOFU).add(TofuItems.TOFU_KINU.key()).add(TofuItems.TOFU_MOMEN.key());
		tag(TofuTags.Items.COOKED_TOFU).add(TofuItems.TOFU_ISHI.key()).add(TofuItems.TOFU_ANNIN.key()).add(TofuItems.TOFU_SESAME.key())
				.add(TofuItems.TOFU_FRIED.key()).add(TofuItems.TOFU_FRIED_POUCH.key())
				.add(TofuItems.TOFU_ZUNDA.key()).add(TofuItems.TOFU_EGG.key()).add(TofuItems.TOFU_MISO.key()).add(TofuItems.TOFU_GRILLED.key()).add(TofuItems.TOFU_DRIED.key()).add(TofuItems.TOFU_STRAWBERRY.key()).add(TofuItems.TOFU_SMOKE.key())
				.add(TofuItems.TOFU_HELL.key()).add(TofuItems.TOFU_SOUL.key()).add(TofuItems.SHUDOFU.key());

		tag(TofuTags.Items.TOFU_FRIED).add(TofuItems.TOFU_FRIED.key());


		tag(ItemTags.PIGLIN_REPELLENTS).add(TofuItems.SOUL_FUKUMAME.key(), TofuItems.SOUL_MANJU.key(), TofuItems.SEEDS_SOYBEANS_SOUL.key());
		tag(ItemTags.TRIM_MATERIALS).add(TofuItems.ZUNDARUBY.key(), TofuItems.TOFU_METAL.key(), TofuItems.TOFU_DIAMOND.key());
		tag(ItemTags.BOATS).add(TofuItems.LEEK_BOAT.key(), TofuItems.LEEK_GREEN_BOAT.key(), TofuItems.TOFU_STEM_BOAT.key());
		tag(ItemTags.CHEST_BOATS).add(TofuItems.LEEK_CHEST_BOAT.key(), TofuItems.LEEK_GREEN_CHEST_BOAT.key(), TofuItems.TOFU_STEM_CHEST_BOAT.key());
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(TofuBlocks.TOFUSLATE.asItem());

		tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(TofuItems.SEEDS_CHILI.key(), TofuItems.SEEDS_SOYBEANS.key());

		this.tag(TofuTags.Items.STATUE_HAPPY).add(TofuItems.EDAMAME_TEMPLA.key(), TofuItems.ZUNDA_MOCHI.key(), TofuItems.ZUNDA_MANJU.key()
						, TofuItems.KINAKO_MOCHI.key(), TofuItems.KINAKO_MANJU.key(), TofuItems.GOHEIMOCHI.key()
						, TofuItems.RICE_SOBORO_TOFU.key()
						, TofuItems.NANBANTOFU.key(), TofuItems.KOYADOFUSTEW.key(), TofuItems.MOYASHIITAME.key(), TofuItems.MOYASHIOHITASHI.key(), TofuItems.TOFUCOOKIE.key(), TofuItems.TOFU_ANNIN.key())
				.add(TofuItems.SESAME_OHAGI.key());

		this.tag(TofuTags.Items.TOFU_DIAMOND_ARMOR_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_HELMET.key(), TofuItems.TOFU_DIAMOND_CHESTPLATE.key(), TofuItems.TOFU_DIAMOND_LEGGINGS.key(), TofuItems.TOFU_DIAMOND_BOOTS.key());
		this.tag(TofuTags.Items.TOFU_DIAMOND_SWORD_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_SWORD.key());
		this.tag(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_PICKAXE.key()).add(TofuItems.TOFU_DIAMOND_SHOVEL.key()).add(TofuItems.TOFU_DIAMOND_HOE.key());

		this.tag(TofuTags.Items.FUKUMAME_ENCHANTABLE).add(TofuItems.FUKUMAME.key()).add(TofuItems.NETHER_FUKUMAME.key()).add(TofuItems.SOUL_FUKUMAME.key());

		this.tag(ItemTags.DURABILITY_ENCHANTABLE).addTag(TofuTags.Items.FUKUMAME_ENCHANTABLE);
		this.tag(ItemTags.HEAD_ARMOR).add(TofuItems.TOFU_KINU_HELMET.key()).add(TofuItems.TOFU_MOMEN_HELMET.key())
				.add(TofuItems.TOFU_METAL_HELMET.key()).add(TofuItems.TOFU_DIAMOND_HELMET.key());
		this.tag(ItemTags.CHEST_ARMOR).add(TofuItems.TOFU_KINU_CHESTPLATE.key()).add(TofuItems.TOFU_MOMEN_CHESTPLATE.key())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.key()).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.key());
		this.tag(ItemTags.LEG_ARMOR).add(TofuItems.TOFU_KINU_LEGGINGS.key()).add(TofuItems.TOFU_MOMEN_LEGGINGS.key())
				.add(TofuItems.TOFU_METAL_LEGGINGS.key()).add(TofuItems.TOFU_DIAMOND_LEGGINGS.key());
		this.tag(ItemTags.FOOT_ARMOR).add(TofuItems.TOFU_KINU_BOOTS.key()).add(TofuItems.TOFU_MOMEN_BOOTS.key())
				.add(TofuItems.TOFU_METAL_BOOTS.key()).add(TofuItems.TOFU_DIAMOND_BOOTS.key());
		this.tag(TofuTags.Items.TOFU_TOOL_MATERIAL).add(TofuItems.TOFU_MOMEN.key(), TofuItems.TOFU_KINU.key());
		this.tag(TofuTags.Items.TOFU_SOLID_TOOL_MATERIAL).add(TofuItems.TOFU_ISHI.key());
		this.tag(TofuTags.Items.TOFU_METAL_TOOL_MATERIAL).add(TofuItems.TOFU_METAL.key());
		this.tag(TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL).add(TofuItems.TOFU_DIAMOND.key());

		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_HELMET.key()).add(TofuItems.TOFU_MOMEN_HELMET.key())
				.add(TofuItems.TOFU_METAL_HELMET.key()).add(TofuItems.TOFU_DIAMOND_HELMET.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_CHESTPLATE.key()).add(TofuItems.TOFU_MOMEN_CHESTPLATE.key())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.key()).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_LEGGINGS.key()).add(TofuItems.TOFU_MOMEN_LEGGINGS.key())
				.add(TofuItems.TOFU_METAL_LEGGINGS.key()).add(TofuItems.TOFU_DIAMOND_LEGGINGS.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_BOOTS.key()).add(TofuItems.TOFU_MOMEN_BOOTS.key())
				.add(TofuItems.TOFU_METAL_BOOTS.key()).add(TofuItems.TOFU_DIAMOND_BOOTS.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_SWORD.key()).add(TofuItems.TOFU_MOMEN_SWORD.key())
				.add(TofuItems.TOFU_METAL_SWORD.key()).add(TofuItems.TOFU_DIAMOND_SWORD.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_AXE.key()).add(TofuItems.TOFU_MOMEN_AXE.key())
				.add(TofuItems.TOFU_METAL_AXE.key()).add(TofuItems.TOFU_DIAMOND_AXE.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_DIAMOND_PICKAXE.key()).add(TofuItems.TOFU_DIAMOND_PICKAXE.key())
				.add(TofuItems.TOFU_DIAMOND_PICKAXE.key()).add(TofuItems.TOFU_DIAMOND_PICKAXE.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_DIAMOND_SHOVEL.key()).add(TofuItems.TOFU_DIAMOND_SHOVEL.key())
				.add(TofuItems.TOFU_DIAMOND_SHOVEL.key()).add(TofuItems.TOFU_DIAMOND_SHOVEL.key());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.ZUNDA_BOW.key());

		this.tag(ItemTags.CHICKEN_FOOD).add(TofuItems.SEEDS_RICE.key(), TofuItems.SOYBEAN_PARCHED.key());
		this.tag(ItemTags.COW_FOOD).add(TofuItems.RICE.key());
		this.tag(ItemTags.PARROT_FOOD).add(TofuItems.SEEDS_RICE.key(), TofuItems.SOYBEAN_PARCHED.key());

		this.tag(ItemTags.FOX_FOOD).add(TofuItems.OAGE.key());
		this.tag(TofuTags.Items.TOFU_COW_FOOD).add(TofuItems.LEEK.key());
		this.tag(TofuTags.Items.TOFU_PIG_FOOD).add(TofuItems.LEEK.key(), TofuItems.NATTO.key());
		this.tag(TofuTags.Items.BITTERN).add(TofuItems.BITTERN_BOTTLE.key()).add(TofuItems.WARPED_BOTTLE.key()).add(TofuItems.CRIMSON_BOTTLE.key());
		this.tag(ItemTags.BOOKSHELF_BOOKS).add(TofuItems.TOFU_CRAFTERS_BOOK.key());
		this.tag(ItemTags.LECTERN_BOOKS).add(TofuItems.TOFU_CRAFTERS_BOOK.key());
		this.tag(Tags.Items.MUSIC_DISCS).add(TofuItems.MUSIC_DISC_GREEN_BRANCH.key());

		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_BOUNCY)
				.add(TofuBlocks.KINUTOFU.asItem())
				.add(TofuBlocks.MOMENTOFU.asItem())
				.add(TofuBlocks.EGG_TOFU.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_FAST_FLAT)
				.add(TofuBlocks.DRIED_TOFU.asItem())
				.add(TofuBlocks.GRILLED_TOFU.asItem())
				.add(TofuBlocks.SESAME_TOFU.asItem())
				.add(TofuBlocks.STRAWBERRY_TOFU.asItem())
				.add(TofuBlocks.MISO_TOFU.asItem())
				.add(TofuBlocks.HELLTOFU.asItem())
				.add(TofuBlocks.SOULTOFU.asItem())
				.add(TofuBlocks.FRIED_TOFU.asItem())
				.add(TofuBlocks.FRIED_POUCH_TOFU.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY)
				.add(TofuBlocks.ZUNDA_TOFU.asItem())
				.add(TofuBlocks.GRILLED_TOFU.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY)
				.add(TofuBlocks.ISHITOFU.asItem())
				.add(TofuBlocks.ISHITOFU_BRICK.asItem())
				.add(TofuBlocks.ISHITOFU_CHISELED_BRICK.asItem())
				.add(TofuBlocks.ISHITOFU_SMOOTH_BRICK.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_FLAT).add(TofuBlocks.DIAMOND_TOFU.asItem()).add(TofuBlocks.METALTOFU.asItem()).add(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.asItem()).add(TofuBlocks.TOFU_GEM_BLOCK.asItem());
	}


	@Override
	protected Appender tag(TagKey<Item> tag) {
		return new Appender(super.tag(tag));
	}

	public record Appender(TagAppender<Item> app) implements TagAppender<Item> {
		@Override
		public Appender add(ResourceKey<Item> element) {
			app.add(element);
			return this;
		}

		@Override
		public Appender addOptional(ResourceKey<Item> element) {
			app.addOptional(element);
			return this;
		}

		@Override
		public Appender addTag(TagKey<Item> tag) {
			app.addTag(tag);
			return this;
		}

		@Override
		public Appender addOptionalTag(TagKey<Item> tag) {
			app.addOptionalTag(tag);
			return this;
		}

		@Override
		public Appender add(TagEntry entry) {
			app.add(entry);
			return this;
		}

		@Override
		public Appender replace(boolean value) {
			app.replace(value);
			return this;
		}

		@Override
		public Appender remove(ResourceKey<Item> element) {
			app.remove(element);
			return this;
		}

		@Override
		public Appender remove(TagKey<Item> tag) {
			app.remove(tag);
			return this;
		}

		public Appender add(Item... items) {
			for (Item item : items) {
				add(BuiltInRegistries.ITEM.wrapAsHolder(item).key());
			}
			return this;
		}
	}
}
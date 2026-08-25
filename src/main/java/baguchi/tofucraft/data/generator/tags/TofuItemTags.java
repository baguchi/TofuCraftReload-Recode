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

		tag(TofuTags.Items.DUST_SALT).add(TofuItems.SALT.getKey());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.getKey());
		tag(TofuTags.Items.STORAGE_SALT).add(TofuBlocks.SALT_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_TOFUGEM).add(TofuBlocks.TOFU_GEM_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_ADV_TOFUGEM).add(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS_NETHER).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.asItem());
		tag(TofuTags.Items.STORAGE_SOYBEANS_SOUL).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.asItem());

		tag(TofuTags.Items.SALT).add(TofuItems.SALT.getKey());
		tag(TofuTags.Items.SOYBEAN).add(TofuItems.SEEDS_SOYBEANS.getKey());
		tag(TofuTags.Items.SOYMILK).addTag(TofuTags.Items.MILK_SOYMILK);
		tag(TofuTags.Items.MILK_SOYMILK).add(TofuItems.SOYMILK_BUCKET.getKey(), TofuItems.SOYMILK_BOTTLE.getKey());
		tag(TofuTags.Items.RICE).add(TofuItems.RICE.getKey());
		tag(ItemTags.ARROWS).add(TofuItems.ZUNDA_ARROW.getKey());
		tag(Tags.Items.SEEDS).add(TofuItems.SEEDS_CHILI.getKey(), TofuItems.SEEDS_SESAME.getKey(), TofuItems.SEEDS_SOYBEANS.getKey(), TofuItems.SEEDS_SOYBEANS_NETHER.getKey(), TofuItems.SEEDS_SOYBEANS_SOUL.getKey(), TofuItems.SEEDS_SOYBEANS_PALE.getKey(), TofuItems.SEEDS_SOYBEANS_PALE_GLOW.getKey(), TofuItems.SEEDS_RICE.getKey());

		tag(TofuTags.Items.RICE_BLOCK).add(TofuBlocks.RICE_BLOCK.asItem());
		tag(TofuTags.Items.SOYBEANS_BLOCK).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.asItem()).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.asItem()).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.asItem());
		tag(Tags.Items.STORAGE_BLOCKS).addTags(TofuTags.Items.RICE_BLOCK).addTags(TofuTags.Items.SOYBEANS_BLOCK);
		tag(ItemTags.METAL_NUGGETS).add(TofuItems.TOFU_METAL_NUGGET.getKey()).add(TofuItems.TOFU_DIAMOND_NUGGET.getKey());

		tag(ItemTags.SWORDS).add(TofuItems.ZUNDA_ALLOY_TOFU_SWORD.getKey()).add(TofuItems.TOFU_KINU_SWORD.getKey()).add(TofuItems.TOFU_MOMEN_SWORD.getKey()).add(TofuItems.TOFU_SOLID_SWORD.getKey()).add(TofuItems.TOFU_METAL_SWORD.getKey()).add(TofuItems.TOFU_DIAMOND_SWORD.getKey());
		tag(ItemTags.PICKAXES).add(TofuItems.TOFU_KINU_PICKAXE.getKey()).add(TofuItems.TOFU_MOMEN_PICKAXE.getKey()).add(TofuItems.TOFU_SOLID_PICKAXE.getKey()).add(TofuItems.TOFU_METAL_PICKAXE.getKey()).add(TofuItems.TOFU_DIAMOND_PICKAXE.getKey());
		tag(ItemTags.AXES).add(TofuItems.TOFU_KINU_AXE.getKey()).add(TofuItems.TOFU_MOMEN_AXE.getKey()).add(TofuItems.TOFU_SOLID_AXE.getKey()).add(TofuItems.TOFU_METAL_AXE.getKey()).add(TofuItems.TOFU_DIAMOND_AXE.getKey());
		tag(ItemTags.SHOVELS).add(TofuItems.TOFU_KINU_SHOVEL.getKey()).add(TofuItems.TOFU_MOMEN_SHOVEL.getKey()).add(TofuItems.TOFU_SOLID_SHOVEL.getKey()).add(TofuItems.TOFU_METAL_SHOVEL.getKey()).add(TofuItems.TOFU_DIAMOND_SHOVEL.getKey());
		tag(ItemTags.HOES).add(TofuItems.TOFU_KINU_HOE.getKey()).add(TofuItems.TOFU_MOMEN_HOE.getKey()).add(TofuItems.TOFU_SOLID_HOE.getKey()).add(TofuItems.TOFU_METAL_HOE.getKey()).add(TofuItems.TOFU_DIAMOND_HOE.getKey());
		this.tag(ItemTags.SPEARS)
				.add(TofuItems.TOFU_KINU_SPEAR.getKey()).add(TofuItems.TOFU_MOMEN_SPEAR.getKey()).add(TofuItems.TOFU_SOLID_SPEAR.getKey()).add(TofuItems.TOFU_METAL_SPEAR.getKey()).add(TofuItems.TOFU_DIAMOND_SPEAR.getKey());
		this.tag(Tags.Items.TOOLS_SHEAR).add(TofuItems.TOFU_METAL_SHEARS.getKey());

		this.tag(Tags.Items.TOOLS_SHEAR).add(TofuItems.TOFU_METAL_SHEARS.getKey());


		this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(TofuItems.ZUNDA_BOW.getKey());
		this.tag(ItemTags.BOW_ENCHANTABLE).add(TofuItems.ZUNDA_BOW.getKey());

		tag(ItemTags.HEAD_ARMOR).add(TofuItems.TOFU_DIAMOND_HELMET.getKey())
				.add(TofuItems.TOFU_METAL_HELMET.getKey())
				.add(TofuItems.TOFU_SOLID_HELMET.getKey())
				.add(TofuItems.TOFU_MOMEN_HELMET.getKey())
				.add(TofuItems.TOFU_KINU_HELMET.getKey());

		tag(ItemTags.CHEST_ARMOR).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.getKey())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.getKey())
				.add(TofuItems.TOFU_SOLID_CHESTPLATE.getKey())
				.add(TofuItems.TOFU_MOMEN_CHESTPLATE.getKey())
				.add(TofuItems.TOFU_KINU_CHESTPLATE.getKey());

		tag(ItemTags.LEG_ARMOR).add(TofuItems.TOFU_DIAMOND_LEGGINGS.getKey())
				.add(TofuItems.TOFU_METAL_LEGGINGS.getKey())
				.add(TofuItems.TOFU_SOLID_LEGGINGS.getKey())
				.add(TofuItems.TOFU_MOMEN_LEGGINGS.getKey())
				.add(TofuItems.TOFU_KINU_LEGGINGS.getKey());
		tag(ItemTags.FOOT_ARMOR).add(TofuItems.TOFU_DIAMOND_BOOTS.getKey())
				.add(TofuItems.TOFU_METAL_BOOTS.getKey())
				.add(TofuItems.TOFU_SOLID_BOOTS.getKey())
				.add(TofuItems.TOFU_MOMEN_BOOTS.getKey())
				.add(TofuItems.TOFU_KINU_BOOTS.getKey());

		tag(TofuTags.Items.NATTO).add(TofuItems.NATTO.getKey());
		tag(TofuTags.Items.MISO).add(TofuItems.MISO.getKey());
		tag(TofuTags.Items.SOYSAUCE_SOYSAUCE).add(TofuItems.BOTTLE_SOYSAUSE.getKey());
		tag(TofuTags.Items.SOYSAUCE).addTag(TofuTags.Items.SOYSAUCE_SOYSAUCE);
		tag(TofuTags.Items.TOFU).add(TofuItems.TOFU_KINU.getKey()).add(TofuItems.TOFU_MOMEN.getKey());
		tag(TofuTags.Items.TOFU_FRIED).add(TofuItems.TOFU_FRIED.getKey());
		tag(ItemTags.PIGLIN_REPELLENTS).add(TofuItems.SOUL_FUKUMAME.getKey(), TofuItems.SOUL_MANJU.getKey(), TofuItems.SEEDS_SOYBEANS_SOUL.getKey());
		tag(ItemTags.TRIM_MATERIALS).add(TofuItems.ZUNDARUBY.getKey(), TofuItems.TOFU_METAL.getKey(), TofuItems.TOFU_DIAMOND.getKey());
		tag(ItemTags.BOATS).add(TofuItems.LEEK_BOAT.getKey(), TofuItems.LEEK_GREEN_BOAT.getKey(), TofuItems.TOFU_STEM_BOAT.getKey());
		tag(ItemTags.CHEST_BOATS).add(TofuItems.LEEK_CHEST_BOAT.getKey(), TofuItems.LEEK_GREEN_CHEST_BOAT.getKey(), TofuItems.TOFU_STEM_CHEST_BOAT.getKey());
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(TofuBlocks.TOFUSLATE.asItem());

		tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(TofuItems.SEEDS_CHILI.getKey(), TofuItems.SEEDS_SOYBEANS.getKey());

		this.tag(TofuTags.Items.STATUE_HAPPY).add(TofuItems.EDAMAME_TEMPLA.getKey(), TofuItems.ZUNDA_MOCHI.getKey(), TofuItems.ZUNDA_MANJU.getKey()
				, TofuItems.KINAKO_MOCHI.getKey(), TofuItems.KINAKO_MANJU.getKey(), TofuItems.GOHEIMOCHI.getKey()
				, TofuItems.RICE_SOBORO_TOFU.getKey()
						, TofuItems.NANBANTOFU.getKey(), TofuItems.KOYADOFUSTEW.getKey(), TofuItems.MOYASHIITAME.getKey(), TofuItems.MOYASHIOHITASHI.getKey(), TofuItems.TOFUCOOKIE.getKey(), TofuItems.TOFU_ANNIN.getKey())
				.add(TofuItems.SESAME_OHAGI.getKey());

		this.tag(TofuTags.Items.TOFU_DIAMOND_ARMOR_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_HELMET.getKey(), TofuItems.TOFU_DIAMOND_CHESTPLATE.getKey(), TofuItems.TOFU_DIAMOND_LEGGINGS.getKey(), TofuItems.TOFU_DIAMOND_BOOTS.getKey());
		this.tag(TofuTags.Items.TOFU_DIAMOND_SWORD_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_SWORD.getKey());
		this.tag(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE).add(TofuItems.TOFU_DIAMOND_PICKAXE.getKey()).add(TofuItems.TOFU_DIAMOND_SHOVEL.getKey()).add(TofuItems.TOFU_DIAMOND_HOE.getKey());

		this.tag(TofuTags.Items.FUKUMAME_ENCHANTABLE).add(TofuItems.FUKUMAME.getKey()).add(TofuItems.NETHER_FUKUMAME.getKey()).add(TofuItems.SOUL_FUKUMAME.getKey());

		this.tag(ItemTags.DURABILITY_ENCHANTABLE).addTag(TofuTags.Items.FUKUMAME_ENCHANTABLE);
		this.tag(ItemTags.HEAD_ARMOR).add(TofuItems.TOFU_KINU_HELMET.getKey()).add(TofuItems.TOFU_MOMEN_HELMET.getKey())
				.add(TofuItems.TOFU_METAL_HELMET.getKey()).add(TofuItems.TOFU_DIAMOND_HELMET.getKey());
		this.tag(ItemTags.CHEST_ARMOR).add(TofuItems.TOFU_KINU_CHESTPLATE.getKey()).add(TofuItems.TOFU_MOMEN_CHESTPLATE.getKey())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.getKey()).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.getKey());
		this.tag(ItemTags.LEG_ARMOR).add(TofuItems.TOFU_KINU_LEGGINGS.getKey()).add(TofuItems.TOFU_MOMEN_LEGGINGS.getKey())
				.add(TofuItems.TOFU_METAL_LEGGINGS.getKey()).add(TofuItems.TOFU_DIAMOND_LEGGINGS.getKey());
		this.tag(ItemTags.FOOT_ARMOR).add(TofuItems.TOFU_KINU_BOOTS.getKey()).add(TofuItems.TOFU_MOMEN_BOOTS.getKey())
				.add(TofuItems.TOFU_METAL_BOOTS.getKey()).add(TofuItems.TOFU_DIAMOND_BOOTS.getKey());
		this.tag(TofuTags.Items.TOFU_TOOL_MATERIAL).add(TofuItems.TOFU_MOMEN.getKey(), TofuItems.TOFU_KINU.getKey());
		this.tag(TofuTags.Items.TOFU_SOLID_TOOL_MATERIAL).add(TofuItems.TOFU_ISHI.getKey());
		this.tag(TofuTags.Items.TOFU_METAL_TOOL_MATERIAL).add(TofuItems.TOFU_METAL.getKey());
		this.tag(TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL).add(TofuItems.TOFU_DIAMOND.getKey());

		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_HELMET.getKey()).add(TofuItems.TOFU_MOMEN_HELMET.getKey())
				.add(TofuItems.TOFU_METAL_HELMET.getKey()).add(TofuItems.TOFU_DIAMOND_HELMET.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_CHESTPLATE.getKey()).add(TofuItems.TOFU_MOMEN_CHESTPLATE.getKey())
				.add(TofuItems.TOFU_METAL_CHESTPLATE.getKey()).add(TofuItems.TOFU_DIAMOND_CHESTPLATE.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_LEGGINGS.getKey()).add(TofuItems.TOFU_MOMEN_LEGGINGS.getKey())
				.add(TofuItems.TOFU_METAL_LEGGINGS.getKey()).add(TofuItems.TOFU_DIAMOND_LEGGINGS.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_BOOTS.getKey()).add(TofuItems.TOFU_MOMEN_BOOTS.getKey())
				.add(TofuItems.TOFU_METAL_BOOTS.getKey()).add(TofuItems.TOFU_DIAMOND_BOOTS.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_SWORD.getKey()).add(TofuItems.TOFU_MOMEN_SWORD.getKey())
				.add(TofuItems.TOFU_METAL_SWORD.getKey()).add(TofuItems.TOFU_DIAMOND_SWORD.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_KINU_AXE.getKey()).add(TofuItems.TOFU_MOMEN_AXE.getKey())
				.add(TofuItems.TOFU_METAL_AXE.getKey()).add(TofuItems.TOFU_DIAMOND_AXE.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_DIAMOND_PICKAXE.getKey()).add(TofuItems.TOFU_DIAMOND_PICKAXE.getKey())
				.add(TofuItems.TOFU_DIAMOND_PICKAXE.getKey()).add(TofuItems.TOFU_DIAMOND_PICKAXE.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.TOFU_DIAMOND_SHOVEL.getKey()).add(TofuItems.TOFU_DIAMOND_SHOVEL.getKey())
				.add(TofuItems.TOFU_DIAMOND_SHOVEL.getKey()).add(TofuItems.TOFU_DIAMOND_SHOVEL.getKey());
		this.tag(TofuTags.Items.REPAIRABLE_TOFU).add(TofuItems.ZUNDA_BOW.getKey());

		this.tag(ItemTags.CHICKEN_FOOD).add(TofuItems.SEEDS_RICE.getKey(), TofuItems.SOYBEAN_PARCHED.getKey());
		this.tag(ItemTags.COW_FOOD).add(TofuItems.RICE.getKey());
		this.tag(ItemTags.PARROT_FOOD).add(TofuItems.SEEDS_RICE.getKey(), TofuItems.SOYBEAN_PARCHED.getKey());

		this.tag(ItemTags.FOX_FOOD).add(TofuItems.OAGE.getKey());
		this.tag(TofuTags.Items.TOFU_COW_FOOD).add(TofuItems.LEEK.getKey());
		this.tag(TofuTags.Items.TOFU_PIG_FOOD).add(TofuItems.LEEK.getKey(), TofuItems.NATTO.getKey());
		this.tag(TofuTags.Items.BITTERN).add(TofuItems.BITTERN_BOTTLE.getKey()).add(TofuItems.WARPED_BOTTLE.getKey()).add(TofuItems.CRIMSON_BOTTLE.getKey());
		this.tag(ItemTags.BOOKSHELF_BOOKS).add(TofuItems.TOFU_CRAFTERS_BOOK.getKey());
		this.tag(ItemTags.LECTERN_BOOKS).add(TofuItems.TOFU_CRAFTERS_BOOK.getKey());
		this.tag(Tags.Items.MUSIC_DISCS).add(TofuItems.MUSIC_DISC_GREEN_BRANCH.getKey());

		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_BOUNCY)
				.add(TofuBlocks.KINUTOFU.asItem())
				.add(TofuBlocks.MOMENTOFU.asItem())
				.add(TofuBlocks.EGGTOFU.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_FAST_FLAT)
				.add(TofuBlocks.DRIEDTOFU.asItem())
				.add(TofuBlocks.GRILLED_TOFU.asItem())
				.add(TofuBlocks.SESAMETOFU.asItem())
				.add(TofuBlocks.STRAWBERRY_TOFU.asItem())
				.add(TofuBlocks.MISOTOFU.asItem())
				.add(TofuBlocks.HELLTOFU.asItem())
				.add(TofuBlocks.SOULTOFU.asItem())
				.add(TofuBlocks.FRIED_TOFU.asItem())
				.add(TofuBlocks.FRIED_POUCH_TOFU.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY)
				.add(TofuBlocks.ZUNDATOFU.asItem())
				.add(TofuBlocks.GRILLED_TOFU.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY)
				.add(TofuBlocks.ISHITOFU.asItem())
				.add(TofuBlocks.ISHITOFU_BRICK.asItem())
				.add(TofuBlocks.ISHITOFU_CHISELED_BRICK.asItem())
				.add(TofuBlocks.ISHITOFU_SMOOTH_BRICK.asItem());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_FLAT).add(TofuBlocks.DIAMONDTOFU.asItem()).add(TofuBlocks.METALTOFU.asItem()).add(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.asItem()).add(TofuBlocks.TOFU_GEM_BLOCK.asItem());
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
				add(BuiltInRegistries.ITEM.wrapAsHolder(item).getKey());
			}
			return this;
		}
	}
}
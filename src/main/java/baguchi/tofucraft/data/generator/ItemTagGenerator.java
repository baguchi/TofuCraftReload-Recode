package baguchi.tofucraft.data.generator;

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

public class ItemTagGenerator extends ItemTagsProvider {
	public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(TofuTags.Items.DUST_SALT).add(TofuItems.SALT.getKey());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.getKey());
		tag(TofuTags.Items.SOYBEAN).add(TofuItems.SEEDS_SOYBEANS.getKey());
		tag(TofuTags.Items.SOYMILK).addTag(TofuTags.Items.MILK_SOYMILK);
		tag(TofuTags.Items.MILK_SOYMILK).add(TofuItems.SOYMILK_BUCKET.getKey(), TofuItems.SOYMILK_BOTTLE.getKey());
		tag(TofuTags.Items.RICE).add(TofuItems.RICE.getKey());
		tag(ItemTags.LOGS_THAT_BURN).add(TofuBlocks.SPROUT_STEM.asItem().builtInRegistryHolder().key(), TofuBlocks.TOFU_STEM.asItem().builtInRegistryHolder().key(), TofuBlocks.LEEK_STEM.asItem().builtInRegistryHolder().key(), TofuBlocks.LEEK_GREEN_STEM.asItem().builtInRegistryHolder().key());
		tag(ItemTags.LOGS).add(TofuBlocks.SPROUT_STEM.asItem().builtInRegistryHolder().key(), TofuBlocks.TOFU_STEM.asItem().builtInRegistryHolder().key(), TofuBlocks.LEEK_STEM.asItem().builtInRegistryHolder().key(), TofuBlocks.LEEK_GREEN_STEM.asItem().builtInRegistryHolder().key());
		tag(ItemTags.PLANKS).add(TofuBlocks.SPROUT_PLANKS.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_PLANKS.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_PLANKS.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_PLANKS.asItem().builtInRegistryHolder().key());
		tag(ItemTags.ARROWS).add(TofuItems.ZUNDA_ARROW.getKey());
		tag(Tags.Items.SEEDS).add(TofuItems.SEEDS_CHILI.getKey(), TofuItems.SEEDS_SOYBEANS.getKey(), TofuItems.SEEDS_SOYBEANS_NETHER.getKey(), TofuItems.SEEDS_SOYBEANS_SOUL.getKey(), TofuItems.SEEDS_SOYBEANS_PALE.getKey(), TofuItems.SEEDS_SOYBEANS_PALE_GLOW.getKey(), TofuItems.SEEDS_RICE.getKey());

		tag(TofuTags.Items.RICE_BLOCK).add(TofuBlocks.RICE_BLOCK.asItem().builtInRegistryHolder().key());
		tag(TofuTags.Items.SOYBEANS_BLOCK).add(TofuBlocks.SOYBEANS_SEEDS_BLOCK.asItem().builtInRegistryHolder().key()).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.asItem().builtInRegistryHolder().key()).add(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.asItem().builtInRegistryHolder().key());
		tag(Tags.Items.STORAGE_BLOCKS).addTags(TofuTags.Items.RICE_BLOCK).addTags(TofuTags.Items.SOYBEANS_BLOCK);
		tag(ItemTags.METAL_NUGGETS).add(TofuItems.TOFU_METAL_NUGGET.getKey()).add(TofuItems.TOFUDIAMOND_NUGGET.getKey());

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
		tag(TofuTags.Items.TOFU).add(TofuItems.TOFUKINU.getKey()).add(TofuItems.TOFUMOMEN.getKey());
		tag(TofuTags.Items.TOFU_FRIED).add(TofuItems.TOFUFRIED.getKey());
		tag(ItemTags.PIGLIN_REPELLENTS).add(TofuItems.SOUL_FUKUMAME.getKey(), TofuItems.SOUL_MANJU.getKey(), TofuItems.SEEDS_SOYBEANS_SOUL.getKey());
		tag(ItemTags.TRIM_MATERIALS).add(TofuItems.ZUNDARUBY.getKey(), TofuItems.TOFUMETAL.getKey(), TofuItems.TOFUDIAMOND.getKey());
		tag(ItemTags.BOATS).add(TofuItems.LEEK_BOAT.getKey(), TofuItems.LEEK_GREEN_BOAT.getKey(), TofuItems.TOFU_STEM_BOAT.getKey());
		tag(ItemTags.CHEST_BOATS).add(TofuItems.LEEK_CHEST_BOAT.getKey(), TofuItems.LEEK_GREEN_CHEST_BOAT.getKey(), TofuItems.TOFU_STEM_CHEST_BOAT.getKey());
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(TofuBlocks.TOFUSLATE.asItem().builtInRegistryHolder().key());

		tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(TofuItems.SEEDS_CHILI.getKey(), TofuItems.SEEDS_SOYBEANS.getKey());
		tag(ItemTags.WOODEN_SLABS).add(TofuBlocks.SPROUT_PLANKS_SLAB.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_PLANKS_SLAB.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.asItem().builtInRegistryHolder().key());
		tag(ItemTags.WOODEN_STAIRS).add(TofuBlocks.SPROUT_PLANKS_STAIR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_PLANKS_STAIR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.asItem().builtInRegistryHolder().key());
		tag(ItemTags.WOODEN_FENCES).add(TofuBlocks.SPROUT_FENCE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_FENCE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_FENCE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_FENCE.asItem().builtInRegistryHolder().key());
		tag(ItemTags.FENCE_GATES).add(TofuBlocks.SPROUT_FENCE_GATE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_FENCE_GATE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_FENCE_GATE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.asItem().builtInRegistryHolder().key());
		tag(ItemTags.WOODEN_DOORS).add(TofuBlocks.SPROUT_DOOR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_DOOR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_DOOR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_DOOR.asItem().builtInRegistryHolder().key());
		tag(ItemTags.WOODEN_TRAPDOORS).add(TofuBlocks.SPROUT_TRAPDOOR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_TRAPDOOR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_TRAPDOOR.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_TRAPDOOR.asItem().builtInRegistryHolder().key());
		tag(ItemTags.WOODEN_PRESSURE_PLATES).add(TofuBlocks.SPROUT_PRESSURE_PLATE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_PRESSURE_PLATE.asItem().builtInRegistryHolder().key());
		tag(ItemTags.WOODEN_BUTTONS).add(TofuBlocks.SPROUT_BUTTON.asItem().builtInRegistryHolder().key()).add(TofuBlocks.TOFU_STEM_BUTTON.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_GREEN_BUTTON.asItem().builtInRegistryHolder().key()).add(TofuBlocks.LEEK_BUTTON.asItem().builtInRegistryHolder().key());


		this.tag(ItemTags.WOODEN_SHELVES)
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.asItem().builtInRegistryHolder().key(),
						TofuBlocks.LEEK_SHELF.asItem().builtInRegistryHolder().key(),
						TofuBlocks.TOFU_STEM_SHELF.asItem().builtInRegistryHolder().key(),
						TofuBlocks.SPROUT_SHELF.asItem().builtInRegistryHolder().key()
				);
		this.tag(ItemTags.SIGNS)
				.add(
						TofuBlocks.LEEK_SIGN.asItem().builtInRegistryHolder().key(),
						TofuBlocks.LEEK_GREEN_SIGN.asItem().builtInRegistryHolder().key(),
						TofuBlocks.TOFU_STEM_SIGN.asItem().builtInRegistryHolder().key(),
						TofuBlocks.SPROUT_SIGN.asItem().builtInRegistryHolder().key()
				);
		this.tag(ItemTags.HANGING_SIGNS)
				.add(
						TofuBlocks.LEEK_HANGING_SIGN.asItem().builtInRegistryHolder().key(),
						TofuBlocks.LEEK_GREEN_HANGING_SIGN.asItem().builtInRegistryHolder().key(),
						TofuBlocks.TOFU_STEM_HANGING_SIGN.asItem().builtInRegistryHolder().key(),
						TofuBlocks.SPROUT_HANGING_SIGN.asItem().builtInRegistryHolder().key()
				);

		this.tag(TofuTags.Items.STATUE_HAPPY).add(TofuItems.EDAMAME_TEMPLA.getKey(), TofuItems.ZUNDA_MOCHI.getKey(), TofuItems.ZUNDA_MANJU.getKey()
				, TofuItems.KINAKO_MOCHI.getKey(), TofuItems.KINAKO_MANJU.getKey(), TofuItems.GOHEIMOCHI.getKey()
				, TofuItems.RICE_SOBORO_TOFU.getKey()
				, TofuItems.NANBANTOFU.getKey(), TofuItems.KOYADOFUSTEW.getKey(), TofuItems.MOYASHIITAME.getKey(), TofuItems.MOYASHIOHITASHI.getKey(), TofuItems.TOFUCOOKIE.getKey(), TofuItems.TOFUANNIN.getKey());

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
		this.tag(TofuTags.Items.TOFU_TOOL_MATERIAL).add(TofuItems.TOFUMOMEN.getKey(), TofuItems.TOFUKINU.getKey());
		this.tag(TofuTags.Items.TOFU_SOLID_TOOL_MATERIAL).add(TofuItems.TOFUISHI.getKey());
		this.tag(TofuTags.Items.TOFU_METAL_TOOL_MATERIAL).add(TofuItems.TOFUMETAL.getKey());
		this.tag(TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL).add(TofuItems.TOFUDIAMOND.getKey());

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
				.add(TofuBlocks.KINUTOFU.asItem().builtInRegistryHolder().key())
				.add(TofuBlocks.MOMENTOFU.asItem().builtInRegistryHolder().key());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY)
				.add(TofuBlocks.ISHITOFU.asItem().builtInRegistryHolder().key())
				.add(TofuBlocks.ISHITOFU_BRICK.asItem().builtInRegistryHolder().key())
				.add(TofuBlocks.ISHITOFU_CHISELED_BRICK.asItem().builtInRegistryHolder().key())
				.add(TofuBlocks.ISHITOFU_SMOOTH_BRICK.asItem().builtInRegistryHolder().key());
		this.tag(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_FLAT).add(TofuBlocks.DIAMONDTOFU.asItem().builtInRegistryHolder().key()).add(TofuBlocks.METALTOFU.asItem().builtInRegistryHolder().key());
	}
}
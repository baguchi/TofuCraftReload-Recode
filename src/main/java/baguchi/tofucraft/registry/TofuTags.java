package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TofuTags {
	public static class Biomes {
		public static final TagKey<Biome> TOFU_CASTLE = tag("has_structure/tofu_castle");
		public static final TagKey<Biome> TOFU_RUINS = tag("has_structure/tofu_ruins");
		public static final TagKey<Biome> TOFU_MINESHAFT = tag("has_structure/tofu_mineshaft");
		public static final TagKey<Biome> TOFU_VILLAGE = tag("has_structure/tofu_village");
		public static final TagKey<Biome> ZUNDA_TOFU_VILLAGE = tag("has_structure/zunda_tofu_village");
		public static final TagKey<Biome> HOT_VARIANT_TOFUNIAN = tag("hot_variant_tofunian");

		private static TagKey<Biome> tag(String name) {
			return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}

	public static class TofunianTrade {
		public static final TagKey<VillagerTrade> FARMER_LEVEL_1 = tag("farmer/level_1");
		public static final TagKey<VillagerTrade> FARMER_LEVEL_2 = tag("farmer/level_2");
		public static final TagKey<VillagerTrade> FARMER_LEVEL_3 = tag("farmer/level_3");
		public static final TagKey<VillagerTrade> FARMER_LEVEL_4 = tag("farmer/level_4");
		public static final TagKey<VillagerTrade> FARMER_LEVEL_5 = tag("farmer/level_5");
		public static final TagKey<VillagerTrade> SOY_WORKER_LEVEL_1 = tag("soy_worker/level_1");
		public static final TagKey<VillagerTrade> SOY_WORKER_LEVEL_2 = tag("soy_worker/level_2");
		public static final TagKey<VillagerTrade> SOY_WORKER_LEVEL_3 = tag("soy_worker/level_3");
		public static final TagKey<VillagerTrade> SOY_WORKER_LEVEL_4 = tag("soy_worker/level_4");
		public static final TagKey<VillagerTrade> SOY_WORKER_LEVEL_5 = tag("soy_worker/level_5");
		public static final TagKey<VillagerTrade> SMITH_LEVEL_1 = tag("smith/level_1");
		public static final TagKey<VillagerTrade> SMITH_LEVEL_2 = tag("smith/level_2");
		public static final TagKey<VillagerTrade> SMITH_LEVEL_3 = tag("smith/level_3");
		public static final TagKey<VillagerTrade> SMITH_LEVEL_4 = tag("smith/level_4");
		public static final TagKey<VillagerTrade> SMITH_LEVEL_5 = tag("smith/level_5");

		public static final TagKey<VillagerTrade> ENGINEER_LEVEL_1 = tag("engineer/level_1");
		public static final TagKey<VillagerTrade> ENGINEER_LEVEL_2 = tag("engineer/level_2");
		public static final TagKey<VillagerTrade> ENGINEER_LEVEL_3 = tag("engineer/level_3");
		public static final TagKey<VillagerTrade> ENGINEER_LEVEL_4 = tag("engineer/level_4");
		public static final TagKey<VillagerTrade> ENGINEER_LEVEL_5 = tag("engineer/level_5");

		public static final TagKey<VillagerTrade> TOFU_CRAFTSMAN_LEVEL_1 = tag("tofu_craftsman/level_1");
		public static final TagKey<VillagerTrade> TOFU_CRAFTSMAN_LEVEL_2 = tag("tofu_craftsman/level_2");
		public static final TagKey<VillagerTrade> TOFU_CRAFTSMAN_LEVEL_3 = tag("tofu_craftsman/level_3");
		public static final TagKey<VillagerTrade> TOFU_CRAFTSMAN_LEVEL_4 = tag("tofu_craftsman/level_4");
		public static final TagKey<VillagerTrade> TOFU_CRAFTSMAN_LEVEL_5 = tag("tofu_craftsman/level_5");



		public static final TagKey<VillagerTrade> TOFUNIAN_TRAVELER_BUYING = tag("tofunian_traveler/buying");
		public static final TagKey<VillagerTrade> TOFUNIAN_TRAVELER_UNCOMMON = tag("tofunian_traveler/uncommon");
		public static final TagKey<VillagerTrade> TOFUNIAN_TRAVELER_COMMON = tag("tofunian_traveler/common");

		private static TagKey<VillagerTrade> tag(String name) {
			return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}

	public static class Blocks {
		public static final TagKey<Block> TOFUNIAN_SOY_WORKER = tag("tofunian_soy_worker");
		public static final TagKey<Block> TOFUNIAN_FARMER = tag("tofunian_farmer");
		public static final TagKey<Block> TOFUNIAN_SMITH = tag("tofunian_smith");
		public static final TagKey<Block> TOFUNIAN_ENGINEER = tag("tofunian_engineer");

		public static final TagKey<Block> HAS_INFO = tag("has_info");

		public static final TagKey<Block> PICKABLE_TOFU = tag("pickable_tofu");

		public static final TagKey<Block> TOFU_TERRAIN = tag("tofu_terrain");
		public static final TagKey<Block> TF_TRANSMITTER = tag("tf_transmitter");

		public static final TagKey<Block> TOFU_WORLD_CARVER_REPLACEABLE = tag("tofu_world_replaceable");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}

	public static class EntityTypes {
		public static final TagKey<EntityType<?>> EXTRA_DAMAGE_ZUNDA = tag("extra_damage_zunda");
		public static final TagKey<EntityType<?>> FUKUMAME = tag("fukumame");
		public static final TagKey<EntityType<?>> WALKABLE_WITHOUT_TRIGGER = tag("walkable_without_trigger");

		private static TagKey<EntityType<?>> tag(String name) {
			return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> SOYBEAN = universalTag("crops/soybean");
		public static final TagKey<Item> SALT = universalTag("salt");
		public static final TagKey<Item> DUST_SALT = universalTag("dusts/salt");
		public static final TagKey<Item> RICE = universalTag("crops/rice");
		public static final TagKey<Item> EGGS = universalTag("eggs");
		public static final TagKey<Item> SOYMILK = universalTag("soy_milk");
		public static final TagKey<Item> MILK_SOYMILK = universalTag("milk/soy_milk");

		public static final TagKey<Item> RICE_BLOCK = universalTag("block/rice_block");
		public static final TagKey<Item> TOFU = universalTag("tofu");
		public static final TagKey<Item> TOFU_FRIED = universalTag("tofu/fried");

		public static final TagKey<Item> MISO = universalTag("miso");
		public static final TagKey<Item> NATTO = universalTag("natto");

		public static final TagKey<Item> SOYSAUCE = universalTag("soysauce");
		public static final TagKey<Item> SOYSAUCE_SOYSAUCE = universalTag("soysauce/soysauce");
		public static final TagKey<Item> STATUE_HAPPY = tag("statue_happy");
		public static final TagKey<Item> TOFU_DIAMOND_ARMOR_ENCHANTABLE = tag("tofu_diamond_armor_enchantable");
		public static final TagKey<Item> TOFU_DIAMOND_SWORD_ENCHANTABLE = tag("tofu_diamond_sword_enchantable");
		public static final TagKey<Item> TOFU_DIAMOND_MINEABLE_ENCHANTABLE = tag("tofu_diamond_mineable_enchantable");
		public static final TagKey<Item> FUKUMAME_ENCHANTABLE = tag("fukumame_enchantable");

		public static final TagKey<Item> TOFU_TOOL_MATERIAL = tag("tofu_tool_material");

		public static final TagKey<Item> TOFU_SOLID_TOOL_MATERIAL = tag("tofu_solid_tool_material");

		public static final TagKey<Item> TOFU_METAL_TOOL_MATERIAL = tag("tofu_metal_tool_material");
		public static final TagKey<Item> TOFU_DIAMOND_TOOL_MATERIAL = tag("tofu_diamond_tool_material");
		public static final TagKey<Item> REPAIRABLE_TOFU = tag("repairable_tofu");


		public static final TagKey<Item> TOFU_COW_FOOD = tag("tofu_cow_food");
		public static final TagKey<Item> TOFU_PIG_FOOD = tag("tofu_pig_food");
		public static final TagKey<Item> BITTERN = tag("bittern");


		private static TagKey<Item> tag(String name) {
			return ItemTags.create(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}

		private static TagKey<Item> universalTag(String name) {
			return ItemTags.create(Identifier.fromNamespaceAndPath("c", name));
		}
	}

	public static class Fluids {
		public static final TagKey<Fluid> SOYMILK = tag("soymilk");

		private static TagKey<Fluid> tag(String name) {
			return FluidTags.create(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}

	public static class PoiTypes {
		public static final TagKey<PoiType> TOFU_VILLAGE = tag("tofu_village");

		private static TagKey<PoiType> tag(String name) {
			return TagKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}

	public static class SoundEvents {
		public static final TagKey<SoundEvent> BOSS_MUSIC = tag("boss_music");

		private static TagKey<SoundEvent> tag(String name) {
			return TagKey.create(Registries.SOUND_EVENT, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
		}
	}
}

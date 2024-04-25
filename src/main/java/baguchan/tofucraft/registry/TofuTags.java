package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TofuTags {
	public static class Blocks {
		public static final TagKey<Block> TOFU_METAL = tag("tofu_metal");
		public static final TagKey<Block> SOFT_TOFU = tag("soft_tofu");

		public static final TagKey<Block> TOFU_TERRAIN = tag("tofu_terrain");
		public static final TagKey<Block> TF_TRANSMITTER = tag("tf_transmitter");

		public static final TagKey<Block> TOFU_WORLD_CARVER_REPLACEABLE = tag("tofu_world_replaceable");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(new ResourceLocation(TofuCraftReload.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> SOYBEAN = universalTag("crops/soybean");
		public static final TagKey<Item> SALT = universalTag("salt");
		public static final TagKey<Item> DUST_SALT = universalTag("dust/salt");
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

		private static TagKey<Item> tag(String name) {
			return ItemTags.create(new ResourceLocation(TofuCraftReload.MODID, name));
		}

		private static TagKey<Item> universalTag(String name) {
			return ItemTags.create(new ResourceLocation("c", name));
		}
	}

	public static class Fluids {
		public static final TagKey<Fluid> SOYMILK = tag("soymilk");

		private static TagKey<Fluid> tag(String name) {
			return FluidTags.create(new ResourceLocation(TofuCraftReload.MODID, name));
		}
	}

	public static class PoiTypes {
		public static final TagKey<PoiType> TOFUNIAN_JOB = tag("tofunian_job");

		private static TagKey<PoiType> tag(String name) {
			return TagKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(TofuCraftReload.MODID, name));
		}
	}
}

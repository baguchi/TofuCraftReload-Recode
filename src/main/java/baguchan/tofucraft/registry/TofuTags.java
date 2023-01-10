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
		public static final TagKey<Block> SOFT_TOFU = tag("soft_tofu");

		public static final TagKey<Block> TOFU_TERRAIN = tag("tofu_terrain");

		public static final TagKey<Block> TOFU_WORLD_CARVER_REPLACEABLE = tag("tofu_world_replaceable");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(new ResourceLocation(TofuCraftReload.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> SOYBEAN = forgeTag("crops/soybean");
		public static final TagKey<Item> SALT = forgeTag("salt");
		public static final TagKey<Item> DUST_SALT = forgeTag("dust/salt");
		public static final TagKey<Item> RICE = forgeTag("crops/rice");
		public static final TagKey<Item> EGGS = forgeTag("eggs");
		public static final TagKey<Item> SOYMILK = forgeTag("soy_milk");
		public static final TagKey<Item> MILK_SOYMILK = forgeTag("milk/soy_milk");

		public static final TagKey<Item> RICE_BLOCK = forgeTag("block/rice_block");

		private static TagKey<Item> tag(String name) {
			return ItemTags.create(new ResourceLocation(TofuCraftReload.MODID, name));
		}

		private static TagKey<Item> forgeTag(String name) {
			return ItemTags.create(new ResourceLocation("forge", name));
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

package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TofuTags {
	public static class Blocks {
		public static final TagKey<Block> SOFT_TOFU = tag("soft_tofu");

		public static final TagKey<Block> TOFU_TERRAIN = tag("tofu_terrain");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(new ResourceLocation(TofuCraftReload.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> SOYBEAN = forgeTag("crops/soybean");
		public static final TagKey<Item> SALT = forgeTag("salt");
		public static final TagKey<Item> RICE = forgeTag("crops/rice");

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
}

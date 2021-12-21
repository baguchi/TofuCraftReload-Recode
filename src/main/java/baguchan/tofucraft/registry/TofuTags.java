package baguchan.tofucraft.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TofuTags {
	public static class Blocks {
		public static final Tag.Named<Block> SOFT_TOFU = tag("soft_tofu");

		public static final Tag.Named<Block> TOFU_TERRAIN = tag("tofu_terrain");

		private static Tag.Named<Block> tag(String name) {
			return BlockTags.bind("tofucraft:" + name);
		}
	}

	public static class Items {
		public static final Tag.Named<Item> SOYBEAN = forgeTag("crops/soybean");
		public static final Tag.Named<Item> SALT = forgeTag("salt");
		public static final Tag.Named<Item> RICE = forgeTag("crops/rice");

		private static Tag.Named<Item> tag(String name) {
			return ItemTags.bind("tofucraft:" + name);
		}

		private static Tag.Named<Item> forgeTag(String name) {
			return ItemTags.bind("forge:" + name);
		}
	}

	public static class Fluids {
		public static final Tag.Named<Fluid> SOYMILK = tag("soymilk");

		private static Tag.Named<Fluid> tag(String name) {
			return FluidTags.bind("tofucraft:" + name);
		}
	}
}

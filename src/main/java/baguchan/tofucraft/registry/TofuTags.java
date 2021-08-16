package baguchan.tofucraft.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
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

	public static class Fluids {
		public static final Tag.Named<Fluid> SOYMILK = tag("soymilk");

		private static Tag.Named<Fluid> tag(String name) {
			return FluidTags.bind("tofucraft:" + name);
		}
	}
}

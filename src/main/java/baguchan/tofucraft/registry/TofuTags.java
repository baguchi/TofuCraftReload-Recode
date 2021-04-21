package baguchan.tofucraft.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;

public class TofuTags {
	public static class Blocks {
		public static final ITag.INamedTag<Block> SOFT_TOFU = tag("soft_tofu");

		public static final ITag.INamedTag<Block> TOFU_TERRAIN = tag("tofu_terrain");

		private static ITag.INamedTag<Block> tag(String name) {
			return BlockTags.func_199894_a("tofucraft:" + name);
		}
	}

	public static class Fluids {
		public static final ITag.INamedTag<Fluid> SOYMILK = tag("soymilk");

		private static ITag.INamedTag<Fluid> tag(String name) {
			return FluidTags.func_206956_a("tofucraft:" + name);
		}
	}
}

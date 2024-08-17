package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class TofuBannerPatterns {
	public static final ResourceKey<BannerPattern> TOFUNIAN = register("tofunian");

	private static ResourceKey<BannerPattern> register(String name) {
		return ResourceKey.create(Registries.BANNER_PATTERN, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<BannerPattern> context) {
		context.register(TOFUNIAN, new BannerPattern(TOFUNIAN.location(), "block.minecraft.banner.tofucraft.tofunian"));
	}
}
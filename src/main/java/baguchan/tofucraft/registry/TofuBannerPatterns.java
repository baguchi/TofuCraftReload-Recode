package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, TofuCraftReload.MODID);

	public static final ResourceKey<BannerPattern> TOFUNIAN_KEY = create("tofunian");

	public static final DeferredHolder<BannerPattern, BannerPattern> TOFUNIAN = BANNER_PATTERNS.register("tofunian", () -> new BannerPattern(new ResourceLocation(TofuCraftReload.MODID, "tofunian"), "tofunian"));

	private static ResourceKey<BannerPattern> create(String p_222757_) {
		return ResourceKey.create(Registries.BANNER_PATTERN, new ResourceLocation(TofuCraftReload.MODID, p_222757_));
	}
}
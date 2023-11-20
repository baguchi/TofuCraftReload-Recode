package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, TofuCraftReload.MODID);

	public static final Supplier<BannerPattern> TOFUNIAN = BANNER_PATTERNS.register("tofunian", () -> new BannerPattern("tofucraft:tfn"));
}
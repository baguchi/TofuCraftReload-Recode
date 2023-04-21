package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TofuBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, TofuCraftReload.MODID);

	public static final RegistryObject<BannerPattern> TOFUNIAN = BANNER_PATTERNS.register("tofunian", () -> new BannerPattern("tofucraft:tfn"));
}
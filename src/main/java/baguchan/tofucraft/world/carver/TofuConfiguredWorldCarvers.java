package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public class TofuConfiguredWorldCarvers {
	public static final ResourceKey<ConfiguredWorldCarver<?>> CAVE = createKey("tofu_cave");
	public static final ResourceKey<ConfiguredWorldCarver<?>> CAVE_EXTRA_UNDERGROUND = createKey("tofu_cave_extra_underground");
	public static final ResourceKey<ConfiguredWorldCarver<?>> CANYON = createKey("tofu_canyon");


	private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String p_256085_) {
		return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(TofuCraftReload.MODID, p_256085_));
	}
}

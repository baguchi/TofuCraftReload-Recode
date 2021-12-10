package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class TofuBiomes {
	public static final ResourceKey<Biome> TOFU_PLAINS = register("tofu_plains");
	public static final ResourceKey<Biome> TOFU_WASTES = register("tofu_wastes");

	private static ResourceKey<Biome> register(String p_48229_) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TofuCraftReload.MODID, p_48229_));
	}
}

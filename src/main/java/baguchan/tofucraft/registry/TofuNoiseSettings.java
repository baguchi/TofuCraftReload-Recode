package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class TofuNoiseSettings {
	public static final ResourceKey<NoiseGeneratorSettings> TOFU_WORLD = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(TofuCraftReload.MODID, "tofu_world_noise"));

}

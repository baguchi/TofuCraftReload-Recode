package baguchan.tofucraft.registry;

import baguchan.tofucraft.world.dimension.TofuBiomeProvider;
import baguchan.tofucraft.world.dimension.TofuChunkGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class TofuDimensions {
	public static final RegistryKey<World> tofu_world = RegistryKey.create(Registry.DIMENSION_REGISTRY, name("tofu_world"));


	private static ResourceLocation name(String name) {
		return new ResourceLocation("tofucraft", name);
	}

	public static void registerDimensionStuff() {
		Registry.register(Registry.CHUNK_GENERATOR, name("chunk_generator"), TofuChunkGenerator.CODEC);
		Registry.register(Registry.BIOME_SOURCE, name("biome_provider"), TofuBiomeProvider.CODEC);
	}
}

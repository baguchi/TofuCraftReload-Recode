package baguchan.tofucraft.registry;

import baguchan.tofucraft.world.dimension.TofuBiomeProvider;
import baguchan.tofucraft.world.dimension.TofuChunkGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class TofuDimensions {
	private static ResourceLocation name(String name) {
		return new ResourceLocation("tofucraft", name);
	}

	public static void registerDimensionStuff() {
		Registry.func_218322_a(Registry.field_239690_aB_, name("chunk_generator"), TofuChunkGenerator.CODEC);
		Registry.func_218322_a(Registry.field_239689_aA_, name("biome_provider"), TofuBiomeProvider.CODEC);
	}
}

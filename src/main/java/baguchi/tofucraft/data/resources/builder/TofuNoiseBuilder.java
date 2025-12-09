package baguchi.tofucraft.data.resources.builder;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuNoiseSettings;
import baguchi.tofucraft.world.gen.TofuSurfaceRuleData;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;

import java.util.List;

public class TofuNoiseBuilder {

	public static NoiseGeneratorSettings tofuWorld(BootstrapContext<NoiseGeneratorSettings> p_256478_) {
		return new NoiseGeneratorSettings(new NoiseSettings(-64, 384, 1, 2), TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), TofuBlocks.SOYMILK.get().defaultBlockState(), NoiseRouterData.overworld(p_256478_.lookup(Registries.DENSITY_FUNCTION), p_256478_.lookup(Registries.NOISE), false, false), TofuSurfaceRuleData.tofuWorld(), List.of(), 63, false, true, false, false);
	}

	public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> p_256365_) {
		p_256365_.register(TofuNoiseSettings.TOFU_WORLD, TofuNoiseBuilder.tofuWorld(p_256365_));
	}

	private static ResourceKey<DensityFunction> createKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.parse(p_209537_));
	}

	private static ResourceKey<DensityFunction> createModKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, p_209537_));
	}


	private static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_, ResourceKey<DensityFunction> p_256077_) {
		return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
	}

}

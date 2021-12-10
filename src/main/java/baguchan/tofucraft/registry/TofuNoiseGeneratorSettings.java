package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.TofuSurfaceRuleData;
import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.Map;
import java.util.Optional;

public class TofuNoiseGeneratorSettings {

	public static final ResourceKey<NoiseGeneratorSettings> TOFU_WORLD = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation(TofuCraftReload.MODID, "tofu_world"));

	public static NoiseGeneratorSettings tofuWorld() {
		Map<StructureFeature<?>, StructureFeatureConfiguration> map = Maps.newHashMap();
		map.put(TofuStructures.TOFU_VILLAGE, new StructureFeatureConfiguration(34, 8, 10387312));
		return new NoiseGeneratorSettings(new StructureSettings(Optional.empty(), map), NoiseSettings.create(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 8), new NoiseSlider(0.1171875D, 3, 0), 1, 2, false, false, false, TerrainProvider.overworld(false)), TofuBlocks.TOFU_TERRAIN.defaultBlockState(), TofuBlocks.SOYMILK.defaultBlockState(), TofuSurfaceRuleData.tofuWorld(), 63, false, true, true, false, true, false);
	}

	public static void register(ResourceKey<NoiseGeneratorSettings> p_198263_, NoiseGeneratorSettings p_198264_) {
		BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, p_198263_.location(), p_198264_);
	}

	public static void init() {
		register(TOFU_WORLD, tofuWorld());
	}
}

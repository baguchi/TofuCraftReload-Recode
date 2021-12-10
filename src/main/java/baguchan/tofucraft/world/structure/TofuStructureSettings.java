package baguchan.tofucraft.world.structure;

import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuStructures;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StrongholdConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class TofuStructureSettings extends StructureSettings {
	private final ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> configuredStructures;


	public TofuStructureSettings(Optional<StrongholdConfiguration> empty, Map<StructureFeature<?>, StructureFeatureConfiguration> map) {
		super(empty, map);
		HashMap<StructureFeature<?>, ImmutableMultimap.Builder<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> hashmap = new HashMap<>();
		registerStructures((p_189367_, p_189368_) -> {
			hashmap.computeIfAbsent(p_189367_.feature, (p_189374_) -> {
				return ImmutableMultimap.builder();
			}).put(p_189367_, p_189368_);
		});
		this.configuredStructures = hashmap.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, (p_189370_) -> {
			return p_189370_.getValue().build();
		}));
	}

	public static void registerStructures(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> p_194758_) {
		register(p_194758_, TofuStructures.TOFU_VILLAGE_FEATURES, TofuBiomes.TOFU_PLAINS);
		register(p_194758_, TofuStructures.TOFU_VILLAGE_FEATURES, TofuBiomes.TOFU_WASTES);
	}

	private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> p_194764_, ConfiguredStructureFeature<?, ?> p_194765_, ResourceKey<Biome> p_194766_) {
		p_194764_.accept(p_194765_, p_194766_);
	}

	public ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> structures(StructureFeature<?> p_189372_) {
		return this.configuredStructures.getOrDefault(p_189372_, ImmutableMultimap.of());
	}
}

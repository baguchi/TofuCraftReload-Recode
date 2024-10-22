package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.biome.TofuBiomeBuilder;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;

import java.util.function.Function;

public class TofuBiomeSources {
	public static final ResourceKey<MultiNoiseBiomeSourceParameterList> TOFU_WORLD = registerPreset("tofu_world");

	public static final MultiNoiseBiomeSourceParameterList.Preset TOFU_WORLD_PRESET = new MultiNoiseBiomeSourceParameterList.Preset(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_world"), new MultiNoiseBiomeSourceParameterList.Preset.SourceProvider() {
		public <T> Climate.ParameterList<T> apply(Function<ResourceKey<Biome>, T> p_275530_) {
			return generateTofuBiomes(p_275530_);
		}
	});

	static <T> Climate.ParameterList<T> generateTofuBiomes(Function<ResourceKey<Biome>, T> p_275249_) {
		ImmutableList.Builder<Pair<Climate.ParameterPoint, T>> builder = ImmutableList.builder();
		(new TofuBiomeBuilder()).addBiomes((p_275579_) -> {
			builder.add(p_275579_.mapSecond(p_275249_));
		});
		return new Climate.ParameterList<>(builder.build());
	}

	private static ResourceKey<MultiNoiseBiomeSourceParameterList> registerPreset(String p_275281_) {
		return ResourceKey.create(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_275281_));
	}

	public static void bootstrapPreset(BootstrapContext<MultiNoiseBiomeSourceParameterList> p_275387_) {
		HolderGetter<Biome> holdergetter = p_275387_.lookup(Registries.BIOME);
		p_275387_.register(TOFU_WORLD, new MultiNoiseBiomeSourceParameterList(TOFU_WORLD_PRESET, holdergetter));
	}
}

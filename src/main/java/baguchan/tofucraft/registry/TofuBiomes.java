package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.biome.TofuBiomeBuilder;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TofuBiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TofuCraftReload.MODID);
	public static final ResourceKey<Biome> TOFU_PLAINS = register("tofu_plains");
	public static final ResourceKey<Biome> TOFU_FOREST = register("tofu_forest");
	public static final ResourceKey<Biome> TOFU_WASTES = register("tofu_waste");
	public static final ResourceKey<Biome> TOFU_MOUNTAIN = register("tofu_mountain");

	public static final ResourceKey<Biome> ZUNDA_FOREST = register("zunda_forest");
	public static final ResourceKey<Biome> SOYBEAN_FOREST = register("soybean_forest");
	public static final ResourceKey<Biome> SOYBEAN_FOREST_SPARSE = register("soybean_forest_sparse");

	public static final ResourceKey<Biome> TOFU_OCEAN = register("tofu_ocean");
	public static final ResourceKey<Biome> TOFU_RIVER = register("tofu_river");
	public static final ResourceKey<Biome> TOFU_BEACH = register("tofu_beach");


	public static final MultiNoiseBiomeSource.Preset TOFU_WORLD = new MultiNoiseBiomeSource.Preset(new ResourceLocation(TofuCraftReload.MODID, "tofu_world"), (p_204281_) -> {
		ImmutableList.Builder<Pair<Climate.ParameterPoint, Holder<Biome>>> builder = ImmutableList.builder();
		(new TofuBiomeBuilder()).addBiomes((p_204279_) -> {
			builder.add(p_204279_.mapSecond(p_204281_::getOrCreateHolderOrThrow));
		});
		return new Climate.ParameterList<>(builder.build());
	});
	public static void init() {

	}

	private static ResourceKey<Biome> register(String p_48229_) {
		BIOMES.register(p_48229_, OverworldBiomes::theVoid);
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TofuCraftReload.MODID, p_48229_));
	}
}

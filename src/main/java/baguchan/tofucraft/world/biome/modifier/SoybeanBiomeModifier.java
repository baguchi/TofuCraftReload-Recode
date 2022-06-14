package baguchan.tofucraft.world.biome.modifier;

import baguchan.tofucraft.registry.TofuBiomeModifiers;
import baguchan.tofucraft.world.placement.ModNetherPlacements;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class SoybeanBiomeModifier implements BiomeModifier {
	public static final SoybeanBiomeModifier INSTANCE = new SoybeanBiomeModifier();

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD) {
			if (biome.is(BiomeTags.IS_NETHER)) {
				builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModNetherPlacements.PATCH_NETHER_SOYBEAN_NORMAL);
			}
			if (biome.is(Biomes.CRIMSON_FOREST)) {
				builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModNetherPlacements.PATCH_NETHER_SOYBEAN_CRIMSON);
			}
			if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
				builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModNetherPlacements.PATCH_SOUL_SOYBEAN);
			}
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return TofuBiomeModifiers.SOYBEAN_MODIFIER_TYPE.get();
	}
}

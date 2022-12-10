package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.biome.TofuBiomeBuilder;
import baguchan.tofucraft.world.gen.TofuNoiseRouterData;
import baguchan.tofucraft.world.gen.TofuSurfaceRuleData;
import com.mojang.serialization.DataResult;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

import java.util.OptionalLong;

public class TofuDimensionSettings {
	public static final ResourceKey<DimensionType> TOFU_WORLD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(TofuCraftReload.MODID, "tofu_world_type"));

	public static final ResourceKey<NoiseGeneratorSettings> TOFU_WORLD = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(TofuCraftReload.MODID, "tofu_world"));


	static final NoiseSettings TOFU_NOISE_SETTINGS = create(-64, 384, 1, 2);

	public static NoiseGeneratorSettings tofuWorld(BootstapContext<NoiseGeneratorSettings> p_256478_) {
		return new NoiseGeneratorSettings(TOFU_NOISE_SETTINGS, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), TofuBlocks.SOYBEAN.get().defaultBlockState(), TofuNoiseRouterData.tofuworld(p_256478_.lookup(Registries.DENSITY_FUNCTION), p_256478_.lookup(Registries.NOISE), false, false), TofuSurfaceRuleData.tofuWorld(), (new TofuBiomeBuilder()).spawnTarget(), 63, false, true, true, false);
	}

	private static DimensionType tofuDimType() {
		return new DimensionType(
				OptionalLong.empty(),
				true, //skylight
				false, //ceiling
				false, //ultrawarm
				true, //natural
				1.0D, //coordinate scale
				true, //bed works
				false, //respawn anchor works
				-64,
				384,
				384, // Logical Height
				BlockTags.INFINIBURN_OVERWORLD, //infiburn
				BuiltinDimensionTypes.OVERWORLD_EFFECTS, // DimensionRenderInfo
				0f, // Wish this could be set to -0.05 since it'll make the world truly blacked out if an area is not sky-lit (see: Dark Forests) Sadly this also messes up night vision so it gets 0
				new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 7)
		);
	}

	private static DataResult<NoiseSettings> guardY(NoiseSettings p_158721_) {
		if (p_158721_.minY() + p_158721_.height() > DimensionType.MAX_Y + 1) {
			return DataResult.error("min_y + height cannot be higher than: " + (DimensionType.MAX_Y + 1));
		} else if (p_158721_.height() % 16 != 0) {
			return DataResult.error("height has to be a multiple of 16");
		} else {
			return p_158721_.minY() % 16 != 0 ? DataResult.error("min_y has to be a multiple of 16") : DataResult.success(p_158721_);
		}
	}

	public static NoiseSettings create(int p_224526_, int p_224527_, int p_224528_, int p_224529_) {
		NoiseSettings noisesettings = new NoiseSettings(p_224526_, p_224527_, p_224528_, p_224529_);
		guardY(noisesettings).error().ifPresent((p_158719_) -> {
			throw new IllegalStateException(p_158719_.message());
		});
		return noisesettings;
	}

	public static void bootstrapNoiseGen(BootstapContext<NoiseGeneratorSettings> p_256365_) {
		p_256365_.register(TofuDimensionSettings.TOFU_WORLD, TofuDimensionSettings.tofuWorld(p_256365_));
	}

	public static void bootstrapDimensionType(BootstapContext<DimensionType> p_256376_) {
		p_256376_.register(TOFU_WORLD_TYPE, TofuDimensionSettings.tofuDimType());
	}
}

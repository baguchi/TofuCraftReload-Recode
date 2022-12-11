package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.TofuNoiseRouterData;
import baguchan.tofucraft.world.gen.TofuSurfaceRuleData;
import com.mojang.serialization.DataResult;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

import java.util.List;

public class TofuNoiseSettings {
	public static final ResourceKey<NoiseGeneratorSettings> TOFU_WORLD = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(TofuCraftReload.MODID, "tofu_world_noise"));


	static final NoiseSettings TOFU_NOISE_SETTINGS = create(-64, 384, 1, 2);

	public static NoiseGeneratorSettings tofuWorld(BootstapContext<NoiseGeneratorSettings> p_256478_) {
		return new NoiseGeneratorSettings(TOFU_NOISE_SETTINGS, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), TofuBlocks.SOYBEAN.get().defaultBlockState(), TofuNoiseRouterData.overworld(p_256478_.lookup(Registries.DENSITY_FUNCTION), p_256478_.lookup(Registries.NOISE), false, false), TofuSurfaceRuleData.tofuWorld(), List.of(), 63, false, true, false, false);
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

	public static void bootstrap(BootstapContext<NoiseGeneratorSettings> p_256365_) {
		p_256365_.register(TofuNoiseSettings.TOFU_WORLD, TofuNoiseSettings.tofuWorld(p_256365_));
	}
}

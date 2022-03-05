package baguchan.tofucraft.world.carver;

import baguchan.tofucraft.registry.TofuCarvers;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class TofuConfiguredWorldCarvers {
	public static final Holder<ConfiguredWorldCarver<CaveCarverConfiguration>> TOFU_CAVE = register("tofucraft:tofu_cave", TofuCarvers.TOFU_CAVE_CARVER.get().configured(new CaveCarverConfiguration(0.15F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
	public static final Holder<ConfiguredWorldCarver<CaveCarverConfiguration>> TOFU_CAVE_EXTRA_UNDERGROUND = register("tofucraft:tofu_cave_extra_underground", TofuCarvers.TOFU_CAVE_CARVER.get().configured(new CaveCarverConfiguration(0.07F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(47)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), CarverDebugSettings.of(false, Blocks.OAK_BUTTON.defaultBlockState()), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
	public static final Holder<ConfiguredWorldCarver<CanyonCarverConfiguration>> TOFU_CANYON = register("tofucraft:tofu_canyon", TofuCarvers.TOFU_CANYON_CARVER.get().configured(new CanyonCarverConfiguration(0.01F, UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(67)), ConstantFloat.of(3.0F), VerticalAnchor.aboveBottom(8), CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(0.0F, 6.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 1.0F, 0.0F))));


	private static <WC extends CarverConfiguration> Holder<ConfiguredWorldCarver<WC>> register(String p_206431_, ConfiguredWorldCarver<WC> p_206432_) {
		return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_CARVER, p_206431_, p_206432_);
	}

	public static void init() {

	}
}

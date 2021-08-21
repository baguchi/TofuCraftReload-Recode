package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.carver.TofuCanyonCarver;
import baguchan.tofucraft.world.carver.TofuCaveCarver;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuCarvers {
	public static final WorldCarver<CaveCarverConfiguration> TOFU_CAVE_CARVER = new TofuCaveCarver(CaveCarverConfiguration.CODEC);
	public static final WorldCarver<CanyonCarverConfiguration> TOFU_CANYON_CARVER = new TofuCanyonCarver(CanyonCarverConfiguration.CODEC);

	public static final ConfiguredWorldCarver<CaveCarverConfiguration> TOFU_CAVE = register("tofucraft:tofu_cave", TOFU_CAVE_CARVER.configured(new CaveCarverConfiguration(0.33333334F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(126)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(8), false, CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
	public static final ConfiguredWorldCarver<CanyonCarverConfiguration> TOFU_CANYON = register("tofucraft:tofu_canyon", TOFU_CANYON_CARVER.configured(new CanyonCarverConfiguration(0.02F, UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(67)), ConstantFloat.of(3.0F), VerticalAnchor.aboveBottom(8), false, CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(0.0F, 6.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 1.0F, 0.0F))));
	public static final ConfiguredWorldCarver<CanyonCarverConfiguration> TOFU_CREVICE = register("tofucraft:tofu_crevice", TOFU_CANYON_CARVER.configured(new CanyonCarverConfiguration(0.00125F, UniformHeight.of(VerticalAnchor.absolute(40), VerticalAnchor.absolute(80)), UniformFloat.of(6.0F, 8.0F), VerticalAnchor.aboveBottom(8), false, CarverDebugSettings.of(false, Blocks.OAK_BUTTON.defaultBlockState()), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.5F, 1.0F), UniformFloat.of(0.0F, 1.0F), 6, UniformFloat.of(0.25F, 1.0F), 0.0F, 5.0F))));

	private static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> register(String p_126856_, ConfiguredWorldCarver<WC> p_126857_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_CARVER, p_126856_, p_126857_);
	}

	@SubscribeEvent
	public static void registerCarvers(RegistryEvent.Register<WorldCarver<?>> registry) {
		registry.getRegistry().register(TOFU_CAVE_CARVER.setRegistryName("tofu_cave"));
		registry.getRegistry().register(TOFU_CANYON_CARVER.setRegistryName("tofu_canyon"));
	}
}

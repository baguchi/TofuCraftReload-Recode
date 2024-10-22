package baguchi.tofucraft.data.resources;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuCarvers;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class TofuConfiguredWorldCarvers {
	public static final ResourceKey<ConfiguredWorldCarver<?>> CAVE = createKey("tofu_cave");
	public static final ResourceKey<ConfiguredWorldCarver<?>> CAVE_EXTRA_UNDERGROUND = createKey("tofu_cave_extra_underground");
	public static final ResourceKey<ConfiguredWorldCarver<?>> CANYON = createKey("tofu_canyon");


	private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String p_256085_) {
		return ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_256085_));
	}

	public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> p_255626_) {
		HolderGetter<Block> holdergetter = p_255626_.lookup(Registries.BLOCK);
		p_255626_.register(TofuConfiguredWorldCarvers.CAVE, TofuCarvers.TOFU_CAVE_CARVER.get().configured(new CaveCarverConfiguration(0.15F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(9), CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), holdergetter.getOrThrow(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
		p_255626_.register(TofuConfiguredWorldCarvers.CAVE_EXTRA_UNDERGROUND, TofuCarvers.TOFU_CAVE_CARVER.get().configured(new CaveCarverConfiguration(0.07F, UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(47)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.aboveBottom(9), CarverDebugSettings.of(false, Blocks.OAK_BUTTON.defaultBlockState()), holdergetter.getOrThrow(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
		p_255626_.register(TofuConfiguredWorldCarvers.CANYON, TofuCarvers.TOFU_CANYON_CARVER.get().configured(new CanyonCarverConfiguration(0.01F, UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(67)), ConstantFloat.of(3.0F), VerticalAnchor.aboveBottom(9), CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()), holdergetter.getOrThrow(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(0.0F, 6.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 1.0F, 0.0F))));
	}
}

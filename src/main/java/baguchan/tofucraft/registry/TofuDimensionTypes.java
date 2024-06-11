package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class TofuDimensionTypes {
	public static final ResourceKey<DimensionType> TOFU_WORLD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_world_type"));


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
				TofuCraftReload.prefix("renderer"), // DimensionRenderInfo
				0f,
				new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 0)
		);
	}

	public static void bootstrap(BootstrapContext<DimensionType> p_256376_) {
		p_256376_.register(TOFU_WORLD_TYPE, TofuDimensionTypes.tofuDimType());
	}
}

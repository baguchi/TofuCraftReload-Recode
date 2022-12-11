package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class TofuDimensionTypes {
	public static final ResourceKey<DimensionType> TOFU_WORLD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(TofuCraftReload.MODID, "tofu_world_type"));


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

	public static void bootstrap(BootstapContext<DimensionType> p_256376_) {
		p_256376_.register(TOFU_WORLD_TYPE, TofuDimensionTypes.tofuDimType());
	}
}

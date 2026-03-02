package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.timeline.Timeline;

import java.util.Optional;

public class TofuDimensionTypes {
	public static final ResourceKey<DimensionType> TOFU_WORLD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_world_type"));

	public static void bootstrap(BootstrapContext<DimensionType> context) {
		HolderGetter<Timeline> holdergetter = context.lookup(Registries.TIMELINE);

		HolderGetter<Timeline> timelines = context.lookup(Registries.TIMELINE);
		HolderGetter<WorldClock> clocks = context.lookup(Registries.WORLD_CLOCK);
		EnvironmentAttributeMap environmentattributemap = EnvironmentAttributeMap.builder()
				.set(EnvironmentAttributes.FOG_COLOR, ARGB.white(0.8F))
				.set(EnvironmentAttributes.SKY_COLOR, ARGB.color(255, 255, 255, 200))
				.set(EnvironmentAttributes.CLOUD_COLOR, ARGB.white(0.8F))
				.set(EnvironmentAttributes.CLOUD_HEIGHT, 192.33F)
				.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(TofuMusics.TOFU_WORLD))
				.set(EnvironmentAttributes.BED_RULE, BedRule.CAN_SLEEP_WHEN_DARK)
				.set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
				.set(EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, true)
				//.set(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
				.build();
		context.register(TOFU_WORLD_TYPE, new DimensionType(
				false,
				true,
				true,
				false,
				1.0,
				-64,
				384,
				384,
				BlockTags.INFINIBURN_OVERWORLD,
				0.0F,
				new DimensionType.MonsterSettings(UniformInt.of(0, 7), 0),
				DimensionType.Skybox.OVERWORLD,
				CardinalLighting.Type.DEFAULT,
				environmentattributemap,
				holdergetter.getOrThrow(TimelineTags.IN_OVERWORLD),
				Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))
		));
	}
}

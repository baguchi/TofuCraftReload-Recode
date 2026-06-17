package baguchi.tofucraft.world.gen;

import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class TofuSurfaceRuleData {
	private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
	private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(TofuBlocks.TOFU_BEDROCK.get());
	private static final SurfaceRules.RuleSource TOFUSLATE = makeStateRule(TofuBlocks.TOFUSLATE.get());
	private static final SurfaceRules.RuleSource TOFU_TERRAIN = makeStateRule(TofuBlocks.TOFU_TERRAIN.get());
	private static final SurfaceRules.RuleSource TOFU_TERRAIN_ZUNDA = makeStateRule(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
	private static final SurfaceRules.RuleSource MINCED_TOFU = makeStateRule(TofuBlocks.MINCEDTOFU.get());
	private static final SurfaceRules.RuleSource OKARA_BLOCK = makeStateRule(TofuBlocks.OKARA_BLOCK.get());

	private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
		return SurfaceRules.state(p_194811_.defaultBlockState());
	}

	public static SurfaceRules.RuleSource tofuWorld(HolderGetter<Biome> biomes) {
		return tofuWorldLike(biomes, false, true);
	}

	public static SurfaceRules.RuleSource tofuWorldLike(HolderGetter<Biome> biomes, boolean p_198382_, boolean p_198383_) {
		ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		if (p_198382_) {
			builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
		}

		if (p_198383_) {
			builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
		}

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), TOFUSLATE));

		SurfaceRules.RuleSource zundaSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), TOFU_TERRAIN_ZUNDA), TOFU_TERRAIN);

		SurfaceRules.RuleSource zunda = SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, TofuBiomes.ZUNDA_FOREST), SurfaceRules.sequence(
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, zundaSurface),
				SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TOFU_TERRAIN)
		));
		SurfaceRules.RuleSource ishi = SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, TofuBiomes.TOFU_BEACH), SurfaceRules.sequence(
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MINCED_TOFU),
				SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MINCED_TOFU)
		));
		SurfaceRules.RuleSource overworldLike = SurfaceRules.sequence(zunda, ishi);



		SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), overworldLike);

		builder.add(surfacerules$rulesource9);

		return SurfaceRules.sequence(builder.build().toArray((p_198379_) -> {
			return new SurfaceRules.RuleSource[p_198379_];
		}));
	}
}

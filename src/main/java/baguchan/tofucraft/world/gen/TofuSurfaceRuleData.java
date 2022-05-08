package baguchan.tofucraft.world.gen;

import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
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

	private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
		return SurfaceRules.state(p_194811_.defaultBlockState());
	}

	public static SurfaceRules.RuleSource tofuWorld() {
		return tofuWorldLike(true, false, true);
	}

	public static SurfaceRules.RuleSource tofuWorldLike(boolean p_198381_, boolean p_198382_, boolean p_198383_) {
		ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		if (p_198382_) {
			builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
		}

		if (p_198383_) {
			builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
		}

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), TOFUSLATE));

		SurfaceRules.RuleSource surface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), TOFU_TERRAIN_ZUNDA), TOFU_TERRAIN);

		SurfaceRules.RuleSource overworldLike = SurfaceRules.ifTrue(SurfaceRules.isBiome(TofuBiomes.ZUNDA_FOREST), SurfaceRules.sequence(
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, surface),
				SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TOFU_TERRAIN)
		));

		SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), overworldLike);

		builder.add(surfacerules$rulesource9);

		return SurfaceRules.sequence(builder.build().toArray((p_198379_) -> {
			return new SurfaceRules.RuleSource[p_198379_];
		}));
	}
}

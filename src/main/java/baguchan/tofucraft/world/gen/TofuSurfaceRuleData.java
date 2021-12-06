package baguchan.tofucraft.world.gen;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class TofuSurfaceRuleData {
	private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
	private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(TofuBlocks.TOFU_BEDROCK);
	private static final SurfaceRules.RuleSource TOFUSLATE = makeStateRule(TofuBlocks.TOFUSLATE);

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

		SurfaceRules.ConditionSource surfacerules$conditionsource7 = SurfaceRules.waterBlockCheck(0, 0);
		SurfaceRules.ConditionSource surfacerules$conditionsource9 = SurfaceRules.hole();
		builder.add(SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource9, SurfaceRules.ifTrue(surfacerules$conditionsource7, AIR))));
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), TOFUSLATE));
		return SurfaceRules.sequence(builder.build().toArray((p_198379_) -> {
			return new SurfaceRules.RuleSource[p_198379_];
		}));
	}
}

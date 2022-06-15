package baguchan.tofucraft.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class TofunianAnimation {
	public static final AnimationDefinition TOFUNIAN_SUCCESS_TRADE = AnimationDefinition.Builder.withLength(2.5F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION
					, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
}

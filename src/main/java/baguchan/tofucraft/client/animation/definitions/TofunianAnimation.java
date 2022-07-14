package baguchan.tofucraft.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class TofunianAnimation {
	public static final AnimationDefinition TOFUNIAN_AGREE_TRADE = AnimationDefinition.Builder.withLength(1.25F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION
					, new Keyframe(0.0F, KeyframeAnimations.degreeVec(32.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.5F, KeyframeAnimations.degreeVec(32.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.0F, KeyframeAnimations.degreeVec(32.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();

	public static final AnimationDefinition TOFUNIAN_DAB = AnimationDefinition.Builder.withLength(1.25F)
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION
					, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-115.0F, -115.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.0F, KeyframeAnimations.degreeVec(-115.0F, -115.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION
					, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-80.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.0F, KeyframeAnimations.degreeVec(-80.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION
					, new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
}

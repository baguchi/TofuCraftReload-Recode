package baguchi.tofucraft.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class CoughAnimation {
	public static final AnimationDefinition COUGH = AnimationDefinition.Builder.withLength(1.05F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION
					, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.05F, KeyframeAnimations.degreeVec(42.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(0.55F, KeyframeAnimations.degreeVec(42.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					, new Keyframe(1.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			)).build();
}

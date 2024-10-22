package baguchi.tofucraft.data.resources;

import net.minecraft.util.CubicSpline;
import net.minecraft.util.Mth;
import net.minecraft.util.ToFloatFunction;
import net.minecraft.world.level.levelgen.NoiseRouterData;

public class TofuTerrainProvider {
	private static final float DEEP_OCEAN_CONTINENTALNESS = -0.51F;
	private static final float OCEAN_CONTINENTALNESS = -0.4F;
	private static final float PLAINS_CONTINENTALNESS = 0.1F;
	private static final float BEACH_CONTINENTALNESS = -0.15F;
	private static final ToFloatFunction<Float> NO_TRANSFORM;
	private static final ToFloatFunction<Float> AMPLIFIED_OFFSET;
	private static final ToFloatFunction<Float> AMPLIFIED_FACTOR;
	private static final ToFloatFunction<Float> AMPLIFIED_JAGGEDNESS;

	public TofuTerrainProvider() {
	}

	public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> overworldOffset(I p_236636_, I p_236637_, I p_236638_, boolean p_236639_) {
		ToFloatFunction<Float> tofloatfunction = p_236639_ ? AMPLIFIED_OFFSET : NO_TRANSFORM;
		CubicSpline<C, I> cubicspline = buildErosionOffsetSpline(p_236637_, p_236638_, -0.15F, 0.0F, 0.0F, 0.1F, 0.0F, -0.03F, false, false, tofloatfunction);
		CubicSpline<C, I> cubicspline1 = buildErosionOffsetSpline(p_236637_, p_236638_, -0.1F, 0.03F, 0.1F, 0.1F, 0.01F, -0.03F, false, false, tofloatfunction);
		CubicSpline<C, I> cubicspline2 = buildErosionOffsetSpline(p_236637_, p_236638_, -0.1F, 0.03F, 0.1F, 0.7F, 0.01F, -0.03F, true, true, tofloatfunction);
		CubicSpline<C, I> cubicspline3 = buildErosionOffsetSpline(p_236637_, p_236638_, -0.05F, 0.03F, 0.1F, 1.0F, 0.01F, 0.01F, true, true, tofloatfunction);
		return CubicSpline.builder(p_236636_, tofloatfunction).addPoint(-1.1F, 0.044F).addPoint(-1.02F, -0.2222F).addPoint(-0.51F, -0.2222F).addPoint(-0.44F, -0.12F).addPoint(-0.18F, -0.12F).addPoint(-0.16F, cubicspline).addPoint(-0.15F, cubicspline).addPoint(-0.1F, cubicspline1).addPoint(0.25F, cubicspline2).addPoint(1.0F, cubicspline3).build();
	}

	public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> overworldFactor(I p_236630_, I p_236631_, I p_236632_, I p_236633_, boolean p_236634_) {
		ToFloatFunction<Float> tofloatfunction = p_236634_ ? AMPLIFIED_FACTOR : NO_TRANSFORM;
		return CubicSpline.builder(p_236630_, NO_TRANSFORM).addPoint(-0.19F, 3.95F).addPoint(-0.15F, getErosionFactor(p_236631_, p_236632_, p_236633_, 6.25F, true, NO_TRANSFORM)).addPoint(-0.1F, getErosionFactor(p_236631_, p_236632_, p_236633_, 5.47F, true, tofloatfunction)).addPoint(0.03F, getErosionFactor(p_236631_, p_236632_, p_236633_, 5.08F, true, tofloatfunction)).addPoint(0.06F, getErosionFactor(p_236631_, p_236632_, p_236633_, 4.69F, false, tofloatfunction)).build();
	}

	public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> overworldJaggedness(I p_236643_, I p_236644_, I p_236645_, I p_236646_, boolean p_236647_) {
		ToFloatFunction<Float> tofloatfunction = p_236647_ ? AMPLIFIED_JAGGEDNESS : NO_TRANSFORM;
		float f = 0.65F;
		return CubicSpline.builder(p_236643_, tofloatfunction).addPoint(-0.11F, 0.0F).addPoint(0.03F, buildErosionJaggednessSpline(p_236644_, p_236645_, p_236646_, 1.0F, 0.5F, 0.0F, 0.0F, tofloatfunction)).addPoint(0.65F, buildErosionJaggednessSpline(p_236644_, p_236645_, p_236646_, 1.0F, 1.0F, 1.0F, 0.0F, tofloatfunction)).build();
	}

	private static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> buildErosionJaggednessSpline(I p_236614_, I p_236615_, I p_236616_, float p_236617_, float p_236618_, float p_236619_, float p_236620_, ToFloatFunction<Float> p_236621_) {
		float f = -0.5775F;
		CubicSpline<C, I> cubicspline = buildRidgeJaggednessSpline(p_236615_, p_236616_, p_236617_, p_236619_, p_236621_);
		CubicSpline<C, I> cubicspline1 = buildRidgeJaggednessSpline(p_236615_, p_236616_, p_236618_, p_236620_, p_236621_);
		return CubicSpline.builder(p_236614_, p_236621_).addPoint(-1.0F, cubicspline).addPoint(-0.78F, cubicspline1).addPoint(-0.5775F, cubicspline1).addPoint(-0.375F, 0.0F).build();
	}

	private static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> buildRidgeJaggednessSpline(I p_236608_, I p_236609_, float p_236610_, float p_236611_, ToFloatFunction<Float> p_236612_) {
		float f = NoiseRouterData.peaksAndValleys(0.4F);
		float f1 = NoiseRouterData.peaksAndValleys(0.56666666F);
		float f2 = (f + f1) / 2.0F;
		CubicSpline.Builder<C, I> builder = CubicSpline.builder(p_236609_, p_236612_);
		builder.addPoint(f, 0.0F);
		if (p_236611_ > 0.0F) {
			builder.addPoint(f2, buildWeirdnessJaggednessSpline(p_236608_, p_236611_, p_236612_));
		} else {
			builder.addPoint(f2, 0.0F);
		}

		if (p_236610_ > 0.0F) {
			builder.addPoint(1.0F, buildWeirdnessJaggednessSpline(p_236608_, p_236610_, p_236612_));
		} else {
			builder.addPoint(1.0F, 0.0F);
		}

		return builder.build();
	}

	private static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> buildWeirdnessJaggednessSpline(I p_236587_, float p_236588_, ToFloatFunction<Float> p_236589_) {
		float f = 0.63F * p_236588_;
		float f1 = 0.3F * p_236588_;
		return CubicSpline.builder(p_236587_, p_236589_).addPoint(-0.01F, f).addPoint(0.01F, f1).build();
	}

	private static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> getErosionFactor(I p_236623_, I p_236624_, I p_236625_, float p_236626_, boolean p_236627_, ToFloatFunction<Float> p_236628_) {
		CubicSpline<C, I> cubicspline = CubicSpline.builder(p_236624_, p_236628_).addPoint(-0.2F, 6.3F).addPoint(0.2F, p_236626_).build();
		CubicSpline.Builder<C, I> builder = CubicSpline.builder(p_236623_, p_236628_).addPoint(-0.6F, cubicspline).addPoint(-0.5F, CubicSpline.builder(p_236624_, p_236628_).addPoint(-0.05F, 6.3F).addPoint(0.05F, 2.67F).build()).addPoint(-0.35F, cubicspline).addPoint(-0.25F, cubicspline).addPoint(-0.1F, CubicSpline.builder(p_236624_, p_236628_).addPoint(-0.05F, 2.67F).addPoint(0.05F, 6.3F).build()).addPoint(0.03F, cubicspline);
		CubicSpline cubicspline1;
		CubicSpline cubicspline2;
		if (p_236627_) {
			cubicspline1 = CubicSpline.builder(p_236624_, p_236628_).addPoint(0.0F, p_236626_).addPoint(0.1F, 0.625F).build();
			cubicspline2 = CubicSpline.builder(p_236625_, p_236628_).addPoint(-0.9F, p_236626_).addPoint(-0.69F, cubicspline1).build();
			builder.addPoint(0.35F, p_236626_).addPoint(0.45F, cubicspline2).addPoint(0.55F, cubicspline2).addPoint(0.62F, p_236626_);
		} else {
			cubicspline1 = CubicSpline.builder(p_236625_, p_236628_).addPoint(-0.7F, cubicspline).addPoint(-0.15F, 1.37F).build();
			cubicspline2 = CubicSpline.builder(p_236625_, p_236628_).addPoint(0.45F, cubicspline).addPoint(0.7F, 1.56F).build();
			builder.addPoint(0.05F, cubicspline2).addPoint(0.4F, cubicspline2).addPoint(0.45F, cubicspline1).addPoint(0.55F, cubicspline1).addPoint(0.58F, p_236626_);
		}

		return builder.build();
	}

	private static float calculateSlope(float p_236573_, float p_236574_, float p_236575_, float p_236576_) {
		return (p_236574_ - p_236573_) / (p_236576_ - p_236575_);
	}

	private static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> buildMountainRidgeSplineWithPoints(I p_236591_, float p_236592_, boolean p_236593_, ToFloatFunction<Float> p_236594_) {
		CubicSpline.Builder<C, I> builder = CubicSpline.builder(p_236591_, p_236594_);
		float f = -0.7F;
		float f1 = -1.0F;
		float f2 = mountainContinentalness(-1.0F, p_236592_, -0.7F);
		float f3 = 1.0F;
		float f4 = mountainContinentalness(1.0F, p_236592_, -0.7F);
		float f5 = calculateMountainRidgeZeroContinentalnessPoint(p_236592_);
		float f6 = -0.65F;
		float f7;
		if (-0.65F < f5 && f5 < 1.0F) {
			f7 = mountainContinentalness(-0.65F, p_236592_, -0.7F);
			float f8 = -0.75F;
			float f9 = mountainContinentalness(-0.75F, p_236592_, -0.7F);
			float f10 = calculateSlope(f2, f9, -1.0F, -0.75F);
			builder.addPoint(-1.0F, f2, f10);
			builder.addPoint(-0.75F, f9);
			builder.addPoint(-0.65F, f7);
			float f11 = mountainContinentalness(f5, p_236592_, -0.7F);
			float f12 = calculateSlope(f11, f4, f5, 1.0F);
			float f13 = 0.01F;
			builder.addPoint(f5 - 0.01F, f11);
			builder.addPoint(f5, f11, f12);
			builder.addPoint(1.0F, f4, f12);
		} else {
			f7 = calculateSlope(f2, f4, -1.0F, 1.0F);
			if (p_236593_) {
				builder.addPoint(-1.0F, Math.max(0.2F, f2));
				builder.addPoint(0.0F, Mth.lerp(0.5F, f2, f4), f7);
			} else {
				builder.addPoint(-1.0F, f2, f7);
			}

			builder.addPoint(1.0F, f4, f7);
		}

		return builder.build();
	}

	private static float mountainContinentalness(float p_236569_, float p_236570_, float p_236571_) {
		float f = 1.17F;
		float f1 = 0.46082947F;
		float f2 = 1.0F - (1.0F - p_236570_) * 0.5F;
		float f3 = 0.5F * (1.0F - p_236570_);
		float f4 = (p_236569_ + 1.17F) * 0.46082947F;
		float f5 = f4 * f2 - f3;
		return p_236569_ < p_236571_ ? Math.max(f5, -0.2222F) : Math.max(f5, 0.0F);
	}

	private static float calculateMountainRidgeZeroContinentalnessPoint(float p_236567_) {
		float f = 1.17F;
		float f1 = 0.46082947F;
		float f2 = 1.0F - (1.0F - p_236567_) * 0.5F;
		float f3 = 0.5F * (1.0F - p_236567_);
		return f3 / (0.46082947F * f2) - 1.17F;
	}

	public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> buildErosionOffsetSpline(I p_236596_, I p_236597_, float p_236598_, float p_236599_, float p_236600_, float p_236601_, float p_236602_, float p_236603_, boolean p_236604_, boolean p_236605_, ToFloatFunction<Float> p_236606_) {
		float f = 0.6F;
		float f1 = 0.5F;
		float f2 = 0.5F;
		CubicSpline<C, I> cubicspline = buildMountainRidgeSplineWithPoints(p_236597_, Mth.lerp(p_236601_, 0.6F, 1.5F), p_236605_, p_236606_);
		CubicSpline<C, I> cubicspline1 = buildMountainRidgeSplineWithPoints(p_236597_, Mth.lerp(p_236601_, 0.6F, 1.0F), p_236605_, p_236606_);
		CubicSpline<C, I> cubicspline2 = buildMountainRidgeSplineWithPoints(p_236597_, p_236601_, p_236605_, p_236606_);
		CubicSpline<C, I> cubicspline3 = ridgeSpline(p_236597_, p_236598_ - 0.15F, 0.5F * p_236601_, Mth.lerp(0.5F, 0.5F, 0.5F) * p_236601_, 0.5F * p_236601_, 0.6F * p_236601_, 0.5F, p_236606_);
		CubicSpline<C, I> cubicspline4 = ridgeSpline(p_236597_, p_236598_, p_236602_ * p_236601_, p_236599_ * p_236601_, 0.5F * p_236601_, 0.6F * p_236601_, 0.5F, p_236606_);
		CubicSpline<C, I> cubicspline5 = ridgeSpline(p_236597_, p_236598_, p_236602_, p_236602_, p_236599_, p_236600_, 0.5F, p_236606_);
		CubicSpline<C, I> cubicspline6 = ridgeSpline(p_236597_, p_236598_, p_236602_, p_236602_, p_236599_, p_236600_, 0.5F, p_236606_);
		CubicSpline<C, I> cubicspline7 = CubicSpline.builder(p_236597_, p_236606_).addPoint(-1.0F, p_236598_).addPoint(-0.4F, cubicspline5).addPoint(0.0F, p_236600_ + 0.07F).build();
		CubicSpline<C, I> cubicspline8 = ridgeSpline(p_236597_, -0.02F, p_236603_, p_236603_, p_236599_, p_236600_, 0.0F, p_236606_);
		CubicSpline.Builder<C, I> builder = CubicSpline.builder(p_236596_, p_236606_).addPoint(-0.85F, cubicspline).addPoint(-0.7F, cubicspline1).addPoint(-0.4F, cubicspline2).addPoint(-0.35F, cubicspline3).addPoint(-0.1F, cubicspline4).addPoint(0.2F, cubicspline5);
		if (p_236604_) {
			builder.addPoint(0.4F, cubicspline6).addPoint(0.45F, cubicspline7).addPoint(0.55F, cubicspline7).addPoint(0.58F, cubicspline6);
		}

		builder.addPoint(0.7F, cubicspline8);
		return builder.build();
	}

	private static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> ridgeSpline(I p_236578_, float p_236579_, float p_236580_, float p_236581_, float p_236582_, float p_236583_, float p_236584_, ToFloatFunction<Float> p_236585_) {
		float f = Math.max(0.5F * (p_236580_ - p_236579_), p_236584_);
		float f1 = 5.0F * (p_236581_ - p_236580_);
		return CubicSpline.builder(p_236578_, p_236585_).addPoint(-1.0F, p_236579_, f).addPoint(-0.4F, p_236580_, Math.min(f, f1)).addPoint(0.0F, p_236581_, f1).addPoint(0.4F, p_236582_, 2.0F * (p_236582_ - p_236581_)).addPoint(1.0F, p_236583_, 0.7F * (p_236583_ - p_236582_)).build();
	}

	static {
		NO_TRANSFORM = ToFloatFunction.IDENTITY;
		AMPLIFIED_OFFSET = ToFloatFunction.createUnlimited((p_236651_) -> {
			return p_236651_ < 0.0F ? p_236651_ : p_236651_ * 2.0F;
		});
		AMPLIFIED_FACTOR = ToFloatFunction.createUnlimited((p_236649_) -> {
			return 1.25F - 6.25F / (p_236649_ + 5.0F);
		});
		AMPLIFIED_JAGGEDNESS = ToFloatFunction.createUnlimited((p_236641_) -> {
			return p_236641_ * 2.0F;
		});
	}
}

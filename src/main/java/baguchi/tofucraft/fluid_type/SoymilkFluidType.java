package baguchi.tofucraft.fluid_type;

import baguchi.tofucraft.registry.TofuFluidTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

public class SoymilkFluidType extends FluidType {
	public SoymilkFluidType(FluidType.Properties properties) {
		super(properties);
	}


	@Override
	public boolean move(LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean flag = entity.getDeltaMovement().y <= 0.0D;
		double d8 = entity.getY();

		float f = entity.isSprinting() ? 0.9F : 0.8F;
		float f1 = 0.02F;
		float f2 = (float) entity.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);
		if (!entity.onGround()) {
			f2 *= 0.5F;
		}

		if (f2 > 0.0F) {
			f += (0.54600006F - f) * f2;
			f1 += (entity.getSpeed() - f1) * f2;
		}

		if (entity.hasEffect(MobEffects.DOLPHINS_GRACE)) {
			f = 0.96F;
		}

		f1 *= (float) entity.getAttributeValue(net.neoforged.neoforge.common.NeoForgeMod.SWIM_SPEED);
		entity.moveRelative(f1, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());
		Vec3 vec3 = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.onClimbable()) {
			vec3 = new Vec3(vec3.x, 0.2, vec3.z);
		}

		vec3 = vec3.multiply(f, 0.8F, f);
		entity.setDeltaMovement(entity.getFluidFallingAdjustedMovement(gravity, flag, vec3));
		return false;
	}


	@Override
	public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
		if (!level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
			return false;
		} else {
			return this == TofuFluidTypes.SOYMILK.get();
		}
	}
}


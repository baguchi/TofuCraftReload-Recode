package baguchi.tofucraft.fluid_type;

import baguchi.tofucraft.mixin.LivingEntityAccessor;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

public class DoubanjiangFluidType extends FluidType {
	public DoubanjiangFluidType(Properties properties) {
		super(properties);
	}

	@Override
	public boolean move(FluidState state, LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean isFalling = entity.getDeltaMovement().y <= 0.0;
		double oldY = entity.getY();
		double baseGravity = gravity;

		float slowDown = entity.isSprinting() ? 0.9F : ((LivingEntityAccessor) entity).getWaterSlowDown();
		float speed = 0.02F;
		float waterWalker = (float) entity.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);
		if (!entity.onGround()) {
			waterWalker *= 0.5F;
		}

		if (waterWalker > 0.0F) {
			slowDown += (0.54600006F - slowDown) * waterWalker;
			speed += (entity.getSpeed() - speed) * waterWalker;
		}

		if (entity.hasEffect(MobEffects.DOLPHINS_GRACE)) {
			slowDown = 0.96F;
		}

		speed *= (float) entity.getAttributeValue(net.neoforged.neoforge.common.NeoForgeMod.SWIM_SPEED);
		entity.moveRelative(speed, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());
		Vec3 movement = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.onClimbable()) {
			movement = new Vec3(movement.x, 0.2, movement.z);
		}

		movement = movement.multiply(slowDown, 0.8F, slowDown);
		entity.setDeltaMovement(entity.getFluidFallingAdjustedMovement(baseGravity, isFalling, movement));
		((LivingEntityAccessor) entity).jumpOutOfFluid(oldY);
		return true;
	}

}

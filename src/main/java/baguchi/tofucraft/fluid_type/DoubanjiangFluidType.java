package baguchi.tofucraft.fluid_type;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

public class DoubanjiangFluidType extends FluidType {
	public DoubanjiangFluidType(Properties properties) {
		super(properties);
	}

	@Override
	public boolean move(FluidState state, LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean flag = entity.getDeltaMovement().y <= 0.0D;
		double d8 = entity.getY();

		entity.moveRelative(0.02F, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());

		if (entity.getFluidTypeHeight(this) <= entity.getFluidJumpThreshold()) {
			entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.8D, (double) 0.8F, 0.8D));
			Vec3 vec33 = entity.getFluidFallingAdjustedMovement(gravity, flag, entity.getDeltaMovement());
			entity.setDeltaMovement(vec33);
		} else {
			entity.setDeltaMovement(entity.getDeltaMovement().scale(0.8D));
		}

		if (!entity.isNoGravity()) {
			entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, -gravity / 4.0D, 0.0D));
		}

		Vec3 vec34 = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.isFree(vec34.x, vec34.y + (double) 0.6F - entity.getY() + d8, vec34.z)) {
			entity.setDeltaMovement(vec34.x, (double) 0.3F, vec34.z);
		}

		return true;
	}

}

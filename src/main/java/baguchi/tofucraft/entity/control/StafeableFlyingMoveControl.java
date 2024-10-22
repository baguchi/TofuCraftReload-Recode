package baguchi.tofucraft.entity.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class StafeableFlyingMoveControl extends MoveControl {
	private final int maxTurn;
	private final boolean hoversInPlace;

	public StafeableFlyingMoveControl(Mob p_24893_, int p_24894_, boolean p_24895_) {
		super(p_24893_);
		this.maxTurn = p_24894_;
		this.hoversInPlace = p_24895_;
	}

	public void tick() {
		if (this.operation == MoveControl.Operation.STRAFE) {
			this.operation = MoveControl.Operation.WAIT;
			if (!this.hoversInPlace) {
				this.mob.setNoGravity(false);
			}

			float f = (float) this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);

			if (!this.mob.onGround()) {
				f = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
			}
			float f1 = (float) this.speedModifier * f;
			float f2 = this.strafeForwards;
			float f3 = this.strafeRight;
			float f4 = Mth.sqrt(f2 * f2 + f3 * f3);
			if (f4 < 1.0F) {
				f4 = 1.0F;
			}

			f4 = f1 / f4;
			f2 *= f4;
			f3 *= f4;
			float f5 = Mth.sin(this.mob.getYRot() * ((float) Math.PI / 180F));
			float f6 = Mth.cos(this.mob.getYRot() * ((float) Math.PI / 180F));
			float f7 = f2 * f6 - f3 * f5;
			float f8 = f3 * f6 + f2 * f5;

			double d0 = this.strafeRight - this.mob.getX();
			double d1 = this.strafeForwards - this.mob.getZ();

			this.mob.setSpeed(f1);
			this.mob.setZza(this.strafeForwards);
			this.mob.setXxa(this.strafeRight);
			this.mob.setYya(-f1 * 1.5F);
			this.operation = MoveControl.Operation.WAIT;
		} else if (this.operation == MoveControl.Operation.MOVE_TO) {
			this.operation = MoveControl.Operation.WAIT;
			this.mob.setNoGravity(true);


			double d0 = this.wantedX - this.mob.getX();
			double d1 = this.wantedY - this.mob.getY();
			double d2 = this.wantedZ - this.mob.getZ();
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			if (d3 < (double) 2.5000003E-7F) {
				this.mob.setYya(0.0F);
				this.mob.setZza(0.0F);
				return;
			}

			float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
			this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
			float f1;
			if (this.mob.onGround()) {
				f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
			} else {
				f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
			}

			this.mob.setSpeed(f1);
			double d4 = Math.sqrt(d0 * d0 + d2 * d2);
			if (Math.abs(d1) > (double) 1.0E-5F || Math.abs(d4) > (double) 1.0E-5F) {
				float f2 = (float) (-(Mth.atan2(d1, d4) * (double) (180F / (float) Math.PI)));
				this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float) this.maxTurn));
				this.mob.setYya(d1 > 0.0D ? f1 : -f1);
			}
		} else {
			if (!this.hoversInPlace) {
				this.mob.setNoGravity(false);
			}
			this.mob.setXxa(0.0F);
			this.mob.setYya(0.0F);
			this.mob.setZza(0.0F);
		}

	}
}
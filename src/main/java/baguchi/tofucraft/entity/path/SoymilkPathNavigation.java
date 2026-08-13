package baguchi.tofucraft.entity.path;

import baguchi.tofucraft.registry.TofuFluidTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;

public class SoymilkPathNavigation extends PathNavigation {
	private boolean allowBreaching;

	public SoymilkPathNavigation(Mob p_26594_, Level p_26595_) {
		super(p_26594_, p_26595_);
	}

	protected PathFinder createPathFinder(int p_26598_) {
		this.allowBreaching = this.mob.getType() == EntityTypes.DOLPHIN;
		this.nodeEvaluator = new SoymilkSwimNodeEvaluator(this.allowBreaching);
		return new PathFinder(this.nodeEvaluator, p_26598_);
	}

	@Override
	protected boolean canUpdatePath() {
		return this.allowBreaching || this.mob.isInFluidType(TofuFluidTypes.SOYMILK.get()) || this.mob.isInFluidType(TofuFluidTypes.SOYMILK_HELL.get()) || this.mob.isInFluidType(TofuFluidTypes.SOYMILK_SOUL.get());
	}

	protected Vec3 getTempMobPos() {
		return new Vec3(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
	}

	protected double getGroundY(Vec3 p_186136_) {
		return p_186136_.y;
	}

	protected boolean canMoveDirectly(Vec3 p_186138_, Vec3 p_186139_) {
		return isClearForMovementBetween(this.mob, p_186138_, p_186139_, false);
	}

	public boolean isStableDestination(BlockPos p_26608_) {
		return !this.level.getBlockState(p_26608_).isSolidRender();
	}

	public void setCanFloat(boolean p_26612_) {
	}

	@Override
	public boolean canNavigateGround() {
		return false;
	}
}
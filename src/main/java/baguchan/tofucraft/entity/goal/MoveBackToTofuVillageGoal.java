package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.registry.TofuPoiTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

public class MoveBackToTofuVillageGoal extends RandomStrollGoal {
	private static final int MAX_XZ_DIST = 10;
	private static final int MAX_Y_DIST = 7;

	public MoveBackToTofuVillageGoal(PathfinderMob p_25568_, double p_25569_, boolean p_25570_) {
		super(p_25568_, p_25569_, 10, p_25570_);
	}

	@Override
	public boolean canUse() {
		ServerLevel serverlevel = (ServerLevel) this.mob.level();
		BlockPos blockpos = this.mob.blockPosition();

		Optional<BlockPos> optional = ((ServerLevel) serverlevel).getPoiManager().findClosest((p_184069_) -> {
			return p_184069_.value() == TofuPoiTypes.TOFUNIAN_STATUE_POI.get();
		}, (p_184055_) -> {
			return true;
		}, this.mob.blockPosition(), 42, PoiManager.Occupancy.IS_OCCUPIED);
		return optional.isPresent() ? false : super.canUse();
	}

	@Nullable
	@Override
	protected Vec3 getPosition() {
		ServerLevel serverlevel = (ServerLevel) this.mob.level();
		BlockPos blockpos = this.mob.blockPosition();
		Optional<BlockPos> optional = ((ServerLevel) serverlevel).getPoiManager().findClosest((p_184069_) -> {
			return p_184069_.value() == TofuPoiTypes.TOFUNIAN_STATUE_POI.get();
		}, (p_184055_) -> {
			return true;
		}, this.mob.blockPosition(), 42, PoiManager.Occupancy.IS_OCCUPIED);
		return optional.isPresent()
				? DefaultRandomPos.getPosTowards(this.mob, 10, 7, optional.get().getCenter(), (float) (Math.PI / 2))
				: null;
	}
}

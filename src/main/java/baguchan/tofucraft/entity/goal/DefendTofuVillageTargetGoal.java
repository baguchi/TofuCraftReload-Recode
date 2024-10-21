package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class DefendTofuVillageTargetGoal extends TargetGoal {
	private final PathfinderMob golem;
	@Nullable
	private LivingEntity potentialTarget;
	private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);

	public DefendTofuVillageTargetGoal(PathfinderMob p_26029_) {
		super(p_26029_, false, true);
		this.golem = p_26029_;
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	public boolean canUse() {
		AABB aabb = this.golem.getBoundingBox().inflate(10.0D, 8.0D, 10.0D);
		List<? extends LivingEntity> list = this.golem.level().getEntitiesOfClass(Tofunian.class, aabb);
		List<Player> list1 = this.golem.level().getEntitiesOfClass(Player.class, aabb, player -> {
			return !player.isSpectator() && !player.isCreative();
		});

		for (LivingEntity livingentity : list) {
			Tofunian villager = (Tofunian) livingentity;

			for (Player player : list1) {
				int i = villager.getPlayerReputation(player);
				if (i <= -100) {
					this.potentialTarget = player;
				}
			}
		}

		if (this.potentialTarget == null) {
			return false;
		} else {
			return !(this.potentialTarget instanceof Player) || !this.potentialTarget.isSpectator() && !((Player) this.potentialTarget).isCreative();
		}
	}

	public void start() {
		this.golem.setTarget(this.potentialTarget);
		super.start();
	}
}
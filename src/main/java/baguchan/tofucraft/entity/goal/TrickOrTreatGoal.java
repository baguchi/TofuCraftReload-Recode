package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.utils.DayHelper;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class TrickOrTreatGoal extends Goal {
	private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreInvisibilityTesting();

	protected final Tofunian tofunian;

	protected final double speedModifier;

	protected int nextStartTick;

	protected Player partner;


	private int tryingTalkingTick;

	public TrickOrTreatGoal(Tofunian entity, double speed) {
		this.tofunian = entity;
		this.speedModifier = speed;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (!DayHelper.isHalloween() || this.tofunian.getAction() != Tofunian.Actions.NORMAL) {
			return false;
		}
		if (this.nextStartTick > 0) {
			this.nextStartTick--;
			return false;
		}
		if (this.tofunian.level().isNight() && !this.tofunian.isBaby() && this.tofunian.getMainHandItem().isEmpty()) {
			this.nextStartTick = this.tofunian.getRandom().nextInt(600) + 600;
			this.partner = getFreePartner();
			return (this.partner != null);
		}
		return false;
	}

	public boolean canContinueToUse() {
		return (this.tryingTalkingTick < 400 && this.partner != null && this.partner.isAlive() && this.tofunian.distanceToSqr(this.partner) < 64) && this.tofunian.getMainHandItem().isEmpty();
	}

	public void start() {
		this.tofunian.setAction(Tofunian.Actions.ASK_FOOD);
	}

	@Override
	public void stop() {
		super.stop();
		if (this.tofunian.getPreviousTreat() == null) {
			this.tofunian.setAction(Tofunian.Actions.CRY);
		}
		this.tryingTalkingTick = 0;
	}

	@Nullable
	private Player getFreePartner() {
		List<Player> list = this.tofunian.level().getNearbyPlayers(PARTNER_TARGETING, this.tofunian, this.tofunian.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		Player player2 = null;
		for (Player player1 : list) {
			if (this.tofunian.distanceToSqr(player1) < d0) {
				player2 = player1;
				d0 = this.tofunian.distanceToSqr(player1);
			}
		}
		return player2;
	}

	public void tick() {
		super.tick();
		++this.tryingTalkingTick;
		if (this.tofunian.hasLineOfSight(this.partner)) {

			if (this.tofunian.distanceToSqr(this.partner) < 6.0F) {
				this.tofunian.getNavigation().stop();
				this.tofunian.getLookControl().setLookAt(this.partner, 30.0F, 30.0F);
			} else {
				this.tofunian.getNavigation().moveTo(this.partner, 1.0D);
				this.tofunian.getLookControl().setLookAt(this.partner, 30.0F, 30.0F);
			}
		} else {
			this.tofunian.getNavigation().moveTo(this.partner, 1.0D);
		}
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}
}
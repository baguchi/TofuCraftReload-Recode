package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class FindJobBlockGoal extends MoveToBlockGoal {
	private final Tofunian creature;
	private boolean findBlock;

	public FindJobBlockGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.level().isDay() && (this.creature.getRole() == Tofunian.Roles.TOFUNIAN || this.creature.getTofunianJobBlock() == null) && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return !this.findBlock && (super.canContinueToUse() && this.creature.level().isDay() && !this.creature.isBaby() && (this.creature.getRole() == Tofunian.Roles.TOFUNIAN || this.creature.getTofunianJobBlock() == null) && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			if (!findNearbyTofunianHadJob(this.creature, this.blockPos)) {
				Tofunian.Roles role = Tofunian.Roles.getJob(this.creature.level().getBlockState(this.blockPos));
					if (role != null && !this.findBlock) {
						if (this.creature.level() instanceof ServerLevel) {
							this.creature.setTofunianJobBlock(this.blockPos);
							if (this.creature.getRole() == Tofunian.Roles.TOFUNIAN) {
								this.creature.setRole(role);
								this.creature.setOffers(null);


								this.findBlock = true;
							}
						}


						this.creature.swing(InteractionHand.MAIN_HAND);
						this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
					}
			} else {
				this.findBlock = true;
			}
		}
	}

	@Override
	protected boolean findNearestBlock() {
		if (super.findNearestBlock()) {
			if (!findNearbyTofunianHadJob(this.creature, this.blockPos)) {
				return true;
			}
		}


		return false;
	}

	public static boolean findNearbyTofunianHadJob(Tofunian tofunian, BlockPos pos) {
		List<Tofunian> list = tofunian.level().getEntitiesOfClass(Tofunian.class, tofunian.getBoundingBox().inflate(32.0D));

		return list.stream().anyMatch((p_34881_) -> {
			return p_34881_ != tofunian && (pos.equals(p_34881_.getTofunianJobBlock()));
		});
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Tofunian.Roles role = Tofunian.Roles.getJob(blockstate);
		BlockHitResult hitResult = worldIn.clip(new ClipContext(this.creature.getEyePosition(), pos.getCenter(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.creature));
		if (role != null && hitResult.getBlockPos().equals(BlockPos.containing(pos.getCenter())) || hitResult.getType() == HitResult.Type.MISS) {
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		super.start();
		this.findBlock = false;
	}

	@Override
	public void stop() {
		super.stop();
	}

	public double acceptedDistance() {
		return 3.0D;
	}
}
package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.registry.TofuTags;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindJobBlockGoal extends MoveToBlockGoal {
	public static final Predicate<Holder<PoiType>> ALL_ACQUIRABLE_JOBS = (p_238239_) -> {
		return p_238239_.is(TofuTags.PoiTypes.TOFUNIAN_JOB);
	};

	private final Tofunian creature;
	private boolean findBlock;
	private ResourceKey<PoiType> poiTypeResourceKey;

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
				if (this.poiTypeResourceKey != null) {
					Tofunian.Roles role = Tofunian.Roles.getJob(this.poiTypeResourceKey);
					if (role != null && !this.findBlock) {
						if (this.creature.level() instanceof ServerLevel) {
							((ServerLevel) this.creature.level()).getPoiManager().getType(this.blockPos).ifPresent((p_217105_) -> {
								((ServerLevel) this.creature.level()).getPoiManager().take(poiTypeHolder -> {
									return poiTypeHolder == ForgeRegistries.POI_TYPES.getHolder(this.poiTypeResourceKey).get();
								}, (p_217108_, p_217109_) -> {
									return p_217109_.equals(this.blockPos);
								}, this.blockPos, 1);
								DebugPackets.sendPoiTicketCountPacket(((ServerLevel) this.creature.level()), this.blockPos);
								this.creature.setTofunianJobBlock(this.blockPos);
								if (this.creature.getRole() == Tofunian.Roles.TOFUNIAN) {
									this.creature.setRole(role);
									this.creature.setOffers(null);

									this.findBlock = true;
								}
							});
						}


						this.creature.swing(InteractionHand.MAIN_HAND);
						this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
					}
				}
			} else {
				this.findBlock = true;
			}
		}
	}

	@Override
	protected boolean findNearestBlock() {
		if (this.creature.level() instanceof ServerLevel) {
			Set<Pair<Holder<PoiType>, BlockPos>> set = ((ServerLevel) this.creature.level()).getPoiManager().findAllClosestFirstWithType(ALL_ACQUIRABLE_JOBS, predicate -> {
				return Tofunian.Roles.get(this.creature.level().getBlockState(predicate)) != null;
			}, this.creature.blockPosition(), 32, PoiManager.Occupancy.HAS_SPACE).limit(5L).collect(Collectors.toSet());
			if (!set.isEmpty()) {

				/*
				 * Find Tofunian's poitype
				 */
				if ((this.creature.getRole() == Tofunian.Roles.TOFUNIAN || this.creature.getTofunianJobBlock() == null)) {
					Optional<Pair<Holder<PoiType>, BlockPos>> pair = set.stream().findFirst();
					if (((ServerLevel) this.creature.level()).getPoiManager().exists(pair.get().getSecond(), (p_217230_) -> {
						return true;
					})) {
						if (!findNearbyTofunianHadJob(this.creature, pair.get().getSecond())) {
							this.blockPos = pair.get().getSecond();
							this.poiTypeResourceKey = pair.get().getFirst().unwrapKey().get();
							return true;
						}
					}
				}
				if (this.creature.getRole() != Tofunian.Roles.TOFUNIAN) {
					Optional<Pair<Holder<PoiType>, BlockPos>> pair = set.stream().findFirst();
					if (((ServerLevel) this.creature.level()).getPoiManager().exists(pair.get().getSecond(), (p_217230_) -> {
						return true;
					})) {
						if (!findNearbyTofunianHadJob(this.creature, pair.get().getSecond())) {
							this.blockPos = pair.get().getSecond();
							this.poiTypeResourceKey = pair.get().getFirst().unwrapKey().get();
							return true;
						}
					}
				}

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
		return this.poiTypeResourceKey != null && Tofunian.Roles.getJobBlock(this.poiTypeResourceKey).contains(blockstate);
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
		return 2.0D;
	}
}
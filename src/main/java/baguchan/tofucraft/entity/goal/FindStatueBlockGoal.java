package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.registry.TofuPoiTypes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.LevelReader;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindStatueBlockGoal extends MoveToBlockGoal {
	public static final Predicate<Holder<PoiType>> STATUE_POI = (p_238239_) -> {
		return p_238239_.is(TofuPoiTypes.TOFUNIAN_STATUE);
	};

	private final Tofunian creature;
	private boolean findBlock;
	private ResourceKey<PoiType> poiTypeResourceKey;

	public FindStatueBlockGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.getVillageCenter() == null && super.canUse());
	}

	public boolean canContinueToUse() {
		return !this.findBlock && (super.canContinueToUse() && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			if (this.poiTypeResourceKey != null) {
				if (!this.findBlock) {
					if (this.creature.level() instanceof ServerLevel) {
						((ServerLevel) this.creature.level()).getPoiManager().getType(this.blockPos).ifPresent((p_217105_) -> {
							((ServerLevel) this.creature.level()).getPoiManager().take(poiTypeHolder -> {
								return poiTypeHolder == BuiltInRegistries.POINT_OF_INTEREST_TYPE.get(this.poiTypeResourceKey).get();
							}, (p_217108_, p_217109_) -> {
								return p_217109_.equals(this.blockPos);
							}, this.blockPos, 1);
							DebugPackets.sendPoiTicketCountPacket(((ServerLevel) this.creature.level()), this.blockPos);
							this.creature.setVillageCenter(this.blockPos);
							this.findBlock = true;
							this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
						});
					}
				}
			}
		}
	}

	@Override
	protected boolean findNearestBlock() {
		if (this.creature.level() instanceof ServerLevel) {
			Set<Pair<Holder<PoiType>, BlockPos>> set = ((ServerLevel) this.creature.level()).getPoiManager().findAllClosestFirstWithType(STATUE_POI, predicate -> {
				return true;
			}, this.creature.blockPosition(), 48, PoiManager.Occupancy.ANY).limit(5L).collect(Collectors.toSet());
			if (!set.isEmpty()) {
				Optional<Pair<Holder<PoiType>, BlockPos>> pair = set.stream().findFirst();
				if (((ServerLevel) this.creature.level()).getPoiManager().exists(pair.get().getSecond(), (p_217230_) -> {
					return true;
				}))
					this.blockPos = pair.get().getSecond();
				this.poiTypeResourceKey = pair.get().getFirst().unwrapKey().get();
				return true;
			}
		}


		return false;
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		return this.poiTypeResourceKey != null;
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
		return 8.0D;
	}
}
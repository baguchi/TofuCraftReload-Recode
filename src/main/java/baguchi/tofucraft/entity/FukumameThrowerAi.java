package baguchi.tofucraft.entity;

import baguchi.tofucraft.entity.behaviors.EatFukumame;
import baguchi.tofucraft.entity.behaviors.SetWalkTargetFromAttackTargetIfTargetOutOfReachOneShot;
import baguchi.tofucraft.entity.behaviors.ThrowFukumame;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BackUpIfTooClose;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.behavior.CopyMemoryWithExpiry;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.ai.behavior.EraseMemoryIf;
import net.minecraft.world.entity.ai.behavior.InteractWith;
import net.minecraft.world.entity.ai.behavior.InteractWithDoor;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MeleeAttack;
import net.minecraft.world.entity.ai.behavior.Mount;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.SetEntityLookTarget;
import net.minecraft.world.entity.ai.behavior.SetLookAndInteract;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetAwayFrom;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetFromLookTarget;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.behavior.StartCelebratingIfTargetDead;
import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
import net.minecraft.world.entity.ai.behavior.StopBeingAngryIfTargetDead;
import net.minecraft.world.entity.ai.behavior.TriggerGate;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.behavior.declarative.Trigger;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.RememberIfHoglinWasKilled;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FukumameThrowerAi {
	private static final UniformInt AVOID_ZOMBIFIED_DURATION = TimeUtil.rangeOfSeconds(5, 7);
	private static final UniformInt BABY_AVOID_NEMESIS_DURATION = TimeUtil.rangeOfSeconds(5, 7);


	public static List<ActivityData<FukumameThrower>> getThrowerActivities(FukumameThrower piglin) {
		return List.of(
				initCoreActivity(),
				initIdleActivity(),
				initFightActivity(piglin),
				initRetreatActivity(),
				initRideHoglinActivity()
		);
	}

	private static ActivityData<FukumameThrower> initCoreActivity() {
		return ActivityData.<FukumameThrower>create(
				Activity.CORE,
				0,
				ImmutableList.of(
						new LookAtTargetSink(45, 90),
						new MoveToTargetSink(),
						InteractWithDoor.create(),
						babyAvoidNemesis(),
						avoidZombified(),
						StartCelebratingIfTargetDead.create(300, FukumameThrowerAi::wantsToDance),
						StopBeingAngryIfTargetDead.create()
				)
		);
	}

	private static ActivityData<FukumameThrower> initIdleActivity() {
		return ActivityData.<FukumameThrower>create(
				Activity.IDLE,
				10,
				ImmutableList.of(
						StartAttacking.<FukumameThrower>create((level, piglin) -> piglin.isAdult(), FukumameThrowerAi::findNearestValidAttackTarget),
						avoidRepellent(),
						createIdleLookBehaviors(),
						createIdleMovementBehaviors(),
						SetLookAndInteract.create(EntityTypes.PLAYER, 4)
				)
		);
	}

	private static ActivityData<FukumameThrower> initFightActivity(FukumameThrower body) {
		return ActivityData.create(
				Activity.FIGHT,
				10,
				ImmutableList.<net.minecraft.world.entity.ai.behavior.BehaviorControl<? super FukumameThrower>>of(
						StopAttackingIfTargetInvalid.create((level, target) -> !isNearestValidAttackTarget(level, body, target)),
						BehaviorBuilder.triggerIf(predicate -> body.getFukumameCount() > 0, BackUpIfTooClose.create(5, 0.75F)),
						BehaviorBuilder.triggerIf(predicate -> body.getFukumameCount() <= 0, SetWalkTargetFromAttackTargetIfTargetOutOfReachOneShot.create(1.0F)),
						BehaviorBuilder.triggerIf(predicate -> body.getFukumameCount() <= 0, MeleeAttack.create(20)),
						new EatFukumame(),
						new ThrowFukumame(),
						RememberIfHoglinWasKilled.create(),
						EraseMemoryIf.create(FukumameThrowerAi::isNearZombified, MemoryModuleType.ATTACK_TARGET)
				),
				MemoryModuleType.ATTACK_TARGET
		);
	}

	private static ActivityData<FukumameThrower> initRetreatActivity() {
		return ActivityData.<FukumameThrower>create(
				Activity.AVOID,
				10,
				ImmutableList.of(
						SetWalkTargetAwayFrom.entity(MemoryModuleType.AVOID_TARGET, 1.0F, 12, true),
						createIdleLookBehaviors(),
						createIdleMovementBehaviors(),
						EraseMemoryIf.<FukumameThrower>create(FukumameThrowerAi::wantsToStopFleeing, MemoryModuleType.AVOID_TARGET)
				),
				MemoryModuleType.AVOID_TARGET
		);
	}

	private static ActivityData<FukumameThrower> initRideHoglinActivity() {
		return ActivityData.<FukumameThrower>create(
				Activity.RIDE,
				10,
				ImmutableList.of(
						Mount.create(0.8F),
						BehaviorBuilder.sequence(
								BehaviorBuilder.triggerIf(Entity::isPassenger),
								TriggerGate.triggerOneShuffled(
										ImmutableList.<Pair<? extends Trigger<? super LivingEntity>, Integer>>builder()
												.addAll(createLookBehaviors())
												.add(Pair.of(BehaviorBuilder.triggerIf(e -> true), 1))
												.build()
								)
						)
				),
				MemoryModuleType.RIDE_TARGET
		);
	}

	private static ImmutableList<Pair<OneShot<LivingEntity>, Integer>> createLookBehaviors() {
		return ImmutableList.of(
				Pair.of(SetEntityLookTarget.create(EntityTypes.PLAYER, 8.0F), 1),
				Pair.of(SetEntityLookTarget.create(EntityTypes.PIGLIN, 8.0F), 1),
				Pair.of(SetEntityLookTarget.create(8.0F), 1)
		);
	}

	private static RunOne<LivingEntity> createIdleLookBehaviors() {
		return new RunOne<>(
				ImmutableList.<Pair<? extends BehaviorControl<? super LivingEntity>, Integer>>builder()
						.addAll(createLookBehaviors())
						.add(Pair.of(new DoNothing(30, 60), 1))
						.build()
		);
	}

	private static RunOne<FukumameThrower> createIdleMovementBehaviors() {
		return new RunOne<>(
				ImmutableList.of(
						Pair.of(RandomStroll.stroll(0.6F), 2),
						Pair.of(InteractWith.of(EntityTypes.PIGLIN, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2),
						Pair.of(BehaviorBuilder.triggerIf(FukumameThrowerAi::doesntSeeAnyPlayerHoldingLovedItem, SetWalkTargetFromLookTarget.create(0.6F, 3)), 2),
						Pair.of(new DoNothing(30, 60), 1)
				)
		);
	}

	private static BehaviorControl<PathfinderMob> avoidRepellent() {
		return SetWalkTargetAwayFrom.pos(MemoryModuleType.NEAREST_REPELLENT, 1.0F, 8, false);
	}

	private static BehaviorControl<FukumameThrower> babyAvoidNemesis() {
		return CopyMemoryWithExpiry.create(FukumameThrower::isBaby, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.AVOID_TARGET, BABY_AVOID_NEMESIS_DURATION);
	}

	private static BehaviorControl<FukumameThrower> avoidZombified() {
		return CopyMemoryWithExpiry.create(
				FukumameThrowerAi::isNearZombified, MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, MemoryModuleType.AVOID_TARGET, AVOID_ZOMBIFIED_DURATION
		);
	}


	protected static void updateActivity(FukumameThrower body) {
		Brain<FukumameThrower> brain = body.getBrain();
		Activity oldActivity = (Activity) brain.getActiveNonCoreActivity().orElse(null);
		brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.ADMIRE_ITEM, Activity.FIGHT, Activity.AVOID, Activity.CELEBRATE, Activity.RIDE, Activity.IDLE));
		Activity newActivity = (Activity) brain.getActiveNonCoreActivity().orElse(null);
		if (oldActivity != newActivity) {
			Optional<SoundEvent> var10000 = getSoundForCurrentActivity(body);
			Objects.requireNonNull(body);
			var10000.ifPresent(body::makeSound);
		}

		body.setAggressive(brain.hasMemoryValue(MemoryModuleType.ATTACK_TARGET));

	}

	public static Optional<SoundEvent> getSoundForCurrentActivity(FukumameThrower body) {
		return body.getBrain().getActiveNonCoreActivity().map(activity -> getSoundForActivity(body, activity));
	}

	private static SoundEvent getSoundForActivity(FukumameThrower body, Activity activity) {
		if (activity == Activity.FIGHT) {
			return SoundEvents.PIGLIN_ANGRY;
		} else if (body.isConverting()) {
			return SoundEvents.PIGLIN_RETREAT;
		} else if (activity == Activity.AVOID && isNearAvoidTarget(body)) {
			return SoundEvents.PIGLIN_RETREAT;
		} else {
			return isNearRepellent(body) ? SoundEvents.PIGLIN_RETREAT : SoundEvents.PIGLIN_AMBIENT;
		}
	}

	private static boolean isNearAvoidTarget(FukumameThrower body) {
		Brain<FukumameThrower> brain = body.getBrain();
		return !brain.hasMemoryValue(MemoryModuleType.AVOID_TARGET) ? false : brain.getMemory(MemoryModuleType.AVOID_TARGET).get().closerThan(body, 12.0);
	}

	private static boolean isNearestValidAttackTarget(ServerLevel level, FukumameThrower body, LivingEntity target) {
		return findNearestValidAttackTarget(level, body).filter(nearestValidTarget -> nearestValidTarget == target).isPresent();
	}

	private static boolean isNearZombified(FukumameThrower body) {
		Brain<FukumameThrower> brain = body.getBrain();
		if (brain.hasMemoryValue(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED)) {
			LivingEntity zombified = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED).get();
			return body.closerThan(zombified, 6.0);
		} else {
			return false;
		}
	}

	private static Optional<? extends LivingEntity> findNearestValidAttackTarget(ServerLevel level, FukumameThrower body) {
		Brain<FukumameThrower> brain = body.getBrain();
		if (isNearZombified(body)) {
			return Optional.empty();
		} else {
			Optional<LivingEntity> angryAt = BehaviorUtils.getLivingEntityFromUUIDMemory(body, MemoryModuleType.ANGRY_AT);
			if (angryAt.isPresent() && Sensor.isEntityAttackableIgnoringLineOfSight(level, body, angryAt.get())) {
				return angryAt;
			} else {
				if (brain.hasMemoryValue(MemoryModuleType.UNIVERSAL_ANGER)) {
					Optional<Player> player = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER);
					if (player.isPresent()) {
						return player;
					}
				}

				Optional<Mob> nemesis = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_NEMESIS);
				if (nemesis.isPresent()) {
					return nemesis;
				} else {
					Optional<Player> playerNotWearingGold = brain.getMemory(MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD);
					return playerNotWearingGold.isPresent() && Sensor.isEntityAttackable(level, body, playerNotWearingGold.get())
							? playerNotWearingGold
							: Optional.empty();
				}
			}
		}
	}


	private static boolean isFood(ItemStack itemStack) {
		return itemStack.is(ItemTags.PIGLIN_FOOD);
	}

	private static boolean isNearRepellent(FukumameThrower body) {
		return body.getBrain().hasMemoryValue(MemoryModuleType.NEAREST_REPELLENT);
	}

	private static boolean seesPlayerHoldingLovedItem(LivingEntity body) {
		return body.getBrain().hasMemoryValue(MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM);
	}

	private static boolean doesntSeeAnyPlayerHoldingLovedItem(LivingEntity body) {
		return !seesPlayerHoldingLovedItem(body);
	}


	private static boolean isAdmiringDisabled(Piglin body) {
		return body.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_DISABLED);
	}

	private static boolean wasHurtRecently(LivingEntity body) {
		return body.getBrain().hasMemoryValue(MemoryModuleType.HURT_BY);
	}

	private static boolean wantsToDance(LivingEntity body, LivingEntity killedTarget) {
		return !killedTarget.is(EntityTypes.HOGLIN) ? false : RandomSource.create(body.level().getGameTime()).nextFloat() < 0.1F;
	}

	private static boolean wantsToStopFleeing(FukumameThrower body) {
		Brain<FukumameThrower> brain = body.getBrain();
		if (!brain.hasMemoryValue(MemoryModuleType.AVOID_TARGET)) {
			return true;
		} else {
			LivingEntity avoidedEntity = brain.getMemory(MemoryModuleType.AVOID_TARGET).get();
			if (avoidedEntity.is(EntityTypes.HOGLIN)) {
				return piglinsEqualOrOutnumberHoglins(body);
			} else {
				return isZombified(avoidedEntity) ? !brain.isMemoryValue(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, avoidedEntity) : false;
			}
		}
	}

	public static boolean isZombified(Entity entity) {
		return entity.is(EntityTypes.ZOMBIFIED_PIGLIN) || entity.is(EntityTypes.ZOGLIN);
	}


	private static boolean piglinsEqualOrOutnumberHoglins(FukumameThrower body) {
		return !hoglinsOutnumberPiglins(body);
	}

	private static boolean hoglinsOutnumberPiglins(FukumameThrower body) {
		int piglinCount = body.getBrain().getMemory(MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT).orElse(0) + 1;
		int hoglinCount = body.getBrain().getMemory(MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT).orElse(0);
		return hoglinCount > piglinCount;
	}
}

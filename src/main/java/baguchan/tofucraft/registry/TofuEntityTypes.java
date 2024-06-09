package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.FukumameThower;
import baguchan.tofucraft.entity.ShuDofuSpider;
import baguchan.tofucraft.entity.TFShulker;
import baguchan.tofucraft.entity.TofuBoat;
import baguchan.tofucraft.entity.TofuChestBoat;
import baguchan.tofucraft.entity.TofuCow;
import baguchan.tofucraft.entity.TofuCreeper;
import baguchan.tofucraft.entity.TofuFish;
import baguchan.tofucraft.entity.TofuGandlem;
import baguchan.tofucraft.entity.TofuGolem;
import baguchan.tofucraft.entity.TofuPig;
import baguchan.tofucraft.entity.TofuSlime;
import baguchan.tofucraft.entity.TofuSpider;
import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.entity.TravelerTofunian;
import baguchan.tofucraft.entity.Zundamite;
import baguchan.tofucraft.entity.effect.NattoCobWebEntity;
import baguchan.tofucraft.entity.projectile.FallingTofuEntity;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NattoBallEntity;
import baguchan.tofucraft.entity.projectile.NattoStringEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import baguchan.tofucraft.entity.projectile.TFShulkerBullet;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TofuCraftReload.MODID);

	public static final Supplier<EntityType<Tofunian>> TOFUNIAN = ENTITIES.register("tofunian", () -> EntityType.Builder.of(Tofunian::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).eyeHeight(1.2F * 0.8F).clientTrackingRange(10).build("tofucraft:tofunian"));

	public static final Supplier<EntityType<TravelerTofunian>> TRAVELER_TOFUNIAN = ENTITIES.register("traveler_tofunian", () -> EntityType.Builder.of(TravelerTofunian::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).eyeHeight(1.2F * 0.8F).build("tofucraft:traveler_tofunian"));

	public static final Supplier<EntityType<TofuCow>> TOFUCOW = ENTITIES.register("tofucow", () -> EntityType.Builder.of(TofuCow::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).eyeHeight(1.4F * 0.8F).build("tofucraft:tofucow"));

	public static final Supplier<EntityType<TofuPig>> TOFUPIG = ENTITIES.register("tofupig", () -> EntityType.Builder.of(TofuPig::new, MobCategory.CREATURE)
			.sized(0.9F, 0.9F).eyeHeight(0.9F * 0.8F).build("tofucraft:tofupig"));

	public static final Supplier<EntityType<TofuFish>> TOFUFISH = ENTITIES.register("tofufish", () -> EntityType.Builder.of(TofuFish::new, MobCategory.WATER_AMBIENT)
			.sized(0.5F, 0.35F).eyeHeight(0.3F).setTrackingRange(4).build("tofucraft:tofufish"));

	public static final Supplier<EntityType<TofuGolem>> TOFU_GOLEM = ENTITIES.register("tofu_golem", () -> EntityType.Builder.of(TofuGolem::new, MobCategory.MISC)
			.sized(0.8F, 1.25F).eyeHeight(1.25F * 0.8F).clientTrackingRange(10).fireImmune().build("tofucraft:tofu_golem"));

	public static final Supplier<EntityType<TofuGandlem>> TOFU_GANDLEM = ENTITIES.register("tofu_gandlem", () -> EntityType.Builder.of(TofuGandlem::new, MobCategory.CREATURE)
			.sized(0.6F, 1.6F).eyeHeight(1.6F * 0.8F).clientTrackingRange(10).fireImmune().build("tofucraft:tofu_gandlem"));


	public static final Supplier<EntityType<TofuSlime>> TOFUSLIME = ENTITIES.register("tofuslime", () -> EntityType.Builder.of(TofuSlime::new, MobCategory.MONSTER)
			.sized(0.52F, 0.52F).eyeHeight(0.325F).build("tofucraft:tofuslime"));
	public static final Supplier<EntityType<TofuCreeper>> TOFUCREEPER = ENTITIES.register("tofucreeper", () -> EntityType.Builder.of(TofuCreeper::new, MobCategory.MONSTER)
			.sized(0.6F, 1.6F).build("tofucraft:tofucreeper"));

	public static final Supplier<EntityType<TofuSpider>> TOFUSPIDER = ENTITIES.register("tofuspider", () -> EntityType.Builder.of(TofuSpider::new, MobCategory.MONSTER)
			.sized(0.95F, 0.55F).eyeHeight(0.3F).build("tofucraft:tofuspider"));
	public static final Supplier<EntityType<Zundamite>> ZUNDAMITE = ENTITIES.register("zundamite", () -> EntityType.Builder.of(Zundamite::new, MobCategory.MONSTER)
			.sized(0.4F, 0.3F).eyeHeight(0.15F).clientTrackingRange(8).build("tofucraft:zundamite"));

	public static final Supplier<EntityType<FukumameEntity>> FUKUMAME = ENTITIES.register("fukumame", () -> EntityType.Builder.<FukumameEntity>of(FukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(30).build("tofucraft:fukumame"));

	public static final Supplier<EntityType<NetherFukumameEntity>> NETHER_FUKUMAME = ENTITIES.register("nether_fukumame", () -> EntityType.Builder.<NetherFukumameEntity>of(NetherFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(30).build("tofucraft:nether_fukumame"));

	public static final Supplier<EntityType<SoulFukumameEntity>> SOUL_FUKUMAME = ENTITIES.register("soul_fukumame", () -> EntityType.Builder.<SoulFukumameEntity>of(SoulFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(30).build("tofucraft:soul_fukumame"));

	public static final Supplier<EntityType<NattoStringEntity>> NATTO_STRNIG = ENTITIES.register("natto_string", () -> EntityType.Builder.<NattoStringEntity>of(NattoStringEntity::new, MobCategory.MISC)
			.sized(0.2F, 0.2F).updateInterval(40).build("tofucraft:natto_string"));

	public static final Supplier<EntityType<ZundaArrow>> ZUNDA_ARROW = ENTITIES.register("zunda_arrow", () -> EntityType.Builder.<ZundaArrow>of(ZundaArrow::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).updateInterval(40).build("tofucraft:zunda_arrow"));


	public static final Supplier<EntityType<NattoCobWebEntity>> NATTO_COBWEB = ENTITIES.register("natto_cobweb", () -> EntityType.Builder.<NattoCobWebEntity>of(NattoCobWebEntity::new, MobCategory.MISC)
			.sized(3F, 0.1F).updateInterval(10).fireImmune().build("tofucraft:natto_cobweb"));

	public static final Supplier<EntityType<NattoBallEntity>> NATTO_BALL = ENTITIES.register("natto_ball", () -> EntityType.Builder.<NattoBallEntity>of(NattoBallEntity::new, MobCategory.MISC)
			.sized(0.35F, 0.35F).updateInterval(20).build("tofucraft:natto_ball"));

	public static final Supplier<EntityType<FallingTofuEntity>> FALLING_TOFU = ENTITIES.register("falling_tofu", () -> EntityType.Builder.<FallingTofuEntity>of(FallingTofuEntity::new, MobCategory.MISC)
			.sized(1.0F, 1.0F).updateInterval(20).build("tofucraft:falling_tofu"));

	public static final Supplier<EntityType<TofuBoat>> TOFU_BOAT = ENTITIES.register("tofu_boat", () -> EntityType.Builder.<TofuBoat>of(TofuBoat::new, MobCategory.MISC)
			.sized(1.375F, 0.5625F).clientTrackingRange(10).updateInterval(3).build("tofucraft:tofu_boat"));
	public static final Supplier<EntityType<TofuChestBoat>> TOFU_CHEST_BOAT = ENTITIES.register("tofu_chest_boat", () -> EntityType.Builder.<TofuChestBoat>of(TofuChestBoat::new, MobCategory.MISC)
			.sized(1.375F, 0.5625F).clientTrackingRange(10).updateInterval(3).build("tofucraft:tofu_chest_boat"));


	public static final Supplier<EntityType<ShuDofuSpider>> SHUDOFUSPIDER = ENTITIES.register("shudofuspider", () -> EntityType.Builder.of(ShuDofuSpider::new, MobCategory.CREATURE)
			.sized(3.5F, 2.9F).eyeHeight(2.0F).clientTrackingRange(10).fireImmune().build("tofucraft:shudofuspider"));

	public static final Supplier<EntityType<FukumameThower>> FUKUMAME_THOWER = ENTITIES.register("fukumame_thower", () -> EntityType.Builder.of(FukumameThower::new, MobCategory.MONSTER).sized(0.6F, 1.85F).clientTrackingRange(8).build("tofucraft:fukumame_thower"));
	public static final Supplier<EntityType<TFShulker>> TF_SHULKER = ENTITIES.register("tf_shulker", () -> EntityType.Builder.of(TFShulker::new, MobCategory.CREATURE).sized(1F, 1F).clientTrackingRange(8).build("tofucraft:tf_shulker"));
	public static final Supplier<EntityType<TFShulkerBullet>> TF_SHULKER_BULLET = ENTITIES.register("tf_shulker_bullet", () -> EntityType.Builder.<TFShulkerBullet>of(TFShulkerBullet::new, MobCategory.MISC).sized(0.3125F, 0.3125F).clientTrackingRange(8).build("tofucraft:tf_shulker_bullet"));

	public static final SpawnPlacementType IN_SOYMILK = (p_325672_, p_325673_, p_325674_) -> {
		if (p_325674_ != null && p_325672_.getWorldBorder().isWithinBounds(p_325673_)) {
			BlockPos blockpos = p_325673_.above();
			return p_325672_.getFluidState(p_325673_).is(TofuTags.Fluids.SOYMILK) && !p_325672_.getBlockState(blockpos).isRedstoneConductor(p_325672_, blockpos);
		} else {
			return false;
		}
	};
	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(TOFUCOW.get(), TofuCow.createAttributes().build());
		event.put(TOFUPIG.get(), TofuPig.createAttributes().build());
		event.put(TOFUNIAN.get(), Tofunian.createAttributes().build());
		event.put(TRAVELER_TOFUNIAN.get(), Tofunian.createAttributes().build());
		event.put(TOFUFISH.get(), AbstractFish.createAttributes().build());
		event.put(TOFU_GOLEM.get(), TofuGolem.createAttributes().build());
		event.put(TOFUSLIME.get(), Monster.createMonsterAttributes().build());
		event.put(TOFUCREEPER.get(), TofuCreeper.createAttributes().build());
		event.put(TOFUSPIDER.get(), TofuSpider.createAttributes().build());
		event.put(ZUNDAMITE.get(), Zundamite.createAttributes().build());
		event.put(TOFU_GANDLEM.get(), TofuGandlem.createAttributes().build());
		event.put(SHUDOFUSPIDER.get(), ShuDofuSpider.createAttributes().build());
		event.put(NATTO_COBWEB.get(), NattoCobWebEntity.createAttributes().build());
		event.put(FUKUMAME_THOWER.get(), FukumameThower.createAttributes().build());
		event.put(TF_SHULKER.get(), Shulker.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerEntityAttribute(SpawnPlacementRegisterEvent event) {
		event.register(TOFUCOW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuCow::checkTofuAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFUPIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuPig::checkTofuAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFUNIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TRAVELER_TOFUNIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFU_GOLEM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

		event.register(TOFUSLIME.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSlime::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFUCREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuCreeper::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFUSPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSpider::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFUFISH.get(), IN_SOYMILK, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuFish::checkTofuFishSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TOFU_GANDLEM.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

		event.register(FUKUMAME_THOWER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FukumameThower::checkFukumameSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(ZUNDAMITE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TF_SHULKER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}
}

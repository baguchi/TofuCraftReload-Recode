package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.ShuDofuSpider;
import baguchan.tofucraft.entity.TofuCow;
import baguchan.tofucraft.entity.TofuFish;
import baguchan.tofucraft.entity.TofuGandlem;
import baguchan.tofucraft.entity.TofuGolem;
import baguchan.tofucraft.entity.TofuPig;
import baguchan.tofucraft.entity.TofuSlime;
import baguchan.tofucraft.entity.TofuSpider;
import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.entity.TravelerTofunian;
import baguchan.tofucraft.entity.effect.NattoCobWebEntity;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NattoBallEntity;
import baguchan.tofucraft.entity.projectile.NattoStringEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TofuCraftReload.MODID);

	public static final RegistryObject<EntityType<Tofunian>> TOFUNIAN = ENTITIES.register("tofunian", () -> EntityType.Builder.of(Tofunian::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).clientTrackingRange(10).build("tofucraft:tofunian"));

	public static final RegistryObject<EntityType<TravelerTofunian>> TRAVELER_TOFUNIAN = ENTITIES.register("traveler_tofunian", () -> EntityType.Builder.of(TravelerTofunian::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).build("tofucraft:traveler_tofunian"));

	public static final RegistryObject<EntityType<TofuCow>> TOFUCOW = ENTITIES.register("tofucow", () -> EntityType.Builder.of(TofuCow::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).build("tofucraft:tofucow"));

	public static final RegistryObject<EntityType<TofuPig>> TOFUPIG = ENTITIES.register("tofupig", () -> EntityType.Builder.of(TofuPig::new, MobCategory.CREATURE)
			.sized(0.9F, 0.9F).build("tofucraft:tofupig"));

	public static final RegistryObject<EntityType<TofuFish>> TOFUFISH = ENTITIES.register("tofufish", () -> EntityType.Builder.of(TofuFish::new, MobCategory.WATER_AMBIENT)
			.sized(0.5F, 0.35F).setTrackingRange(4).build("tofucraft:tofufish"));
	public static final RegistryObject<EntityType<TofuGolem>> TOFU_GOLEM = ENTITIES.register("tofu_golem", () -> EntityType.Builder.of(TofuGolem::new, MobCategory.MISC)
			.sized(0.8F, 1.25F).clientTrackingRange(10).fireImmune().build("tofucraft:tofu_golem"));


	public static final RegistryObject<EntityType<TofuGandlem>> TOFU_GANDLEM = ENTITIES.register("tofu_gandlem", () -> EntityType.Builder.of(TofuGandlem::new, MobCategory.CREATURE)
			.sized(0.6F, 1.6F).clientTrackingRange(10).fireImmune().build("tofucraft:tofu_gandlem"));


	public static final RegistryObject<EntityType<TofuSlime>> TOFUSLIME = ENTITIES.register("tofuslime", () -> EntityType.Builder.of(TofuSlime::new, MobCategory.MONSTER)
			.sized(2.04F, 2.04F).build("tofucraft:tofuslime"));

	public static final RegistryObject<EntityType<TofuSpider>> TOFUSPIDER = ENTITIES.register("tofuspider", () -> EntityType.Builder.of(TofuSpider::new, MobCategory.MONSTER)
			.sized(0.95F, 0.55F).build("tofucraft:tofuspider"));

	public static final RegistryObject<EntityType<FukumameEntity>> FUKUMAME = ENTITIES.register("fukumame", () -> EntityType.Builder.<FukumameEntity>of(FukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(20).build("tofucraft:fukumame"));

	public static final RegistryObject<EntityType<NetherFukumameEntity>> NETHER_FUKUMAME = ENTITIES.register("nether_fukumame", () -> EntityType.Builder.<NetherFukumameEntity>of(NetherFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(20).build("tofucraft:nether_fukumame"));

	public static final RegistryObject<EntityType<SoulFukumameEntity>> SOUL_FUKUMAME = ENTITIES.register("soul_fukumame", () -> EntityType.Builder.<SoulFukumameEntity>of(SoulFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(20).build("tofucraft:soul_fukumame"));

	public static final RegistryObject<EntityType<NattoStringEntity>> NATTO_STRNIG = ENTITIES.register("natto_string", () -> EntityType.Builder.<NattoStringEntity>of(NattoStringEntity::new, MobCategory.MISC)
			.sized(0.2F, 0.2F).updateInterval(20).build("tofucraft:natto_string"));

	public static final RegistryObject<EntityType<ZundaArrow>> ZUNDA_ARROW = ENTITIES.register("zunda_arrow", () -> EntityType.Builder.<ZundaArrow>of(ZundaArrow::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).updateInterval(20).build("tofucraft:zunda_arrow"));


	public static final RegistryObject<EntityType<NattoCobWebEntity>> NATTO_COBWEB = ENTITIES.register("natto_cobweb", () -> EntityType.Builder.<NattoCobWebEntity>of(NattoCobWebEntity::new, MobCategory.MISC)
			.sized(3F, 0.1F).build("tofucraft:natto_cobweb"));

	public static final RegistryObject<EntityType<NattoBallEntity>> NATTO_BALL = ENTITIES.register("natto_ball", () -> EntityType.Builder.<NattoBallEntity>of(NattoBallEntity::new, MobCategory.MISC)
			.sized(0.35F, 0.35F).build("tofucraft:natto_ball"));

	public static final RegistryObject<EntityType<ShuDofuSpider>> SHUDOFUSPIDER = ENTITIES.register("shudofuspider", () -> EntityType.Builder.of(ShuDofuSpider::new, MobCategory.CREATURE)
			.sized(3.5F, 2.9F).clientTrackingRange(10).fireImmune().build("tofucraft:shudofuspider"));

	/*public static final EntityType<ZundaArrowEntity> ZUNDA_ARROW = EntityType.Builder.<ZundaArrowEntity>of(ZundaArrowEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).build("tofucraft:zunda_arrow");*/

	public static final SpawnPlacements.Type IN_SOYMILK = SpawnPlacements.Type.create("in_soymilk", (levelReader, pos, entityType) -> {
		return levelReader.getFluidState(pos).is(TofuTags.Fluids.SOYMILK);
	});

	public static final BlockPathTypes UNSTABLE_TOFU_PATH = BlockPathTypes.create("tofucraft:unstable_tofu", 4.0F);

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(TOFUCOW.get(), TofuCow.createAttributes().build());
		event.put(TOFUPIG.get(), TofuPig.createAttributes().build());
		event.put(TOFUNIAN.get(), Tofunian.createAttributes().build());
		event.put(TRAVELER_TOFUNIAN.get(), Tofunian.createAttributes().build());
		event.put(TOFUFISH.get(), AbstractFish.createAttributes().build());
		event.put(TOFU_GOLEM.get(), TofuGolem.createAttributes().build());
		event.put(TOFUSLIME.get(), Monster.createMonsterAttributes().build());
		event.put(TOFUSPIDER.get(), TofuSpider.createAttributes().build());
		event.put(TOFU_GANDLEM.get(), TofuGandlem.createAttributes().build());
		event.put(SHUDOFUSPIDER.get(), ShuDofuSpider.createAttributes().build());
		event.put(NATTO_COBWEB.get(), NattoCobWebEntity.createAttributes().build());

		SpawnPlacements.register(TOFUCOW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuCow::checkTofuAnimalSpawnRules);
		SpawnPlacements.register(TOFUPIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuPig::checkTofuAnimalSpawnRules);
		SpawnPlacements.register(TOFUNIAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
		SpawnPlacements.register(TRAVELER_TOFUNIAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
		SpawnPlacements.register(TOFU_GOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
		SpawnPlacements.register(TOFUSLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSlime::checkMonsterSpawnRules);
		SpawnPlacements.register(TOFUSPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSpider::checkMonsterSpawnRules);
		SpawnPlacements.register(TOFUFISH.get(), IN_SOYMILK, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuFish::checkTofuFishSpawnRules);
		SpawnPlacements.register(TOFU_GANDLEM.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}
}

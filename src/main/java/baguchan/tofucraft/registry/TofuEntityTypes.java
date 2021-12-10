package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.*;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuEntityTypes {
	public static final EntityType<TofunianEntity> TOFUNIAN = EntityType.Builder.of(TofunianEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).build("tofucraft:tofunian");

	/*public static final EntityType<TravelerTofunianEntity> TRAVELER_TOFUNIAN = EntityType.Builder.of(TravelerTofunianEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).build("tofucraft:traveler_tofunian");*/

	public static final EntityType<TofuCowEntity> TOFUCOW = EntityType.Builder.of(TofuCowEntity::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).build("tofucraft:tofucow");

	public static final EntityType<TofuFishEntity> TOFUFISH = EntityType.Builder.of(TofuFishEntity::new, MobCategory.WATER_AMBIENT)
			.sized(0.5F, 0.3F).setTrackingRange(4).build("tofucraft:tofufish");

	public static final EntityType<TofuSlimeEntity> TOFUSLIME = EntityType.Builder.of(TofuSlimeEntity::new, MobCategory.MONSTER)
			.sized(2.04F, 2.04F).build("tofucraft:tofuslime");

	public static final EntityType<TofuSpiderEntity> TOFUSPIDER = EntityType.Builder.of(TofuSpiderEntity::new, MobCategory.MONSTER)
			.sized(0.95F, 0.55F).build("tofucraft:tofuspider");

	public static final EntityType<FukumameEntity> FUKUMAME = EntityType.Builder.<FukumameEntity>of(FukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("tofucraft:fukumame");

	public static final EntityType<NetherFukumameEntity> NETHER_FUKUMAME = EntityType.Builder.<NetherFukumameEntity>of(NetherFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("tofucraft:nether_fukumame");

	public static final EntityType<SoulFukumameEntity> SOUL_FUKUMAME = EntityType.Builder.<SoulFukumameEntity>of(SoulFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("tofucraft:soul_fukumame");

	/*public static final EntityType<ZundaArrowEntity> ZUNDA_ARROW = EntityType.Builder.<ZundaArrowEntity>of(ZundaArrowEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).build("tofucraft:zunda_arrow");*/

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> registry) {
		registry.getRegistry().register(TOFUCOW.setRegistryName("tofucow"));
		registry.getRegistry().register(TOFUNIAN.setRegistryName("tofunian"));
		registry.getRegistry().register(TOFUFISH.setRegistryName("tofufish"));
		registry.getRegistry().register(TOFUSLIME.setRegistryName("tofuslime"));
		registry.getRegistry().register(TOFUSPIDER.setRegistryName("tofuspider"));

		SpawnPlacements.register(TOFUCOW, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuCowEntity::checkTofuAnimalSpawnRules);
		SpawnPlacements.register(TOFUNIAN, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
		SpawnPlacements.register(TOFUSLIME, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSlimeEntity::checkMonsterSpawnRules);
		SpawnPlacements.register(TOFUSPIDER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSpiderEntity::checkMonsterSpawnRules);
		SpawnPlacements.register(TOFUFISH, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuFishEntity::checkTofuFishSpawnRules);


		registry.getRegistry().register(FUKUMAME.setRegistryName("fukumame"));
		registry.getRegistry().register(NETHER_FUKUMAME.setRegistryName("nether_fukumame"));
		registry.getRegistry().register(SOUL_FUKUMAME.setRegistryName("soul_fukumame"));
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(TOFUCOW, TofuCowEntity.createAttributes().build());
		event.put(TOFUNIAN, TofunianEntity.createAttributes().build());
		event.put(TOFUFISH, AbstractFish.createAttributes().build());
		event.put(TOFUSLIME, Monster.createMonsterAttributes().build());
		event.put(TOFUSPIDER, TofuSpiderEntity.createAttributes().build());
	}
}

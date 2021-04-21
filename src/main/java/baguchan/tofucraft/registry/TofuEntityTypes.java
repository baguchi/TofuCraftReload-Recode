package baguchan.tofucraft.registry;

import baguchan.tofucraft.entity.TofuCowEntity;
import baguchan.tofucraft.entity.TofuFishEntity;
import baguchan.tofucraft.entity.TofuSlimeEntity;
import baguchan.tofucraft.entity.TofuSpiderEntity;
import baguchan.tofucraft.entity.TofunianEntity;
import baguchan.tofucraft.entity.TravelerTofunianEntity;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import baguchan.tofucraft.entity.projectile.ZundaArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuEntityTypes {
	public static final EntityType<TofunianEntity> TOFUNIAN = EntityType.Builder.func_220322_a(TofunianEntity::new, EntityClassification.CREATURE)
			.func_220321_a(0.6F, 1.2F).func_206830_a("tofucraft:tofunian");

	public static final EntityType<TravelerTofunianEntity> TRAVELER_TOFUNIAN = EntityType.Builder.func_220322_a(TravelerTofunianEntity::new, EntityClassification.CREATURE)
			.func_220321_a(0.6F, 1.2F).func_206830_a("tofucraft:traveler_tofunian");

	public static final EntityType<TofuCowEntity> TOFUCOW = EntityType.Builder.func_220322_a(TofuCowEntity::new, EntityClassification.CREATURE)
			.func_220321_a(0.9F, 1.4F).func_206830_a("tofucraft:tofucow");

	public static final EntityType<TofuFishEntity> TOFUFISH = EntityType.Builder.func_220322_a(TofuFishEntity::new, EntityClassification.WATER_AMBIENT)
			.func_220321_a(0.5F, 0.3F).setTrackingRange(4).func_206830_a("tofucraft:tofufish");

	public static final EntityType<TofuSlimeEntity> TOFUSLIME = EntityType.Builder.func_220322_a(TofuSlimeEntity::new, EntityClassification.MONSTER)
			.func_220321_a(2.04F, 2.04F).func_206830_a("tofucraft:tofuslime");

	public static final EntityType<TofuSpiderEntity> TOFUSPIDER = EntityType.Builder.func_220322_a(TofuSpiderEntity::new, EntityClassification.MONSTER)
			.func_220321_a(0.95F, 0.55F).func_206830_a("tofucraft:tofuspider");

	public static final EntityType<FukumameEntity> FUKUMAME = EntityType.Builder.func_220322_a(FukumameEntity::new, EntityClassification.MISC)
			.func_220321_a(0.25F, 0.25F).func_206830_a("tofucraft:fukumame");

	public static final EntityType<NetherFukumameEntity> NETHER_FUKUMAME = EntityType.Builder.func_220322_a(NetherFukumameEntity::new, EntityClassification.MISC)
			.func_220321_a(0.25F, 0.25F).func_206830_a("tofucraft:nether_fukumame");

	public static final EntityType<SoulFukumameEntity> SOUL_FUKUMAME = EntityType.Builder.func_220322_a(SoulFukumameEntity::new, EntityClassification.MISC)
			.func_220321_a(0.25F, 0.25F).func_206830_a("tofucraft:soul_fukumame");

	public static final EntityType<ZundaArrowEntity> ZUNDA_ARROW = EntityType.Builder.func_220322_a(ZundaArrowEntity::new, EntityClassification.MISC)
			.func_220321_a(0.5F, 0.5F).func_206830_a("tofucraft:zunda_arrow");

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> registry) {
		registry.getRegistry().register(TOFUNIAN.setRegistryName("tofunian"));
		registry.getRegistry().register(TRAVELER_TOFUNIAN.setRegistryName("traveler_tofunian"));
		registry.getRegistry().register(TOFUCOW.setRegistryName("tofucow"));
		registry.getRegistry().register(TOFUFISH.setRegistryName("tofufish"));
		registry.getRegistry().register(TOFUSLIME.setRegistryName("tofuslime"));
		registry.getRegistry().register(TOFUSPIDER.setRegistryName("tofuspider"));
		GlobalEntityTypeAttributes.put(TOFUNIAN, TofunianEntity.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(TRAVELER_TOFUNIAN, TravelerTofunianEntity.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(TOFUCOW, TofuCowEntity.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(TOFUFISH, AbstractFishEntity.func_234176_m_().func_233813_a_());
		GlobalEntityTypeAttributes.put(TOFUSLIME, MonsterEntity.func_234295_eP_().func_233813_a_());
		GlobalEntityTypeAttributes.put(TOFUSPIDER, SpiderEntity.func_234305_eI_().func_233813_a_());
		EntitySpawnPlacementRegistry.func_209343_a(TOFUNIAN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
		EntitySpawnPlacementRegistry.func_209343_a(TRAVELER_TOFUNIAN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
		EntitySpawnPlacementRegistry.func_209343_a(TOFUCOW, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TofuCowEntity::canTofuCowSpawn);
		EntitySpawnPlacementRegistry.func_209343_a(TOFUFISH, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TofuFishEntity::canTofuFishSpawnOn);
		EntitySpawnPlacementRegistry.func_209343_a(TOFUSLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TofuSlimeEntity::checkTofuSlimeSpawnRules);
		EntitySpawnPlacementRegistry.func_209343_a(TOFUSPIDER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);
		registry.getRegistry().register(FUKUMAME.setRegistryName("fukumame"));
		registry.getRegistry().register(NETHER_FUKUMAME.setRegistryName("nether_fukumame"));
		registry.getRegistry().register(SOUL_FUKUMAME.setRegistryName("soul_fukumame"));
		registry.getRegistry().register(ZUNDA_ARROW.setRegistryName("zunda_arrow"));
	}
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuEntityTypes {
	/*public static final EntityType<TofunianEntity> TOFUNIAN = EntityType.Builder.of(TofunianEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).build("tofucraft:tofunian");
			*/
	/*public static final EntityType<TravelerTofunianEntity> TRAVELER_TOFUNIAN = EntityType.Builder.of(TravelerTofunianEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).build("tofucraft:traveler_tofunian");

	public static final EntityType<TofuCowEntity> TOFUCOW = EntityType.Builder.of(TofuCowEntity::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).build("tofucraft:tofucow");

	public static final EntityType<TofuFishEntity> TOFUFISH = EntityType.Builder.of(TofuFishEntity::new, MobCategory.WATER_AMBIENT)
			.sized(0.5F, 0.3F).setTrackingRange(4).build("tofucraft:tofufish");

	public static final EntityType<TofuSlimeEntity> TOFUSLIME = EntityType.Builder.of(TofuSlimeEntity::new, MobCategory.MONSTER)
			.sized(2.04F, 2.04F).build("tofucraft:tofuslime");

	public static final EntityType<TofuSpiderEntity> TOFUSPIDER = EntityType.Builder.of(TofuSpiderEntity::new, MobCategory.MONSTER)
			.sized(0.95F, 0.55F).build("tofucraft:tofuspider");*/

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
		registry.getRegistry().register(FUKUMAME.setRegistryName("fukumame"));
		registry.getRegistry().register(NETHER_FUKUMAME.setRegistryName("nether_fukumame"));
		registry.getRegistry().register(SOUL_FUKUMAME.setRegistryName("soul_fukumame"));
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
	}
}

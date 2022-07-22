package baguchan.tofucraft;

import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuPoiTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillageSiegeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;

import java.util.Optional;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class CommonEvents {

	@SubscribeEvent
	public static void onRegisterEntityCapabilities(RegisterCapabilitiesEvent event) {
		event.register(SoyHealthCapability.class);
		event.register(TofuLivingCapability.class);
	}

	@SubscribeEvent
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity) {
			event.addCapability(new ResourceLocation(TofuCraftReload.MODID, "soy_health"), new SoyHealthCapability());
			event.addCapability(new ResourceLocation(TofuCraftReload.MODID, "tofu_living"), new TofuLivingCapability());
		}
	}

	@SubscribeEvent
	public static void onEntitySpawn(LivingSpawnEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (!livingEntity.level.isClientSide())
			livingEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
				SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(livingEntity, cap.getSoyHealthLevel(), false);
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> livingEntity), message);
			});
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingTickEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (!livingEntity.level.isClientSide()) {
			livingEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
				cap.tick(livingEntity);
			});
		}
		livingEntity.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).ifPresent(tofuLivingCapability -> {
			tofuLivingCapability.tick(livingEntity);
		});
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Player player = event.getEntity();
		if (player instanceof ServerPlayer)
			player.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SoyMilkDrinkedMessage(player, handler.getSoyHealthLevel(), false)));
	}

	@SubscribeEvent
	public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player playerEntity = event.getEntity();
		playerEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerEntity), new SoyMilkDrinkedMessage(playerEntity, handler.getSoyHealthLevel(), false)));
	}

	/*@SubscribeEvent
	public static void onFluidPlaceEvent(BlockEvent.FluidPlaceBlockEvent event) {
		FluidState fluidState = event.getLevel().getFluidState(event.getLiquidPos());
		if (fluidState.getType() == TofuFluids.SOYMILK || fluidState.getType() == TofuFluids.SOYMILK_FLOW)
			event.setNewState(TofuBlocks.TOFU_TERRAIN.defaultBlockState());
	}*/

	/*private static final Map<ServerWorld, TravelerTofunianSpawner> TRAVELER_TOFUNIAN_SPAWNER_MAP = new HashMap<>();

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load evt) {
		if (!evt.getLevel().isClientSide() && evt.getLevel() instanceof ServerWorld)
			TRAVELER_TOFUNIAN_SPAWNER_MAP.put((ServerWorld) evt.getLevel(), new TravelerTofunianSpawner((ServerWorld) evt.getLevel()));
	}

	@SubscribeEvent
	public static void worldUnload(WorldEvent.Unload evt) {
		if (!evt.getLevel().isClientSide() && evt.getLevel() instanceof ServerWorld)
			TRAVELER_TOFUNIAN_SPAWNER_MAP.remove(evt.getLevel());
	}

	@SubscribeEvent
	public static void onServerTick(TickEvent.WorldTickEvent tick) {
		if (!tick.world.isClientSide && tick.world instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) tick.world;
			TravelerTofunianSpawner spawner = TRAVELER_TOFUNIAN_SPAWNER_MAP.get(serverWorld);
			if (spawner != null)
				spawner.tick();
		}
	}*/

	/*
	 * This Event make mob cannot spawn when morijio is near
	 *
	 * */
	@SubscribeEvent
	public static void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
		LivingEntity livingEntity = event.getEntity();
		LevelAccessor level = event.getLevel();
		if (livingEntity instanceof Enemy) {
			if (event.getSpawnReason() != MobSpawnType.SPAWNER && event.getSpawnReason() != MobSpawnType.EVENT && event.getSpawnReason() != MobSpawnType.BREEDING && event.getSpawnReason() != MobSpawnType.PATROL) {
				if (level instanceof ServerLevel) {
					Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
						return p_184069_.is(TofuPoiTypes.MORIJIO);
					}, (p_184055_) -> {
						return true;
					}, livingEntity.blockPosition(), 48, PoiManager.Occupancy.ANY);

					if (optional.isPresent()) {
						event.setResult(Event.Result.DENY);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onCheckZombieSiege(VillageSiegeEvent event) {
		Vec3 vec3 = event.getAttemptedSpawnPos();
		LevelAccessor level = event.getLevel();
		if (level instanceof ServerLevel) {
			Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
				return p_184069_.is(TofuPoiTypes.MORIJIO);
			}, (p_184055_) -> {
				return true;
			}, new BlockPos(vec3), 48, PoiManager.Occupancy.ANY);

			if (optional.isPresent()) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onBlockDrop(BlockEvent.BreakEvent event) {
		if (!event.getPlayer().isCreative() && (
				event.getLevel().getBlockState(event.getPos()).is(Blocks.FERN) || event.getLevel().getBlockState(event.getPos()).is(Blocks.TALL_GRASS) || event.getLevel().getBlockState(event.getPos()).is(Blocks.GRASS)) &&
				event.getLevel() instanceof Level && ((Level) event.getLevel()).random.nextFloat() < 0.075F) {
			ItemEntity entity = new ItemEntity((Level) event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(((Level) event.getLevel()).random.nextBoolean() ? TofuItems.SEEDS_SOYBEANS.get() : TofuItems.SEEDS_RICE.get()));
			event.getLevel().addFreshEntity(entity);
		}
	}
}

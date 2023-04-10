package baguchan.tofucraft;

import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuPoiTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillageSiegeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
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
	    if (event.getObject() instanceof Player) 
            event.addCapability(new ResourceLocation(TofuCraftReload.MODID, "soy_health"), new SoyHealthCapability());
		if (event.getObject() instanceof LivingEntity) 
			event.addCapability(new ResourceLocation(TofuCraftReload.MODID, "tofu_living"), new TofuLivingCapability());		
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingUpdateEvent event) {
		LivingEntity livingEntity = event.getEntityLiving();
		if (livingEntity instanceof Player player && !livingEntity.level.isClientSide()) {
		    player.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
				cap.tick(player);
			});
		}
		livingEntity.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).ifPresent(tofuLivingCapability -> {
			tofuLivingCapability.tick(livingEntity);
		});
	}

	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event) {
	  if (!event.isWasDeath()) {
	    LazyOptional<SoyHealthCapability> oldSoyCap = event.getOriginal().getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY);
	    LazyOptional<SoyHealthCapability> newSoyCap = event.getPlayer().getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY);
	    if (oldSoyCap.isPresent() && newSoyCap.isPresent()) {
	      newSoyCap.ifPresent((newCap) -> {
	        oldSoyCap.ifPresent((oldCap) -> {
	          newCap.deserializeNBT(oldCap.serializeNBT());
	        });
	      });
	    }
	  }
	}

	@SubscribeEvent
	public static void onBlockUsed(PlayerInteractEvent.RightClickBlock event) {
		if (event.getItemStack().is(TofuItems.BUCKET_SOYMILK.get()) && event.getWorld().getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
			event.getWorld().setBlock(event.getPos(), TofuBlocks.SOYMILK_CAULDRON.get().defaultBlockState(), 2);
			event.getPlayer().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack2 = new ItemStack(Items.BUCKET);
			if (!event.getPlayer().isCreative()) {
				event.getItemStack().shrink(1);
			}
			if (event.getItemStack().isEmpty()) {
				event.getPlayer().setItemInHand(event.getHand(), itemstack2);
			} else if (!event.getPlayer().isCreative() &&
					!event.getPlayer().getInventory().add(itemstack2)) {
				event.getPlayer().drop(itemstack2, false);
			}

			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
		if (event.getItemStack().is(TofuItems.BUCKET_SOYMILK_NETHER.get()) && event.getWorld().getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
			event.getWorld().setBlock(event.getPos(), TofuBlocks.SOYMILK_NETHER_CAULDRON.get().defaultBlockState(), 2);
			event.getPlayer().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack2 = new ItemStack(Items.BUCKET);
			if (!event.getPlayer().isCreative()) {
				event.getItemStack().shrink(1);
			}
			if (event.getItemStack().isEmpty()) {
				event.getPlayer().setItemInHand(event.getHand(), itemstack2);
			} else if (!event.getPlayer().isCreative() &&
					!event.getPlayer().getInventory().add(itemstack2)) {
				event.getPlayer().drop(itemstack2, false);
			}

			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
		if (event.getItemStack().is(TofuItems.BUCKET_SOYMILK_SOUL.get()) && event.getWorld().getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
			event.getWorld().setBlock(event.getPos(), TofuBlocks.SOYMILK_SOUL_CAULDRON.get().defaultBlockState(), 2);
			event.getPlayer().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack2 = new ItemStack(Items.BUCKET);
			if (!event.getPlayer().isCreative()) {
				event.getItemStack().shrink(1);
			}
			if (event.getItemStack().isEmpty()) {
				event.getPlayer().setItemInHand(event.getHand(), itemstack2);
			} else if (!event.getPlayer().isCreative() &&
					!event.getPlayer().getInventory().add(itemstack2)) {
				event.getPlayer().drop(itemstack2, false);
			}

			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
	}


	/*@SubscribeEvent
	public static void onFluidPlaceEvent(BlockEvent.FluidPlaceBlockEvent event) {
		FluidState fluidState = event.getWorld().getFluidState(event.getLiquidPos());
		if (fluidState.getType() == TofuFluids.SOYMILK || fluidState.getType() == TofuFluids.SOYMILK_FLOW)
			event.setNewState(TofuBlocks.TOFU_TERRAIN.defaultBlockState());
	}*/

	/*private static final Map<ServerWorld, TravelerTofunianSpawner> TRAVELER_TOFUNIAN_SPAWNER_MAP = new HashMap<>();

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load evt) {
		if (!evt.getWorld().isClientSide() && evt.getWorld() instanceof ServerWorld)
			TRAVELER_TOFUNIAN_SPAWNER_MAP.put((ServerWorld) evt.getWorld(), new TravelerTofunianSpawner((ServerWorld) evt.getWorld()));
	}

	@SubscribeEvent
	public static void worldUnload(WorldEvent.Unload evt) {
		if (!evt.getWorld().isClientSide() && evt.getWorld() instanceof ServerWorld)
			TRAVELER_TOFUNIAN_SPAWNER_MAP.remove(evt.getWorld());
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
		LivingEntity livingEntity = event.getEntityLiving();
		LevelAccessor level = event.getWorld();
		if (livingEntity instanceof Enemy) {
			if (event.getSpawnReason() != MobSpawnType.SPAWNER && event.getSpawnReason() != MobSpawnType.EVENT && event.getSpawnReason() != MobSpawnType.BREEDING && event.getSpawnReason() != MobSpawnType.PATROL) {
				if (level instanceof ServerLevel) {
					Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
						return p_184069_ == TofuPoiTypes.MORIJIO_POI.get();
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
		LevelAccessor level = event.getWorld();
		if (level instanceof ServerLevel) {
			Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
				return p_184069_ == TofuPoiTypes.MORIJIO_POI.get();
			}, (p_184055_) -> {
				return true;
			}, new BlockPos(vec3), 48, PoiManager.Occupancy.ANY);

			if (optional.isPresent()) {
				event.setCanceled(true);
			}
		}
	}

}

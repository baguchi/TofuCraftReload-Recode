package baguchan.tofucraft;

import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.world.TravelerTofunianSpawner;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.PacketDistributor;

@EventBusSubscriber(modid = "tofucraft")
public class CommonEvents {
	@SubscribeEvent
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity)
			event.addCapability(new ResourceLocation("tofucraft", "soy_health"), (ICapabilityProvider) new SoyHealthCapability());
	}

	@SubscribeEvent
	public static void onEntitySpawn(LivingSpawnEvent event) {
		LivingEntity livingEntity = event.getEntityLiving();
		if (!livingEntity.level.isClientSide())
			livingEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
				SoyMilkDrinkedMessage message = new SoyMilkDrinkedMessage(livingEntity, cap.getSoyHealthLevel());
				TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(()), message);
			});
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingUpdateEvent event) {
		LivingEntity livingEntity = event.getEntityLiving();
		if (!livingEntity.level.isClientSide())
			livingEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(SoyHealthCapability::tick);
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		if (player instanceof ServerPlayerEntity)
			player.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.PLAYER.with(()), new SoyMilkDrinkedMessage((LivingEntity) player, handler.getSoyHealthLevel())));
	}

	@SubscribeEvent
	public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		PlayerEntity playerEntity = event.getPlayer();
		playerEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(()), new SoyMilkDrinkedMessage((LivingEntity) playerEntity, handler.getSoyHealthLevel())));
	}

	@SubscribeEvent
	public static void onFluidPlaceEvent(BlockEvent.FluidPlaceBlockEvent event) {
		FluidState fluidState = event.getWorld().func_204610_c(event.getLiquidPos());
		if (fluidState.func_206886_c() == TofuFluids.SOYMILK || fluidState.func_206886_c() == TofuFluids.SOYMILK_FLOW)
			event.setNewState(TofuBlocks.TOFU_TERRAIN.func_176223_P());
	}

	private static final Map<ServerWorld, TravelerTofunianSpawner> TRAVELER_TOFUNIAN_SPAWNER_MAP = new HashMap<>();

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
		if (!tick.world.field_72995_K && tick.world instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) tick.world;
			TravelerTofunianSpawner spawner = TRAVELER_TOFUNIAN_SPAWNER_MAP.get(serverWorld);
			if (spawner != null)
				spawner.tick();
		}
	}

	@SubscribeEvent
	public static void onBlockDrop(BlockEvent.BreakEvent event) {
		if (!event.getPlayer().func_184812_l_() && (
				event.getWorld().getBlockState(event.getPos()).func_203425_a(Blocks.field_150349_c) || event.getWorld().getBlockState(event.getPos()).func_203425_a(Blocks.field_196804_gh)) &&
				event.getWorld() instanceof World && ((World) event.getWorld()).field_73012_v.nextFloat() < 0.075F) {
			ItemEntity entity = new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack((IItemProvider) TofuItems.SEEDS_SOYBEANS));
			event.getWorld().func_217376_c((Entity) entity);
		}
	}
}

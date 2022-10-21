package baguchan.tofucraft;

import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.entity.TofuGandlem;
import baguchan.tofucraft.message.SoyHealthMessage;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuPoiTypes;
import baguchan.tofucraft.registry.TofuStructures;
import baguchan.tofucraft.utils.JigsawHelper;
import baguchan.tofucraft.world.TofuLevelData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
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
		if (player instanceof ServerPlayer) {
			player.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SoyMilkDrinkedMessage(player, handler.getSoyHealthLevel(), false)));
			player.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SoyHealthMessage(player, handler.getSoyHealth(), handler.getSoyMaxHealth())));

		}
	}

	@SubscribeEvent
	public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player playerEntity = event.getEntity();
		playerEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerEntity), new SoyMilkDrinkedMessage(playerEntity, handler.getSoyHealthLevel(), false)));
		playerEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(handler -> TofuCraftReload.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerEntity), new SoyHealthMessage(playerEntity, handler.getSoyHealth(), handler.getSoyMaxHealth())));
	}

	@SubscribeEvent
	public static void onBlockUsed(PlayerInteractEvent.RightClickBlock event) {
		if (event.getItemStack().is(TofuItems.BUCKET_SOYMILK.get()) && event.getLevel().getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
			event.getLevel().setBlock(event.getPos(), TofuBlocks.SOYMILK_CAULDRON.get().defaultBlockState(), 2);
			event.getEntity().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack2 = new ItemStack(Items.BUCKET);
			if (!event.getEntity().isCreative()) {
				event.getItemStack().shrink(1);
			}
			if (event.getItemStack().isEmpty()) {
				event.getEntity().setItemInHand(event.getHand(), itemstack2);
			} else if (!event.getEntity().isCreative() &&
					!event.getEntity().getInventory().add(itemstack2)) {
				event.getEntity().drop(itemstack2, false);
			}

			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onBlockBreaked(BlockEvent.BreakEvent event) {
		if (event.getPlayer() instanceof ServerPlayer) {
			LevelAccessor world = event.getPlayer().level;
			if (world instanceof ServerLevel) {
				ServerLevel serverLevel = (ServerLevel) world;
				Structure structure = serverLevel.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY).get(TofuStructures.TOFU_CASTLE);
				if (structure != null) {
					StructureStart structureStart = serverLevel.structureManager().getStructureAt(event.getPos(), structure);
					if (structureStart.isValid()) {
						TofuGandlem gandlem = serverLevel.getNearestEntity(TofuGandlem.class, TargetingConditions.forNonCombat(), (LivingEntity) event.getPlayer(), event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(), event.getPlayer().getBoundingBox().inflate(32.0F));

						if (gandlem != null) {
							ServerPlayer player = (ServerPlayer) event.getPlayer();
							if (!player.isCreative() && !(event.getState().getBlock() instanceof TorchBlock)) {
								player.displayClientMessage(Component.translatable("tofucraft.need_defeat_boss"), true);
								event.setCanceled(true);
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
		if (event.getEntity() instanceof ServerPlayer) {
			LevelAccessor world = event.getEntity().level;
			if (world instanceof ServerLevel) {
				ServerLevel serverLevel = (ServerLevel) world;
				Structure structure = serverLevel.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY).get(TofuStructures.TOFU_CASTLE);
				if (structure != null) {
					StructureStart structureStart = serverLevel.structureManager().getStructureAt(event.getPos(), structure);
					if (structureStart.isValid()) {
						TofuGandlem gandlem = serverLevel.getNearestEntity(TofuGandlem.class, TargetingConditions.forNonCombat(), (LivingEntity) event.getEntity(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity().getBoundingBox().inflate(32.0F));
						if (gandlem != null) {
							ServerPlayer player = (ServerPlayer) event.getEntity();
							if (!player.isCreative() && !(event.getState().getBlock() instanceof TorchBlock)) {
								player.displayClientMessage(Component.translatable("tofucraft.need_defeat_boss"), true);
								event.setCanceled(true);
							}
						}
					}
				}
			}
		}
	}

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
			if (!(event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ShearsItem)) {
				ItemEntity entity = new ItemEntity((Level) event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(((Level) event.getLevel()).random.nextBoolean() ? TofuItems.SEEDS_SOYBEANS.get() : TofuItems.SEEDS_RICE.get()));
				entity.setDefaultPickUpDelay();
				event.getLevel().addFreshEntity(entity);
			}
		}
	}

	@SubscribeEvent
	public static void onWorldLoad(LevelEvent.Load event) {
		if (event.getLevel() instanceof ServerLevel level && level.dimension().location().equals(TofuDimensions.tofu_world.location())) {
			TofuLevelData levelData = new TofuLevelData(level.getServer().getWorldData(), level.getServer().getWorldData().overworldData());
			level.serverLevelData = levelData;
			level.levelData = levelData;
		}
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {
		LivingEntity livingEntity = event.getEntity();
		livingEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
			if (cap.getSoyHealth() > 0) {
				if (event.getSource().isProjectile() || event.getSource().isFall()) {
					if (cap.getSoyHealth() - event.getAmount() * 0.75F >= 0) {
						cap.setSoyHealth(livingEntity, cap.getSoyHealth() - event.getAmount() * 0.5F, cap.getSoyMaxHealth());
						event.setAmount(0);
					} else {
						float remainDamage = event.getAmount() * 0.75F - cap.getSoyHealth();
						cap.setSoyHealth(livingEntity, 0, cap.getSoyMaxHealth());
						event.setAmount(remainDamage);
					}
				} else {
					if (cap.getSoyHealth() - event.getAmount() >= 0) {
						cap.setSoyHealth(livingEntity, cap.getSoyHealth() - event.getAmount() * 0.5F, cap.getSoyMaxHealth());
						event.setAmount(0);
					} else {
						float remainDamage = event.getAmount() - cap.getSoyHealth();
						cap.setSoyHealth(livingEntity, 0, cap.getSoyMaxHealth());
						event.setAmount(remainDamage);
					}
				}
			}
		});
	}

	@SubscribeEvent
	public static void onServerAboutToStartEvent(ServerStartedEvent event) {
		// SETUP Tofu Worker House
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_plains_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_taiga_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_savanna_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_snowy_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_desert_1"), 8);
	}
}

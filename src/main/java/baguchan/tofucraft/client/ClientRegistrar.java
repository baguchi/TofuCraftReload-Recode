package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.attachment.TofuLivingAttachment;
import baguchan.tofucraft.client.model.FukumameThowerModel;
import baguchan.tofucraft.client.model.ShuDofuSpiderModel;
import baguchan.tofucraft.client.model.TofuFishModel;
import baguchan.tofucraft.client.model.TofuGandlemModel;
import baguchan.tofucraft.client.model.TofuGolemModel;
import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.model.TravelerTofunianModel;
import baguchan.tofucraft.client.render.FukumameRender;
import baguchan.tofucraft.client.render.NattoBallRender;
import baguchan.tofucraft.client.render.NattoStringRender;
import baguchan.tofucraft.client.render.NetherFukumameRender;
import baguchan.tofucraft.client.render.SoulFukumameRender;
import baguchan.tofucraft.client.render.ZundaArrowRender;
import baguchan.tofucraft.client.render.blockentity.FoodPlateRender;
import baguchan.tofucraft.client.render.blockentity.TofuBedRenderer;
import baguchan.tofucraft.client.render.blockentity.TofuChestRenderer;
import baguchan.tofucraft.client.render.blockentity.TofunianStatueRender;
import baguchan.tofucraft.client.render.entity.FallingTofuRenderer;
import baguchan.tofucraft.client.render.entity.FukumameThowerRenderer;
import baguchan.tofucraft.client.render.entity.ShuDofuSpiderRender;
import baguchan.tofucraft.client.render.entity.SoyballRenderer;
import baguchan.tofucraft.client.render.entity.TofuBoatRenderer;
import baguchan.tofucraft.client.render.entity.TofuCowRender;
import baguchan.tofucraft.client.render.entity.TofuCreeperRender;
import baguchan.tofucraft.client.render.entity.TofuFishRender;
import baguchan.tofucraft.client.render.entity.TofuGandlemRender;
import baguchan.tofucraft.client.render.entity.TofuGolemRender;
import baguchan.tofucraft.client.render.entity.TofuPigRender;
import baguchan.tofucraft.client.render.entity.TofuSlimeRender;
import baguchan.tofucraft.client.render.entity.TofuSpiderRender;
import baguchan.tofucraft.client.render.entity.TofunianRender;
import baguchan.tofucraft.client.render.entity.TravelerTofunianRender;
import baguchan.tofucraft.client.render.entity.ZundamiteRender;
import baguchan.tofucraft.client.render.entity.effect.NattoCobWebRender;
import baguchan.tofucraft.client.render.item.TofuBedBWLR;
import baguchan.tofucraft.client.render.item.TofuChestBWLR;
import baguchan.tofucraft.client.render.item.TofuShieldBWLR;
import baguchan.tofucraft.client.render.item.TofunianStatueBWLR;
import baguchan.tofucraft.client.screen.SaltFurnaceScreen;
import baguchan.tofucraft.client.screen.TFCrafterScreen;
import baguchan.tofucraft.client.screen.TFOvenScreen;
import baguchan.tofucraft.client.screen.TFStorageScreen;
import baguchan.tofucraft.entity.TofuBoat;
import baguchan.tofucraft.registry.TofuAttachments;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuMenus;
import baguchan.tofucraft.registry.TofuWoodTypes;
import baguchan.tofucraft.utils.ClientUtils;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BrushableBlockRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.VaultRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	private static final ResourceLocation TEXTURE_SOYHEARTS = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/soy_hearts.png");

	public static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(TofuWoodTypes.LEEK);
			Sheets.addWoodType(TofuWoodTypes.LEEK_GREEN);
			Sheets.addWoodType(TofuWoodTypes.TOFU_STEM);
		});

	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.SOYMILK.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_hell_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.SOYMILK_HELL.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_soul_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}


			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.SOYMILK_SOUL.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/bittern_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.BITTERN.get());

		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/doubanjiang");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/doubanjiang");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/doubanjiang_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.DOUBANJIANG.get());


		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/crimson"),
					FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/crimson");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOW;
			}
		}, TofuFluidTypes.CRIMSON.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/warped"),
					FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/warped");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOW;
			}
		}, TofuFluidTypes.WARPED.get());

		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new TofuShieldBWLR();
			}
		}, TofuItems.REFLECT_TOFU_SHIELD.get());
		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new TofuShieldBWLR();
			}
		}, TofuItems.TOFU_SHIELD.get());
		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new TofuChestBWLR();
			}
		}, TofuBlocks.TOFUCHEST.get().asItem());
		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new TofunianStatueBWLR();
			}
		}, TofuBlocks.TOFUNIAN_STATUE.get().asItem());
		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new TofuBedBWLR();
			}
		}, TofuBlocks.TOFUBED.get().asItem());
	}

	@SubscribeEvent
	public static void screenEvent(RegisterMenuScreensEvent event) {

		event.register(TofuMenus.SALT_FURNACE.get(), SaltFurnaceScreen::new);
		event.register(TofuMenus.TF_STORAGE.get(), TFStorageScreen::new);
		event.register(TofuMenus.TF_CRAFTER.get(), TFCrafterScreen::new);
		event.register(TofuMenus.TF_OVEN.get(), TFOvenScreen::new);
	}

	@SubscribeEvent
	public static void modelBake(ModelEvent.ModifyBakingResult event) {

		ItemProperties.register(TofuItems.TOFU_SHIELD.get(), ResourceLocation.parse("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
			return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
		});
		ItemProperties.register(TofuItems.REFLECT_TOFU_SHIELD.get(), ResourceLocation.parse("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
			return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
		});
		ItemProperties.register(TofuItems.ZUNDA_BOW.get(), ResourceLocation.parse("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
			if (p_174637_ == null) {
				return 0.0F;
			} else {
				return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float) (p_174635_.getUseDuration(p_174637_) - p_174637_.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(TofuItems.ZUNDA_BOW.get(), ResourceLocation.parse("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
			return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
		});
	}


	@SubscribeEvent
	public static void registerColorBlock(RegisterColorHandlersEvent.Block event) {
		event.register((p_92621_, p_92622_, p_92623_, p_92624_) -> {
			return p_92622_ != null && p_92623_ != null ? BiomeColors.getAverageWaterColor(p_92622_, p_92623_) : -1;
		}, TofuBlocks.SALTPAN.get(), TofuBlocks.SPROUTSJAR.get());
	}

	@SubscribeEvent
	public static void registerColorItem(RegisterColorHandlersEvent.Item event) {

	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TofuEntityTypes.TOFUCOW.get(), TofuCowRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUPIG.get(), TofuPigRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUNIAN.get(), TofunianRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TRAVELER_TOFUNIAN.get(), TravelerTofunianRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUFISH.get(), TofuFishRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFU_GOLEM.get(), TofuGolemRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSLIME.get(), TofuSlimeRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUCREEPER.get(), TofuCreeperRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSPIDER.get(), TofuSpiderRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFU_GANDLEM.get(), TofuGandlemRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SHUDOFUSPIDER.get(), ShuDofuSpiderRender::new);

		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME.get(), FukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NETHER_FUKUMAME.get(), NetherFukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SOUL_FUKUMAME.get(), SoulFukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.ZUNDA_ARROW.get(), ZundaArrowRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SOYBALL.get(), SoyballRenderer::new);

		event.registerEntityRenderer(TofuEntityTypes.NATTO_STRNIG.get(), (context) -> new NattoStringRender<>(context, 1.0F, true));
		event.registerEntityRenderer(TofuEntityTypes.NATTO_COBWEB.get(), NattoCobWebRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NATTO_BALL.get(), NattoBallRender::new);
		event.registerEntityRenderer(TofuEntityTypes.FALLING_TOFU.get(), FallingTofuRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFU_BOAT.get(), (r) -> new TofuBoatRenderer(r, false));
		event.registerEntityRenderer(TofuEntityTypes.TOFU_CHEST_BOAT.get(), (r) -> new TofuBoatRenderer(r, true));
		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME_THOWER.get(), (p_174064_) -> {
			return new FukumameThowerRenderer<>(p_174064_, TofuModelLayers.FUKUMAME_THOWER, ModelLayers.PIGLIN_INNER_ARMOR, ModelLayers.PIGLIN_OUTER_ARMOR, false);
		});
		event.registerEntityRenderer(TofuEntityTypes.ZUNDAMITE.get(), ZundamiteRender::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUBED.get(), TofuBedRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUCHEST.get(), TofuChestRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.FOODPLATE.get(), FoodPlateRender::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUNIAN_STATUE.get(), TofunianStatueRender::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.SUSPICIOUS_TOFU.get(), BrushableBlockRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFU_SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFU_HANGING_SIGN.get(), HangingSignRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFU_VAULT.get(), VaultRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TofuModelLayers.TOFUNIAN, TofunianModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TRAVELER_TOFUNIAN, TravelerTofunianModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFUSPIDER, TofuSpiderModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFUFISH, TofuFishModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFU_GOLEM, TofuGolemModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFU_GANDLEM, TofuGandlemModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.SHUDOFUSPIDER, ShuDofuSpiderModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.FUKUMAME_THOWER, FukumameThowerModel::createBodyLayer);

		for (TofuBoat.Type boatType : TofuBoat.Type.values()) {
			event.registerLayerDefinition(TofuBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(TofuBoatRenderer.createChestBoatModelName(boatType), ChestBoatModel::createBodyModel);
		}
	}

	@SubscribeEvent
	public static void registerOverlay(RegisterGuiLayersEvent event) {
		event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_portal_overlay"), (guiGraphics, partialTicks) -> {
			Minecraft minecraft = Minecraft.getInstance();
			Window window = minecraft.getWindow();
			LocalPlayer player = minecraft.player;
			if (player != null) {
				renderTofuPortalOverlay(guiGraphics, minecraft, window, player.getData(TofuAttachments.TOFU_LIVING.get()), partialTicks);
			}
		});
	}

	private static void renderTofuPortalOverlay(GuiGraphics guiGraphics, Minecraft minecraft, Window window, TofuLivingAttachment handler, DeltaTracker partialTicks) {
		float timeInPortal = Mth.lerp(partialTicks.getGameTimeDeltaPartialTick(false), handler.getPrevPortalAnimTime(), handler.getPortalAnimTime());
		if (timeInPortal > 0.0F) {
			if (timeInPortal < 1.0F) {
				timeInPortal *= timeInPortal;
				timeInPortal *= timeInPortal;
				timeInPortal = timeInPortal * 0.8F + 0.2F;
			}

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			guiGraphics.setColor(1.0F, 1.0F, 1.0F, timeInPortal);
			TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(TofuBlocks.TOFU_PORTAL.get().defaultBlockState());
			guiGraphics.blit(0, 0, -90, guiGraphics.guiWidth(), guiGraphics.guiHeight(), textureatlassprite);
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@SubscribeEvent
	public static void registerDimensionEffect(RegisterDimensionSpecialEffectsEvent event) {
		TofuDimensionEffects renderInfo = new TofuDimensionEffects();
		event.register(TofuCraftReload.prefix("renderer"), renderInfo);
	}
}

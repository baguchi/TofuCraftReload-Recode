package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.model.ShuDofuSpiderModel;
import baguchan.tofucraft.client.model.TofuFishModel;
import baguchan.tofucraft.client.model.TofuGandlemModel;
import baguchan.tofucraft.client.model.TofuGolemModel;
import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.model.TravelerTofunianModel;
import baguchan.tofucraft.client.overlay.TofuPortalOverlay;
import baguchan.tofucraft.client.render.FukumameRender;
import baguchan.tofucraft.client.render.NattoBallRender;
import baguchan.tofucraft.client.render.NattoStringRender;
import baguchan.tofucraft.client.render.NetherFukumameRender;
import baguchan.tofucraft.client.render.SoulFukumameRender;
import baguchan.tofucraft.client.render.ZundaArrowRender;
import baguchan.tofucraft.client.render.entity.FallingTofuRenderer;
import baguchan.tofucraft.client.render.entity.ShuDofuSpiderRender;
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
import baguchan.tofucraft.client.render.entity.effect.NattoCobWebRender;
import baguchan.tofucraft.client.render.tileentity.FoodPlateRender;
import baguchan.tofucraft.client.render.tileentity.SuspiciousTofuRenderer;
import baguchan.tofucraft.client.render.tileentity.TofuBedRenderer;
import baguchan.tofucraft.client.render.tileentity.TofuChestRenderer;
import baguchan.tofucraft.client.screen.SaltFurnaceScreen;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuContainers;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	private static final ResourceLocation TEXTURE_SOYHEARTS = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/soy_hearts.png");

	public static void renderEntity() {
	}

	public static void renderTileEntity() {
		BlockEntityRenderers.register(TofuBlockEntitys.TOFUBED.get(), TofuBedRenderer::new);
		BlockEntityRenderers.register(TofuBlockEntitys.TOFUCHEST.get(), TofuChestRenderer::new);
		BlockEntityRenderers.register(TofuBlockEntitys.FOODPLATE.get(), FoodPlateRender::new);
		BlockEntityRenderers.register(TofuBlockEntitys.SUSPICIOUS_TOFU.get(), SuspiciousTofuRenderer::new);
	}


	public static void setup(FMLCommonSetupEvent event) {
		renderEntity();
		renderTileEntity();

		ItemProperties.register(TofuItems.TOFU_SHIELD.get(), new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
			return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
		});
		ItemProperties.register(TofuItems.ZUNDA_BOW.get(), new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
			if (p_174637_ == null) {
				return 0.0F;
			} else {
				return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float) (p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(TofuItems.ZUNDA_BOW.get(), new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
			return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
		});

		MenuScreens.register(TofuContainers.SALT_FURNACE.get(), SaltFurnaceScreen::new);
	}

	@SubscribeEvent
	public static void registerColor(RegisterColorHandlersEvent.Block event) {
		event.register((p_92621_, p_92622_, p_92623_, p_92624_) -> {
			return p_92622_ != null && p_92623_ != null ? BiomeColors.getAverageWaterColor(p_92622_, p_92623_) : -1;
		}, TofuBlocks.SALTPAN.get(), TofuBlocks.SPROUTSJAR.get());
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

		event.registerEntityRenderer(TofuEntityTypes.NATTO_STRNIG.get(), (context) -> new NattoStringRender<>(context, 1.0F, true));
		event.registerEntityRenderer(TofuEntityTypes.NATTO_COBWEB.get(), NattoCobWebRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NATTO_BALL.get(), NattoBallRender::new);
		event.registerEntityRenderer(TofuEntityTypes.FALLING_TOFU.get(), FallingTofuRenderer::new);
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
	}

	@SubscribeEvent
	public static void registerOverlay(RegisterGuiOverlaysEvent event) {
		event.registerAboveAll("tofu_portal_overlay", new TofuPortalOverlay());
	}
}

package baguchi.tofucraft.client;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.attachment.TofuLivingAttachment;
import baguchi.tofucraft.client.model.FukumameThrowerModel;
import baguchi.tofucraft.client.model.ShuDofuSpiderModel;
import baguchi.tofucraft.client.model.SoyBallModel;
import baguchi.tofucraft.client.model.TofuFishModel;
import baguchi.tofucraft.client.model.TofuGandlemModel;
import baguchi.tofucraft.client.model.TofuGolemModel;
import baguchi.tofucraft.client.model.TofuSpiderModel;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.model.TravelerTofunianModel;
import baguchi.tofucraft.client.particle.ParticleSimpleStink;
import baguchi.tofucraft.client.particle.ParticleStink;
import baguchi.tofucraft.client.particle.ParticleZundaCloud;
import baguchi.tofucraft.client.particle.SoymilkDripParticle;
import baguchi.tofucraft.client.particle.SoymilkSplashParticle;
import baguchi.tofucraft.client.particle.TofuPortalParticle;
import baguchi.tofucraft.client.particle.ZundaExplosionParticle;
import baguchi.tofucraft.client.particle.ZundaExplosionSeedParticle;
import baguchi.tofucraft.client.render.FukumameRender;
import baguchi.tofucraft.client.render.NattoBallRender;
import baguchi.tofucraft.client.render.NattoStringRender;
import baguchi.tofucraft.client.render.NetherFukumameRender;
import baguchi.tofucraft.client.render.SoulFukumameRender;
import baguchi.tofucraft.client.render.ZundaArrowRender;
import baguchi.tofucraft.client.render.ZundaBusterRenderer;
import baguchi.tofucraft.client.render.blockentity.FoodPlateRender;
import baguchi.tofucraft.client.render.blockentity.TofuBedRenderer;
import baguchi.tofucraft.client.render.blockentity.TofuChestRenderer;
import baguchi.tofucraft.client.render.blockentity.TofunianStatueRender;
import baguchi.tofucraft.client.render.entity.FallingTofuRenderer;
import baguchi.tofucraft.client.render.entity.FukumameThrowerRenderer;
import baguchi.tofucraft.client.render.entity.ShuDofuSpiderRender;
import baguchi.tofucraft.client.render.entity.SoyballRenderer;
import baguchi.tofucraft.client.render.entity.TofuCowRender;
import baguchi.tofucraft.client.render.entity.TofuCreeperRender;
import baguchi.tofucraft.client.render.entity.TofuFishRender;
import baguchi.tofucraft.client.render.entity.TofuGandlemRender;
import baguchi.tofucraft.client.render.entity.TofuGolemRender;
import baguchi.tofucraft.client.render.entity.TofuPigRender;
import baguchi.tofucraft.client.render.entity.TofuSlimeRender;
import baguchi.tofucraft.client.render.entity.TofuSpiderRender;
import baguchi.tofucraft.client.render.entity.TofunianRender;
import baguchi.tofucraft.client.render.entity.TravelerTofunianRender;
import baguchi.tofucraft.client.render.entity.ZundamiteRender;
import baguchi.tofucraft.client.render.entity.effect.NattoCobWebRender;
import baguchi.tofucraft.client.render.item.properties.TFProperty;
import baguchi.tofucraft.client.render.layer.ZundaLayer;
import baguchi.tofucraft.client.render.layer.ZundaSlimeOuterLayer;
import baguchi.tofucraft.client.render.special.TofuShieldSpecialRenderer;
import baguchi.tofucraft.client.render.special.TofunianStatueSpecialRenderer;
import baguchi.tofucraft.client.screen.ReceivingTofuLevelScreen;
import baguchi.tofucraft.client.screen.SaltFurnaceScreen;
import baguchi.tofucraft.client.screen.TFCrafterScreen;
import baguchi.tofucraft.client.screen.TFOvenScreen;
import baguchi.tofucraft.client.screen.TFStorageScreen;
import baguchi.tofucraft.client.screen.TfCraftingTableScreen;
import baguchi.tofucraft.client.screen.TofuPotScreen;
import baguchi.tofucraft.mixin.client.GuiAccessor;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDimensions;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuMenus;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import baguchi.tofucraft.registry.TofuWoodTypes;
import com.google.common.reflect.TypeToken;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterConditionalItemModelPropertyEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionTransitionScreenEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterRecipeBookSearchCategoriesEvent;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;

import static net.minecraft.client.renderer.RenderPipelines.FOG_SNIPPET;
import static net.minecraft.client.renderer.RenderPipelines.GLOBALS_SNIPPET;
import static net.minecraft.client.renderer.RenderPipelines.MATRICES_PROJECTION_SNIPPET;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT)
public class ClientRegistrar {
	private static final ResourceLocation TEXTURE_RECOVER_HEART = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "hud/heart/recover_container");
	private static final ResourceLocation TEXTURE_RECOVER_HEART_HALF = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "hud/heart/recover_container_half");

	public static final RenderPipeline ZUNDA =
			RenderPipeline.builder(new RenderPipeline.Snippet[]{MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET})
					.withLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "pipeline/zunda"))
					.withVertexShader("core/glint").withFragmentShader("core/glint")
					.withSampler("Sampler0")
					.withBlend(BlendFunction.ADDITIVE)
					.withDepthWrite(false).withCull(false).withDepthTestFunction(DepthTestFunction.EQUAL_DEPTH_TEST).withVertexFormat(DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS).build();

	public static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(TofuWoodTypes.LEEK);
			Sheets.addWoodType(TofuWoodTypes.LEEK_GREEN);
			Sheets.addWoodType(TofuWoodTypes.TOFU_STEM);
		});

	}

	@SubscribeEvent
	public static void registerRecipeBookSearchCategories(RegisterRecipeBookSearchCategoriesEvent event) {
		event.register(TofuRecipeBookCategory.SEARCH, TofuRecipeBookCategory.COOKING_FAST_FOODS.get(), TofuRecipeBookCategory.COOKING_DRINKS.get(), TofuRecipeBookCategory.COOKING_MEALS.get(), TofuRecipeBookCategory.COOKING_MISC.get());
		event.register(TofuRecipeBookCategory.TF_SEARCH, TofuRecipeBookCategory.TF_MECHA.get(), TofuRecipeBookCategory.TF_MISC.get());
	}

	@SubscribeEvent
	public static void registerDimensionTransitionScreens(RegisterDimensionTransitionScreenEvent event) {
		event.registerIncomingEffect(TofuDimensions.tofu_world, (supplier, reason) -> new ReceivingTofuLevelScreen(supplier));
		event.registerOutgoingEffect(TofuDimensions.tofu_world, (supplier, reason) -> new ReceivingTofuLevelScreen(supplier));
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_overlay");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				return new Vector4f(255 / 255F, 251 / 255F, 222 / 255F, 1F);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0F;
				fogData.environmentalEnd = 5.0F;
			}
		}, TofuFluidTypes.SOYMILK.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell_overlay");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				return new Vector4f(156 / 255F, 145 / 255F, 78 / 255F, 1F);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0F;
				fogData.environmentalEnd = 5.0F;
			}

		}, TofuFluidTypes.SOYMILK_HELL.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul_overlay");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				return new Vector4f(78 / 255F, 145 / 255F, 156 / 255F, 1F);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0F;
				fogData.environmentalEnd = 5.0F;
			}
		}, TofuFluidTypes.SOYMILK_SOUL.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern_overlay");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				return new Vector4f(104 / 255F, 157 / 255F, 170 / 255F, 1F);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0F;
				fogData.environmentalEnd = 6.0F;
			}
		}, TofuFluidTypes.BITTERN.get());

		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/doubanjiang");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/doubanjiang_flow");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				return new Vector4f(155 / 255F, 25 / 255F, 0 / 255F, 1F);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0F;
				fogData.environmentalEnd = 3.0F;
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
	}

	@SubscribeEvent
	public static void screenEvent(RegisterMenuScreensEvent event) {

		event.register(TofuMenus.SALT_FURNACE.get(), SaltFurnaceScreen::new);
		event.register(TofuMenus.TF_STORAGE.get(), TFStorageScreen::new);
		event.register(TofuMenus.TF_CRAFTER.get(), TFCrafterScreen::new);
		event.register(TofuMenus.TF_OVEN.get(), TFOvenScreen::new);
		event.register(TofuMenus.TF_CRAFTING_TABLE.get(), TfCraftingTableScreen::new);
		event.register(TofuMenus.TOFU_POT.get(), TofuPotScreen::new);
	}
	@SubscribeEvent
	public static void specialModelRender(RegisterSpecialModelRendererEvent event) {
		event.register(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_shield"), TofuShieldSpecialRenderer.Unbaked.MAP_CODEC);
		event.register(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian_statue"), TofunianStatueSpecialRenderer.Unbaked.MAP_CODEC);
	}


	@SubscribeEvent
	public static void registerColorBlock(RegisterColorHandlersEvent.Block event) {
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
		event.registerEntityRenderer(TofuEntityTypes.SOYBALL.get(), SoyballRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.UNSTABLE_ZUNDAMA.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.ZUNDA_BUSTER.get(), ZundaBusterRenderer::new);

		event.registerEntityRenderer(TofuEntityTypes.NATTO_STRNIG.get(), (context) -> new NattoStringRender<>(context, 1.0F, true));
		event.registerEntityRenderer(TofuEntityTypes.NATTO_COBWEB.get(), NattoCobWebRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NATTO_BALL.get(), NattoBallRender::new);
		event.registerEntityRenderer(TofuEntityTypes.FALLING_TOFU.get(), FallingTofuRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME_THROWER.get(), FukumameThrowerRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.ZUNDAMITE.get(), ZundamiteRender::new);
		event.registerEntityRenderer(TofuEntityTypes.LEEK_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.LEEK_GREEN_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_GREEN_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.TOFU_STEM_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.TOFU_STEM_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.LEEK_CHEST_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_CHEST_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.LEEK_GREEN_CHEST_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_GREEN_CHEST_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.TOFU_STEM_CHEST_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.TOFU_STEM_CHEST_BOAT));


		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUBED.get(), TofuBedRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUCHEST.get(), TofuChestRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.FOODPLATE.get(), FoodPlateRender::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUNIAN_STATUE.get(), TofunianStatueRender::new);
	}

	@SubscribeEvent
	public static void registerItemModelProperty(RegisterConditionalItemModelPropertyEvent event) {
		event.register(TofuCraftReload.prefix("has_tf"), TFProperty.MAP_CODEC);
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
		event.registerLayerDefinition(TofuModelLayers.FUKUMAME_THROWER, FukumameThrowerModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.SOYBALL, SoyBallModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFU_STEM_BOAT, BoatModel::createBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_BOAT, BoatModel::createBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_GREEN_BOAT, BoatModel::createBoatModel);
		event.registerLayerDefinition(TofuModelLayers.TOFU_STEM_CHEST_BOAT, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_CHEST_BOAT, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_GREEN_CHEST_BOAT, BoatModel::createChestBoatModel);
	}

	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.AddLayers event) {
		event.getContext().getEntityRenderDispatcher().getSkinMap().forEach((model, player) ->
		{
			if (event.getSkin(model) != null) {
				if (player instanceof LivingEntityRenderer) {
					((LivingEntityRenderer<?, ?, ?>) player).addLayer(new ZundaLayer(event.getSkin(model)));
				}
			}
		});
		event.getEntityTypes().forEach(entityType -> {
			if (event.getRenderer(entityType) instanceof LivingEntityRenderer r) {
				r.addLayer(new ZundaLayer(r));

			}
			if (event.getRenderer(entityType) instanceof SlimeRenderer r) {
				r.addLayer(new ZundaSlimeOuterLayer(r, event.getEntityModels()));
			}
		});
	}

	@SubscribeEvent
	public static void registerState(RegisterRenderStateModifiersEvent event) {
		event.registerEntityModifier(new TypeToken<LivingEntityRenderer<LivingEntity, LivingEntityRenderState, ?>>(LivingEntityRenderer.class) {
		}, (entity, state) -> {
			if (entity.hasData(TofuAttachments.TOFU_LIVING)) {
				if (entity.getData(TofuAttachments.TOFU_LIVING).isZundafied()) {
					state.setRenderData(ZundaLayer.ZUNDA_KEY, true);
				}
			}
		});
	}

	@SubscribeEvent
	public static void registerPipelines(RegisterRenderPipelinesEvent event) {
		event.registerPipeline(ZUNDA);
	}


	@SubscribeEvent
	public static void registerOverlay(RegisterGuiLayersEvent event) {
		event.registerBelow(VanillaGuiLayers.CAMERA_OVERLAYS, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_portal_overlay"), (guiGraphics, partialTicks) -> {
			Minecraft minecraft = Minecraft.getInstance();
			Window window = minecraft.getWindow();
			LocalPlayer player = minecraft.player;
			if (player != null) {
				renderTofuPortalOverlay(guiGraphics, minecraft, window, player.getData(TofuAttachments.TOFU_LIVING.get()), partialTicks);
			}
		});
		event.registerAbove(VanillaGuiLayers.PLAYER_HEALTH, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "recover_hearts"), (guiGraphics, partialTicks) -> {
			Minecraft minecraft = Minecraft.getInstance();
			Window window = minecraft.getWindow();
			Gui gui = minecraft.gui;
			LocalPlayer player = minecraft.player;
			if (player != null) {
				renderRecoverHearts(guiGraphics, minecraft, window, gui, player);
			}
		});
	}

	private static void renderRecoverHearts(GuiGraphics guiGraphics, Minecraft minecraft, Window window, Gui gui, LocalPlayer player) {
		GuiAccessor guiAccessor = (GuiAccessor) gui;

		if (minecraft.gameMode.canHurtPlayer()) {
			var tofuLivingAttachment = player.getData(TofuAttachments.TOFU_LIVING);
			if (tofuLivingAttachment.getRecoverHealth() > 0) {
				AttributeInstance attributeInstance = player.getAttribute(Attributes.MAX_HEALTH);
				if (attributeInstance != null) {
					int lastRecoverHealth = 0;
					int lastOverallHealth = 0;

					double overallHealth = attributeInstance.getValue();
					double maxRecoverHealth = tofuLivingAttachment.getRecoverHealth();

					int maxDefaultHealth = Mth.ceil(overallHealth);

					int currentOverallHealth = Mth.ceil(player.getHealth());
					int currentRecoverHealth = Mth.ceil(maxRecoverHealth);

					boolean highlight = guiAccessor.tofucraft$getHealthBlinkTime() > (long) gui.getGuiTicks() && (guiAccessor.tofucraft$getHealthBlinkTime() - (long) gui.getGuiTicks()) / 3L % 2L == 1L;
					if (Util.getMillis() - guiAccessor.tofucraft$getLastHealthTime() > 1000L) {
						lastOverallHealth = currentOverallHealth;
						lastRecoverHealth = currentRecoverHealth;
					}
					//do NOT cast this to long. This is the only way the hearts will properly shake when health is low
					//the only time the shaking will be off is if the player's max health attribute base is below 0. This probably can't be fixed.
					guiAccessor.tofucraft$getRandom().setSeed(gui.getGuiTicks() * 312871L);

					float displayOverallHealth = Math.max(lastOverallHealth, currentOverallHealth);
					float displayRecoverHealth = Mth.clamp(Math.max(lastRecoverHealth, currentRecoverHealth), 0, maxDefaultHealth);
					int absorption = Mth.ceil(player.getAbsorptionAmount());

					int healthRows = Mth.ceil((displayOverallHealth + absorption) / 2.0F / 10.0F);
					int rowHeight = Math.max(10 - (healthRows - 2), 3);

					int left = window.getGuiScaledWidth() / 2 - 91;
					int top = window.getGuiScaledHeight() - 39;

					int regen = Integer.MIN_VALUE;
					if (player.hasEffect(MobEffects.REGENERATION)) {
						regen = gui.getGuiTicks() % Mth.ceil(displayOverallHealth + 5.0F);
					}

					renderHearts(guiGraphics, player, gui, left, top, regen, displayOverallHealth, displayRecoverHealth, maxDefaultHealth, currentRecoverHealth, rowHeight, absorption, highlight);

				}
			}
		}
	}

	private static void renderHearts(GuiGraphics guiGraphics, Player player, Gui gui, int left, int top, int regen, float displayOverallHealth, float displayRecoverHealth, int maxDefaultHealth, int recoverHealth, int rowHeight, int absorption, boolean highlight) {
		GuiAccessor guiAccessor = (GuiAccessor) gui;
		int overallHearts = Mth.ceil((double) displayOverallHealth / 2.0);
		int recoverHearts = Mth.ceil((double) displayRecoverHealth / 2.0);
		int maxDefaultHearts = Mth.ceil((double) maxDefaultHealth / 2.0);
		for (int currentHeart = maxDefaultHearts - 1; currentHeart >= overallHearts - 1; --currentHeart) {
			int x = left + (currentHeart) % 10 * 8;
			int y = top - (currentHeart) / 10 * rowHeight;

			if (Mth.ceil(player.getHealth()) + absorption <= 4) {
				y += guiAccessor.tofucraft$getRandom().nextInt(2);
			}
			if ((maxDefaultHearts >= 10 ? overallHearts - 10 : maxDefaultHearts) < overallHearts && Math.min(maxDefaultHearts, 10) - 0 == regen) {
				y -= 2;
			}

			int i2 = currentHeart * 2;
			boolean flag3 = i2 + 1 == displayOverallHealth;
			boolean flag4 = recoverHearts * 2 == recoverHealth;

			if (currentHeart < (flag4 ? overallHearts + recoverHearts : overallHearts + recoverHearts - 1)) {
				if (currentHeart != overallHearts - 2) {
					if (!flag3 && currentHeart != overallHearts - 1) {
						renderHeart(guiGraphics, TEXTURE_RECOVER_HEART, x, y);
					} else if (flag3) {
						renderHeart(guiGraphics, TEXTURE_RECOVER_HEART_HALF, x, y);

					}
				}
			}

		}
	}

	private static void renderHeart(
			GuiGraphics p_283024_, ResourceLocation p_281393_, int p_283636_, int p_283279_
	) {
		p_283024_.blitSprite(RenderPipelines.GUI_TEXTURED, p_281393_, p_283636_, p_283279_, 9, 9);
	}

	private static void renderTofuPortalOverlay(GuiGraphics guiGraphics, Minecraft minecraft, Window window, TofuLivingAttachment handler, DeltaTracker partialTicks) {
		float timeInPortal = Mth.lerp(partialTicks.getGameTimeDeltaPartialTick(false), handler.getPrevPortalAnimTime(), handler.getPortalAnimTime());
		if (timeInPortal > 0.0F) {
			if (timeInPortal < 1.0F) {
				timeInPortal *= timeInPortal;
				timeInPortal *= timeInPortal;
				timeInPortal = timeInPortal * 0.8F + 0.2F;
			}
			int i = ARGB.white(timeInPortal);
			TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(TofuBlocks.TOFU_PORTAL.get().defaultBlockState());
			guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, textureatlassprite, 0, 0,
					guiGraphics.guiWidth(),
					guiGraphics.guiHeight(),
					i);
		}
	}

	@SubscribeEvent
	public static void registerDimensionEffect(RegisterDimensionSpecialEffectsEvent event) {
		TofuDimensionEffects renderInfo = new TofuDimensionEffects();
		event.register(TofuCraftReload.prefix("renderer"), renderInfo);
	}

	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(TofuParticleTypes.TOFU_PORTAL.get(), TofuPortalParticle.Provider::new);
		event.registerSpriteSet(TofuParticleTypes.TOFU_PORTAL.get(), TofuPortalParticle.Provider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_SOYMILK_HANG.get(), SoymilkDripParticle.SoymilkHangProvider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_SOYMILK_FALL.get(), SoymilkDripParticle.SoymilkFallProvider::new);
		event.registerSpriteSet(TofuParticleTypes.SOYMILK_SPLASH.get(), SoymilkSplashParticle.Provider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_SOYSAUCE_HANG.get(), SoymilkDripParticle.SoysauceHangProvider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_SOYSAUCE_FALL.get(), SoymilkDripParticle.SoysauceFallProvider::new);
		event.registerSpriteSet(TofuParticleTypes.SOYSAUCE_SPLASH.get(), SoymilkSplashParticle.SoysauceProvider::new);
		event.registerSpriteSet(TofuParticleTypes.ZUNDA_CLOUD.get(), ParticleZundaCloud.CloudFactory::new);
		event.registerSpriteSet(TofuParticleTypes.STINK.get(), ParticleStink.StinkFactory::new);
		event.registerSpriteSet(TofuParticleTypes.ZUNDA_EXPLOSION.get(), ZundaExplosionParticle.Provider::new);
		event.registerSpecial(TofuParticleTypes.ZUNDA_EMIT.get(), new ZundaExplosionSeedParticle.Provider<>());
		event.registerSpriteSet(TofuParticleTypes.SIMPLE_STINKE.get(), ParticleSimpleStink.Provider::new);
	}
}

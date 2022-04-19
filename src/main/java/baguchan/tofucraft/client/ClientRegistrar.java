package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.render.FukumameRender;
import baguchan.tofucraft.client.render.NetherFukumameRender;
import baguchan.tofucraft.client.render.SoulFukumameRender;
import baguchan.tofucraft.client.render.entity.*;
import baguchan.tofucraft.client.render.item.TofuShieldBWLR;
import baguchan.tofucraft.client.render.tileentity.TofuBedRenderer;
import baguchan.tofucraft.client.render.tileentity.TofuChestRenderer;
import baguchan.tofucraft.client.screen.SaltFurnaceScreen;
import baguchan.tofucraft.client.screen.TFStorageScreen;
import baguchan.tofucraft.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {

	public static void renderEntity() {
	}

	public static void renderTileEntity() {
		BlockEntityRenderers.register(TofuBlockEntitys.TOFUBED.get(), TofuBedRenderer::new);
		BlockEntityRenderers.register(TofuBlockEntitys.TOFUCHEST.get(), TofuChestRenderer::new);
	}

	public static void renderBlockColor() {
		Minecraft.getInstance().getBlockColors().register((p_92621_, p_92622_, p_92623_, p_92624_) -> {
			return p_92622_ != null && p_92623_ != null ? BiomeColors.getAverageWaterColor(p_92622_, p_92623_) : -1;
		}, TofuBlocks.SALTPAN.get());
	}

	public static void renderBlockLayer() {
		setRenderLayer(TofuBlocks.SOYBEAN.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.SOYBEAN_NETHER.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.SOYBEAN_SOUL.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.LEEK_CROP.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFUTORCH_KINU.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUTORCH_MOMEN.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUTORCH_ISHI.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUTORCH_METAL.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_KINU.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_MOMEN.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_ISHI.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_METAL.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFULADDER_KINU.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_MOMEN.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_ISHI.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_ISHIBRICK.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_METAL.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFUDOOR_KINU.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_MOMEN.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_ISHI.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_METAL.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_HELL.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_SOUL.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.SAPLING_TOFU.get(), RenderType.cutout());
		setRenderLayer(TofuBlocks.LEAVES_TOFU.get(), RenderType.cutoutMipped());

		setRenderLayer(TofuBlocks.LEEK.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.ZUNDATOFU_MUSHROOM.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFU_PORTAL.get(), RenderType.translucent());
		setRenderLayer(TofuBlocks.SALTPAN.get(), RenderType.cutout());

		setRenderLayer(TofuBlocks.ANTENNA_BASIC.get(), RenderType.cutout());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block, type::equals);
	}

	public static void setup(FMLCommonSetupEvent event) {
		renderEntity();
		renderTileEntity();
		renderBlockColor();
		renderBlockLayer();

		ItemProperties.register(TofuItems.TOFU_SHIELD.get(), new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
			return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
		});

		MenuScreens.register(TofuContainers.SALT_FURNACE, SaltFurnaceScreen::new);
		MenuScreens.register(TofuContainers.TF_STORAGE, TFStorageScreen::new);
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TofuEntityTypes.TOFUCOW.get(), TofuCowRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUNIAN.get(), TofunianRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUFISH.get(), TofuFishRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSLIME.get(), TofuSlimeRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSPIDER.get(), TofuSpiderRender::new);

		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME.get(), FukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NETHER_FUKUMAME.get(), NetherFukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SOUL_FUKUMAME.get(), SoulFukumameRender::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TofuModelLayers.TOFUNIAN, TofunianModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFUSPIDER, TofuSpiderModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		if (event.getAtlas().location().equals(Sheets.BED_SHEET)) {
			event.addSprite(TofuBedRenderer.BED_TEXTURES);
		}
		if (event.getAtlas().location().equals(Sheets.CHEST_SHEET)) {
			event.addSprite(TofuChestRenderer.CHEST_LOCATION.texture());
			event.addSprite(TofuChestRenderer.CHEST_LOCATION_LEFT.texture());
			event.addSprite(TofuChestRenderer.CHEST_LOCATION_RIGHT.texture());
		}
		if (event.getAtlas().location().equals(Sheets.SHIELD_SHEET)) {
			event.addSprite(TofuShieldBWLR.SHIELD.texture());
		}
	}
}

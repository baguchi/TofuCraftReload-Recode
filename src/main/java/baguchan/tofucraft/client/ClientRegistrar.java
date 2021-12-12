package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.render.FukumameRender;
import baguchan.tofucraft.client.render.NetherFukumameRender;
import baguchan.tofucraft.client.render.SoulFukumameRender;
import baguchan.tofucraft.client.render.entity.*;
import baguchan.tofucraft.client.render.tileentity.TofuBedRenderer;
import baguchan.tofucraft.client.render.tileentity.TofuChestRenderer;
import baguchan.tofucraft.client.screen.SaltFurnaceScreen;
import baguchan.tofucraft.client.screen.TFStorageScreen;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuContainers;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
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
		BlockEntityRenderers.register(TofuBlockEntitys.TOFUBED, TofuBedRenderer::new);
		BlockEntityRenderers.register(TofuBlockEntitys.TOFUCHEST, TofuChestRenderer::new);
	}

	public static void renderBlockColor() {
		Minecraft.getInstance().getBlockColors().register((p_92621_, p_92622_, p_92623_, p_92624_) -> {
			return p_92622_ != null && p_92623_ != null ? BiomeColors.getAverageWaterColor(p_92622_, p_92623_) : -1;
		}, TofuBlocks.SALTPAN);
	}

	public static void renderBlockLayer() {
		setRenderLayer(TofuBlocks.SOYBEAN, RenderType.cutout());
		setRenderLayer(TofuBlocks.SOYBEAN_NETHER, RenderType.cutout());
		setRenderLayer(TofuBlocks.SOYBEAN_SOUL, RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFUTORCH_KINU, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUTORCH_MOMEN, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUTORCH_ISHI, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUTORCH_METAL, RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_KINU, RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_MOMEN, RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_ISHI, RenderType.cutout());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_METAL, RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFULADDER_KINU, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_MOMEN, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_ISHI, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_ISHIBRICK, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFULADDER_METAL, RenderType.cutout());

		setRenderLayer(TofuBlocks.SAPLING_TOFU, RenderType.cutout());
		setRenderLayer(TofuBlocks.LEAVES_TOFU, RenderType.cutoutMipped());

		setRenderLayer(TofuBlocks.LEEK, RenderType.cutout());

		setRenderLayer(TofuBlocks.ZUNDATOFU_MUSHROOM, RenderType.cutout());

		setRenderLayer(TofuBlocks.TOFU_PORTAL, RenderType.translucent());
		setRenderLayer(TofuBlocks.SALTPAN, RenderType.cutout());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block, type::equals);
	}

	public static void setup(FMLCommonSetupEvent event) {
		renderEntity();
		renderTileEntity();
		renderBlockColor();
		renderBlockLayer();
		MenuScreens.register(TofuContainers.SALT_FURNACE, SaltFurnaceScreen::new);
		MenuScreens.register(TofuContainers.TF_STORAGE, TFStorageScreen::new);
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TofuEntityTypes.TOFUCOW, TofuCowRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUNIAN, TofunianRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUFISH, TofuFishRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSLIME, TofuSlimeRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSPIDER, TofuSpiderRender::new);

		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME, FukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NETHER_FUKUMAME, NetherFukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SOUL_FUKUMAME, SoulFukumameRender::new);
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
	}
}

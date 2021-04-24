package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.render.TofuDimensionRenderInfo;
import baguchan.tofucraft.client.render.tileentity.TofuBedBlockRenderer;
import baguchan.tofucraft.client.render.tileentity.TofuChestBlockRenderer;
import baguchan.tofucraft.registry.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {

	public static void renderEntity() {
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.TOFUNIAN, baguchan.tofucraft.client.render.entity.TofunianRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.TRAVELER_TOFUNIAN, baguchan.tofucraft.client.render.entity.TravelerTofunianRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.TOFUCOW, baguchan.tofucraft.client.render.entity.TofuCowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.TOFUFISH, baguchan.tofucraft.client.render.entity.TofuFishRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.TOFUSLIME, baguchan.tofucraft.client.render.entity.TofuSlimeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.TOFUSPIDER, baguchan.tofucraft.client.render.entity.TofuSpiderRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.FUKUMAME, baguchan.tofucraft.client.render.entity.FukumameRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.NETHER_FUKUMAME, baguchan.tofucraft.client.render.entity.NetherFukumameRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.SOUL_FUKUMAME, baguchan.tofucraft.client.render.entity.SoulFukumameRender::new);
		RenderingRegistry.registerEntityRenderingHandler(TofuEntityTypes.ZUNDA_ARROW, baguchan.tofucraft.client.render.entity.ZundaArrowRender::new);
	}

	public static void renderTileEntity() {
		ClientRegistry.bindTileEntityRenderer(TofuTileEntitys.TOFUBED, TofuBedBlockRenderer::new);
		ClientRegistry.bindTileEntityRenderer(TofuTileEntitys.TOFUCHEST, TofuChestBlockRenderer::new);
	}

	public static void renderBlockColor() {
		Minecraft.getInstance().getBlockColors().register((state, reader, pos, color) ->
				(reader != null && pos != null) ? BiomeColors.getAverageWaterColor(reader, pos) : -1, TofuBlocks.SALTPAN);
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
		setRenderLayer(TofuBlocks.TOFUDOOR_KINU, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_MOMEN, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_ISHI, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFUDOOR_METAL, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFU_FLOWER, RenderType.cutout());
		setRenderLayer(TofuBlocks.POTTED_TOFU_FLOWER, RenderType.cutout());
		setRenderLayer(TofuBlocks.BLOCKLEEK, RenderType.cutout());
		setRenderLayer(TofuBlocks.ZUNDATOFU_MUSHROOM, RenderType.cutout());
		setRenderLayer(TofuBlocks.TOFU_PORTAL, RenderType.translucent());
		setRenderLayer(TofuBlocks.SALTPAN, RenderType.cutout());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		RenderTypeLookup.setRenderLayer(block, type::equals);
	}

	public static void setup(FMLCommonSetupEvent event) {
		renderEntity();
		renderTileEntity();
		renderBlockColor();
		renderBlockLayer();
		ItemModelsProperties.register(TofuItems.TOFUISHI_SHIELD, new ResourceLocation("blocking"), (p_239421_0_, p_239421_1_, p_239421_2_) ->
				(p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == p_239421_0_) ? 1.0F : 0.0F);
		ItemModelsProperties.register(TofuItems.TOFUMETAL_SHIELD, new ResourceLocation("blocking"), (p_239421_0_, p_239421_1_, p_239421_2_) ->
				(p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == p_239421_0_) ? 1.0F : 0.0F);
		ScreenManager.register(TofuContainers.SALT_FURNACE, baguchan.tofucraft.client.screen.SaltFurnaceScreen::new);
		DimensionRenderInfo.EFFECTS.put(TofuCraftReload.prefix("effect"), new TofuDimensionRenderInfo());
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		if (event.getMap().location().equals(Atlases.BED_SHEET))
			event.addSprite(TofuBedBlockRenderer.TOFUBED_LOCATION);
		if (event.getMap().location().equals(Atlases.CHEST_SHEET)) {
			event.addSprite(TofuChestBlockRenderer.TOFUCHEST_LOCATION);
			event.addSprite(TofuChestBlockRenderer.TOFUCHEST_LEFT_LOCATION);
			event.addSprite(TofuChestBlockRenderer.TOFUCHEST_RIGHT_LOCATION);
		}
	}
}

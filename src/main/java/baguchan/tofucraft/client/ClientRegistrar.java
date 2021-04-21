package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.render.TofuDimensionRenderInfo;
import baguchan.tofucraft.client.render.tileentity.TofuBedBlockRenderer;
import baguchan.tofucraft.client.render.tileentity.TofuChestBlockRenderer;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuContainers;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTileEntitys;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = "tofucraft", value = {Dist.CLIENT}, bus = EventBusSubscriber.Bus.MOD)
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
		Minecraft.func_71410_x().func_184125_al().func_186722_a((state, reader, pos, color) ->
				(reader != null && pos != null) ? BiomeColors.func_228363_c_(reader, pos) : -1, new Block[]{TofuBlocks.SALTPAN});
	}

	public static void renderBlockLayer() {
		setRenderLayer(TofuBlocks.SOYBEAN, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.SOYBEAN_NETHER, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.SOYBEAN_SOUL, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUTORCH_KINU, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUTORCH_MOMEN, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUTORCH_ISHI, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUTORCH_METAL, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_KINU, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_MOMEN, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_ISHI, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.WALLTOFUTORCH_METAL, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFULADDER_KINU, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFULADDER_MOMEN, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFULADDER_ISHI, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFULADDER_ISHIBRICK, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFULADDER_METAL, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUDOOR_KINU, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUDOOR_MOMEN, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUDOOR_ISHI, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFUDOOR_METAL, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.TOFU_FLOWER, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.POTTED_TOFU_FLOWER, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.BLOCKLEEK, RenderType.func_228643_e_());
		setRenderLayer(TofuBlocks.ZUNDATOFU_MUSHROOM, RenderType.func_228643_e_());
		setRenderLayer((Block) TofuBlocks.TOFU_PORTAL, RenderType.func_228645_f_());
		setRenderLayer(TofuBlocks.SALTPAN, RenderType.func_228643_e_());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		RenderTypeLookup.setRenderLayer(block, type::equals);
	}

	public static void setup(FMLCommonSetupEvent event) {
		renderEntity();
		renderTileEntity();
		renderBlockColor();
		renderBlockLayer();
		ItemModelsProperties.func_239418_a_(TofuItems.TOFUISHI_SHIELD, new ResourceLocation("blocking"), (p_239421_0_, p_239421_1_, p_239421_2_) ->
				(p_239421_2_ != null && p_239421_2_.func_184587_cr() && p_239421_2_.func_184607_cu() == p_239421_0_) ? 1.0F : 0.0F);
		ItemModelsProperties.func_239418_a_(TofuItems.TOFUMETAL_SHIELD, new ResourceLocation("blocking"), (p_239421_0_, p_239421_1_, p_239421_2_) ->
				(p_239421_2_ != null && p_239421_2_.func_184587_cr() && p_239421_2_.func_184607_cu() == p_239421_0_) ? 1.0F : 0.0F);
		ScreenManager.func_216911_a(TofuContainers.SALT_FURNACE, baguchan.tofucraft.client.screen.SaltFurnaceScreen::new);
		DimensionRenderInfo.field_239208_a_.put(TofuCraftReload.prefix("effect"), new TofuDimensionRenderInfo());
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		if (event.getMap().func_229223_g_().equals(Atlases.field_228743_b_))
			event.addSprite(TofuBedBlockRenderer.TOFUBED_LOCATION);
		if (event.getMap().func_229223_g_().equals(Atlases.field_228747_f_)) {
			event.addSprite(TofuChestBlockRenderer.TOFUCHEST_LOCATION);
			event.addSprite(TofuChestBlockRenderer.TOFUCHEST_LEFT_LOCATION);
			event.addSprite(TofuChestBlockRenderer.TOFUCHEST_RIGHT_LOCATION);
		}
	}
}

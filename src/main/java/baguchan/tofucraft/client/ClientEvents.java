package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.capability.TofuLivingCapability;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void onRenderOverlayPost(RenderGameOverlayEvent.Post event) {
		Minecraft minecraft = Minecraft.getInstance();
		Window window = minecraft.getWindow();
		LocalPlayer player = minecraft.player;
		if (player != null) {
			if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
				player.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).ifPresent(handler -> renderAetherPortalOverlay(event, minecraft, window, handler));
			}
		}
	}

	public static void renderAetherPortalOverlay(RenderGameOverlayEvent.Post event, Minecraft mc, Window window, TofuLivingCapability handler) {
		float timeInPortal = handler.getPrevPortalAnimTime() + (handler.getPortalAnimTime() - handler.getPrevPortalAnimTime()) * event.getPartialTicks();
		if (timeInPortal > 0.0F) {
			if (timeInPortal < 1.0F) {
				timeInPortal = timeInPortal * timeInPortal;
				timeInPortal = timeInPortal * timeInPortal;
				timeInPortal = timeInPortal * 0.8F + 0.2F;
			}

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.defaultBlendFunc();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			TextureAtlasSprite textureatlassprite = mc.getBlockRenderer().getBlockModelShaper().getParticleIcon(Blocks.NETHER_PORTAL.defaultBlockState());
			float f = textureatlassprite.getU0();
			float f1 = textureatlassprite.getV0();
			float f2 = textureatlassprite.getU1();
			float f3 = textureatlassprite.getV1();
			Tesselator tesselator = Tesselator.getInstance();
			BufferBuilder bufferbuilder = tesselator.getBuilder();
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(0.0D, (double) window.getScreenHeight(), -90.0D).uv(f, f3).endVertex();
			bufferbuilder.vertex((double) window.getScreenWidth(), (double) window.getScreenHeight(), -90.0D).uv(f2, f3).endVertex();
			bufferbuilder.vertex((double) window.getScreenWidth(), 0.0D, -90.0D).uv(f2, f1).endVertex();
			bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(f, f1).endVertex();
			tesselator.end();
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}

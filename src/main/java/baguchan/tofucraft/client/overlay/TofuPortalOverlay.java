package baguchan.tofucraft.client.overlay;

import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.registry.TofuAttachments;
import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class TofuPortalOverlay implements LayeredDraw.Layer {
	@Override
	public void render(GuiGraphics poseStack, DeltaTracker partialTick) {
		Minecraft mc = Minecraft.getInstance();
		TofuLivingCapability tofuLivingCapability = mc.player.getData(TofuAttachments.TOFU_LIVING);
		renderTofuPortalOverlay(poseStack, mc, partialTick.getGameTimeDeltaTicks(), poseStack.guiWidth(), poseStack.guiHeight(), tofuLivingCapability);

	}

	private void renderTofuPortalOverlay(GuiGraphics poseStack, Minecraft mc, float partialTick, int width, int height, TofuLivingCapability handler) {
		float timeInPortal = handler.getPrevPortalAnimTime() + (handler.getPortalAnimTime() - handler.getPrevPortalAnimTime()) * partialTick;
		if (timeInPortal > 0.0F) {
			if (timeInPortal < 1.0F) {
				timeInPortal = timeInPortal * timeInPortal;
				timeInPortal = timeInPortal * timeInPortal;
				timeInPortal = timeInPortal * 0.8F + 0.2F;
			}

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			TextureAtlasSprite textureatlassprite = mc.getBlockRenderer().getBlockModelShaper().getParticleIcon(TofuBlocks.TOFU_PORTAL.get().defaultBlockState());
			float f = textureatlassprite.getU0();
			float f1 = textureatlassprite.getV0();
			float f2 = textureatlassprite.getU1();
			float f3 = textureatlassprite.getV1();
			Tesselator tesselator = Tesselator.getInstance();
			BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.addVertex(0.0F, height, -90.0F).setUv(f, f3);
			bufferbuilder.addVertex(width, height, -90.0F).setUv(f2, f3);
			bufferbuilder.addVertex(width, 0.0F, -90.0F).setUv(f2, f1);
			bufferbuilder.addVertex(0.0F, 0.0F, -90.0F).setUv(f, f1);
			BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
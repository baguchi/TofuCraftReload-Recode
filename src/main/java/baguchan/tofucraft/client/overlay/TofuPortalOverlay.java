package baguchan.tofucraft.client.overlay;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

public class TofuPortalOverlay implements IGuiOverlay {
	@Override
	public void render(ExtendedGui gui, GuiGraphics poseStack, float partialTick, int width, int height) {
		Minecraft mc = Minecraft.getInstance();
		mc.player.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).ifPresent(handler -> renderTofuPortalOverlay(poseStack, mc, partialTick, width, height, handler));

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
			RenderSystem.defaultBlendFunc();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			TextureAtlasSprite textureatlassprite = mc.getBlockRenderer().getBlockModelShaper().getParticleIcon(TofuBlocks.TOFU_PORTAL.get().defaultBlockState());
			float f = textureatlassprite.getU0();
			float f1 = textureatlassprite.getV0();
			float f2 = textureatlassprite.getU1();
			float f3 = textureatlassprite.getV1();
			Tesselator tesselator = Tesselator.getInstance();
			BufferBuilder bufferbuilder = tesselator.getBuilder();
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(0.0D, (double) height, -90.0D).uv(f, f3).endVertex();
			bufferbuilder.vertex((double) width, (double) height, -90.0D).uv(f2, f3).endVertex();
			bufferbuilder.vertex((double) width, 0.0D, -90.0D).uv(f2, f1).endVertex();
			bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(f, f1).endVertex();
			tesselator.end();
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
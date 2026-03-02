package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.multiplayer.LevelLoadTracker;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

public class ReceivingTofuLevelScreen extends LevelLoadingScreen {
	private static final Component TRAVELING_IN_TOFU_PORTAL = Component.translatable("multiplayer.tofucraft.travel_tofuworld");

	private @Nullable TextureAtlasSprite cachedNetherPortalSprite;

	public ReceivingTofuLevelScreen(LevelLoadTracker levelReceived) {
		super(levelReceived, Reason.OTHER);
	}

	private TextureAtlasSprite getTofuPortalSprite() {
		if (this.cachedNetherPortalSprite != null) {
			return this.cachedNetherPortalSprite;
		} else {
			this.cachedNetherPortalSprite = this.minecraft
					.getBlockRenderer()
					.getBlockModel(TofuBlocks.TOFU_PORTAL.get().defaultBlockState())
					.particleMaterial().sprite();
			return this.cachedNetherPortalSprite;
		}
	}

	@Override
	public void render(GuiGraphics p_281489_, int p_282902_, int p_283018_, float p_281251_) {
		super.render(p_281489_, p_282902_, p_283018_, p_281251_);
		p_281489_.drawCenteredString(this.font, TRAVELING_IN_TOFU_PORTAL, this.width / 2, this.height / 2, 16777215);
	}

	@Override
	public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBlurredBackground(pGuiGraphics);
		pGuiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.getTofuPortalSprite(), 0, 0, pGuiGraphics.guiWidth(), pGuiGraphics.guiHeight(), 0);
		this.renderTransparentBackground(pGuiGraphics);
	}
}
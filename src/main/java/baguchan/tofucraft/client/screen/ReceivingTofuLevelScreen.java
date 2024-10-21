package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.function.BooleanSupplier;

public class ReceivingTofuLevelScreen extends ReceivingLevelScreen {
	private static final Component TRAVELING_IN_TOFU_PORTAL = Component.translatable("multiplayer.tofucraft.travel_tofuworld");

	@Nullable
	private TextureAtlasSprite cachedTofuPortalSprite;

	public ReceivingTofuLevelScreen(BooleanSupplier levelReceived) {
		super(levelReceived, Reason.OTHER);
	}

	private TextureAtlasSprite getTofuPortalSprite() {
		if (this.cachedTofuPortalSprite != null) {
			return this.cachedTofuPortalSprite;
		} else {
			this.cachedTofuPortalSprite = this.minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(TofuBlocks.TOFU_PORTAL.get().defaultBlockState());
			return this.cachedTofuPortalSprite;
		}
	}

	@Override
	public void render(GuiGraphics p_281489_, int p_282902_, int p_283018_, float p_281251_) {
		super.render(p_281489_, p_282902_, p_283018_, p_281251_);
		p_281489_.drawCenteredString(this.font, TRAVELING_IN_TOFU_PORTAL, this.width / 2, this.height / 2, 16777215);
	}

	@Override
	public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBlurredBackground();
		pGuiGraphics.blitSprite(RenderType::guiTextured, this.getTofuPortalSprite(), 0, 0, pGuiGraphics.guiWidth(), pGuiGraphics.guiHeight(), 0);
		this.renderTransparentBackground(pGuiGraphics);
	}
}
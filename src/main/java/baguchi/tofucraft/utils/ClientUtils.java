package baguchi.tofucraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import org.joml.Matrix3x2fStack;

public class ClientUtils {

	public static void playPortalSound(Player localPlayer) {
		Minecraft.getInstance()
				.getSoundManager()
				.play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, localPlayer.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));

	}


	public static void renderFluidStack(GuiGraphicsExtractor guiGraphics, Matrix3x2fStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluid.defaultFluidState()).stillMaterial().sprite();
		int xTileCount = desiredWidth / 16;
		int yTileCount = desiredHeight / 16;
		int yRemainder = desiredHeight - (yTileCount * 16);

		FluidTintSource fluidTintSource = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluid.defaultFluidState()).fluidTintSource();

		for (int yTile = 0; yTile <= yTileCount; yTile++) {

			int tileHeight = (yTile == yTileCount) ? yRemainder : 16;
			if (tileHeight > 0) {

				final int yStart = yPosition;

				int maskTop = 16 - tileHeight;
				int y = yStart - ((yTile + 1) * 16) + maskTop;
				if (fluidTintSource != null) {
					int color = fluidTintSource.color(fluid.defaultFluidState());
					guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, xPosition, y, desiredWidth, tileHeight, color);
				} else {
					guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, xPosition, y, desiredWidth, tileHeight);
				}
			}
		}
	}
}

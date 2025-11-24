package baguchi.tofucraft.client.render.dimension;

import baguchi.tofucraft.client.TofuWorldTextureManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.neoforged.neoforge.client.extensions.IDimensionSpecialEffectsExtension;
import org.joml.Matrix4f;

public class TofuWorldSpecialEffect implements IDimensionSpecialEffectsExtension {

	@Override
	public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {
		TofuWorldRenderer tofuWorldRenderer = TofuWorldTextureManager.INSTANCE.getTofuWorldRenderer();
		if (tofuWorldRenderer != null) {
			PoseStack poseStack = new PoseStack();
			setupFog.run();

			tofuWorldRenderer.renderTofuSunMoonAndStars(
					poseStack, skyRenderState.sunAngle,
					skyRenderState.moonAngle,
					skyRenderState.starAngle,
					skyRenderState.moonPhase,
					skyRenderState.rainBrightness,
					skyRenderState.starBrightness
			);
		}

		return true;
	}
}

package baguchi.tofucraft.client.render.dimension;

import baguchi.tofucraft.client.TofuWorldTextureManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.Matrix4fc;

public class TofuWorldSpecialEffect implements CustomSkyboxRenderer {

	@Override
	public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4fc modelViewMatrix, Runnable setupFog) {
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

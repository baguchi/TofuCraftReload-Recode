package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.model.AbstractTofunianModel;
import baguchi.tofucraft.client.render.state.AbstractTofunianRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Pose;


public class TofunianEyeLayer<T extends AbstractTofunianRenderState, M extends AbstractTofunianModel<T>> extends RenderLayer<T, M> {
	public static final Identifier LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian_eye.png");

	public TofunianEyeLayer(RenderLayerParent<T, M> tofunianRender) {
		super(tofunianRender);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T tofunianState, float v, float v1) {
		float f3 = (tofunianState.ageInTicks + tofunianState.id);

		if (!tofunianState.isBaby && !tofunianState.isInvisible && (0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F) || tofunianState.pose == Pose.SLEEPING) {
			renderColoredCutoutModel(this.getParentModel(), LOCATION, poseStack, submitNodeCollector, i, tofunianState, -1, 1);
		}
	}
}

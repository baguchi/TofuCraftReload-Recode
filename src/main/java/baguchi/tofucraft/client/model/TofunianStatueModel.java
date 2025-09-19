package baguchi.tofucraft.client.model;

import baguchi.tofucraft.client.render.blockentity.state.TofunianStateRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class TofunianStatueModel<T extends TofunianStateRenderState> extends Model<T> {
	public TofunianStatueModel(ModelPart root) {
		super(root, RenderType::entityCutoutNoCull);
	}
}
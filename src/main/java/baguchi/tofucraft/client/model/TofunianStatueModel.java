package baguchi.tofucraft.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;

public class TofunianStatueModel extends Model<Direction> {
	public TofunianStatueModel(ModelPart root) {
		super(root, RenderType::entityCutoutNoCull);
	}
}
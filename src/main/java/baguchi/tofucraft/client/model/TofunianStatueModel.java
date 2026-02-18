package baguchi.tofucraft.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.Direction;

public class TofunianStatueModel extends Model<Direction> {
	public TofunianStatueModel(ModelPart root) {
		super(root, RenderTypes::entityCutout);
	}
}
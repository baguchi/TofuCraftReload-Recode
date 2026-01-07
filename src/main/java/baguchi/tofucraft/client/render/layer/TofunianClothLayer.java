package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;

import java.time.LocalDate;
import java.time.temporal.ChronoField;


public class TofunianClothLayer<S extends TofunianRenderState> extends RenderLayer<S, TofunianModel<S>> {
	public static final Identifier BAGU_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagu_chan.png");

	public TofunianClothLayer(RenderLayerParent<S, TofunianModel<S>> tofunianRender) {
		super(tofunianRender);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, S tofunianRenderState, float v, float v1) {
		if (!tofunianRenderState.isInvisible && tofunianRenderState.clothTexture != null) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(tofunianRenderState), poseStack, submitNodeCollector, i, tofunianRenderState, -1, 2);

		}
	}

	public Identifier getTextureLocation(S entity) {
		if (entity.nameTag != null) {
			String s = ChatFormatting.stripFormatting(entity.nameTag.getString());
			if (s != null && "bagu_chan".equals(s)) {
				LocalDate localdate = LocalDate.now();
				int i = localdate.get(ChronoField.DAY_OF_MONTH);
				int j = localdate.get(ChronoField.MONTH_OF_YEAR);
				if (!(j == 10 && i == 31 || (j == 12 && i == 15))) {
					return BAGU_LOCATION;
				}
			}
		}

		return entity.clothTexture;
	}
}

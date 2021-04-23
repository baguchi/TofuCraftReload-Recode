package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.entity.TofunianEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class TofunianRoleLayer<T extends TofunianEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
	private final TofunianModel<T> tofunianModel = new TofunianModel();

	public TofunianRoleLayer(IEntityRenderer<T, M> tofunianRender) {
		super(tofunianRender);
	}

	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entitylivingbaseIn.getRole() != TofunianEntity.Roles.TOFUNIAN) {
			this.getParentModel().copyPropertiesTo(this.tofunianModel);
			this.tofunianModel.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.tofunianModel.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entitylivingbaseIn)));
			this.tofunianModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public ResourceLocation getTextureLocation(T entity) {
		String role = "";
		if (entity.getRole() != TofunianEntity.Roles.TOFUNIAN)
			role = entity.getRole().name().toLowerCase();
		return new ResourceLocation("tofucraft:textures/entity/tofunian/" + role + ".png");
	}
}

package baguchan.tofucraft.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSpiderModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer body;

	public ModelRenderer legL3;

	public ModelRenderer legR2;

	public ModelRenderer legL2;

	public ModelRenderer legL1;

	public ModelRenderer legR;

	public ModelRenderer body2;

	public ModelRenderer legR3;

	public ModelRenderer head;

	public ModelRenderer eyeL;

	public ModelRenderer mouseR;

	public ModelRenderer eyeR;

	public ModelRenderer mouseL;

	public TofuSpiderModel() {
		this.field_78090_t = 64;
		this.field_78089_u = 32;
		this.legR2 = new ModelRenderer((Model) this, 0, 18);
		this.legR2.func_78793_a(-3.4F, 0.0F, 4.0F);
		this.legR2.func_228302_a_(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legR2, 0.0F, -0.0F, -0.5009095F);
		this.legR3 = new ModelRenderer((Model) this, 0, 18);
		this.legR3.func_78793_a(-3.4F, 0.0F, 6.5F);
		this.legR3.func_228302_a_(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legR3, 0.0F, 0.4098033F, -0.5009095F);
		this.legR = new ModelRenderer((Model) this, 0, 18);
		this.legR.func_78793_a(-3.4F, 0.0F, 1.5F);
		this.legR.func_228302_a_(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legR, 0.0F, -0.4098033F, -0.5009095F);
		this.legL3 = new ModelRenderer((Model) this, 14, 18);
		this.legL3.func_78793_a(3.4F, 0.0F, 1.5F);
		this.legL3.func_228302_a_(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legL3, 0.0F, 0.4098033F, 0.5009095F);
		this.legL1 = new ModelRenderer((Model) this, 14, 18);
		this.legL1.func_78793_a(3.4F, 0.0F, 6.5F);
		this.legL1.func_228302_a_(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legL1, 0.0F, -0.4098033F, 0.5009095F);
		this.head = new ModelRenderer((Model) this, 32, 0);
		this.head.func_78793_a(0.0F, -0.4F, -0.7F);
		this.head.func_228302_a_(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.eyeL = new ModelRenderer((Model) this, 32, 8);
		this.eyeL.func_78793_a(2.1F, -0.5F, -4.3F);
		this.eyeL.func_228302_a_(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.body2 = new ModelRenderer((Model) this, 0, 13);
		this.body2.func_78793_a(0.0F, 0.0F, -1.0F);
		this.body2.func_228302_a_(-3.5F, -2.5F, 0.0F, 7.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.eyeR = new ModelRenderer((Model) this, 38, 8);
		this.eyeR.func_78793_a(-2.1F, -0.5F, -4.3F);
		this.eyeR.func_228302_a_(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.mouseL = new ModelRenderer((Model) this, 0, 0);
		this.mouseL.func_78793_a(0.5F, 0.8F, -5.2F);
		this.mouseL.func_228302_a_(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer((Model) this, 0, 0);
		this.body.func_78793_a(0.0F, 21.1F, -3.0F);
		this.body.func_228302_a_(-4.0F, -3.0F, 0.0F, 8.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.mouseR = new ModelRenderer((Model) this, 0, 0);
		this.mouseR.func_78793_a(-1.5F, 0.8F, -5.2F);
		this.mouseR.func_228302_a_(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.legL2 = new ModelRenderer((Model) this, 14, 18);
		this.legL2.func_78793_a(3.6F, 0.0F, 4.0F);
		this.legL2.func_228302_a_(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legL2, 0.0F, -0.0F, 0.5009095F);
		this.body.func_78792_a(this.legR2);
		this.body.func_78792_a(this.legR3);
		this.body.func_78792_a(this.legR);
		this.body.func_78792_a(this.legL3);
		this.body.func_78792_a(this.legL1);
		this.body.func_78792_a(this.head);
		this.head.func_78792_a(this.eyeL);
		this.body.func_78792_a(this.body2);
		this.head.func_78792_a(this.eyeR);
		this.head.func_78792_a(this.mouseL);
		this.head.func_78792_a(this.mouseR);
		this.body.func_78792_a(this.legL2);
	}

	public void func_225598_a_(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach(modelRenderer -> modelRenderer.func_228309_a_(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
	}

	public void func_225597_a_(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.field_78796_g = netHeadYaw * 0.017453292F;
		this.head.field_78795_f = headPitch * 0.017453292F;
		this.legR.field_78796_g = MathHelper.func_76126_a(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.4098033F;
		this.legR2.field_78796_g = MathHelper.func_76126_a(limbSwing * 0.6662F + 3.1415927F) * 0.5F * limbSwingAmount;
		this.legR3.field_78796_g = MathHelper.func_76126_a(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.4098033F;
		this.legL1.field_78796_g = MathHelper.func_76126_a(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.4098033F;
		this.legL2.field_78796_g = MathHelper.func_76126_a(limbSwing * 0.6662F + 3.1415927F) * 0.6F * limbSwingAmount;
		this.legL3.field_78796_g = MathHelper.func_76126_a(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.4098033F;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.field_78795_f = x;
		modelRenderer.field_78796_g = y;
		modelRenderer.field_78808_h = z;
	}
}

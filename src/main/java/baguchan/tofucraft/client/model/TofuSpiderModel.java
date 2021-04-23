package baguchan.tofucraft.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
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
		this.texWidth = 64;
		this.texHeight = 32;
		this.legR2 = new ModelRenderer(this, 0, 18);
		this.legR2.setPos(-3.4F, 0.0F, 4.0F);
		this.legR2.addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legR2, 0.0F, -0.0F, -0.5009095F);
		this.legR3 = new ModelRenderer(this, 0, 18);
		this.legR3.setPos(-3.4F, 0.0F, 6.5F);
		this.legR3.addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legR3, 0.0F, 0.4098033F, -0.5009095F);
		this.legR = new ModelRenderer(this, 0, 18);
		this.legR.setPos(-3.4F, 0.0F, 1.5F);
		this.legR.addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legR, 0.0F, -0.4098033F, -0.5009095F);
		this.legL3 = new ModelRenderer(this, 14, 18);
		this.legL3.setPos(3.4F, 0.0F, 1.5F);
		this.legL3.addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legL3, 0.0F, 0.4098033F, 0.5009095F);
		this.legL1 = new ModelRenderer(this, 14, 18);
		this.legL1.setPos(3.4F, 0.0F, 6.5F);
		this.legL1.addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legL1, 0.0F, -0.4098033F, 0.5009095F);
		this.head = new ModelRenderer(this, 32, 0);
		this.head.setPos(0.0F, -0.4F, -0.7F);
		this.head.addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.eyeL = new ModelRenderer(this, 32, 8);
		this.eyeL.setPos(2.1F, -0.5F, -4.3F);
		this.eyeL.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.body2 = new ModelRenderer(this, 0, 13);
		this.body2.setPos(0.0F, 0.0F, -1.0F);
		this.body2.addBox(-3.5F, -2.5F, 0.0F, 7.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.eyeR = new ModelRenderer(this, 38, 8);
		this.eyeR.setPos(-2.1F, -0.5F, -4.3F);
		this.eyeR.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.mouseL = new ModelRenderer(this, 0, 0);
		this.mouseL.setPos(0.5F, 0.8F, -5.2F);
		this.mouseL.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setPos(0.0F, 21.1F, -3.0F);
		this.body.addBox(-4.0F, -3.0F, 0.0F, 8.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.mouseR = new ModelRenderer(this, 0, 0);
		this.mouseR.setPos(-1.5F, 0.8F, -5.2F);
		this.mouseR.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.legL2 = new ModelRenderer(this, 14, 18);
		this.legL2.setPos(3.6F, 0.0F, 4.0F);
		this.legL2.addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		setRotateAngle(this.legL2, 0.0F, -0.0F, 0.5009095F);
		this.body.addChild(this.legR2);
		this.body.addChild(this.legR3);
		this.body.addChild(this.legR);
		this.body.addChild(this.legL3);
		this.body.addChild(this.legL1);
		this.body.addChild(this.head);
		this.head.addChild(this.eyeL);
		this.body.addChild(this.body2);
		this.head.addChild(this.eyeR);
		this.head.addChild(this.mouseL);
		this.head.addChild(this.mouseR);
		this.body.addChild(this.legL2);
	}

	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		ImmutableList.of(this.body).forEach(modelRenderer -> modelRenderer.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_));
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.xRot = headPitch * 0.017453292F;
		this.legR.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.4098033F;
		this.legR2.yRot = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.5F * limbSwingAmount;
		this.legR3.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.4098033F;
		this.legL1.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.4098033F;
		this.legL2.yRot = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.6F * limbSwingAmount;
		this.legL3.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.4098033F;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}

package baguchan.tofucraft.client.model;

import baguchan.tofucraft.entity.AbstractTofunianEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BipedTofunianModel<T extends AbstractTofunianEntity> extends BipedModel<T> {
	public BipedTofunianModel() {
		this(0.0F, false);
	}

	protected BipedTofunianModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
	}

	public BipedTofunianModel(float modelSize, boolean par2) {
		super(modelSize, 0.0F, 64, par2 ? 32 : 64);
		this.texWidth = 64;
		this.texHeight = 64;
		this.rightArm = new ModelRenderer(this, 28, 16);
		this.rightArm.setPos(-4.0F, 15.0F, 0.0F);
		this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736647217022F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setPos(0.0F, 14.0F, -0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -11.0F, -0.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.setPos(-1.4F, 18.0F, 0.0F);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 8, 16);
		this.body.setPos(0.0F, 14.0F, 0.0F);
		this.body.addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.setPos(1.4F, 18.0F, 0.0F);
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 28, 16);
		this.leftArm.mirror = true;
		this.leftArm.setPos(4.0F, 15.0F, 0.0F);
		this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.10000736647217022F);
		this.hat = new ModelRenderer(this, 32, 0);
		this.hat.setPos(0.0F, 14.0F, -0.0F);
		this.hat.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, 0.5F, 0.5F);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float f6 = 12.0F;
		this.rightArm.x = -4.0F;
		this.leftArm.x = 4.0F;
		this.rightArm.z = 0.0F;
		this.leftArm.z = 0.0F;
		this.rightLeg.z = 0.0F;
		this.leftLeg.z = 0.0F;
		this.rightLeg.y = 6.0F + f6;
		this.leftLeg.y = 6.0F + f6;
		this.head.z = -0.0F;
		this.head.y = f6 + 2.0F;
		if (this.riding) {
			this.body.xRot = 0.5F;
			this.rightArm.xRot += 0.4F;
			this.leftArm.xRot += 0.4F;
			this.rightLeg.z = 3.9F;
			this.leftLeg.z = 3.9F;
			this.rightLeg.y = 6.0F + f6 + 0.2F;
			this.leftLeg.y = 6.0F + f6 + 0.2F;
			this.head.y = f6 + 2.0F + 4.2F;
			this.body.y = 17.2F;
			this.leftArm.y = 18.2F;
			this.rightArm.y = 18.2F;
		} else {
			this.body.xRot = 0.0F;
			this.rightLeg.z = 0.0F;
			this.leftLeg.z = 0.0F;
			this.rightLeg.y = 6.0F + f6;
			this.leftLeg.y = 6.0F + f6;
			this.head.y = f6 + 2.0F;
			this.body.y = 14.0F;
			this.leftArm.y = 15.0F;
			this.rightArm.y = 15.0F;
		}
		boolean flag = (entityIn.getUnhappyCounter() > 0);
		if (flag) {
			this.head.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
			this.head.xRot = 0.4F;
		} else {
			this.head.zRot = 0.0F;
		}
		if (this.young) {
			this.head.y = f6 + 1.0F;
		} else {
			this.head.y = f6 + 2.0F;
		}
		this.hat.x = this.head.x;
		this.hat.y = this.head.y;
		this.hat.z = this.head.z;
		this.hat.xRot = this.head.xRot;
		this.hat.yRot = this.head.yRot;
		this.hat.zRot = this.head.zRot;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}

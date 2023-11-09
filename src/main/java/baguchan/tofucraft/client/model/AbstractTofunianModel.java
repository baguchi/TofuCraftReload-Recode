package baguchan.tofucraft.client.model;

import baguchan.tofucraft.entity.AbstractTofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class AbstractTofunianModel<T extends AbstractTofunian> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {
	public final ModelPart realRoot;
	public final ModelPart root;
	public final ModelPart head;
	public final ModelPart hat;
	public final ModelPart body;
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	public final ModelPart rightArm;
	public final ModelPart leftArm;

	public AbstractTofunianModel(ModelPart p_170688_) {
		this.realRoot = p_170688_;
		this.root = p_170688_.getChild("root");
		this.head = this.root.getChild("head");
		this.hat = this.head.getChild("hat");
		this.body = this.root.getChild("body");
		this.leftLeg = this.root.getChild("left_leg");
		this.rightLeg = this.root.getChild("right_leg");
		this.leftArm = this.root.getChild("left_arm");
		this.rightArm = this.root.getChild("right_arm");
	}

	public ModelPart root() {
		return this.realRoot;
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.xRot = headPitch * 0.017453292F;

		boolean flag = entity.getUnhappyCounter() > 0;

		if (flag) {
			this.head.zRot = 0.3F * Mth.sin(0.45F * ageInTicks);
			this.head.xRot = 0.4F;
		} else {
			this.head.zRot = 0.0F;
		}

		if (this.riding) {
			this.rightArm.xRot = -0.62831855F;
			this.rightArm.yRot = 0.0F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.xRot = -0.62831855F;
			this.leftArm.yRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightLeg.xRot = -1.4137167F;
			this.rightLeg.yRot = 0.31415927F;
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = -1.4137167F;
			this.leftLeg.yRot = -0.31415927F;
			this.leftLeg.zRot = -0.07853982F;
		} else {
			this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
			this.rightArm.yRot = 0.0F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
			this.leftArm.yRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
			this.rightLeg.yRot = 0.0F;
			this.rightLeg.zRot = 0.0F;
			this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount * 0.5F;
			this.leftLeg.yRot = 0.0F;
			this.leftLeg.zRot = 0.0F;
		}

		if (attackTime > 0) {
			if (entity.getMainArm() == HumanoidArm.RIGHT) {
				this.rightArm.xRot = attackTime * -0.75F;
				this.rightArm.zRot = attackTime * -0.5F;
			} else {
				this.rightArm.xRot = attackTime * -0.75F;
				this.rightArm.zRot = attackTime * 0.5F;
			}
		}

		float f6 = 12.0F;

		if (entity.isBaby()) {
			this.head.y = -7.0F;
			this.rightArm.visible = false;
			this.leftArm.visible = false;
			this.body.visible = false;
		} else {
			this.head.y = -11.0F;
			this.rightArm.visible = true;
			this.leftArm.visible = true;
			this.body.visible = true;
		}
	}

	private ModelPart getArm(HumanoidArm p_102923_) {
		return p_102923_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
	}

	public ModelPart getHead() {
		return this.head;
	}

	public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
		if (!this.young) {
			this.root.translateAndRotate(p_102926_);
			this.getArm(p_102925_).translateAndRotate(p_102926_);
			p_102926_.translate(0, -0.15D, 0);
			p_102926_.scale(0.95F, 0.95F, 0.95F);
		}
	}
}
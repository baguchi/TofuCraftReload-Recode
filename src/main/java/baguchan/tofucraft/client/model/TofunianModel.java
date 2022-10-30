package baguchan.tofucraft.client.model;

import baguchan.tofucraft.client.animation.definitions.TofunianAnimation;
import baguchan.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class TofunianModel<T extends Tofunian> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart body;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public TofunianModel(ModelPart p_170688_) {
		this.root = p_170688_;
		this.head = p_170688_.getChild("head");
		this.hat = this.head.getChild("hat");
		this.body = p_170688_.getChild("body");
		this.leftLeg = p_170688_.getChild("left_leg");
		this.rightLeg = p_170688_.getChild("right_leg");
		this.leftArm = p_170688_.getChild("left_arm");
		this.rightArm = p_170688_.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition var0 = new MeshDefinition();
		PartDefinition var1 = var0.getRoot();
		PartDefinition var2 = var1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F).addBox(-1.5F, -11.0F, -0.0F, 3.0F, 3.0F, 0.0F), PartPose.offset(0.0F, 14.0F, -0.0F));
		var2.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, -0.0F));
		var1.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F), PartPose.offset(0.0F, 14.0F, 0.0F));

		var1.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(-1.4F, 18.0F, 0.0F));
		var1.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(1.4F, 18.0F, 0.0F));
		var1.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.offset(-4.0F, 15.0F, 0.0F));
		var1.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.offset(4.0F, 15.0F, 0.0F));
		return LayerDefinition.create(var0, 64, 64);
	}

	public ModelPart root() {
		return this.root;
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
			this.head.y = 18.0F;
			this.rightArm.visible = false;
			this.leftArm.visible = false;
			this.body.visible = false;
		} else {
			this.head.y = 14.0F;
			this.rightArm.visible = true;
			this.leftArm.visible = true;
			this.body.visible = true;
		}
		this.animate(entity.agreeAnimationState, TofunianAnimation.TOFUNIAN_AGREE_TRADE, ageInTicks);
	}

	private ModelPart getArm(HumanoidArm p_102923_) {
		return p_102923_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
	}

	public ModelPart getHead() {
		return this.head;
	}

	public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
		this.getArm(p_102925_).translateAndRotate(p_102926_);
	}
}
package baguchan.tofucraft.client.model;

import baguchan.tofucraft.client.animation.definitions.TofunianAnimation;
import baguchan.tofucraft.client.render.state.AbstractTofunianRenderState;
import baguchan.tofucraft.entity.AbstractTofunian;
import baguchi.bagus_lib.client.layer.IArmor;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class AbstractTofunianModel<T extends AbstractTofunianRenderState> extends EntityModel<T> implements ArmedModel, HeadedModel, IArmor {
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
		super(p_170688_);
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

	@Override
	public void setupAnim(T entity) {
		super.setupAnim(entity);
		this.head.yRot = entity.yRot * 0.017453292F;
		this.head.xRot = entity.xRot * 0.017453292F;

		boolean flag = entity.unhappyCounter > 0;

		if (flag) {
			this.head.zRot = 0.3F * Mth.sin(0.45F * entity.ageInTicks);
			this.head.xRot = 0.4F;
		} else {
			this.head.zRot = 0.0F;
		}

		if (entity.riding) {
			this.rightArm.xRot = -0.62831855F;
			this.leftArm.xRot = -0.62831855F;
			this.rightLeg.xRot = -1.4137167F;
			this.leftLeg.xRot = -1.4137167F;
		} else {
			this.rightArm.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F + 3.1415927F) * 2.0F * entity.walkAnimationSpeed * 0.5F;
			this.leftArm.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F) * 2.0F * entity.walkAnimationSpeed * 0.5F;
			this.rightLeg.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F) * 1.4F * entity.walkAnimationSpeed * 0.5F;
			this.leftLeg.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F + 3.1415927F) * 1.4F * entity.walkAnimationSpeed * 0.5F;
		}

		if (entity.attackTime > 0) {
			if (entity.mainArm == HumanoidArm.RIGHT) {
				this.rightArm.xRot = entity.attackTime * -0.75F;
				this.rightArm.zRot = entity.attackTime * -0.5F;
			} else {
				this.rightArm.xRot = entity.attackTime * -0.75F;
				this.rightArm.zRot = entity.attackTime * 0.5F;
			}
		}

		float f6 = 12.0F;

		if (entity.isBaby) {
			this.applyStatic(TofunianAnimation.BABY);
			this.rightArm.visible = false;
			this.leftArm.visible = false;
			this.body.visible = false;
		} else {
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
		this.root.translateAndRotate(p_102926_);
		this.getArm(p_102925_).translateAndRotate(p_102926_);
		p_102926_.translate(0, -0.15D, 0);
		p_102926_.scale(0.95F, 0.95F, 0.95F);
	}


	@Override
	public void translateToHead(ModelPart modelPart, PoseStack poseStack) {
		this.root.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
	}

	@Override
	public void translateToChest(ModelPart modelPart, PoseStack poseStack) {
		this.root.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
		poseStack.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	public void translateToLeg(ModelPart modelPart, PoseStack poseStack) {
		this.root.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
		poseStack.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public void translateToChestPat(ModelPart modelPart, PoseStack poseStack) {
		this.root.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
		poseStack.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	public Iterable<ModelPart> rightHandArmors() {
		return ImmutableList.of(this.rightArm);
	}

	@Override
	public Iterable<ModelPart> leftHandArmors() {
		return ImmutableList.of(this.leftArm);
	}

	@Override
	public Iterable<ModelPart> rightLegPartArmors() {
		return ImmutableList.of(this.rightLeg);
	}

	@Override
	public Iterable<ModelPart> leftLegPartArmors() {
		return ImmutableList.of(this.leftLeg);
	}

	@Override
	public Iterable<ModelPart> bodyPartArmors() {
		return ImmutableList.of(this.body);
	}

	@Override
	public Iterable<ModelPart> headPartArmors() {
		return ImmutableList.of(this.head);
	}
}
package baguchan.tofucraft.client.model;// Made with Blockbench 4.3.0
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

import baguchan.tofucraft.client.animation.definitions.TofuGandlemAnimation;
import baguchan.tofucraft.entity.TofuGandlem;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.List;

public class TofuGandlemModel<T extends TofuGandlem> extends HierarchicalModel<T> {
	private final ModelPart baseRoot;
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart core;
	private final ModelPart core2;

	private final List<ModelPart> coreModelParts;

	public TofuGandlemModel(ModelPart root) {
		this.baseRoot = root;
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.core = this.root.getChild("core");
		this.core2 = this.root.getChild("core2");
		this.coreModelParts = ImmutableList.of(this.core, this.core2);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition core2 = root.addOrReplaceChild("core2", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, -3.0F));

		PartDefinition right_hand = root.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(0, 21).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -16.0F, 0.0F));

		PartDefinition right_hand2 = right_hand.addOrReplaceChild("right_hand2", CubeListBuilder.create().texOffs(0, 21).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition left_hand = root.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -16.0F, 0.0F));

		PartDefinition left_hand2 = left_hand.addOrReplaceChild("left_hand2", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);


		if (entity.isSleep()) {
			this.head.xRot = 0.4F;
			this.head.yRot = 0.0F;
		} else {
			this.head.xRot = headPitch * 0.017453292F;
			this.head.yRot = netHeadYaw * 0.017453292F;
		}

		this.animate(entity.attackAnimationState, TofuGandlemAnimation.ATTACK, ageInTicks);
		this.animate(entity.shootAnimationState, TofuGandlemAnimation.SHOOT, ageInTicks);
		this.animate(entity.shootingAnimationState, TofuGandlemAnimation.SHOOTING, ageInTicks);
		this.animate(entity.rushAnimationState, TofuGandlemAnimation.RUSH, ageInTicks);
		this.animate(entity.idleAnimationState, TofuGandlemAnimation.IDLE, ageInTicks);
		this.animate(entity.deathAnimationState, TofuGandlemAnimation.DEATH, ageInTicks);
		this.animate(entity.chargeAnimationState, TofuGandlemAnimation.CHARGE, ageInTicks);
		this.animate(entity.chargeStopAnimationState, TofuGandlemAnimation.CHARGE_STOP, ageInTicks);
		this.animate(entity.chargeFailAnimationState, TofuGandlemAnimation.CHARGE_FAIL, ageInTicks);
	}

	public List<ModelPart> getCoreModelParts() {
		return coreModelParts;
	}

	@Override
	public ModelPart root() {
		return this.baseRoot;
	}
}
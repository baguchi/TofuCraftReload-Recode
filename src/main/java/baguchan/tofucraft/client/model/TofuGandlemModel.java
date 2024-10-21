package baguchan.tofucraft.client.model;// Made with Blockbench 4.3.0
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

import baguchan.tofucraft.client.animation.definitions.TofuGandlemAnimation;
import baguchan.tofucraft.client.render.state.TofuGandlemRenderState;
import baguchan.tofucraft.entity.TofuGandlem;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.List;

public class TofuGandlemModel<T extends TofuGandlemRenderState> extends EntityModel<T> {
	private final ModelPart baseRoot;
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart core;
	private final ModelPart core2;

	private final List<ModelPart> coreModelParts;

	public TofuGandlemModel(ModelPart root) {
		super(root);
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

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition core2 = root.addOrReplaceChild("core2", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition right_hand = root.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(0, 21).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -4.0F, 0.0F));

		PartDefinition right_hand2 = right_hand.addOrReplaceChild("right_hand2", CubeListBuilder.create().texOffs(0, 21).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition left_hand = root.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -4.0F, 0.0F));

		PartDefinition left_hand2 = left_hand.addOrReplaceChild("left_hand2", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity) {
		super.setupAnim(entity);


		if (entity.sleep) {
			this.head.xRot = 0.4F;
			this.head.yRot = 0.0F;
		} else {
			this.head.xRot = entity.xRot * 0.017453292F;
			this.head.yRot = entity.yRot * 0.017453292F;
		}

		this.animate(entity.attackAnimationState, TofuGandlemAnimation.ATTACK, entity.ageInTicks);
		this.animate(entity.shootAnimationState, TofuGandlemAnimation.SHOOT, entity.ageInTicks);
		this.animate(entity.shootingAnimationState, TofuGandlemAnimation.SHOOTING, entity.ageInTicks);
		this.animate(entity.rushAnimationState, TofuGandlemAnimation.RUSH, entity.ageInTicks);
		this.animate(entity.idleAnimationState, TofuGandlemAnimation.IDLE, entity.ageInTicks);
		this.animate(entity.deathAnimationState, TofuGandlemAnimation.DEATH, entity.ageInTicks);
		this.animate(entity.chargeAnimationState, TofuGandlemAnimation.CHARGE, entity.ageInTicks);
		this.animate(entity.chargeStopAnimationState, TofuGandlemAnimation.CHARGE_STOP, entity.ageInTicks);
		this.animate(entity.chargeFailAnimationState, TofuGandlemAnimation.CHARGE_FAIL, entity.ageInTicks);
	}

	public List<ModelPart> getCoreModelParts() {
		return coreModelParts;
	}
}
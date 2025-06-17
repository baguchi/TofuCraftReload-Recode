package baguchi.tofucraft.client.model;// Made with Blockbench 4.3.0
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

import baguchi.tofucraft.client.animation.definitions.TofuGandlemAnimation;
import baguchi.tofucraft.client.render.state.TofuGandlemRenderState;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.animation.KeyframeAnimation;
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

	private final KeyframeAnimation attackAnimation;
	private final KeyframeAnimation preShootAnimation;
	private final KeyframeAnimation stopShootAnimation;
	private final KeyframeAnimation shootAnimation;
	private final KeyframeAnimation shootingAnimation;
	private final KeyframeAnimation deathAnimation;
	private final KeyframeAnimation rushAnimation;
	private final KeyframeAnimation chargeAnimation;
	private final KeyframeAnimation chargeStopAnimation;
	private final KeyframeAnimation chargeFailAnimation;

	public TofuGandlemModel(ModelPart root) {
		super(root);
		this.baseRoot = root;
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.core = this.root.getChild("core");
		this.core2 = this.root.getChild("core2");
		this.coreModelParts = ImmutableList.of(this.core, this.core2);
		this.attackAnimation = TofuGandlemAnimation.attack.bake(root);
		this.shootAnimation = TofuGandlemAnimation.shoot.bake(root);
		this.preShootAnimation = TofuGandlemAnimation.pre_shoot.bake(root);
		this.stopShootAnimation = TofuGandlemAnimation.stop_shoot.bake(root);
		this.shootingAnimation = TofuGandlemAnimation.shooting.bake(root);
		this.deathAnimation = TofuGandlemAnimation.death.bake(root);
		this.rushAnimation = TofuGandlemAnimation.RUSH.bake(root);
		this.chargeAnimation = TofuGandlemAnimation.charge.bake(root);
		this.chargeStopAnimation = TofuGandlemAnimation.charge_stop.bake(root);
		this.chargeFailAnimation = TofuGandlemAnimation.charge_fail.bake(root);
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

		attackAnimation.apply(entity.attackAnimationState, entity.ageInTicks);
		shootAnimation.apply(entity.shootAnimationState, entity.ageInTicks);
		preShootAnimation.apply(entity.preShootAnimationState, entity.ageInTicks);
		stopShootAnimation.apply(entity.stopShootAnimationState, entity.ageInTicks);
		shootingAnimation.apply(entity.shootingAnimationState, entity.ageInTicks);
		rushAnimation.apply(entity.rushAnimationState, entity.ageInTicks);
		deathAnimation.apply(entity.deathAnimationState, entity.ageInTicks);
		chargeAnimation.apply(entity.chargeAnimationState, entity.ageInTicks);
		chargeStopAnimation.apply(entity.chargeStopAnimationState, entity.ageInTicks);
		chargeFailAnimation.apply(entity.chargeFailAnimationState, entity.ageInTicks);
	}

	public List<ModelPart> getCoreModelParts() {
		return coreModelParts;
	}
}
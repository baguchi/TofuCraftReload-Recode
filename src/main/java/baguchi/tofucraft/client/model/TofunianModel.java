package baguchi.tofucraft.client.model;

import baguchi.tofucraft.client.animation.definitions.TofunianAnimation;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class TofunianModel<T extends TofunianRenderState> extends AbstractTofunianModel<T> {

	private final KeyframeAnimation happyAnimation;
	private final KeyframeAnimation eatAnimation;
	private final KeyframeAnimation waveAnimation;
	private final KeyframeAnimation waveChildAnimation;
	private final KeyframeAnimation cryAnimation;
	private final KeyframeAnimation avoidAnimation;
	private final KeyframeAnimation sitAnimation;


	public TofunianModel(ModelPart root) {
		super(root);
		this.happyAnimation = TofunianAnimation.HAPPY.bake(root);
		this.eatAnimation = TofunianAnimation.EAT.bake(root);
		this.waveAnimation = TofunianAnimation.wave.bake(root);
		this.waveChildAnimation = TofunianAnimation.wave_child.bake(root);
		this.cryAnimation = TofunianAnimation.CRY.bake(root);
		this.avoidAnimation = TofunianAnimation.AVOIDING.bake(root);
		this.sitAnimation = TofunianAnimation.SIT.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("roots", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(), PartPose.offset(1.5F, -6.0F, 0.0F));

		PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -6.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -11.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -11.5F, 0.0F));

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(), PartPose.offset(3.0F, -11.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(T entity) {
		super.setupAnim(entity);
		this.head.yRot = entity.yRot * 0.017453292F;
		if (entity.actions != Tofunian.Actions.NORMAL) {
			this.rightArm.xRot = 0.0F;
			this.leftArm.xRot = 0.0F;
		}
		this.happyAnimation.apply(entity.happyAnimationState, entity.ageInTicks);
		this.eatAnimation.apply(entity.eatFoodAnimationState, entity.ageInTicks);
		if (entity.isBaby) {
			this.waveAnimation.apply(entity.waveAnimationState, entity.ageInTicks);
		} else {
			this.waveChildAnimation.apply(entity.waveAnimationState, entity.ageInTicks);
		}

		if (entity.actions == Tofunian.Actions.CRY) {
			this.head.xRot = 0.0F;
			this.head.yRot = 0.0F;
			this.cryAnimation.applyStatic();
		} else if (entity.actions == Tofunian.Actions.AVOID) {
			this.avoidAnimation.applyStatic();
		} else if (entity.actions == Tofunian.Actions.SIT) {
			this.rightArm.xRot = -0.62831855F;
			this.rightArm.yRot = 0.0F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.xRot = -0.62831855F;
			this.leftArm.yRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightLeg.xRot = (float) (-Math.PI / 2F);
			this.rightLeg.yRot = 0.31415927F;
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = (float) (-Math.PI / 2F);
			this.leftLeg.yRot = -0.31415927F;
			this.leftLeg.zRot = -0.07853982F;
			this.sitAnimation.applyStatic();
		}
	}

}
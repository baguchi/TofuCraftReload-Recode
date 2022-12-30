package baguchan.tofucraft.client.model;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.tofucraft.entity.TofuGolem;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class TofuGolemModel<T extends TofuGolem> extends HierarchicalModel<T> {
	private final ModelPart roots;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart handR;
	private final ModelPart handL;

	public TofuGolemModel(ModelPart root) {
		this.roots = root.getChild("roots");
		this.body = this.roots.getChild("body");
		this.head = this.body.getChild("head");
		this.handR = this.body.getChild("handR");
		this.handL = this.body.getChild("handL");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition roots = partdefinition.addOrReplaceChild("roots", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition body = roots.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 14.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 40).addBox(-3.0F, 14.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition handR = body.addOrReplaceChild("handR", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition handL = body.addOrReplaceChild("handL", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.xRot = headPitch * 0.017453292F;

		if (entity.isAggressive()) {
			AnimationUtils.swingWeaponDown(this.handR, this.handL, entity, this.attackTime, ageInTicks);
		} else {
			this.handR.xRot = 0.0F;
			this.handL.xRot = 0.0F;
			this.handR.yRot = 0.0F;
			this.handL.yRot = 0.0F;
		}
	}

	@Override
	public ModelPart root() {
		return this.roots;
	}
}
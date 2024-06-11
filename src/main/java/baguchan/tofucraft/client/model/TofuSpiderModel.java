package baguchan.tofucraft.client.model;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class TofuSpiderModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart legR;
	private final ModelPart legR2;
	private final ModelPart legR3;
	private final ModelPart legL;
	private final ModelPart legL2;
	private final ModelPart legL3;

	public TofuSpiderModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.legR = this.body.getChild("legR");
		this.legR2 = this.body.getChild("legR2");
		this.legR3 = this.body.getChild("legR3");
		this.legL = this.body.getChild("legL");
		this.legL2 = this.body.getChild("legL2");
		this.legL3 = this.body.getChild("legL3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.1F, -3.0F));

		PartDefinition legR2 = body.addOrReplaceChild("legR2", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, 0.0F, 4.0F, 0.0F, 0.0F, 0.5009F));

		PartDefinition legR3 = body.addOrReplaceChild("legR3", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, 0.0F, 6.5F, 0.0F, -0.4098F, 0.5009F));

		PartDefinition legR = body.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, 0.0F, 1.5F, 0.0F, 0.4098F, 0.5009F));

		PartDefinition legL3 = body.addOrReplaceChild("legL3", CubeListBuilder.create().texOffs(14, 18).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, 0.0F, 1.5F, 0.0F, -0.4098F, -0.5009F));

		PartDefinition legL1 = body.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(14, 18).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, 0.0F, 6.5F, 0.0F, 0.4098F, -0.5009F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.4F, -0.7F));

		PartDefinition eyeL = head.addOrReplaceChild("eyeL", CubeListBuilder.create().texOffs(32, 8).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.1F, -0.5F, -4.3F));

		PartDefinition eyeR = head.addOrReplaceChild("eyeR", CubeListBuilder.create().texOffs(38, 8).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.1F, -0.5F, -4.3F));

		PartDefinition mouseL = head.addOrReplaceChild("mouseL", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.8F, -5.2F));

		PartDefinition mouseR = head.addOrReplaceChild("mouseR", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.8F, -5.2F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 13).addBox(-3.5F, -2.5F, 0.0F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition legL2 = body.addOrReplaceChild("legL2", CubeListBuilder.create().texOffs(14, 18).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6F, 0.0F, 4.0F, 0.0F, 0.0F, -0.5009F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.legR.yRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.4098033F;
		this.legR2.yRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 0.5F * limbSwingAmount;
		this.legR3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.4098033F;
		this.legL.yRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.4098033F;
		this.legL2.yRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 0.6F * limbSwingAmount;
		this.legL3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.4098033F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int p_350308_) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
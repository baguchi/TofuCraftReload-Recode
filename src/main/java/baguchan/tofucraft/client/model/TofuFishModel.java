package baguchan.tofucraft.client.model;// Made with Blockbench 4.4.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class TofuFishModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftFin;
	private final ModelPart rightFin;
	private final ModelPart tailfin;

	public TofuFishModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leftFin = root.getChild("leftFin");
		this.rightFin = root.getChild("rightFin");
		this.tailfin = root.getChild("tailfin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, -6.0F, 1.0F, 3.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 0).mirror().addBox(0.0F, -7.0F, 1.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 0).mirror().addBox(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -5.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-1.5F, -3.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 22.0F, -5.0F));

		PartDefinition leftFin = partdefinition.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 23.0F, -3.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition rightFin = partdefinition.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 23.0F, -3.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition tailfin = partdefinition.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(0.0F, -6.0F, 0.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.tailfin.yRot = -f * 0.45F * Mth.sin(0.6F * limbSwingAmount);
	}

	public ModelPart root() {
		return this.root;
	}
}
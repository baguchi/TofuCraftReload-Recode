package baguchi.tofucraft.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ZundaBusterModel<T extends ProjectileRenderState> extends EntityModel<T> {
	private final ModelPart core;
	private final ModelPart bone;
	private final ModelPart bone2;

	public ZundaBusterModel(ModelPart root) {
		super(root);
		this.core = root.getChild("core");
		this.bone = this.core.getChild("bone");
		this.bone2 = this.core.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bone = core.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, -29).addBox(0.0F, -13.5F, -16.0F, 0.0F, 27.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone2 = core.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, -29).addBox(0.0F, -13.5F, -16.0F, 0.0F, 27.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}
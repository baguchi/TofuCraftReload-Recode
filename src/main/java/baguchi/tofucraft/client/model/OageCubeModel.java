package baguchi.tofucraft.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;

import java.util.Arrays;

public class OageCubeModel<T extends SlimeRenderState> extends EntityModel<T> {
	private final ModelPart inside;
	private final ModelPart[] bodyCubes = new ModelPart[8];

	public OageCubeModel(ModelPart root) {
		super(root);
		this.inside = root.getChild("inside");
		Arrays.setAll(this.bodyCubes, (p_170709_) -> root.getChild(getSegmentName(p_170709_)));
	}

	private static String getSegmentName(int p_170706_) {
		return "outside_" + p_170706_;
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition inside = partdefinition.addOrReplaceChild("inside", CubeListBuilder.create().texOffs(24, 40).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition outside_0 = partdefinition.addOrReplaceChild("outside_0", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 16.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_1 = partdefinition.addOrReplaceChild("outside_1", CubeListBuilder.create().texOffs(0, 9).addBox(-4.0F, 17.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_2 = partdefinition.addOrReplaceChild("outside_2", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, 18.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_3 = partdefinition.addOrReplaceChild("outside_3", CubeListBuilder.create().texOffs(0, 27).addBox(-4.0F, 19.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_4 = partdefinition.addOrReplaceChild("outside_4", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 20.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_5 = partdefinition.addOrReplaceChild("outside_5", CubeListBuilder.create().texOffs(32, 9).addBox(-4.0F, 21.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_6 = partdefinition.addOrReplaceChild("outside_6", CubeListBuilder.create().texOffs(32, 18).addBox(-4.0F, 22.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outside_7 = partdefinition.addOrReplaceChild("outside_7", CubeListBuilder.create().texOffs(32, 27).addBox(-4.0F, 23.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T p_365333_) {
		super.setupAnim(p_365333_);
		float f = Math.max(0.0F, p_365333_.squish);

		for (int i = 0; i < this.bodyCubes.length; ++i) {
			this.bodyCubes[i].y = (float) (-(4 - i)) * f * 1.7F;
		}

	}

}
package baguchan.tofucraft.client.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.tofucraft.client.animation.definitions.TofuPufferAnimation;
import baguchan.tofucraft.entity.TofuPuffer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class TofuPufferModel<T extends TofuPuffer> extends HierarchicalModel<T> {
	private final ModelPart realRoot;
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart fin_r;
	private final ModelPart fin_l;

	public TofuPufferModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.realRoot = root;
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.fin_r = this.body.getChild("fin_r");
		this.fin_l = this.body.getChild("fin_l");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fin_r = body.addOrReplaceChild("fin_r", CubeListBuilder.create().texOffs(-6, 16).addBox(-7.0F, 0.0F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition fin_l = body.addOrReplaceChild("fin_l", CubeListBuilder.create().texOffs(-6, 16).mirror().addBox(0.0F, 0.0F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(TofuPuffer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(TofuPufferAnimation.swim, limbSwing, limbSwingAmount, 2.0F, 2.5F);
	}

	@Override
	public ModelPart root() {
		return this.realRoot;
	}
}
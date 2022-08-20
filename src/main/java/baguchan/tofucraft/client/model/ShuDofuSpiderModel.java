package baguchan.tofucraft.client.model;

import baguchan.tofucraft.client.animation.definitions.ShuDofuSpiderAnimation;
import baguchan.tofucraft.entity.ShuDofuSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;

public class ShuDofuSpiderModel<T extends ShuDofuSpider> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart neck;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;
	private final ModelPart bone;
	private final ModelPart bone13;
	private final ModelPart bone23;
	private final ModelPart bone8;
	private final ModelPart bone18;
	private final ModelPart bone28;


	public ShuDofuSpiderModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.root = root.getChild("root");
		this.neck = this.root.getChild("neck");
		this.head = this.neck.getChild("head");
		this.leg1 = this.neck.getChild("leg1");
		this.leg2 = this.neck.getChild("leg2");
		this.leg3 = this.neck.getChild("leg3");
		this.leg4 = this.neck.getChild("leg4");
		this.leg5 = this.neck.getChild("leg5");
		this.leg6 = this.neck.getChild("leg6");
		this.bone = this.leg1.getChild("bone");
		this.bone13 = this.leg3.getChild("bone13");
		this.bone23 = this.leg5.getChild("bone23");
		this.bone8 = this.leg2.getChild("bone8");
		this.bone18 = this.leg4.getChild("bone18");
		this.bone28 = this.leg6.getChild("bone28");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.02F, -17.54F, -3.52F));

		PartDefinition neck_front_r1 = neck.addOrReplaceChild("neck_front_r1", CubeListBuilder.create().texOffs(0, 103).addBox(-8.0F, -8.5F, -8.5F, 16.0F, 13.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition bone6 = neck.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 61).addBox(-13.0F, -10.2693F, -1.4327F, 26.0F, 21.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.26F, -1.22F, 4.66F, 0.1745F, 0.0F, 0.0F));

		PartDefinition neck_back_r1 = bone6.addOrReplaceChild("neck_back_r1", CubeListBuilder.create().texOffs(76, 85).addBox(-11.0F, -12.0F, -10.5F, 22.0F, 16.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2693F, 24.0673F, 0.4363F, 0.0F, 0.0F));

		PartDefinition bone33 = bone6.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(122, 119).addBox(-5.8247F, -0.6409F, 0.7162F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.75F, -16.0193F, 8.5673F, -0.519F, 0.4801F, 0.0085F));

		PartDefinition bone34 = bone6.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(47, 110).addBox(-5.1753F, -0.6409F, 0.7162F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.73F, -16.0193F, 8.5673F, -0.519F, -0.4801F, -0.0085F));

		PartDefinition bone35 = bone6.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(0, 61).addBox(-5.8247F, 1.3591F, 0.7162F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -16.0193F, 3.5673F, -0.519F, 0.4801F, 0.0085F));

		PartDefinition bone36 = bone6.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(0, 30).addBox(-4.1753F, 1.3591F, 0.7162F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.23F, -16.0193F, 3.5673F, -0.519F, -0.4801F, -0.0085F));

		PartDefinition body = bone6.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.24F, -2.1693F, 13.7273F, 0.0436F, 0.0F, 0.0F));

		PartDefinition bone7 = body.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.7532F, 17.9781F));

		PartDefinition body_r1 = bone7.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(40, 103).addBox(-4.5F, -4.5F, 16.5F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-16.5F, -16.5F, -18.5F, 33.0F, 26.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.8923F, 10.3301F, 0.7854F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(101, 0).addBox(-10.0F, -8.0F, -16.0F, 20.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.18F, -6.14F, -4.4F, 0.0873F, 0.0F, 0.0F));

		PartDefinition tooth_left = head.addOrReplaceChild("tooth_left", CubeListBuilder.create().texOffs(0, 0).addBox(-1.8214F, -2.3333F, -12.1785F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(-4.8714F, -2.0033F, -11.8985F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9835F, 5.6019F, -13.0492F, 0.2618F, -0.3927F, 0.0F));

		PartDefinition tooth_right = head.addOrReplaceChild("tooth_right", CubeListBuilder.create().texOffs(0, 15).addBox(-1.6602F, -2.5401F, -11.5281F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.2898F, -2.2101F, -11.2481F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.27F, 5.97F, -13.42F, 0.2618F, 0.3927F, 0.0F));

		PartDefinition leg1 = neck.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(116, 145).addBox(-17.1765F, -3.8397F, -3.9965F, 19.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(104, 192).addBox(-24.1765F, -2.8397F, -2.9965F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4355F, 0.1509F, -1.4883F, -0.2058F, -0.284F, 0.6404F));

		PartDefinition bone = leg1.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(136, 32).addBox(-21.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-28.5269F, -2.891F, -0.6806F, 0.0F, 0.0F, -1.9199F));

		PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-18.2696F, 1.0113F, -0.116F));

		PartDefinition bone40 = bone5.addOrReplaceChild("bone40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.9659F, -0.2588F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition bone4 = bone40.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(170, 145).addBox(-62.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(42.7896F, 35.7887F, 8.416F));

		PartDefinition bone2 = bone40.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(133, 182).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.385F, -0.4477F, -1.3183F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone3 = bone40.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(182, 127).addBox(-7.1272F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2304F, -0.0113F, 1.616F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leg2 = neck.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(70, 137).addBox(-0.6763F, -5.478F, -3.9965F, 19.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(78, 192).addBox(18.3237F, -4.478F, -2.9965F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3955F, 2.1509F, -1.4883F, -0.2058F, 0.284F, -0.6404F));

		PartDefinition bone8 = leg2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(133, 51).addBox(-3.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(29.6741F, -4.5293F, -0.6806F, 0.0F, 0.0F, 1.9199F));

		PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(18.2696F, 1.0113F, -0.116F));

		PartDefinition bone37 = bone9.addOrReplaceChild("bone37", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9659F, -0.2588F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition bone10 = bone37.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(44, 170).addBox(-9.5F, -2.5F, -3.0F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(9.7304F, -0.5113F, 0.116F));

		PartDefinition bone11 = bone37.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(87, 182).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.385F, -0.4477F, -1.3183F, 0.0F, -0.0873F, 0.0F));

		PartDefinition bone12 = bone37.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(181, 177).addBox(-10.8728F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.2304F, -0.0113F, 1.616F, 0.0F, 0.0873F, 0.0F));

		PartDefinition leg3 = neck.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(46, 153).addBox(-15.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(26, 191).addBox(-22.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.99F, -2.14F, 6.42F, 0.0F, 0.0F, 0.6109F));

		PartDefinition bone13 = leg3.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(122, 127).addBox(-21.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, -1.9199F));

		PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(-18.2696F, 1.0113F, -0.116F));

		PartDefinition bone41 = bone14.addOrReplaceChild("bone41", CubeListBuilder.create(), PartPose.offsetAndRotation(0.9659F, -0.2588F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition bone15 = bone41.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(142, 161).addBox(-62.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(42.7896F, 35.7887F, 8.416F));

		PartDefinition bone16 = bone41.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(0, 176).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.385F, -0.4477F, -1.3183F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone17 = bone41.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(173, 21).addBox(-7.1272F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2304F, -0.0113F, 1.616F, 0.0F, -0.0873F, 0.0F));

		PartDefinition bone44 = bone14.addOrReplaceChild("bone44", CubeListBuilder.create(), PartPose.offset(20.2696F, -0.7253F, -7.2116F));

		PartDefinition bone45 = bone14.addOrReplaceChild("bone45", CubeListBuilder.create(), PartPose.offset(21.2653F, 6.7637F, 7.3263F));

		PartDefinition leg4 = neck.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(156, 92).addBox(0.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(52, 191).addBox(15.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.95F, -2.14F, 6.42F, 0.0F, 0.0F, -0.6109F));

		PartDefinition bone18 = leg4.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 131).addBox(-12.5F, -2.9122F, -4.9718F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.7647F, 7.1559F, -0.8122F, 0.0F, 0.0F, 1.9199F));

		PartDefinition bone42 = bone18.addOrReplaceChild("bone42", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7123F, 7.2385F));

		PartDefinition bone43 = bone18.addOrReplaceChild("bone43", CubeListBuilder.create(), PartPose.offset(0.9957F, -8.2012F, -7.2994F));

		PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offset(9.2696F, 1.0991F, -0.0878F));

		PartDefinition bone38 = bone19.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9659F, -0.2588F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition bone20 = bone38.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(0, 165).addBox(43.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-42.7896F, 35.7887F, 8.416F));

		PartDefinition bone22 = bone38.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(180, 79).addBox(-10.8728F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.2304F, -0.0113F, 1.616F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone21 = bone38.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(41, 181).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.385F, -0.4477F, -1.3183F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leg5 = neck.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 149).addBox(-14.7741F, -2.3802F, -4.4466F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(173, 187).addBox(-21.7741F, -1.3802F, -3.4466F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4374F, -2.4227F, 14.9245F, 0.1789F, 0.2489F, 0.6333F));

		PartDefinition bone23 = leg5.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(62, 119).addBox(-21.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.1245F, -1.4315F, -1.1307F, 0.0F, 0.0F, -1.9199F));

		PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(-17.3037F, 0.7525F, -0.116F, 0.0F, 0.0F, -0.2618F));

		PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(92, 161).addBox(-62.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(42.7896F, 35.7887F, 8.416F));

		PartDefinition bone26 = bone24.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(173, 11).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.385F, -0.4477F, -1.3183F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone27 = bone24.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(140, 172).addBox(-7.1272F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2304F, -0.0113F, 1.616F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leg6 = neck.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(148, 111).addBox(0.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 186).addBox(15.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.95F, -2.14F, 14.42F, 0.1789F, -0.2489F, -0.6333F));

		PartDefinition bone28 = leg6.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(73, 61).addBox(-3.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, 1.9199F));

		PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create(), PartPose.offset(18.2696F, 1.0113F, -0.116F));

		PartDefinition bone39 = bone29.addOrReplaceChild("bone39", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9659F, -0.2588F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition bone30 = bone39.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(157, 0).addBox(43.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-42.7896F, 35.7887F, 8.416F));

		PartDefinition bone31 = bone39.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(94, 172).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.385F, -0.4477F, -1.3183F, 0.0F, -0.0873F, 0.0F));

		PartDefinition bone32 = bone39.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(143, 69).addBox(-10.8728F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.2304F, -0.0113F, 1.616F, 0.0F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		var direction = entity.getDirection();
		var pos = entity.blockPosition();
		var air = Blocks.AIR.defaultBlockState();
		this.head.xRot = headPitch * 0.017453292F;
		this.head.yRot = netHeadYaw * 0.017453292F;
		if (!entity.isJump()) {
			if (direction == Direction.NORTH) {

				var rBlock = entity.level.getBlockState(pos.below().west().west());
				var rBlockF = entity.level.getBlockState(pos.below().west().west().north());
				var rBlockB = entity.level.getBlockState(pos.below().west().west().south());
				if (rBlock == air && rBlockF == air && rBlockB == air) {
					this.leg2.zRot = 0.12F;
					this.leg4.zRot = 0.12F;
					this.leg6.zRot = 0.12F;
					this.bone8.zRot = 1.2F;
					this.bone18.zRot = 1.2F;
					this.bone28.zRot = 1.2F;
				}
				var lBlock = entity.level.getBlockState(pos.below().east().east());
				var lBlockF = entity.level.getBlockState(pos.below().east().east().north());
				var lBlockB = entity.level.getBlockState(pos.below().east().east().south());
				if (lBlock == air && lBlockF == air && lBlockB == air) {
					this.leg1.zRot = -0.12F;
					this.leg3.zRot = -0.12F;
					this.leg5.zRot = -0.12F;
					this.bone.zRot = -1.2F;
					this.bone13.zRot = -1.2F;
					this.bone23.zRot = -1.2F;
				}

			} else if (direction == Direction.EAST) {
				var rBlock = entity.level.getBlockState(pos.below().north().north());
				var rBlockF = entity.level.getBlockState(pos.below().north().north().east());
				var rBlockB = entity.level.getBlockState(pos.below().north().north().west());
				if (rBlock == air && rBlockF == air && rBlockB == air) {
					this.leg2.zRot = 0.12F;
					this.leg4.zRot = 0.12F;
					this.leg6.zRot = 0.12F;
					this.bone8.zRot = 1.2F;
					this.bone18.zRot = 1.2F;
					this.bone28.zRot = 1.2F;
				}
				var lBlock = entity.level.getBlockState(pos.below().south().south());
				var lBlockF = entity.level.getBlockState(pos.below().south().south().east());
				var lBlockB = entity.level.getBlockState(pos.below().south().south().west());
				if (lBlock == air && lBlockF == air && lBlockB == air) {
					this.leg1.zRot = -0.12F;
					this.leg3.zRot = -0.12F;
					this.leg5.zRot = -0.12F;
					this.bone.zRot = -1.2F;
					this.bone13.zRot = -1.2F;
					this.bone23.zRot = -1.2F;
				}
			} else if (direction == Direction.WEST) {
				var rBlock = entity.level.getBlockState(pos.below().south().south());
				var rBlockF = entity.level.getBlockState(pos.below().south().south().west());
				var rBlockB = entity.level.getBlockState(pos.below().south().south().east());
				if (rBlock == air && rBlockF == air && rBlockB == air) {
					this.leg2.zRot = 0.12F;
					this.leg4.zRot = 0.12F;
					this.leg6.zRot = 0.12F;
					this.bone8.zRot = 1.2F;
					this.bone18.zRot = 1.2F;
					this.bone28.zRot = 1.2F;
				}
				var lBlock = entity.level.getBlockState(pos.below().north().north());
				var lBlockF = entity.level.getBlockState(pos.below().north().north().west());
				var lBlockB = entity.level.getBlockState(pos.below().north().north().east());
				if (lBlock == air && lBlockF == air && lBlockB == air) {
					this.leg1.zRot = -0.12F;
					this.leg3.zRot = -0.12F;
					this.leg5.zRot = -0.12F;
					this.bone.zRot = -1.2F;
					this.bone13.zRot = -1.2F;
					this.bone23.zRot = -1.2F;
				}
			} else if (direction == Direction.SOUTH) {
				var rBlock = entity.level.getBlockState(pos.below().east().east());
				var rBlockF = entity.level.getBlockState(pos.below().east().east().south());
				var rBlockB = entity.level.getBlockState(pos.below().east().east().north());
				if (rBlock == air && rBlockF == air && rBlockB == air) {
					this.leg2.zRot = 0.12F;
					this.leg4.zRot = 0.12F;
					this.leg6.zRot = 0.12F;
					this.bone8.zRot = 1.2F;
					this.bone18.zRot = 1.2F;
					this.bone28.zRot = 1.2F;
				}
				var lBlock = entity.level.getBlockState(pos.below().west().west());
				var lBlockF = entity.level.getBlockState(pos.below().west().west().south());
				var lBlockB = entity.level.getBlockState(pos.below().west().west().north());
				if (lBlock == air && lBlockF == air && lBlockB == air) {
					this.leg1.zRot = -0.12F;
					this.leg3.zRot = -0.12F;
					this.leg5.zRot = -0.12F;
					this.bone.zRot = -1.2F;
					this.bone13.zRot = -1.2F;
					this.bone23.zRot = -1.2F;
				}
			}
		}


		this.animate(entity.idleAnimationState, ShuDofuSpiderAnimation.IDLE, ageInTicks);
		this.animate(entity.walkAnimationState, ShuDofuSpiderAnimation.WALK, ageInTicks);
		this.animate(entity.attackAnimationState, ShuDofuSpiderAnimation.SWIPE, ageInTicks);
		this.animate(entity.deathAnimationState, ShuDofuSpiderAnimation.DEATH, ageInTicks);
		this.animate(entity.jumpAnimationState, ShuDofuSpiderAnimation.JUMP_ATTACK, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}

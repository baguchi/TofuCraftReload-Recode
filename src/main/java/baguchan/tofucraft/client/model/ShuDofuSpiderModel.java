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
		this.head = this.root.getChild("head");
		this.neck = this.root.getChild("neck");
		this.leg1 = this.root.getChild("leg1");
		this.leg2 = this.root.getChild("leg2");
		this.leg3 = this.root.getChild("leg3");
		this.leg4 = this.root.getChild("leg4");
		this.leg5 = this.root.getChild("leg5");
		this.leg6 = this.root.getChild("leg6");
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

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(119, 0).addBox(-10.0F, -8.0F, -16.0F, 20.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.16F, -18.68F, -7.92F));

		PartDefinition tooth_left = head.addOrReplaceChild("tooth_left", CubeListBuilder.create().texOffs(0, 0).addBox(-1.8214F, -2.3333F, -12.1785F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(-4.8714F, -2.0033F, -11.8985F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9835F, 5.6019F, -13.0492F, 0.2618F, -0.3927F, 0.0F));

		PartDefinition tooth_right = head.addOrReplaceChild("tooth_right", CubeListBuilder.create().texOffs(0, 15).addBox(-1.6602F, -2.5401F, -11.5281F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.2898F, -2.2101F, -11.2481F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.27F, 5.97F, -13.42F, 0.2618F, 0.3927F, 0.0F));

		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(156, 32).addBox(-9.0F, -8.5F, -5.5F, 18.0F, 17.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, -17.54F, -3.52F, 0.0873F, 0.0F, 0.0F));

		PartDefinition bone6 = neck.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(107, 107).addBox(-15.0F, -12.2693F, -1.4327F, 30.0F, 25.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.26F, -1.22F, 4.66F, 0.1745F, 0.0F, 0.0F));

		PartDefinition bone33 = bone6.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(95, 88).addBox(-8.0F, -2.5F, 0.0F, 13.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.75F, -16.0193F, 8.5673F, -0.519F, 0.4801F, 0.0085F));

		PartDefinition bone34 = bone6.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(0, 30).addBox(-5.0F, -2.5F, 0.0F, 13.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.73F, -16.0193F, 8.5673F, -0.519F, -0.4801F, -0.0085F));

		PartDefinition bone35 = bone6.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(119, 32).addBox(-6.0F, -0.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -16.0193F, 3.5673F, -0.519F, 0.4801F, 0.0085F));

		PartDefinition bone36 = bone6.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(0, 94).addBox(-4.0F, -0.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.23F, -16.0193F, 3.5673F, -0.519F, -0.4801F, -0.0085F));

		PartDefinition body = bone6.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-20.5F, -15.7468F, -0.5219F, 41.0F, 33.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.24F, -2.1693F, 13.7273F, 0.0436F, 0.0F, 0.0F));

		PartDefinition bone7 = body.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 70).addBox(-16.5F, -12.5F, -14.5F, 33.0F, 25.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.7532F, 17.9781F));

		PartDefinition leg1 = root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(175, 0).addBox(-15.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(201, 117).addBox(-22.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.97F, -19.68F, -5.1F, -0.2058F, -0.284F, 0.6404F));

		PartDefinition bone = leg1.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(140, 149).addBox(-21.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, -1.9199F));

		PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-18.2696F, 1.0113F, -0.116F));

		PartDefinition bone4 = bone5.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(184, 106).addBox(-62.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(42.7896F, 35.7887F, 8.416F));

		PartDefinition bone2 = bone5.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(179, 198).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.385F, -0.4477F, -1.3183F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone3 = bone5.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(133, 198).addBox(-7.1272F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2304F, -0.0113F, 1.616F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leg2 = root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(138, 167).addBox(0.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(200, 143).addBox(15.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.97F, -19.68F, -5.1F, -0.2058F, 0.284F, -0.6404F));

		PartDefinition bone8 = leg2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(70, 149).addBox(-3.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, 1.9199F));

		PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(18.2696F, 1.0113F, -0.116F));

		PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(184, 78).addBox(43.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-42.7896F, 35.7887F, 8.416F));

		PartDefinition bone11 = bone9.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(194, 89).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.385F, -0.4477F, -1.3183F, 0.0F, -0.0873F, 0.0F));

		PartDefinition bone12 = bone9.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(87, 194).addBox(-10.8728F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.2304F, -0.0113F, 1.616F, 0.0F, 0.0873F, 0.0F));

		PartDefinition leg3 = root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(46, 167).addBox(-15.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(70, 134).addBox(-22.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.97F, -19.68F, 2.9F, 0.0F, 0.0F, 0.6109F));

		PartDefinition bone13 = leg3.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(124, 88).addBox(-21.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, -1.9199F));

		PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(-18.2696F, 1.0113F, -0.116F));

		PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(44, 183).addBox(-62.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(42.7896F, 35.7887F, 8.416F));

		PartDefinition bone16 = bone14.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(191, 16).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.385F, -0.4477F, -1.3183F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone17 = bone14.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(190, 188).addBox(-7.1272F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2304F, -0.0113F, 1.616F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leg4 = root.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(92, 167).addBox(0.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 199).addBox(15.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.97F, -19.68F, 2.9F, 0.0F, 0.0F, -0.6109F));

		PartDefinition bone18 = leg4.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 142).addBox(-3.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, 1.9199F));

		PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offset(18.2696F, 1.0113F, -0.116F));

		PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(94, 183).addBox(43.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-42.7896F, 35.7887F, 8.416F));

		PartDefinition bone21 = bone19.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(41, 194).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.385F, -0.4477F, -1.3183F, 0.0F, -0.0873F, 0.0F));

		PartDefinition bone22 = bone19.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(193, 60).addBox(-10.8728F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.2304F, -0.0113F, 1.616F, 0.0F, 0.0873F, 0.0F));

		PartDefinition leg5 = root.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 160).addBox(-9.7701F, 1.4407F, -3.8452F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 82).addBox(-16.7701F, 2.4407F, -2.8452F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.4174F, -25.9627F, 11.4045F, 0.1789F, 0.2489F, 0.6333F));

		PartDefinition bone23 = leg5.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(0, 124).addBox(-21.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.1205F, 2.3894F, -0.5292F, 0.0F, 0.0F, -1.9199F));

		PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offset(-18.2696F, 1.0113F, -0.116F));

		PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(178, 177).addBox(-62.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(42.7896F, 35.7887F, 8.416F));

		PartDefinition bone26 = bone24.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(0, 189).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.385F, -0.4477F, -1.3183F, 0.0F, 0.0873F, 0.0F));

		PartDefinition bone27 = bone24.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(144, 188).addBox(-7.1272F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.2304F, -0.0113F, 1.616F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leg6 = root.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(155, 62).addBox(0.41F, -2.28F, -4.1F, 15.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 70).addBox(15.41F, -1.28F, -3.1F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.97F, -19.68F, 10.9F, 0.1789F, -0.2489F, -0.6333F));

		PartDefinition bone28 = leg6.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(95, 70).addBox(-3.5F, -3.0F, -5.0F, 25.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.7604F, -1.3313F, -0.784F, 0.0F, 0.0F, 1.9199F));

		PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create(), PartPose.offset(18.2696F, 1.0113F, -0.116F));

		PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(0, 177).addBox(43.02F, -38.8F, -11.3F, 19.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-42.7896F, 35.7887F, 8.416F));

		PartDefinition bone31 = bone29.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(184, 167).addBox(-9.0F, -2.4993F, -2.4999F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.385F, -0.4477F, -1.3183F, 0.0F, -0.0873F, 0.0F));

		PartDefinition bone32 = bone29.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(60, 124).addBox(-10.8728F, -2.9364F, -2.5897F, 18.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.2304F, -0.0113F, 1.616F, 0.0F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		var direction = entity.getDirection();
		var pos = entity.blockPosition();
		var air = Blocks.AIR.defaultBlockState();
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


		this.animate(entity.idleAnimationState, ShuDofuSpiderAnimation.IDLE, ageInTicks);
		this.animate(entity.walkAnimationState, ShuDofuSpiderAnimation.WALK, ageInTicks);
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

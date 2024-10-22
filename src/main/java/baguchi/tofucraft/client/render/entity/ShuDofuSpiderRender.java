package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.ShuDofuSpiderModel;
import baguchi.tofucraft.client.render.state.ShuDofuSpiderRenderState;
import baguchi.tofucraft.entity.ShuDofuSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ShuDofuSpiderRender extends MobRenderer<ShuDofuSpider, ShuDofuSpiderRenderState, ShuDofuSpiderModel> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/shudofuspider/shudofuspider.png");
	private static final ResourceLocation CRACK_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/shudofuspider/shudofuspider_angry_layer.png");


	public ShuDofuSpiderRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new ShuDofuSpiderModel(p_173956_.bakeLayer(TofuModelLayers.SHUDOFUSPIDER)), 0.5F);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, ShuDofuSpiderRenderState p_363277_, float p_116987_, float p_116988_) {

				if (p_363277_.angry) {
					VertexConsumer vertexconsumer = p_116984_.getBuffer(this.renderType());
					this.getParentModel().renderToBuffer(p_116983_, vertexconsumer, 1728640, OverlayTexture.NO_OVERLAY);
				}
			}

			@Override
			public RenderType renderType() {
				return RenderType.eyes(CRACK_LOCATION);
			}
		});
	}

	@Override
	public ShuDofuSpiderRenderState createRenderState() {
		return new ShuDofuSpiderRenderState();
	}

	@Override
	public void extractRenderState(ShuDofuSpider p_362733_, ShuDofuSpiderRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.attackAnimationState.copyFrom(p_362733_.attackAnimationState);
		p_360515_.deathAnimationState.copyFrom(p_362733_.deathAnimationState);
		p_360515_.graspAnimationState.copyFrom(p_362733_.graspAnimationState);
		p_360515_.graspPreAnimationState.copyFrom(p_362733_.graspPreAnimationState);
		p_360515_.idleAnimationState.copyFrom(p_362733_.idleAnimationState);
		p_360515_.jumpAnimationState.copyFrom(p_362733_.jumpAnimationState);
		p_360515_.leftLegAnimation = p_362733_.getLeftLegAnimationScale(p_361157_);
		p_360515_.rightLegAnimation = p_362733_.getRightLegAnimationScale(p_361157_);
	}

	@Override
	protected float getFlipDegrees() {
		return 0.0F;
	}

	@Override
	protected void scale(ShuDofuSpiderRenderState p_362272_, PoseStack p_115315_) {
		super.scale(p_362272_, p_115315_);
		float var4 = p_362272_.scale;
		this.shadowRadius = 2.0F;

		p_115315_.scale(var4, var4, var4);
	}

	@Override
	public ResourceLocation getTextureLocation(ShuDofuSpiderRenderState p_114029_) {
		return LOCATION;
	}
}

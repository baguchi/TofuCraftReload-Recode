package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.ShuDofuSpiderModel;
import baguchi.tofucraft.client.render.state.ShuDofuSpiderRenderState;
import baguchi.tofucraft.entity.ShuDofuSpider;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.HitboxRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.entity.PartEntity;

public class ShuDofuSpiderRender extends MobRenderer<ShuDofuSpider, ShuDofuSpiderRenderState, ShuDofuSpiderModel> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/shudofuspider/shudofuspider.png");
	private static final ResourceLocation CRACK_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/shudofuspider/shudofuspider_angry_layer.png");


	public ShuDofuSpiderRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new ShuDofuSpiderModel(p_173956_.bakeLayer(TofuModelLayers.SHUDOFUSPIDER)), 0.5F);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public void submit(PoseStack p_433452_, SubmitNodeCollector p_433171_, int p_434650_, ShuDofuSpiderRenderState p_435883_, float p_433542_, float p_435619_) {
				if (p_435883_.angry) {
					p_433171_.submitModel(this.getParentModel(), p_435883_, p_433452_, this.renderType(), 1728640, OverlayTexture.NO_OVERLAY, p_435883_.outlineColor, null);
				}
			}

			@Override
			public RenderType renderType() {
				return RenderType.eyes(CRACK_LOCATION);
			}
		});
	}

	@Override
	protected void extractAdditionalHitboxes(ShuDofuSpider p_412673_, ImmutableList.Builder<HitboxRenderState> p_412323_, float p_412176_) {
		super.extractAdditionalHitboxes(p_412673_, p_412323_, p_412176_);
		double d0 = -Mth.lerp((double) p_412176_, p_412673_.xOld, p_412673_.getX());
		double d1 = -Mth.lerp((double) p_412176_, p_412673_.yOld, p_412673_.getY());
		double d2 = -Mth.lerp((double) p_412176_, p_412673_.zOld, p_412673_.getZ());

		for (PartEntity<?> enderdragonpart : p_412673_.getParts()) {
			AABB aabb = enderdragonpart.getBoundingBox();
			HitboxRenderState hitboxrenderstate = new HitboxRenderState(
					aabb.minX - enderdragonpart.getX(),
					aabb.minY - enderdragonpart.getY(),
					aabb.minZ - enderdragonpart.getZ(),
					aabb.maxX - enderdragonpart.getX(),
					aabb.maxY - enderdragonpart.getY(),
					aabb.maxZ - enderdragonpart.getZ(),
					(float) (d0 + Mth.lerp((double) p_412176_, enderdragonpart.xOld, enderdragonpart.getX())),
					(float) (d1 + Mth.lerp((double) p_412176_, enderdragonpart.yOld, enderdragonpart.getY())),
					(float) (d2 + Mth.lerp((double) p_412176_, enderdragonpart.zOld, enderdragonpart.getZ())),
					0.25F,
					1.0F,
					0.0F
			);
			p_412323_.add(hitboxrenderstate);
		}
	}

	@Override
	public ShuDofuSpiderRenderState createRenderState() {
		return new ShuDofuSpiderRenderState();
	}

	@Override
	public void extractRenderState(ShuDofuSpider p_362733_, ShuDofuSpiderRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.angry = p_362733_.isAngry();
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

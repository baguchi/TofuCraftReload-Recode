package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.ShuDofuSpiderModel;
import baguchan.tofucraft.entity.ShuDofuSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ShuDofuSpiderRender<T extends ShuDofuSpider> extends MobRenderer<T, ShuDofuSpiderModel<T>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/shudofuspider/shudofuspider.png");
	private static final ResourceLocation CRACK_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/shudofuspider/shudofuspider_angry_layer.png");


	public ShuDofuSpiderRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new ShuDofuSpiderModel<>(p_173956_.bakeLayer(TofuModelLayers.SHUDOFUSPIDER)), 0.5F);
		this.addLayer(new EyesLayer<T, ShuDofuSpiderModel<T>>(this) {
			@Override
			public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, T p_116986_, float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_) {
				if (p_116986_.isAngry()) {
					VertexConsumer vertexconsumer = p_116984_.getBuffer(this.renderType());
					this.getParentModel().renderToBuffer(p_116983_, vertexconsumer, 1728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				}
			}

			@Override
			public RenderType renderType() {
				return RenderType.eyes(CRACK_LOCATION);
			}
		});
	}

	protected float getFlipDegrees(T p_115337_) {
		return 0.0F;
	}

	protected void scale(ShuDofuSpider p_116314_, PoseStack p_116315_, float p_116316_) {
		float var4 = p_116314_.getScale();
		this.shadowRadius = 2.0F;

		p_116315_.scale(var4, var4, var4);
	}


	public ResourceLocation getTextureLocation(T p_114029_) {
		return LOCATION;
	}
}

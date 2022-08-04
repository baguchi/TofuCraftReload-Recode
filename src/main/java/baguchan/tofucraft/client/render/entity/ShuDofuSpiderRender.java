package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.ShuDofuSpiderModel;
import baguchan.tofucraft.entity.ShuDofuSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShuDofuSpiderRender<T extends ShuDofuSpider> extends MobRenderer<T, ShuDofuSpiderModel<T>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/shudofuspider.png");

	public ShuDofuSpiderRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new ShuDofuSpiderModel<>(p_173956_.bakeLayer(TofuModelLayers.SHUDOFUSPIDER)), 0.5F);
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

package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.entity.TofuSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSpiderRender extends MobRenderer<TofuSpider, TofuSpiderModel<TofuSpider>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofuspider/tofuspider.png");

	public TofuSpiderRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofuSpiderModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUSPIDER)), 0.5F);
	}

	protected void scale(TofuSpider p_116314_, PoseStack p_116315_, float p_116316_) {
		float var4 = 1.3F;

		p_116315_.scale(var4, var4, var4);
	}

	@Override
	protected boolean isShaking(TofuSpider p_115304_) {
		return super.isShaking(p_115304_) || p_115304_.isConverting();
	}

	public ResourceLocation getTextureLocation(TofuSpider p_114029_) {
		return LOCATION;
	}
}

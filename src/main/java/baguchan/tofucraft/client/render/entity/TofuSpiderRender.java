package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.client.render.state.TofuSpiderRenderState;
import baguchan.tofucraft.entity.TofuSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSpiderRender extends MobRenderer<TofuSpider, TofuSpiderRenderState, TofuSpiderModel<TofuSpiderRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofuspider/tofuspider.png");

	public TofuSpiderRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofuSpiderModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUSPIDER)), 0.5F);
	}

	@Override
	public TofuSpiderRenderState createRenderState() {
		return new TofuSpiderRenderState();
	}

	@Override
	public void extractRenderState(TofuSpider p_362733_, TofuSpiderRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
	}

	protected void scale(TofuSpiderRenderState p_116314_, PoseStack p_116315_) {
		super.scale(p_116314_, p_116315_);
		float var4 = 1.3F;

		p_116315_.scale(var4, var4, var4);
	}

	@Override
	protected boolean isShaking(TofuSpiderRenderState p_115304_) {
		return super.isShaking(p_115304_) || p_115304_.converting;
	}

	public ResourceLocation getTextureLocation(TofuSpiderRenderState p_114029_) {
		return LOCATION;
	}
}

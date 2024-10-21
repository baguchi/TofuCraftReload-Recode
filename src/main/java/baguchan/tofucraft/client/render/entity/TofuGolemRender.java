package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuGolemModel;
import baguchan.tofucraft.client.render.state.TofuGolemRenderState;
import baguchan.tofucraft.entity.TofuGolem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuGolemRender extends MobRenderer<TofuGolem, TofuGolemRenderState, TofuGolemModel<TofuGolemRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_golem/tofu_golem.png");
	private static final ResourceLocation GLOW_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_golem/tofu_golem_glow.png");

	public TofuGolemRender(EntityRendererProvider.Context p_173954_) {
		super(p_173954_, new TofuGolemModel<>(p_173954_.bakeLayer(TofuModelLayers.TOFU_GOLEM)), 0.3F);
		this.addLayer(new EyesLayer<>(this) {

			@Override
			public RenderType renderType() {
				return RenderType.eyes(GLOW_LOCATION);
			}
		});
	}

	@Override
	public TofuGolemRenderState createRenderState() {
		return new TofuGolemRenderState();
	}

	@Override
	public void extractRenderState(TofuGolem p_362733_, TofuGolemRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.idleAnimationState.copyFrom(p_362733_.idleAnimationState);
		p_360515_.spitAnimationState.copyFrom(p_362733_.spitAnimationState);
	}

	public ResourceLocation getTextureLocation(TofuGolemRenderState p_114015_) {
		return LOCATION;
	}
}
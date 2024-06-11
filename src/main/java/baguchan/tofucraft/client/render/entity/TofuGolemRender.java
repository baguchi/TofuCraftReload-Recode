package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuGolemModel;
import baguchan.tofucraft.entity.TofuGolem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuGolemRender extends MobRenderer<TofuGolem, TofuGolemModel<TofuGolem>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_golem/tofu_golem.png");
	private static final ResourceLocation GLOW_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_golem/tofu_golem_glow.png");

	public TofuGolemRender(EntityRendererProvider.Context p_173954_) {
		super(p_173954_, new TofuGolemModel<>(p_173954_.bakeLayer(TofuModelLayers.TOFU_GOLEM)), 0.3F);
		this.addLayer(new EyesLayer<TofuGolem, TofuGolemModel<TofuGolem>>(this) {

			@Override
			public RenderType renderType() {
				return RenderType.eyes(GLOW_LOCATION);
			}
		});
	}

	public ResourceLocation getTextureLocation(TofuGolem p_114015_) {
		return LOCATION;
	}
}
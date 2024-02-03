package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TFTurretModel;
import baguchan.tofucraft.entity.TFMiningTurret;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TFMiningTurretRenderer<T extends TFMiningTurret> extends MobRenderer<T, TFTurretModel<T>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tf_turret/tf_turret.png");
	private static final ResourceLocation GLOW_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tf_turret/tf_turret_eye.png");

	public TFMiningTurretRenderer(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TFTurretModel<>(p_173956_.bakeLayer(TofuModelLayers.TF_TURRET)), 0.3F);
		this.addLayer(new EyesLayer<>(this) {

			@Override
			public RenderType renderType() {
				return RenderType.eyes(GLOW_LOCATION);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		return LOCATION;
	}
}

package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.Zundamite;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZundamiteRender<T extends Zundamite> extends MobRenderer<T, LivingEntityRenderState, EndermiteModel> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/zundamite.png");

	public ZundamiteRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new EndermiteModel(p_173956_.bakeLayer(ModelLayers.ENDERMITE)), 0.3F);
	}

	@Override
	protected float getFlipDegrees() {
		return 180.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState p_363663_) {
		return LOCATION;
	}

	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
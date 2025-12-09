package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.OageCubeModel;
import baguchi.tofucraft.entity.OageCube;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class OageCubeRenderer extends MobRenderer<OageCube, SlimeRenderState, OageCubeModel<SlimeRenderState>> {
	private static final Identifier MAGMACUBE_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/oage_cube.png");

	public OageCubeRenderer(EntityRendererProvider.Context p_174298_) {
		super(p_174298_, new OageCubeModel<>(p_174298_.bakeLayer(TofuModelLayers.OAGE_CUBE)), 0.25F);
	}


	public Identifier getTextureLocation(SlimeRenderState p_361835_) {
		return MAGMACUBE_LOCATION;
	}

	public SlimeRenderState createRenderState() {
		return new SlimeRenderState();
	}

	public void extractRenderState(OageCube p_362519_, SlimeRenderState p_361851_, float p_361242_) {
		super.extractRenderState(p_362519_, p_361851_, p_361242_);
		p_361851_.squish = Mth.lerp(p_361242_, p_362519_.oSquish, p_362519_.squish);
		p_361851_.size = p_362519_.getSize();
	}

	protected float getShadowRadius(SlimeRenderState p_382806_) {
		return p_382806_.size * 0.25F;
	}

	protected void scale(SlimeRenderState p_362807_, PoseStack p_115390_) {
		int i = p_362807_.size;
		float f = p_362807_.squish / (i * 0.5F + 1.0F);
		float f1 = 1.0F / (f + 1.0F);
		p_115390_.scale(f1 * i, 1.0F / f1 * i, f1 * i);
	}
}

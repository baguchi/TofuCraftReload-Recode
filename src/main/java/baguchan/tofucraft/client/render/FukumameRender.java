package baguchan.tofucraft.client.render;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.render.state.ProjectileRenderState;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FukumameRender extends AbstractSoybeanRender<FukumameEntity, ProjectileRenderState> {
	public static final ResourceLocation FUKUMAME_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/projectiles/fukumame.png");

	public FukumameRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	@Override
	public ProjectileRenderState createRenderState() {
		return new ProjectileRenderState();
	}

	@Override
	public void extractRenderState(FukumameEntity p_362104_, ProjectileRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		p_361028_.xRot = p_362104_.getXRot(p_362204_);
		p_361028_.yRot = p_362104_.getYRot(p_362204_);
	}

	@Override
	protected ResourceLocation getTextureLocation(ProjectileRenderState p113839) {
		return FUKUMAME_LOCATION;
	}

	public ResourceLocation getTextureLocation(FukumameEntity p_116001_) {
		return FUKUMAME_LOCATION;
	}
}
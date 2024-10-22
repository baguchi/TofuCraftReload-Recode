package baguchi.tofucraft.client.render;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import baguchi.tofucraft.entity.projectile.NetherFukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherFukumameRender extends AbstractSoybeanRender<NetherFukumameEntity, ProjectileRenderState> {
	public static final ResourceLocation FUKUMAME_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/projectiles/nether_fukumame.png");

	public NetherFukumameRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	@Override
	public ProjectileRenderState createRenderState() {
		return new ProjectileRenderState();
	}

	@Override
	public void extractRenderState(NetherFukumameEntity p_362104_, ProjectileRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		p_361028_.xRot = p_362104_.getXRot(p_362204_);
		p_361028_.yRot = p_362104_.getYRot(p_362204_);
	}

	public ResourceLocation getTextureLocation(ProjectileRenderState p_116001_) {
		return FUKUMAME_LOCATION;
	}
}
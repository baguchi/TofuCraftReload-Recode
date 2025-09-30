package baguchi.tofucraft.client.render;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.entity.projectile.NetherFukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;


public class NetherFukumameRender extends AbstractSoybeanRender<NetherFukumameEntity, ArrowRenderState> {
	public static final ResourceLocation FUKUMAME_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/projectiles/nether_fukumame.png");

	public NetherFukumameRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}

	@Override
	public void extractRenderState(NetherFukumameEntity p_362104_, ArrowRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		p_361028_.xRot = p_362104_.getXRot(p_362204_);
		p_361028_.yRot = p_362104_.getYRot(p_362204_);
	}

	public ResourceLocation getTextureLocation(ArrowRenderState p_116001_) {
		return FUKUMAME_LOCATION;
	}
}
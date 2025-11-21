package baguchi.tofucraft.client.render;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;


public class ZundaArrowRender extends ArrowRenderer<ZundaArrow, ArrowRenderState> {
	public static final Identifier LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/projectiles/zunda_arrow.png");

	public ZundaArrowRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}

	public Identifier getTextureLocation(ArrowRenderState p_116001_) {
		return LOCATION;
	}
}
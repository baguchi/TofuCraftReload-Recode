package baguchan.tofucraft.client.render;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZundaArrowRender extends ArrowRenderer<ZundaArrow, ArrowRenderState> {
	public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/projectiles/zunda_arrow.png");

	public ZundaArrowRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}

	public ResourceLocation getTextureLocation(ArrowRenderState p_116001_) {
		return LOCATION;
	}
}
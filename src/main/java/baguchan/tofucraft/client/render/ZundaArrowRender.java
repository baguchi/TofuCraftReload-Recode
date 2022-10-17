package baguchan.tofucraft.client.render;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZundaArrowRender extends ArrowRenderer<ZundaArrow> {
	public static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/projectiles/zunda_arrow.png");

	public ZundaArrowRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	public ResourceLocation getTextureLocation(ZundaArrow p_116001_) {
		return LOCATION;
	}
}
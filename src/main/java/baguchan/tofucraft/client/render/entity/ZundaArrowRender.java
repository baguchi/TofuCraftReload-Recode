package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.projectile.ZundaArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZundaArrowRender<T extends ZundaArrowEntity> extends ArrowRenderer<T> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft", "textures/entity/projectiles/zunda_arrow.png");

	public ZundaArrowRender(EntityRendererManager manager) {
		super(manager);
	}

	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
}

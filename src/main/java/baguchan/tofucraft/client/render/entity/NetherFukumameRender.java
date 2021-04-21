package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class NetherFukumameRender<T extends NetherFukumameEntity> extends FukumameRender<T> {
	public static final ResourceLocation FUKUMAME_TEXTURE = new ResourceLocation("tofucraft", "textures/entity/projectiles/nether_fukumame.png");

	public NetherFukumameRender(EntityRendererManager manager) {
		super(manager);
	}

	public ResourceLocation getTextureLocation(T entity) {
		return FUKUMAME_TEXTURE;
	}
}

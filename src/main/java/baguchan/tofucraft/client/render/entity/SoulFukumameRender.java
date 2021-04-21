package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SoulFukumameRender<T extends SoulFukumameEntity> extends FukumameRender<T> {
	public static final ResourceLocation FUKUMAME_TEXTURE = new ResourceLocation("tofucraft", "textures/entity/projectiles/soul_fukumame.png");

	public SoulFukumameRender(EntityRendererManager manager) {
		super(manager);
	}

	public ResourceLocation getTextureLocation(T entity) {
		return FUKUMAME_TEXTURE;
	}
}

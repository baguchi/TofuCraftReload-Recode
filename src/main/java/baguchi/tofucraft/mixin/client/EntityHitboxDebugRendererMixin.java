package baguchi.tofucraft.mixin.client;

import baguchi.tofucraft.entity.ShuDofuSpider;
import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityHitboxDebugRenderer.class)
public class EntityHitboxDebugRendererMixin {

	@Inject(method = "showHitboxes", at = @At(value = "HEAD"))
	public void showHitboxes(Entity p_454832_, float p_456217_, boolean p_455354_, CallbackInfo ci) {
		if (p_454832_ instanceof ShuDofuSpider shuDofuSpider) {
			for (PartEntity<?> part : shuDofuSpider.getParts()) {
				Vec3 vec34 = part.position();
				Vec3 vec35 = part.getPosition(p_456217_);
				Vec3 vec36 = vec35.subtract(vec34);
				Gizmos.cuboid(part.getBoundingBox().move(vec36), GizmoStyle.stroke(ARGB.colorFromFloat(1.0F, 0.25F, 1.0F, 0.0F)));
			}
		}

	}
}

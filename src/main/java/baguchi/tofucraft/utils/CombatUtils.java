package baguchi.tofucraft.utils;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.phys.Vec3;

public class CombatUtils {
	public static float isBlockingWithOutCheck(ServerLevel p_401171_, LivingEntity living, DamageSource p_401067_, float p_401377_) {
		ItemStack itemstack = living.getItemBlockingWith();
		if (itemstack == null) {
			return 0.0F;
		} else {
			BlocksAttacks blocksattacks = itemstack.get(DataComponents.BLOCKS_ATTACKS);
			if (blocksattacks != null) {
				if (p_401067_.getDirectEntity() instanceof AbstractArrow abstractarrow && abstractarrow.getPierceLevel() > 0) {
					return 0.0F;
				} else {
					Vec3 vec3 = p_401067_.getSourcePosition();
					double d0;
					if (vec3 != null) {
						Vec3 vec31 = living.calculateViewVector(0.0F, living.getYHeadRot());
						Vec3 vec32 = vec3.subtract(living.position());
						vec32 = new Vec3(vec32.x, 0.0, vec32.z).normalize();
						d0 = Math.acos(vec32.dot(vec31));
					} else {
						d0 = (float) Math.PI;
					}

					float f = blocksattacks.resolveBlockedDamage(p_401067_, p_401377_, d0);

					return f;

				}
			}
		}
		return 0.0F;
	}
}

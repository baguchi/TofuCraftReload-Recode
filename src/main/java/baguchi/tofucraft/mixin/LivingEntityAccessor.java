package baguchi.tofucraft.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
	@Invoker(value = "jumpOutOfFluid")
	void jumpOutOfFluid(double oldY);

	@Invoker(value = "getWaterSlowDown")
	float getWaterSlowDown();
}
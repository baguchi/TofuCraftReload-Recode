package baguchi.tofucraft.mixin;

import baguchi.tofucraft.entity.behaviors.CoughTask;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = WardenAi.class)
public class WardenAiMixin {


	@Inject(method = "getActivities", at = @At("RETURN"))
	private static void getActivities(Warden body, CallbackInfoReturnable<List<ActivityData<Warden>>> cir) {

		List<ActivityData<Warden>> wardenActivityData = cir.getReturnValue();
		wardenActivityData.add(ActivityData.create(Activity.IDLE, 10, ImmutableList.of(new CoughTask())));
	}
}

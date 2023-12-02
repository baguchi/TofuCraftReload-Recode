package baguchan.tofucraft.mixin;

import baguchan.tofucraft.entity.behaviors.CoughTask;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WardenAi.class)
public class WardenAiMixin {


	@Inject(method = "initIdleActivity", at = @At("HEAD"))
	private static void initIdleActivity(Brain<Warden> p_219537_, CallbackInfo callbackInfo) {
		p_219537_.addActivity(Activity.IDLE, 10, ImmutableList.of(new CoughTask()));
	}
}

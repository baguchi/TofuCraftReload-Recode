package baguchi.tofucraft.mixin;

import baguchi.tofucraft.entity.behaviors.CoughTask;
import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.monster.warden.WardenAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Mixin(value = WardenAi.class)
public class WardenAiMixin {


	@ModifyExpressionValue(
			method = "initCoreActivity",
			at = @At(
					value = "INVOKE",
					target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"
			)
	)
	private static ImmutableList tofucraft$addCough(ImmutableList original) {

		final ArrayList behaviors = new ArrayList<>(original);
		behaviors.add(new CoughTask());
		return ImmutableList.copyOf(behaviors);
	}
}

package baguchan.tofucraft.mixin.client;

import baguchan.tofucraft.api.ISmell;
import baguchan.tofucraft.client.animation.definitions.WardenCoughAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WardenModel.class)
public abstract class WardenModelMixin<T extends Warden> extends HierarchicalModel<T> {

	@Inject(method = "setupAnim", at = @At("TAIL"))
	public void setupAnim(T p_233531_, float p_233532_, float p_233533_, float p_233534_, float p_233535_, float p_233536_, CallbackInfo callbackInfo) {
		this.animate(((ISmell) p_233531_).getCoughAnimationState(), WardenCoughAnimation.COUGH, p_233534_);
	}
}

package baguchan.tofucraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SoyHealthyEffect extends MobEffect {
	public SoyHealthyEffect(MobEffectCategory mobEffectCategory, int i) {
		super(mobEffectCategory, i);
	}

	public void applyEffectTick(LivingEntity livingEntity, int p_301079_) {
		super.applyEffectTick(livingEntity, p_301079_);
		if (livingEntity instanceof Player player) {
			player.causeFoodExhaustion(-0.005F * (float) (p_301079_ + 1));
		}
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}

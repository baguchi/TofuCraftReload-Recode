package baguchan.tofucraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SoyHealthyEffect extends MobEffect {
	public SoyHealthyEffect(MobEffectCategory mobEffectCategory, int i) {
		super(mobEffectCategory, i);
	}

	public boolean applyEffectTick(LivingEntity livingEntity, int p_301079_) {
		super.applyEffectTick(livingEntity, p_301079_);
		if (livingEntity instanceof Player player) {
			player.heal(1);
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
		int i = 160 >> p_294232_;
		return i > 0 ? p_295368_ % i == 0 : true;
	}
}

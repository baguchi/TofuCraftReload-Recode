package baguchi.tofucraft.effect;

import baguchi.bagus_lib.util.client.AnimationUtil;
import baguchi.tofucraft.registry.TofuAnimations;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.gameevent.GameEvent;

public class CoughEffect extends MobEffect {
	public CoughEffect(MobEffectCategory mobEffectCategory, int i) {
		super(mobEffectCategory, i);
	}

	@Override
	public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int p_301079_) {
		super.applyEffectTick(serverLevel, livingEntity, p_301079_);

		if (!livingEntity.level().isClientSide()) {
			AnimationUtil.sendAnimation(livingEntity, TofuAnimations.COUGH);
		}
		if (!(livingEntity instanceof Warden player)) {
			livingEntity.playSound(SoundEvents.PLAYER_BREATH);
			livingEntity.gameEvent(GameEvent.ENTITY_ACTION);
		} else {
			livingEntity.playSound(SoundEvents.WARDEN_HURT, 4.0F, 1.0F);

		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
		int i = 140 >> p_294232_;
		return i > 0 ? p_295368_ % i == 0 : true;
	}
}

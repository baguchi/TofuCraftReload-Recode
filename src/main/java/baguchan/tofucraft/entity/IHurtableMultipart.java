package baguchan.tofucraft.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public interface IHurtableMultipart {
	void onAttackedFromServer(LivingEntity parent2, float damage, DamageSource source);
}

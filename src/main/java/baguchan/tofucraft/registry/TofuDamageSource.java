package baguchan.tofucraft.registry;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;

import javax.annotation.Nullable;

public class TofuDamageSource {
    public static DamageSource zunda(AbstractArrow p_19350_, @Nullable Entity p_19351_) {
        return p_19351_ == null ? (new IndirectEntityDamageSource("tofucraft.onZunda", p_19350_, p_19350_)).setMagic() : (new IndirectEntityDamageSource("tofucraft.zunda", p_19350_, p_19351_)).setMagic();
    }

    public static DamageSource tofu(Entity p_19350_, @Nullable Entity p_19351_) {
        return p_19351_ == null ? (new IndirectEntityDamageSource("tofucraft.onFallingTofu", p_19350_, p_19350_)).damageHelmet() : (new IndirectEntityDamageSource("tofucraft.fallingTofu", p_19350_, p_19351_)).damageHelmet();
    }
}

package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.effect.SoyHealthyEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TofuCraftReload.MODID);

	public static final Holder<MobEffect> SOY_HEALTHY = MOB_EFFECTS.register("soy_healthy", () -> new SoyHealthyEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF));

}

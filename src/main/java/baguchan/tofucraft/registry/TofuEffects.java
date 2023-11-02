package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.effect.SoyHealthyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class TofuEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TofuCraftReload.MODID);

	public static final RegistryObject<MobEffect> SOY_HEALTHY = MOB_EFFECTS.register("soy_healthy", () -> new SoyHealthyEffect(MobEffectCategory.BENEFICIAL, 0));

}

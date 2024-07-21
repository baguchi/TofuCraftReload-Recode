package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.effect.CoughEffect;
import baguchan.tofucraft.effect.SoyHealthyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TofuCraftReload.MODID);

	public static final RegistryObject<MobEffect> SOY_HEALTHY = MOB_EFFECTS.register("soy_healthy", () -> new SoyHealthyEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF));
	public static final RegistryObject<MobEffect> COUGH = MOB_EFFECTS.register("cough", () -> new CoughEffect(MobEffectCategory.HARMFUL, 0xFF7D7D));

}

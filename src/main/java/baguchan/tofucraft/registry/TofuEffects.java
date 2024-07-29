package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.effect.CoughEffect;
import baguchan.tofucraft.effect.SaltBoostEffect;
import baguchan.tofucraft.effect.SoyHealthyEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TofuCraftReload.MODID);

	public static final Holder<MobEffect> SOY_HEALTHY = MOB_EFFECTS.register("soy_healthy", () -> new SoyHealthyEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF));
	public static final Holder<MobEffect> COUGH = MOB_EFFECTS.register("cough", () -> new CoughEffect(MobEffectCategory.HARMFUL, 0xFF7D7D));
	public static final Holder<MobEffect> SALT_BOOST = MOB_EFFECTS.register("salt_boost", () -> new SaltBoostEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_speed"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE).addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_attack_speed"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

}

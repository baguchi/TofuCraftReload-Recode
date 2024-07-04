package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface TofuDamageSource {
	ResourceKey<DamageType> ZUNDA = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "zunda"));
	ResourceKey<DamageType> FALLING_TOFU = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "falling_tofu"));
	ResourceKey<DamageType> SOY_SPORE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "soy_spore"));
	ResourceKey<DamageType> SOY_SPLASH = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "soy_splash"));

}

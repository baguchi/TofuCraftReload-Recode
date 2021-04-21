package baguchan.tofucraft.mixin;

import baguchan.tofucraft.world.SeedHolder;

import java.util.Optional;

import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DimensionGeneratorSettings.class})
public class DimensionGeneratorSettingsMixin {
	@Inject(method = {"<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;Ljava/util/Optional;)V"}, at = {@At("RETURN")})
	private void getSeedFromConstructor(long seed, boolean generateFeatures, boolean bonusChest, SimpleRegistry<Dimension> options, Optional<String> legacyOptions, CallbackInfo ci) {
		SeedHolder.putInSeed(seed);
	}
}

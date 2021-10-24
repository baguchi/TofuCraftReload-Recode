package baguchan.tofucraft.mixin;

import baguchan.tofucraft.world.SeedHolder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldGenSettings.class)
public class WorldGenSettingsMixin {
	@Inject(method = {"<init>(JZZLnet/minecraft/core/MappedRegistry;Ljava/util/Optional;)V"}, at = @At("RETURN"))
	private void getSeedFromConstructor(long seed, boolean p_64615_, boolean p_64616_, MappedRegistry<LevelStem> p_64617_, java.util.Optional<String> p_64618_, CallbackInfo ci) {
		SeedHolder.putInSeed(seed);
	}
}

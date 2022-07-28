package baguchan.tofucraft.mixin.client;

import baguchan.tofucraft.registry.TofuDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Nullable
	@Shadow
	public LocalPlayer player;

	@Inject(method = "getSituationalMusic", at = @At("HEAD"), cancellable = true)
	public void getSituationalMusic(CallbackInfoReturnable<Music> callbackInfo) {
		if (this.player != null) {
			if (this.player.level.dimension() == TofuDimensions.tofu_world) {
				Holder<Biome> holder = this.player.level.getBiome(this.player.blockPosition());
				callbackInfo.setReturnValue(holder.value().getBackgroundMusic().orElse(Musics.GAME));
			}
		}
	}
}

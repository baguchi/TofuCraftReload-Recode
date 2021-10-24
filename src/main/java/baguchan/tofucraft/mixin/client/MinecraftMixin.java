package baguchan.tofucraft.mixin.client;

import baguchan.tofucraft.registry.TofuDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow
	@Final
	private MusicManager musicManager;
	@Shadow
	public ClientLevel level;
	@Shadow
	public LocalPlayer player;

	@Inject(method = {"getSituationalMusic"}, at = @At("HEAD"), cancellable = true)
	public void getSituationalMusic(CallbackInfoReturnable<Music> callbackInfo) {
		if (this.player != null) {
			if (this.player.level.dimension() == TofuDimensions.tofu_world) {
				Biome.BiomeCategory biome$biomecategory = this.player.level.getBiome(this.player.blockPosition()).getBiomeCategory();
				if (!this.musicManager.isPlayingMusic(Musics.UNDER_WATER) && (biome$biomecategory != Biome.BiomeCategory.OCEAN && biome$biomecategory != Biome.BiomeCategory.RIVER)) {
					callbackInfo.setReturnValue(this.level.getBiomeManager().getNoiseBiomeAtPosition(this.player.blockPosition()).getBackgroundMusic().orElse(Musics.GAME));
				}
			}
		}
	}
}
